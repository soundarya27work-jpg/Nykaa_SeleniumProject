import time
from selenium.webdriver.common.by import By


class LoginPage:
    def __init__(self, driver, helper):
        self.driver = driver
        self.helper = helper
        self.sign_in_button = (By.XPATH, "//button[contains(., 'Sign in') or contains(., 'Login')]")
        self.mobile_email_option = (By.XPATH, "//button[contains(., 'Sign in with Mobile / Email')]")
        self.mobile_field = (By.XPATH, "//input[@type='tel' or @placeholder='Enter Phone Number']")
        self.get_otp_button = (By.CLASS_NAME, "css-15q5a8e")

    def open_homepage(self):
        self.driver.get("https://www.nykaa.com/")
        print("🌐 Opened Nykaa homepage")
        self.helper.take_screenshot("OpenNykaa")

    def login(self):
        self.helper.click(self.sign_in_button, "Click Sign In")
        self.helper.click(self.mobile_email_option, "Select Mobile/Email Option")
        self.helper.type(self.mobile_field, "8925334178", "Enter Mobile Number")
        self.helper.click(self.get_otp_button, "Click Get OTP")
        print("⏳ Waiting 30 seconds for manual OTP entry...")
        time.sleep(30)
        self.helper.take_screenshot("LoginSuccess")