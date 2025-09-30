package workflows;

import extensions.*;
import utilities.ManagePages;


public class RegistrationFlow {
	
	public static void userRegister(String email, String password) {
		UiActions.click(ManagePages.login().createAccountLink());
		UiActions.enterText(ManagePages.register().emailAddress(), email);
		UiActions.enterText(ManagePages.register().password(), password);
		UiActions.enterText(ManagePages.register().passwordConfirmation(), password);
		UiActions.click(ManagePages.register().CreateAccount());
		Verifications.isDisplayed(ManagePages.otp().registerImage(),true);
	}
}
