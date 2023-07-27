package UI;

import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjectModel.UI.Authentication;

public class Login {
    private WebDriver driver;
    private JSONFileManager testDataReader;
    private Authentication auth;

    @DataProvider(name = "login")
    public Object[][] dataProvider(){
        return new Object[][] {
                testDataReader.getTestDataAsArrayObjects("loginUser1"),
                testDataReader.getTestDataAsArrayObjects("loginUser2"),
                testDataReader.getTestDataAsArrayObjects("loginUser3")
        };
    }

    @Test(dataProvider = "login")
    public void loginToAccount(String email, String password, String username){

        auth.login(email, password);

        Assert.assertEquals(auth.getLoggedInName(), username);
    }

    @AfterMethod
    public void logout(){
       auth.logout();
    }

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver");

        driver = new ChromeDriver();
        auth = new Authentication(driver);
        testDataReader = new JSONFileManager("src/main/resources/testData/Login.json");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
