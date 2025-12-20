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
        return _driver.findElement(By.cssSelector("input[name='user-age-input']"));
    }
    
    public WebElement lastName() {
        return _driver.findElement(By.cssSelector("input[name='lastName']"));
    }
    // Dropdowns: 1. Marital status; 2. Gender; 3. Single breadwinner; 4. Residential area; 5. City type; 6. Type of residence
    public List<WebElement> dropdowns() {
    	return _driver.findElements(By.cssSelector("select.MuiNativeSelect-select"));
    }
    
    public WebElement kidsCount() {
        return _driver.findElement(By.cssSelector("input[name='kids-count-input']"));
    }
    
    public WebElement ages(int index) {
        return _driver.findElement(By.cssSelector(String.format("input[name='child-age-input-%d']", index-1)));
    }

    public WebElement createProfile() {
        return _driver.findElement(By.cssSelector("button[type='submit']"));
    }
}
