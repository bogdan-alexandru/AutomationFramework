package com.osf.bmPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.osf.util.BasePage;

public class BMHomePage extends BasePage{
	WebDriver driver;
	By siteDropdownSelector = By.className("sod_select");
	By siteValueSelector = By.cssSelector("span[title='FastOMS SFRA']");
	By merchantToolsDropdownSelector = By.className("icon-menu-menu_down_arrow");
	By manageOrdersSelector = By.cssSelector("div[title='Manage Orders']");
	
	public BMHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void clickSitesDropdown() {
		click(siteDropdownSelector);
	}
	
	public void selectASite() {
		click(siteValueSelector);
	}
	
	public void changeSandbox() {
		clickSitesDropdown();
		selectASite();
	}
	
	public void expandDropdownMerchantTools() {
		click(merchantToolsDropdownSelector);
	}
	
	public void clickManageOrders() {
		click(manageOrdersSelector);
	}
}


