package com.osf.bmPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.osf.util.BasePage;

public class BMProductInformationTabPage extends BasePage{
	WebDriver driver;
	
	By addProductTextFieldSelector = By.id("searchProduct");
	By productSugetionsSelector = By.cssSelector("#search-suggestions>div");
	
	public BMProductInformationTabPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void enterProductInAddProductSearchField(String product) {
		typeText(addProductTextFieldSelector, product);
		wait(6);
	}
	
	public void clickOnSugesstedProduct() {
		clickElementWithJS(productSugetionsSelector);
		wait(10);
	}
}
