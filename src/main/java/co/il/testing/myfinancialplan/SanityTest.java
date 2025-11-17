package co.il.testing.myfinancialplan;
import java.io.IOException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utilities.TestData;
import workflows.*;
import base.BasePage;

public class SanityTest extends BasePage {
	
	public SanityTest() throws IOException {
		super();
	}

	@Feature("Registration")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User register to the website with credentials.")
	@Ignore("Skip user registration.")
	@Test(description = "User register")
	public void userRegisterTest() {
		RegistrationFlow.userRegister(prop.getProperty("username"),prop.getProperty("password"));		
	}
	
	@Feature("Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User login to the website with credentials.")
	@Test(priority = 1, description = "User login to website")
	public void userLoginTest() {
		LoginFlow.userLogin(prop.getProperty("username"),prop.getProperty("password"), "/auth/verify");	
		OtpFlow.typePassword("","/introduction");
	}
	
	@Feature("Profile")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Create profile with personal details.")
	@Ignore("Skip profile creation.")
	@Test(priority = 2, description = "Create profile",dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void createProfileTest(String profile, String dropdowns, String kids) {
	    ProfileCreationFlow.createProfile(profile,dropdowns,kids);
	}
	
	@Feature("Introduction")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Open and close introduction goals.")
	@Test(priority = 3, description = "Introduction goals")
	public void introductionTest() {
	    IntroductionFlow.introduction();
	}
	
	@Feature("Incomes")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Add incomes.")
	@Test(priority = 4, description = "Incomes",dataProvider = "dynamicProvider", dataProviderClass = TestData.class)
	public void addIncomesTest(String names, String amounts) {
	    IncomesFlow.addIncomes(names, amounts);
	}
}