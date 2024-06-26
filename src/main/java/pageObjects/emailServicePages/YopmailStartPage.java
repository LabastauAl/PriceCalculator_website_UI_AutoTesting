package pageObjects.emailServicePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class YopmailStartPage extends PageObjectBasics {
    public YopmailStartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='listeliens']/a[@href='email-generator']")
    private WebElement emailGeneratorButton;

    @FindBy(xpath = "//iframe[@id='aswift_6']")
    private WebElement iframe1;

    @FindBy(xpath = "//iframe[@title='Advertisement' and @id='ad_iframe']")
    private WebElement iframe2;


    @FindBy(xpath = "//div[@id='ad_position_box']")
    private WebElement adBox;

    @FindBy(xpath = "//div[@id='dismiss-button']")
    private WebElement dismissButtonPopup;

    @FindBy(xpath = "//div[contains(@class,'close-button')]")
    private WebElement closeButtonPopup;

    @FindBy(xpath = "//div[@class='tooltip']/button[@id='cprnd']")
    private WebElement copyToClipboardButton;

    @FindBy(xpath = "//div[@class='nw']/button[@class='md but text f24 egenbut'][2]")
    private WebElement checkEmailButton;

    public void generateNewEmail() {
        emailGeneratorButton.click();
        closeAdPopup();
        copyToClipboardButton.click();
    }

    public void goToEmailPage() {
        checkEmailButton.click();
    }

    public void closeAdPopup(){
        actions.moveByOffset(10, 10).click().build().perform();
        //driver.switchTo().frame(iframe1);
        //adBox.click();
        //driver.switchTo().frame(iframe2);
        //dismissButtonPopup.click();
    }
}
