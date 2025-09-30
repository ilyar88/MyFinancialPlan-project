package extensions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UiActions {
	
	public static void click(WebElement elem) {
	    elem.click();
	}
	
	public static void enterText(WebElement elem, String text) {
		elem.clear();
	    elem.sendKeys(text);
	}
	
	public static void selectOption(WebElement elem,String option, String value) {
	    Select dropdown = new Select(elem);

	    switch (option) {
	        case "value":
	            dropdown.selectByValue(value);
	            break;
	        case "index":
	            dropdown.selectByIndex(Integer.parseInt(value));
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid select type: " + value);
	    }
	}

}
