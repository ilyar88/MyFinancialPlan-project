package workflows;

import java.util.concurrent.atomic.AtomicInteger;
import extensions.UiActions;
import io.qameta.allure.Allure;
import pageObjects.ProfilePage;
import utilities.ManagePages;
import utilities.WaitForElement;

public class ProfileCreationFlow {
    // Counts iterations from the DataProvider; when it reaches the end, run the verification.
    private static final AtomicInteger ix = new AtomicInteger(0);

    public static void createProfile(String[] profile, String[] dropdowns, String[] kids) {
        Allure.step("Create profile flow", () -> {
            // Index map: [0]=firstName, [1]=lastName, [2]=age,
            // [3..9]=dropdowns (7 items), [10]=kidsCount, [11..]=kids ages
            final int ddStart = 3, ddEnd = 10, kidsIx = 10, agesStart = 11;

            int i = ix.getAndIncrement();
            switch (i) {
                case 0:
                    UiActions.enterText(ManagePages.page(ProfilePage.class).firstName(), profile[i]);
                    break;
                case 1:
                    UiActions.enterText(ManagePages.page(ProfilePage.class).lastName(), profile[i]);
                    break;
                case 2:
                    UiActions.enterText(ManagePages.page(ProfilePage.class).age(), profile[i]);
                    break;
                default:
                    if (i >= ddStart && i < ddEnd) { //Loop through all dropdown elements
                        UiActions.selectOption(
                            ManagePages.page(ProfilePage.class).dropdowns().get(i -ddStart),
                            "value",dropdowns[i -ddStart]);
                        
                    } else if (i == kidsIx) {
                        UiActions.enterText(ManagePages.page(ProfilePage.class).kidsCount(), kids[i -kidsIx]);
                    } else {
                        UiActions.enterText(
                            ManagePages.page(ProfilePage.class).ages().get(i -agesStart),
                            kids[i -agesStart]);
                    }
                    break;
            }

            // Run verification once, after the last DataProvider iteration.
            if (ix.get() == profile.length + dropdowns.length + kids.length) {
                UiActions.click(ManagePages.page(ProfilePage.class).createProfile());
                WaitForElement.waitUntilUrlContains("/introduction");
            }
        });
    }
}