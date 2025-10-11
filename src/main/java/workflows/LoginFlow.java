package workflows;

import extensions.UiActions;
import extensions.Verifications;
import io.qameta.allure.Step;
import utilities.ManagePages;

public class LoginFlow {
	
	@Step("User login to the website flow")
	public static void userLogin(String email, String password) {
		UiActions.enterText(ManagePages.login().emailAddress(),email);
		UiActions.enterText(ManagePages.login().password(), password);
		UiActions.click(ManagePages.login().login());
		Verifications.isDisplayed(ManagePages.otp().registerImage(),true);
		
	}
}
