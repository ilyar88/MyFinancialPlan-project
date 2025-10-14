package workflows;

import extensions.UiActions;
import extensions.Verifications;
import utilities.ManagePages;
import io.qameta.allure.Allure;

public class LoginFlow {

    public static void userLogin(String email, String password) {
        Allure.step("User login flow", () -> {
            UiActions.enterText(ManagePages.login().emailAddress(), email);
            UiActions.enterText(ManagePages.login().password(), password);
            UiActions.click(ManagePages.login().login());
            Verifications.isDisplayed(ManagePages.otp().registerImage(), true);
        });
    }
}
