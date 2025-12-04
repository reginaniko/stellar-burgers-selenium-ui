package Base;

import HttpRequests.CreateUserRequest;
import HttpRequests.CreateUserResponse;
import PageObjects.*;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import io.restassured.parsing.Parser;
import static io.restassured.RestAssured.given;


public class BaseTest {
    protected WebDriver driver;
    //select browser (CHROME / YANDEX / FIREFOX)
    private final BrowserDriverFactory.BrowserType browserType = BrowserDriverFactory.BrowserType.valueOf(System.getProperty("browser", "CHROME").toUpperCase());

    public final String PAGE_URL = "https://reginaniko.github.io/stellar-burgers";
    public final String API_BASE_URL = "https://stellarburgers.education-services.ru";
    public final String LOGIN_URL = PAGE_URL + "/login";
    public final String SIGNUP_URL = PAGE_URL + "/register";
    public final String USER_PROFILE_URL = PAGE_URL + "/profile";
    public final String USER_ENDPOINT = "/api/auth/register";
    public final String DELETE_USER_ENDPOINT = "/api/auth/user";
    public final String LOGIN_USER_ENDPOINT = "/api/auth/login";

    protected HomePageObject homePage;
    protected SignUpPageObject signUpPage;
    protected LoginPageObject loginPage;
    protected ProfilePageObject profilePage;
    protected ForgotPasswordPageObject forgotPasswordPage;
    protected Faker faker = new Faker();
    //test data
    public CreateUserRequest userRequestBody = new CreateUserRequest (faker.internet().emailAddress(), faker.internet().password(), faker.name().username());
    public CreateUserResponse createUserResponse;

    @BeforeClass
    public static void setupRestAssured() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @Before
    public void setUp() {
        driver = BrowserDriverFactory.createDriver(browserType);
        driver.manage().window().maximize();
        driver.get(PAGE_URL);
        homePage = new HomePageObject(driver);
        signUpPage = new SignUpPageObject(driver);
        loginPage = new LoginPageObject(driver);
        profilePage = new ProfilePageObject(driver);
        forgotPasswordPage = new ForgotPasswordPageObject(driver);
    }

    @Step("Create user via API")
    public void createUser(){
            RestAssured.baseURI = API_BASE_URL;
            createUserResponse =
        given()
            .header("Content-type", "application/json")
            .body(userRequestBody)
            .log().all()
        .when()
            .post(USER_ENDPOINT)
        .then()
            .log().all()
            .statusCode(200)
            .extract()
            .as(CreateUserResponse.class);
    }

    @Step("Navigate to the sign-up page")
    public void navigateToSignUpPage(){
        homePage.clickOnProfileButton();
        loginPage.navigateToSignUpPage();
    }

    @Step("Populate login fields and click 'Log in'")
    public void populateLogInFieldsAndClickLogInButton(){
        loginPage.populateAllFields(userRequestBody.getEmail(), userRequestBody.getPassword());
        loginPage.clickLogInButton();
    }

    @Step("Open profile and compare email with the created user")
    public void navigateToProfileAndCompareEmail(){
        homePage.clickOnProfileButton();
        profilePage.waitForProfilePageLoad();
        Assert.assertEquals(userRequestBody.getEmail(), profilePage.getEmailField());
    }

    @Step("Populate sign-up fields and click 'Sign up'")
    public void populateSignUpFieldsAndClickSignUpButton(String name, String email, String password){
        signUpPage.populateNameField(name);
        signUpPage.populateEmailField(email);
        signUpPage.populatePasswordField(password);
        signUpPage.clickSignUpButton();
    }


    public String getAccessToken(String email, String password){
        RestAssured.baseURI = API_BASE_URL;
        CreateUserRequest body = CreateUserRequest.builder()
            .email(email)
            .password(password)
            .build();

        CreateUserResponse userResponse =
            given()
                .header("Content-type", "application/json")
                .body(body)
            .when()
                .post(LOGIN_USER_ENDPOINT)
            .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreateUserResponse.class);

        return userResponse.getAccessToken();
        }

    public void deleteUser(String token){
        given().header("Content-type", "application/json").header("Authorization", token).log().all()
                .delete(DELETE_USER_ENDPOINT).then().log().all();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
