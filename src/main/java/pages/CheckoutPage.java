package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import utilities.Util;

public class CheckoutPage extends BasePage {
    public WebDriver driver;
    private String first_name_field = "id=>first-name";
    private String last_name_field = "id=>last-name";
    private String postal_code_field = "id=>postal-code";
    private String continue_btn = "id=>continue";
    private String total_price_text = "class=>summary_subtotal_label";
    private String finish_btn = "id=>finish";
    private String confirmation_msg = "class=>complete-text";
    private String error_message = "tag=>h3";
    private String page_title = "class=>title";
    private String tax_amount = "class=>summary_tax_label";
    private String total_price_with_tax = "class=>summary_total_label";

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickContinue() {
        elementClick(continue_btn, "Continue button");
    }

    public void sendFirstName(String firstName) {
        sendData(first_name_field, firstName, "first name");
    }

    public void sendLastName(String lastName) {
        sendData(last_name_field, lastName, "last name");
    }

    public void sendPostalCode(String postalCode) {
        sendData(postal_code_field, postalCode, "postal code");
    }

    public void submitUserData(String firstName, String lastName, String postalCode) {
        sendFirstName(firstName);
        sendLastName(lastName);
        sendPostalCode(postalCode);
        clickContinue();
    }

    public String getErrorMessage() {
        System.out.println("****** " + getText(error_message, "error message"));
        return getText(error_message, "error message");
    }

    public double getItemTotalPrice() {
        String text = getText(total_price_text, "total price");
        String total = text.split("\\$")[1];
        return Double.parseDouble(total);
    }

    public double getTaxAmount() {
        String text = getText(tax_amount, "tax amount");
        String tax = text.split("\\$")[1];
        return Double.parseDouble(tax);
    }

    public double getTotalPriceWithTax() {
        String text = getText(total_price_with_tax, "total price with tax");
        String total = text.split("\\$")[1];
        return Double.parseDouble(total);
    }

    public double calculateTotalPriceAndTax() {
        return getItemTotalPrice() + getTaxAmount();
    }

    public void finishShopping() {
        elementClick(finish_btn, "Finish button");
    }

    public boolean verifyConfirmationMessageContains(String expectedMessage) {
        String confirmationMessage = getText(confirmation_msg, "Confirm message");
        return Util.verifyTextContains(confirmationMessage, expectedMessage);
    }

    public boolean verifyTitleExists() {
        return isElementPresent(page_title, "Page title");
    }
}
