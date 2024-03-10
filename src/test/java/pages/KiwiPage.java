package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class KiwiPage {
    public KiwiPage(){
        PageFactory.initElements((WebDriver) Driver.getAndroidDriver(),this);
    }

    @FindBy(xpath = "//*[@text='Continue as a guest']")
    public WebElement verifyElement;

    @FindBy(xpath = "//*[@text='Return']")
    public WebElement tripTypeMenu;

    @FindBy(xpath = "//*[@text='From:']")
    public WebElement departureMenu;

    @FindBy(xpath = "//android.widget.EditText")
    public WebElement searchBox;

    @FindBy(xpath = "//*[@text='Choose']")
    public WebElement chooseButton;

    @FindBy(xpath = "(//*[@class='android.widget.TextView'])[12]")
    public WebElement price;

}