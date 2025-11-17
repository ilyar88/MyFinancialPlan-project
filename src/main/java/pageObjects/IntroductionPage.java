package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IntroductionPage {
	
	private  WebDriver _driver;
	
	public IntroductionPage(WebDriver driver) {
		_driver = driver;
    }
	
    public List<WebElement> expandIcons() {
        return _driver.findElements(By.cssSelector("svg.MuiBox-root"));
    }
    
    public WebElement letsBegin() {
        return _driver.findElement(By.cssSelector("button.MuiButton-root"));
    }
 }
