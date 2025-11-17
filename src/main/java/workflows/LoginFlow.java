package workflows;

import extensions.UiActions;
import utilities.ManagePages;
import utilities.WaitForElement;
import io.qameta.allure.Allure;
import pageObjects.LoginPage;

public class LoginFlow {

    public static void userLogin(String email, String password, String uri) {
		Allure.step("User login flow", () -> {
			UiActions.enterText(ManagePages.page(LoginPage.class).emailAddress(), email);
	        UiActions.enterText(ManagePages.page(LoginPage.class).password(), password);
	        UiActions.click(ManagePages.page(LoginPage.class).login());
	        WaitForElement.waitUntilUrlContains(uri);
		});
    }

    public static void userLogin_TDD(String email, String password, String uri) {
    	userLogin(email, password, uri);
    }
}
