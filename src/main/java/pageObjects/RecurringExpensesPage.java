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
	
    public WebElement addExpense() {
        return _driver.findElements(By.cssSelector("button.MuiButton-containedPrimary")).get(1);
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
    public WebElement years() {
        return _driver.findElement(By.cssSelector("input[name='yearsUntilEvent']"));
    }
    public WebElement liquidAmount() {
        return _driver.findElements(By.cssSelector("input[type='text'][role='combobox']")).get(1);
    }
    //Risk options: 1. Risk-free; 2. Medium risk; 3. High risk
    public List<WebElement> riskOptions() {
        return _driver.findElements(By.cssSelector("button.custom-button"));
    }
    // 1. Edit button 2. Delete button
    public List<WebElement> editButtons() {
        return _driver.findElements(By.cssSelector("button.MuiButtonBase-root.MuiIconButton-root.MuiIconButton-sizeSmall"));
    }
    //Monthly payment per risk calculation text
    public WebElement monthlyPayment() {
        return _driver.findElement(By.cssSelector("div.MuiAlert-message p"));
    }
    //Confirm delete button for removing a recurring expense
    public WebElement confirmDelete() {
        return _driver.findElement(By.cssSelector("button.MuiButtonBase-root.MuiButton-root.MuiLoadingButton-root"));
    }
}
