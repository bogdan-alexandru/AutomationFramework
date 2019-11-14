package com.osf.storefrontPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.osf.util.BasePage;

public class StorefrontCartPage extends BasePage {
	WebDriver driver;
	By checkoutButtonSelector = By.cssSelector("a.checkout-btn");

	public StorefrontCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickCheckoutButton() {
		clickElementWithJS(checkoutButtonSelector);
		wait(3);
	}
}
