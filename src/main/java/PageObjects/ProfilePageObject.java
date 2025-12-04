package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    private By logOutButton = By.cssSelector("[data-testid='logout-btn']");
    private By emailField = By.cssSelector("[data-testid='user-email']");
    public By preloader = By.cssSelector("[data-testid='preloader']");

    public ProfilePageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /* ---------- Visibility Waits ---------- */
    public void waitForProfilePageLoad(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
    }

    private void waitForPreloaderToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
    }

    public void clickLogOutButton(){
        waitForPreloaderToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
    }


    public String getEmailField() {
        waitForPreloaderToDisappear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return driver.findElement(emailField).getAttribute("value");
    }
}
