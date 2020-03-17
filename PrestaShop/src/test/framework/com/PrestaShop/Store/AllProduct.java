package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.Wait.Wait.*;

import java.util.List;

public class AllProduct{

	private RemoteWebDriver driver;
	
	private int randomNumberProduct;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='products row']//div[@class='product-description']//a")
	private List<WebElement> allProducts;
	
	private By next = By.xpath("//a[@rel = 'next']");
	
	AllProduct(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
		randomNumberProduct = (int) (Math.random() * allProducts.size());
	}

	private WebElement clickOnNext() {

		return waitingForElementToBeClickable(driver, next, 20);
	}
	
	public Product clickOnRandomProduct() {
		
		allProducts.get(randomNumberProduct).click();
		
		return new Product(driver, randomNumberProduct);
	}
	
	private WebElement selectProduct(String nameProduct) {

		By selectProduct = By.linkText(nameProduct);
		
		WebElement element = null;

		boolean flag = true;

		while (flag) {
			try {

				element = waitingForElementToBeClickable(driver, selectProduct, 5);
				flag = false;

			} catch (TimeoutException e) {

				clickOnNext().click();

			}
		}
		return element;

	}

	public Product clickOnProduct(String nameProduct) {

		selectProduct(nameProduct).click();
		return new Product(driver);
	}
	
	public Product clickOnProduct(StringBuilder nameProduct) {

		selectProduct(nameProduct.toString()).click();
		return new Product(driver);
	}
	
	public Product clickOnRandomProductAgain(int numberProduct) {

		allProducts.get(numberProduct).click();
		return new Product(driver);
	}
}
