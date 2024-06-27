package smokeTesting.computeEngineTestScenarios;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.pricingCalculatorPages.CostDetailsRightSidebar;

import pageObjects.pricingCalculatorPages.ComputeEnginePage;
import pageObjects.pricingCalculatorPages.WelcomePage;
import pageObjects.prisingCalcPagesLegacy.StartSearchPage;
import parentTestClass.TestBasics;

import utils.JavaScriptMethods;
import utils.PropertiesDataReader;
import utils.ScreenshotMaker;

import java.util.ArrayList;
import java.util.List;

public class CalculatePriceTest extends TestBasics {
    private static String searchQuery;

    private static String initialBrowserWindow, secondBrowserWindow;
    private String estimatedCost_rightSidebar;
    private StartSearchPage startSearchPage;

    private WelcomePage welcomePage;
    private ComputeEnginePage computeEnginePage;
    private CostDetailsRightSidebar costDetailsRightSidebar;


    @BeforeTest(description = "Activation the instances of all pages and elements participating in testing", enabled = true)
    public void activateTestPages() {
        System.out.println("Webdriver instance " + "\"" + getClass() + "\" test class. " + driver);
        initialBrowserWindow = driver.getWindowHandle();

        searchQuery = PropertiesDataReader.getPropertyValue("testAndSetupData", "search_query");

        startSearchPage = new StartSearchPage(driver);
        welcomePage = new WelcomePage(driver);
        computeEnginePage = new ComputeEnginePage(driver);
        costDetailsRightSidebar = new CostDetailsRightSidebar(driver);
    }

    @BeforeClass
    public void enterToCloudSearchPage() {
        driver.get("https://cloud.google.com/search");
        Assert.assertEquals(driver.getTitle(), "Search | Google Cloud");
    }

    @Test(description = "Searching of required content and going to the desired page", priority = 1, enabled = true)
    public void searchingRequiredContent() {
        System.out.println("\t*** Test 1 ***");

        startSearchPage.findNeedInfo(searchQuery);
        startSearchPage.chooseSearchingResult();

        Assert.assertEquals(driver.getCurrentUrl(), "https://cloud.google.com/products/calculator");
    }

    @Test(description = "Choose the category for estimation from presented cards in popup", priority = 2, enabled = true)
    public void chooseCategoryToEstimate() {
        System.out.println("\t*** Test 2 ***");
        System.out.println(driver.getTitle());

        Assert.assertEquals(driver.getTitle(), "Google Cloud Pricing Calculator");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()=\"Welcome to Google Cloud's pricing calculator\"]")).isDisplayed());

        welcomePage.pressAddToEstimateButton();
        welcomePage.pressComputeEnginePopupCard();
    }

    @Test(description = "Setting up the required configuration of hardware", priority = 3, enabled = true)
    public void setupEquipmentConfiguration() {
        System.out.println("\t*** Test 3 ***");

        Assert.assertTrue(driver.findElement(By.xpath("//h1[@aria-label='Selected product title' and text() = 'Compute Engine']")).isDisplayed());

        computeEnginePage.setNumberOfInstances();
        computeEnginePage.chooseOperatingSystem();
        computeEnginePage.selectProvisioningModel();
        computeEnginePage.chooseMachineType();
        computeEnginePage.selectBootDiskType();
        computeEnginePage.addSustainedUseDiscounts();
        computeEnginePage.addGPUs();
        computeEnginePage.choseGPUs();
        computeEnginePage.choseLocalSSD();
        computeEnginePage.choseRegion();
        computeEnginePage.selectCommittedUseDiscount();

        estimatedCost_rightSidebar = costDetailsRightSidebar.getEstimatedCost().getText();
        System.out.println("Total cost of using configured hardware: " + estimatedCost_rightSidebar + " per month");
    }


    @Test(description = "Checking of chosen Machine type in block", priority = 4, enabled = true)
    public void validationOfMachineType() throws InterruptedException {
        System.out.println("\t*** Test 4 ***");

        WebElement machineTypeTitle = driver.findElement(By.xpath("//div[text()='Machine type']"));
        WebElement chosenMachineTypeFromBlock = driver.findElement(By.xpath("//div[text()='Machine Type']/.."));
        WebElement chosenMachineTypeFromField = computeEnginePage.getChosenMachineTypeFromField();

        JavaScriptMethods.scrollToElement(machineTypeTitle);
        JavaScriptMethods.getJsExecutor().executeScript("window.scrollBy(0, -100)", "");  //Scrolling up vertical by 100px from current position
        JavaScriptMethods.elementsHighlighter(chosenMachineTypeFromBlock);

        System.out.println("The chosen configuration of hardware: " + chosenMachineTypeFromField.getAttribute("textContent"));

        Assert.assertEquals(chosenMachineTypeFromField.getAttribute("textContent").split("vCPUs:")[0],
                chosenMachineTypeFromBlock.getAttribute("textContent").split("selections")[1].split("vCPUs:")[0]);
    }

    @Test(description = "Checking range of vCPUs and amount of RAM", priority = 5, enabled = true)
    public void validationOfChosenCPUsAndRAM() {
        System.out.println("\t*** Test 5 ***");

        WebElement machineTypeChosenHardware = driver.findElement(By.xpath("//div[text()='Machine Type']/../div[4]"));
        WebElement vCPUsRangeSlider = driver.findElement(By.xpath("//div//span[contains(text(),' vCPUs')]"));
        WebElement memoryRangeSlider = driver.findElement(By.xpath("//div[text()='Amount of memory']/../../..//span[contains(text(),' GB')]"));

        System.out.println("The range of vCPUs in chosen configuration: " + vCPUsRangeSlider.getText().split(" vCPUs")[0]);
        System.out.println("The amount of RAM in chosen configuration: " + memoryRangeSlider.getText().split(" GB")[0]);

        Assert.assertEquals(machineTypeChosenHardware.getText().split("vCPUs: ")[1].split(", RAM:")[0],
                vCPUsRangeSlider.getText().split(" vCPUs")[0]);
        Assert.assertEquals(machineTypeChosenHardware.getText().split("RAM: ")[1].split(" GB")[0],
                memoryRangeSlider.getText().split(" GB")[0]);
    }

    @Test(description = "Checking the results of calculated price on Compute Engine header and right sidebar", priority = 6, enabled = true)
    public void compareOfCalculatedPriceResult() {
        System.out.println("\t*** Test 6 ***");

        JavaScriptMethods.elementsHighlighter(costDetailsRightSidebar.getEstimatedCost());
        System.out.println("The total cost from Compute Engine header: " + computeEnginePage.getCalculatedPriceFromHeader());
        ScreenshotMaker.screenCaptureDuringTest(driver);

        Assert.assertEquals(costDetailsRightSidebar.getEstimatedCost().getText(), computeEnginePage.getCalculatedPriceFromHeader());
    }

    @Test(description = "Checking detailed pricing information for configured equipment", priority = 7, enabled = true)
    public void checkingDetailedInformationOfTotalCost() {
        System.out.println("\t*** Test 7 ***");

        costDetailsRightSidebar.showDetailedInformationAboutCost();
        costDetailsRightSidebar.viewPricesOfComponents();

        Double calculatedPriceFromHeader = Double.parseDouble(computeEnginePage.getCalculatedPriceFromHeader().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(costDetailsRightSidebar.getSumOfTotalCost(), calculatedPriceFromHeader);

        costDetailsRightSidebar.closeInstancesPopup();
    }

    @Test(description = "Opening detailed view in a new browser tab and  validation page title", priority = 8, enabled = true)
    public void viewEstimateSummaryPage() {
        System.out.println("\t*** Test 8 ***");

        costDetailsRightSidebar.openEstimateSummaryPage();

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google Cloud Estimate Summary");
    }

    @Test(description = "Checking the Total estimated cost value in the Estimate Summary page", priority = 9, enabled = true)
    public void validateEstimatedCostFromSummaryPage() {
        System.out.println("\t*** Test 9 ***");

        WebElement totalEstimatedCost = driver.findElement(By.xpath("//h5[text()='Total estimated cost']/following-sibling::h4"));
        System.out.println("Total estimated cost from Summary page: " + totalEstimatedCost.getText());
        Assert.assertEquals(totalEstimatedCost.getText(), estimatedCost_rightSidebar);
    }

    @AfterClass(enabled = true)
    public void closeBrowserTab() {
        driver.close();
        driver.switchTo().window(initialBrowserWindow);
    }
}
