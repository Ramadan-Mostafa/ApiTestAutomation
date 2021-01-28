import api.APICall;
import helper.LoadDataFile;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ApiTest extends TestBase {
    Response response;
    APICall APICall = new APICall();

    @Test(priority = 1)
    public void retrieveCurrenciesIds() {
        Map<String, String> map = new HashMap<>();
        map.put("eth", "ETH");
        map.put("usdt", "USDT");
        map.put("btc", "BTC");
        map = APICall.getAllCurrenciesIds(map);
        // assert that the map contains the USDT id which is 825
        Assert.assertTrue(map.containsValue("825"));
    }

    @Test(priority = 2)
    public void convertOneCurrencyToBoliviano() {
        response = APICall.convertToBoliviano("ETH", "1000");
        Assert.assertEquals(APICall.checkStatusCode(response), 200);
    }

    @Test(priority = 3)
    public void checkEthereumInfo() {
        response = APICall.retrieveEthereum("1027");
        // Assert that the logo is exist
        System.out.println("logo: " + response.jsonPath().getString("data.1027.logo"));
        Assert.assertTrue(response.jsonPath().getString("data.1027.logo").equals(LoadDataFile.userdata.getProperty("logo")));

        // Assert that the technical_doc is present
        System.out.println("technical doc: " + response.jsonPath().getString("data.1027.urls.technical_doc[0]"));
        Assert.assertTrue(response.jsonPath().getString("data.1027.urls.technical_doc").equals(LoadDataFile.userdata.getProperty("technicalDoc")));

        // Assert that the symbol is ETH
        System.out.println("symbol: " + response.jsonPath().getString("data.1027.symbol"));
        Assert.assertTrue(response.jsonPath().getString("data.1027.symbol").equals(LoadDataFile.userdata.getProperty("symbol")));

        // Assert that date added
        System.out.println("date added: " + response.jsonPath().getString("data.1027.date_added"));
        Assert.assertTrue(response.jsonPath().getString("data.1027.date_added").equals(LoadDataFile.userdata.getProperty("dateAdded")));

        // Assert that platform is null
        Assert.assertTrue(response.jsonPath().getString("data.1027.platform") == null);

        // Assert that currency has mineable tag
        System.out.println("tags: " + response.jsonPath().getString("data.1027.tags"));
        Assert.assertTrue(response.jsonPath().getString("data.1027.tags").contains(LoadDataFile.userdata.getProperty("tags")));
    }
}
