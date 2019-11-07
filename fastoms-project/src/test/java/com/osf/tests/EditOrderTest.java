//package com.osf.tests;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.osf.model.Order;
//import com.osf.pageObjects.HomePage;
//import com.osf.pageObjects.LoginPage;
//import com.osf.pageObjects.ManageOrdersPage;
//import com.osf.pageObjects.OrderPage;
//import com.osf.pageObjects.ProductInformationTabPage;
//import com.osf.util.BrowserFactory;
//import com.osf.util.PageFactory;
//
//public class EditOrderTest {
//
//	private static final String CHROME_BROWSER = "Chrome";
//	private static final String SANDBOX_URL = "https://osfglobal10-alliance-prtnr-na03-dw.demandware.net/on/demandware.store/Sites-Site/default/ViewApplication-DisplayWelcomePage";
//	private static final String TEST_CUSTOMER = "test customer";
//	private static final int DROPDOWN_OPTION_NEW_INDEX = 2;
//	WebDriver driver;
//
//	LoginPage loginPage;
//	HomePage homePage;
//	ManageOrdersPage manageOrdersPage;
//	OrderPage orderPage;
//	ProductInformationTabPage productInformationTabPage;
//	PageFactory pageFactory;
//	String orderNumber;
//
//	@BeforeMethod
//	public void beforeMethod() {
//		driver = new BrowserFactory().getBrowser(CHROME_BROWSER);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.get(SANDBOX_URL);
//		driver.manage().window().maximize();
//		pageFactory = new PageFactory();
//
//		loginPage = pageFactory.getLoginPage(driver);
//		homePage = pageFactory.getHomePage(driver);
//		manageOrdersPage = pageFactory.getManageOrdersPage(driver);
//		orderPage = pageFactory.getOrderPage(driver);
//		productInformationTabPage = pageFactory.getProductInformationTabPage(driver);
//
//		try (InputStream input = new FileInputStream("properties.properties")) {
//
//			Properties prop = new Properties();
//
//			// load a properties file
//			prop.load(input);
//
//			String user = prop.getProperty("username");
//			String password = prop.getProperty("password");
//
//			// login to application
//			loginPage.login(user, password);
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	@AfterMethod
//	public void afterMethod() {
//		driver.quit();
//	}
//
//	@Test(priority = 0, alwaysRun = true)
//	public void testEditOrder() {
//		// change site and go to Manage Orders
//		homePage.changeSandbox();
//		homePage.expandDropdownMerchantTools();
//		homePage.clickManageOrders();
//
//		// go to Advanced Tab, choose status NEW, click Search and choose first order
//		// it is supposed several orders are created and the first one has status new
//		// and Payment Status = Not Paid
//		// otherwise the tests will fail
//		manageOrdersPage.clickAdvancedSearchTab();
//		manageOrdersPage.selectOrderStatusFromDropdown(DROPDOWN_OPTION_NEW_INDEX);
//		manageOrdersPage.clickOnSearchButton();
//		manageOrdersPage.clickOnFoundOrder();
//
//		Order initialOrder = orderPage.getOrder();
//		// click on Edit Order button, edit customer name and click on Cancel Edit
//		// button
//		orderPage.clickOnEditOrderButton();
//		orderPage.editCustomerName(TEST_CUSTOMER);
//		orderPage.clickOnCancelEditButton();
//
//		Order orderAfterEdit = orderPage.getOrder();
//
//		// verify the customer name was not edited and the same is displayed
//		Assert.assertNotEquals(orderAfterEdit.getCustomerName(), TEST_CUSTOMER);
//		Assert.assertEquals(orderAfterEdit.getCustomerName(), initialOrder.getCustomerName());
//	}
//}