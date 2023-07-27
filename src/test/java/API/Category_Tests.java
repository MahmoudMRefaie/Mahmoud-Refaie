package API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.framework.JSONFileManager;
import org.framework.ReportManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectModel.API.Category;

public class Category_Tests {
    private JSONFileManager testDataReader;
    private Category category;

    @Test
    public void checkCreateCategory(){

        String categoryID = testDataReader.getTestData("created_category.categoryID")+ System.currentTimeMillis();
        Response response = category.createCategory(categoryID,
                testDataReader.getTestData("created_category.categoryName"));

        JsonPath jsonPath = new JsonPath(response.body().asString());

        Assert.assertEquals(jsonPath.getString("id"), categoryID);
        Assert.assertEquals(jsonPath.getString("name"),
                testDataReader.getTestData("created_category.categoryName"));
    }

    @Test
    public void checkCreateCategoryWithExistsId(){
        String categoryID = testDataReader.getTestData("created_category.categoryID")+ System.currentTimeMillis();
        category.createCategory(categoryID,
                testDataReader.getTestData("created_category.categoryName"));
        Response response = category.createInvalidCategory(categoryID,
                testDataReader.getTestData("created_category.categoryName"));

        JsonPath jsonPath = new JsonPath(response.body().asString());

        Assert.assertEquals(jsonPath.getString("message"),
                testDataReader.getTestData("categoryWithExistsId.message"));
        Assert.assertEquals(jsonPath.getString("errors[0].message"),
                testDataReader.getTestData("categoryWithExistsId.error"));
    }

    @Test
    public void checkGettingCategoriesNameContainsSpecificString(){
        String containedString = testDataReader.getTestData("categoryName_contains.substring");
        Response response = category.getCategoriesNameContainsSpecificString(containedString);

        JsonPath jsonPath = new JsonPath(response.body().asString());

        ReportManager.log("Assert that all returned categories contains: " + containedString);
        for (Object categoryName: jsonPath.getList("data.name")) {
            Assert.assertTrue(categoryName.toString().contains(containedString));
        }
    }

    @BeforeClass
    public void setUp() {
        category = new Category();
        testDataReader = new JSONFileManager("src/main/resources/testData/Category_Tests.json");
    }

}
