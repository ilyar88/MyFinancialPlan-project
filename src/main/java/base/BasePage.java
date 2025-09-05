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
import utilities.ManagePages;


public class BasePage {
	
	public static WebDriver driver;
	private Properties prop;

	public BasePage() throws IOException {
		prop = new Properties();
		FileInputStream data = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\configuration\\config.properties");
		prop.load(data);
	}
	
    @BeforeClass(alwaysRun = true) // runs once before all tests in this class
    public void setup() {
        driver = getDriver();
        driver.manage().window().maximize();
        int waitSeconds = Integer.parseInt(prop.getProperty("waitTime"));
        driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
        driver.get(System.getenv("URL"));
        ManagePages.init(driver);
    }
    
    @AfterClass(alwaysRun = true)
    public void teardown() {
    	driver.quit();
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

	public void takeSnapShot(String name) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "\\target\\screenshots\\"
				+ timestamp() + ".png");

		FileUtils.copyFile(srcFile, destFile);
	}

	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
}
