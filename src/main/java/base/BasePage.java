package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.*;
import workflows.OtpFlow;

public class BasePage {
	/**
	 * Base test class: loads config, initializes WebDriver and
	 * handles page objects + teardown.
	 */
	public static WebDriver driver;
	protected Properties prop;

	public BasePage() throws IOException {
	    prop = new Properties();

	    try (FileInputStream data = new FileInputStream(
	            System.getProperty("user.dir") + "\\src\\main\\java\\configuration\\config.properties")) {
	        prop.load(data);
	    }

	    // Resolve placeholder like ${URL} using env first, then system properties
	    for (String key : prop.stringPropertyNames()) {
	        String val = prop.getProperty(key);
	        if (val != null && val.startsWith("${") && val.endsWith("}")) {
	            String var = val.substring(2, val.length() - 1); 
	            String resolved = System.getenv(var);
	            if (resolved == null) resolved = System.getProperty(var); // if not found, try Java system property (set by Maven)
	            if (resolved != null) prop.setProperty(key, resolved); 
	        }
	    }
	}

	/** Prepare browser before tests: create driver, set waits, open URL, init page objects. */
    @BeforeClass(alwaysRun = true) // runs once before all tests in this class
    public void setup() {
        driver = getDriver();
        driver.manage().window().maximize();
        int waitSeconds = Integer.parseInt(prop.getProperty("waitTime"));
        driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
        driver.get(prop.getProperty("URL"));
        ManagePages.init(driver);
        WaitForElement.init(driver,waitSeconds);
        OtpFlow.setOtp(prop.getProperty("username"), prop.getProperty("appPassword"), "gmail.com");
    }
    
    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    

	public WebDriver getDriver() {
		
	    if (driver != null) return driver;

	    String browser = prop.getProperty("browser");

	    switch (browser) {
	        case "chrome":
	            WebDriverManager.chromedriver().setup();
	            return new ChromeDriver();
	        case "firefox":
	            WebDriverManager.firefoxdriver().setup();
	            return new FirefoxDriver();
	        case "edge":
	            WebDriverManager.edgedriver().setup();
	           return new EdgeDriver();
	        default:
	            throw new IllegalArgumentException("Unsupported browser: " + browser);
	    }
	}
	
	/** Take a screenshot and save it under /target/screenshots/<timestamp>.png. */
	public void takeSnapShot(String name) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "\\target\\screenshots\\"
				+ timestamp() + "_" + name + ".png");

		FileUtils.copyFile(srcFile, destFile);
	}

	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
}
