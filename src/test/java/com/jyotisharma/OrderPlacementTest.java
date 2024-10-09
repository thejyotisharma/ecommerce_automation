package com.jyotisharma;

import com.jyotisharma.helper.Login;
import com.jyotisharma.helper.Notification;
import com.jyotisharma.helper.OrderPlacement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderPlacementTest {

    private static WebDriver driver;
    private static Wait<WebDriver> fluentWait;
    private static Login login;
    private static OrderPlacement orderPlacement;
    private static Notification notification;

    @BeforeAll
    public static void Setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        login = new Login(driver, fluentWait);
        login.PerformLogin("jyotisharma1", "7JeE3T@7m5MQhh");
        orderPlacement = new OrderPlacement(driver, fluentWait);
        notification = new Notification(driver, fluentWait);
    }

    @Test
    public void SearchForProductAndCheckout() {
        List<WebElement> items = orderPlacement.SearchProducts("shoes");
        Assertions.assertEquals(2, items.size());
        for (WebElement item : items) {
            String nameOfProduct = orderPlacement.GetProductName(item);
            Assertions.assertEquals(true, nameOfProduct.toLowerCase().contains("shoes"));
        }

        orderPlacement.AddToCart(items.get(0));
        String name = orderPlacement.GetCartItem();
        Assertions.assertEquals(name, orderPlacement.GetProductName(items.get(0)));

        orderPlacement.CheckOut();
        Assertions.assertEquals("Order placed successfully!", notification.GetNotificationMessage());
        Assertions.assertEquals("Yay! It's ordered \uD83D\uDE03", orderPlacement.GetConfirmOrderText());
        Assertions.assertEquals("https://crio-qkart-frontend-qa.vercel.app/thanks", driver.getCurrentUrl());
    }

    @AfterAll
    public static void TearDown() {
        driver.quit();
    }

}
