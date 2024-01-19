package base;

import org.openqa.selenium.WebDriver;

public class BasePage extends CustomDriver{
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
