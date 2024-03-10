package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ArabamApp {
    AndroidDriver<AndroidElement> driver;
    @BeforeTest
    public void arabamSetUp() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PIXEL");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");

        capabilities.setCapability("appPackage","com.dogan.arabam");
        // The appPackage value, which is the identification information of the application,
        // is the one we want to work on. This corresponds to the application we want to operate on.

        capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");
        // The entered activity value to determine from which page of the application identified in AppPackage will be started.

        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void arabamTest() throws InterruptedException {
        // Verified that the application is successfully installed.
        assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        // Verified that the application is successfully opened.
        assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());

        // The "İlan Ara" button is clicked on the sub-menu.
        driver.findElementByXPath("//*[@text='İlan Ara']").click();

        // "Otomobil" is selected as the category.
        //  driver.findElementByXPath("//*[@text='Otomobil']").click(); // Classic method used for clicking

        Thread.sleep(1000);
        TouchAction action = new TouchAction<>(driver); // The driver corresponding to the device on which the operation will be performed.
        action.
                press(PointOption.point(994,500)) // The section where width and height information for clicking on the screen is entered.
                                                                  // Width and height information are obtained from the inspector.
                .release() // After performing the click operation, lifting our finger from the screen to ensure that the click is executed.
                .perform(); // The command given to the action methods to perform the given action tasks.
        Thread.sleep(500);

        for (int i=0; i<5; i++){
            action
                    .press(PointOption.point(482,1516)) // The initial point to be determined on the screen for scrolling.
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(750))) // The duration that determines the speed between the start and end.
                    // If the duration is shorter, more distance is covered. In other words, a faster downward movement occurs on the screen.
                    // If the duration is longer, less distance is covered. In other words, a slower downward movement occurs on the screen.
                    .moveTo(PointOption.point(482,320)) // The transition from the initially determined point to the final point,
                                                                        // representing the last phase of the scrolling process.
                    .release() // Lifting the finger from the screen.
                    .perform(); // These operations are carried out through the 'perform' method.
            Thread.sleep(500);
        }

        // Select "Volkswagen" as the vehicle.
        driver.findElementByXPath("//*[@text='Volkswagen']").click();

        // Select "Passat" as the vehicle model.
        driver.findElementByXPath("//*[@text='Passat']").click();

        // Select 1.4 TSI BlueMotion option.
        driver.findElementByXPath("//*[@text='1.4 TSi BlueMotion']").click();
        Thread.sleep(500);

        // Make package selection.
        // 500 700
        action
                .press(PointOption.point(500,700))
                .release()
                .perform();
        Thread.sleep(500);

        // Do filtering by sorting from cheap to expensive.
        driver.findElementById("com.dogan.arabam:id/textViewSorting").click();
        Thread.sleep(500);
        driver.findElementByXPath("//*[@text='Fiyat - Ucuzdan Pahalıya']").click();

        // Confirm that the cheapest incoming vehicle is greater than 500,000 TL.
        AndroidElement cheapestVehiclePriceElement =driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvPrice'])[1]");
        String lastPriceForVehicle = cheapestVehiclePriceElement.getText();
        System.out.println(lastPriceForVehicle); // 670.000 TL
        lastPriceForVehicle = lastPriceForVehicle.replaceAll("\\D","");
        System.out.println(lastPriceForVehicle);

        assertTrue(Integer.parseInt(lastPriceForVehicle)>500000);
    }
}