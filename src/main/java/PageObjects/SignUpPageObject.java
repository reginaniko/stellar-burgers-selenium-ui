package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    private By nameInputField = By.cssSelector("[data-testid='register-name']");
    private By emailInputField = By.cssSelector("[data-testid='register-email']");
    private By passwordInputField = By.cssSelector("[data-testid='register-password']");
    private By signUpButton = By.cssSelector("[data-testid='register-submit']");
    public By passwordValidationError = By.xpath("//p[contains(text(),'Wrong Password')]");

    public By logInButton = By.cssSelector("[data-testid='register-login']");

    public SignUpPageObject(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void populateNameField(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputField))
            .sendKeys(username);
    }

    public void populateEmailField(String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputField))
            .sendKeys(email);
    }

    public void populatePasswordField(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputField))
            .sendKeys(password);
    }

    public void clickSignUpButton(){
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }

    public void clickLogInButton(){
        wait.until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }
}
