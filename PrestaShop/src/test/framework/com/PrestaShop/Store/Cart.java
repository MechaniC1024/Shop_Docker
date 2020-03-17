package com.PrestaShop.Store;

import static com.PrestaShop.Report.Report.addAttachmentToReport;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class Cart{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='product-line-info']/a")
	private WebElement nameProduct;

	@CacheLookup
	@FindBy(xpath = "//div[@class='product-line-grid-body col-md-4 col-xs-8']/div[2]/span")
	private WebElement priceProduct;

	@CacheLookup
	@FindBy(xpath = "//span[@class='label js-subtotal']")
	private WebElement quantityProduct;

	@CacheLookup
	@FindBy(xpath = "//div[@class = 'text-xs-center']/a")
	private WebElement ordering;

	public Cart(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);		
	}
	
	public Cart checkNameProductInCart(StringBuilder name) {
		
		Assert.assertEquals(nameProduct.getText().toUpperCase(), name.toString().toUpperCase(), "Разные названия товара.");
		return this;
	}
	
	public Cart checkPriceProductInCart(StringBuilder price) {
		
		Assert.assertEquals(priceProduct.getText().substring(0, priceProduct.getText().length() - 2).replace(',', '.'), price.toString(),
				"Разная цена на товар.");
		return this;
	}
	
	public Cart checkQuantityProductInCart(String quantity) {
		
		Assert.assertEquals(quantityProduct.getText().substring(0, quantityProduct.getText().length() - 4), quantity,
				"Разное количество товара.");

		return this;
	}
	
	public Cart addPropertyOfProductToReport(StringBuilder nameProduct, StringBuilder priceProduct, StringBuilder quantityAllProduct) {
		
		addAttachmentToReport("Имя товара.", nameProduct);
		addAttachmentToReport("Цена товара.", priceProduct);
		addAttachmentToReport("Количество товара.", quantityAllProduct);
		
		return this;
	}

	public PersonalOrderData clickOnPlaceOrder() {

		ordering.click();
		return new PersonalOrderData(driver);
	}
}
