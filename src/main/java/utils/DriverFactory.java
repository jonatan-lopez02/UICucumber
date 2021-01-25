package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {
    private static final Map<DriverEnum, Supplier<WebDriver>> driverMap = new HashMap<>();


    private static final Supplier<WebDriver> chromeDriverSupplier = () -> {
        String os=System.getProperty("os.name").toUpperCase();
        if(os.contains("MAC")){
            System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_MAC);
        }else if(os.contains("NIX") || os.contains("AIX") || os.contains("NUX")){
            System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_LINUX);
        }else {
            System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_WIN);
        }
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver();
    };

    private static final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        String os=System.getProperty("os.name").toUpperCase();
        if(os.contains("MAC")){
            System.setProperty(Constants.FIREFOX_DRIVER, Constants.FIREFOX_DRIVER_MAC);
        }else if(os.contains("NIX") || os.contains("AIX") || os.contains("NUX")){
            System.setProperty(Constants.FIREFOX_DRIVER, Constants.FIREFOX_DRIVER_LINUX);
        }else {
            System.setProperty(Constants.FIREFOX_DRIVER, Constants.FIREFOX_DRIVER_WIN);
        }
        return new FirefoxDriver();
    };

    private static final Supplier<WebDriver> ieDriverSupplier = () -> {
        System.setProperty("webdriver.ie.driver", "/Users/username/Downloads/geckodriver");
        return new FirefoxDriver();
    };

    static{
        driverMap.put(DriverEnum.CHROME, chromeDriverSupplier);
        driverMap.put(DriverEnum.FIREFOX, firefoxDriverSupplier);
        driverMap.put(DriverEnum.IE, ieDriverSupplier);
    }

    public static WebDriver getDriver(String type){
        switch (type){
            case "chrome":
                return driverMap.get(DriverEnum.CHROME).get();
            case "firefox":
                return driverMap.get(DriverEnum.FIREFOX).get();
            case "ie":
                return driverMap.get(DriverEnum.IE).get();
            default:
                throw new AssertionError("Invalid browser " + type);
        }

    }

}