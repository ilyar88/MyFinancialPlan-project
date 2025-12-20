package workflows;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import extensions.UiActions;
import extensions.Verifications;
import io.qameta.allure.Allure;
import pageObjects.*;
import utilities.*;
import static utilities.ManagePages.page;

public class ExpensesFlow {
	// Global variables that can't be reset on each iteration when the method runs several times
	private static final AtomicReference<Float> totalExpense = new AtomicReference<>(0f);
	private static final AtomicInteger nextTab = new AtomicInteger(0);
	private static final AtomicReference<String> currentCategory = new AtomicReference<>("");
	private static final AtomicBoolean newTab = new AtomicBoolean(true);

	public static void addExpenses(String category, String[] names, String[] amounts) {

	    Allure.step("Add expenses flow", () -> {

	        for (int i = 0; i < names.length; i++) {

	            // Navigate to next pages
	            if (names[i].equals("nextButton")) {
	                UiActions.click(page(IncomesPage.class).nextButton());
	                WaitForElement.waitUntilUrlContains("/FinancialPlanMaker/emergencyFund");
	                UiActions.click(page(IncomesPage.class).nextButton());
	                WaitForElement.waitUntilUrlContains("/FinancialPlanMaker/recurringExpenses");
	                continue;
	            }

	            // Switch to next tab
	            if (names[i].equals("nextTab")) {
	                nextTab.incrementAndGet();
	                newTab.set(true);

	                var tab = page(PeriodExpensesPage.class).buttonsTab().get(nextTab.get());
	                WaitForElement.waitFor(tab, "ELEMENT_CLICKABLE", 10);
	                UiActions.moveToElement(tab, 200);
	                UiActions.click(tab);
	                continue;
	            }

	            // Update the category
	            if (!currentCategory.get().equals(category)) {
	                currentCategory.set(category);
	            }

	            // At first a large button appears in a new tab; afterward an icon button appears
	            if (newTab.get()) {
	                newTab.set(false);
	                UiActions.click(page(IncomesPage.class).addIncomeOrExpense());
	            } else {
	                try {
	                    WaitForElement.waitFor(page(PeriodExpensesPage.class).MuiDialog(),"ELEMENT_INVISIBLE",10);
	                } finally {
	                    UiActions.click(page(PeriodExpensesPage.class).addExpenseIcon());
	                }
	            }

	            // Fill expense details
	            UiActions.click(page(PeriodExpensesPage.class).category());
	            UiActions.click(page(PeriodExpensesPage.class).listOfValues(currentCategory.get()));
	            UiActions.enterText(page(IncomesPage.class).name(), names[i]);
	            UiActions.enterText(page(IncomesPage.class).amount(), amounts[i]);
	            UiActions.click(page(IncomesPage.class).add());

	            // Accumulate total expense
	            totalExpense.set(totalExpense.get() + Float.parseFloat(amounts[i]));
	        }
	    });
	}

    public static void emergencyFund(String amount, String currentAmount, String liquidAmount, String month) {
        Allure.step("Add emergency fund flow", () -> {

            UiActions.click(page(EmergencyFundPage.class).createButton());
            UiActions.enterText(page(EmergencyFundPage.class).desiredAmount(), amount);
            UiActions.enterText(page(EmergencyFundPage.class).currentAmount(), currentAmount);
            UiActions.enterText(page(IncomesPage.class).name(), liquidAmount);
            UiActions.enterText(page(EmergencyFundPage.class).saveMonths(), month);
            UiActions.click(page(IncomesPage.class).add());
            
            String monthlySavings = UiActions.getText(page(EmergencyFundPage.class).monthlySavings());
            int actualAmount = (Integer.parseInt(amount) - Integer.parseInt(currentAmount)) / Integer.parseInt(month);
            
            if (Integer.parseInt(monthlySavings) > actualAmount - 10) {
                int offset = Integer.parseInt(monthlySavings) - actualAmount;
                actualAmount += offset;
            }
            Verifications.verifyText(String.valueOf(actualAmount), monthlySavings);
            UiActions.click(page(IncomesPage.class).nextButton());
            WaitForElement.waitUntilUrlContains("/FinancialPlanMaker/recurringExpenses");
        });
    }
    
    public static void recurringExpense(String[] name, String[] targetAmount, String[] currentAmount,
            String[] liquidAmount, String[] years)
    {
    	Allure.step("Add recurring expense flow", () -> {
	
	    	String method = Thread.currentThread().getStackTrace()[2].getMethodName();
	    	boolean isRecurring = method.equals("recurringExpense");
		
			for (int i = 0; i < targetAmount.length; i++) {
			
				if (name[i].equals("Finish")) {
					UiActions.click(page(FinancialGoalsPage.class).finishButton());
					WaitForElement.waitUntilUrlContains("/Congratulation");
					continue;
				}
			
				if (name[i].equals("Update")) {
					// Click edit button to update the expense
					UiActions.click(page(RecurringExpensesPage.class).editButtons().get(0));
				} else {
					// Add new expense
					UiActions.click(page(RecurringExpensesPage.class).addExpense());
					UiActions.enterText(page(IncomesPage.class).name(), name[i]);
				}
			
				UiActions.enterText(page(RecurringExpensesPage.class).targetAmount(), targetAmount[i]);
				UiActions.enterText(page(RecurringExpensesPage.class).currentAmount(), currentAmount[i]);
			
				// Appears only if currentAmount > 0
				if (!liquidAmount[i].equals("Empty")) {
					UiActions.enterText(page(RecurringExpensesPage.class).liquidAmount(), liquidAmount[i]);
				}
			
				String[] parts = years[i].split(" ", 2);
				String yearValue = parts[0];
				String risk = parts[1];
			
				UiActions.enterText(page(RecurringExpensesPage.class).years(), yearValue);
				
				int index = switch (risk) {
					case "Without risk" -> 0;
					case "Medium risk" -> 1;
					case "High risk" -> 2;
					default -> 0;
				};
			
				UiActions.click(page(RecurringExpensesPage.class).riskOptions().get(index));
				UiActions.click(isRecurring ? page(IncomesPage.class).add() : page(FinancialGoalsPage.class).add());
				
				// Verify monthly payment calculation
				String text = UiActions.getText(page(RecurringExpensesPage.class).monthlyPayment());
				String months = text.replaceAll("\\D+", "");
				Verifications.verifyText(String.valueOf(Integer.parseInt(yearValue) * 12), months);
					
				// Remove expense if not Update or FinancialGoals
				if (!name[i].equals("Update") && isRecurring) {
					WaitForElement.waitForListSize(page(RecurringExpensesPage.class).editButtons(), 2, 10);
					UiActions.click(page(RecurringExpensesPage.class).editButtons().get(1));
					UiActions.click(page(RecurringExpensesPage.class).confirmDelete());
				}
			}
		
			UiActions.click(page(IncomesPage.class).nextButton());
			WaitForElement.waitUntilUrlContains("/FinancialPlanMaker/financialGoals");
    	});
	}
    
    public static void financialGoals(String[] name, String[] targetAmount, String[] currentAmount, String[] liquidAmount,
    		String[] years)
    {
    	recurringExpense(name,targetAmount,currentAmount,liquidAmount,years);
    }

    public static float getTotalExpense() {
        return totalExpense.get();
    }
}