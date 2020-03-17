package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DeliveryAddress{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//p[@class='add-address']/a")
	private WebElement addAddress;	

	@CacheLookup
	@FindBy(xpath = "//input[@name='address1']")
	private WebElement fieldAddress;

	@CacheLookup
	@FindBy(xpath = "//input[@name='postcode']")
	private WebElement fieldPostcode;

	@CacheLookup
	@FindBy(xpath = "//input[@name='city']")
	private WebElement fieldCity;

	@CacheLookup
	@FindBy(xpath = "//div[@id='delivery-address']//button")
	private WebElement buttonContinueAddress;
	
	public DeliveryAddress(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}
	
	public DeliveryAddress addAddress() {

		addAddress.click();
		return this;
	}

	public DeliveryAddress inputAddress(String address) {

		fieldAddress.clear();
		fieldAddress.sendKeys(address);
		return this;
	}

	public DeliveryAddress inputPostcode(String postcode) {
		
		fieldPostcode.clear();
		fieldPostcode.sendKeys(postcode);
		return this;
	}

	public DeliveryAddress inputCity(String city) {

		fieldCity.clear();
		fieldCity.sendKeys(city);
		return this;
	}

	public DeliveryMethod clickOnButtonContinueAddress() {

		buttonContinueAddress.click();
		return new DeliveryMethod(driver);
	}	
}
