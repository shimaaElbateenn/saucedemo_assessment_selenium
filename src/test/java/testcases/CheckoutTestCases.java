package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.NavigationPage;
import utilities.Constants;
import utilities.ExcelUtility;

public class CartTestCases extends BaseTest {

    @DataProvider(name = "checkoutData")
    public Object[][] getCheckoutData() {
        Object[][] testData = ExcelUtility.getTestData("checkout_data");
        return testData;
    }

    @BeforeClass
    public void setUp() {
        homePage = loginPage.signInWith(Constants.USER_NAME, Constants.PASSWORD);
        homePage.addProductsToCart();
        navigationPage = new NavigationPage(driver);
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE, "checkoutTests");
    }

    @BeforeMethod
    public void beforeMethod() {
        cartPage = navigationPage.openCart();
    }

    @Test
    public void verifyRemovingProduct() {
        Assert.assertTrue(cartPage.isProductPresent());
        cartPage.removeProduct();
        Assert.assertFalse(cartPage.isProductPresent());
    }

    @Test(dataProvider = "checkoutData")
    public void verifyCalculatingTotalPrice(String firstName, String lastName, String postalCode) {
        double expectedTotalPrice = cartPage.calculateProductsPrice();
        checkoutPage = cartPage.checkout();
        checkoutPage.submitUserData(firstName, lastName, postalCode);
        double actualTotalPrice = checkoutPage.getTotalPrice();
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test(dataProvider = "checkoutData")
    public void verifySuccessfulCheckoutProcess(String firstName, String lastName, String postalCode) {
        checkoutPage = cartPage.checkout();
        checkoutPage.submitUserData(firstName, lastName, postalCode);
        checkoutPage.finishShopping();
        Assert.assertTrue(checkoutPage.verifyConfirmationMessageContains("Your order has been dispatched"));
    }
}
