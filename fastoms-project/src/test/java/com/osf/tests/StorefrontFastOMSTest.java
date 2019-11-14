package com.osf.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.osf.storefrontPageObjects.StorefrontCheckoutPage;
import com.osf.storefrontPageObjects.StorefrontCategoryPage;
import com.osf.storefrontPageObjects.StorefrontCartPage;
import com.osf.storefrontPageObjects.StorefrontHomePage;
import com.osf.storefrontPageObjects.StorefrontLoginPage;
import com.osf.storefrontPageObjects.StorefrontProductDetailsPage;
import com.osf.util.BrowserFactory;
import com.osf.util.PageFactory;

public class StorefrontFastOMSTest {

	private static final String CHROME_BROWSER = "Chrome";
	private static final String SANDBOX_URL = "https://osfglobal10-alliance-prtnr-na03-dw.demandware.net/s/FastOMS-SFRA";
//	private static final String SANDBOX_URL = "https://osfglobal14-alliance-prtnr-eu10-dw.demandware.net/s/RefArch";
	WebDriver driver;

	StorefrontLoginPage storefrontLoginPage;
	StorefrontHomePage storefrontHomePage;
	StorefrontCategoryPage storefrontCategoryPage;
	StorefrontProductDetailsPage storefrontProductDetailsPage;
	StorefrontCartPage storefrontCartPage;
	StorefrontCheckoutPage storefrontCheckoutPage;
	PageFactory pageFactory;

	@BeforeTest
	public void beforeTest() {
		driver = new BrowserFactory().getBrowser(CHROME_BROWSER);
		Reporter.log("Open browser");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(SANDBOX_URL);
		Reporter.log("Navigate to URL");
		driver.manage().window().maximize();
		Reporter.log("Maximize browser");
		pageFactory = new PageFactory();

		storefrontLoginPage = pageFactory.getStorefrontLoginPage(driver);
		storefrontHomePage = pageFactory.getStorefrontHomePage(driver);
		storefrontCategoryPage = pageFactory.getStorefrontCategoryPage(driver);
		storefrontProductDetailsPage = pageFactory.getStorefrontProductDetailsPage(driver);
		storefrontCartPage = pageFactory.getStorefrontCartPage(driver);
		storefrontCheckoutPage = pageFactory.getStorefrontCheckoutPage(driver);
	}

	@AfterTest
	public void afterTest() {
//		driver.quit();
	}

	@Test(priority = 0, alwaysRun = true)
	public void placeAnOrder() {
		String user = null;
		String password = null;
		try (InputStream input = new FileInputStream("properties.properties")) {

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			user = prop.getProperty("storefrontUsername");// "test@test.com";
			password = prop.getProperty("storefrontPassword");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		storefrontHomePage.clickAcceptTrackingConsentButton();
		Reporter.log("Accept tracking consent");
		storefrontHomePage.clickLoginButton();
		Reporter.log("Go to Login");
		storefrontLoginPage.login(user, password);
		Reporter.log("Enter username and password and click on Login");
		Assert.assertTrue(storefrontHomePage.isUserButtonVisible());
		Reporter.log("Verify that user button is visible");
		storefrontHomePage.clickSubSubCategoryByName("Suits", "Clothing", "Mens");
		Reporter.log("Go to Mens -> Clothing -> Suits");
		storefrontHomePage.clickAcceptTrackingConsentButton();
		Reporter.log("Accept tracking consent");
		storefrontCategoryPage.clickProductByIndexToOpenPDP(0);
		Reporter.log("Open PDP of first product");
		storefrontProductDetailsPage.changeSize("36");
		Reporter.log("Choose size");
		storefrontProductDetailsPage.changeWidth("Short");
		Reporter.log("Choose width");
		storefrontProductDetailsPage.changeQuantity("2");
		Reporter.log("Choose quantity");
		storefrontProductDetailsPage.clickAddToCartButton();
		Reporter.log("Click on Add To Cart button");
		storefrontHomePage.clickOnMinicartIcon();
		Reporter.log("Click on minicart icon in the header");
		storefrontCartPage.clickCheckoutButton();
		Reporter.log("Click on Checkout button in cart");
		storefrontCheckoutPage.enterMandatoryShippingDetalils("first", "last", "address1", "United Kingdom", "city",
				"SW42 4RG", "01222 555 555");
		Reporter.log("Enter mandatory fields for shipping");
		storefrontCheckoutPage.clickContinueToPaymentButton();
		Reporter.log("Submit to continue to Payment page");
		storefrontCheckoutPage.clickOnAddPaymentButtonIfPresent("test@test.com", "41111111111111111", "04", "2020",
				"737", "01222 555 555");
//		storefrontCheckoutPage.enterMandatoryPaymentDetails("test@test.com", "5555555555554444", "04", "2020", "737",
//				"01222 555 555");
		Reporter.log("Enter mandatory fields for payment");
		storefrontCheckoutPage.clickOnContinueToOrderConfirmButton();
		Reporter.log("Submit to continue to order confirmation");
		storefrontCheckoutPage.clickOnPlaceOrderButton();
		Reporter.log("Click on Place Order Button");
	}
}