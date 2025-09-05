package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	
	private  WebDriver _driver;
	
	public RegisterPage(WebDriver driver) {
		_driver = driver;
    }
	//*[@id=":rn:"]
    public WebElement login() {
        return _driver.findElement(By.linkText("התחברות"));
    }
    
    public WebElement emailAddress() {
    	return _driver.findElement(By.cssSelector("input[name='email'][type='text']"));
    }

    public WebElement password() {
    	return _driver.findElement(By.cssSelector("input[name='password'][type='password']"));
    }
    
    public WebElement passwordVerification() {
    	return _driver.findElement(By.cssSelector("input[name='passwordConfirmation'][type='password']"));
    }
    
    public WebElement CreateAccount() {
    	return _driver.findElement(By.cssSelector("button[type='submit']"));
    }
}
