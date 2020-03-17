package com.PrestaShop.test1;

import static com.PrestaShop.DataResources.ProcessingData.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.DataResources.ProcessingData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 1.")
@Feature("Вход и выход с админ панель сайта.")
public class LoginAndLogout extends InitialConfiguration{
	
	@Step("Ввод логина и пароля, нажатие кнопки логин.")
	@Story("Ввод логина и пароля, нажатие кнопки логин.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = ProcessingData.class, description = "Вход в админ панель сайта.")
	public void login(String login, String password) {
		
		setURL(getUrlAdminPanel());
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.
				inputLogin(login).
				inputPassword(password).
				clickOnLoginButton();
		}
	
	@Step("Выход из админ панели сайта.")
	@Story("Выход из админ панели сайта.")
	@Test(dependsOnMethods = "login", description = "Выход из админ панели сайта.")
	public void logout() {
		
		UserPage userPage = new UserPage(getDriver());
		
		userPage.
				clickOnImageProfile().
				clickOnLogOut();
		
	}	
}
