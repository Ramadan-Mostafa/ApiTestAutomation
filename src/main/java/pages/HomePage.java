package pages;

import helper.LoadDataFile;
import helper.ReadExternalData;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(RemoteWebDriver driver) {
        super(driver);
    }

    // Login
    public void login() {

        String email = LoadDataFile.userdata.getProperty("email");
        String password = LoadDataFile.userdata.getProperty("password");
        // Click on the login
        WebElement btnLogin = helper.getElement.getWebElement("btnLogin");
        btnLogin.click();

        WebElement editTextEmail = helper.getElement.getWebElement("editTextEmail");
        WebElement editTextPassword = helper.getElement.getWebElement("editTextPassword");

        editTextEmail.sendKeys(email);
        editTextPassword.sendKeys(password);

        btnLogin.click();
    }

    // Check that the 100 results are exist
    public boolean verify100ResultsIsExist() {
        driver.get("https://coinmarketcap.com/");
        List<WebElement> TRCount = driver.findElements(By.tagName("tr"));
        System.out.println(TRCount.size());
        if (TRCount.size() == 100) {
            return true;
        } else {
            return false;
        }
    }

    // Add 5 cryptocurrencies
    public void add5Cryptocurrency() {
        driver.get("https://coinmarketcap.com/");

        // Select 5 icon stars
        driver.findElement(By.xpath("(//span[child::span[@class='icon-Star']]/parent::*)[2]")).click();

        helper.getElement.getWebElement("btnCheckout").click();
        login();
        List<WebElement> iconStar = driver.findElements(By.xpath("//span[child::span[@class='icon-Star']]/parent::*"));

        for (WebElement element : iconStar) {
            for (int i = 0; i < 6; i++) {
                element.click();
            }
            break;
        }

        // Open watch list in a new tab
        String lnkWatchList = Keys.chord(Keys.CONTROL, Keys.ENTER);
        helper.getElement.getWebElement("btnWatchList").sendKeys(lnkWatchList);

        // Go to the watch list tab

        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            driver.switchTo().window(availableWindows.get(1));
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
