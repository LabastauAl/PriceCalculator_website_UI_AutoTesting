package pageObjects.pricingCalculatorPages.numberInputFields;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class BootDiskSize extends PageObjectBasics {
    public BootDiskSize(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[text()='Boot disk size (GiB)']/../../../div[2]//input")
    private WebElement diskSizeField;

    @FindBy(xpath = "//div[text()='Boot disk size (GiB)']/../../../div[2]//button[@aria-label='Increment']")
    private WebElement incrementButton;

    @FindBy(xpath = "//div[text()='Boot disk size (GiB)']/../../../div[2]//button[@aria-label='Decrement']")
    private WebElement decrementButton;

    public void clearInitialValue(){
        actions.moveToElement(diskSizeField).pause(300).click().build().perform();
        diskSizeField.clear();
    }

    public void setBootDiskSize(String value){
        diskSizeField.sendKeys(value);
    }

    public boolean incrementButtonIsActive(){
        actions.pause(500).build().perform();
        return incrementButton.isEnabled();
    }

    public boolean decrementButtonIsActive(){
        actions.pause(500).build().perform();
        return decrementButton.isEnabled();
    }

    public WebElement getBootDiskSizeField(){
        return diskSizeField;
    }
}

