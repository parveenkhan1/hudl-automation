package com.hudl.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    protected Properties config;
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void baseSetUp() throws IOException {
        ReportManager.initReport();
        loadConfig();
        String browser = config.getProperty("browser", "chrome");
        String chromePath = config.getProperty("chrome.driver.path");
        DriverFactory.initDriver(browser, chromePath);
        driver = DriverFactory.getDriver();
        // optional implicit wait - you can remove and prefer explicit waits
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait","10"))));
    }


    @AfterClass(alwaysRun = true)
    public void baseTearDown() {
        DriverFactory.quitDriver();
        ReportManager.flushReport();
    }

    private void loadConfig() throws IOException {
        config = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                config.load(is);
            } else {
                throw new IOException("config.properties not found on classpath (src/test/resources)");
            }
        }
    }
}
