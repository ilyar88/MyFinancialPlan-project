package workflows;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.*;
import org.openqa.selenium.WebElement;
import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.ComparisonTerm;
import jakarta.mail.search.FromStringTerm;
import jakarta.mail.search.ReceivedDateTerm;
import utilities.*;
import extensions.*;

public class OtpFlow {
	
	private static String _user;
	private static String _appPass;
	private static String _domain;
	
	public static void setOtp(String user,String appPass ,String domain) {
		_user = user;
		_appPass = appPass;
		_domain = domain;
    }

    public static void typePassword() {
        List<WebElement> elements = ManagePages.otp().otpPassword();
        String password = "";
        final long deadline = System.currentTimeMillis() + 10_000; // 10 seconds
        final long pollEveryMs = 1_000;

        try {
            while (System.currentTimeMillis() < deadline && (password == null || password.isBlank())) {
                password = getPassword(); 
                if (password == null || password.isBlank()) Thread.sleep(pollEveryMs);
            }
            // If still empty → fail early (don’t try to charAt)
            if (password == null || password.isBlank()) {
                Verifications.assertFailed("OTP password not found in the mail within timeout.");
                return; // make sure we don't continue the flow
            }
            for (int i = 0; i < elements.size(); i++) {
                UiActions.enterText(elements.get(i), String.valueOf(password.charAt(i)));
            }
            
            UiActions.click(ManagePages.otp().verifyPassword());
            WaitForElement.delayWait(ManagePages.otp().passwordAlert(), 1, 10);
            Verifications.isDisplayed(ManagePages.otp().passwordAlert(), false);
            
        } catch (Exception e) {
        	Verifications.assertFailed("Failed while waiting/typing OTP: " + e.getMessage());
        }
    }

 // Returns a string with the (last) 6-digit OTP found in today's emails
 // from senders whose address contains "@{domain}". If no match → "".
 static String getPassword() throws Exception {

	 Properties props = new Properties();
	 props.put("mail.store.protocol", "imaps"); // use IMAPS (TLS)

	 // Open an IMAP Store and ensure it closes automatically
	 try (Store store = Session.getInstance(props).getStore("imaps")) {
	     // Connect to Gmail's IMAP endpoint
	     store.connect("imap.gmail.com", 993, _user, _appPass);

	     // Try to open the Primary category; fall back to INBOX if it doesn't exist
	     Folder folder = firstMail(store, "[Gmail]/Categories/Primary");
	     folder.open(Folder.READ_ONLY); // read-only: we only need to read messages

	     try {
	         // Build a "since start of today" timestamp in your JVM's default timezone
	         Date since = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

	         // Search criteria = (From contains "@{domain}") AND (Received on/after "since")
	         Message[] lines = folder.search(new AndTerm(
	                 new FromStringTerm("@" + _domain),                   // sender filter
	                 new ReceivedDateTerm(ComparisonTerm.GE, since)));    // date filter

	         String sss = "";                   // will hold the result (last OTP found)
	         if (lines.length == 0) return sss; // no emails matched → return empty string

	         // Iterate over all matched messages
	         for (Message m : lines) {
	             // Extract a text representation of the message body
	             String body = htmlToText(m).trim();

	             // Regex for exactly six digits with word boundaries on both sides
	             Pattern OTP_6_DIGITS = Pattern.compile("\\b\\d{6}\\b");

	             // Scan the body for 6-digit sequences
	             Matcher matcher = OTP_6_DIGITS.matcher(body);
	             while (matcher.find()) {
	                 // Save the match. Note: this overwrites on each match,
	                 // so after the loop 'sss' contains the *last* 6-digit code seen
	                 sss = matcher.group();
	             }
	         }
	         // Return the last 6-digit code found across all matching emails ("" if none)
	         return sss;
	     } finally {
	         // Always close the folder (ignore close errors)
	         try { folder.close(false); } catch (Exception ignore) {}
	     }
	 }
 }

 // Picks the first folder from 'names' that exists; otherwise returns INBOX.
 static Folder firstMail(Store store, String... names) throws MessagingException {
     for (String n : names) {
         Folder f = store.getFolder(n);
         if (f != null && f.exists()) return f;
     }
     return store.getFolder("INBOX");
 }

 // Convert a Message/Part content to plain text:
 // - Prefer text/plain
 // - Fall back to cleaned text/html
 // - Recurse through multiparts and embedded messages
 static String htmlToText(Part part) throws Exception {
     // If the part is plain text, return it as-is
     if (part.isMimeType("text/plain")) return String.valueOf(part.getContent());

     // If HTML, strip tags/whitespace and return
     if (part.isMimeType("text/html"))  return normalizeHtml(String.valueOf(part.getContent()));

     // If multipart/*, walk its body parts to find the best text
     if (part.isMimeType("multipart/*")) {
         Multipart mp = (Multipart) part.getContent();
         String htmlFallback = null; // keep HTML as fallback if no plain text part is found

         for (int i = 0; i < mp.getCount(); i++) {
             BodyPart b = mp.getBodyPart(i);

             // Prefer the first text/plain part found
             if (b.isMimeType("text/plain")) return String.valueOf(b.getContent()).trim();

             // Remember text/html in case no plain text exists
             if (b.isMimeType("text/html"))  htmlFallback = String.valueOf(b.getContent());

             // Recurse into nested multiparts or nested messages
             if (b.isMimeType("multipart/*") || b.isMimeType("message/rfc822")) {
                 String nested = htmlToText(b);
                 if (!nested.isBlank()) return nested.trim(); // return first non-blank nested text
             }
         }

         // No plain text found; return cleaned HTML if available, else ""
         return htmlFallback == null ? "" : normalizeHtml(htmlFallback);
     }

     // If the part is an encapsulated message, recurse into it
     if (part.isMimeType("message/rfc822")) return htmlToText((Message) part.getContent());

     return "";
 	}

 	// Minimal HTML → text: remove tags and collapse whitespace
 	private static String normalizeHtml(String html) {
 		return html.replaceAll("(?s)<[^>]*>", " ") // strip tags (including multiline)
                .replaceAll("\\s+", " ")        // collapse whitespace
                .trim();
 	}
}
