package com.jyotisharma;

import com.jyotisharma.helper.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

class HomePageTest {

    private static WebDriver driver;
    private static HomePage home;

    @BeforeAll
    public static void Setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        home = new HomePage(driver);
        home.NavigateToHome();
    }

    @Test
    public void CheckHeaderContent() {
        Assertions.assertEquals(true, home.IsSearchvisible());
        Assertions.assertEquals(true, home.IsLoginvisible());
        Assertions.assertEquals(true, home.IsRegistervisible());
    }

    @Test
    public void CheckBodyContent() {
        Assertions.assertEquals(true, home.GetItemsOnHomeScreen().size() > 0);
    }

    @Test
    public void CheckFooter() {
        Assertions.assertEquals("Privacy policy", home.GetPrivacyPolicyText());
        Assertions.assertEquals("Terms of Service", home.GetTermsOfServiceText());
    }

    @AfterAll
    public static void TearDown() {
        driver.quit();
    }

}