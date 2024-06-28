package PageObjectClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToHomePage() {
        driver.get("https://www.demoblaze.com/");
    }

    public void selectCategory(String category) {
        driver.findElement(By.linkText(category)).click();
    }

    public void selectProduct(String productName) {
        driver.findElement(By.linkText(productName)).click();
    }

    public void clickCartButton() {
        driver.findElement(By.id("cartur")).click();
    }
}