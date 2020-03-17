package com.PrestaShop.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait{

	private static WebDriverWait waitFor(RemoteWebDriver driver, int second) {
		return new WebDriverWait(driver, second);
	}

	public static WebElement waitingForElementToBeClickable(RemoteWebDriver driver, By element, int second) {
		return waitFor(driver, second).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static WebElement waitingForElementToBeClickable(RemoteWebDriver driver, WebElement element, int second) {
		return waitFor(driver, second).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static WebElement waitingForVisibilityOfElementLocated(RemoteWebDriver driver, By element, int second) {
		return waitFor(driver, second).until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public static boolean waitingForInvisibilityOfElementLocated(RemoteWebDriver driver, By element, int second) {
		return waitFor(driver, second).until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
}
