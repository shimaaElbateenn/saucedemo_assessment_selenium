package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class NavigationPage extends BasePage {
    public WebDriver driver;
    private String menu_btn = "id=>react-burger-menu-btn";
    private String logout_btn = "id=>logout_sidebar_link";
    private String cart_link = "class=>shopping_cart_link";
    private String reset_app_btn = "id=>reset_sidebar_link";

    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void logout() {
        elementClick(menu_btn, "Menu Button");
        clickWhenReady(getByType(logout_btn), 3);
    }

    public CartPage openCart() {
        elementClick(cart_link, "Cart link");
        return new CartPage(driver);
    }

    public void resetAppState() {
        elementClick(menu_btn, "menu");
        elementClick(reset_app_btn, "Reset app");
    }
}
