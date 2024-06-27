package pageObjects.pricingCalculatorPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class CostDetailsRightSidebar extends PageObjectBasics {
    public CostDetailsRightSidebar(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[text()='Estimated cost']/following-sibling::div/label")
    private WebElement estimatedCost;

    @FindBy(xpath = "//h2[text()='Cost details']/../../div[3]//div[4]//button")
    private WebElement instancesOptionsButton;

    @FindBy(xpath = "//span[text()='View details']/../..")
    private WebElement submenuViewDetailsButton;

    @FindBy(xpath = "//h3[text()='Instances']/../../div[2]//div[text()='Machine type']/../../div[2]")
    private WebElement MachineTypePrice_InstancesPopup;

    @FindBy(xpath = "//h3[text()='Instances']/../../div[2]//div[text()='Number of GPUs']/../../div[2]")
    private WebElement GPUsPrice_InstancesPopup;

    @FindBy(xpath = "//h3[text()='Instances']/../../div[2]//div[text()='Local SSD']/../../div[2]")
    private WebElement LocalSsdPrice_InstancesPopup;

    @FindBy(xpath = "//h3[text()='Instances']/../../div[2]//div[text()='Boot disk size (GiB)']/../../div[2]")
    private WebElement BootDiskPrice_InstancesPopup;

    @FindBy(xpath = "//button[@aria-label='Close dialog']")
    private WebElement closeDialog_InstancesPopup;

    @FindBy(xpath = "//a[@aria-label='Open detailed view']")
    private WebElement openDetailedViewButton;

    private double totalCostOfItems;

    public WebElement getEstimatedCost(){
        actions.pause(2000).perform();
        return estimatedCost;
    }

    public void showDetailedInformationAboutCost(){
        instancesOptionsButton.click();
        waitForVisibility(submenuViewDetailsButton).click();
    }

    public void viewPricesOfComponents(){
        double machineTypePrice = Double.parseDouble(MachineTypePrice_InstancesPopup.getText().replaceAll("[^0-9.]", ""));
        double GPUsPrice = Double.parseDouble(GPUsPrice_InstancesPopup.getText().replaceAll("[^0-9.]", ""));
        double ssdPrice = Double.parseDouble(LocalSsdPrice_InstancesPopup.getText().replaceAll("[^0-9.]", ""));
        double bootDiskPrice = Double.parseDouble(BootDiskPrice_InstancesPopup.getText().replaceAll("[^0-9.]", ""));

        totalCostOfItems = machineTypePrice + GPUsPrice + ssdPrice + bootDiskPrice;

        System.out.println("Cost of individual configuration components: "
                + machineTypePrice + " + " + GPUsPrice + " + " + ssdPrice + " + " + bootDiskPrice + " = " + totalCostOfItems);
    }

    public double getSumOfTotalCost(){
        return totalCostOfItems;
    }

    public void closeInstancesPopup(){
        closeDialog_InstancesPopup.click();
    }

    public void openEstimateSummaryPage(){
        openDetailedViewButton.click();
    }
}
