package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class AllCurrencyConventerApp {

    @Test
    public void allCurrencyConventerAppTest() throws InterruptedException, IOException {
        // Confirm the installation of the "all currency" application.
        assertTrue(Driver.getAndroidDriver().isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));

        // Confirm the opening of the application.
        assertTrue(Driver.getAndroidDriver().findElementByXPath("//*[@text='CURRENCY CONVERTER']").isDisplayed());

        // Choose the currency to be converted as "zloty."
        ReusableMethods.clickOnCoordinate(435,347);
        ReusableMethods.scrollWithUiScrollable("Polish Zloty");

        // Choose the currency to be converted into as "Turkish Lira (TL)."
        ReusableMethods.clickOnCoordinate(424,482);
        ReusableMethods.scrollWithUiScrollable("Turkish Lira");

        // Save the translated amount as a screenshot.
        ReusableMethods.scrollWithUiScrollable("1");
        ReusableMethods.scrollWithUiScrollable("5");
        ReusableMethods.scrollWithUiScrollable("3");
        ReusableMethods.scrollWithUiScrollable("8");

        ReusableMethods.getScreenshot("ihsan");

        // Then, save the TL equivalent of zloty's value.
        WebElement exchangeResult= Driver.getAndroidDriver().findElementById("com.smartwho.SmartAllCurrencyConverter:id/EditTextCurrencyB");
        String resultText = exchangeResult.getText();

        // Notify the user via SMS.
        Driver.getAndroidDriver().sendSMS("555555555555555","Result of the currency you want to convert = " + resultText);
    }
}