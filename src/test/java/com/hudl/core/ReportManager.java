package com.hudl.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReport() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
