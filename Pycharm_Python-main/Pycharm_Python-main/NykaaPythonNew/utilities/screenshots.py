import os
from datetime import datetime

def capture_screenshot(driver, name):
    folder = "reports/screenshots"
    os.makedirs(folder, exist_ok=True)
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    path = os.path.join(folder, f"{name}_{timestamp}.png")
    driver.save_screenshot(path)
    return path
