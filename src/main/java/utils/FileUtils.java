package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {

    public String getEnvProperties(String property) {
        String value = "";
        try {
        FileReader reader = new FileReader("./src/main/resources/environment/staging.properties");
        Properties properties = new Properties();
        properties.load(reader);
        value = properties.getProperty(property);
        properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    public String getRandomAlphaNumString(int numDigits)
    {
        String ID;
        ID = RandomStringUtils.random(numDigits, true, true);
        System.out.println("ID is "+ ID);
        return ID;

    }
}
