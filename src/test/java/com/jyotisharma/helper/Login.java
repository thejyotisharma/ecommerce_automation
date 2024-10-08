package com.jyotisharma.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

    WebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/";

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void PerformLogin(String username, String password) {
        navigateToLoginPage();

        WebElement userNameElement = driver.findElement(By.id("username"));
        userNameElement.sendKeys(username);

        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Login to QKart']"));
        submitButton.click();
    }

    public String GetLoggedInUser() {
        WebElement userProfile = driver.findElement(By.className("username-text"));
        return userProfile.getText();
    }

    private void navigateToLoginPage() {
        this.driver.get(this.url);
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
        loginButton.click();
    }

}
