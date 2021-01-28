import org.testng.annotations.*;
import pages.BasePage;
import pages.HomePage;

public class UITest extends TestBase {
    HomePage homePage = new HomePage(BasePage.getDriver());

    @Test
    public void testVerify100Results() {
        homePage.verify100ResultsIsExist();
    }

    @Test
    public void testAdd5Cryptocurrency() {
        homePage.add5Cryptocurrency();
    }
}