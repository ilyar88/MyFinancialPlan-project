package workflows;

import extensions.*;
import static utilities.ManagePages.page;
import utilities.WaitForElement;
import io.qameta.allure.Allure;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class RegistrationFlow {

    public static void userRegister(String email, String password) {
    	
        Allure.step("User registration flow", () -> {
        	
            UiActions.click(page(LoginPage.class).createAccountLink());
            UiActions.enterText(page(RegisterPage.class).emailAddress(), email);
            UiActions.enterText(page(RegisterPage.class).password(), password);
            UiActions.enterText(page(RegisterPage.class).passwordConfirmation(), password);
            UiActions.click(page(RegisterPage.class).CreateAccount());
            WaitForElement.waitUntilUrlContains("/auth/verify");
        });
    }
}
