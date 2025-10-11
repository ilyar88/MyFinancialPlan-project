package extensions;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;

public class Verifications {
	
	@Step("Verify equals")
    public static void verifyEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected,
                "Verify equals failed: " + actual + " is not equal to: " + expected);
    }

	@Step("Verify text")
    public static void verifyText(String actual, String expected) {
        Assert.assertTrue(expected != null && expected.contains(actual),
                "Verify equals failed: " + actual + " is not in: " + expected);
    }
    
	@Step("Assert failed")
    public static void assertFailed(String message) {
        Assert.fail(message);
    }

	@Step("Verify that the element is displayed")
    public static void isDisplayed(WebElement elem, boolean expected) {
        boolean actual = elem != null && elem.isDisplayed();

        Assert.assertEquals(actual, expected,
                "Verify display failed, element: " +
                (elem != null ? elem.toString() : "<null>") +
                " expected displayed=" + expected + " but was " + actual);
    }
    
	@Step("Verify text using a soft assert")
    public static void verifyTextSoft(SoftAssert sa, String actual, String expected) {
        sa.assertTrue(expected != null && expected.contains(actual),
                "Verify string (soft) failed: " + actual + " is not in: " + expected);
    }
}
