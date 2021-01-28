package targets;

import helper.Common;
import helper.Constants;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

public class Firefox {
    public static void init() {
        RemoteWebDriver driver = BasePage.getDriver();
        Common.OSType osType = Common.getOperatingSystemType();

        switch (osType) {
            case Windows:
                System.setProperty(Constants.FIREFOX_DRIVER_PROPERTY, Constants.FIREFOX_DRIVER_PATH_WIN);
                break;
            case MacOS:
                System.setProperty(Constants.FIREFOX_DRIVER_PATH_WIN, Constants.FIREFOX_DRIVER_PATH_MAC);
                break;
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
    }
}