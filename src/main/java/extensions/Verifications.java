package extensions;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Allure;
import utilities.ElementUtil;

public class Verifications {

    public static void verifyEquals(String actual, String expected) {
        Allure.step("Verify text: " + actual + " is equal to " + expected, () -> {
            Assert.assertEquals(actual, expected,
                    "Verify equals failed: " + actual + " is not equal to: " + expected);
        });
    }

    public static void verifyText(String actual, String expected) {
        Allure.step("Verify test: " + actual + " is in " + expected, () -> {
            Assert.assertTrue(actual != null && expected != null && actual.contains(expected),
                    "Verify text failed: " + expected + " does not contain: " + actual);
        });
    }

    public static void assertFailed(String message) {
        Allure.step("Assert failed", () -> {
            Assert.fail(message);
        });
    }

    public static void isDisplayed(WebElement elem, boolean expected) {
        Allure.step("Verify element display: " + ElementUtil.name(elem), () -> {
            boolean actual = elem != null && elem.isDisplayed();
            Assert.assertEquals(actual, expected,
                    "Verify display failed, element: " + ElementUtil.name(elem));
        });
    }

    public static void verifyTextSoft(String actual, String expected) {
    	SoftAssert softAssert = new SoftAssert();
        Allure.step("Verify soft assert text: " + actual + " is in " + expected, () -> {
        	softAssert.assertTrue(expected != null && expected.contains(actual),
                    "Verify soft assert failed: " + actual + " is not in: " + expected);
        });
    }
}
