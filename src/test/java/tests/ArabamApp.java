package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ArabamApp {
    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void arabamSetUp() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");// capabilities.setCapability("deviceName","Pixel 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability("appPackage","com.dogan.arabam");
        // Hangi uygulama uzerinde calismak istiyorsak o uygulamaya ait appPackage degeri yani uygulamanin kimlik bilgisi
        capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");
        // AppPackage da belirlenen uygulamanin hangi sayfasindan baslanacak oldugunu belirlemek icin girilen activity degeri
        driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }


    @Test
    public void arabamTest() throws InterruptedException {
        // driver.activateApp("com.dogan.arabam");
        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        assertTrue(driver.isAppInstalled("com.dogan.arabam"));
        // uygulaminin basarili bir sekilde acildigi dogrulanir
        assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());

        // alt menuden ilan ara butonuna tiklanir
        driver.findElementByXPath("//*[@text='İlan Ara']").click();

        // kategori olarak otomobil secilir
        driver.findElementByXPath("//*[@text='Otomobil']").click();

        // arac olarak Volkswagen secilir
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN);
        Thread.sleep(2000);
        driver.findElementByXPath("//*[@text = 'Volkswagen']").click();

        // arac markasi olarak passat secilir
        driver.findElementByXPath("//*[@text = 'Passat']").click();

        // 1.4 TSI BlueMotion secilir
        driver.findElementByXPath("//*[@text = '1.4 TSi BlueMotion']").click();

        // Paket secimi yapilir
        driver.findElementByXPath("//*[@text = 'Exclusive']").click();

        // Ucuzdan pahaliya siralama yaparak filtreleme yapilir
        driver.findElementById("com.dogan.arabam:id/textViewSorting").click();
        driver.findElementByXPath("//*[@text = 'Fiyat - Ucuzdan Pahalıya']").click();

        // Gelen en ucuz aracin fiyatının, 500.000 tl den buyuk oldugu dogrulanir
        String actualPrice = driver.findElementByXPath("//*[@text= '900.000 TL']").getText();
        int expectedPrice = 500000;
        assertTrue(Integer.parseInt(actualPrice) > expectedPrice);
    }
}