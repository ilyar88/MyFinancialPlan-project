package extensions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.qameta.allure.Allure;
import utilities.ManagePages;

public class UiActions {

    public static void click(WebElement elem) {
    	
        Allure.step("Click on element (" + ManagePages.locatorName(elem) + ")", () -> elem.click());
    }

    public static void enterText(WebElement elem, String text) {
    	//Hide text that equal to environment variable in the allure step
    	String shown = text;
    	for (String val : System.getenv().values()) {
            if (text.equals(val)) {
            	shown = text.replace(val, "*".repeat(val.length()));
            }
        }

        Allure.step("Enter text (" + ManagePages.locatorName(elem) + "): " + shown, () -> {
            elem.clear();
            elem.sendKeys(text);
        });
    }


    public static void selectOption(WebElement elem, String option, String value) {
        Allure.step("Select option (" + ManagePages.locatorName(elem) + "): " + getText(elem), () -> {
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
    	final Pattern NUM = Pattern.compile("\\d[\\d\\s,]*(?:[\\.,]\\d+)?");
    	String t = elem.getText();
        Matcher m = NUM.matcher(t);
        //If a number is found return it; otherwise return the full text
        String r = m.find() ? m.group() : t;
        return Allure.step("Element text: \"" + t + "\" ⇒ \"" + r + "\"", () -> r);
    }
}
