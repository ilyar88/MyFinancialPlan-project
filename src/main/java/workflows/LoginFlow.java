package workflows;

import extensions.UiActions;
import utilities.WaitForElement;
import io.qameta.allure.Allure;
import pageObjects.LoginPage;
import static utilities.ManagePages.page;

public class LoginFlow {

    public static void userLogin(String email, String password, String uri) {
    	
		Allure.step("User login flow", () -> {
			
			UiActions.enterText(page(LoginPage.class).emailAddress(), email);
	        UiActions.enterText(page(LoginPage.class).password(), password);
	        UiActions.click(page(LoginPage.class).login());
	        WaitForElement.waitUntilUrlContains(uri);
		});
    }

    public static void userLogin_TDD(String email, String password, String uri) {
    	userLogin(email, password, uri);
    }
}
