package com.osf.bmPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.osf.util.BasePage;

public class BMManageOrdersPage extends BasePage {
	WebDriver driver;

	By searchFieldSelector = By.name("simpleSearch");
	By searchButtonSelector = By.cssSelector(".searchBtn");
	By foundOrderNumberSelector = By.cssSelector("th[scope='row']");
	By foundOrderCustomerNameSelector = By.cssSelector("th[scope='row']");
	By foundOrderSelector = By.cssSelector("tr.list-item");
	By advancedSearchTabSelector = By.id("advanced-search-tab");
	By simpleSearchTabSelector = By.id("simple-search-tab");
	By statusDropdownSelector = By.name("status");

	public BMManageOrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnSearchButton() {
		clickElementWithJS(searchButtonSelector);
		wait(5);
	}

	public void searchOrderByOrderNumber(String orderNumber) {
		typeText(searchFieldSelector, orderNumber);
		clickOnSearchButton();
	}

	public void clickOnFoundOrder() {
		clickElementWithJS(foundOrderNumberSelector);
		wait(10);
	}

	public void clickAdvancedSearchTab() {
		scrollToElement(advancedSearchTabSelector);
		clickElementWithJS(advancedSearchTabSelector);
		wait(5);
	}

	public void clickSimpleSearchTab() {
		scrollToElement(simpleSearchTabSelector);
		clickElementWithJS(simpleSearchTabSelector);
	}

	public void selectOrderStatusFromDropdown(String option) {
//		clickStatusDropdown();
		selectOptionFromDropdownByText(statusDropdownSelector, option);
//		selectOptionFromDropdownByIndex(statusDropdownSelector, option);
		wait(5);
	}

	public void clickStatusDropdown() {
		click(statusDropdownSelector);
	}
}
