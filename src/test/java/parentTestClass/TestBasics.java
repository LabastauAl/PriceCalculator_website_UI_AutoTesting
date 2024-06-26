package parentTestClass;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import testListeners.ExecutionListener;
import testListeners.TestListener;
import utils.PropertiesDataReader;
import utils.WebDriverSingleton;

@Listeners({TestListener.class, ExecutionListener.class})
public class TestBasics {
    protected static WebDriver driver = null;
    protected static String browserType = null;
    protected static String driverPath = null;
    protected static Actions actions;

    protected TestBasics() {

        if (browserType == null)
            browserType = PropertiesDataReader.getPropertyValue("testAndSetupData", "browser_type");
        if (driverPath == null)
            driverPath = PropertiesDataReader.getPropertyValue("testAndSetupData", "driver_path");;

        driver = WebDriverSingleton.setupDriver(browserType, driverPath);
        actions = new Actions(driver);
    }

    protected static Boolean checkWebElement(String webElementXpath){
        try{
            if(driver.findElement(By.xpath(webElementXpath)).isEnabled())
                return true;
        } catch (NoSuchElementException e){
            //System.out.println("The web-element is not represented in the DOM of this webpage");
        }
        return false;
    }

    @BeforeSuite(enabled = true)
    public void somePreparingActions() {
        System.out.println("Test suite started from test-class: " + getClass());
    }

    @AfterTest(enabled = true)
    public void endOfSession() {
        driver.quit();
        System.out.println(driver);
    }
}
