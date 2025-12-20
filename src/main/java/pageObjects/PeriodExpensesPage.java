package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PeriodExpensesPage {
	private  WebDriver _driver;
	
	public PeriodExpensesPage(WebDriver driver) {
		_driver = driver;
    }
    //Circle add expense button
    public WebElement addExpenseIcon() {
        return _driver.findElement(By.cssSelector("button.MuiFab-root[aria-label='add expense']"));
    }
	//Buttons: 1. Regular monthly holidays; 2. Variable monthly expenses; 3. Fixed annual expenses; 4. Variable annual expenses
    public List<WebElement> buttonsTab() {
        return _driver.findElements(By.cssSelector("button[role='tab']"));
    }
    //Categories: 1. Apartment; 2. InternetAndMedia; 3. HealthAndLeisure; 4. Various
    public WebElement category() {
        return _driver.findElement(By.cssSelector("div[role='combobox']"));
    }
    //List of values in the category
    public WebElement listOfValues(String value) {
        return _driver.findElement(By.cssSelector("li[role='option'][data-value='" + value + "']"));
    }
    //This dialog appear when adding several expenses
    public WebElement MuiDialog() {
    	return _driver.findElement(By.cssSelector("div.MuiDialog-container"));
    }
    //Expenses total amount
    public WebElement totalAmount() {
        return _driver.findElement(By.cssSelector("div > div:nth-of-type(1) > h5"));
    }
}
