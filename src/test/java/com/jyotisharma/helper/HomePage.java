package com.jyotisharma.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void NavigateToHome() {
        String url = "https://crio-qkart-frontend-qa.vercel.app";
        if (!driver.getCurrentUrl().equalsIgnoreCase(url)) {
            driver.get(url);
        }
    }

    public Boolean IsSearchvisible() {
        WebElement search = driver.findElement(By.xpath("//input[@name = 'search']"));
        return search.isDisplayed() && search.isEnabled();
    }

    public Boolean IsLoginvisible() {
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
        return loginButton.isDisplayed() && loginButton.isEnabled();
    }

    public Boolean IsRegistervisible() {
        WebElement btn = driver.findElement(By.xpath("//button[text()='Register']"));
        return btn.isDisplayed() && btn.isEnabled();
    }

    public String GetPrivacyPolicyText() {
        WebElement href = driver.findElement(By.xpath("//a[@href='privacy-policy']"));
        return href.getText();
    }

    public String GetTermsOfServiceText() {
        WebElement href = driver.findElement(By.xpath("//a[@href='terms-of-service']"));
        return href.getText();
    }

    public List<WebElement> GetItemsOnHomeScreen() {
        List<WebElement> items = driver.findElements(By.className("MuiGrid-item"));
        return items;
    }

}
