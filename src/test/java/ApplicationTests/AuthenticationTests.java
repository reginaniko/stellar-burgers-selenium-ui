package ApplicationTests;
import io.qameta.allure.junit4.DisplayName;
import Base.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;


public class AuthenticationTests extends BaseTest {

    @Test
    @DisplayName("Login from the home page via 'Log in to account' button")
    public void logInWithSignInButtonIsSuccessful(){
        createUser();
        homePage.clickOnLoginButton();
        populateLogInFieldsAndClickLogInButton();
        navigateToProfileAndCompareEmail();
    }

    @Test
    @DisplayName("Login from the home page via 'Profile' button")
    public void logInWithProfileButtonIsSuccessful(){
        createUser();
        homePage.clickOnProfileButton();
        populateLogInFieldsAndClickLogInButton();
        navigateToProfileAndCompareEmail();
    }

    @Test
    @DisplayName("Login from registration form via 'Log in' button")
    public void logInFromSignUpPageButtonIsSuccessful(){
        createUser();
        System.out.println(driver.findElement(homePage.profileButton).getText());
        homePage.clickOnProfileButton();
        loginPage.clickSignUpButton();
        signUpPage.clickLogInButton();
        populateLogInFieldsAndClickLogInButton();
        loginPage.navigateToHomePage();
        navigateToProfileAndCompareEmail();
    }

    @Test
    @DisplayName("Login from password recovery form via 'Log in' button")
    public void logInFromRestorePasswordPageIsSuccessful(){
        createUser();
        homePage.clickOnProfileButton();
        loginPage.clickRestorePasswordButton();
        forgotPasswordPage.clickLogInButton();
        populateLogInFieldsAndClickLogInButton();
        navigateToProfileAndCompareEmail();
    }

    @Test
    @DisplayName("Logout from profile via 'Log out' button")
    public void verifyLogOutIsSuccessful(){
        createUser();
        homePage.clickOnLoginButton();
        populateLogInFieldsAndClickLogInButton();
        homePage.clickOnProfileButton();
        profilePage.clickLogOutButton();
        loginPage.waitForLogInPageLoad();
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @After
    public void deleteLogInUser() {
        if (createUserResponse != null && createUserResponse.getAccessToken() != null) {
            deleteUser(createUserResponse.getAccessToken());
        } else {
            System.out.println("No user was created. Skipping deletion.");
        }
    }
}
