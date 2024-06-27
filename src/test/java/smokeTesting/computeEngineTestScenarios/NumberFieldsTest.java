package smokeTesting.computeEngineTestScenarios;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.pricingCalculatorPages.ComputeEnginePage;
import pageObjects.pricingCalculatorPages.numberInputFields.BootDiskSize;
import pageObjects.pricingCalculatorPages.numberInputFields.Instances;
import pageObjects.pricingCalculatorPages.WelcomePage;
import pageObjects.pricingCalculatorPages.numberInputFields.UsageTime;
import parentTestClass.TestBasics;
import utils.JavaScriptMethods;

public class NumberFieldsTest extends TestBasics {
    private WelcomePage welcomePage;
    private Instances instances;
    private UsageTime usageTime;

    private BootDiskSize bootDiskSize;

    @BeforeTest(description = "Activation the instances of all pages and elements participating in testing", enabled = true)
    public void activateTestPages() {
        System.out.println("Webdriver instance " + "\"" + getClass() + "\" test class. " + driver);
        welcomePage = new WelcomePage(driver);
        instances = new Instances(driver);
        usageTime = new UsageTime(driver);
        bootDiskSize = new BootDiskSize(driver);
    }

    @Test
    public void enterToPricingCalculatorPage() {
        driver.get("https://cloud.google.com/products/calculator");
        Assert.assertEquals(driver.getTitle(), "Google Cloud Pricing Calculator");
    }

    @Test(description = "Choose the category for estimation from presented cards in popup", priority = 1, enabled = true)
    public void chooseCategoryToEstimate() {
        System.out.println("\t*** Test 1 ***");
        System.out.println(driver.getTitle());

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()=\"Welcome to Google Cloud's pricing calculator\"]")).isDisplayed());

        welcomePage.pressAddToEstimateButton();
        welcomePage.pressComputeEnginePopupCard();
    }

    @Test(description = "Checking the behavior of \"Number of instances*\" number input field using boundary values", priority = 2, enabled = true)
    public void checkNumberOfInstancesField(){
        System.out.println("\t*** Test 2 ***");
        System.out.println("Value needs to be greater than 0 and less than or equal to 50,000");

        instances.deleteInitialValue();
        Assert.assertTrue(instances.warningFieldRequiredPresence());

        instances.setNumberOfInstances("0");
        Assert.assertTrue(instances.warningValuesRangePresence());
        instances.clearNumberOfInstances();

        instances.setNumberOfInstances("1");
        Assert.assertFalse(instances.warningFieldRequiredPresence());
        Assert.assertFalse(instances.warningValuesRangePresence());
        instances.clearNumberOfInstances();

        instances.setNumberOfInstances("50001");
        Assert.assertTrue(instances.warningValuesRangePresence());
        instances.clearNumberOfInstances();

        instances.setNumberOfInstances("50000");
        Assert.assertFalse(instances.warningFieldRequiredPresence());
        Assert.assertFalse(instances.warningValuesRangePresence());
        instances.clearNumberOfInstances();

        instances.setNumberOfInstances("1");
    }
    @Test(description = "Checking the behavior of \"Total instance usage time*\" number input field using boundary values", priority = 3, enabled = true)
    public void checkTotalInstancesUsageTimeField() {
        System.out.println("\t*** Test 3 ***");
        System.out.println("Value needs to be greater than 0 and less than or equal to 36,500,000");

        usageTime.deleteInitialValue();
        Assert.assertTrue(usageTime.warningFieldRequiredPresence());

        usageTime.setUsageTime("0");
        Assert.assertTrue(usageTime.warningValuesRangePresence());
        usageTime.clearUsageTime();

        usageTime.setUsageTime("1");
        Assert.assertFalse(usageTime.warningFieldRequiredPresence());
        Assert.assertFalse(usageTime.warningValuesRangePresence());
        usageTime.clearUsageTime();

        usageTime.setUsageTime("36500001");
        Assert.assertTrue(usageTime.warningValuesRangePresence());
        usageTime.clearUsageTime();

        usageTime.setUsageTime("36500000");
        Assert.assertFalse(usageTime.warningFieldRequiredPresence());
        Assert.assertFalse(usageTime.warningValuesRangePresence());
        usageTime.clearUsageTime();

        usageTime.setUsageTime("730");
    }

    @Test(description = "Checking the behavior of \"Boot disk size (GiB)\" input field's increment and decrement buttons using boundary values", priority = 4, enabled = true)
    public void checkBootDiskSizeField() throws InterruptedException {
        System.out.println("\t *** Test 4***");
        System.out.println("Value needs to be greater than or equal to 10 GiB and less than or equal to 65,536 GiB");

        JavaScriptMethods.scrollToElement(bootDiskSize.getBootDiskSizeField());
        JavaScriptMethods.getJsExecutor().executeScript("window.scrollBy(0, -100)", "");  //Scrolling up vertical by 100px from current position

        Assert.assertTrue(bootDiskSize.decrementButtonIsActive());
        Assert.assertTrue(bootDiskSize.incrementButtonIsActive());

        bootDiskSize.clearInitialValue();
        bootDiskSize.setBootDiskSize("0");
        Assert.assertFalse(bootDiskSize.decrementButtonIsActive());

        bootDiskSize.clearInitialValue();
        bootDiskSize.setBootDiskSize("10");
        Assert.assertFalse(bootDiskSize.decrementButtonIsActive());

        bootDiskSize.clearInitialValue();
        bootDiskSize.setBootDiskSize("11");
        Assert.assertTrue(bootDiskSize.decrementButtonIsActive());

        bootDiskSize.clearInitialValue();
        bootDiskSize.setBootDiskSize("65535");
        Assert.assertTrue(bootDiskSize.incrementButtonIsActive());

        bootDiskSize.clearInitialValue();
        bootDiskSize.setBootDiskSize("65536");
        Assert.assertFalse(bootDiskSize.incrementButtonIsActive());

        bootDiskSize.clearInitialValue();
        bootDiskSize.setBootDiskSize("65537");
        Assert.assertFalse(bootDiskSize.incrementButtonIsActive());

        bootDiskSize.clearInitialValue();
        bootDiskSize.setBootDiskSize("9");
        Assert.assertFalse(bootDiskSize.decrementButtonIsActive());
    }
}
