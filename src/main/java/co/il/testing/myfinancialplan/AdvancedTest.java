package co.il.testing.myfinancialplan;

import java.io.IOException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BasePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import utilities.TestData;
import workflows.LoginFlow;
import workflows.OtpFlow;
import workflows.ResetPasswordFlow;

public class AdvancedTest extends BasePage {

	public AdvancedTest() throws IOException {
		super();
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Reset password to user (TDD).")
	@Test(priority = 1, description = "Reset password",dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void resetPassword_TDD(String username, String[] password, String[] uri) {
		ResetPasswordFlow.resetPassword(username, password, uri);
	}
	
	@Feature("Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User login to the website with credentials (TDD).")
	@Ignore("Skip user login.")
	@Test(priority = 2, description = "User login to website",dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void userLoginTest_TDD(String username, String password, String uri) {
		LoginFlow.userLogin_TDD(username, password,uri);
	}
	
	@Feature("OTP")
	@Severity(SeverityLevel.CRITICAL)
	@Description("OTP validation (TDD).")
	@Ignore("Skip OTP validation.")
	@Test(priority = 3, description = "OTP",dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void otpTest_TDD(String password, String uri) {
		OtpFlow.typePassword(password,uri);
	}
}
