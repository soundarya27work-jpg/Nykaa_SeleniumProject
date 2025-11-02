import time
from selenium.webdriver.common.by import By


class ProductPage:
    def __init__(self, driver, helper):
        self.driver = driver
        self.helper = helper
        self.add_to_bag_button = (By.CSS_SELECTOR, ".btn-text")
        self.wishlist_button = (By.CSS_SELECTOR, ".wishlist_button_text.css-17jex54")
        self.product_selector = (By.CSS_SELECTOR, ".css-xrzmfa")

    def add_to_bag(self):
        self.helper.click(self.add_to_bag_button, "Click Add To Bag")
        self.helper.take_screenshot("ProductAddedToBag")


