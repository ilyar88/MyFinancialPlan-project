package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.*;

public class ManagePages {
	
	private static WebDriver _driver;
	
	public static void init(WebDriver driver) {
		_driver = driver;
    }
	
	public static LoginPage login() {
	    return new LoginPage(_driver);
	}
	
	public static RegisterPage register() {
	    return new RegisterPage(_driver);
	}
	
	public static OtpPage otp() {
	    return new OtpPage(_driver);
	}
	
	public static ProfilePage profile() {
	    return new ProfilePage(_driver);
	}
	
    /** Returns a short, readable name for a WebElement's locator. */
    public static String locatorName(WebElement elem) {
    	final Pattern LOCATOR_PAT =
                Pattern.compile(".*->\\s*[^:]+:\\s*(.*?)]");
    	
        if (elem == null) return "<null>";
        String s = elem.toString();
        Matcher m = LOCATOR_PAT.matcher(s);
        return m.matches() ? m.group(1).trim() : s.trim();
    }
}
