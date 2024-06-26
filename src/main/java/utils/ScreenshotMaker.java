package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotMaker {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
    private static String currentDate = ZonedDateTime.now().format(dateFormatter);
    //private static String currentTime = ZonedDateTime.now().format(timeFormatter);

    public static void screenCaptureDuringTest(WebDriver driver) {
        String currentTime = ZonedDateTime.now().format(timeFormatter);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        try {
            FileUtils.copyFile(screenshotFile, new File(".//target/screenshots/DuringTest/" + currentDate + "/" + currentTime + ".png"));
            System.out.println("Screenshot saved at: " + currentTime);
        } catch (IOException e) {
            System.out.println("Screenshot saving failed:\t" + e.getMessage());
        }
    }

    public static void screenCaptureFailedTest(WebDriver driver) {
        String currentTime = ZonedDateTime.now().format(timeFormatter);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(".//target/screenshots/FailedTest/" + currentDate + "/" + currentTime + ".png"));
        } catch (IOException e) {
            System.out.println("Screenshot saving failed:\t" + e.getMessage());
        }
    }
}
