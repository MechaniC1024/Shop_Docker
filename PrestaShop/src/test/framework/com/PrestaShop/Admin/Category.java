package com.PrestaShop.Admin;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class Category{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//input[@name='categoryFilter_name']")
	private WebElement categoryFilterName;
	
	@CacheLookup
	@FindBy(xpath = "//td[@class='pointer' and position() = 3]")
	private List<WebElement> categoryNames;
		
	@CacheLookup
	@FindBy(xpath = "//button[@name='submitFilter']")
	private WebElement searchFilter;

	@CacheLookup
	@FindBy(xpath = "//div[@class='alert alert-success']/button[@class='close']")
	private WebElement closeButtonAlertSuccess;
	
	@CacheLookup
	@FindBy(xpath = "//a[@id = 'page-header-desc-category-new_category']")
	private WebElement addCategories;

	public Category(RemoteWebDriver driver) {		
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}
	
	public NewCategory addNewCategory() {

		addCategories.click();
		return new NewCategory(driver);
	}

	public Category clickOnCloseButtonAlert() {
		
		WebElement parentElement = closeButtonAlertSuccess.findElement(By.xpath("./.."));
		
		String text = parentElement.getText();
		String textCheck = text.substring(1, text.length()).trim();
		Assert.assertEquals("Создано", textCheck);
		closeButtonAlertSuccess.click();
		
		return this;
	}

	public Category setFilterByNameCategory(String nameCategory) {
		
		categoryFilterName.sendKeys(nameCategory);		
		return this;
	}
	
	public Category filterSearch() {
		
		searchFilter.click();		
		return this;
	}
	
	public Category containsCategory(String nameCategory) {
		
		List<String> listNames = new LinkedList<>(); 
		for(WebElement element: categoryNames)
			listNames.add(element.getText());
		
		Assert.assertEquals(true, listNames.contains(nameCategory));		
		return this;
	}
}
