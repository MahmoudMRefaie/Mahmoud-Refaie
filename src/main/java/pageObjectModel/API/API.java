package pageObjectModel.API;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.framework.PropertiesManager;
import org.framework.ReportManager;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class API {

    private final String baseURL;

    public API() {
        new PropertiesManager();
        baseURL = System.getProperty("BestBuyBaseURL");
    }

    public enum RequestType {
        POST("POST"), GET("GET");

        RequestType(String requestType) {
            this.requestType = requestType;
        }

        private final String requestType;
        public String getRequestType() {
            return requestType;
        }
    }

    /**
     * This method is created to perform requests using request body
     * @param serviceName used endpoint
     * @param requestType request method
     * @param statusCode expected request status code
     * @param requestBody request body
     * @return response contains request response details
     */
    public Response performRequest(String serviceName,RequestType requestType, int statusCode , JsonObject requestBody){
        RestAssured.baseURI = baseURL;
        ReportManager.log("Perform request: " + baseURL+serviceName);

        switch (requestType.getRequestType()) {
        case "POST":
            return given()
                    .header("Content-type", "application/json")
                    .and().body(requestBody)
                    .when().post(serviceName)
                    .then().statusCode(statusCode).extract().response();
        case "GET":
            return given()
                    .header("Content-type", "application/json")
                    .and().body(requestBody)
                    .when().get(serviceName)
                    .then().statusCode(statusCode).extract().response();
        }
        return null;
    }

    /**
     * This method is created to perform requests using query parameters
     * @param serviceName used endpoint
     * @param requestType request method
     * @param statusCode expected request status code
     * @param queryParameters request query parameters
     * @return response contains request response details
     */
    public Response performRequestQueryParameter(String serviceName,RequestType requestType, int statusCode , Map<String, ?> queryParameters){
        RestAssured.baseURI = baseURL;
        ReportManager.log("Perform request: " + baseURL+serviceName);

        switch (requestType.getRequestType()) {
        case "POST":
            return given()
                    .header("Content-type", "application/json")
                    .and().queryParams(queryParameters)
                    .when().post(serviceName)
                    .then().statusCode(statusCode).extract().response();
        case "GET":
            return given()
                    .header("Content-type", "application/json")
                    .and().queryParams(queryParameters)
                    .when().get(serviceName)
                    .then().statusCode(statusCode).extract().response();
        }
        return null;
    }
}
