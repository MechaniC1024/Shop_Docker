package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.Wait.Wait.*;

public class ProductDesign{
	
	private RemoteWebDriver driver;
	
	private By clearanceOfProduct = By.xpath("//div[@class='cart-content']/a");
	
	public ProductDesign(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}
	
	public Cart clearanceOfProduct() {
		
		waitingForElementToBeClickable(driver, clearanceOfProduct, 20).click();
		return new Cart(driver);
	}

}
