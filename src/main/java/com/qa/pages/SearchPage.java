package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.constants.AppConstants;
import com.qa.pages.ProductInfoPage;
import com.qa.utils.ElementUtil;
import com.qa.utils.JavaScriptUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;

	By searchIcon = By.xpath("//a[@title='Search']");
	By searchText = By.xpath("//input[@id='side-panel-search-input']");
	By searchProductResult = By.xpath("//div[@class='product-card-info']");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		
	}
	
	public String getSearchPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, "Nishhair");
		System.out.println("Page title is: "+ title);
		return title;
	}

	public String getSearchPageURL() {
		return eleUtil.getURL();
	}

	public boolean isSearchExists() {
		return eleUtil.doElementIsDisplayed(searchIcon);
	}

	public void searchProduct(String searchKey) {

		if (isSearchExists()) {
			eleUtil.waitForElementPresence(searchIcon, AppConstants.DEFAULT_LONG_TIME_OUT);
			eleUtil.doClickWithActionsAndWait(AppConstants.DEFAULT_MEDIUM_TIME_OUT, searchIcon);
			eleUtil.waitForElementPresence(searchText, AppConstants.DEFAULT_LONG_TIME_OUT);
			eleUtil.doSendKeys(searchText, searchKey);
			eleUtil.enterKey(searchText);

		} else {
			System.out.println("Search field is not present on the page...");

		}

	}
	
	public int searchPageProductCount() {
		 int productCount = eleUtil.waitForElementsVisible(searchProductResult, AppConstants.DEFAULT_LONG_TIME_OUT).size();
		 System.out.println("Product count: "+productCount);
		 return productCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.xpath("//div[@class='product-card-info']/a[@title='"+productName+"']");
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, productLocator);
		jsUtil.clickElementByJS(eleUtil.getElement(productLocator));
		return new ProductInfoPage(driver);
		
		
	}

}
