package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static AndroidDriver<AndroidElement> appiumDriver;
    static final String TELEPHONENAME ="PIXEL";
    static final String ANDROIDVERSION="10.0";
    static final String PLATFORM="Android";
    static final String AUTOMATIONNAME ="UiAutomator2";

    public static AndroidDriver getAndroidDriver()  {
        URL appiumServerURL = null;
        try {
            appiumServerURL = new URL("http:127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (appiumDriver == null) {
            DesiredCapabilities caps =new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, TELEPHONENAME);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, ANDROIDVERSION);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATIONNAME);
            caps.setCapability("appPackage","com.smartwho.SmartAllCurrencyConverter");
            caps.setCapability("appActivity","com.smartwho.SmartAllCurrencyConverter.CurrencyConverter");
            caps.setCapability(MobileCapabilityType.NO_RESET,false);
            // If this capability is used as FALSE, after testing the application,
            // user data is cleared every time, and the application is reverted to
            // its initial installed state.

            // If it is set to TRUE, user information is reset after testing,
            // and preferences are saved. Processes continue from where they left off.

            if (ConfigReader.getProperty("platformName").equals("Android")) {
                assert appiumServerURL != null;
                appiumDriver = new AndroidDriver<AndroidElement>(appiumServerURL,caps);
                appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            }else {
                throw new UnsupportedOperationException("Invalid Platform Name ");
            }
        }
        return appiumDriver;
    }

    public static void quitAppiumDriver(){
        if (appiumDriver != null) {
            appiumDriver.closeApp();
            appiumDriver = null;
        }
    }
}