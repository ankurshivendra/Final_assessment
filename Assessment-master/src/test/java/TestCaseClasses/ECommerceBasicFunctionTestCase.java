package TestCaseClasses;

import PageObjectClasses.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ECommerceBasicFunctionTestCase {
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @DataProvider(name = "productDetails")
    public Object[][] productDetails() {
        return new Object[][] {
                {new String[]{"Sony vaio i5", "Dell i7 8gb"}, "Shivander", "India", "Nodia", "1234567812345678", "12", "2025"}
                // Add more product details if needed
        };
    }

    @Test(dataProvider = "productDetails")
    public void testECommerceFlow(String[] productNames, String name, String country, String city, String card, String month, String year) {
        homePage.goToHomePage();
        homePage.selectCategory("Laptops");

        for (String productName : productNames) {
            homePage.selectProduct(productName);
            productPage.addToCart();
            driver.navigate().back(); // Navigate back to the product listing
            driver.navigate().back(); // Navigate back to the home page
            homePage.selectCategory("Laptops"); // Re-select the category
        }

        homePage.clickCartButton();
        cartPage.proceedToCheckout();
        checkoutPage.fillInDetails(name, country, city, card, month, year);
        checkoutPage.clickPurchase();
        assert checkoutPage.isOrderConfirmed();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
