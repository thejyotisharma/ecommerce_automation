package com.jyotisharma.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserRegistration {

    WebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/register";

    public UserRegistration(WebDriver driver) {
        this.driver = driver;
    }

    public void RegisterUser(String username, String password) {
        RegisterUser(username, password, password);
    }

    public void RegisterUser(String username, String password, String confirmPassword) {
        navigateToRegisterPage();

        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys(Keys.CONTROL + "a");
        userName.sendKeys(Keys.DELETE);
        userName.sendKeys(username);

        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(Keys.CONTROL + "a");
        passwordElement.sendKeys(Keys.DELETE);
        passwordElement.sendKeys(password);

        WebElement confPassword = driver.findElement(By.id("confirmPassword"));
        confPassword.sendKeys(Keys.CONTROL + "a");
        confPassword.sendKeys(Keys.DELETE);
        confPassword.sendKeys(confirmPassword);

        driver.findElement(By.className("button")).click();
    }

    private void navigateToRegisterPage() {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error in redirecting to register page" + e.getMessage());
            }
        }
    }

}
