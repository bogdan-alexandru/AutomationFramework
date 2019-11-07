package com.osf.storefrontPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.osf.util.BasePage;

public class StorefrontLoginPage extends BasePage {

	WebDriver driver;

	By emailSelector = By.id("login-form-email");

	By passwordSelector = By.id("login-form-password");

	By loginButtonSelector = By.cssSelector("form.login button.btn-primary");

	public StorefrontLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	// Set user name in textbox

	public void setEmail(String email) {
		typeText(emailSelector, email);
	}

	// Set password in password textbox

	public void setPassword(String strPassword) {
		typeText(passwordSelector, strPassword);
	}

	// Click on login button

	public void clickLogin() {
		click(loginButtonSelector);
	}

	public void login(String email, String strPasword) {

		// Fill user name

		this.setEmail(email);

		// Fill password

		this.setPassword(strPasword);

		// Click Login button

		this.clickLogin();
		wait(5);
	}

}
