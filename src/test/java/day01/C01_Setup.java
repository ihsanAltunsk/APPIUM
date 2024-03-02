package day01;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class C01_Setup {
    AndroidDriver<AndroidElement> driver; // It enables us to perform operations on Android devices.
    //IOSDriver<IOSElement> iosDriver; - It enables us to perform operations on iOS devices.
    //AppiumDriver<MobileElement> appiumDriver; - It enables us to perform operations on both devices.

    @Test
    public void deneme() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PIXEL");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //uiAutomator2 works only for Android systems from version 6 and above. uiAutomator, on the other hand, works for version 6 and below.
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\user\\IdeaProjects\\Appium\\Apps\\Apk Bilgisi_2.5.2_apkcombo.com.apk");
        //desiredCapabilities.setCapability("deviceName","PIXEL");
        //desiredCapabilities.setCapability("platformName", "Android");
        //desiredCapabilities.setCapability("platformVersion", "10.0");
        //desiredCapabilities.setCapability("automationName", "UiAutomator2");
        //desiredCapabilities.setCapability("app", "C:\\Users\\user\\IdeaProjects\\untitled1\\Apps\\Calculator_3.1.5_Apkpure.apk");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub") , desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}