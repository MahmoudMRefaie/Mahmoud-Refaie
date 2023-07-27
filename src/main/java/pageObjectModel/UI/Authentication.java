package pageObjectModel.UI;

import org.framework.PropertiesManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Authentication {

    private final WebDriver driver;
    private final String baseURL;
    private final By email_loginInput = By.id("email");
    private final By password_loginInput = By.id("pass");
    private final By login_btn = By.cssSelector("[data-testid='royal_login_button']");
    private final By moreOptions_drp = By.cssSelector("[aria-label='More options']");
    private final By logout_btn = By.cssSelector("[data-visualcompletion='ignore-dynamic'][data-nocookies='true']");
    private final By logoutConfirm = By.xpath("//span[contains(text(),'Log out')]");
    private final By usernameProfileName = By.cssSelector("[role='banner'] .xuxw1ft");

    private final By createNewAccount_btn = By.cssSelector("[data-testid='open-registration-form-button']");
    private final By signUpPopup = By.className("_8ien");
    private final By firstNameEle = By.xpath("//input[@name='firstname']");
    private final By surNameEle = By.xpath("//input[@name='lastname']");
    private final By emailEle = By.xpath("//input[@name='reg_email__']");
    private final By emailConfirmationEle = By.xpath("//input[@name='reg_email_confirmation__']");
    private final By passwordEle = By.xpath("//input[@name='reg_passwd__']");
    private final By dayOfBirthEle = By.xpath("//select[@name='birthday_day']");
    private final By monthOfBirthEle = By.xpath("//select[@name='birthday_month']");
    private final By yearOfBirthEle = By.xpath("//select[@name='birthday_year']");
    private final By submitbun = By.xpath("//div[@class='large_form'] //button[@type='submit']");
    private final By emailFwdCode = By.cssSelector(".UIFullPage_Container .fwb");


    public enum Gender {
        MALE("Male"), FEMALE("Female"), CUSTOM("Custom");

        Gender(String gender) {
            this.gender = gender;
        }

        private final String gender;
        public String getGender() {
            return gender;
        }
    }

    public Authentication(WebDriver driver) {
        this.driver = driver;
        new PropertiesManager();
        baseURL = System.getProperty("FacebookBaseURL");
        driver.get(baseURL);
        openWebsite();
    }

    /**
     * This method is used to login to facebook
     * @param email user logged in email
     * @param password user logged in password
     */
    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(login_btn));

        driver.findElement(email_loginInput).sendKeys(email);
        driver.findElement(password_loginInput).sendKeys(password);
        driver.findElement(login_btn).click();
    }

    /**
     * This method is used to logout from the website
     */
    public void logout(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(moreOptions_drp).click();
        driver.findElement(logout_btn).click();
        driver.findElement(logoutConfirm).click();
    }

    /**
     * This method is used to open main website in the browser
     */
    public void openWebsite(){
        driver.get(baseURL);
    }

    /**
     * This method is used to go to registration page
     */
    public void clickCreateNewAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(createNewAccount_btn));

        driver.findElement(createNewAccount_btn).click();
    }

    /**
     * This method is used to register new account
     * @param firstName user first name
     * @param surName user last name
     * @param email user email name
     * @param password user password name
     * @param dayOfBirth user day of birth name
     * @param monthOfBirth user month of birth name
     * @param yearOfBirth user year of birth name
     * @param gender user gender
     */
    public void signUp(String firstName, String surName, String email, String password,
            String dayOfBirth, String monthOfBirth, String yearOfBirth, String gender){

        clickCreateNewAccount();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(signUpPopup));

        driver.findElement(firstNameEle).sendKeys(firstName);
        driver.findElement(surNameEle).sendKeys(surName);
        driver.findElement(emailEle).sendKeys(email);

        WebElement emailConfirmation = driver.findElement(emailConfirmationEle);
        WebDriverWait waitEmailConfirmation = new WebDriverWait(driver, Duration.ofSeconds(2));
        waitEmailConfirmation.until(ExpectedConditions.visibilityOf(emailConfirmation));
        emailConfirmation.sendKeys(email);

        driver.findElement(passwordEle).sendKeys(password);

        selectBirthDate(dayOfBirth,monthOfBirth,yearOfBirth);

        selectGender(gender);

        driver.findElement(submitbun).click();
    }

    /**
     * This method is used to select whole date of birth (day, month, year)
     * @param dayOfBirth user day of birth
     * @param monthOfBirth user month of birth
     * @param yearOfBirth user year of birth
     */
    private void selectBirthDate(String dayOfBirth, String monthOfBirth, String yearOfBirth){
        Select drp_dayOfBirth = new Select(driver.findElement(dayOfBirthEle));
        drp_dayOfBirth.selectByValue(dayOfBirth);
        Select drp_monthOfBirth = new Select(driver.findElement(monthOfBirthEle));
        drp_monthOfBirth.selectByValue(monthOfBirth);
        Select drp_yearOfBirth = new Select(driver.findElement(yearOfBirthEle));
        drp_yearOfBirth.selectByValue(yearOfBirth);
    }

    /**
     * This method is used to select user gender
     * @param gender user gender
     */
    private void selectGender(String gender){
        driver.findElement(getGenderElement(gender)).click();
    }

    /**
     * This method is used to get gender element
     * @param gender gender string
     * @return gender element
     */
    private By getGenderElement(String gender){
        return By.xpath("//label[contains(text(),'" + gender + "')]");
    }

    /**
     * This method is used to get created email in confirmation email code page
     * @return created email
     */
    public String getCreatedEmail(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        return driver.findElement(emailFwdCode).getText();
    }

    /**
     * This method is used to get logged user name in the website banner
     * @return logged in user name
     */
    public String getLoggedInName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(usernameProfileName));

        return driver.findElement(usernameProfileName).getText();
    }

}
