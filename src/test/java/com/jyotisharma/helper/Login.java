package com.jyotisharma.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class Login {

    WebDriver driver;
    Wait<WebDriver> wait;
    String url = "https://crio-qkart-frontend-qa.vercel.app/";

    public Login(WebDriver driver, Wait<WebDriver> fluentWait) {
        this.driver = driver;
        this.wait = fluentWait;
    }

    public void PerformLogin(String username, String password) {
        navigateToLoginPage();

        WebElement userNameElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
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

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
        loginButton.click();
    }

    public void Logout() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Logout']")));
        loginButton.click();
    }
}
