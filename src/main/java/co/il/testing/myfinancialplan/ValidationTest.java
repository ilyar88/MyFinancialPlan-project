package co.il.testing.myfinancialplan;

import base.BasePage;
import java.io.IOException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utilities.TestData;
import workflows.*;

public class ValidationTest extends BasePage {

	public ValidationTest() throws IOException {
		super();
	}
	
	@Feature("Reset password")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Reset password to user (TDD).")
	@Test(priority = 1, dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void resetPassword_TDD(String username, String[] password, String[] uri) {
		ResetPasswordFlow.resetPassword(username, password, uri);
	}
	
	@Feature("Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User login to the website with credentials (TDD).")
	@Test(priority = 2, dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void userLoginTest_TDD(String username, String password, String uri) {
		LoginFlow.userLogin_TDD(username, password,uri);
	}
	
	@Feature("OTP")
	@Severity(SeverityLevel.CRITICAL)
	@Description("OTP validation (TDD).")
	@Ignore("Skip OTP validation.")
	@Test(priority = 3, dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void otpTest_TDD(String password, String uri) {
		OtpFlow.typePassword(password,uri);
	}
	
	@Feature("My plan page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Validate the My Plan page, including incomes and expenses.")
	@Ignore("Skip my plan page validation.")
	@Test(priority = 4, dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void myPlan_TDD(String category, String[] names, String[] amounts) {
		MyPlanFlow.updateIncomesOrExpenses(category, names, amounts);
	}
}
