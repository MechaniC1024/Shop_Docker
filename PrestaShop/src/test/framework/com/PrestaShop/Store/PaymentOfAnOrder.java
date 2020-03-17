package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.Wait.Wait.*;

public class PaymentOfAnOrder{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//input[@id='payment-option-1']")
	private WebElement paymentByCheck;

	@CacheLookup
	@FindBy(xpath = "//input[@id='payment-option-2']")
	private WebElement paymentByCard;

	@CacheLookup
	@FindBy(xpath = "//input[@id='conditions_to_approve[terms-and-conditions]']")
	private WebElement termsOfAgreement;

	private By buttonOrder = By.xpath("//div[@id='payment-confirmation']//button");
	
	public PaymentOfAnOrder(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}
	
	public PaymentOfAnOrder paymentMethod(int paymentMethod_One_Or_Two) {

		switch (paymentMethod_One_Or_Two) {
		case 1:
		default: {
			paymentByCheck.click();
			break;
		}
		case 2: {
			paymentByCard.click();
			break;
		}
	  }
		
		return this;
	}

	public PaymentOfAnOrder termsOfAgreement() {

		termsOfAgreement.click();
		return this;
	}

	public ConfirmationOfAnOrder clickOnButtonOrder() {

		waitingForElementToBeClickable(driver, buttonOrder, 20).click();
		return new ConfirmationOfAnOrder(driver);
	}
}
