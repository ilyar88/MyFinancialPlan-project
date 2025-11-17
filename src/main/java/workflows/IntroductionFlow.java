package workflows;

import java.util.List;
import org.openqa.selenium.WebElement;
import extensions.UiActions;
import utilities.ManagePages;
import utilities.WaitForElement;
import io.qameta.allure.Allure;
import pageObjects.IntroductionPage;

public class IntroductionFlow {
	
	public static void introduction() {
		Allure.step("Introduction flow", () -> {
			List<WebElement> elements = ManagePages.page(IntroductionPage.class).expandIcons();
			for (int i = 0; i < elements.size(); i++) {
                UiActions.click(elements.get(i));
                UiActions.click(elements.get(i));
            }
			UiActions.click(ManagePages.page(IntroductionPage.class).letsBegin());
			WaitForElement.waitUntilUrlContains("/FinancialPlanMaker/Incomes");
		});
	}
}
