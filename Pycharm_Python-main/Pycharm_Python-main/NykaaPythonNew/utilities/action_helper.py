import os
import time
from datetime import datetime
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains


class ActionHelper:
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 20)

    def click(self, locator, description=""):
        try:
            element = self.wait.until(EC.element_to_be_clickable(locator))
            element.click()
            print(f"✅ {description}: Clicked {locator}")
        except Exception as e:
            print(f"❌ {description} failed: {e}")
            self.take_screenshot(description)

    def type(self, locator, text, description=""):
        try:
            element = self.wait.until(EC.visibility_of_element_located(locator))
            element.clear()
            element.send_keys(text)
            print(f"✅ {description}: Typed '{text}'")
        except Exception as e:
            print(f"❌ {description} failed: {e}")
            self.take_screenshot(description)

    def press_enter(self, locator):
        try:
            element = self.wait.until(EC.visibility_of_element_located(locator))
            element.send_keys(Keys.ENTER)
            print("✅ Pressed Enter")
        except Exception as e:
            print(f"❌ Press Enter failed: {e}")
            self.take_screenshot("PressEnter")

    def wait_for_visible(self, locator):
        return self.wait.until(EC.visibility_of_element_located(locator))

    def hover(self, element):
        try:
            self.driver.execute_script("arguments[0].scrollIntoView(true);", element)
            time.sleep(0.5)
            ActionChains(self.driver).move_to_element(element).pause(0.5).perform()
            print("✅ Hovered over element")
        except Exception as e:
            print(f"⚠️ Hover failed: {e}")
            self.take_screenshot("HoverFailed")

    def take_screenshot(self, name="screenshot"):
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        folder = "screenshots"
        os.makedirs(folder, exist_ok=True)
        path = os.path.join(folder, f"{name}_{timestamp}.png")
        self.driver.save_screenshot(path)
        print(f"📸 Screenshot saved: {path}")
