package testListeners;

import org.testng.IExecutionListener;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ExecutionListener implements IExecutionListener {
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    private static String currentTime, currentDate;

    @Override
    public void onExecutionStart(){
        currentDate = ZonedDateTime.now().format(dateFormatter);
        currentTime = ZonedDateTime.now().format(timeFormatter);
        System.out.println("The test suite started " + currentDate + " at: " + currentTime);
    }

    @Override
    public void onExecutionFinish(){
        currentDate = ZonedDateTime.now().format(dateFormatter);
        currentTime = ZonedDateTime.now().format(timeFormatter);
        System.out.println("The test suite finished " + currentDate + " at: " + currentTime);
    }

}
