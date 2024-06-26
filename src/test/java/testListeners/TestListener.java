package testListeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotMaker;
import utils.WebDriverSingleton;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        ScreenshotMaker.screenCaptureFailedTest(WebDriverSingleton.getWebDriver());
    }
}
