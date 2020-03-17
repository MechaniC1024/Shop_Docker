package com.PrestaShop.test2;

import static com.PrestaShop.DataResources.ProcessingData.*;
import static com.PrestaShop.Report.Report.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.DataResources.ProcessingData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 2.")
@Feature("Проверка титулов страниц сайта.")
public class AddNewCategory extends InitialConfiguration{
	
	private String newCategory = "Jacket";
	
	private UserPage userPage;
	
	private Category category;
	
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
		
	@Step("Переход в раздел категории товаров.")
	@Story("Переход в раздел категории товаров.")
	@Test(dependsOnMethods = "signIn", description = "Переход в раздел категории товаров.")
	public void categorySection() {	
		
		category = 
				userPage.
					selectCatalog().
					goToCategories();
	}
	
	@Step("Создание новой категории.")
	@Story("Создание новой категории.")
	@Test(dependsOnMethods = "categorySection", description = "Добавление новой категории.")
	public void addNewCategory() {	
		
		addAttachmentToReport("Название новой категории.", newCategory);
		
		category.
			addNewCategory().
			setNameAndSaveCategoty(newCategory).
			clickOnCloseButtonAlert();
	}
	
	@Step("Проверка созданной новой категории.")
	@Story("Проверка созданной новой категории.")
	@Test(dependsOnMethods = "addNewCategory", description = "Проверка созданной категории.")
	public void checkingNewCategory() {	
		
		addAttachmentToReport("Проверка новой категории.", newCategory);
		
		category.
			setFilterByNameCategory(newCategory).
			filterSearch().
			containsCategory(newCategory);
	}
	
	@Step("Выход из админ панели сайта.")
	@Story("Выход из админ панели сайта.")
	@Test(dependsOnMethods = "checkingNewCategory", description = "Выход с аккаунта.")
	public void logOut() {		
		
		userPage.
			clickOnImageProfile().
			clickOnLogOut();		
	}
}