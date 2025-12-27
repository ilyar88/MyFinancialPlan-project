package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyPlanPage {
	
	private  WebDriver _driver;
	
	public MyPlanPage(WebDriver driver) {
		_driver = driver;
    }
    public WebElement category() {
        return _driver.findElement(By.cssSelector("div[role='combobox']"));
    }
    //Category options: 1. Net income; 2. Expenses; 3. Emergency fund; 4. Multi-year recurring expenses; 5. Financial goals
    public WebElement chooseCategory(String value) {
        return _driver.findElement(By.cssSelector("li[role='option'][data-value='" + value + "']"));
    }
	//Find all income or expense names to extract comparison information about the median
    public List<WebElement> headers() {
        return _driver.findElements(By.cssSelector("div.MuiCardContent-root h6.MuiTypography-root"));
    }
	//Find all income or expense names to extract comparison information about the median
    public List<WebElement> amounts() {
        return _driver.findElements(By.cssSelector("div.MuiCardContent-root h5.MuiTypography-root"));
    }
	//Appears when trying to add an invalid amount
    public WebElement errorMessage() {
        return _driver.findElement(By.cssSelector("p.MuiFormHelperText-root.Mui-error"));
    }
	//Editing expenses and incomes
    public List<WebElement> editIcon() {
        return _driver.findElements(By.cssSelector("svg[data-testid='EditIcon']"));
    }
    //Delete expenses and incomes
    public List<WebElement> deleteIcon() {
        return _driver.findElements(By.cssSelector("svg[data-testid='DeleteIcon']"));
    }   
    //This button shows the median salary when hover over on it.
    public List<WebElement> median() {
        return _driver.findElements(By.cssSelector("button[aria-label*='Show median']"));
    }
    //This is used to get the median value from the element for assertion
    public WebElement medianSalary() {
        return _driver.findElement(By.xpath("//*[normalize-space(text())='חציון']"));
    }
}
