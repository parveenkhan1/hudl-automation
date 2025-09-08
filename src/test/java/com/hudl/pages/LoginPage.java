package com.hudl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By emailField = By.id("username");
    private By continueButton = By.xpath("//button[@type='submit' and @name='action']");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit' and @name='action']");
    private By errorMessage = By.cssSelector("div.c-error-message");

    // Actions
    public void enterEmail(String email) {
        WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailElem.clear();
        emailElem.sendKeys(email);
    }

    public void clickContinue() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueBtn);
        continueBtn.click();
    }

    public void enterPassword(String password) {
        WebElement passwordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElem.clear();
        passwordElem.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        loginBtn.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
