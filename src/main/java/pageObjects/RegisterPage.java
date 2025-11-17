package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	
	private  WebDriver _driver;
	
	public RegisterPage(WebDriver driver) {
		_driver = driver;
    }
    public WebElement login() {
        return _driver.findElement(By.cssSelector("a[href='/']"));
    }
    
    public WebElement emailAddress() {
    	return _driver.findElement(By.cssSelector("input[name='email']"));
    }

    public WebElement password() {
    	return _driver.findElement(By.cssSelector("input[name='password']"));
    }
    
    public WebElement passwordConfirmation() {
    	return _driver.findElement(By.cssSelector("input[name='passwordConfirmation']"));
    }
    
    public WebElement CreateAccount() {
    	return _driver.findElement(By.cssSelector("button[type='submit']"));
    }
    
    public WebElement fastRegistration() {
    	return _driver.findElement(By.cssSelector("button#\\:rt\\:"));
    }
}