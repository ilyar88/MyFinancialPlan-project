package workflows;

import static utilities.ManagePages.*;
import org.openqa.selenium.WebElement;
import extensions.UiActions;
import extensions.Verifications;
import io.qameta.allure.Allure;
import pageObjects.HomePage;
import pageObjects.SumActionsPage;
import utilities.ManagePages;
import utilities.WaitForElement;

public class HomeFlow {
	
	public static void navigateSidebar() {
		
		Allure.step("Navigation sidebar flow", () -> {
				
			for (String uri : getUris()) {
				openNewTab(page(HomePage.class).links(uri)); //Open a new tab from an <a href> element
				switchToTab(); //Switch to last tab
				WaitForElement.waitUntilUrlContains(uri);
				closeTab();
				switchToTab();
			}
		});
	}
	
	public static void notifications() {
		
		Allure.step("Notifications flow", () -> {
			//Click on the notification icon.
			UiActions.click(page(HomePage.class).icons().get(1));
	        for (WebElement item : page(HomePage.class).items()) {	        	
	            openNewTab(item);
	            switchToTab();
	            for (String uri : getUris())
	            {
	            	if(getUrl().contains(uri))
	            	{
		                WaitForElement.waitUntilUrlContains(uri);
		                break;
	            	}
	            }
				closeTab();
				switchToTab();
	        }
		});
	}
	
	public static void actions(int riskOption) {

	    Allure.step("Deposit and withdrawal flow", () -> {

	        UiActions.click(page(HomePage.class).cards("/sumActions"));

	        for (int i = 0; i < 2; i++) {
		        String actualAmount = "";
		        var sumActionsPage = page(SumActionsPage.class);
	            // 1. Without risk 2. Medium risk 3. High risk
	            UiActions.click(sumActionsPage.riskActions().get(riskOption));
	            // Click on deposit / withdrawal button
	            UiActions.click(sumActionsPage.actions().get(0));

	            if (i == 0) { 
	                // Get the amount for the deposit assert
	                actualAmount = UiActions.getText(sumActionsPage.amount());
	            } else {
	                // Withdrawal flow
	                UiActions.click(sumActionsPage.withdrawal());
	                UiActions.click(sumActionsPage.chooseWithdrawal().get(0));
	                // Get the amount for the withdrawal assert
	                actualAmount = UiActions.getText(sumActionsPage.depositAmount().get(0));
	            }
	            UiActions.click(sumActionsPage.confirmButton());
		        // Get the header amount for the selected risk option
		        String expectedAmount = UiActions.getText(sumActionsPage.headerAmount().get(0));
		        Verifications.verifyText(actualAmount, expectedAmount);
	        }
	        ManagePages.goBack();
	        WaitForElement.waitUntilUrlContains("/home");
	    });
	}
	
	public static void anotherPlans() {

	    Allure.step("Another plans flow", () -> {

	    });
	}
	
	public static void logout() {

	    Allure.step("Logout flow", () -> {
	    	
	    	UiActions.click(page(HomePage.class).logout());
	    	WaitForElement.waitUntilUrlContains("/auth/login");
	    });
	}
	
	private static String[] getUris()
	{
		return new String[]{"/home", "/profile", "/sumActions", "/myPlan", "/anotherPlans", "/report", "/feedback", "/contact"};
	}
}