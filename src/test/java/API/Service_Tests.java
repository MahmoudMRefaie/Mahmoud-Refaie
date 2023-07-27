package API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.framework.JSONFileManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModel.API.Service;

public class Service_Tests {
    private JSONFileManager testDataReader;
    private Service service;

    @Test
    public void testCreateService() {
        String serviceName = testDataReader.getTestData("created_service.serviceName");

        Response response = service.createService(serviceName);

        JsonPath jsonPath = new JsonPath(response.body().asString());

        Assert.assertEquals(jsonPath.getString("name"), serviceName);
        Assert.assertNotNull(jsonPath.getString("createdAt"));
    }

    @Test
    public void testCreatingStoreWithInvalidInputData() {

        Response response = service.createServiceWithInvalidInput(
                Integer.parseInt(testDataReader.getTestData("service_InvalidInput.invalidServiceName")));

        JsonPath jsonPath = new JsonPath(response.body().asString());

        Assert.assertEquals(jsonPath.getString("message"), testDataReader.getTestData("service_InvalidInput.message"));
        Assert.assertEquals(jsonPath.getList("errors").get(0), testDataReader.getTestData("service_InvalidInput.error"));
    }

    @BeforeClass
    public void setUp() {
        service = new Service();
        testDataReader = new JSONFileManager("src/main/resources/testData/Service_Tests.json");
    }
}
