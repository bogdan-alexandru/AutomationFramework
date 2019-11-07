package com.osf.bmPageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.osf.model.GeneralOrderInformation;
import com.osf.model.Order;
import com.osf.model.Product;
import com.osf.util.BasePage;

public class BMOrderPage extends BasePage {
	WebDriver driver;

	By editOrderButtonSelector = By.cssSelector(".editBtn");
	By saveChangesButtonSelector = By.cssSelector(".saveBtn");
	By cancelOrderButtonSelector = By.cssSelector(".cancelBtn");
	By orderDetailsRowsSelector = By.cssSelector("#general table.table tr");
	By cancelOrderWhitoutRefundChecboxSelector = By.cssSelector("input[name='withoutRefund']");
	By customerNameInputFieldSelector = By.cssSelector("#general input[name='customerName']");
	By statusInputFieldSelector = By.cssSelector("#general select[name='orderStatus']");
	By cancellationDescriptionInputFieldSelector = By.id("cancelDescription");
	By confirmOrderCancellationButtonSelector = By.cssSelector(".cancelOrder");
	By productInformationTabSelector = By.id("product-tab");
	By paymentInformationTabSelector = By.id("payment-tab");
	By shippingDetailsTabSelector = By.id("shipping-tab");
	By confirmOrderSaveButtonSelector = By.id("btnSaveOrder");
	By orderNumberSelector = By.id("orderNumber");
	By backToOrdersButtonSelector = By.id("backToList");
	By productsListSelector = By.cssSelector("#products-table tr");
//	By totalsInformationDiscountInputFieldSelector = By.id("discountValue");
	By totalsInformationProductsPriceInputFieldSelector = By.id("orderProducts");
	By totalsInformationTaxPriceInputFieldSelector = By.id("orderTax");
	By totalsInformationTotalOrderPriceInputFieldSelector = By.id("orderTotal");

	public Order getOrder() {
		Order newOrder = new Order();
		newOrder.setCustomerName(getCustomerNameFromElement());
		newOrder.setStatus(getStatusFromElement());
		newOrder.setOrderNumber(getOrderNumberFromElement());
		return newOrder;
	}

	public BMOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnProductInformationTab() {
		waitForElementClickable(getElement(productInformationTabSelector));
		clickElementWithJS(productInformationTabSelector);
		wait(5);
	}

	public void clickOnPaymentInformationTab() {
		clickElementWithJS(paymentInformationTabSelector);
	}

	public void clickOnShippingDetailsTab() {
		clickElementWithJS(shippingDetailsTabSelector);
	}

	public void clickOnEditOrderButton() {
		clickElementWithJS(editOrderButtonSelector);
		wait(5);
	}

	public void clickOnSaveChangesButton() {
		clickElementWithJS(saveChangesButtonSelector);
	}

	public void clickOnCancelEditButton() {
		clickElementWithJS(editOrderButtonSelector);
		wait(10);
	}

	public void clickOnCancelOrderButton() {
		clickElementWithJS(cancelOrderButtonSelector);
	}

	public void checkCancelOrderWithoutRefundCheckbox() {
		clickElementWithJS(cancelOrderWhitoutRefundChecboxSelector);
	}

	public void setCancellationDescription(String cancellationDescription) {
		typeText(cancellationDescriptionInputFieldSelector, cancellationDescription);
	}

	public void clickOnConfirmOrderCancellation() {
		clickElementWithJS(confirmOrderCancellationButtonSelector);
		wait(5);
	}

	public void clickOnConfirmOrderSave() {
		clickElementWithJS(confirmOrderSaveButtonSelector);
		wait(25);
	}

	private WebElement getCustomerNameRowElement() {
		return getParentOfElement(getParentOfElement(getElement(customerNameInputFieldSelector)));
	}

	private WebElement getStatusRowElement() {
		return getParentOfElement(getParentOfElement(getElement(statusInputFieldSelector)));
	}

	public String getStatusFromElement() {
		return getStatusRowElement().findElements(By.xpath("./*")).get(1).findElement(By.tagName("span")).getText();
	}

	public String getCustomerNameFromElement() {
		return getCustomerNameRowElement().findElements(By.xpath("./*")).get(1).findElement(By.tagName("span"))
				.getText();
	}

	public String getOrderNumberFromElement() {
		return getElement(orderNumberSelector).getText();
	}

	public void editCustomerName(String newCustomerName) {
		WebElement customerNameInputFieldElement = getCustomerNameRowElement().findElements(By.xpath("./*")).get(1)
				.findElement(By.tagName("input"));
		clear(customerNameInputFieldElement);
		typeText(customerNameInputFieldElement, newCustomerName);
	}

	public void clickOnBackToOrdersButton() {
		waitForElementClickable(getElement(backToOrdersButtonSelector));
		scrollToElement(backToOrdersButtonSelector);
		clickElementWithJS(backToOrdersButtonSelector);
	}

	private String getProductNameFromProductElement(WebElement element) {
		return element.findElement(By.cssSelector("div.product-name")).getText();
	}

	private String getProductIdFromProductElement(WebElement element) {
		return element.findElement(By.className("product-id")).getText();
	}

	private String getPriceFromProductElement(WebElement element) {
		String productId = getProductIdFromProductElement(element);
		StringBuilder strB = new StringBuilder();
		strB.append("Base_");
		strB.append(productId);
		return getElement(By.id(strB.toString())).getText();
	}

	private String getQuantityFromProductElement(WebElement element) {
		return getParentOfElement(element.findElement(By.cssSelector("input.quantity-product")))
				.findElement(By.tagName("span")).getText();
	}

	private String getTotalQuantitiesPriceOfProductFromProductElement(WebElement element) {
		String productId = getProductIdFromProductElement(element);
		StringBuilder strB = new StringBuilder();
		strB.append("Total_");
		strB.append(productId);
		return element.findElement(By.id(strB.toString())).getText();
	}

	private String getTotalsInformationDiscountFromElement(By element) {
		return getElement(element).getAttribute("value");
	}

	private String getTotalsInformationProductsPriceFromElement(By element) {
		return getElement(element).getText();
	}

	private String getTotalsInformationTaxPriceFromElement(By element) {
		return getElement(element).getText();
	}

	private String getTotalsInformationTotalOrderPriceFromElement(By element) {
		return getElement(element).getText();
	}

	public List<Product> getProductsListForOrder() {
		List<Product> productsList = new ArrayList<>();
		List<WebElement> productsElementsList = getElements(productsListSelector);
		for (WebElement productElement : productsElementsList) {
			Product product = new Product();

			product.setProductName(getProductNameFromProductElement(productElement));

			String productId = getProductIdFromProductElement(productElement);
			product.setProductId(productId);

			String price = getPriceFromProductElement(productElement);
			product.setPrice(price);

			String quantity = getQuantityFromProductElement(productElement);
			product.setQuantity(quantity);

			String total = getTotalQuantitiesPriceOfProductFromProductElement(productElement);
			product.setTotalQuantitiesPriceForProduct(total);
			productsList.add(product);
		}
		return productsList;
	}

	public GeneralOrderInformation getGeneralInformationForOrder() {
		GeneralOrderInformation orderInformation = new GeneralOrderInformation();

//		orderInformation
//				.setDiscount(getTotalsInformationDiscountFromElement(totalsInformationDiscountInputFieldSelector));
		orderInformation
				.setTaxPrice(getTotalsInformationTaxPriceFromElement(totalsInformationTaxPriceInputFieldSelector));
		orderInformation.setTotalOrderPrice(
				getTotalsInformationTotalOrderPriceFromElement(totalsInformationTotalOrderPriceInputFieldSelector));
		orderInformation.setTotalPriceForProducts(
				getTotalsInformationProductsPriceFromElement(totalsInformationProductsPriceInputFieldSelector));
		return orderInformation;
	}

	public Product getProductById(String id, List<Product> productsList) {
		for (Product product : productsList) {
			if (product.getProductId().equals(id)) {
				return product;
			}
		}
		return null;
	}
}
