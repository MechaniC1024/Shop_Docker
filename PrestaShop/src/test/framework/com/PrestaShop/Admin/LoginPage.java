package com.PrestaShop.Admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage{

	@CacheLookup
	@FindBy(xpath = "//input[@id = 'email']")
	private WebElement loginField;

	@CacheLookup
	@FindBy(xpath = "//input[@id = 'passwd']")
	private WebElement passwordField;

	@CacheLookup
	@FindBy(xpath = "//button[@name = 'submitLogin']")
	private WebElement buttonLogin;

	public LoginPage(RemoteWebDriver driver) {
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	public LoginPage inputLogin(String login) {

		loginField.clear();
		loginField.sendKeys(login);
		return this;

	}

	public LoginPage inputPassword(String password) {

		passwordField.clear();
		passwordField.sendKeys(password);
		return this;

	}

	public void clickOnLoginButton() {

		buttonLogin.click();
	}
}
