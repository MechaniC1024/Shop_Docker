package com.PrestaShop.Admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Products{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//a[@id = 'page-header-desc-configuration-add']")
	private WebElement buttonNewProduct;

	public Products(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}
	
	public CreateProduct clickOnNewProductButton() {
	
		buttonNewProduct.click();
		return new CreateProduct(driver);
	}

}
