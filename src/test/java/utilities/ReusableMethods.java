package utilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class ReusableMethods {
    static TouchAction touchAction = new TouchAction<>(Driver.getAndroidDriver());
    public static void clickOnCoordinate(int x, int y) throws InterruptedException {
        touchAction.press(PointOption.point(x , y))
                .release()
                .perform();
        Thread.sleep(500);
    }

    public static void scrollPage(int x, int y, int z , int duration){
        touchAction.press(PointOption.point(x , y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                .moveTo(PointOption.point(x , z))
                .release()
                .perform();
    }
}