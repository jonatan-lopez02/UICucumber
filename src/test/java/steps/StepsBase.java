package steps;

import org.openqa.selenium.WebDriver;
import utils.Constants;
import utils.DriverFactory;
import utils.FileUtils;

import java.util.concurrent.TimeUnit;

public class StepsBase {
    FileUtils fileUtils = new FileUtils();
    protected WebDriver driver;

    public void setUp(){
        driver = DriverFactory.getDriver(fileUtils.getEnvProperties(Constants.BROWSER));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(fileUtils.getEnvProperties(Constants.URL));
    }
}
