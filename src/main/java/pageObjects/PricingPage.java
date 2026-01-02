package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PricingPage {
	
	private  WebDriver _driver;
	
	public PricingPage(WebDriver driver) {
		_driver = driver;
    }
	//There are three buttons: 1. for base path, 2. for standard path, 3. for Premium path  
    public List<WebElement> paths() {
        return _driver.findElements(By.cssSelector("button[type='button']"));
    }
    //There are two checkboxes for standard and Premium paths
    public List<WebElement> monthlyPay() {
        return _driver.findElements(By.cssSelector("input[type='checkbox']"));
    }
}
