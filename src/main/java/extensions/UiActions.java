package extensions;

import org.openqa.selenium.WebElement;

public class UiActions {
	
	public static void click(WebElement elem) {
	    elem.click();
	}
	
	public static void enterText(WebElement elem, String text) {
		elem.clear();
	    elem.sendKeys(text);
	}
}
