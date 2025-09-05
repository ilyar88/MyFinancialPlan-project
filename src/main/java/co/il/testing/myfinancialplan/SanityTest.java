package co.il.testing.myfinancialplan;

import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import workflows.RegistrationFlow;
import base.BasePage;

@Listeners(utilities.Listeners.class)
public class SanityTest extends BasePage {
	
	public SanityTest() throws IOException {
		super();
	}

	@Feature("Registration")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Users register on the website using a username and password.")
	@Test
	public void userRegisterTest() {
		RegistrationFlow.userRegister(System.getenv("USERNAME"),System.getenv("PASSWORD"));		
	}
}
