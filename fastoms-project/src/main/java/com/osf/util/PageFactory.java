package com.osf.util;

import org.openqa.selenium.WebDriver;

import com.osf.bmPageObjects.BMHomePage;
import com.osf.bmPageObjects.BMLoginPage;
import com.osf.bmPageObjects.BMManageOrdersPage;
import com.osf.bmPageObjects.BMOrderPage;
import com.osf.bmPageObjects.BMProductInformationTabPage;
import com.osf.storefrontPageObjects.StorefrontCheckoutPage;
import com.osf.storefrontPageObjects.StorefrontCategoryPage;
import com.osf.storefrontPageObjects.StorefrontCartPage;
import com.osf.storefrontPageObjects.StorefrontHomePage;
import com.osf.storefrontPageObjects.StorefrontLoginPage;
import com.osf.storefrontPageObjects.StorefrontProductDetailsPage;

public class PageFactory {
	StorefrontLoginPage storefrontLoginPage;
	StorefrontHomePage storefrontHomePage;
	StorefrontCategoryPage storefrontCategoryPage;
	StorefrontProductDetailsPage storefrontProductDetailsPage;
	StorefrontCheckoutPage storefrontCheckoutPage;
	StorefrontCartPage storefrontCartPage;
	BMLoginPage bmLoginPage;
	BMHomePage bmHomePage;
	BMManageOrdersPage manageOrdersPage;
	BMOrderPage orderPage;
	BMProductInformationTabPage productInformationTabPage;

	public StorefrontCheckoutPage getStorefrontCheckoutPage(WebDriver driver) {
		if (storefrontCheckoutPage == null) {
			storefrontCheckoutPage = new StorefrontCheckoutPage(driver);
		}
		return storefrontCheckoutPage;
	}

	public StorefrontCartPage getStorefrontCartPage(WebDriver driver) {
		if (storefrontCartPage == null) {
			storefrontCartPage = new StorefrontCartPage(driver);
		}
		return storefrontCartPage;
	}

	public StorefrontProductDetailsPage getStorefrontProductDetailsPage(WebDriver driver) {
		if (storefrontProductDetailsPage == null) {
			storefrontProductDetailsPage = new StorefrontProductDetailsPage(driver);
		}
		return storefrontProductDetailsPage;
	}

	public StorefrontHomePage getStorefrontHomePage(WebDriver driver) {
		if (storefrontHomePage == null) {
			storefrontHomePage = new StorefrontHomePage(driver);
		}
		return storefrontHomePage;
	}

	public StorefrontLoginPage getStorefrontLoginPage(WebDriver driver) {
		if (storefrontLoginPage == null) {
			storefrontLoginPage = new StorefrontLoginPage(driver);
		}
		return storefrontLoginPage;
	}

	public StorefrontCategoryPage getStorefrontCategoryPage(WebDriver driver) {
		if (storefrontCategoryPage == null) {
			storefrontCategoryPage = new StorefrontCategoryPage(driver);
		}
		return storefrontCategoryPage;
	}

	public BMLoginPage getBMLoginPage(WebDriver driver) {
		if (bmLoginPage == null) {
			bmLoginPage = new BMLoginPage(driver);
		}
		return bmLoginPage;
	}

	public BMHomePage getBMHomePage(WebDriver driver) {
		if (bmHomePage == null) {
			bmHomePage = new BMHomePage(driver);
		}
		return bmHomePage;
	}

	public BMManageOrdersPage getManageOrdersPage(WebDriver driver) {
		if (manageOrdersPage == null) {
			manageOrdersPage = new BMManageOrdersPage(driver);
		}
		return manageOrdersPage;
	}

	public BMOrderPage getOrderPage(WebDriver driver) {
		if (orderPage == null) {
			orderPage = new BMOrderPage(driver);
		}
		return orderPage;
	}

	public BMProductInformationTabPage getProductInformationTabPage(WebDriver driver) {
		if (productInformationTabPage == null) {
			productInformationTabPage = new BMProductInformationTabPage(driver);
		}
		return productInformationTabPage;
	}
}
