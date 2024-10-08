package com.jyotisharma;

import com.jyotisharma.helper.Login;
import com.jyotisharma.helper.Notification;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;

class LoginTest {

    private static WebDriver driver;
    private static Login login;
    private static Notification notification;
    private static Wait<WebDriver> fluentWait;

    @BeforeAll
    public static void Setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        login = new Login(driver);
        notification = new Notification(driver, fluentWait);
    }

    @Test
    public void PerformLoginWithInvalidPassword() {
        login.PerformLogin("jyotisharma1", "INVALID_PASSWORD");
        Assertions.assertEquals("Password is incorrect", notification.GetNotificationMessage());
        Assertions.assertEquals("https://crio-qkart-frontend-qa.vercel.app/login", driver.getCurrentUrl());
    }

    @Test
    public void PerformLogin() {
        login.PerformLogin("jyotisharma1", "7JeE3T@7m5MQhh");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Error in login " + e.getMessage());
        }
        Assertions.assertEquals("Logged in successfully", notification.GetNotificationMessage());
        Assertions.assertEquals("https://crio-qkart-frontend-qa.vercel.app/", driver.getCurrentUrl());
        Assertions.assertEquals("jyotisharma1", login.GetLoggedInUser());
    }

    @AfterAll
    public static void TearDown() {
        driver.quit();
    }

}