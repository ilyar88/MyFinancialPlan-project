package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreditCheckoutPage {
	
	private  WebDriver _driver;
	
	public CreditCheckoutPage(WebDriver driver) {
		_driver = driver;
    }
	//Card number textbox
    public WebElement cardNumber() {
        return _driver.findElement(By.cssSelector("input[name='cardNumber']"));
    }
	
    public WebElement year() {
        return _driver.findElement(By.cssSelector("select[name='expYear']"));
    }
    
    public WebElement month() {
        return _driver.findElement(By.cssSelector("select[name='expMonth']"));
    }
    //Personal id textbox
    public WebElement id() {
        return _driver.findElement(By.cssSelector("input[name='personalId']"));
    }
    
    public WebElement pay() {
        return _driver.findElement(By.cssSelector("button[name='submit-btn']"));
    }
}
