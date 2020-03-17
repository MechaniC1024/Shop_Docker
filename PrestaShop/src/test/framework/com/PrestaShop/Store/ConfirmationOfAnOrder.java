package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class ConfirmationOfAnOrder{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//h3[@class='h1 card-title']")
	private WebElement titleOrderConfirmed;

	@CacheLookup
	@FindBy(xpath = "//div[@class='col-xs-5 text-sm-right text-xs-left']")
	private WebElement price;

	@CacheLookup
	@FindBy(xpath = "//div[@class='col-xs-2']")
	private WebElement quantity;

	@CacheLookup
	@FindBy(xpath = "//div[@class='col-sm-4 col-xs-9 details']/span")
	private WebElement name;

	@CacheLookup
	@FindBy(xpath = "//section/a")
	private WebElement allProducts;

	public ConfirmationOfAnOrder(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}
	
	public ConfirmationOfAnOrder checkOrderNameProduct(StringBuilder nameProduct) {
		
		Assert.assertEquals(name.getText().substring(0, nameProduct.length()).toUpperCase(), nameProduct.toString().toUpperCase(),
				"Разное название продукта.");
		
		return this;
	}
	
	public ConfirmationOfAnOrder checkOrderPriceOrder(StringBuilder priceProduct) {
		
		Assert.assertEquals(price.getText().substring(0, price.getText().length() - 2).replace(',', '.'), priceProduct.toString(),
				"Разная цена товара.");
		
		return this;
	}
	
	public ConfirmationOfAnOrder checkOrderQuantityProduct(String quantityProduct) {
		
		Assert.assertEquals(quantity.getText(), quantityProduct, "Разное количество товара.");
		
		return this;
	}
	
	public ConfirmationOfAnOrder checkOrderTitle() {
		
		Assert.assertEquals(titleOrderConfirmed.getText().substring(1, titleOrderConfirmed.getText().length()),
				"ВАШ ЗАКАЗ ПОДТВЕРЖДЁН", "Разное сообщение о заказе.");
		
		return this;
	}
	
	public AllProduct selectAllProduct() {

		allProducts.click();
		return new AllProduct(driver);
	}

}