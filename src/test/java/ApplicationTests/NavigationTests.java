package ApplicationTests;

import Base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class NavigationTests extends BaseTest {

    @Test
    @DisplayName("Navigation from the home page to the login page via the “Profile” button")
    public void navigateToLoginPageFromHomePageProfileButton(){
        homePage.clickOnProfileButton();
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Navigation from the home page to the login page via the “Log In” button")
    public void navigateToLoginPageFromHomePageLoginButton(){
        homePage.clickOnLoginButton();
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Navigation from the profile page to the constructor via the “Constructor” button")
    public void navigateToConstructorPageFromLoginPage(){
        homePage.clickOnProfileButton();
        loginPage.clickConstructorButton();
        Assert.assertEquals(PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Navigation from the profile page to the home page via the Stellar Burgers logo")
    public void navigateToHomePageFromLoginPage(){
        homePage.clickOnProfileButton();
        loginPage.clickLogoButton();
        Assert.assertEquals(PAGE_URL, driver.getCurrentUrl());
    }
}
