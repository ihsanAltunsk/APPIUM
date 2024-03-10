package utilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ReusableMethods {
    static TouchAction touchAction = new TouchAction<>(Driver.getAndroidDriver());
    public static void clickOnCoordinate(int x, int y) throws InterruptedException {
        touchAction.press(PointOption.point(x , y))
                .release()
                .perform();
        Thread.sleep(400);
    }

    public static void scrollPage(int x, int y, int z , int duration){
        touchAction.press(PointOption.point(x , y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                .moveTo(PointOption.point(x , z))
                .release()
                .perform();
    }

    public static void scrollWithUiScrollable(String elementText) {
        AndroidDriver driver = (AndroidDriver)  Driver.getAndroidDriver();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))");
        driver.findElementByXPath("//*[@text='" + elementText + "']").click();
    }

    public static String getScreenshot(String name) throws IOException {
        // Naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot)Driver.getAndroidDriver();

        File source = ts.getScreenshotAs(OutputType.FILE);
        // Full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // Save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}