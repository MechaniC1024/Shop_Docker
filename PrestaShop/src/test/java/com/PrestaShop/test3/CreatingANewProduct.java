package com.PrestaShop.test3;

import static com.PrestaShop.DataResources.ProcessingData.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.DataResources.*;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.Store.*;

import io.qameta.allure.*;

@Epic("Тестовый набор 3.")
@Feature("Создание и проверка товара.")
public class CreatingANewProduct extends InitialConfiguration {

	private ProductData productData = new ProductData();

	private UserPage userPage;
	
	private Products product;
	
	private CreateProduct createProduct;
	
	private String nameProduct;
	
	private String priceProduct;
	
	private String quantityProduct;

	@Step("Ввод логина и пароля, нажатие кнопки логин.")
	@Story("Ввод логина и пароля, нажатие кнопки логин.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = ProcessingData.class, description = "Вход в админ панель сайта.")
	public void signIn(String login, String password) {

		setURL(getUrlAdminPanel());
		LoginPage loginPage = new LoginPage(getDriver());

		loginPage.
				inputLogin(login).
				inputPassword(password).
				clickOnLoginButton();

		userPage = new UserPage(getDriver());
		
		productData.generateNamePriceProductQuantity();		
		
		nameProduct = productData.getNameProduct();		
		priceProduct = productData.getPriceProduct();		
		quantityProduct = productData.getQuantityProduct();
	}

	@Step("Переход в раздел товаров.")
	@Story("Переход в раздел товаров.")
	@Test(dependsOnMethods = "signIn", description = "Переход в раздел товаров.")
	public void productSection() {

		product =
				userPage.
					selectCatalog().
					goToProduct();
	}
	
	@Step("Создание нового товара по сгенерированным данным.")
	@Story("Создание нового товара по сгенерированным данным.")
	@Test(dependsOnMethods = "productSection", description = "Создание нового товара.")
	public void newProduct() {
		
		createProduct =
				product.
					clickOnNewProductButton().
					inputNameNewProduct(nameProduct).
					inputQuantityNewProduct(quantityProduct).
					inputPriceNewProduct(priceProduct).
					addPropertyOfProductToReport(nameProduct, priceProduct, quantityProduct);		
	}
	
	@Step("Сохранение и активация товара.")
	@Story("Сохранение и активация товара.")
	@Test(dependsOnMethods = "productSection", description = "Сохранение и активация нового товара.")
	public void productActivation() {

		createProduct.
				clickOnActivateANewProduct().		
				closeSuccessfulUpdate().			
				clickOnButtonSaveNewProduct().
				closeSuccessfulUpdate();

	}
	
	@Step("Выход из админ панели сайта.")
	@Story("Выход из админ панели сайта.")
	@Test(dependsOnMethods = "productActivation", description = "Выход из админ панели сайта.")
	public void logOut() {		
		
		userPage.
			clickOnImageProfile().
			clickOnLogOut();		
	}
	
	@Step("Проверка созданного товара на странице маганзина.")
	@Story("Проверка созданного товара на странице маганзина.")
	@Test(dependsOnMethods = "logOut", description = "Проверка созданного товара.")
	public void checkNewProduct() {

		setURL(getUrlSite());
		HomePage homePage = new HomePage(getDriver());
		
		homePage.
			clickOnAllProduct().
			clickOnProduct(nameProduct).
			checkNameProduct(nameProduct).
			checkPriceProduct(priceProduct).
			checkQuantityProduct(quantityProduct).
			addPropertyOfProductToReport(nameProduct, priceProduct, quantityProduct);

	}
}