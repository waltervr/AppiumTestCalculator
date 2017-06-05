package main;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Calculator {
	DesiredCapabilities capability = new DesiredCapabilities();
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	String URL;

	@SuppressWarnings("rawtypes")
	@BeforeMethod
	@Parameters("Device_ID")
	public void setUp(String Device_ID) throws MalformedURLException {

		if (Device_ID.equalsIgnoreCase("Samsung J3")) {
			setCapabilities(Device_ID);
			driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capability);
		}

		if (Device_ID.equalsIgnoreCase("Galaxy Tab")) {
			setCapabilities(Device_ID);
			driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capability);
		}

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	@Test
	public void testAddition() throws Exception {
		driver.findElement(By.name("2")).click();
		driver.findElement(By.name("+")).click();
		driver.findElement(By.name("4")).click();
		driver.findElement(By.name("=")).click();

		String result = String.valueOf(driver.findElementById("com.sec.android.app.popupcalculator:id/txtCalc").getText().charAt(0));
		assertTrue(result.equals("6"), "Actual value is : " + result + " did not match with expected value: 6");
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	private void setCapabilities(String device) {

		capability.setCapability("platformName", "Android");
		capability.setCapability("deviceName", "Android");
		capability.setCapability("applicationName", device);
		capability.setCapability("noReset", true);

		capability.setCapability("appPackage", "com.sec.android.app.popupcalculator");
		capability.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

	}
}
