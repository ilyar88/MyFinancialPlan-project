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
        return _driver.findElement(By.xpath("//*[normalize-space(text()),'כתובת מייל)]"));
    }
    
    public WebElement password() {
        return _driver.findElement(By.xpath("//*[normalize-space(text()),'סיסמא)]"));
    }
    
    public WebElement login() {
        return _driver.findElement(By.xpath("//*[normalize-space(text()),'התחברות)]"));
    }
}
