from selenium.webdriver.common.by import By


class CheckoutPage:
    def __init__(self, driver, helper):
        self.driver = driver
        self.helper = helper
        self.bag_icon = (By.ID, "header-bag-icon")
        self.proceed_button = (By.CSS_SELECTOR, ".css-1l90yrs")

    def open_bag_and_checkout(self):
        self.helper.click(self.bag_icon, "Open Bag")
        self.helper.take_screenshot("OpenBag")

        self.helper.click(self.proceed_button, "Proceed To Checkout")
        self.helper.take_screenshot("ProceedToCheckout")
        print(" Checkout process reached")