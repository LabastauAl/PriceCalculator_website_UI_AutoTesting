package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptMethods {
    static JavascriptExecutor jsEx = (JavascriptExecutor) WebDriverSingleton.getWebDriver();

    public static JavascriptExecutor getJsExecutor(){
        return jsEx;
    }

    public static void elementsHighlighter(WebElement element) {
        jsEx.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;')", element);
//        Thread.sleep(1000);
//        jsEx.executeScript("arguments[0].style.border=''", element, "");
//        Thread.sleep(1000);
//        jsEx.executeScript("arguments[0].style.background=''", element, "");
    }

    public static void pageScroller() throws InterruptedException {
        jsEx.executeScript("window.scrollTo(0, 1500)");         //Scrolling down vertical to position 1500px
        Thread.sleep(1000);
        jsEx.executeScript("window.scrollBy(0, 100)", "");  //Scrolling down vertical by 100px from current position (1500 + 100)
        Thread.sleep(1000);
        jsEx.executeScript("window.scrollTo(0, 150)");      //Scrolling (up in this case) vertical to position 150px
        Thread.sleep(1000);

        jsEx.executeScript("window.scrollTo(0, document.body.scrollHeight)");      //Scrolling down vertical to the bottom of the page
        Thread.sleep(1000);

        jsEx.executeScript("window.scrollTo(document.body.scrollHeight, 0)");     //Scrolling up vertical to the top of the page
        Thread.sleep(1000);

//        jsEx.executeScript("window.scrollTo(0, 0)");
//        Thread.sleep(1000);
    }

    public static void scrollToElement(WebElement element) throws InterruptedException {
        jsEx.executeScript("arguments[0].scrollIntoView();", element);          //Scrolling to web webElement
        Thread.sleep(1000);
    }
}
