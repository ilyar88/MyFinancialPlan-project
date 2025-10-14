package workflows;

import java.util.List;
import org.openqa.selenium.WebElement;
import extensions.UiActions;
//import extensions.Verifications;
import io.qameta.allure.Allure;
import utilities.ManagePages;

public class ProfileCreationFlow {
    public static void createProfile(String[] userProfile,String kidsCount, String[] ages) {
        Allure.step("Create profile flow", () -> {
        	UiActions.enterText(ManagePages.profile().firstName(),userProfile[0]);
        	UiActions.enterText(ManagePages.profile().lastName(),userProfile[1]);
        	UiActions.enterText(ManagePages.profile().age(),userProfile[2]);
        	
        	List<WebElement> dropdowns = ManagePages.profile().dropdowns();
        	for (int i = 0; i < dropdowns.size(); i++) {
        		UiActions.selectOption(dropdowns.get(i), "index", userProfile[i+3]);
        	}
        	
        	UiActions.enterText(ManagePages.profile().kidsCount(), kidsCount);
        	List<WebElement> textboxes = ManagePages.profile().ages();
        	for (int i = 0; i < textboxes.size(); i++) {
        		UiActions.enterText(textboxes.get(i), ages[i]);
        	}
        	UiActions.click(ManagePages.profile().createProfile());
        });
    }
}
