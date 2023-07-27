package pageObjectModel.API;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.framework.ReportManager;

import java.util.HashMap;
import java.util.Map;

public class Category extends API {

    private final String categoriesServiceName = "/categories";

    /**
     * This method is user to create new category
     * @param categoryID category ID
     * @param categoryName new category name
     * @return response with new created category details
     */
    public Response createCategory(String categoryID, String categoryName) {

        ReportManager.log("Create new category: " + categoryName);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("id", categoryID);
        requestBody.addProperty("name", categoryName);

        return performRequest(categoriesServiceName, RequestType.POST, 201 , requestBody);
    }

    /**
     * This method is used to create invalid category
     * @param categoryID invalid category ID
     * @param categoryName new category name
     * @return response with failure details
     */
    public Response createInvalidCategory(String categoryID, String categoryName) {

        ReportManager.log("Create invalid category: " + categoryName);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("id", categoryID);
        requestBody.addProperty("name", categoryName);

        return performRequest(categoriesServiceName, RequestType.POST, 400 , requestBody);
    }

    /**
     * This method is used to get categories name that contain specific string
     * @param nameContains substring from category names
     * @return response with categories that contains this string in its name
     */
    public Response getCategoriesNameContainsSpecificString(String nameContains) {

        ReportManager.log("Get highest priced products");

        Map queryParams = new HashMap();
        queryParams.put("name[$like]", "*" + nameContains + "*");

        return performRequestQueryParameter(categoriesServiceName, RequestType.GET, 200  , queryParams);
    }
}
