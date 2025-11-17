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
	//Buttons: 1. Regular monthly holidays; 2. Variable monthly expenses; 3. Fixed annual expenses; 4. Variable annual expenses
    public List<WebElement> buttonsTab() {
        return _driver.findElements(By.cssSelector("button[role='tab']"));
    }
    
    public WebElement category() {
        return _driver.findElement(By.cssSelector("div[role='combobox']"));
    }
    //List of values in the category
    public WebElement listOfValues(String value) {
        return _driver.findElement(By.cssSelector("ul[role='listbox'] li[role='option'][data-value='" + value + "']"));
    }
}
