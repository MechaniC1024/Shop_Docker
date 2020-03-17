package com.PrestaShop.Store;

import static com.PrestaShop.Report.Report.*;
import static com.PrestaShop.InitialConfiguration.InitialConfiguration.*;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

	private RemoteWebDriver driver;

	@CacheLookup
	@FindBy(xpath = "//div[@id = '_mobile_logo']/a")
	private WebElement mobileLogo;

	@CacheLookup
	@FindBy(xpath = "//section[@id = 'content']/section/a")
	private WebElement allProduct;

	public HomePage(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	public HomePage checkingTheVersionOfTheSite() {

		try {

			if (mobileLogo.isDisplayed() == true && driver.getCapabilities().getPlatform() == platform) {
				addAttachmentToReport("Version: ", "Mobile version.");
			} else {
				addAttachmentToReport("Version: ", "Error version.");
			}

		} catch (NoSuchElementException e) {

			if (driver.getCapabilities().getPlatform() != platform) {
				addAttachmentToReport("Version: ", "Desktop version.");
			} else {
				addAttachmentToReport("Version: ", "Mobile version.");
			}
		}

		return this;
	}

	public AllProduct clickOnAllProduct() {

		allProduct.click();
		return new AllProduct(driver);
	}
}
