package com.NykaaApp.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import com.google.common.collect.ImmutableMap;
import com.NykaaApp.utilities.Screenshots;

import java.net.URL;
import java.time.Duration;

@Listeners(Screenshots.class)
public class Search_Test {

    AndroidDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        try {
            System.out.println(" Setting up Appium driver...");

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("deviceName", "Nothing Phone (3a)");
            caps.setCapability("udid", "001623535000406");
            caps.setCapability("noReset", true);
            caps.setCapability("newCommandTimeout", 300);
            caps.setCapability("appPackage", "com.fsn.nykaa");
            caps.setCapability("appActivity", "com.fsn.nykaa.Nykaa");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Store driver reference for listener to use
            context.setAttribute("driver", driver);

            System.out.println(" Nykaa App launched successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(" Driver setup failed: " + e.getMessage());
        }
    }

    @Test
    public void searchProduct() throws Exception {
        try {
            System.out.println(" Starting search test...");

            // Click search bar
            driver.findElement(By.xpath("//android.widget.TextSwitcher[@resource-id='com.fsn.nykaa:id/ts_search_bar']")).click();
            System.out.println("Clicked on search bar");

            // Wait for EditText
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("android.widget.EditText")
            ));
            searchInput.sendKeys("Plum Serum");
            System.out.println("Entered 'Plum Serum'");

            // Press Enter/Search
            driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
            System.out.println("Pressed Enter (Search)");

            // Optional: wait for results
            Thread.sleep(3000);

            // Simple validation — check if results appear
            boolean resultsVisible = driver.findElements(By.xpath("//android.widget.TextView")).size() > 0;
            Assert.assertTrue(resultsVisible, "No search results found!");

            System.out.println("Search test passed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Search test failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println(" Driver closed successfully.");
        }
    }
}
