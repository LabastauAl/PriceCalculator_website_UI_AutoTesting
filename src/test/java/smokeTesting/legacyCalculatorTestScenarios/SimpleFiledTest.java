package smokeTesting.legacyCalculatorTestScenarios;

import org.openqa.selenium.By;;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.prisingCalcPagesLegacy.PricingCalculatorLegacyPage;
import parentTestClass.TestBasics;
import utils.JavaScriptMethods;

import java.time.Duration;

public class SimpleFiledTest extends TestBasics {
    private PricingCalculatorLegacyPage pricingCalculatorLegacyPage;

    @BeforeTest(description = "Activation of instances of all pages and elements participating in testing", enabled = true)
    public void activateTestPage() {
        System.out.println("Webdriver instance " + "\"" + getClass() + "\" test class. " + driver);
        pricingCalculatorLegacyPage = new PricingCalculatorLegacyPage(driver);
    }

    @BeforeClass(description = "Enter to the site and validate start page title", enabled = true)
    public void enterToPage() {
        driver.get("https://cloud.google.com/products/calculator-legacy");
        Assert.assertEquals(driver.getTitle(), "Google Cloud Pricing Calculator");
    }

    @Test(description = "Checking of footer element presence. Different variants of page scrolling", priority = 1, enabled = true)
    public void pageScrolling() throws InterruptedException {
        System.out.println("\t*** Test 1 ***");
        WebElement footerSolutions = driver.findElement(By.xpath("//section[@id='gc-wrapper']/devsite-footer-linkboxes/nav/ul/li/h3[text()='Solutions']"));
        Assert.assertTrue(footerSolutions.isEnabled());
        JavaScriptMethods.elementsHighlighter(footerSolutions);
        JavaScriptMethods.pageScroller();
    }

    @Test(description = "Failed test. Trying to click on non-interacting web element", priority = 2, enabled = true)
    public void failedTest() {
        System.out.println("\t*** Test 2 ***");
        pricingCalculatorLegacyPage.pressComputeEngineButton();
        WebElement addToEstimate = driver.findElement(By.xpath("//form[@name='ComputeEngineForm']//button[contains(text(), 'Add to Estimate')]"));
        Assert.assertTrue(addToEstimate.isDisplayed());
        addToEstimate.click();
        JavaScriptMethods.elementsHighlighter(addToEstimate);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(addToEstimate)).click();

    }
}
