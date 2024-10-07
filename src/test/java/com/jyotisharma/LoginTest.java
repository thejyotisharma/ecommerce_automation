package com.jyotisharma;

import com.jyotisharma.helper.Login;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

class LoginTest {

    private static WebDriver driver;
    private static Login login;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        login = new Login(driver);
    }

    @Test
    public void performLoginWithInvalidPassword() {
        login.PerformLogin("jyotisharma1","INVALID_PASSWORD");
        Assertions.assertEquals("https://crio-qkart-frontend-qa.vercel.app/login", driver.getCurrentUrl());
    }

    @Test
    public void performLogin() {
        login.PerformLogin("jyotisharma1","7JeE3T@7m5MQhh");
        Assertions.assertEquals("https://crio-qkart-frontend-qa.vercel.app/", driver.getCurrentUrl());
        Assertions.assertEquals("jyotisharma1", login.GetLoggedInUser());
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}