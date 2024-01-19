package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.NavigationPage;
import utilities.Constants;

public class ProductListingTestCases extends BaseTest {

    @BeforeClass
    public void setUp() {
        homePage = loginPage.signInWith(Constants.USER_NAME, Constants.PASSWORD);
        navigationPage = new NavigationPage(driver);
    }

    @Test
    public void verifyProductListing() {
        homePage.selectSortOrder("Name (A to Z)");
        Assert.assertTrue(homePage.isProductsTitleSorted("Ascending"));
        homePage.selectSortOrder("Name (Z to A)");
        Assert.assertTrue(homePage.isProductsTitleSorted("Descending"));

        homePage.selectSortOrder("Price (low to high)");
        Assert.assertTrue(homePage.isProductsPricesSorted("Ascending"));
        homePage.selectSortOrder("Price (high to low)");
        Assert.assertTrue(homePage.isProductsPricesSorted("Descending"));
    }

    @Test
    public void verifyAddingAndRemovingProductFromCart() {
        homePage.addBackpackToCart();
        Assert.assertTrue(homePage.isRemoveButtonExists());
        homePage.removeProductFromCart();
        Assert.assertFalse(homePage.isRemoveButtonExists());
    }

    @Test
    public void verifyResetAppState() {
        homePage.addBackpackToCart();
        Assert.assertTrue(homePage.isShoppingCartBadgeExists());
        navigationPage.resetAppState();
        Assert.assertFalse(homePage.isShoppingCartBadgeExists());
    }
}
