package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage {
    // This is the base which is responsible for creating the remote driver, set and get
    protected static RemoteWebDriver driver = null;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public static RemoteWebDriver getDriver() {
        return driver;
    }

    public static void setDriver(RemoteWebDriver currentDriver) {
        driver = currentDriver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
