package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DeliveryMethod{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//form[@id='js-delivery']/button")
	private WebElement buttonContinueDelivery;

	public DeliveryMethod(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);		
	}
	
	public PaymentOfAnOrder clickOnButtonContinueDelivery() {

		buttonContinueDelivery.click();
		return new PaymentOfAnOrder(driver);
	}
}
