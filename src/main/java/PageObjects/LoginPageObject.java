package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    public By constructorButton = By.cssSelector("[data-testid='constructor-btn']");
    public By logoButton = By.cssSelector("[data-testid='logo-btn']");
    public By emailInputField = By.cssSelector("[data-testid='login-email']");
    private By passwordInputField = By.cssSelector("[data-testid='login-password']");
    private By logInButton = By.cssSelector("[data-testid='login-submit']");
    public By signUpButton = By.cssSelector("[data-testid='signup-link']");
    public By restorePasswordButton = By.cssSelector("[data-testid='forgot-link']");


    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToSignUpPage(){
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }

    public void navigateToHomePage(){
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
    }

    public void waitForLogInPageLoad(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpButton));
    }

    public void populateEmailField(String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputField))
            .sendKeys(email);
    }

    public void populatePasswordField(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputField))
            .sendKeys(password);
    }

    public void populateAllFields(String email, String password){
        populateEmailField(email);
        populatePasswordField(password);
    }

    public void clickLogInButton(){
        wait.until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }

    public void clickConstructorButton(){
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    public void clickLogoButton(){
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
    }

    public void clickSignUpButton(){
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }

    public void clickRestorePasswordButton(){
        wait.until(ExpectedConditions.elementToBeClickable(restorePasswordButton)).click();
    }

    public By getEmailInputField() {
        return emailInputField;
    }
}
