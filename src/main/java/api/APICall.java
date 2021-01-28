package api;

import helper.Constants;
import helper.WebServiceHelper;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class APICall {
    WebServiceHelper webServiceHelper;

    Map<String, Object> params = new HashMap<>();


    // Get one currency id
    public String getOneCurrencyId(String currencySymbol) {
        webServiceHelper = new WebServiceHelper();
        int index = 0;
        String id = null;
        Response response = webServiceHelper.execute(Constants.MAP_ENDPOINT, WebServiceHelper.HttpMethod.GET);
        ArrayList<String> currency = response.jsonPath().get("data.symbol");
        if (currency.contains(currencySymbol)) {
            index = currency.indexOf(currencySymbol);
        }
        id = response.jsonPath().getString("data.id[" + index + "]");
        return id;
    }

    // Convert to Boliviano after getting the id
    public Response convertToBoliviano(String currencySymbol, String amount) {
        String id = getOneCurrencyId(currencySymbol);
        params.put("id", id);
        params.put("amount", amount);
        webServiceHelper = new WebServiceHelper(params);
        Response response = webServiceHelper.execute(Constants.CONVERT_ENDPOINT, WebServiceHelper.HttpMethod.GET);
        System.out.println(response.getBody().prettyPrint());
        return response;
    }

    // Check the status
    public int checkStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        return statusCode;
    }

    // Get all currencies ids
    public Map<String, String> getAllCurrenciesIds(Map<String, String> currency) {
        webServiceHelper = new WebServiceHelper();
        Response response = webServiceHelper.execute(Constants.MAP_ENDPOINT, WebServiceHelper.HttpMethod.GET);
        String eth = currency.get("eth");
        String usdt = currency.get("usdt");
        String btc = currency.get("btc");

        Map<String, String> currencyIds = new HashMap<>();

        ArrayList<String> currencyList = response.jsonPath().get("data.symbol");

        int ETHIndex = currencyList.indexOf(eth);
        int BTCIndex = currencyList.indexOf(btc);
        int USDTIndex = currencyList.indexOf(usdt);

        currencyIds.put(usdt, response.jsonPath().getString("data.id[" + USDTIndex + "]"));
        currencyIds.put(btc, response.jsonPath().getString("data.id[" + BTCIndex + "]"));
        currencyIds.put(eth, response.jsonPath().getString("data.id[" + ETHIndex + "]"));

        for (Map.Entry<String, String> entry : currencyIds.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        return currencyIds;
    }

    // Get the info for ethereum
    public Response retrieveEthereum(String id) {
        params.put("id", id);
        webServiceHelper = new WebServiceHelper(params);
        Response response = webServiceHelper.execute(Constants.EHTEREUM_ENDPOINT, WebServiceHelper.HttpMethod.GET);
        return response;
    }


}
