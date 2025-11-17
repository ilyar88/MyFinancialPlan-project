package workflows;

import java.util.concurrent.atomic.AtomicInteger;
import extensions.UiActions;
import extensions.Verifications;
import io.qameta.allure.Allure;
import pageObjects.IncomesPage;
import utilities.ManagePages;
import utilities.WaitForElement;

public class IncomesFlow {

    // This variable counts the number of iterations in the data provider. When it equals to zero, run the verification
    public static final AtomicInteger ix = new AtomicInteger(0);

    public static void addIncomes(String names, String amounts) {
        Allure.step("Add incomes flow", () -> {

        	int i = ix.getAndIncrement();
            switch (i) {
                case 0: //First click on the add income button
                    UiActions.click(ManagePages.page(IncomesPage.class).addIncome());
                    break;
                default: //After adding the first income, click the circle button
                    UiActions.click(ManagePages.page(IncomesPage.class).addIncomeIcon());
                    break;
            }

            // Fill current row (name, amount) and click on add button
            UiActions.enterText(ManagePages.page(IncomesPage.class).incomeName(), names);
            UiActions.enterText(ManagePages.page(IncomesPage.class).amount(), amounts);
            UiActions.click(ManagePages.page(IncomesPage.class).add());

            if (ix.get() == names.length()) {
                UiActions.click(ManagePages.page(IncomesPage.class).nextButton());
                String income = UiActions.getText(ManagePages.page(IncomesPage.class).balanceIncome());
                Verifications.verifyText(income, "14000");
                WaitForElement.waitUntilUrlContains("/FinancialPlanMaker/monthly_yearly_expenses");
            }
        });
    }
}
