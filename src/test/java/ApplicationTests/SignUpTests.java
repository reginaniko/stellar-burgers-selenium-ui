package ApplicationTests;

import Base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SignUpTests extends BaseTest {

    private final List<String> userTokens = new ArrayList<>();

    @Test
    @DisplayName("User can successfully register using unique data")
    public void verifyCorrectSignUpIsSuccessful(){
        String uniqueEmail = "sb_" + System.currentTimeMillis() + "@test.com";
        String password = faker.internet().password();
        String name = faker.name().username();

        userRequestBody.setEmail(uniqueEmail);
        userRequestBody.setPassword(password);
        userRequestBody.setName(name);

        navigateToSignUpPage();
        populateSignUpFieldsAndClickSignUpButton(name, uniqueEmail, password);
        navigateToProfileAndCompareEmail();
        Assert.assertEquals(USER_PROFILE_URL, driver.getCurrentUrl());
        userTokens.add(getAccessToken(uniqueEmail, password)); //clean up
}

    @Test
    @DisplayName("Validation error appears when password is shorter than 6 characters")
    public void verifyWrongPasswordReturnsError(){
        navigateToSignUpPage();
        String shortPass = faker.internet().password(1, 5);

        signUpPage.populatePasswordField(shortPass);
        signUpPage.clickSignUpButton();
        Assert.assertTrue(driver.findElement(signUpPage.passwordValidationError).isDisplayed());
    }

    @After
    public void deleteUser(){
        if (!userTokens.isEmpty()){
            RestAssured.baseURI = PAGE_URL;
            for (String token : userTokens) {
                deleteUser(token);
            }
        }else{
            System.out.println("No user was created. Skipping deletion.");
        }
    }
}
