package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NavigationPage;
import utilities.Constants;
import utilities.ExcelUtility;

public class LoginTests extends BaseTest {

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData() {
        Object[][] testData = ExcelUtility.getTestData("valid_login_data");
        return testData;
    }

    @DataProvider(name = "inValidLoginData")
    public Object[][] getInValidLoginData() {
        Object[][] testData = ExcelUtility.getTestData("invalid_login_data");
        return testData;
    }

    @BeforeClass
    public void setUp() {
        navigationPage = new NavigationPage(driver);
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE, "LoginTests");
    }

    @Test(dataProvider = "validLoginData")
    public void validLogin(String useName, String password) {
        homePage = loginPage.signInWith(useName, password);
        Assert.assertTrue(homePage.isUserLoggedIn());
        navigationPage.logout();
    }

    @Test(dataProvider = "inValidLoginData")
    public void inValidLogin(String useName, String password) {
        loginPage.signInWith(useName, password);
        boolean isErrorMsgExists = loginPage.verifyErrorMessage();
        Assert.assertTrue(isErrorMsgExists);
    }
}
