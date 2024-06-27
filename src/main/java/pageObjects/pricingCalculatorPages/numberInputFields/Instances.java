package pageObjects.pricingCalculatorPages.numberInputFields;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class Instances extends PageObjectBasics {
    public Instances(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[text()='Number of instances' and text()='*']/../../div[3]//input")
    private WebElement numberOfInstancesField;

    @FindBy(xpath = "//div[2]/span[text()='Required field']")
    private WebElement warningFieldRequired;

    @FindBy(xpath = "//div[2]/span[text()='Value needs to be greater than 0 and less than or equal to 50,000']")
    private WebElement warningValuesRange;

    @FindBy(xpath = "//div[text()='Number of instances' and text()='*']/../../div[3]//button[@aria-label='Decrement']")
    private WebElement decrementButton;



    public void deleteInitialValue(){
        actions.moveToElement(numberOfInstancesField).click().pause(300).build().perform();
        numberOfInstancesField.sendKeys(Keys.BACK_SPACE);
    }
    public void clearNumberOfInstances(){
        actions.moveToElement(numberOfInstancesField).click().pause(300).build().perform();
        numberOfInstancesField.clear();
    }
    public void setNumberOfInstances(String value){
        numberOfInstancesField.sendKeys(value);
    }

    public boolean warningFieldRequiredPresence(){
        actions.pause(200).build().perform();
        return checkWebElementDisplayed(warningFieldRequired);
    }

    public boolean warningValuesRangePresence(){
        actions.pause(200).build().perform();
        return checkWebElementDisplayed(warningValuesRange);
    }

    public boolean decrementButtonClickable(){
        actions.pause(500).build().perform();
        return decrementButton.isEnabled();
    }
}
