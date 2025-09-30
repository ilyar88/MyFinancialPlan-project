package co.il.testing.myfinancialplan;

import java.io.IOException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import workflows.*;
import base.BasePage;

@Listeners(utilities.Listeners.class)
public class SanityTest extends BasePage {
	
	public SanityTest() throws IOException {
		super();
	}

	@Feature("Registration")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User register on the website using a username and password.")
	@Ignore("Skipping until registration flow is stable")
	@Test
	public void userRegisterTest() {
		RegistrationFlow.userRegister(prop.getProperty("username"),prop.getProperty("password"));		
	}
	
	@Feature("Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Users login on the website using a username and password.")
	@Test
	public void userLoginTest() {
		LoginFlow.userLogin(prop.getProperty("username"),prop.getProperty("password"));	
		OtpFlow.typePassword();
	}
}
