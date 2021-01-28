package helper;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * WebServiceHelper is responsible of sending all HTTP requests using restAssured
 */
public class WebServiceHelper {

    private RequestSpecification request;
    private String apiKey = Constants.API_KEY;
    public static HashMap<String, String> header = new HashMap<>();

    public WebServiceHelper() {

        header.put("Content-Type", "application/json");
        header.put("X-CMC_PRO_API_KEY", apiKey);
        request = given();
        request.headers(header);
    }

    public WebServiceHelper(Map<String, Object> params) {
        header.put("Content-Type", "application/json");
        header.put("X-CMC_PRO_API_KEY", apiKey);
        request = given();
        request.params(params);
        request.headers(header);
        params.clear();
    }

    /**
     * execute is responsible of sending the actual HTTP Request
     *
     * @param endpoint: is the endpoint inside the request that will be sent
     * @param method:   is the request type(verb)
     * @return response of the request sent
     */
    public Response execute(String endpoint, HttpMethod method) {
        Response response = null;
        switch (method) {
            case GET: {
                response = request.get(endpoint);
                break;
            }
            case POST: {
                response = request.post(endpoint);
                break;
            }
            case DELETE: {
                response = request.delete(endpoint);
                break;
            }
            case PUT: {
                response = request.put(endpoint);
                break;
            }
        }
        return response;
    }

    public enum HttpMethod {

        GET("get"), POST("post"), DELETE("delete"), PUT("put");

        private String method;

        HttpMethod(String method) {
            this.method = method;
        }
    }
}