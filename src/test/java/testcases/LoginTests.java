package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTests {
    WebDriver driver;
    String baseURL;

    @BeforeClass
    public void setUpi() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        baseURL = "https://www.saucedemo.com/";
        driver.get(baseURL);
    }

    @Test
    public void dummyTest() {

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
