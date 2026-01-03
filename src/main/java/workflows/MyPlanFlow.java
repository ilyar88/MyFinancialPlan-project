package workflows;

import static utilities.ManagePages.page;
import extensions.UiActions;
import extensions.Verifications;
import io.qameta.allure.Allure;
import pageObjects.HomePage;
import pageObjects.IncomesPage;
import pageObjects.MyPlanPage;
import utilities.ManagePages;
import utilities.WaitForElement;

public class MyPlanFlow {
	
	public static void median(String category) {
		
		Allure.step("Median flow", () -> {

	        UiActions.click(page(HomePage.class).cards("/myPlan"));
	        UiActions.click(page(MyPlanPage.class).category());
	        UiActions.click(page(MyPlanPage.class).chooseCategory(category));
	        //Extract comparison information about the median salary
	        var headers = page(MyPlanPage.class).headers();
	        var amounts = page(MyPlanPage.class).amounts();
	        var medians = page(MyPlanPage.class).median();

	        for (int i = 0; i < headers.size(); i++) {

	            StringBuilder text = new StringBuilder();
	            text.append("The median for the ").append(headers.get(i)).append(" with the amount is: ")
	                 .append(amounts.get(i)).append(", ");

	            UiActions.click(medians.get(i));
	            UiActions.moveToElement(page(MyPlanPage.class).medianSalary(), 5);

	            String salary = UiActions.getText(page(MyPlanPage.class).medianSalary());
	            text.append(salary);

	            Allure.step(text.toString());
	        }
	        ManagePages.goBack();
	        WaitForElement.waitUntilUrlContains("/home");
		});
	}
	
	public static void updateIncomesOrExpenses(String category, String[] names, String[] amounts) {

	    Allure.step("Update incomes or expenses flow", () -> {

	        UiActions.click(page(HomePage.class).cards("/myPlan"));
	        UiActions.click(page(MyPlanPage.class).category());
	        UiActions.click(page(MyPlanPage.class).chooseCategory(category));

	        for (int i = 0; i < names.length; i++) {

	            var editIcon = page(MyPlanPage.class).editIcon().get(i);

	            UiActions.moveToElement(editIcon, 10);
	            WaitForElement.waitFor(editIcon, "ELEMENT_CLICKABLE", 10);
	            UiActions.click(editIcon);

	            UiActions.enterText(page(IncomesPage.class).name(), names[i]);
	            UiActions.enterText(page(IncomesPage.class).amount(), amounts[i]);
	            UiActions.click(page(IncomesPage.class).add());

	            //Click the cancel button if the name is empty
	            if (UiActions.getText(page(IncomesPage.class).name()).isBlank()) {
	                UiActions.click(page(IncomesPage.class).cancel());
	            }
	            else if (!UiActions.getText(page(IncomesPage.class).amount()).matches("\\d+(\\.\\d+)?")) 
	                Verifications.assertFailed("Invalid input: amount is not a number.");
	            else {
					try { //If an error message appears due to an invalid number, click the cancel button 
						WaitForElement.waitFor(page(MyPlanPage.class).errorMessage(), "ELEMENT_DISPLAYED", 5);
						UiActions.click(page(IncomesPage.class).cancel());
					} catch (Exception ignored) { }
				}
	        }
	        ManagePages.goBack();
	        WaitForElement.waitUntilUrlContains("/home");
	    });
	}
}
