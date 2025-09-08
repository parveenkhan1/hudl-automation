package com.hudl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators (update these as per actual Hudl home page)
    private By avatar = By.cssSelector("img[alt='User Avatar'], .hui-globaluseritem__avatar");
    private By navBar = By.cssSelector("nav, .hui-globalnavigation");

    public boolean isAvatarDisplayed() {
        try {
            WebElement avatarElem = wait.until(ExpectedConditions.visibilityOfElementLocated(avatar));
            return avatarElem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNavigationBarDisplayed() {
        try {
            WebElement navElem = wait.until(ExpectedConditions.visibilityOfElementLocated(navBar));
            return navElem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
