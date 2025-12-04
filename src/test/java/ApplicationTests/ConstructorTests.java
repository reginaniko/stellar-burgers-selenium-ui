package ApplicationTests;

import Base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorTests extends BaseTest {

    @Test
    @DisplayName("Navigation to Buns section activates corresponding tab")
    public void verifyNavigationToBunsSectionIsSuccessful() {
        homePage.clickTab("Toppings");
        homePage.clickTab("Buns");
        Assert.assertTrue(homePage.isTabActive("Buns"));
    }

    @Test
    @DisplayName("Navigation to Sauces section activates corresponding tab")
    public void verifyNavigationToSauceSectionIsSuccessful() {
        homePage.clickTab("Sauces");
        Assert.assertTrue(homePage.isTabActive("Sauces"));
    }

    @Test
    @DisplayName("Navigation to Toppings section activates corresponding tab")
    public void verifyNavigationToToppingsSectionIsSuccessful() {
        homePage.clickTab("Toppings");
        Assert.assertTrue(homePage.isTabActive("Toppings"));
    }
}
