package UI;

import org.framework.JSONFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageObjectModel.UI.Authentication;

public class Registration {
    private WebDriver driver;
    private JSONFileManager testDataReader;
    private Authentication auth;

    @DataProvider(name = "register")
    public Object[][] dataProvider(){
        return new Object[][] {
                testDataReader.getTestDataAsArrayObjects("registerUser1"),
                testDataReader.getTestDataAsArrayObjects("registerUser2"),
                testDataReader.getTestDataAsArrayObjects("registerUser3")
        };
    }

    @Test(dataProvider = "register")
    public void registerNewAccount(String firstName, String surName, String email, String password,
            String dayOfBirth, String monthOfBirth, String yearOfBirth, String gender){

        auth.signUp(firstName, surName, System.currentTimeMillis() + email, password, dayOfBirth, monthOfBirth, yearOfBirth, gender);

        //Assert.assertEquals(auth.getCreatedEmail(), email.toLowerCase());
    }

    @AfterMethod
    public void returnToRootPage(){
        auth.openWebsite();
    }

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver");
        driver = new ChromeDriver();
        auth = new Authentication(driver);
        testDataReader = new JSONFileManager("src/main/resources/testData/Registration.json");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
