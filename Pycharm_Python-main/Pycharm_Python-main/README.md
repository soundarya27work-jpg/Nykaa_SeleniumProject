🛍️ Nykaa Automation Testing using Selenium
This project automates basic user actions on the Nykaa website using Python, Selenium, and PyTest.

It performs:
-> Opening the Nykaa homepage
-> Logging in using mobile number
-> Searching for a product
-> Adding it to the bag
-> Proceeding to checkout

🧰 Tech Stack 🧰
🐍 Python 3.13+ :Core programming language
🌐 Selenium WebDriver :Browser automation
🧪 PyTest	:Test framework
📊 PyTest-HTML :Reporting plugin
🧠 Page Object Model (POM) :Test design pattern
🧩 ChromeDriver	:Browser driver for automation
📸 Screenshots & Logging	:Debugging and evidence collection

▶️ How to Run
python -m venv .venv
.venv\Scripts\activate
pip install -r requirements.txt
python -m tests.test_nykaa
pytest -v --html=reports/nykaa_report.html --self-contained-html
