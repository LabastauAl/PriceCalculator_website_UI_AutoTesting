package pageObjects.pricingCalculatorPages.numberInputFields;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class UsageTime extends PageObjectBasics {
    public UsageTime(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[text()='Total instance usage time' and text()='*']/../../div[3]//input")
    private WebElement usageTimeField;

    @FindBy(xpath = "//div[text()='Total instance usage time' and text()='*']/../../span[text() = 'Required field']")
    private  WebElement warningFieldRequired;

    @FindBy(xpath = "//div[text()='Total instance usage time' and text()='*']/../../span[text() = 'Value needs to be greater than 0 and less than or equal to 36,500,000']")
    private WebElement warningValuesRange;

    public void deleteInitialValue(){
        actions.moveToElement(usageTimeField).click().pause(300).build().perform();
        usageTimeField.sendKeys(Keys.BACK_SPACE);
        usageTimeField.sendKeys(Keys.BACK_SPACE);
        usageTimeField.sendKeys(Keys.BACK_SPACE);
    }
    public void clearUsageTime(){
        actions.moveToElement(usageTimeField).click().pause(300).build().perform();
        usageTimeField.clear();
    }
    public void setUsageTime(String value){
        usageTimeField.sendKeys(value);
    }

    public boolean warningFieldRequiredPresence(){
        actions.pause(500).build().perform();
        return checkWebElementDisplayed(warningFieldRequired);
    }

    public boolean warningValuesRangePresence(){
        actions.pause(200).build().perform();
        return checkWebElementDisplayed(warningValuesRange);
    }

}
