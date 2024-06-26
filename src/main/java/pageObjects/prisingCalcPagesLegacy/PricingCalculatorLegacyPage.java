package pageObjects.prisingCalcPagesLegacy;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class PricingCalculatorLegacyPage extends PageObjectBasics {

    public PricingCalculatorLegacyPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@layout='column']/parent::div[@title='Compute Engine']/ancestor::md-tab-item")
    private WebElement computeEngineButton;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesField;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']")
    private WebElement operatingSystemField;

    @FindBy(xpath = "//md-option/div[@class='md-text' and contains(text(), 'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)')]")
    private WebElement operatingSystemVariant;

    @FindBy(xpath = "//label[contains(text(), 'Provisioning model')]/following-sibling::md-select")
    private WebElement provisioningModelField;

    @FindBy(xpath = "//md-option/div[@class='md-text' and contains(text(), 'Regular')]")
    private WebElement provisioningModeVariant;

    @FindBy(xpath = "//label[contains(text(), 'Series')]/following-sibling::md-select")
    private WebElement seriesField;

    @FindBy(xpath = "//md-option[@ng-value='item.value']/div[contains (text(), 'N1')]")
    private WebElement seriesVariant;

    @FindBy(xpath = "//label[contains(text(), 'Machine type')]/following-sibling::md-select")
    private WebElement machineTypeField;

    @FindBy(xpath = "//md-option/div[@class='md-text ng-binding' and contains(text(), 'n1-standard-8 (vCPUs: 8, RAM: 30GB)')]")
    private WebElement machineTypeVariant;

    @FindBy(xpath = "//input[@name='bootDiskSize']")
    private WebElement bootDiskSizeField;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//div[@class='md-label' and contains(text(), 'Add GPUs.')]")
    private WebElement addGPUsCheckbox;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement gpuTypeField;

    @FindBy(xpath = "//md-option/div[contains (text(), 'NVIDIA Tesla P100')]")
    private WebElement gpuTypeVariant;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUsField;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']/md-select-menu/md-content[@class='_md']/md-option[@value='1']")
    private WebElement numberOfGPUsVariant;

    @FindBy(xpath = "//div[contains(@ng-if, 'computeServer')]//md-select[@placeholder='Local SSD']")
    private WebElement localSSDField;

    @FindBy(xpath = "//md-option/div[contains(text(), '2x375 GB')]")
    private WebElement localSSDVariant;

    @FindBy(xpath = "//md-input-container/label[contains(text(), 'Datacenter location')]/following-sibling::md-select[@ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocationField;

    //@FindBy(xpath = "//md-option[contains(@ng-repeat, 'fullRegionList')]/div[contains(text(),'Frankfurt (europe-west3)')]")
    //private WebElement datacenterLocationVariant;

    @FindBy(xpath = "//md-option[contains(@ng-repeat, 'fullRegionList')]/div[contains(text(),'Belgium (europe-west1)')]")
    private WebElement datacenterLocationVariant;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage' and @ng-disabled='listingCtrl.isCudDisabled']")
    private WebElement committedUsageField;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//div[contains(text(), '1 Year')]")
    private WebElement committedUsageVariant;

    @FindBy(xpath = "//*[@id='mainForm']//md-card-content//div[20]/button[contains(text(), 'Add to Estimate')]")
    private WebElement addToEstimateButton;
    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement iframe1;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement iframe2;


    public void pressComputeEngineButton() {
        driver.switchTo().frame(iframe1);
        driver.switchTo().frame(iframe2);
        computeEngineButton.click();
    }

    public void enterNumberOfInstances() {
        numberOfInstancesField.click();
        numberOfInstancesField.sendKeys(Keys.NUMPAD4);
    }

    public void selectOperatingSystem() {
        operatingSystemField.click();
        waitForVisibility(operatingSystemVariant).click();
    }

    public void selectProvisioningModel() {
        provisioningModelField.click();
        waitForVisibility(provisioningModeVariant).click();
    }

    public void selectSeries() {
        seriesField.click();
        waitForVisibility(seriesVariant).click();
    }

    public void selectMachineType() {
        machineTypeField.click();
        waitForVisibility(machineTypeVariant).click();
    }

    public void enterBootDiskSize() {
        bootDiskSizeField.click();
        bootDiskSizeField.sendKeys("30");
    }

    public void chooseAddGPUsOption() {
        addGPUsCheckbox.click();

        gpuTypeField.click();
        waitForVisibility(gpuTypeVariant).click();

        numberOfGPUsField.click();
        waitForVisibility(numberOfGPUsVariant).click();
    }

    public void selectLocalSSD() {
        localSSDField.click();
        waitForVisibility(localSSDVariant).click();
    }

    public void selectDataCenterLocation(){
        datacenterLocationField.click();
        waitForVisibility(datacenterLocationVariant).click();
    }

    public void selectCommittedUsage(){
        committedUsageField.click();
        waitForVisibility(committedUsageVariant).click();
    }

    public void addToEstimateAndCalculateCost() {
        addToEstimateButton.click();
    }

}
