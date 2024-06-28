package PageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "country")
    WebElement countryField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "card")
    WebElement cardField;

    @FindBy(id = "month")
    WebElement monthField;

    @FindBy(id = "year")
    WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    WebElement purchaseButton;

    @FindBy(xpath = "//h2[contains(text(), 'Thank you')]")
    WebElement orderConfirmationMessage;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInDetails(String name, String country, String city, String card, String month, String year) {
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(card);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }

    public void clickPurchase() {
        purchaseButton.click();
    }

    public boolean isOrderConfirmed() {
        return orderConfirmationMessage.isDisplayed();
    }
}