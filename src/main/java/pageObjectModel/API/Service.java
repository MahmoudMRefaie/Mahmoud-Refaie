package pageObjectModel.API;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.framework.ReportManager;

public class Service extends API{

    private final String servicesServiceName = "/services";

    /**
     * This method is used to create new service
     * @param serviceName new service name
     * @return response with new created service details
     */
    public Response createService(String serviceName) {

        ReportManager.log("Create new service: " + serviceName);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", serviceName);

        return performRequest(servicesServiceName, RequestType.POST, 201 , requestBody);
    }

    /**
     * This method is used to create new service with invalid input
     * @param invalidServiceName invalid service name
     * @return response with failure details for this service
     */
    public Response createServiceWithInvalidInput(int invalidServiceName) {

        ReportManager.log("Create invalid new service: " + invalidServiceName);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", invalidServiceName);

        return performRequest(servicesServiceName, RequestType.POST, 400 , requestBody);
    }

}
