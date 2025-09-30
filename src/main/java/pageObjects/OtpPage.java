package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OtpPage {
	private  WebDriver _driver;
	
	public OtpPage(WebDriver driver) {
		_driver = driver;
    }
	
    public WebElement registerImage() {
        return _driver.findElement(By.cssSelector("img[alt='empty content']"));
    }
    
    public List<WebElement> otpPassword() {
        return _driver.findElements(By.cssSelector("input[type='text'][maxlength='1']"));
    }
    
    public WebElement verifyPassword() {
        return _driver.findElement(By.cssSelector("button[type='submit']"));
    }
    
    public WebElement resendPassword() {
        return _driver.findElement(By.cssSelector("a.MuiLink-root.MuiLink-underlineHover"));
    }
    
    public WebElement passwordAlert() {
        return _driver.findElement(By.cssSelector("div[role='alert']"));
    }
}
