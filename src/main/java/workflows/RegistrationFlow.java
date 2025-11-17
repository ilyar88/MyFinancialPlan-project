package workflows;

import extensions.*;
import utilities.ManagePages;
import utilities.WaitForElement;
import io.qameta.allure.Allure;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class RegistrationFlow {

    public static void userRegister(String email, String password) {
        Allure.step("User registration flow", () -> {
            UiActions.click(ManagePages.page(LoginPage.class).createAccountLink());
            UiActions.enterText(ManagePages.page(RegisterPage.class).emailAddress(), email);
            UiActions.enterText(ManagePages.page(RegisterPage.class).password(), password);
            UiActions.enterText(ManagePages.page(RegisterPage.class).passwordConfirmation(), password);
            UiActions.click(ManagePages.page(RegisterPage.class).CreateAccount());
            WaitForElement.waitUntilUrlContains("/auth/verify");
        });
    }
}
