package com.osf.storefrontPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.osf.util.BasePage;

public class StorefrontProductDetailsPage extends BasePage {
	WebDriver driver;
	By sizeDropdownSelector = By.cssSelector("select.select-size");
	By widthDropdownSelector = By.cssSelector("select.select-width");
	By quantityDropdownSelector = By.cssSelector("select.quantity-select");
	By addToCartButtonSelector = By.cssSelector("button.add-to-cart");

	public StorefrontProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickSizeDropdown() {
		click(sizeDropdownSelector);
	}

	public void clickWidthDropdown() {
		click(sizeDropdownSelector);
	}

	public void clickQuantityDropdown() {
		click(sizeDropdownSelector);
	}

	public void selectSizeFromDropdown(String optionText) {
		selectOptionFromDropdownByText(sizeDropdownSelector, optionText);
	}

	public void selectWidthFromDropdown(String optionText) {
		selectOptionFromDropdownByText(widthDropdownSelector, optionText);
	}

	public void selectQuantityFromDropdown(String optionText) {
		selectOptionFromDropdownByText(quantityDropdownSelector, optionText);
	}

	public void changeSize(String optionText) {
//		clickSizeDropdown();
		selectSizeFromDropdown(optionText);
		wait(5);
	}

	public void changeWidth(String optionText) {
//		clickWidthDropdown();
		selectWidthFromDropdown(optionText);
		wait(5);
	}

	public void changeQuantity(String optionText) {
//		clickQuantityDropdown();
		selectQuantityFromDropdown(optionText);
		wait(5);
	}

	public void clickAddToCartButton() {
		clickElementWithJS(addToCartButtonSelector);
	}
}
