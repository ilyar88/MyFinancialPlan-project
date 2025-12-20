package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SumActionsPage {
	private  WebDriver _driver;
	
	public SumActionsPage(WebDriver driver) {
		_driver = driver;
	}
	//Buttons for the following risks: 1. Without risk 2. Medium risk 3. High risk
    public List<WebElement> riskActions() {
    	return _driver.findElements(By.cssSelector("button[aria-label='open actions menu']"));
    }
    //Header showing the deposit amount
    public List<WebElement> headerAmount () {
    	return _driver.findElements(By.cssSelector("h2.MuiTypography-h3"));
    }
    /**The following actions are:
     * 1. deposit / withdrawal 
     * 2. Where is the money? */
    public List<WebElement> actions() {
    	return _driver.findElements(By.cssSelector("ul[role='menu'] li[role='menuitem']"));
    }
    
    public WebElement deposit() {
    	return _driver.findElements(By.cssSelector("button[value='deposit']")).get(0);
    }
    
    public WebElement withdrawal() {
    	return _driver.findElements(By.cssSelector("button[value='withdrawal']")).get(0);
    }

    public WebElement amount() {
    	return _driver.findElements(By.cssSelector("input[type='text'][inputmode='decimal']")).get(0);
    }
    //1. Monthly deposit amount update 2. Update deposit amount and period
    public List<WebElement> chooseWithdrawal() {
    	return _driver.findElements(By.cssSelector("button[type='button'].MuiButton-outlinedPrimary"));
    }
    //Deposit amount text for assert check
    public List<WebElement> depositAmount() {
    	return _driver.findElements(By.cssSelector("li.MuiListItem-root span"));
    }
    
    public WebElement confirmButton() {
    	return _driver.findElements(By.cssSelector("button[type='button'].MuiButton-containedSuccess")).get(0);
    }
}
