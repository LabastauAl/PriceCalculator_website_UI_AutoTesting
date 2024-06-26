package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDataReader {
    private static Properties properties = null;
    private static FileInputStream fileInputStream = null;

    public static String getPropertyValue(String fileName, String propertyKey) {
        try {
            fileInputStream = new FileInputStream("./src/test/resources/" + fileName + ".properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException exc) {
            System.out.println(exc);
            properties = null;
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException exc) {
                System.out.println(exc);
            }
        }
        return properties.getProperty(propertyKey);
    }
}