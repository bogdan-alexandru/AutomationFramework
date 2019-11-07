package com.osf.model;

import java.math.BigDecimal;

public class Product {
	String productName;
	String productId;
	String price;
	String quantity;
	String totalQuantitiesPriceForProduct;
	String color;

	public Product(String productId) {
		this.productId = productId;
	}

	public Product() {
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	String size;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalQuantitiesPriceForProduct() {
		return new BigDecimal(totalQuantitiesPriceForProduct);
	}

	public void setTotalQuantitiesPriceForProduct(String total) {
		this.totalQuantitiesPriceForProduct = total;
	}

	@Override
	public boolean equals(Object obj) {
		Product p = (Product) obj;
		if (this.productId.equals(p.productId)) {
			return true;
		} else {
			return false;
		}
	}
}
