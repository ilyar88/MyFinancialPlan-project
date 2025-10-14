package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage {
	private  WebDriver _driver;
	
	public ProfilePage(WebDriver driver) {
		_driver = driver;
    }
	   
    public WebElement firstName() {
        return _driver.findElement(By.cssSelector("input[name='firstName']"));
    }
    
    public WebElement age() {
        return _driver.findElement(By.cssSelector("input[name='age']"));
    }
    
    public WebElement lastName() {
        return _driver.findElement(By.cssSelector("input[name='lastName']"));
    }
    
    public List<WebElement> dropdowns() {
    	return _driver.findElements(By.cssSelector("select.MuiNativeSelect-select"));
    }
    
    public WebElement kidsCount() {
        return _driver.findElement(By.cssSelector("input[name='kids.count']"));
    }
    
    public List<WebElement> ages() {
        return _driver.findElements(By.cssSelector("input[name^='kids.ages']"));
    }
    
    public WebElement createProfile() {
        return _driver.findElement(By.cssSelector("button[type='submit']"));
    }
}
