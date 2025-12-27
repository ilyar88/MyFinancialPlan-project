package workflows;

import extensions.UiActions;
import io.qameta.allure.Allure;
import pageObjects.LoginPage;
import pageObjects.ResetPasswordPage;
import static utilities.ManagePages.page;
import static utilities.ManagePages.getUrl;
import utilities.WaitForElement;

public class ResetPasswordFlow {
	
	public static void resetPassword(String username, String[] password, String[] uri) {
		
		Allure.step("Reset password flow", () -> {
			
			for(int i=0; i<password.length; i++)
			{
				if(i == 0)
				{
					UiActions.click(page(LoginPage.class).resetPassword());
					WaitForElement.waitUntilUrlContains("/reset-password");
					UiActions.enterText(page(LoginPage.class).emailAddress(), username);
					UiActions.click(page(LoginPage.class).login());
					//Search for this URL in the mailbox
					OtpFlow.setRegex("^https?://testing\\.myfinancialplan\\.co\\.il/auth/new-password/[^/?#]+$");
					String url = OtpFlow.getText(); //Get the URL address
					getUrl(url);
			        WaitForElement.waitUntilUrlContains("/auth/new-password/");
				}
		        UiActions.enterText(page(ResetPasswordPage.class).newPassword(), password[i]);
		        UiActions.enterText(page(ResetPasswordPage.class).confirmPassword(), password[i]);
		        UiActions.click(page(LoginPage.class).login());
		        WaitForElement.waitUntilUrlContains(uri[i]);
		   }
	   });

	}
}
