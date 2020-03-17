package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import static com.PrestaShop.Report.Report.addAttachmentToReport;
import static com.PrestaShop.Wait.Wait.*;

public class Product{
	
	private RemoteWebDriver driver;
	
	private int numberProduct;
	
	@CacheLookup
	@FindBy(xpath = "//h1[@class = 'h1']")
	private WebElement nameProduct;
	
	@CacheLookup
	@FindBy(xpath = "//ol/li[last()]//span")
	private WebElement nameProductTitle;

	@CacheLookup
	@FindBy(xpath = "//span[@itemprop = 'price']")
	private WebElement priceProduct;
	
	@CacheLookup
	@FindBy(xpath = "//a[@href = '#product-details']")
	private WebElement productDetails;

	@CacheLookup
	@FindBy(xpath = "//button[@data-button-action = 'add-to-cart']")
	private WebElement addToCart;
	
	private By quantityProduct = By.xpath("//div[@class = 'product-quantities']/span");
	
	public Product(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}
	
	public Product(RemoteWebDriver driver, int numberProduct) {

		this(driver);
		this.numberProduct = numberProduct;
	}
	
	public int getNumberProduct() {
		
		return numberProduct;
	}

	private void productDetails() {

		productDetails.click();
	}
	
	private WebElement getWebElementQuantityProduct() {

		return waitingForVisibilityOfElementLocated(driver, this.quantityProduct, 20);
	}
	
	private String getTextNameProduct() {
		
		return this.nameProduct.getText();
	}
	
	private String getTextPriceProduct() {
		
		return this.priceProduct.getText().substring(0, priceProduct.getText().length() - 2).replace(',', '.');
	}
	
	private String getTextQuantityProduct() {
		
		productDetails();
		WebElement quantityProductElement = getWebElementQuantityProduct();
		String quantity = quantityProductElement.getText().substring(0, quantityProductElement.getText().length() - 7);
		
		return quantity;
	}
	
	public Product getNameProduct(StringBuilder nameProduct) {
				
		nameProduct.append(getTextNameProduct());
		
		return this;
	}
	
	public Product getPriceProduct(StringBuilder priceProduct) {
		
		priceProduct.append(getTextPriceProduct());
		
		return this;
	}
	
	public Product getQuantityProduct(StringBuilder quantityProduct) {
		
		quantityProduct.append(getTextQuantityProduct());
		
		return this;
	}
	
	public Product addPropertyOfProductToReport(StringBuilder nameProduct, StringBuilder priceProduct, StringBuilder quantityAllProduct) {
		
		addAttachmentToReport("Имя товара.", nameProduct);
		addAttachmentToReport("Цена товара.", priceProduct);
		addAttachmentToReport("Количество товара.", quantityAllProduct);
		
		return this;
	}
	
	public Product addPropertyOfProductToReport(String nameProduct, String priceProduct, String quantityProduct) {
		
		addAttachmentToReport("Название нового продукта на странице магазина.", nameProduct);
		addAttachmentToReport("Цена нового продукта на странице магазина.", priceProduct);
		addAttachmentToReport("Количество нового продукта на странице магазина.", quantityProduct);
		
		return this;
	}
	
	public ProductDesign addToCard() {

		addToCart.click();
		return new ProductDesign(driver);
	}
	
	public Product checkNameProduct(String nameProduct) {
		
		String name = getTextNameProduct();
		Assert.assertEquals(name, nameProduct.toUpperCase(), "Разное название товара.");
		
		return this;
	}
	
	public Product checkPriceProduct(String priceProduct) {
		
		String price = getTextPriceProduct();
		Assert.assertEquals(price, priceProduct, "Разная цена товара.");
		
		return this;
	}	
	
	public Product checkQuantityProduct(String quantityProduct) {
		
		String quantity = getTextQuantityProduct();
		Assert.assertEquals(quantity, quantityProduct, "Разное количество товара.");
		
		return this;
	}
	
	public Product checkTheRestOfTheProduct(String quantityProduct, StringBuilder quantityAllProduct) {
		
		Integer quantity = Integer.parseInt(getTextQuantityProduct());		
		Integer balance = Integer.parseInt(quantityAllProduct.toString()) - Integer.parseInt(quantityProduct);
		
		Assert.assertEquals(quantity, balance, "Количество товаров не изменилось или изменилось более чем на указанное количество.");
		
		return this;
	}
}
