package com.jyotisharma.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class OrderPlacement {

    WebDriver driver;
    Wait<WebDriver> wait;

    public OrderPlacement(WebDriver driver, Wait<WebDriver> fluentWait) {
        this.driver = driver;
        this.wait = fluentWait;
    }

    public List<WebElement> SearchProducts(String item) {
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'search']")));

        search.clear();
        search.sendKeys(item);

        List<WebElement> results = wait.until(driver -> {
            List<WebElement> list = driver.findElements(By.className("MuiPaper-root"));
            return (list.size() > 0 && GetProductName(list.get(0)).toLowerCase().contains(item.toLowerCase())) ? list : null;
        });

        return results;
    }

    public String GetProductName(WebElement item) {
        WebElement name = item.findElement(By.className("css-yg30e6"));
        return name.getText();
    }

    public void AddToCart(WebElement item) {
        WebElement sizeDropdown = item.findElement(By.xpath("//select[@id='uncontrolled-native']"));
        sizeDropdown.click();
        Select dropdown = new Select(item.findElement(By.xpath("//select[@id='uncontrolled-native']")));
        dropdown.selectByIndex(3);
        WebElement addtoCart = item.findElement(By.xpath("//button[text()='Add to cart']"));
        addtoCart.click();
    }

    public String GetCartItem() {
        WebElement cartItem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".css-1gjj37g > div:nth-child(1)")));
        return cartItem.getText();
    }

    public void CheckOut() {
        WebElement checkoutButton = wait.until(driver -> {
            WebElement item = driver.findElement(By.xpath("//button[contains(@class, 'checkout-btn')]"));
            return item.isDisplayed() && item.isEnabled() ? item : null;
        });
        checkoutButton.click();

        WebElement address = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiRadio-root")));
        address.click();

        WebElement placeOrderButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".css-177pwqq")));
        placeOrderButton.click();
    }

    public String GetConfirmOrderText() {
        WebElement orderConfirmation = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".greeting-container > h2")));
        return orderConfirmation.getText();
    }

}
