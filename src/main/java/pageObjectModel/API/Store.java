package pageObjectModel.API;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.framework.ReportManager;

public class Store extends API{

    private final String storesServiceName = "/stores";

    /**
     * This method is used to create new store
     * @param storeName new store name
     * @param storeType store type
     * @param storeAddress store address
     * @param city store city
     * @param state located store state
     * @param zipCode store zip code
     * @param latitude store location latitude
     * @param longitude store location longitude
     * @param operationHours store operation hours
     * @return response with new created store details
     */
    public Response createStore(String storeName, String storeType, String storeAddress, String city, String state,
            String zipCode, double latitude, double longitude, String operationHours) {

        ReportManager.log("Create new store: " + storeName);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", storeName);
        requestBody.addProperty("type", storeType);
        requestBody.addProperty("address", storeAddress);
        requestBody.addProperty("city", city);
        requestBody.addProperty("state", state);
        requestBody.addProperty("zip", zipCode);
        requestBody.addProperty("lat", latitude);
        requestBody.addProperty("lng", longitude);
        requestBody.addProperty("hours", operationHours);

        return performRequest(storesServiceName, RequestType.POST, 201 , requestBody);
    }

    /**
     * This method is used to create invalid store using invalid input
     * @param storeName new store name
     * @param storeType store type
     * @param storeAddress store address
     * @param city store city
     * @param state located store state
     * @param zipCode long store zip code more than 30 characters
     * @param latitude store location latitude
     * @param longitude store location longitude
     * @param operationHours store operation hours
     * @return response with failure details
     */
    public Response createStoreWithInvalidInput(String storeName, String storeType, String storeAddress, String city, String state,
            String zipCode, double latitude, double longitude, String operationHours) {

        ReportManager.log("Create new store: " + storeName);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", storeName);
        requestBody.addProperty("type", storeType);
        requestBody.addProperty("address", storeAddress);
        requestBody.addProperty("city", city);
        requestBody.addProperty("state", state);
        requestBody.addProperty("zip", zipCode);
        requestBody.addProperty("lat", latitude);
        requestBody.addProperty("lng", longitude);
        requestBody.addProperty("hours", operationHours);

        return performRequest(storesServiceName, RequestType.POST, 400 , requestBody);
    }
}
