package utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ManagePages {
    private static WebDriver _driver;

    public static void init(WebDriver driver) {
    	_driver = driver;
    }
    // Creates and returns a page object instance of the given class using the WebDriver.
    public static <T> T page(Class<T> type) {
        try {
            return type.getConstructor(WebDriver.class).newInstance(_driver);
        } catch (Exception e) {
            throw new RuntimeException("Can't create " + type.getSimpleName(), e);
        }
    }
    // Open new browser tab.
    public static void openNewTab(WebElement elem) {
        Actions actions = new Actions(_driver);
        actions.keyDown(Keys.CONTROL).click(elem).keyUp(Keys.CONTROL).perform();
    }
    // Switch to the last browser tab.
    public static void switchToTab() {
        String lastHandle = null;
        
        for (String handle : _driver.getWindowHandles()) {
            lastHandle = handle;
        }
        _driver.switchTo().window(lastHandle);
    }
    
	public static void getUrl(String url) {
		_driver.get(url);
	}
	
	public static String getUrl() {
	    return _driver.getCurrentUrl();
	}
	//Navigate to previous URL.
	public static void goBack() {
		_driver.navigate().back();
    }
    
    public static void closeTab() {
    	_driver.close();
    }
}