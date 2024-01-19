package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;
import utilities.Constants;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected String baseURL;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected NavigationPage navigationPage;

    @BeforeClass
    public void baseSetUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        baseURL = Constants.BASE_URL;
        driver.get(baseURL);
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void baseTearDown() {
        driver.quit();
    }
}
