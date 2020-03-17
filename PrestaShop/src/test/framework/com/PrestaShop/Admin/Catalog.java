package com.PrestaShop.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.Wait.Wait.*;

public class Catalog{
	
	private RemoteWebDriver driver;
	
	private Actions actions;
	
	private By products = By.xpath("//li[@data-submenu = '10']/a");
	
	private By categories = By.xpath("//li[@data-submenu = '11']/a");
	
	public Catalog(RemoteWebDriver driver, Actions actions) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
		
		this.actions = actions;
	}
		
	public Category goToCategories() {

		WebElement categories = waitingForElementToBeClickable(driver, driver.findElement(this.categories), 20);
		actions.click(categories).perform();
		
		return new Category(driver); 
	}
	
	public Products goToProduct() {
		
		WebElement products = waitingForElementToBeClickable(driver, driver.findElement(this.products), 20);
		actions.click(products).build().perform();
		
		return new Products(driver);		
	}
}
