package com.osf.storefrontPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.osf.util.BasePage;

public class StorefrontCategoryPage extends BasePage {
	WebDriver driver;
	By productSelector = By.cssSelector("div.product-grid div.product-tile");

	public StorefrontCategoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickProductByIndexToOpenPDP(int index) {
		List<WebElement> productsList = getElements(productSelector);
		click(productsList.get(index));
	}
}
