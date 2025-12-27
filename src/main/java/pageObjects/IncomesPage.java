package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IncomesPage {
	
	private  WebDriver _driver;
	
	public IncomesPage(WebDriver driver) {
		_driver = driver;
    }
	//Large add income button
    public WebElement addIncomeOrExpense() {
        return _driver.findElement(By.cssSelector("button.MuiButton-sizeLarge"));
    }
    //Circle add income button
    public WebElement addIncomeIcon() {
        return _driver.findElement(By.cssSelector("button.MuiFab-root[aria-label='add income']"));
    }
    //icons: 1. Revenue summary; 2. Monthly and annual expenses; 3. Emergency fund 4. Recurring expenses; 5. One-time expenses and goals
    public List<WebElement> icons() {
        return _driver.findElements(By.cssSelector("text.MuiStepIcon-text"));       
    }
    //Name of the income or expense in the dialog
    public WebElement name() {
        return _driver.findElement(By.cssSelector("input[role='combobox']"));
    }
    //TextBox: name amount the income in the dialog
    public WebElement amount() {
        return _driver.findElement(By.cssSelector("input[name='amount']"));
    }
    //Add button in the dialog  
    public WebElement add() {
        return _driver.findElement(By.cssSelector("button[type='submit']"));
    }
    //Cancel button in the dialog 
    public WebElement cancel() {
        return _driver.findElement(By.cssSelector("button.MuiButton-outlined"));
    }
    //Amount balance
    public WebElement balanceIncome() {
        return _driver.findElement(By.cssSelector("h6.MuiTypography-root"));
    }//
    //Total net summary
    public WebElement netSummary() {
        return _driver.findElement(By.cssSelector("div:nth-of-type(1) > h5"));
    }//
    //Moving to the next section
    public WebElement nextButton() {
        return _driver.findElement(By.cssSelector("button.MuiButton-root.MuiButton-containedPrimary.MuiButton-sizeMedium"));
    }    
}
