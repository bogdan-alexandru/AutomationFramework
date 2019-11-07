package com.osf.storefrontPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.osf.util.BasePage;

public class StorefrontHomePage extends BasePage {
	WebDriver driver;
	By accepTrackingConsentButtonSelector = By.cssSelector(".affirm");
	By rejectTrackingConsentButtonSelector = By.cssSelector(".decline");
	By loginButtonSelector = By.cssSelector("div.user .user-message");
	By userButtonSelector = By.cssSelector("div.user");//("myaccount");
	By categorySelector = By.cssSelector("ul.navbar-nav li.nav-item > a");
	By mensCategoryButtonSelector = By.id("mens");
	By mensClothingSubcategorySelector = By.id("mens-clothing");
	By mensClothingSuitsSubcategorySelector = By.id("mens-clothing-suits");
	By subcategorySelector = By.cssSelector("li.dropdown-item > a");
	By minicartIconSelector = By.className("minicart-link");

	public StorefrontHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnMinicartIcon() {
		clickElementWithJS(minicartIconSelector);
	}

	public void clickAcceptTrackingConsentButton() {
		try {
			getElement(accepTrackingConsentButtonSelector);
			clickElementWithJS(accepTrackingConsentButtonSelector);
			wait(5);
		} catch (Exception e) {
		}
	}

	public void clickRejectTrackingConsentButton() {
		clickElementWithJS(accepTrackingConsentButtonSelector);
	}

	public void clickLoginButton() {
		clickElementWithJS(loginButtonSelector);
	}

	public boolean isUserButtonVisible() {
		return verifyElementPresent(userButtonSelector);
	}

	private List<WebElement> getCategoriesElementsList() {
		return getElements(categorySelector);
	}

	private WebElement getCategoryElementByName(String category) {
		List<WebElement> categoriesElements = getCategoriesElementsList();
		return getElementByNameFromList(categoriesElements, category);
	}

	public WebElement hoverCategoryByName(String category) {
		WebElement categoryElement = getCategoryElementByName(category);
		hoverElement(categoryElement);
		return categoryElement;
	}

	public void clickCategoryByName(String category) {
		WebElement categoryElement = getCategoryElementByName(category);
		click(categoryElement);
	}

	private List<WebElement> getSubgategoriesForCategoryElement(WebElement categoryElement) {
		return getElements(getParentOfElement(categoryElement), subcategorySelector);
	}

	private WebElement getSubategoryElementOfCategoryByName(String subCategory, WebElement categoryElement) {
		List<WebElement> subCategoriesElements = getSubgategoriesForCategoryElement(categoryElement);
		return getElementByNameFromList(subCategoriesElements, subCategory);
	}

	public WebElement hoverSubcategoryOfCategoryByName(String subCategory, String category) {
		WebElement categoryElement = hoverCategoryByName(category);
		WebElement subcategoryElement = getSubategoryElementOfCategoryByName(subCategory, categoryElement);
		hoverElement(subcategoryElement);
		return subcategoryElement;
	}

	public void clickSubcategoryOfCategoryByName(String subCategory, String category) {
		WebElement categoryElement = hoverCategoryByName(category);
		WebElement subcategoryElement = getSubategoryElementOfCategoryByName(subCategory, categoryElement);
		click(subcategoryElement);
	}

	public void clickSubSubCategoryByName(String subSubCategory, String subCategory, String category) {
		WebElement subcategoryElement = hoverSubcategoryOfCategoryByName(subCategory, category);
		WebElement subSubCategoryElement = getSubategoryElementOfCategoryByName(subSubCategory, subcategoryElement);
		click(subSubCategoryElement);
	}

	public WebElement hoverSubSubCategoryByName(String subSubCategory, String subCategory, String category) {
		WebElement subcategoryElement = hoverSubcategoryOfCategoryByName(subCategory, category);
		WebElement subSubCategoryElement = getSubategoryElementOfCategoryByName(subSubCategory, subcategoryElement);
		hoverElement(subSubCategoryElement);
		return subSubCategoryElement;
	}

	public void hoverMensCategoryButton() {
		hoverElement(mensCategoryButtonSelector);
	}

	public void clickMensClothingSubcategory() {
		hoverMensCategoryButton();
		click(mensClothingSubcategorySelector);
	}

	public void hoverMensClothingSubcategory() {
		hoverMensCategoryButton();
		hoverElement(mensClothingSubcategorySelector);
	}

	public void clickMensClothingSuitsSubcategory() {
		hoverMensCategoryButton();
		hoverMensClothingSubcategory();
		click(mensClothingSuitsSubcategorySelector);
	}

}
