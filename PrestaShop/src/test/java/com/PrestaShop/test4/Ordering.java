package com.PrestaShop.test4;

import static com.PrestaShop.DataResources.ProcessingData.*;

import org.testng.annotations.Test;

import com.PrestaShop.DataResources.ProcessingData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.Store.*;

import io.qameta.allure.*;

@Epic("Тестовый набор 4.")
@Feature("Проверка покупки товара.")
public class Ordering extends InitialConfiguration{
	
	private StringBuilder nameProduct = new StringBuilder();
	private StringBuilder priceProduct = new StringBuilder();
	private StringBuilder quantityAllProduct = new StringBuilder();
	
	private String quantityProduct = "1";
	
	private Product product;
	
	private Cart cart;
	
	private PersonalOrderData orderData;
	
	private DeliveryAddress deliveryAddress;
	
	private DeliveryMethod deliveryMethod;
	
	private PaymentOfAnOrder payment;
	
	private ConfirmationOfAnOrder order;

	@Step("Выбор случайного товара из предложенных товаров.")
	@Story("Выбор случайного товара из предложенных товаров.")
	@Test(description = "Выбор случайного товара.")
	public void choiceOfRandomGoods() {
		
		setURL(getUrlSite());
		
		HomePage homePage = new HomePage(getDriver());

		product = 
			homePage.
				checkingTheVersionOfTheSite().
				clickOnAllProduct().
				clickOnRandomProduct();
	}
	
	@Step("Получаем свойства выбранного товара.")
	@Story("Получаем свойства выбранного товара.")
	@Test(dependsOnMethods = "choiceOfRandomGoods", description = "Взятие свойств товара.")
	public void productProperties() {
			
		product.
			getNameProduct(nameProduct).
			getPriceProduct(priceProduct).
			getQuantityProduct(quantityAllProduct).
			addPropertyOfProductToReport(nameProduct, priceProduct, quantityAllProduct);

	}	
	
	@Step("Добавляем выбранный товар в корзину.")
	@Story("Добавляем выбранный товар в корзину.")
	@Test(dependsOnMethods = "productProperties", description = "Добавление товара в корзину.")
	public void addingProductToCart() {
	
		cart =
			product.
				addToCard().
				clearanceOfProduct();
	}
	
	@Step("Проверяем свойства продукта добавленного в корзину.")
	@Story("Проверяем свойства продукта добавленного в корзину.")
	@Test(dependsOnMethods = "addingProductToCart", description = "Проверка свойств продукта.")
	public void checkingPropertiesProduct() {
		
		orderData = 
			cart.
				checkNameProductInCart(nameProduct).
				checkPriceProductInCart(priceProduct).
				checkQuantityProductInCart(quantityProduct).
				addPropertyOfProductToReport(nameProduct, priceProduct, quantityAllProduct).
				clickOnPlaceOrder();
		
	}	
	
	@Step("Личные данные заказчика для оформления товара.")
	@Story("Личные данные заказчика для оформления товара.")
	@Test(dependsOnMethods = "checkingPropertiesProduct", description = "Ввод личных данных заказчика.", dataProvider = "getCustomerData", dataProviderClass = ProcessingData.class)
	public void inputCustomerData(String firstName, String lastName, String email) {
		
		deliveryAddress = 
			orderData.
				inputFirstName(firstName).
				inputLastName(lastName).
				inputEmail(email).
				clickOnButtonContinueInform();
	}		
	
	@Step("Адресс заказчика для доставки товара.")
	@Story("Адресс заказчика для доставки товара.")
	@Test(dependsOnMethods = "inputCustomerData", description = "Ввод адресса доставки товара.", dataProvider = "getCustomerAddress", dataProviderClass = ProcessingData.class)
	public void inputDeliveryAddressData(String address, String postcode, String city) {
		
		deliveryMethod = 
			deliveryAddress.
				addAddress().
				inputAddress(address).
				inputPostcode(postcode).
				inputCity(city).
				clickOnButtonContinueAddress();
	}	
	
	@Step("Доставка товара.")
	@Story("Доставка товара.")
	@Test(dependsOnMethods = "inputDeliveryAddressData", description = "Доставка товара.")
	public void deliveryMethod() {
		payment = 
			deliveryMethod.
				clickOnButtonContinueDelivery();
	}	
	
	@Step("Выбор случайного метода оплаты.")
	@Story("Выбор случайного метода оплаты.")
	@Test(dependsOnMethods = "deliveryMethod", description = "Оплата товара.")
	public void paymentMethod() {
		
		order = 
			payment.
				paymentMethod(getRandomPayment()).			
				termsOfAgreement().			
				clickOnButtonOrder();
	}	
	
	@Step("Проверка свойств товара в заказе.")
	@Story("Проверка свойств товара в заказе.")
	@Test(dependsOnMethods = "paymentMethod", description = "Проверка свойств товара в заказе.")
	public void checkingPropertiesOrder() {
		
		order.
			checkOrderTitle().
			checkOrderQuantityProduct(quantityProduct).
			checkOrderNameProduct(nameProduct).
			checkOrderPriceOrder(priceProduct);
	}	
	
	@Step("Проверка изменения количества товара после выполнения заказа.")
	@Story("Проверка изменения количества товара после выполнения заказа.")
	@Test(dependsOnMethods = "checkingPropertiesOrder", description = "Проверка количества товара после заказа.")
	public void checkingQuantityProduct() {
		
		order.
			selectAllProduct().
			clickOnRandomProductAgain(product.getNumberProduct()).
			checkTheRestOfTheProduct(quantityProduct, quantityAllProduct);
	}
}