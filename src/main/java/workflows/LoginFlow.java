package workflows;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import extensions.UiActions;
import utilities.ManagePages;
import utilities.WaitForElement;
import io.qameta.allure.Allure;

public class LoginFlow {

    public static void userLogin(String email, String password, String uri) {
        Allure.step("User login flow", () -> {
            UiActions.enterText(ManagePages.login().emailAddress(), email);
            UiActions.enterText(ManagePages.login().password(), password);
            UiActions.click(ManagePages.login().login());
            WaitForElement.waitUntilUrlContains(uri);
        });
    }
    
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
        	{"irahmilevich$gmail.com", "Welcome!", "/auth/login/"},
            {System.getenv("USERNAME"), "12345", "/auth/login/"},
            {"irahmilevich@gmail.c2m", "12345678", "/auth/login/"},
            {"irahmilevichgmail.com", "Welcome!12", "/auth/login/"},
            {System.getenv("USERNAME"), "Passwrd!", "/auth/login/"},
            {"", "", "/auth/login/"},
            {System.getenv("USERNAME"), System.getenv("PASSWORD"), "/auth/verify"}
        };
    }

    @Test(dataProvider = "loginData")
    public static void userLogin_TDD(String email, String password, String uri) {
    	userLogin(email, password, uri);
    }

}
