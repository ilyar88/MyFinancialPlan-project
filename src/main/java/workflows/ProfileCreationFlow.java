package workflows;

import extensions.UiActions;
import io.qameta.allure.Allure;
import pageObjects.ProfilePage;
import static utilities.ManagePages.page;
import utilities.WaitForElement;

public class ProfileCreationFlow {

	public static void createProfile(String[] profile, String[] dropdowns, String[] kids) {
		
        Allure.step("Create profile flow", () -> {
        	     	
            UiActions.enterText(page(ProfilePage.class).firstName(), profile[0]);
            UiActions.enterText(page(ProfilePage.class).lastName(), profile[1]);
            UiActions.enterText(page(ProfilePage.class).age(), profile[2]);
            /** 
             * 1) Gender
             * 2) Sole breadwinner
             * 3) Has children?
             * 4) Region
             * 5) City type
             * 6) Residence type */
            for (int i = 0; i < dropdowns.length; i++) {
                UiActions.selectOption(page(ProfilePage.class).dropdowns().get(i),"value",dropdowns[i]);
            }
            //Number of kids in the family
            WaitForElement.waitFor(page(ProfilePage.class).kidsCount(),"ELEMENT_CLICKABLE", 10);
            UiActions.click(page(ProfilePage.class).kidsCount());
            UiActions.enterText(page(ProfilePage.class).kidsCount(), kids[0]);
            for(int i=1; i < kids.length; i++)
            	UiActions.enterText(page(ProfilePage.class).ages(i), kids[i]);
            
            WaitForElement.waitFor(page(ProfilePage.class).createProfile(),"ELEMENT_CLICKABLE", 10);
            UiActions.click(page(ProfilePage.class).createProfile());
            WaitForElement.waitUntilUrlContains("/introduction");
        });
    }
}
