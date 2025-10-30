/*
import java.net.MalformedURLException;

import java.net.URL;

import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Nykaa_PhoneApp {

	public static void main(String[] args) throws MalformedURLException {

		// TODO Auto-generated method stub

		DesiredCapabilities caps=new DesiredCapabilities();

		caps.setCapability("platformName", "Android");

		caps.setCapability("appium:platformVersion","15");

		caps.setCapability("appium:deviceName","vivo Y56 5G");

		caps.setCapability("appium:udid", "10BD5K2810000LR");

		caps.setCapability("appium:appPackage", "com.vivo.calculator");

		caps.setCapability("appium:appActivity","com.vivo.calculator.Calculator");

		caps.setCapability("appium:automationName", "UiAutomator2");

		  AndroidDriver driver=new AndroidDriver(new URL("https://127.0.0.1:4723/wd/hub"),caps);

		  driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.fsn.nykaa:id/actionbar_icon\"])[1]")).click();

		  driver.findElement(By.id("com.fsn.nykaa:id/ts_search_bar")).sendKeys("kurties");

		  
driver.findElement(By.id("com.vivo.calculator:id/digit_9\r\n")).click();

driver.findElement(By.id("com.vivo.calculator:id/op_add\r\n")).click();

driver.findElement(By.id("com.vivo.calculator:id/digit_2\r\n")).click();

driver.findElement(By.id("com.vivo.calculator:id/eq\r\n")).click();

String total=driver.findElement(By.id("com.vivo.calculator:id/touchlayout\r\n")).getText();

System.out.println("The sum is:"+total);

 
//search id

//com.fsn.nykaa:id/ts_search_bar

//

	}

}

*/
 