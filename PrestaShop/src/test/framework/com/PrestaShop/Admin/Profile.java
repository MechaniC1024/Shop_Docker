package com.PrestaShop.Admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Profile{
	
	@CacheLookup
	@FindBy(xpath = "//a[@id = 'header_logout']")
	private WebElement logOut;

	public Profile(RemoteWebDriver driver) {
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	public void clickOnLogOut() {
		
		logOut.click();
	}

}
