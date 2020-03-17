package com.PrestaShop.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.Report.Report.addAttachmentToReport;
import static com.PrestaShop.Wait.Wait.*;

public class CreateProduct{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//input[@id = 'form_step1_name_1']")
	private WebElement fieldNameNewProduct;

	@CacheLookup
	@FindBy(xpath = "//input[@id = 'form_step1_qty_0_shortcut']")
	private WebElement fieldQuantityNewProduct;

	@CacheLookup
	@FindBy(xpath = "//input[@id = 'form_step1_price_shortcut']")
	private WebElement fieldPriceNewProduct;

	@CacheLookup
	@FindBy(xpath = "//div[@class = 'col-lg-5']/div")
	private WebElement activateNewProduct;

	@CacheLookup
	@FindBy(xpath = "//div[contains(@class, 'btn-group')]/button[@type='submit']")
	private WebElement buttonSaveNewProduct;

	private By buttonCloseSuccessfulUpdate = By.xpath("//div[@id = 'growls']//div[@class = 'growl-close']");
	
	public CreateProduct(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	public CreateProduct inputNameNewProduct(String nameNewProduct) {
		
		fieldNameNewProduct.clear();
		fieldNameNewProduct.sendKeys(nameNewProduct);
		return this;
	}

	public CreateProduct inputQuantityNewProduct(String quantityNewProduct) {

		fieldQuantityNewProduct.clear();
		fieldQuantityNewProduct.sendKeys(quantityNewProduct);
		return this;
	}

	public CreateProduct inputPriceNewProduct(String priceNewProduct) {

		fieldPriceNewProduct.clear();
		fieldPriceNewProduct.sendKeys(priceNewProduct);
		return this;
	}

	public CreateProduct clickOnActivateANewProduct() {

		activateNewProduct.click();
		return this;
	}

	public CreateProduct clickOnButtonSaveNewProduct() {

		buttonSaveNewProduct.click();
		return this;
	}

	public CreateProduct closeSuccessfulUpdate() {

		waitingForVisibilityOfElementLocated(driver, buttonCloseSuccessfulUpdate, 20).click();
		waitingForInvisibilityOfElementLocated(driver, buttonCloseSuccessfulUpdate, 20);
		return this;
	}
	
	public CreateProduct addPropertyOfProductToReport(String nameProduct, String priceProduct, String quantityProduct) {
		
		addAttachmentToReport("Название нового продукта.", nameProduct);
		addAttachmentToReport("Цена нового продукта.", priceProduct);
		addAttachmentToReport("Количество нового продукта.", quantityProduct);
		
		return this;
	}
	
}
