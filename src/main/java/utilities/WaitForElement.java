package utilities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForElement {
	
	private static WebDriver _driver;
	private static int _seconds;
	
	public static void init(WebDriver driver, int seconds) {
		_driver = driver;
		_seconds = seconds;
    }
	
	public static void waitFor(String forElement, By locator, int seconds) {
		// Temporarily disable implicit wait to avoid it compounding with explicit wait
		 _driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        
        try {
        	WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(seconds));
            switch (For.parse(forElement)) {
                case ELEMENT_EXISTS:
                    wait.until(ExpectedConditions.presenceOfElementLocated(locator)); break;
                case ELEMENT_DISPLAYED:
                    wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); break;
                case ELEMENT_CLICKABLE:
                    wait.until(ExpectedConditions.elementToBeClickable(locator)); break;
                default:
                    throw new IllegalArgumentException("Unsupported wait condition: " + forElement);
            }
        } finally {
            // Always restore the previously configured implicit wait
        	_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(_seconds));
          }
    }
	
	public static WebElement delayWait(WebElement element, int delaySeconds, int timeoutSeconds) {
	    try {
	    	 _driver.manage().timeouts().implicitlyWait(Duration.ZERO);

	        FluentWait<WebDriver> wait = new FluentWait<>(_driver)
	            .withTimeout(Duration.ofSeconds(delaySeconds + timeoutSeconds))
	            .pollingEvery(Duration.ofSeconds(1))
	            .ignoring(Exception.class);

	        return wait.until(d -> {
	            long elapsed = (System.currentTimeMillis() - delaySeconds) / 1000;
	            if (elapsed < delaySeconds) {
	                return null; // still in "delay" phase
	            }
	            return element.isDisplayed() ? element : null;
	        });
	    } finally {
	    	_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(_seconds));
	    }
	}
	
	enum For {
	    ELEMENT_EXISTS, ELEMENT_DISPLAYED, ELEMENT_CLICKABLE;

	    static For parse(String s) {
	        try {
	            return For.valueOf(
	                s.trim().replace(' ', '_').replace('-', '_').toUpperCase(java.util.Locale.ROOT)
	            );
	        } catch (Exception e) {
	            return ELEMENT_EXISTS; // default
	        }
	    }
	}
}
