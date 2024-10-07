package com.jyotisharma;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderPlacementTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void searchProduct() {
    }

    @Test
    public void addProductToCart() {
    }


    @Test
    public void placeOrder() {
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
