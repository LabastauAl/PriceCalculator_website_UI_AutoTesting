package pageObjects.emailServicePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class IncomingEmailsPage extends PageObjectBasics {
    public IncomingEmailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='mail']//tr//h2")
    private WebElement receivedStringWithPrise;

    @FindBy(xpath = "//iframe[@id='ifmail']")
    private WebElement iFrame;

    public double receivedPrice;

    public void getPriceFromEmail(){
        driver.switchTo().frame(iFrame);
        System.out.println(receivedStringWithPrise.getText());
        receivedPrice = Double.parseDouble(receivedStringWithPrise.getText().replaceAll("[^0-9.]", ""));
        System.out.println(receivedPrice);
    }

}
