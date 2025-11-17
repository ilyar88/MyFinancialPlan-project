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
        return _driver.findElement(By.cssSelector("img[src='/assets/register2-C_iQ8INp.webp']"));
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
    
    public WebElement loginPage() {
        return _driver.findElement(By.cssSelector("a[href='/auth/login']"));
    }
    
    public WebElement alert() {
        return _driver.findElement(By.cssSelector("div[role='alert']"));
    }
}
