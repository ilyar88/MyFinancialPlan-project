package extensions;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Verifications {
    public static void verifyEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected,
                "Verify equals failed: " + actual + " is not equal to: " + expected);
    }

    public static void verifyText(String actual, String expected) {
        Assert.assertTrue(expected != null && expected.contains(actual),
                "Verify equals failed: " + actual + " is not in: " + expected);
    }
    
    public static void assertFailed(String message) {
        Assert.fail(message);
    }

    public static void isDisplayed(WebElement elem, boolean expected) {
        boolean actual = elem != null && elem.isDisplayed();

        Assert.assertEquals(actual, expected,
                "Verify display failed, element: " +
                (elem != null ? elem.toString() : "<null>") +
                " expected displayed=" + expected + " but was " + actual);
    }
    
    public static void verifyTextSoft(SoftAssert sa, String actual, String expected) {
        sa.assertTrue(expected != null && expected.contains(actual),
                "Verify string (soft) failed: " + actual + " is not in: " + expected);
    }
}
