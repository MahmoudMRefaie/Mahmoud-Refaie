package API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.framework.JSONFileManager;
import org.framework.ReportManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjectModel.API.Product;

public class Product_Tests {
    private JSONFileManager testDataReader;
    private Product product;

    @Test
    public void checkCreateProduct(){

        Response response = product.createProduct(testDataReader.getTestData("created_product.productName"),
                testDataReader.getTestData("created_product.type"),
                testDataReader.getTestData("created_product.upc"),
                Double.parseDouble(testDataReader.getTestData("created_product.price")),
                testDataReader.getTestData("created_product.description"),
                testDataReader.getTestData("created_product.model"));

        JsonPath jsonPath = new JsonPath(response.body().asString());

        Assert.assertEquals(jsonPath.getString("name"),
                testDataReader.getTestData("created_product.productName"));
        Assert.assertEquals(jsonPath.getDouble("price"),
                Double.parseDouble(testDataReader.getTestData("created_product.price")));
    }

    @Test
    public void checkCreateProductMissingAttribute(){

        Response response = product.createProductMissingAttribute(testDataReader.getTestData("created_product.productName"),
                testDataReader.getTestData("created_product.type"),
                testDataReader.getTestData("created_product.upc"),
                Double.parseDouble(testDataReader.getTestData("created_product.price")),
                testDataReader.getTestData("created_product.description"));

        JsonPath jsonPath = new JsonPath(response.body().asString());

        Assert.assertEquals(jsonPath.getString("message"), testDataReader.getTestData("missingAttribute.message"));
        Assert.assertEquals(jsonPath.getList("errors").get(0), testDataReader.getTestData("missingAttribute.error"));
    }

    @Test
    public void checkHighestPricedProducts(){

        Response response = product.getHighestPricedProducts();

        JsonPath jsonPath = new JsonPath(response.body().asString());

        ReportManager.log("Assert on highest priced products name");
        Assert.assertEquals(jsonPath.getList("data.name"),
                testDataReader.getTestDataAsList("highestPriced_products.productsNames"));
    }

    @BeforeClass
    public void setUp() {
        product = new Product();
        testDataReader = new JSONFileManager("src/main/resources/testData/Product_Tests.json");
    }

}
