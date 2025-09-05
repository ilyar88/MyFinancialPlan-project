package workflows;

import extensions.UiActions;
import utilities.ManagePages;

public class RegistrationFlow {
	public static void userRegister(String email, String password) {
		UiActions.click(ManagePages.login().createAccountLink());
		UiActions.enterText(ManagePages.register().emailAddress(), email);
		UiActions.enterText(ManagePages.register().password(), password);
		UiActions.enterText(ManagePages.register().passwordVerification(), password);
		UiActions.click(ManagePages.register().CreateAccount());
	}
}
