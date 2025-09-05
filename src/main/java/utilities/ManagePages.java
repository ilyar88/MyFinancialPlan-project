package utilities;

import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class ManagePages {
	
	private static WebDriver _driver;
	
	public static void init(WebDriver driver) {
		_driver = driver;
    }
	
	public static LoginPage login() {
	    return new LoginPage(_driver);
	}
	
	public static RegisterPage register() {
	    return new RegisterPage(_driver);
	}
}
