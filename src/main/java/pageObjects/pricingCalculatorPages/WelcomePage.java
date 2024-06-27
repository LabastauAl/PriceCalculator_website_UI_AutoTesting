package pageObjects.pricingCalculatorPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class WelcomePage extends PageObjectBasics {
    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[text()=\"Welcome to Google Cloud's pricing calculator\"]")
    private WebElement welcomeHeader;

    @FindBy(xpath = "//h2[contains (text(), 'Get started with your estimate')]/parent::div//button")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//div[@aria-label='Add to this estimate']//h2[text()='Compute Engine']")
    private WebElement computeEnginePopupCard;

    public void pressAddToEstimateButton() {
        addToEstimateButton.click();
    }

    public void pressComputeEnginePopupCard() {
        computeEnginePopupCard.click();
    }

    public WebElement getWelcomeHeader(){
        return  welcomeHeader;
    }
}
