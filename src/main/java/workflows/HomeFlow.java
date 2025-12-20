package workflows;

import static utilities.ManagePages.*;
import org.openqa.selenium.WebElement;
import extensions.UiActions;
import extensions.Verifications;
import io.qameta.allure.Allure;
import pageObjects.HomePage;
import pageObjects.SumActionsPage;
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
			UiActions.click(page(HomePage.class).cards("sumActions"));	
			//Get the amount header for the assert
			
			String actualAmount = "";
			
			for(int i=0; i<2; i++)
			{	//1. Without risk 2. Medium risk 3. High risk
				UiActions.click(page(SumActionsPage.class).riskActions().get(riskOption));
				//Click on deposit / withdrawal button
				UiActions.click(page(SumActionsPage.class).actions().get(0));
				if(i == 0) //Get the amount for the deposit assert
					actualAmount = UiActions.getText(page(SumActionsPage.class).amount());
				
				else
				{	//Click on the withdrawal option
					UiActions.click(page(SumActionsPage.class).withdrawal());
					UiActions.click(page(SumActionsPage.class).chooseWithdrawal().get(0));
					//Get the amount for the deposit assert
					actualAmount = UiActions.getText(page(SumActionsPage.class).depositAmount().get(0));
				}						
				UiActions.click(page(SumActionsPage.class).confirmButton());
			} //Get the header amount for the selected risk option
			String expectedAmount = UiActions.getText(page(SumActionsPage.class).headerAmount().get(0));
			Verifications.verifyText(actualAmount, expectedAmount);
		});
	}
	
	public static void median() {
		
		Allure.step("Median flow", () -> {


		});
	}
	
	public static void anotherPlans() {
		
		Allure.step("Another plans flow", () -> {


		});
	}
	
	private static String[] getUris()
	{
		return new String[]{"/home", "/profile", "/sumActions", "/myPlan", "/anotherPlans", "/report", "/feedback", "/contact"};
	}
}
