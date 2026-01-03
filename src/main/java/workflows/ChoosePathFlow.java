package workflows;

import static utilities.ManagePages.*;
import extensions.UiActions;
import extensions.Verifications;
import io.qameta.allure.Allure;
import pageObjects.CongratulationPage;
import pageObjects.CreditCheckoutPage;
import pageObjects.OrderSummaryPage;
import pageObjects.PricingPage;
import utilities.WaitForElement;

public class ChoosePathFlow {
	
	public static void congratulation(String feedback) {
		
		Allure.step("Congratulation flow", () -> {
			//Open a new tab and redirect to api.whatsapp page.
			UiActions.click(page(CongratulationPage.class).buttons().get(0));
			switchToTab();
			WaitForElement.waitUntilUrlContains("/api.whatsapp.com/send");
			closeTab();
			switchToTab();
			UiActions.enterText(page(CongratulationPage.class).feedback(), feedback);
			UiActions.click(page(CongratulationPage.class).buttons().get(2));
			//Wait for form submitted successfully message
			WaitForElement.waitFor(page(CongratulationPage.class).alertText(), "ELEMENT_DISPLAYED", 5);
			//Choosing a financial path
			UiActions.click(page(CongratulationPage.class).buttons().get(1));
			WaitForElement.waitUntilUrlContains("/pricing");
		});
	}
	
	public static void choosePath(String name,String email, String phone) {
		
	    Allure.step("Choose path flow", () -> {
	    	
	    	float ActualPrice = 0f;
	    	//Select Premium path
	    	UiActions.click(page(PricingPage.class).monthlyPay().get(1));
	        UiActions.click(page(PricingPage.class).paths().get(2));
	        WaitForElement.waitUntilUrlContains("/orderSummary");
	        UiActions.enterText(page(OrderSummaryPage.class).fullname(),name);
	        UiActions.enterText(page(OrderSummaryPage.class).emailAddress(),email);
	        UiActions.enterText(page(OrderSummaryPage.class).phone(),phone);
	        for(int i=0; i<page(OrderSummaryPage.class).prices().size()-1; i++)
	        	ActualPrice += Float.parseFloat(UiActions.getText(page(OrderSummaryPage.class).prices().get(i)));
	        
	        String expectedPrice = UiActions.getText(page(OrderSummaryPage.class).prices().get(2));
	        Verifications.verifyEquals(String.valueOf(ActualPrice),expectedPrice);
	        UiActions.click(page(OrderSummaryPage.class).beyondPayment());
	        WaitForElement.waitUntilUrlContains("/credit-checkout");	        
	    });
	}
	
	public static void credit_checkout(String credit, String year, String month, String id) {
		
		Allure.step("Credit checkout flow", () -> {
			//Enter personal details for checkout
			UiActions.enterText(page(CreditCheckoutPage.class).cardNumber(),credit);
			UiActions.selectOption(page(CreditCheckoutPage.class).year(), "value", year);
			UiActions.selectOption(page(CreditCheckoutPage.class).month(), "value",month);
			UiActions.enterText(page(CreditCheckoutPage.class).id(),id);
			UiActions.click(page(CreditCheckoutPage.class).pay());
			OtpFlow.setOtp("", "", "gmail.com");
			OtpFlow.setRegex("^\s*תודה,\s*התשלום\s*בוצע!\s*$");
			String actualText = OtpFlow.getEmailText();
			Verifications.verifyEquals(actualText, "תודה, התשלום בוצע!");
			WaitForElement.waitUntilUrlContains("/home");
		});
	}
}
