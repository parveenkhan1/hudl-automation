package com.hudl.tests;

import com.hudl.core.BaseTest;
import com.hudl.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testHudlLogin() {
        String baseUrl = config.getProperty("base.url", "https://www.hudl.com");
        String email = System.getProperty("HUDL_EMAIL", config.getProperty("default.email"));
        String password = System.getProperty("HUDL_PASSWORD", config.getProperty("default.password"));

        var test = com.hudl.core.ReportManager.createTest("Hudl Login Test");
        test.info("Navigating to login page: " + baseUrl + "/login");
        driver.get(baseUrl + "/login");

        LoginPage loginPage = new LoginPage(driver);

        test.info("Entering email: " + email);
        loginPage.enterEmail(email);
        test.info("Clicking Continue");
        loginPage.clickContinue();
        test.info("Entering password");
        loginPage.enterPassword(password);
        test.info("Clicking Login");
        loginPage.clickLogin();

        if (config.getProperty("default.email", "dummy@example.com").equals(email)) {
            boolean errorDisplayed = loginPage.isErrorMessageDisplayed();
            test.info("Checking for error message (invalid login)");
            if (errorDisplayed) {
                test.pass("Error message displayed as expected for invalid login");
            } else {
                test.fail("Error message NOT displayed for invalid login");
            }
            Assert.assertTrue(errorDisplayed, "Error message should appear for invalid login");
            System.out.println("Dummy login attempted (expected to fail).");
        } else {
            boolean onHome = driver.getCurrentUrl().contains("/home");
            test.info("Checking if redirected to /home after login");
            if (onHome) {
                test.pass("Login successful and user redirected to home page.");
                // Additional UI checks
                com.hudl.pages.HomePage homePage = new com.hudl.pages.HomePage(driver);
                test.info("Checking if avatar is displayed");
                boolean avatarDisplayed = homePage.isAvatarDisplayed();
                if (avatarDisplayed) {
                    test.pass("Avatar is displayed on home page.");
                } else {
                    test.fail("Avatar is NOT displayed on home page.");
                }
                Assert.assertTrue(avatarDisplayed, "Avatar should be displayed on home page");

                test.info("Checking if navigation bar is displayed");
                boolean navBarDisplayed = homePage.isNavigationBarDisplayed();
                if (navBarDisplayed) {
                    test.pass("Navigation bar is displayed on home page.");
                } else {
                    test.fail("Navigation bar is NOT displayed on home page.");
                }
                Assert.assertTrue(navBarDisplayed, "Navigation bar should be displayed on home page");
            } else {
                test.fail("Login did not redirect to home page.");
            }
            Assert.assertTrue(onHome, "User should land on /home after login");
            System.out.println("Login successful and user redirected to home page.");
        }
    }
}
