package com.osf.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.osf.bmPageObjects.BMHomePage;
import com.osf.bmPageObjects.BMLoginPage;
import com.osf.bmPageObjects.BMManageOrdersPage;
import com.osf.bmPageObjects.BMOrderPage;
import com.osf.bmPageObjects.BMProductInformationTabPage;
import com.osf.model.GeneralOrderInformation;
import com.osf.model.Order;
import com.osf.model.Product;
import com.osf.util.BrowserFactory;
import com.osf.util.PageFactory;

public class BMFastOMSTest {

	private static final String CHROME_BROWSER = "Chrome";
	private static final String SANDBOX_URL = "https://osfglobal10-alliance-prtnr-na03-dw.demandware.net/on/demandware.store/Sites-Site/default/ViewApplication-DisplayWelcomePage";
	private static final String TEST_CUSTOMER = "test customer";
	private static final String TEST_CANCELLATION = "test cancellation";
	private static final String PRODUCT_ID = "701642854760M";
	private static final String STATUS_NEW = "NEW";
	private static final String STATUS_CANCELLED = "CANCELLED";
	private static final int DROPDOWN_OPTION_NEW_INDEX = 2;
	WebDriver driver;

	BMLoginPage loginPage;
	BMHomePage homePage;
	BMManageOrdersPage manageOrdersPage;
	BMOrderPage orderPage;
	BMProductInformationTabPage productInformationTabPage;
	PageFactory pageFactory;
	String orderNumber;

	@BeforeTest
	public void beforeTest() {
		driver = new BrowserFactory().getBrowser(CHROME_BROWSER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(SANDBOX_URL);
		driver.manage().window().maximize();
		pageFactory = new PageFactory();

		loginPage = pageFactory.getBMLoginPage(driver);
		homePage = pageFactory.getBMHomePage(driver);
		manageOrdersPage = pageFactory.getManageOrdersPage(driver);
		orderPage = pageFactory.getOrderPage(driver);
		productInformationTabPage = pageFactory.getProductInformationTabPage(driver);

		try (InputStream input = new FileInputStream("properties.properties")) {

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			String user = prop.getProperty("bmUsername");
			String password = prop.getProperty("bmPassword");
			// login to application
			loginPage.login(user, password);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
//		driver.quit();
	}

	@Test(priority = 1, alwaysRun = true)
	public void testEditOrder() {
		// change site and go to Manage Orders
		homePage.changeSandbox();
		homePage.expandDropdownMerchantTools();
		homePage.clickManageOrders();

		// go to Advanced Tab, choose status NEW, click Search and choose first order
		// it is supposed several orders are created and the first one has status new
		// and Payment Status = Not Paid
		// otherwise the tests will fail
		manageOrdersPage.clickAdvancedSearchTab();
//		manageOrdersPage.selectOrderStatusFromDropdown(DROPDOWN_OPTION_NEW_INDEX);
		manageOrdersPage.clickOnSearchButton();
		manageOrdersPage.clickOnFoundOrder();

		Order initialOrder = orderPage.getOrder();
		// click on Edit Order button, edit customer name and click on Cancel Edit
		// button
		orderPage.clickOnEditOrderButton();
		orderPage.editCustomerName(TEST_CUSTOMER);
		orderPage.clickOnCancelEditButton();

		Order orderAfterEdit = orderPage.getOrder();

		// verify the customer name was not edited and the same is displayed
		Assert.assertNotEquals(orderAfterEdit.getCustomerName(), TEST_CUSTOMER);
		Assert.assertEquals(orderAfterEdit.getCustomerName(), initialOrder.getCustomerName());
	}

	@Test(priority = 3, alwaysRun = true)
	public void testCancelOrder() {
		// change site and go to Manage Orders
		homePage.changeSandbox();
		homePage.expandDropdownMerchantTools();
		homePage.clickManageOrders();

		// go to Advanced Tab, choose status NEW, click Search and choose first order
		// it is supposed several orders are created and the first one has status new
		// and Payment Status = Not Paid
		// otherwise the tests will fail

//		manageOrdersPage.clickAdvancedSearchTab();
		manageOrdersPage.selectOrderStatusFromDropdown("New");
//		manageOrdersPage.clickOnSearchButton();

		manageOrdersPage.searchOrderByOrderNumber(orderNumber);
		manageOrdersPage.clickOnFoundOrder();

		// click on Edit Order button, click on Cancel Order Button, check Cancel Order
		// Without Refund, set Cancellation description and confirm Cancellation
		orderPage.clickOnEditOrderButton();
		orderPage.clickOnCancelOrderButton();
		orderPage.checkCancelOrderWithoutRefundCheckbox();
		orderPage.setCancellationDescription(TEST_CANCELLATION);
		orderPage.clickOnConfirmOrderCancellation();

		Order editedOrder = orderPage.getOrder();

		// verify the status is CANCELLED
		Assert.assertEquals(editedOrder.getStatus(), STATUS_CANCELLED);
	}

	@Test(priority = 2, alwaysRun = true)
	public void testAddProduct() {
		// change site and go to Manage Orders
		homePage.changeSandbox();
		homePage.expandDropdownMerchantTools();
		homePage.clickManageOrders();

		// go to Advanced Tab, choose status NEW, click Search and choose first order
		// it is supposed several orders are created and the first one has status new
		// and Payment Status = Not Paid
		// otherwise the tests will fail
		manageOrdersPage.clickAdvancedSearchTab();
//		manageOrdersPage.selectOrderStatusFromDropdown(DROPDOWN_OPTION_NEW_INDEX);
		manageOrdersPage.selectOrderStatusFromDropdown("New");
		manageOrdersPage.clickOnSearchButton();
		manageOrdersPage.clickOnFoundOrder();
//		manageOrdersPage.searchOrderByOrderNumber("00076231");

		Order originalOrder = orderPage.getOrder();
		GeneralOrderInformation originalOrderInformation = orderPage.getGeneralInformationForOrder();

		// click Edit Order and go to Product Information Tab
		orderPage.clickOnEditOrderButton();
		orderPage.clickOnProductInformationTab();

		// add product id and choose suggested product
		productInformationTabPage.enterProductInAddProductSearchField(PRODUCT_ID);
		productInformationTabPage.clickOnSugesstedProduct();

		GeneralOrderInformation updatedOrderInformation = orderPage.getGeneralInformationForOrder();
		List<Product> updatedProductsList = orderPage.getProductsListForOrder();

		BigDecimal originalOrderTotalPrice = originalOrderInformation.getTotalOrderPrice();
		BigDecimal updatedOrderTotalPrice = updatedOrderInformation.getTotalOrderPrice();
		BigDecimal addedProductTotalQuantitiesPrice = orderPage.getProductById(PRODUCT_ID, updatedProductsList)
				.getTotalQuantitiesPriceForProduct();

		// verify product is added to list and total order price after update equals
		// initial total order price plus added product price
		Assert.assertEquals(updatedOrderTotalPrice, originalOrderTotalPrice.add(addedProductTotalQuantitiesPrice));
		Assert.assertTrue(updatedProductsList.contains(new Product(PRODUCT_ID)));

		// click on save changes and confirm save
		orderPage.clickOnSaveChangesButton();
		orderPage.clickOnConfirmOrderSave();

		Order newOrder = orderPage.getOrder();
		orderNumber = newOrder.getOrderNumber();
		// verify new order is displayed with status NEW
		Assert.assertEquals(newOrder.getStatus(), STATUS_NEW);

		// go to Product Information Tab
		orderPage.clickOnProductInformationTab();

		GeneralOrderInformation newOrderInformation = orderPage.getGeneralInformationForOrder();
		List<Product> newOrderProductsList = orderPage.getProductsListForOrder();

		// Verify New Order contains the added product and new order Total Price equals
		// initial order Total Price plus added product price
		Assert.assertTrue(newOrderProductsList.contains(new Product(PRODUCT_ID)));
		Assert.assertEquals(newOrderInformation.getTotalOrderPrice(),
				originalOrderTotalPrice.add(addedProductTotalQuantitiesPrice));

		// go back to order list, go to Simple Search Tab and search the initial edited
		// order by its order number
		orderPage.clickOnBackToOrdersButton();
		manageOrdersPage.clickSimpleSearchTab();
		manageOrdersPage.searchOrderByOrderNumber(originalOrder.getOrderNumber());
		manageOrdersPage.clickOnFoundOrder();

		Order originalOrderEdited = orderPage.getOrder();

		// verify the order has the Status CANCELLED
		Assert.assertEquals(originalOrderEdited.getStatus(), STATUS_CANCELLED);
	}
}