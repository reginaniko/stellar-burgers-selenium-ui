package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

import java.time.Duration;

@Getter
public class HomePageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    public By profileButton = By.cssSelector("[data-testid='profile-btn']");
    public By loginButton = By.cssSelector("[data-testid='place-order-btn']");
    public By bunButton = By.xpath("//span[text()='Buns']");
    public By sauceButton = By.xpath("//span[text()='Sauces']");
    public By toppingsButton = By.xpath("//span[text()='Toppings']");
    public By preloader = By.cssSelector("[data-testid='preloader']");

    public HomePageObject (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /* ---------- Visibility Wait ---------- */
    private void waitForPreloaderToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
    }

    /* ---------- Generic constructor tab locator ---------- */

    // Returns the <div class="tab ..."> for a given label, e.g. "Buns", "Sauces", "Toppings"
    private By tabByLabel(String label) {
        return By.xpath(
                "//div[contains(@class,'tab') and .//span[normalize-space()='" + label + "']]"
        );
    }

    /* ---------- Actions ---------- */

    public void clickOnProfileButton(){
        waitForPreloaderToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    public void clickOnLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }


    @Step("Click constructor tab: {label}")
    public void clickTab(String label) {
        waitForPreloaderToDisappear();
        By tabLocator = tabByLabel(label);
        wait.until(ExpectedConditions.elementToBeClickable(tabLocator)).click();
    }

     /* ---------- State checks for assertions ---------- */

     @Step("Check if constructor tab '{label}' is active")
     public boolean isTabActive(String label) {
         By tabLocator = tabByLabel(label);
         wait.until(ExpectedConditions.visibilityOfElementLocated(tabLocator));
         String classes = driver.findElement(tabLocator).getAttribute("class");
         return classes != null && classes.contains("tab_type_current");
     }
}
