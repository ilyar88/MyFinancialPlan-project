package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

public class ElementUtil {
   /** Returns a short, readable name for a WebElement's locator. */
    public static String name(WebElement elem) {
    	final Pattern LOCATOR_PAT =
                Pattern.compile(".*->\\s*[^:]+:\\s*(.*?)]");
    	
        if (elem == null) return "<null>";
        String s = elem.toString();
        Matcher m = LOCATOR_PAT.matcher(s);
        return m.matches() ? m.group(1).trim() : s.trim();
    }
}
