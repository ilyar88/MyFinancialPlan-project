package workflows;

import java.util.List;
import org.openqa.selenium.WebElement;
import extensions.UiActions;
import static utilities.ManagePages.page;
import utilities.WaitForElement;
import io.qameta.allure.Allure;
import pageObjects.IntroductionPage;

public class IntroductionFlow {
	
	public static void introduction() {
		Allure.step("Introduction flow", () -> {
			
			List<WebElement> elements = page(IntroductionPage.class).expandIcons();
			for (int i = 0; i < elements.size(); i++) {
				WaitForElement.waitFor(elements.get(i), "ELEMENT_CLICKABLE", 10);
                UiActions.click(elements.get(i));
				WaitForElement.waitFor(elements.get(i), "ELEMENT_CLICKABLE", 10);
                UiActions.click(elements.get(i));
            }
			UiActions.click(page(IntroductionPage.class).letsBegin());
			WaitForElement.waitUntilUrlContains("/FinancialPlanMaker/Incomes");
		});
	}
}
