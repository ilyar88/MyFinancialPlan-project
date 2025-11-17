package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FinancialGoalsPage {
	
	private  WebDriver _driver;
	
	public FinancialGoalsPage(WebDriver driver) {
		_driver = driver;
    }
	
    public WebElement addGoal() {
        return _driver.findElement(By.cssSelector("button.MuiButton-containedPrimary"));
    }
    
    public WebElement goalName() {
        return _driver.findElement(By.cssSelector("input[role='combobox']"));
    }
    //TextBox: target amount
    public WebElement targetAmount() {
        return _driver.findElement(By.cssSelector("input[name='targetAmount']"));
    }
    //TextBox: if there is an amount dedicated to this expense?
    public WebElement desiredAmount() {
        return _driver.findElement(By.cssSelector("input#\\:r5l\\:"));
    }
    //TextBox: every how many years does this happen?
    public WebElement yearsUntil() {
        return _driver.findElement(By.cssSelector("input[name='yearsUntilEvent']"));
    }   
    //Risk options: 1. Risk-free; 2. Medium risk; 3. High risk
    public List<WebElement> riskOptions() {
        return _driver.findElements(By.cssSelector("button.custom-button"));
    }
    //Save button
    public WebElement save() {
        return _driver.findElement(By.cssSelector("button[data-testid='fg-save']"));
    }
    //Cancel button
    public WebElement cancel() {
        return _driver.findElement(By.cssSelector("button.MuiButton-disableElevation.css-wl6wy6-MuiButtonBase-root-MuiButton-root"));
    }
}
