package pageObjects.prisingCalcPagesLegacy;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

import java.util.List;

public class StartSearchPage extends PageObjectBasics {
    public StartSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='i3']")
    private WebElement inputSearchField;
    @FindBy(xpath = "//c-wiz[2]//div[3]/c-wiz/div[1]//div[1]/a")
    private List<WebElement> searchingResults;

    public void findNeedInfo(String searchContent) {
        inputSearchField.sendKeys(searchContent);
        inputSearchField.sendKeys(Keys.ENTER);
        System.out.println("Amount of search results - " + searchingResults.size());
        for (int i = 0; i < searchingResults.size(); i++) {
            System.out.println(searchingResults.get(i).getAttribute("pathname"));

        }
    }

    public void chooseSearchingResultLegacy() {
        for (int i = 0; i < searchingResults.size(); i++) {
            if (searchingResults.get(i).getAttribute("pathname").equals("/products/calculator-legacy")) {
                searchingResults.get(i).click();
                return;
            }
        }
    }

    public void chooseSearchingResult() {
        for (int i = 0; i < searchingResults.size(); i++) {
            if (searchingResults.get(i).getAttribute("pathname").equals("/products/calculator")) {
                searchingResults.get(i).click();
                return;
            }
        }
    }

}
