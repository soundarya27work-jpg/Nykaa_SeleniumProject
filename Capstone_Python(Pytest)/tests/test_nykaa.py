import pytest
from base.base_test import BaseTest
from pages.login_page import LoginPage
from pages.search_page import SearchPage
from pages.product_page import ProductPage
from pages.checkout_page import CheckoutPage


class TestNykaa(BaseTest):

    def test_full_flow(self):
        try:
            # Step 0: Setup browser
            self.setup_method()

            # Instantiate page objects
            login_page = LoginPage(self.driver, self.helper)
            search_page = SearchPage(self.driver, self.helper)
            product_page = ProductPage(self.driver, self.helper)
            checkout_page = CheckoutPage(self.driver, self.helper)

            # Step 1: Login
            login_page.open_homepage()
            login_page.login()

            # Step 2: Search Product
            search_page.search_product("Plum Serum")
            search_page.select_first_product()

            # Step 3: Product actions
            product_page.add_to_bag()

            # Step 4: Checkout
            checkout_page.open_bag_and_checkout()

            print(" Test execution completed successfully!")

        except Exception as e:
            print(f" Test failed: {e}")
            self.helper.take_screenshot("TestFailed")
            raise e

        finally:
            # Step 5: Close browser
            self.teardown_method()


if __name__ == "__main__":
    test = TestNykaa()
    test.test_full_flow()