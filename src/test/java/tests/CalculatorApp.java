package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CalculatorApp {
    AndroidDriver<AndroidElement> driver;
    @Test
    public void deneme() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PIXEL");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\user\\IdeaProjects\\Appium\\Apps\\Calculator_8.4 (503542421)_Apkpure.apk");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub") , desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 1- Confirms that the application is installed (isInstalled)
        Assert.assertTrue( driver.isAppInstalled("com.google.android.calculator"));

        // 2- Confirms that the application is opened
        Assert.assertTrue(driver.findElementById("com.google.android.calculator:id/clr").isDisplayed());

        // 3- Let's verify that 200 times 7 equals 1400 using the calculator
        driver.findElementByAccessibilityId("2").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("multipl").click();
        driver.findElementByAccessibilityId("7").click();
        driver.findElementByAccessibilityId("equals").click();
        String result = driver.findElementById("com.google.android.calculator:id/result_final").getText(); // 1400
        Assert.assertEquals(Integer.parseInt(result),1400);
    }
}