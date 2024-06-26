package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverSingleton {
    private static WebDriver driver = null;

    public static WebDriver setupDriver(String browserType, String driverPath) {
        if (driver == null) {
            switch (browserType) {
                case "Chrome":
                case "chrome":
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", driverPath);
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
                    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
                    driver.manage().window().maximize();
                    break;
                case "FireFox":
                case "Firefox":
                case "FIREFOX":
                    System.setProperty("webdriver.firefox.driver", driverPath);
                    driver = new FirefoxDriver();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
                    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
                    driver.manage().window().maximize();
                    break;
                default:
                    throw new IllegalArgumentException("Value of browser type " + browserType + " is incorrect");
            }
        }
        return driver;
    }

    public static WebDriver getWebDriver(){
        if(driver != null)
            return driver;
        return null;
    }
}
