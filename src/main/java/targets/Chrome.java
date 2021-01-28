package targets;

import helper.Common;
import helper.Constants;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.BasePage;
import java.util.concurrent.TimeUnit;

public class Chrome {
    /***
     * This method is desired to initialize the driver according to the parameter comes from the testng file in the
     * setup method in the TestBase class
     */
    public static void init() {
        RemoteWebDriver driver = BasePage.getDriver();
        Common.OSType osType = Common.getOperatingSystemType();

        switch (osType){
            case Windows:
                System.setProperty(Constants.CHROME_DRIVER_PROPERTY, Constants.CHROME_DRIVER_PATH_WIN);
                break;
            case MacOS:
                System.setProperty(Constants.CHROME_DRIVER_PROPERTY, Constants.CHROME_DRIVER_PATH_MAC);
                break;
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
    }
}
