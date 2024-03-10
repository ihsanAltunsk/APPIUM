package tests;

import io.appium.java_client.TouchAction;
import org.testng.annotations.Test;
import pages.KiwiPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import static org.testng.Assert.assertTrue;

public class KiwiApp {
    KiwiPage kiwiPage = new KiwiPage();
    @Test
    public void kiwiAppTest() throws InterruptedException {
        // Verify that the application is installed.
        Driver.getAndroidDriver().isAppInstalled(ConfigReader.getProperty("kiwiBundle"));

        // Confirm that the application is successfully opened.
        assertTrue(kiwiPage.verifyElement.isDisplayed());

        // Click on "Continue as a guest".
        kiwiPage.verifyElement.click();

        // Proceed through the upcoming 3 steps by clicking the green button.
        for (int i = 0; i < 3; i++) {
            ReusableMethods.clickOnCoordinate(532,1700);
        }

        // Select "Trip type, one way".
        kiwiPage.tripTypeMenu.click();
        ReusableMethods.clickOnCoordinate(600,1500);

        // Click on the departure country option and remove the default country.
        kiwiPage.departureMenu.click();
        ReusableMethods.clickOnCoordinate(1017,136);

        // Enter the departure country/city and click on "select".
        if (!Driver.getAndroidDriver().isKeyboardShown()){
            kiwiPage.searchBox.sendKeys("Ankara");
        } else {
            Driver.getAndroidDriver().getKeyboard().pressKey("Istanbul");
        }
        Thread.sleep(1500);
        ReusableMethods.clickOnCoordinate(482,289);
        kiwiPage.chooseButton.click();

        // Click on the arrival country option and enter the destination country.
        ReusableMethods.clickOnCoordinate(508,917);
        Driver.getAndroidDriver().getKeyboard().pressKey("Antalya");
        Thread.sleep(1500);
        ReusableMethods.clickOnCoordinate(482,289);
        kiwiPage.chooseButton.click();

        // Select the departure date as September 21 and click on "set date".
        ReusableMethods.clickOnCoordinate(537,1048);
        ReusableMethods.clickOnCoordinate(541,1140);
        ReusableMethods.clickOnCoordinate(716,1721);

        // Click on the "search" button.
        ReusableMethods.clickOnCoordinate(541,1205);
        Thread.sleep(3000);

        // Apply filters for the cheapest and non-stop options.
        ReusableMethods.clickOnCoordinate(252,252);
        ReusableMethods.clickOnCoordinate(563,584);
        ReusableMethods.clickOnCoordinate(519,256);
        ReusableMethods.clickOnCoordinate(511,1458);
        Thread.sleep(2000);
        // Save the incoming ticket price and send it to the user's phone as an SMS.
        String ticketPrice = kiwiPage.price.getText();
        Driver.getAndroidDriver().sendSMS("55555555555555",
                "The price of the ticket you are looking for in the Kiwi app = " + ticketPrice);

    }
}