package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    private By logInButton = By.xpath("//a[contains(text(),'Log In')]");

    public ForgotPasswordPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLogInButton(){
        wait.until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }
}
