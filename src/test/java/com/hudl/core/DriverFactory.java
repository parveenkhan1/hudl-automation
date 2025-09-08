package com.hudl.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String browser, String driverPath) {
        if (driver.get() != null) {
            return; // already initialized
        }

        if ("chrome".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            // add other options as needed
            driver.set(new ChromeDriver(options));
        } else {
            // default to chrome for now
            System.setProperty("webdriver.chrome.driver", driverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver.set(new ChromeDriver(options));
        }
    }

    public static void quitDriver() {
        WebDriver wd = driver.get();
        if (wd != null) {
            wd.quit();
            driver.remove();
        }
    }
}
