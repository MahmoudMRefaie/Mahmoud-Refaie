package pageObjectModel.API;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.framework.ReportManager;

import java.util.HashMap;
import java.util.Map;

public class Product extends API {

    private final String productsServiceName = "/products";

    /**
     * This method is user to create new produce
     * @param productName new product name
     * @param type produce type
     * @param UPCCode upcCode
     * @param price produce price
     * @param description produce description
     * @param model produce model
     * @return response with new created produce details
     */
    public Response createProduct(String productName,String type,String UPCCode, double price, String description, String model) {

        ReportManager.log("Create new product: " + productName);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", productName);
        requestBody.addProperty("type", type);
        requestBody.addProperty("upc", UPCCode);
        requestBody.addProperty("price", price);
        requestBody.addProperty("description", description);
        requestBody.addProperty("model", model);

        return performRequest(productsServiceName, RequestType.POST, 201 , requestBody);
    }

    /**
     * This method is used to create produce with missing required model attribute
     * @param productName new product name
     * @param type produce type
     * @param UPCCode upcCode
     * @param price produce price
     * @param description produce description
     * @return response with failure details
     */
    public Response createProductMissingAttribute(String productName,String type,String UPCCode, double price, String description) {

        ReportManager.log("Create new product: " + productName);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", productName);
        requestBody.addProperty("type", type);
        requestBody.addProperty("upc", UPCCode);
        requestBody.addProperty("price", price);
        requestBody.addProperty("description", description);

        return performRequest(productsServiceName, RequestType.POST, 400 , requestBody);
    }

    /**
     * Ths method is used to get highest priced produces
     * @return response with limited highest priced products
     */
    public Response getHighestPricedProducts() {

        ReportManager.log("Get highest priced products");

        Map queryParams = new HashMap();
        queryParams.put("$sort[price]", -1);

        return performRequestQueryParameter(productsServiceName, RequestType.GET, 200  , queryParams);
    }

}
