package co.il.testing.myfinancialplan;

import java.io.IOException;
import org.testng.annotations.Test;
import base.BasePage;
//import extensions.Verifications;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import workflows.LoginFlow;
import workflows.OtpFlow;

public class AdvancedTest extends BasePage {

	public AdvancedTest() throws IOException {
		super();
	}
	
	@Feature("Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User login to the website with credentials (TDD).")
	@Test(priority = 1, description = "User login to website",dataProvider = "loginData", dataProviderClass = LoginFlow.class)
	public void userLoginTest_TDD(String username, String password, String uri) {
		LoginFlow.userLogin_TDD(username, password,uri);
	}
	
	@Feature("OTP")
	@Severity(SeverityLevel.CRITICAL)
	@Description("OTP validation (TDD).")
	@Test(priority = 2, description = "OTP")
	public void otpTest_TDD() {
		OtpFlow.typePassword();
	}
}
