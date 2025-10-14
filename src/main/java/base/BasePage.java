package base;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import utilities.*;
import workflows.OtpFlow;


@Listeners({AllureTestNg.class, utilities.Listeners.class })
public class BasePage {
    /** Base test class: loads config, initializes WebDriver and handles teardown. */
	static WebDriver driver;
    protected Properties prop;

    public BasePage() throws IOException {
        prop = new Properties();
        Path cfg = Paths.get(System.getProperty("user.dir"),
                "src", "main", "java", "configuration", "config.properties");
        try (InputStream input = Files.newInputStream(cfg)) {
            prop.load(input);
        }

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
    @BeforeClass(alwaysRun = true)
    public void setup() {
        Allure.step("Create a " + prop.getProperty("browser") + " WebDriver", () -> {
            driver = getDriver();
            driver.manage().window().setSize(new Dimension(1920, 1080));
            int waitSeconds = Integer.parseInt(prop.getProperty("waitTime"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitSeconds));
        });

        driver.get(prop.getProperty("URL"));
        Allure.step("Open website");

        Allure.step("Initialize page utilities", () -> {
            int waitSeconds = Integer.parseInt(prop.getProperty("waitTime"));
            ManagePages.init(driver);
            WaitForElement.init(driver, waitSeconds);
            OtpFlow.setOtp(prop.getProperty("username"), prop.getProperty("appPassword"), "gmail.com");
        });
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        Allure.step("Quit WebDriver", () -> {
            driver.quit();
        });
    }

    // temp browser profile
    private Path tempProfileDir;

    public WebDriver getDriver() {
        if (driver != null) return driver;

        boolean headless = true;

        if (tempProfileDir == null) {
            try {
                tempProfileDir = Files.createTempDirectory("browser-profile-");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        switch (prop.getProperty("browser")) {
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
                eOpts.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath());
                eOpts.addArguments("--window-size=1920,1080");
                if (headless) eOpts.addArguments("--headless");
                driver = new EdgeDriver(eOpts);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions cOpts = new ChromeOptions();
                cOpts.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath());
                cOpts.addArguments("--window-size=1920,1080");
                if (headless) cOpts.addArguments("--headless");
                driver = new ChromeDriver(cOpts);
        }
        return driver;
    }

    /** Take a screenshot, save it and attach to Allure. */
    public void takeSnapShot(String name) throws IOException {
        Allure.step("Take snapshot", () -> {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File destFile = Paths.get(
                    System.getProperty("user.dir"), "target", "screenshots",name, timestamp() + ".png").toFile();

            // ensure folder exists
            destFile.getParentFile().mkdirs();

            FileUtils.copyFile(srcFile, destFile);

            try (java.io.FileInputStream content = new java.io.FileInputStream(destFile)) {
                Allure.addAttachment(timestamp(), "image/png", content, "png");
            }
        });
    }

    String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}
