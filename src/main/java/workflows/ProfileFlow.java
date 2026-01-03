package workflows;

import extensions.UiActions;
import io.qameta.allure.Allure;
import pageObjects.ProfilePage;
import static utilities.ManagePages.page;
import java.util.function.BiConsumer;
import org.openqa.selenium.WebElement;
import utilities.WaitForElement;

public class ProfileFlow {

	public static void profile(String[] profile, String[] dropdowns, String[] kids) {
		
        Allure.step("Create or update profile flow", () -> {
        	//Enters text into the element if the value is not marked as "Unchanged"     
        	BiConsumer<WebElement, String> value =
        		    (el, val) -> { if (!val.contains("Unchanged")) UiActions.enterText(el, val); };

        	value.accept(page(ProfilePage.class).firstName(), profile[0]);
        	value.accept(page(ProfilePage.class).lastName(), profile[1]);
        	value.accept(page(ProfilePage.class).age(), profile[2]);

            /** 
             * 1) Gender
             * 2) Sole breadwinner
             * 3) Has children?
             * 4) Region
             * 5) City type
             * 6) Residence type */
            for (int i = 0; i < dropdowns.length; i++) {
            	if (!dropdowns[i].contains("Unchanged"))
            		UiActions.selectOption(page(ProfilePage.class).dropdowns().get(i),"value",dropdowns[i]);
            }
            //Number of kids in the family
            WaitForElement.waitFor(page(ProfilePage.class).kidsCount(),"ELEMENT_CLICKABLE", 10);
            UiActions.click(page(ProfilePage.class).kidsCount());
            
            for(int i=0; i < kids.length; i++) {
            	if (kids[i].contains("Unchanged"))
            		continue;
            	
                if (i == 0)
                	UiActions.enterText(page(ProfilePage.class).kidsCount(), kids[i]);               
                else
            		UiActions.enterText(page(ProfilePage.class).ages(i), kids[i]);
            }            
            WaitForElement.waitFor(page(ProfilePage.class).createProfile(),"ELEMENT_CLICKABLE", 10);
            UiActions.click(page(ProfilePage.class).createProfile());
            WaitForElement.waitUntilUrlContains("/introduction");
        });
    }
}
