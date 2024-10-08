package com.jyotisharma.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class Notification {
    private WebDriver driver;
    Wait<WebDriver> wait;

    public Notification(WebDriver driver, Wait<WebDriver> fluentWait){
        this.driver = driver;
        this.wait = fluentWait;
    }

    public String GetNotificationMessage(){
        WebElement notification = wait.until(ExpectedConditions.elementToBeClickable(By.id("notistack-snackbar")));
        return notification.getText();
    }
}
