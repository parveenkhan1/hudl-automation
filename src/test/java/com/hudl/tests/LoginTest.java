package com.hudl.tests;

import com.hudl.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    // Credentials from system properties (Maven -D parameters)
    private final String EMAIL = System.getProperty("HUDL_EMAIL", "dummy@example.com");
    private final String PASSWORD = System.getProperty("HUDL_PASSWORD", "Password123");

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize Page Object
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testHudlLogin() {
        // Navigate to login page
        driver.get("https://www.hudl.com/login");

        // Perform login steps
        loginPage.enterEmail(EMAIL);
        loginPage.clickContinue();
        loginPage.enterPassword(PASSWORD);
        loginPage.clickLogin();

        // Verify login outcome
        if (EMAIL.equals("dummy@example.com")) {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should appear for invalid login");
            System.out.println("Dummy login attempted (expected to fail).");
        } else {
            // ✅ New check: URL contains /home after successful login
            Assert.assertTrue(driver.getCurrentUrl().contains("/home"),
                    "User should land on /home after login");

            System.out.println("Login successful and user redirected to home page.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
