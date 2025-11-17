package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private  WebDriver _driver;
	
	public LoginPage(WebDriver driver) {
		_driver = driver;
    }
	
    public WebElement createAccountLink() {
        return _driver.findElement(By.cssSelector("a[href='/auth/register']"));
    }
    
    public WebElement emailAddress() {
        return _driver.findElement(By.cssSelector("input[name='email']"));
    }
    
    public WebElement password() {
        return _driver.findElement(By.cssSelector("input[name='password']"));
    }
    
    public WebElement login() {
        return _driver.findElement(By.cssSelector("button[type='submit']"));
    }

    public WebElement fastLogin() {
        return _driver.findElement(By.cssSelector("button#\\:rv\\:"));
    }
    
    public WebElement resetPassword() {
        return _driver.findElement(By.cssSelector("a[href='/auth/reset-password']"));
    }
}
