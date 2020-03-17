package com.PrestaShop.test1;

import static com.PrestaShop.DataResources.ProcessingData.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.DataResources.ProcessingData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 1.")
@Feature("Проверка титулов страниц сайта.")
public class Titles extends InitialConfiguration {
	
	private UserPage userPage;
	
	@Step("Ввод логина и пароля, нажатие кнопки логин.")
	@Story("Ввод логина и пароля, нажатие кнопки логин.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = ProcessingData.class, description = "Вход в админ панель.")
	public void signIn(String login, String password) {
		
		setURL(getUrlAdminPanel());
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.
			inputLogin(login).
			inputPassword(password).
			clickOnLoginButton();
		
		userPage = new UserPage(getDriver());
	}
	
	@Step("Проверка титула страницы на вкладке \"Dashboard\".")
	@Story("Проверка титула страницы на вкладке \"Dashboard\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Dashboard\" после обновления страницы.")
	public void checkTitleDashboard() {		
		userPage.clickOnDashboardGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Заказы\".")
	@Story("Проверка титула страницы на вкладке \"Заказы\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Заказы\" после обновления страницы.")
	public void checkTitleOrder() {
		userPage.clickOnOrdersGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Каталог\".")
	@Story("Проверка титула страницы на вкладке \"Каталог\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Каталог\" после обновления страницы.")
	public void checkTitleCatalog() {
		userPage.clickOnCatalogGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Клиенты\".")
	@Story("Проверка титула страницы на вкладке \"Клиенты\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Клиенты\" после обновления страницы.")
	public void checkTitleCustomer() {
		userPage.clickOnCustomerGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Служба поддержки\".")
	@Story("Проверка титула страницы на вкладке \"Служба поддержки\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Служба поддержки\" после обновления страницы.")
	public void checkTitleCustomerService() {
		userPage.clickOnCustomerServiceGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Статистика\".")
	@Story("Проверка титула страницы на вкладке \"Статистика\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Статистика\" после обновления страницы.")
	public void checkTitleStatistics() {
		userPage.clickOnStatisticsGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Modules\".")
	@Story("Проверка титула страницы на вкладке \"Modules\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Modules\" после обновления страницы.")
	public void checkTitleModules() {
		userPage.clickOnModulesGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Design\".")
	@Story("Проверка титула страницы на вкладке \"Design\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Design\" после обновления страницы.")
	public void checkTitleDesign() {
		userPage.clickOnDesignGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Delivery\".")
	@Story("Проверка титула страницы на вкладке \"Delivery\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Delivery\" после обновления страницы.")
	public void checkTitleDelivery() {
		userPage.clickOnDeliveryGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Способ оплаты\".")
	@Story("Проверка титула страницы на вкладке \"Способ оплаты\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Способ оплаты\" после обновления страницы.")
	public void checkTitlePayment() {
		userPage.clickOnPaymentGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"International\".")
	@Story("Проверка титула страницы на вкладке \"International\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"International\" после обновления страницы.")
	public void checkTitleInternational() {
		userPage.clickOnInternationalGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Shop Parameters\".")
	@Story("Проверка титула страницы на вкладке \"Shop Parameters\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Shop Parameters\" после обновления страницы.")
	public void checkTitleShopParameters() {
		userPage.clickOnShopParametersGetTitleAndRefresh();
	}
	
	@Step("Проверка титула страницы на вкладке \"Конфигурация\".")
	@Story("Проверка титула страницы на вкладке \"Конфигурация\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Конфигурация\" после обновления страницы.")
	public void checkTitleAdvancedParameters() {
		userPage.clickOnAdvancedParametersGetTitleAndRefresh();		
	}
	
	@Step("Выход из админ панели сайта.")
	@Story("Выход из админ панели сайта.")
	@Test(dependsOnMethods = "signIn", dependsOnGroups = "title", description = "Выход с аккаунта.")
	public void logOut() {		
		
		userPage.
			clickOnImageProfile().
			clickOnLogOut();		
	}
}
