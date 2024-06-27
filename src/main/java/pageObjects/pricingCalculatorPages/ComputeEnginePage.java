package pageObjects.pricingCalculatorPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class ComputeEnginePage extends PageObjectBasics {
    public ComputeEnginePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[text()='Number of instances' and text()='*']/../../div[3]//input")
    private WebElement numberOfInstancesField;

    @FindBy(xpath = "//span[text()='Operating System / Software']/../../..")
    private WebElement operatingSystemsField;

    @FindBy(xpath = "//li[@data-value='free-debian-centos-coreos-ubuntu-or-byol-bring-your-own-license']")
    private WebElement operatingSystemVariant1;

    @FindBy(xpath = "//li[@data-value='paid-sles-15-for-sap']")
    private WebElement operatingSystemVariant2;

    @FindBy(xpath = "//label[text()='Regular']/..")
    private WebElement provisioningModelRegularButton;

    @FindBy(xpath = "//span[text()='Series']/../../..")
    private WebElement seriesField;

    @FindBy(xpath = "//li[@data-value='n1']")
    private WebElement seriesVariant1;

    @FindBy(xpath = "//li[@data-value='n2']")
    private WebElement seriesVariant2;

    @FindBy(xpath = "//span[text()='Machine type']/ancestor::span/../..")
    private WebElement machineTypeField;

    @FindBy(xpath = "//li[@data-value='n1-standard-8']")
    private WebElement machineTypeVariant;

    @FindBy(xpath = "//input[@id='balanced-persistent-disk']/..")
    private WebElement bootDiskTypeButton;

    @FindBy(xpath = "//div[text()='Boot disk size (GiB)']/../../..//input")
    private WebElement bootDiskSizeInputField;

    @FindBy(xpath = "//button[@aria-label='Add sustained use discounts']")
    private WebElement sustainedUseDiscountsSwitch;

    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    private WebElement GPUsSwitch;

    @FindBy(xpath = "//span[text()='GPU Model']/../../..")
    private WebElement GPUModelField;

    @FindBy(xpath = "//li[@data-value='nvidia-tesla-p100']")
    private WebElement GPUModelVariant;

    @FindBy(xpath = "//span[text()='Number of GPUs']/../../..")
    private WebElement numberOfGPUsField;

    @FindBy(xpath = "//span[text()='Number of GPUs']/../../../..//li[@data-value='1']")
    private WebElement numberOfGPUsVariant;

    @FindBy(xpath = "//span[text()='Local SSD' and @id]/../../../..")
    private WebElement localSSDField;

    @FindBy(xpath = "//li//span[text()='2x375 GB']/..")
    private WebElement localSSDVariant;

    @FindBy(xpath = "//span[text()='Region']/../../..")
    private WebElement regionField;

    @FindBy(xpath = "//li[@data-value='europe-west1']")
    private WebElement regionVariant;

    @FindBy(xpath = "//label[text()='1 year']/..")
    private WebElement committedDiscountUsageButton;

    @FindBy(xpath = "//h1[text()='Compute Engine']/../div[3]/span[1]")
    private WebElement calculatedCost;

    public void setNumberOfInstances() {
        actions.moveToElement(numberOfInstancesField).pause(500).click().perform();
        numberOfInstancesField.clear();
        //numberOfInstancesField.sendKeys(Keys.BACK_SPACE);
        numberOfInstancesField.sendKeys(Keys.NUMPAD4);
    }


    public void chooseOperatingSystem() {
        operatingSystemsField.click();
        actions.moveToElement(operatingSystemVariant2).pause(500).perform();
        actions.moveToElement(operatingSystemVariant1).pause(500).click().perform();
    }

    public void selectProvisioningModel() {
        provisioningModelRegularButton.click();
    }

    public void chooseMachineType() {
        seriesField.click();
        actions.moveToElement(seriesVariant2).pause(500).perform();
        seriesVariant1.click();

        machineTypeField.click();
        actions.pause(500).moveToElement(machineTypeVariant).pause(500).click().perform();
    }

    public void selectBootDiskType() {
        bootDiskTypeButton.click();
        bootDiskSizeInputField.click();
        bootDiskSizeInputField.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE);
        bootDiskSizeInputField.sendKeys("30");
    }

    public void addSustainedUseDiscounts() {
        sustainedUseDiscountsSwitch.click();
    }

    public void addGPUs() {
        GPUsSwitch.click();
    }

    public void choseGPUs() {
        GPUModelField.click();
        actions.pause(500).moveToElement(GPUModelVariant).pause(500).click().perform();

        numberOfGPUsField.click();
        actions.pause(500).moveToElement(numberOfGPUsVariant).pause(500).click().perform();
    }

    public void choseLocalSSD() {
        localSSDField.click();
        actions.pause(500).moveToElement(localSSDVariant).pause(500).click().perform();
    }

    public void choseRegion() {
        regionField.click();
        actions.pause(500).moveToElement(regionVariant).pause(500).click().perform();
    }

    public void selectCommittedUseDiscount() {
        committedDiscountUsageButton.click();
    }

    public WebElement getChosenMachineTypeFromField() {
        return machineTypeVariant;
    }

    public String getCalculatedPriceFromHeader() {
        return calculatedCost.getText();
    }


}
