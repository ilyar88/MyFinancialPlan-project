package extensions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.qameta.allure.Allure;
import utilities.ElementUtil;

public class UiActions {

    public static void click(WebElement elem) {
    	
        Allure.step("Click on element (" + ElementUtil.name(elem) + ")", () -> elem.click());
    }

    public static void enterText(WebElement elem, String text) {
    	//Hide text that equal to environment variable in the allure step
    	String shown = text;
    	for (String val : System.getenv().values()) {
            if (text.equals(val)) {
            	shown = text.replace(val, "*".repeat(val.length()));
            }
        }

        Allure.step("Enter text (" + ElementUtil.name(elem) + "): " + shown, () -> {
        	elem.click();                               
            elem.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            elem.sendKeys(Keys.DELETE); // Clear the text
            elem.sendKeys(text);
        });
    }


    public static void selectOption(WebElement elem, String option, String value) {
        Allure.step("Select option (" + ElementUtil.name(elem) + "): " + getText(elem), () -> {
            Select dropdown = new Select(elem);
            switch (option) {
                case "value":
                    dropdown.selectByValue(value);
                    break;
                case "index":
                    dropdown.selectByIndex(Integer.parseInt(value));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid select type: " + option);
            }
        });
    }
    
    //Locate an element that appears after a mouse hover
    public static void moveToElement(WebDriver driver, WebElement elem) {
        Allure.step("Move mouse to element",
            () -> new Actions(driver).moveToElement(elem).perform()
        );
    }

    public static String getText(WebElement elem) {
    	//Search for number in the text
        String t = elem.getText();
        String digits = t.replaceAll("\\D+", "");
        String r = digits.isEmpty() ? t : digits;
        return Allure.step("Element text: " + r, () -> r);
    }
}
