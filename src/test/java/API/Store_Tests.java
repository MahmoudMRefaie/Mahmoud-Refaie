package API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.framework.JSONFileManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModel.API.Store;

public class Store_Tests {
    private JSONFileManager testDataReader;
    private Store store;

    @Test
    public void testCreateStore() {
        String storeName = testDataReader.getTestData("created_store.storeName");

        Response response = store.createStore(storeName,
                testDataReader.getTestData("created_store.storeType"),
                testDataReader.getTestData("created_store.storeAddress"),
                testDataReader.getTestData("created_store.city"),
                testDataReader.getTestData("created_store.state"),
                testDataReader.getTestData("created_store.zip"),
                Double.parseDouble(testDataReader.getTestData("created_store.latitude")),
                Double.parseDouble(testDataReader.getTestData("created_store.longitude")),
                testDataReader.getTestData("created_store.hours"));

        JsonPath jsonPath = new JsonPath(response.body().asString());

        Assert.assertEquals(jsonPath.getString("name"), storeName);
        Assert.assertNotNull(jsonPath.getString("updatedAt"));
    }

    @Test
    public void testCreatingStoreWithInvalidInputData() {
        String storeName = testDataReader.getTestData("created_store.storeName");

        Response response = store.createStoreWithInvalidInput(storeName,
                testDataReader.getTestData("created_store.storeType"),
                testDataReader.getTestData("created_store.storeAddress"),
                testDataReader.getTestData("created_store.city"),
                testDataReader.getTestData("created_store.state"),
                testDataReader.getTestData("invalidInput.hugeZipCode"),
                Double.parseDouble(testDataReader.getTestData("created_store.latitude")),
                Double.parseDouble(testDataReader.getTestData("created_store.longitude")),
                testDataReader.getTestData("created_store.hours"));

        JsonPath jsonPath = new JsonPath(response.body().asString());

        Assert.assertEquals(jsonPath.getString("message"), testDataReader.getTestData("invalidInput.message"));
        Assert.assertEquals(jsonPath.getList("errors").get(0), testDataReader.getTestData("invalidInput.error"));
    }

    @BeforeClass
    public void setUp() {
        store = new Store();
        testDataReader = new JSONFileManager("src/main/resources/testData/Store_Tests.json");
    }
}
