package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmergencyFundPage {
	
	private  WebDriver _driver;
	
	public EmergencyFundPage(WebDriver driver) {
		_driver = driver;
    }
	//Button that opens the emergency fund dialog
    public WebElement createButton() {
        return _driver.findElement(By.cssSelector("button[aria-label]"));
    }
    //Textbox: What is the desired amount for you?
    public WebElement desiredAmount() {
        return _driver.findElement(By.cssSelector("input[name='targetAmount']"));
    }
    //Textbox: Where is the liquid amount?
    public WebElement currentAmount() {
        return _driver.findElement(By.cssSelector("input[name='currentAmount']"));
    }
    //TextBox: In how many months will you want to save the balance?
    public WebElement saveMonths() {
        return _driver.findElement(By.cssSelector("input[name='monthsToSave']"));
    }
    //h3: Monthly savings number 
    public WebElement monthlySavings() {
        return _driver.findElement(By.cssSelector("div.MuiTypography-h3"));
    }
}
