
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import reportgenerator.ReportManager;

import java.lang.reflect.Method;

public class TestBase {

    @BeforeSuite
    public void suiteSetup() {
        ReportManager.generateReport();
        ReportManager.setEnvironmentData();
    }

    @BeforeTest
    @Parameters({"platform"})
    public static void setUp(String platform) {
        switch (platform) {
            case "firefox":
                targets.Firefox.init();
                break;
            case "chrome":
                targets.Chrome.init();
                break;
            case "api":
                break;
        }
    }

    @BeforeMethod
    public void beforeTestMethod(Method method) {
        ReportManager.stepLogGeneration(method.getName());
    }

    @AfterMethod
    public void getStatus(Method method, ITestResult result) {
        ReportManager.getTestStatus(result);
    }

    @AfterClass
    public void tearDown() {
        BasePage.quit();
    }

    @AfterSuite
    public void generateReport() {
        ReportManager.endReport();
    }
}
