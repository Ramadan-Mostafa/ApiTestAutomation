package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage {
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



//    public static void takeScreenshot() {
//        try {
//            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(srcFile,new File(Constants.SCREENSHOT_DIR+ Common.getCurrentTime()+".jpg"));
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println(e.getMessage());
//        }
//    }