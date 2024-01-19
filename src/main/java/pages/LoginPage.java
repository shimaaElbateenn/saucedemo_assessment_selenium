package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import utilities.Util;

public class LoginPage extends BasePage {
    public WebDriver driver;
    private String user_name_field = "id=>user-name";
    private String password_field = "id=>password";
    private String login_btn = "id=>login-button";
    private String login_error_msg = "tag=>h3";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage signInWith(String userName, String password) {
        sendData(user_name_field, userName, "user name");
        sendData(password_field, password, "password");
        elementClick(login_btn, "login button");
        return new HomePage(driver);
    }

    public boolean verifyErrorMessage() {
        String actualText = getText(login_error_msg, "login error message");
        return Util.verifyTextContains(actualText, "Epic sadface");
    }

}
