package com.PrestaShop.InitialConfiguration;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.*;
import org.testng.ITestContext;
import org.testng.annotations.*;

import io.qameta.allure.Step;

public class InitialConfiguration {

	public static final Platform platform = Platform.ANDROID;

	private RemoteWebDriver driver;
	static volatile Map<String, List<InstanceDriver.Driver>> mapOfBrowsers;

	private String browserSuite;
	private String browserTest;

	@Step("Браузер на котором выполняются тесты {browser}.")
	@BeforeSuite(description = "Инициализация браузера.")
	@Parameters({ "browser", "threadCount" })
	public void setUp(@Optional("Chrome") String browser, String threadCount) {

		browserSuite = browser;

		DesiredCapabilities cap = Browsers.valueOf(browser.toUpperCase()).create();
		
		cap.setCapability("enableVNC", true);
		cap.setCapability("enableLog", true);
		cap.setCapability("logName", browser + ".log");
		cap.setCapability("enableVideo", true);
		cap.setCapability("videoName", browser + ".mp4");

		InstanceDriver.INSTANCE_DRIVER.addBrowser(browser, Integer.parseInt(threadCount), cap);
		mapOfBrowsers = InstanceDriver.INSTANCE_DRIVER.getDriver();
	}

	@BeforeTest
	public void beforeTest(ITestContext context) {

		browserTest = context.getCurrentXmlTest().getName();
		String browser = context.getCurrentXmlTest().getParameter("browser");

		List<InstanceDriver.Driver> list = mapOfBrowsers.get(browser);

		bre: while (true) {
			cont: for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getFlag() == true) {
					list.get(i).setFlag(false);
					driver = list.get(i).getDriver();
					break bre;
				} else
					continue cont;
			}
		}
	}

	@AfterTest
	public void afterTest(ITestContext context) {

		String afterBrowserTest = context.getCurrentXmlTest().getName();
		String browser = context.getCurrentXmlTest().getParameter("browser");

		List<InstanceDriver.Driver> list = mapOfBrowsers.get(browser);

		if (afterBrowserTest.equals(browserTest)) {

			for (int i = 0; i < list.size(); i++) {
				if (driver == list.get(i).getDriver())
					list.get(i).setFlag(true);
			}
		}
	}

	public void setURL(String url) {

		driver.get(url);
	}

	public RemoteWebDriver getDriver() {

		return driver;
	}

	@AfterSuite
	public void afterSuite() {

		List<InstanceDriver.Driver> list = mapOfBrowsers.get(browserSuite);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).getDriver().quit();
		}
		mapOfBrowsers.remove(browserSuite);
	}
}
