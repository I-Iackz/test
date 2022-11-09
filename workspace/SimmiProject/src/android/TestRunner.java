package android;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Capabilities;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


/**
 * Technical, practical test:
 * Please write code to open the native calculator app on the Android
 * Write a small test to add 2 numbers and verify the result
 * Create a repository on GitHub with a clear Readme on how to prepare env and run the test
 * 
 * @author dominicj
 *
 */
public class TestRunner {
	
	private static AndroidDriver driver;
	
	private static final String APP_PACKAGE = "com.huawei.calculator";
	private static final String APP_ACTIVITY = "com.huawei.calculator.calculatorhistory.CalculatorHistoryActivity";
	
	public static void run() throws MalformedURLException {
		Capabilities desiredCaps = new DesiredCapabilities();
		((DesiredCapabilities) desiredCaps).setCapability(MobileCapabilityType.DEVICE_NAME, "I-Iackz HS");
		((DesiredCapabilities) desiredCaps).setCapability(MobileCapabilityType.UDID, "URT0220303000040");
		((DesiredCapabilities) desiredCaps).setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
		((DesiredCapabilities) desiredCaps).setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
		((DesiredCapabilities) desiredCaps).setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		((DesiredCapabilities) desiredCaps).setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
		driver = new AndroidDriver(new URL("http:Dr//127.0.0.1:4723/wd/hub"), desiredCaps);
		 
		driver.startActivity(new Activity(APP_PACKAGE, APP_ACTIVITY));
		addTwoNumbers("2", "4");
	}
	
	/**
	 * Since we are starting calculator with possible existing history, first clear the history.
	 * Add the two numbers supplied using the application and verify the result against our own arithmetic.
	 *
	 * @param value1
	 * @param value2
	 */
	public static void addTwoNumbers(String value1, String value2) {
		driver.findElement(By.name("C")).click(); // Clear any existing calculator arithmetic
		driver.findElement(By.name(value1)).click();
		driver.findElement(By.name("+")).click(); // Since the test said to specifically "add" two numbers, we will assume the arithmetic will always be addition
		driver.findElement(By.name(value2)).click();
		driver.findElement(By.name("=")).click();
		String calVal = driver.findElement(By.name("input")).getText();
		int val = Math.addExact(Integer.parseInt(value1), Integer.parseInt(value2));
		Assert.assertEquals("Calculator arithmedtic did not return correct expression", Integer.parseInt(calVal), val);
	}

	public static void main(String[] args) throws MalformedURLException {
		TestRunner.run();
	}

}
