package com.PrestaShop.DataResources;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductData {

	private String nameProduct;
	private String quantityProduct;
	private String priceProduct;

	public void generateNameProduct() {

		nameProduct = "Product " + System.currentTimeMillis();

	}

	public void generateQuantityProduct() {

		int quantityProduct = (int) ((Math.random() * 99) + 1);
		this.quantityProduct = quantityProduct + "";

	}

	public void generatePriceProduct() {

		double priceProduct = (Math.random() * 99.9) + 0.1;
		priceProduct = new BigDecimal(priceProduct).setScale(2, RoundingMode.UP).doubleValue();
		this.priceProduct = priceProduct + "";

	}

	public void generateNamePriceProductQuantity() {

		generateNameProduct();
		generateQuantityProduct();
		generatePriceProduct();
	}

	public String getNameProduct() {

		return nameProduct;
	}

	public String getQuantityProduct() {

		return quantityProduct;
	}

	public String getPriceProduct() {

		return priceProduct;
	}

}
