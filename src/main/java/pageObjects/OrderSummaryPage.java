package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderSummaryPage {
	
	private  WebDriver _driver;
	
	public OrderSummaryPage(WebDriver driver) {
		_driver = driver;
    }
	//Full name textbox
    public WebElement fullname() {
        return _driver.findElement(By.cssSelector("input[name='fullName']"));
    }
    //Email address textbox
    public WebElement emailAddress() {
        return _driver.findElement(By.cssSelector("input[name='email']"));
    }
    //Phone textbox
    public WebElement phone() {
        return _driver.findElement(By.cssSelector("input[name='phone']"));
    }
    
    public WebElement beyondPayment() {
        return _driver.findElement(By.cssSelector("button[type='submit']"));
    }
    public WebElement changePath() {
        return _driver.findElement(By.cssSelector("button[type='button']"));
    }
    
    public List<WebElement> prices() {
        return _driver.findElements(By.cssSelector("h6.MuiTypography-root, h5.MuiTypography-root"));
    }
}
