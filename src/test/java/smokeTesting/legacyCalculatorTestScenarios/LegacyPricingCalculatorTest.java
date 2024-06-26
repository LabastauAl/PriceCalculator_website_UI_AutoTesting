package smokeTesting.legacyCalculatorTestScenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.emailServicePages.IncomingEmailsPage;
import pageObjects.emailServicePages.YopmailStartPage;
import pageObjects.prisingCalcPagesLegacy.EstimateRightSidebar;
import pageObjects.prisingCalcPagesLegacy.PricingCalculatorLegacyPage;
import parentTestClass.TestBasics;
import utils.PropertiesDataReader;
import utils.ScreenshotMaker;

public class LegacyPricingCalculatorTest extends TestBasics {
    private static String provisioningModel;
    private static String instanceType;
    private static String region;
    private static String localSSD;
    private static String commitmentTerm;

    private static String initialBrowserWindow, secondBrowserWindow;
    private PricingCalculatorLegacyPage pricingCalculatorLegacyPage;
    private EstimateRightSidebar estimateRightSidebar;
    private YopmailStartPage yopmailStartPage;
    private IncomingEmailsPage incomingEmailsPage;

    @BeforeTest(description = "Activating the instances of the all web-pages and elements participating in test scenario", enabled = true)
    public void activateWebPages() {
        System.out.println("Webdriver instance " + "\"" + getClass() + "\" test class. " + driver);
        initialBrowserWindow = driver.getWindowHandle();

        provisioningModel = PropertiesDataReader.getPropertyValue("testAndSetupData", "provisioning_model");
        instanceType = PropertiesDataReader.getPropertyValue("testAndSetupData", "instance_type");
        region = PropertiesDataReader.getPropertyValue("testAndSetupData", "region");
        localSSD = PropertiesDataReader.getPropertyValue("testAndSetupData", "local_SSD");
        commitmentTerm = PropertiesDataReader.getPropertyValue("testAndSetupData", "commitment_term");

        pricingCalculatorLegacyPage = new PricingCalculatorLegacyPage(driver);
        estimateRightSidebar = new EstimateRightSidebar(driver);
        yopmailStartPage = new YopmailStartPage(driver);
        incomingEmailsPage = new IncomingEmailsPage(driver);
    }

    @BeforeClass(description = "Enter to the Google Cloud Legacy Pricing Calculator webpage", enabled = true)
    public void enterToLegacyCalculatorPage() {
        driver.get("https://cloud.google.com/products/calculator-legacy");
        Assert.assertEquals(driver.getCurrentUrl(), "https://cloud.google.com/products/calculator-legacy");
        Assert.assertEquals(driver.getTitle(), "Google Cloud Pricing Calculator");
    }

    @Test(description = "Choosing the equipment and adding to estimate for calculate", priority = 1, enabled = true)
    public void chooseTheEquipment() {
        System.out.println("\t*** Test 1 ***");

        WebElement headerOfTheForm;
        pricingCalculatorLegacyPage.pressComputeEngineButton();
        headerOfTheForm = driver.findElement(By.xpath("//h2[text()='Instances']"));
        Assert.assertEquals(headerOfTheForm.getText(), "Instances");

        pricingCalculatorLegacyPage.enterNumberOfInstances();
        pricingCalculatorLegacyPage.selectOperatingSystem();
        pricingCalculatorLegacyPage.selectProvisioningModel();
        pricingCalculatorLegacyPage.selectSeries();
        pricingCalculatorLegacyPage.selectMachineType();
        pricingCalculatorLegacyPage.enterBootDiskSize();
        pricingCalculatorLegacyPage.chooseAddGPUsOption();
        pricingCalculatorLegacyPage.selectLocalSSD();
        pricingCalculatorLegacyPage.selectDataCenterLocation();
        pricingCalculatorLegacyPage.selectCommittedUsage();

        pricingCalculatorLegacyPage.addToEstimateAndCalculateCost();

        estimateRightSidebar.highlightPrices();
    }

    @Test(description = "Validating of requirements", priority = 2, enabled = true)
    public void validationOfRequirements() {
        System.out.println("\t*** Test 2 ***");

        estimateRightSidebar.getEquipmentResults();
        Assert.assertTrue(estimateRightSidebar.provisioningModel.contains(provisioningModel));
        Assert.assertTrue(estimateRightSidebar.instanceType.contains(instanceType));
        Assert.assertTrue(estimateRightSidebar.region.contains(region));
        Assert.assertTrue(estimateRightSidebar.localSSD.contains(localSSD));
        Assert.assertTrue(estimateRightSidebar.commitmentTerm.contains(commitmentTerm));
    }

    @Test(description = "Summing of components prices and validation with total estimated cost", priority = 3, enabled = true)
    public void estimatePrice() {
        System.out.println("\t*** Test 3 ***");

        estimateRightSidebar.getPricesOfComponentsAndTotalCost();
        Assert.assertEquals(estimateRightSidebar.totalCost, estimateRightSidebar.componentsPricesSumValue);
        ScreenshotMaker.screenCaptureDuringTest(driver);
    }

    @Test(description = "Open temporary_email service in new tab and generate new email address", priority = 4, enabled = true)
    public void getEmailAddress() {
        System.out.println("\t*** Test 4 ***");

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://yopmail.com/");
        Assert.assertTrue(driver.getTitle().contains("YOPmail"));
        secondBrowserWindow = driver.getWindowHandle();
        yopmailStartPage.generateNewEmail();
    }

    @Test(description = "Sending an email from result cost table and verifying correctness of total estimated cost in received email", priority = 5, enabled = true)
    public void sendEmailAndValidatePrice() {
        System.out.println("\t*** Test 5 ***");

        driver.switchTo().window(initialBrowserWindow);
        estimateRightSidebar.sendEmailWithPrice();
        actions.pause(2000).perform();
        driver.switchTo().window(secondBrowserWindow);
        //actions.pause(2000).perform();
        driver.navigate().refresh();
        yopmailStartPage.goToEmailPage();
        actions.pause(2000).perform();
        ScreenshotMaker.screenCaptureDuringTest(driver);
        incomingEmailsPage.getPriceFromEmail();
        Assert.assertEquals(incomingEmailsPage.receivedPrice, estimateRightSidebar.totalCost);
    }

    @AfterClass(enabled = true)
    public void closeBrowserTab() {
        driver.close();
        driver.switchTo().window(initialBrowserWindow);
    }
}
