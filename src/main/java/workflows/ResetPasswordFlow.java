package workflows;

import java.util.concurrent.atomic.AtomicInteger;
import base.BasePage;
import extensions.UiActions;
import io.qameta.allure.Allure;
import pageObjects.LoginPage;
import pageObjects.ResetPasswordPage;
import utilities.ManagePages;
import utilities.WaitForElement;

public class ResetPasswordFlow {
	
	private static final AtomicInteger ix = new AtomicInteger(0);

	public static void resetPassword(String[] username, String[] password, String[] uri) {
		Allure.step("Reset password flow", () -> {
			int i = ix.get();
			if(i == 0)
			{
				UiActions.click(ManagePages.page(LoginPage.class).resetPassword());
				WaitForElement.waitUntilUrlContains("/reset-password");
				UiActions.enterText(ManagePages.page(LoginPage.class).emailAddress(), username[i]);
				UiActions.click(ManagePages.page(LoginPage.class).login());
				OtpFlow.setRegex("^https?://testing\\.myfinancialplan\\.co\\.il/auth/new-password/[^/?#]+$");
				String url = OtpFlow.getText();
				BasePage.getUrl(url);
		        WaitForElement.waitUntilUrlContains("/auth/new-password/");
			}
	        UiActions.enterText(ManagePages.page(ResetPasswordPage.class).newPassword(), password[i]);
	        UiActions.enterText(ManagePages.page(ResetPasswordPage.class).confirmPassword(), password[i]);
	        UiActions.click(ManagePages.page(LoginPage.class).login());
	        WaitForElement.waitUntilUrlContains(uri[i]);
		});
		ix.getAndIncrement();
	}
}
