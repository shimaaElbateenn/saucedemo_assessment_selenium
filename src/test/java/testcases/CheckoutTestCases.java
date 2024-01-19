package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.NavigationPage;
import utilities.Constants;
import utilities.ExcelUtility;

public class CheckoutTestCases extends BaseTest {

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
        double actualTotalPrice = checkoutPage.getItemTotalPrice();
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);

        double expectedPriceWithTax = checkoutPage.calculateTotalPriceAndTax();
        double actualPriceWithTax = checkoutPage.getTotalPriceWithTax();
        Assert.assertEquals(expectedPriceWithTax, actualPriceWithTax);
    }

    @Test(dataProvider = "checkoutData")
    public void verifySuccessfulCheckoutProcess(String firstName, String lastName, String postalCode) {
        checkoutPage = cartPage.checkout();
        checkoutPage.submitUserData(firstName, lastName, postalCode);
        checkoutPage.finishShopping();
        Assert.assertTrue(checkoutPage.verifyConfirmationMessageContains("Your order has been dispatched"));
    }

    @Test(dataProvider = "checkoutData")
    public void verifyCheckoutErrorMessages(String firstName, String lastName, String postalCode) {
        checkoutPage = cartPage.checkout();
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
        checkoutPage.sendFirstName(firstName);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
        checkoutPage.sendLastName(lastName);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
        checkoutPage.sendPostalCode(postalCode);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.verifyTitleExists());
    }
}
