from selenium.webdriver.common.by import By


class SearchPage:
    def __init__(self, driver, helper):
        self.driver = driver
        self.helper = helper
        self.search_box = (By.XPATH, "//input[contains(@placeholder, 'Search')]")
        self.first_product = (By.CSS_SELECTOR, ".css-xrzmfa")

    def search_product(self, product_name):
        self.helper.type(self.search_box, product_name, "Search Product")
        self.helper.press_enter(self.search_box)
        print(f"🔍 Searching for: {product_name}")
        self.helper.take_screenshot("SearchProduct")

    def select_first_product(self):
        self.helper.click(self.first_product, "Select First Product")
        all_tabs = self.driver.window_handles
        self.driver.switch_to.window(all_tabs[-1])
        print("✅ Switched to product tab")
        self.helper.take_screenshot("ProductPageOpened")
