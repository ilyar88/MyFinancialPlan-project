package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private  WebDriver _driver;
	
	public HomePage(WebDriver driver) {
		_driver = driver;
	}
    //Icons: 1. Medals 2. Notifications 
    public List<WebElement> icons() {
    	return _driver.findElements(By.cssSelector("button.MuiIconButton-root[type='button']"));
    }
    //Items in the notification list
    public List<WebElement> items() {
    	return _driver.findElements(By.cssSelector("ul.MuiList-root > div.MuiButtonBase-root"));
    }
    /**Links
     * 1) home
     * 2) profile
     * 3) sumActions -> path to goals and dreams
     * 4) myPlan
     * 5) anotherPlans
     * 6) report
     * 7) feedback
     * 8) contact 
     * 9) auth/login -> disconnect */
    public WebElement links(String uri) {
    	return _driver.findElement(By.cssSelector("a.MuiLink-root[href='" + uri + "']"));
    }
    //Cards: 1. Path to goals and dreams 2. My plans 3. Another plans
    public WebElement cards(String uri) {
    	return _driver.findElement(By.cssSelector("div.MuiCardContent-root a[href='" + uri + "']"));
    }
    //Cards: 1. Another plans 2. Home page 3. My plan 4. Path to goals and dreams 
    public List<WebElement> navigationButtons() {
    	return _driver.findElements(By.cssSelector("button.MuiBottomNavigationAction-root"));
    }
}
