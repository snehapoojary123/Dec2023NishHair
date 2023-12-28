package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class SearchPageTest extends BaseTest{
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("To get the title of the page..")
	@Test(priority = 1)
	public void pageTitleTest() {
		
		String actTitle = searchPage.getSearchPageTitle();
		String expectedTitle = com.qa.constants.AppConstants.SEARCH_PAGE_TITLE;
		Assert.assertTrue(actTitle.contains(AppConstants.SEARCH_PAGE_TITLE));
			
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("To get the url of the page..")
	@Test(priority = 2)
	public void searchPageURLTest() {
		String actURL = searchPage.getSearchPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.SEARCH_PAGE_URL_FRACTON_VALUE));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("To verify search icon..")
	@Test(priority = 3)
	public void searchIconExistsTest() {
		Assert.assertTrue(searchPage.isSearchExists());
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Hair Topper"},
			{"Bangs"}
			
		};
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("To verify login to open cart application with valid credentials..")
	@Test(priority = 4,dataProvider = "getProductData")
	public void searchTest(String searchKey) {
		searchPage.searchProduct(searchKey);
		

	}
	
	@DataProvider
	public Object[][] getProductSeriesData() {
		return new Object[][] {
			{"Hair Topper","Bangs With Scalp Hair Topper  –  Silk Base Bangs  –  Nish Hair"},
			{"Hair Topper","Mini Scalp Topper  –  2 x 3 Silk Base Hair Topper | Nish Hair"},
			{"Bangs","Wispy Clip-In Bangs | Nish Hair"},
			{"Bangs","Curtain Clip-In Bangs | Nish Hair"}
			
		};
	}
	
	@Test(priority = 5,dataProvider = "getProductSeriesData")
	public void selectProductTest(String product,String productSeries) {
		searchPage.searchProduct(product);
		if(searchPage.searchPageProductCount()> 0) {
			productInfoPage = searchPage.selectProduct(productSeries);
			String actHeaderValue = productInfoPage.productHeader();
			Assert.assertEquals(actHeaderValue.toLowerCase().replace(" ", ""), productSeries.toLowerCase().replace(" ", ""));
		}
		else {
			System.out.println("No search results found.....");
		}
		
		
		
	}

}
