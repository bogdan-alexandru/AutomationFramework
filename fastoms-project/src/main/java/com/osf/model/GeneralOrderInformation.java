package com.osf.model;

import java.math.BigDecimal;

public class GeneralOrderInformation {
	String discount;
	String totalPriceForProducts;
	String shippingPrice;
	String taxPrice;
	String totalOrderPrice;

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getTotalPriceForProducts() {
		return totalPriceForProducts;
	}

	public void setTotalPriceForProducts(String totalPriceForProducts) {
		this.totalPriceForProducts = totalPriceForProducts;
	}

	public String getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(String shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public String getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getTotalOrderPrice() {
		return new BigDecimal(totalOrderPrice);
	}

	public void setTotalOrderPrice(String totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
}
