package pageObjects.prisingCalcPagesLegacy;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;
import utils.JavaScriptMethods;

public class EstimateRightSidebar extends PageObjectBasics {
    public EstimateRightSidebar(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//md-card-content[@id='resultBlock']//div[contains(text(), 'Provisioning model:')]")
    private WebElement resultBlock_provisioningModel;

    @FindBy(xpath = "//md-card-content[@id='resultBlock']//div[contains(text(), 'Instance type:')]")
    private WebElement resultBlock_instanceType;

    @FindBy(xpath = "//md-card-content[@id='resultBlock']//div[contains(text(), 'Region:')]")
    private WebElement resultBlock_region;

    @FindBy(xpath = "//md-card-content[@id='resultBlock']//div[contains(text(), 'Local SSD:')]")
    private WebElement resultBlock_localSSD;

    @FindBy(xpath = "//md-card-content[@id='resultBlock']//div[contains(text(), 'Commitment term:')]")
    private WebElement resultBlock_commitmentTerm;


    @FindBy(xpath = "//md-card-content[@id='resultBlock']//div[contains(text(), 'Instance type:')]/following-sibling::div[@class='ng-binding']")
    private WebElement instancePrice;
    @FindBy(xpath = "//md-card-content[@id='resultBlock']//div[contains(text(), 'Local SSD:')]/following-sibling::div[@class='ng-binding']")
    private WebElement localSSDPrice;
    @FindBy(xpath = "//md-card-content[@id='resultBlock']//div[contains(text(), 'GPU dies:')]/following-sibling::div[@class='ng-binding']")
    private WebElement GPUsPrice;
    @FindBy(xpath = "//span[text()='Persistent Disk (Accompanying)']/ancestor::md-toolbar/following-sibling::div//b[@class='ng-binding']")
    private WebElement bootDiskPrice;

    @FindBy(xpath = "//div[@class='cpc-cart-total']//b[contains(normalize-space(text()), 'Total Estimated Cost:')]")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//button[@id='Email Estimate']/span")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//md-input-container//input[@type='email']")
    private WebElement emailPopupField;

    @FindBy(xpath = "//button[(contains (text(), 'Send Email'))]")
    private WebElement sendEmailPopupButton;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement iframe1;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement iframe2;

    public String provisioningModel, instanceType, region, localSSD, commitmentTerm, totalEstimatedCostString;
    public double totalCost, instPriceValue, SSDPriceValue, GPUsPriceValue, bootDiskPriceValue, componentsPricesSumValue;

    public void getEquipmentResults() {
        provisioningModel = resultBlock_provisioningModel.getText();
        System.out.println(provisioningModel);
        instanceType = resultBlock_instanceType.getText();
        System.out.println(instanceType);
        region = resultBlock_region.getText();
        System.out.println(region);
        localSSD = resultBlock_localSSD.getText();
        System.out.println(localSSD);
        commitmentTerm = resultBlock_commitmentTerm.getText();
        System.out.println(commitmentTerm);
    }

    public void getPricesOfComponentsAndTotalCost(){
        totalEstimatedCostString = totalEstimatedCost.getText();
        System.out.println(totalEstimatedCostString);

        totalEstimatedCostString = totalEstimatedCostString.split("Cost: USD ")[1].split(" per")[0].replaceAll("[^0-9.]", "");
        //totalEstimatedCostString = totalEstimatedCostString.split(" per")[0];
        //totalEstimatedCostString = totalEstimatedCostString.replaceAll("[^0-9.]", "");
        totalCost = Double.parseDouble(totalEstimatedCostString);
        System.out.println("Value of total cost: " + totalCost);

        instPriceValue = Double.parseDouble(instancePrice.getText().replaceAll("[^0-9.]", ""));
        SSDPriceValue = Double.parseDouble(localSSDPrice.getText().replaceAll("[^0-9.]", ""));
        GPUsPriceValue = Double.parseDouble(GPUsPrice.getText().replaceAll("[^0-9.]", ""));
        bootDiskPriceValue = Double.parseDouble(bootDiskPrice.getText().replaceAll("[^0-9.]",""));


        componentsPricesSumValue = instPriceValue + SSDPriceValue + GPUsPriceValue + bootDiskPriceValue;
        System.out.println("Estimated Components Cost: " + instPriceValue + " + " + SSDPriceValue +
                " + " + GPUsPriceValue + " + " + bootDiskPriceValue + " = " + componentsPricesSumValue);
    }

    public void sendEmailWithPrice() {
        driver.switchTo().frame(iframe1);
        driver.switchTo().frame(iframe2);
        emailEstimateButton.click();
        waitForVisibility(emailPopupField).click();
        actions.pause(2000).perform();
        emailPopupField.sendKeys(Keys.CONTROL + "v");
        emailPopupField.sendKeys(Keys.ENTER);
        sendEmailPopupButton.click();
    }

    public void highlightPrices(){
        JavaScriptMethods.elementsHighlighter(instancePrice);
        JavaScriptMethods.elementsHighlighter(GPUsPrice);
        JavaScriptMethods.elementsHighlighter(localSSDPrice);
        JavaScriptMethods.elementsHighlighter(bootDiskPrice);
        JavaScriptMethods.elementsHighlighter(totalEstimatedCost);
    }
}
