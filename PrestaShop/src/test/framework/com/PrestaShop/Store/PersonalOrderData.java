package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PersonalOrderData{
	
	private RemoteWebDriver driver;

	@CacheLookup
	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement fieldFirstName;

	@CacheLookup
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement fieldLastName;

	@CacheLookup
	@FindBy(xpath = "//div[@id='checkout-guest-form']//input[@name='email']")
	private WebElement fieldEmail;

	@CacheLookup
	@FindBy(xpath = "//form[@id='customer-form']/footer/button")
	private WebElement buttonContinueInform;

	public PersonalOrderData(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	public PersonalOrderData inputFirstName(String firstName) {

		fieldFirstName.clear();
		fieldFirstName.sendKeys(firstName);
		return this;
	}

	public PersonalOrderData inputLastName(String lastName) {

		fieldLastName.clear();
		fieldLastName.sendKeys(lastName);
		return this;
	}

	public PersonalOrderData inputEmail(String email) {

		fieldEmail.clear();
		fieldEmail.sendKeys(email);
		return this;
	}

	public DeliveryAddress clickOnButtonContinueInform() {

		buttonContinueInform.click();
		return new DeliveryAddress(driver);
	}

}
