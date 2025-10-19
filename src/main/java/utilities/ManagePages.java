package utilities;

import org.openqa.selenium.WebDriver;

import pageObjects.*;

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
	
	public static OtpPage otp() {
	    return new OtpPage(_driver);
	}
	
	public static ProfilePage profile() {
	    return new ProfilePage(_driver);
	}
}
