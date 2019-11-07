package com.osf.storefrontPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.osf.util.BasePage;

public class StorefrontCheckoutPage extends BasePage {
	WebDriver driver;
	By continueToPaymentButtonSelector = By.cssSelector("button.submit-shipping");
	By firstNameInputFieldSelector = By.id("shippingFirstName");
	By lastNameInputFieldSelector = By.id("shippingLastName");
	By address1InputFieldSelector = By.id("shippingAddressOne");
	By countryDropdownSelector = By.id("shippingCountry");
	By cityInputFieldSelector = By.id("shippingAddressCity");
	By postalCodeInputFieldSelector = By.id("shippingZipCode");
	By shippingPhoneNumberInputFieldSelector = By.id("shippingPhoneNumber");
	By emailInputFieldSelector = By.id("email");
	By cardNumberInputFieldSelector = By.id("cardNumber");
	By expirationMonthDropdownSelector = By.id("expirationMonth");
	By expirationYearDropdownSelector = By.id("expirationYear");
	By securityCodeInputFieldSelector = By.id("securityCode");
	By continueToOrderConfirmButtonSelector = By.cssSelector("button.submit-payment");
	By placeOrderButtonSelector = By.cssSelector("button.place-order");
	By addPaymentButtonSelector = By.cssSelector("button.add-payment");
	By paymentPhoneNumberInputFieldSelector = By.id("phoneNumber");

	public StorefrontCheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickContinueToPaymentButton() {
		clickElementWithJS(continueToPaymentButtonSelector);
	}

	public void enterFirstName(String firstName) {
		clear(firstNameInputFieldSelector);
		typeText(firstNameInputFieldSelector, firstName);
	}

	public void enterlastName(String lastName) {
		clear(lastNameInputFieldSelector);
		typeText(lastNameInputFieldSelector, lastName);
	}

	public void enterAddress1(String address1) {
		clear(address1InputFieldSelector);
		typeText(address1InputFieldSelector, address1);
	}

	public void selectCountryFromDropdown(String country) {
		selectOptionFromDropdownByText(countryDropdownSelector, country);
	}

	public void enterCity(String city) {
		clear(cityInputFieldSelector);
		typeText(cityInputFieldSelector, city);
	}

	public void enterPostalCode(String postalCode) {
		clear(postalCodeInputFieldSelector);
		typeText(postalCodeInputFieldSelector, postalCode);
	}

	public void enterShippingPhoneNumber(String phoneNumber) {
		clear(shippingPhoneNumberInputFieldSelector);
		typeText(shippingPhoneNumberInputFieldSelector, phoneNumber);
	}

	public void enterMandatoryShippingDetalils(String firstName, String lastName, String address1, String country,
			String city, String postalCode, String phoneNumber) {
		enterFirstName(firstName);
		enterlastName(lastName);
		enterAddress1(address1);
		selectCountryFromDropdown(country);
		enterCity(city);
		enterPostalCode(postalCode);
		enterShippingPhoneNumber(phoneNumber);
	}

	public void enterEmail(String email) {
		clear(emailInputFieldSelector);
		typeText(emailInputFieldSelector, email);
	}

	public void enterCardNumber(String cardNumber) {
		clear(cardNumberInputFieldSelector);
		typeText(cardNumberInputFieldSelector, cardNumber);
	}

	public void selectExpirationMonthFromDropdown(String month) {
		selectOptionFromDropdownByText(expirationMonthDropdownSelector, month);
	}

	public void selectExpirationYearFromDropdown(String year) {
		selectOptionFromDropdownByText(expirationYearDropdownSelector, year);
	}

	public void enterSecurityCode(String securityCode) {
		clear(securityCodeInputFieldSelector);
		typeText(securityCodeInputFieldSelector, securityCode);
	}

	public void enterPaymentPhoneNumber(String phoneNumber) {
		clear(paymentPhoneNumberInputFieldSelector);
		typeText(paymentPhoneNumberInputFieldSelector, phoneNumber);
	}

	public void enterMandatoryPaymentDetails(String email, String cardNumber, String month, String year,
			String securityCode, String phoneNumber) {
		enterEmail(email);
		enterCardNumber(cardNumber);
		selectExpirationMonthFromDropdown(month);
		selectExpirationYearFromDropdown(year);
		enterSecurityCode(securityCode);
		enterPaymentPhoneNumber(phoneNumber);
	}

	public void clickOnContinueToOrderConfirmButton() {
		clickElementWithJS(continueToOrderConfirmButtonSelector);
	}

	public void clickOnPlaceOrderButton() {
		clickElementWithJS(placeOrderButtonSelector);
	}

	public void clickOnAddPaymentButtonIfPresent(String email, String cardNumber, String month, String year,
			String securityCode, String phoneNumber) {
		try {
			getElement(addPaymentButtonSelector);
			click(addPaymentButtonSelector);
			enterMandatoryPaymentDetails(email, cardNumber, month, year, securityCode, phoneNumber);
		} catch (Exception e) {
			enterMandatoryPaymentDetails(email, cardNumber, month, year, securityCode, phoneNumber);
		}
	}
}
