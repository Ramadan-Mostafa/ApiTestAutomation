package targets;

import helper.Common;
import helper.Constants;
import helper.ReadExternalData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.codehaus.groovy.reflection.android.AndroidSupport;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.BasePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IOS {
    static DesiredCapabilities capabilities = new DesiredCapabilities();
    static IOSDriver driver = (IOSDriver) BasePage.getDriver();

    /***
     * This method is desired to initialize the driver according to the parameter comes from the testng file in the
     * setup method in the TestBase class
     */
    public static void init() {
        ReadExternalData.setPlatform(Common.TargetPlatform.Android.toString());
        capabilities = ReadExternalData.setDesiredCapabilities();
        String appiumServerUrl = capabilities.getCapability(Constants.APPIUM_SERVER).toString();
        try {
            driver = new IOSDriver(new URL(appiumServerUrl), capabilities);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
