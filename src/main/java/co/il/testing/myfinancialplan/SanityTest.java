package co.il.testing.myfinancialplan;

import java.io.IOException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import io.qameta.allure.*;
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
		OtpFlow.typePassword();
	}
	
	@Feature("Profile")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Create profile with personal details.")
	@Ignore("Skip create profile")
	@Test(priority = 2, description = "Create profile")
	public void createProfileTest() {
		String[] details = {"Ilya","Rahmilevich","37","1","0","0","2","1","1"};
		String[] ages = {"1"};
		ProfileCreationFlow.createProfile(details,"1",ages);	
	}
}