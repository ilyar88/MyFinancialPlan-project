package extensions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

public class UiActions {
	
	@Step("Click on element")
	public static void click(WebElement elem) {
	    elem.click();
	}
	
	@Step("Enter text")
	public static void enterText(WebElement elem, String text) {
		elem.clear();
	    elem.sendKeys(text);
	}
	
	@Step("Select option")
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
	
	@Step("Get the element’s text")
	public static String getText(WebElement elem) {
		return elem.getText();
	}

}
