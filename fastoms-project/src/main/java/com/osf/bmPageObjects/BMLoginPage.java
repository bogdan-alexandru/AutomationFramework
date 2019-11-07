package com.osf.bmPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.osf.util.BasePage;

public class BMLoginPage extends BasePage {

	WebDriver driver;

	By userName = By.name("LoginForm_Login");

	By password = By.name("LoginForm_Password");

	By loginButton = By.name("login");

	public BMLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	// Set user name in textbox

	public void setUserName(String strUserName) {
		typeText(userName, strUserName);
	}

	// Set password in password textbox

	public void setPassword(String strPassword) {
		typeText(password, strPassword);
	}

	// Click on login button

	public void clickLogin() {
		click(loginButton);
	}

	public void login(String strUserName, String strPasword) {

		// Fill user name

		this.setUserName(strUserName);

		// Fill password

		this.setPassword(strPasword);

		// Click Login button[]

		this.clickLogin();
	}

}
