package com.NykaaApp.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshots implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // ------------------------- Report Setup -------------------------
    private static ExtentReports createInstance() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/reports/NykaaReport_" + timestamp + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setDocumentTitle("Nykaa Test Report");
        reporter.config().setReportName("Nykaa App Automation Execution");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Platform", "Android");
        extent.setSystemInfo("Tester", "Soundarya");
        extent.setSystemInfo("Tool", "Appium + TestNG");
        return extent;
    }

    // ------------------------- Test Start -------------------------
    @Override
    public void onTestStart(ITestResult result) {
        if (extent == null) {
            extent = createInstance();
        }
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    // ------------------------- Test Pass -------------------------
    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "✅ Test Passed");
    }

    // ------------------------- Test Fail (Screenshot Logic) -------------------------
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testThread.get();
        test.log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());

        Object driverObj = result.getTestContext().getAttribute("driver");
        if (driverObj instanceof AndroidDriver) {
            AndroidDriver driver = (AndroidDriver) driverObj;
            try {
                String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
                test.addScreenCaptureFromPath(screenshotPath);
                test.log(Status.INFO, "📸 Screenshot captured at failure");
            } catch (Exception e) {
                test.log(Status.WARNING, "⚠️ Screenshot capture failed: " + e.getMessage());
            }
        }
    }

    // ------------------------- Test Skipped -------------------------
    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "⏭️ Test Skipped: " + result.getThrowable());
    }

    // ------------------------- Flush Report -------------------------
    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    // ------------------------- Screenshot Helper -------------------------
    private String captureScreenshot(AndroidDriver driver, String testName) throws Exception {
        File src = driver.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
        FileUtils.copyFile(src, new File(path));
        return path;
    }
}
