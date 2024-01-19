package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {
    public WebDriver driver;
    private String products_price = "class=>inventory_item_price";
    private String remove_backpack_btn = "id=>remove-sauce-labs-backpack";
    private String backpack_element = "id=>item_4_title_link";
    private String checkout_link = "id=>checkout";

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public double calculateProductsPrice() {
        double sum = 0;
        List<WebElement> prices =  getElementList(products_price, "Price");
        for (WebElement price : prices) {
            String text = price.getText().substring(1);
            sum = sum + Double.parseDouble(text);
        }
        return sum;
    }

    public void removeProduct() {
        elementClick(remove_backpack_btn, "Remove backpack");
    }

    public boolean isProductPresent() {
        return isElementPresent(backpack_element, "Backpack");
    }

    public CheckoutPage checkout() {
        elementClick(checkout_link, "checkout");
        return new CheckoutPage(driver);
    }
}
