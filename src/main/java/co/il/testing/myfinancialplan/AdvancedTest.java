package co.il.testing.myfinancialplan;

import java.io.IOException;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import base.BasePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import workflows.RegistrationFlow;

public class AdvancedTest extends BasePage {

	public AdvancedTest() throws IOException {
		super();
	}
	
	@Feature("Registration")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User register to the website with credentials (TTD test).")
	@Ignore("Skip user registration TTD test")
	@Test(description = "User register")
	public void userRegisterTest() {
		RegistrationFlow.userRegister(prop.getProperty("username"),prop.getProperty("password"));		
	}
	
	@Feature("OTP")
	@Severity(SeverityLevel.CRITICAL)
	@Description("OTP validation")
	@Ignore("Skip OTP validation")
	@Test(description = "OTP")
	public void otpTest() {
			
	}
}
