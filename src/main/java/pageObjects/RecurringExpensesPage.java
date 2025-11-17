package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecurringExpensesPage {
	
	private  WebDriver _driver;
	
	public RecurringExpensesPage(WebDriver driver) {
		_driver = driver;
    }
	
    public WebElement addIncome() {
        return _driver.findElement(By.cssSelector("div.MuiBox-root.css-1fs5xc2 > div > button"));
    }
    //TextBox: recurring expense name
    public WebElement incomeName() {
        return _driver.findElement(By.cssSelector("input[role='combobox']"));
    }
    //TextBox: target amount
    public WebElement targetAmount() {
        return _driver.findElement(By.cssSelector("input[name='targetAmount']"));
    }
    //TextBox: if there is an amount dedicated to this expense?
    public WebElement currentAmount() {
        return _driver.findElement(By.cssSelector("input[name='currentAmount']"));
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
        return _driver.findElement(By.cssSelector("button[type='submit']"));
    }
    //Cancel button
    public WebElement cancel() {
        return _driver.findElement(By.cssSelector("button.MuiButton-disableElevation.css-wl6wy6-MuiButtonBase-root-MuiButton-root"));
    }
}

