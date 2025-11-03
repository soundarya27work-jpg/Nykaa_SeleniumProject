import pytest
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from utilities.action_helper import ActionHelper


@pytest.fixture(scope="function", autouse=True)
def setup(request):
    chrome_options = Options()
    chrome_options.add_argument("--start-maximized")
    chrome_options.add_argument("--disable-notifications")

    driver = webdriver.Chrome(options=chrome_options)
    helper = ActionHelper(driver)

    request.cls.driver = driver
    request.cls.helper = helper

    yield
    driver.quit()