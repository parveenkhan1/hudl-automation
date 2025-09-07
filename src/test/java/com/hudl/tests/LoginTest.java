package com.hudl.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    // Default dummy credentials (safe to commit)
    private final String EMAIL = System.getProperty("HUDL_EMAIL", "dummy@example.com");
    private final String PASSWORD = System.getProperty("HUDL_PASSWORD", "Password123");

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testHudlLogin() {
        // Step 1: Navigate to Hudl login page
        driver.get("https://www.hudl.com/login");

        // Step 2: Enter email/username
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        emailField.sendKeys(EMAIL);

        // Click Continue button
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and @name='action']")));
        continueButton.click();

        // Step 3: Enter password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys(PASSWORD);

        // Click Login button safely
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and @name='action']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        loginButton.click();

        // Step 4: Verify login outcome
        if (EMAIL.equals("dummy@example.com")) {
            // Dummy login: expect login failure
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.c-error-message"))); // adjust selector if necessary
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message should appear for invalid login");
            System.out.println("Dummy login attempted (expected to fail).");
        } else {
            // Real credentials: wait for user avatar to confirm login
            WebElement userAvatar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id='ssr-webnav']/div/div[1]/nav[1]/div[4]/div[3]/div[1]/div[1]/div/div/h5")));
            Assert.assertTrue(userAvatar.isDisplayed(), "User avatar should be visible after login");
            System.out.println("Login successful.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
