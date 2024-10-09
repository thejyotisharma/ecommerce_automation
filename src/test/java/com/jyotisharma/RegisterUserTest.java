package com.jyotisharma;

import com.jyotisharma.helper.Login;
import com.jyotisharma.helper.Notification;
import com.jyotisharma.helper.UserRegistration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegisterUserTest {

    private static WebDriver driver;
    private static UserRegistration userRegistration;
    private static Login login;
    private static Notification notification;
    private static Wait<WebDriver> fluentWait;

    @BeforeAll
    public static void Setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        notification = new Notification(driver, fluentWait);

        userRegistration = new UserRegistration(driver);
        login = new Login(driver, fluentWait);
        notification = new Notification(driver, fluentWait);
    }

    @BeforeEach
    void SetUp() throws InterruptedException {
        Thread.sleep(5000);
    }

    @Test
    public void RegisterNewUser() {
        String userName = "test_user_" + new Random().nextInt(10000);
        String password = "secure_password_1";

        userRegistration.RegisterUser(userName, password);

        Assertions.assertEquals("Registered Successfully", notification.GetNotificationMessage());
        login.PerformLogin(userName, password);
        Assertions.assertEquals(userName, login.GetLoggedInUser());
    }

    @Test
    public void ShouldGetErrorIfExistingUser() {
        String userName = "jyotisharma1";
        String password = "secure_password_1";

        userRegistration.RegisterUser(userName, password);
        Assertions.assertEquals("Username already exists", notification.GetNotificationMessage());
    }

    @Test
    public void ShouldGetErrorIfPasswordDontMatch() {
        String userName = "test_user_" + new Random().nextInt(10000);
        String password = "secure_password_1";

        userRegistration.RegisterUser(userName, password, "wrong_confirm_password");
        Assertions.assertEquals("Passwords do not match", notification.GetNotificationMessage());
    }

    @Test
    public void ShouldGetErrorIfUserNameNotEntered() {
        String userName = "";
        String password = "secure_password_1";

        userRegistration.RegisterUser(userName, password, "wrong_confirm_password");
        Assertions.assertEquals("Username is a required field", notification.GetNotificationMessage());
    }

    @Test
    public void ShouldGetErrorIfPasswordNotEntered() {
        String userName = "test_user_123";
        String password = "";

        userRegistration.RegisterUser(userName, password, "wrong_confirm_password");
        Assertions.assertEquals("Password is a required field", notification.GetNotificationMessage());
    }

    @AfterAll
    public static void TearDown() {
        driver.quit();
    }

}