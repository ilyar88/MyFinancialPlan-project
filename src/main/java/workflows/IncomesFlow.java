package workflows;

import extensions.UiActions;
import extensions.Verifications;
import io.qameta.allure.Allure;
import pageObjects.IncomesPage;
import static utilities.ManagePages.page;
import utilities.WaitForElement;

public class IncomesFlow {

    private static float totalIncome = (float) 0.0;
    
    public static void addIncomes(String[] names, String[] amounts) {
    	
        Allure.step("Add incomes flow", () -> {
    	
        	for(int i=0; i<names.length; i++)
        	{
        		UiActions.click(i == 0 ? page(IncomesPage.class).addIncomeOrExpense() 
        					           : page(IncomesPage.class).addIncomeIcon());
	
	            // Fill in the income name and amount, then click the Add button
	            UiActions.enterText(page(IncomesPage.class).name(), names[i]);
	            UiActions.enterText(page(IncomesPage.class).amount(), amounts[i]);
	            UiActions.click(page(IncomesPage.class).add());
	            totalIncome += Float.parseFloat(amounts[i]);
        	}
            String income = UiActions.getText(page(IncomesPage.class).netSummary());
            Verifications.verifyText(String.valueOf(totalIncome), income);
            UiActions.click(page(IncomesPage.class).nextButton());
            WaitForElement.waitUntilUrlContains("/FinancialPlanMaker/monthly_yearly_expenses");
        });
    }
    
	 public static float getTotalIncome()  {
		 return totalIncome;
	 }

}
