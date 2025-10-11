package base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
	    Path cfg = Paths.get(System.getProperty("user.dir"),
                "src","main","java","configuration","config.properties");
	    try (InputStream input = Files.newInputStream(cfg)) {
	        prop.load(input);
	    }
	    // Resolve placeholder like ${URL} using env first, then system properties
	    for (String key : prop.stringPropertyNames()) {
	        String val = prop.getProperty(key);
	        if (val != null && val.startsWith("${") && val.endsWith("}")) {
	            String var = val.substring(2, val.length() - 1);
	            String resolved = System.getenv(var);
	            if (resolved == null) resolved = System.getProperty(var);
	            if (resolved != null) prop.setProperty(key, resolved);
	        }
	    }
	}

	/** Prepare browser before tests: create driver, set waits, open URL, init page objects. */
    @BeforeClass(alwaysRun = true) // runs once before all tests in this class
    public void setup() {
        driver = getDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        int waitSeconds = Integer.parseInt(prop.getProperty("waitTime"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitSeconds));
        driver.get(prop.getProperty("URL"));
        ManagePages.init(driver);
        WaitForElement.init(driver,waitSeconds);
        OtpFlow.setOtp(prop.getProperty("username"), prop.getProperty("appPassword"), "gmail.com");
    }
    
    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    // add this field if you want to clean temp dirs later
    private Path tempProfileDir;

    public WebDriver getDriver() {
        if (driver != null) return driver;

        String browser = prop.getProperty("browser");
        boolean headless = true;

        // ensure a unique temp profile directory exists before passing to browsers
        if (tempProfileDir == null) {
            try {
                tempProfileDir = Files.createTempDirectory("browser-profile-");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        switch (browser.toLowerCase()) {
            case "firefox":
            	WebDriverManager.firefoxdriver().setup();
            	FirefoxOptions fOpts = new FirefoxOptions();
            	fOpts.addArguments("-profile", tempProfileDir.toAbsolutePath().toString());
                fOpts.addArguments("-width=1920", "-height=1080");
            	if (headless) fOpts.addArguments("-headless");
            	driver = new FirefoxDriver(fOpts);
                break;

            case "edge":
            	WebDriverManager.edgedriver().setup();
            	EdgeOptions eOpts = new EdgeOptions();
            	eOpts.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath().toString());
                eOpts.addArguments("--window-size=1920,1080");
            	if (headless) eOpts.addArguments("--headless");
            	driver = new EdgeDriver(eOpts); 
                break;

            case "chrome":
            default:
            	WebDriverManager.chromedriver().setup();
            	ChromeOptions cOpts = new ChromeOptions();
            	cOpts.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath().toString());
                cOpts.addArguments("--window-size=1920,1080");
            	if (headless) cOpts.addArguments("--headless");
            	driver = new ChromeDriver(cOpts);
        }
        return driver;
    }

	/** Take a screenshot and save it under /target/screenshots/<timestamp>.png. */
	public void takeSnapShot(String name) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destFile = Paths.get(
				System.getProperty("user.dir"),
				"target","screenshots", timestamp() + "_" + name + ".png"
		).toFile();

		FileUtils.copyFile(srcFile, destFile);
	}

	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
}