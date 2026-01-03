package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CongratulationPage {
	
	private  WebDriver _driver;
	
	public CongratulationPage(WebDriver driver) {
		_driver = driver;
    }
	//Buttons 1. Share on WhatsApp, 2. Choose path, 3. Send feedback
    public List<WebElement> buttons() {
        return _driver.findElements(By.cssSelector("button[type='button']"));
    }
    
    public WebElement feedback() {
        return _driver.findElement(By.cssSelector("textarea[aria-invalid='false']"));
    }
    //Form submitted successfully
    public WebElement alertText() {
        return _driver.findElement(By.cssSelector("div#notistack-snackbar"));
    }
}
