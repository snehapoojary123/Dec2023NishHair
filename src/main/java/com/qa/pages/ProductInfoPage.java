package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.constants.AppConstants;
import com.qa.utils.ElementUtil;
import com.qa.utils.JavaScriptUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	
	private By productHeader = By.tagName("h1");
	//private By quantity = By.id("input-quantity");
	private By addToCart = By.id("AddToCart");
	private By cartHeader = By.xpath("(//h4)[1]");
	private By closeCart = By.xpath("//*[@id=\"Cart-Drawer\"]/div/div[1]/div/side-panel-close");
	
	public ProductInfoPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public String productHeader() {
		return eleUtil.doElementGetText(productHeader);

	}
	
		
	public String addToCart() {
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_LONG_TIME_OUT,addToCart);
		jsUtil.clickElementByJS(eleUtil.getElement(addToCart));
		String successMessg = eleUtil.waitForElementVisible(cartHeader, AppConstants.DEFAULT_LONG_TIME_OUT).getText();
		eleUtil.clickWhenReady(AppConstants.DEFAULT_LONG_TIME_OUT,closeCart);
		
		System.out.println("Success Message: "+successMessg);
		return successMessg;
	}
	
	

}
