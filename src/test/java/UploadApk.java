import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class UploadApk {
    AndroidDriver<AndroidElement> driver;// It enables us to perform operations on Android devices.
    //IOSDriver<IOSElement> iosDriver; - It enables us to perform operations on iOS devices.
    //AppiumDriver<MobileElement> appiumDriver; - It enables us to perform operations on both devices.
    @Test
    public void apkYukleme() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PIXEL");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\ahmet\\IdeaProjects\\Appium_T127\\Apps\\arabam.com_5.1.6_Apkpure.apk");
        //uiAutomator2 works only for Android systems from version 6 and above.
        //uiAutomator, on the other hand, works for version 6 and below.
        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}
