package com.qa.test;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;

public class ProductInfoTest extends BaseTest {
	
	@DataProvider
	public Object[][] getProductSeriesData() {
		return new Object[][] {
			{"Hair Topper","Bangs With Scalp Hair Topper  –  Silk Base Bangs  –  Nish Hair"},
			{"Hair Topper","Mini Scalp Topper  –  2 x 3 Silk Base Hair Topper | Nish Hair"},
			{"Bangs","Wispy Clip-In Bangs | Nish Hair"},
			{"Bangs","Curtain Clip-In Bangs | Nish Hair"}
			
		};
	}
	
	@Test(dataProvider = "getProductSeriesData")
	public void addToCartTest(String productKey, String ProductName) {
		searchPage.searchProduct(productKey);
		productInfoPage = searchPage.selectProduct(ProductName);
		String actSuccMsg = productInfoPage.addToCart();
		
		softAssert.assertEquals(actSuccMsg, "CART");
		softAssert.assertAll();
		
	}

}
