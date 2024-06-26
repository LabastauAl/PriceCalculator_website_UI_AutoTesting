package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageObjectBasics {
    protected static WebDriver driver = null;
    protected static Actions actions;

    public PageObjectBasics(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    protected boolean checkWebElementEnabled(WebElement webElement) {
        try {
            webElement.isEnabled();
        } catch (NoSuchElementException exc) {
            return false;
        }
        return true;
    }

    protected boolean checkWebElementDisplayed(WebElement webElement){
        try{
            webElement.isDisplayed();
        } catch (NoSuchElementException exc){
            return false;
        }
        return true;
    }

    public WebElement waitForVisibility(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitForClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public List<WebElement> waitForVisibility(List<WebElement> listOfElements) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(listOfElements));
        return listOfElements;
    }
}
