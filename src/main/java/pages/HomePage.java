package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
    public WebDriver driver;
    private String logo_locator = "class=>app_logo";
    private String addToCart_btn1 = "id=>add-to-cart-sauce-labs-backpack";
    private String addToCart_btn2 = "id=>add-to-cart-sauce-labs-bike-light";
    private String addToCart_btn3 = "id=>add-to-cart-sauce-labs-bolt-t-shirt";
    private String product_sort_container = "class=>select_container";
    private String product_sort_option = "class=>product_sort_container";
    private String product_title = "class=>inventory_item_name";
    private String product_price = "class=>inventory_item_price";
    private String removeFromCart_btn1 = "id=>remove-sauce-labs-backpack";
    private String shopping_cart_badge = "class=>shopping_cart_badge";

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(logo_locator, "Logo");
    }

    public void addProductsToCart() {
        addBackpackToCart();
        elementClick(addToCart_btn2, "Add Bike Light to Cart");
        elementClick(addToCart_btn3, "Add Shirt to Cart");
    }

    public void addBackpackToCart() {
        elementClick(addToCart_btn1, "Add Backpack to Cart");
    }

    public void selectSortOrder(String option) {
        elementClick(product_sort_container, "Product sort container");
        selectOption(product_sort_option, option, "select option");
    }

    public boolean isProductsTitleSorted(String sortType) {
        List<WebElement> nameList = getElementList(product_title, "products title");
        List<String> names = nameList.stream()
                .map(n -> n.getText())
                .collect(Collectors.toList());
        List<String> sortedNames = names;
        if (sortType == "Ascending") {
            Collections.sort(sortedNames);
        } else if (sortType == "Descending") {
            Collections.sort(sortedNames, Collections.reverseOrder());
        }
        return names.equals(sortedNames);
    }

    public boolean isProductsPricesSorted(String sortType) {
        List<WebElement> priceList =  getElementList(product_price, "Price");
        List<Double> prices = new ArrayList<>();
        for (WebElement price : priceList) {
            String text = price.getText().substring(1);
            prices.add(Double.parseDouble(text));
        }
        List<Double> sortedPrices = prices;
        if (sortType == "Ascending") {
            Collections.sort(sortedPrices);
        } else if (sortType == "Descending") {
            Collections.sort(sortedPrices, Collections.reverseOrder());
        }
        return prices.equals(sortedPrices);
    }

    public boolean isRemoveButtonExists() {
        return isElementPresent(removeFromCart_btn1, "Backpack");
    }

    public void removeProductFromCart() {
        elementClick(removeFromCart_btn1, "Remove Backpack");
    }

    public boolean isShoppingCartBadgeExists() {
        return isElementPresent(shopping_cart_badge, "shopping cart badge");
    }


}
