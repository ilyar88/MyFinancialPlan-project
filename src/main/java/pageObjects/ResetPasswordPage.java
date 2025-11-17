package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResetPasswordPage {
	
	private  WebDriver _driver;
	
	public ResetPasswordPage(WebDriver driver) {
		_driver = driver;
    }
	
    public WebElement newPassword() {
        return _driver.findElement(By.cssSelector("input[name='newPassword']"));
    }
    
    public WebElement confirmPassword() {
        return _driver.findElement(By.cssSelector("input[name='confirmPassword']"));
    }
    
    public WebElement loginLink() {
        return _driver.findElement(By.cssSelector("a[href='/']"));
    }
}
