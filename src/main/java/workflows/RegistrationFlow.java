package workflows;

import extensions.*;
import io.qameta.allure.Step;
import utilities.ManagePages;


public class RegistrationFlow {
	
	@Step("User registration flow")
	public static void userRegister(String email, String password) {
		UiActions.click(ManagePages.login().createAccountLink());
		UiActions.enterText(ManagePages.register().emailAddress(), email);
		UiActions.enterText(ManagePages.register().password(), password);
		UiActions.enterText(ManagePages.register().passwordConfirmation(), password);
		UiActions.click(ManagePages.register().CreateAccount());
		Verifications.isDisplayed(ManagePages.otp().registerImage(),true);
	}
}
