package com.nykaa.tests;

import org.testng.annotations.*;
import com.nykaa.base.Base;
import com.nykaa.pages.*;
import com.nykaa.utilities.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.Status;

public class NykaaShoppingTest extends Base {

    @Test
    public void testNykaaShoppingFlow() throws Exception {
        //Create Extent test entry
        test = extent.createTest("Nykaa Shopping Flow", "Automated test for Nykaa end-to-end shopping journey");

        try {
            // --- Initialize Page Objects ---
            LoginPage login = new LoginPage(driver);
            HomePage home = new HomePage(driver);
            WishlistPage wishlist = new WishlistPage(driver);
            ProductPage product = new ProductPage(driver);
            CartPage cart = new CartPage(driver);

            // --- STEP 1: Open Nykaa ---
            login.openNykaa();
            test.log(Status.INFO, "Opened Nykaa homepage")
                .addScreenCaptureFromPath(Screenshots.capture(driver, "OpenNykaa"));

            // --- STEP 2: Login with Mobile ---
            login.loginWithMobile("8925334178");
            test.log(Status.PASS, "Entered mobile number and sent OTP (manual entry)")
                .addScreenCaptureFromPath(Screenshots.capture(driver, "LoginPage"));

            // --- STEP 3: Search Product ---
            home.searchProduct("plum serum");
            test.log(Status.PASS, "Searched for product: plum serum")
                .addScreenCaptureFromPath(Screenshots.capture(driver, "SearchProduct"));

            // --- STEP 4: Add to Wishlist ---
            wishlist.addToWishlist();
            test.log(Status.PASS, "Added product to wishlist")
                .addScreenCaptureFromPath(Screenshots.capture(driver, "AddToWishlist"));

            // --- STEP 5: Select Product ---
            product.selectFirstProduct();
            test.log(Status.PASS, "Selected first product and switched tab")
                .addScreenCaptureFromPath(Screenshots.capture(driver, "SelectProduct"));

            // --- STEP 6: Add to Bag ---
            product.addToBag();
            test.log(Status.PASS, "Added product to shopping bag")
                .addScreenCaptureFromPath(Screenshots.capture(driver, "AddToBag"));

            // --- STEP 7: Open Bag ---
            cart.openBag();
            test.log(Status.PASS, "Opened shopping bag successfully")
                .addScreenCaptureFromPath(Screenshots.capture(driver, "OpenBag"));

            // --- STEP 8: Proceed to Checkout ---
            cart.proceedToCheckout();
            test.log(Status.PASS, "Proceeded to checkout successfully")
                .addScreenCaptureFromPath(Screenshots.capture(driver, "Checkout"));

            test.log(Status.PASS, "Nykaa shopping flow completed successfully!");
        } 
        catch (Exception e) {
            String screenshotPath = Screenshots.capture(driver, "TestFailed");
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage())
                .addScreenCaptureFromPath(screenshotPath);
            throw e; // rethrow to mark test failed
        }
    }
}
