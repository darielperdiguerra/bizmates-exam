package com.flowkl.manager;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	public static ThreadLocal<WebDriver> driverForThreaddr = new ThreadLocal<WebDriver>();

	public synchronized static WebDriver getDriver() {
		return driverForThreaddr.get();

	}

	public synchronized static void setWebDriver(WebDriver driver) {
		driverForThreaddr.set(driver);
	}

	
}
