package com.qa.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;


import com.qa.pages.ProductInfoPage;
import com.qa.pages.SearchPage;
import com.beust.jcommander.Parameter;
import com.qa.factory.DriverFactory;

public class BaseTest {
	
	DriverFactory df;
	protected Properties prop;
	WebDriver driver;
	//String browser = "chrome";
	
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	
	
	protected SoftAssert softAssert;
	
	@Parameters({"browser","browserversion","testcasename"})					
	@BeforeTest
	public void setup(String browserName, String browserVersion, String testCaseName ) {
		df = new DriverFactory();
		prop = df.initializeProperties();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testcasename", testCaseName);
			
		}
		driver = df.initializeDriver(prop);
		searchPage = new SearchPage(driver);
		softAssert = new SoftAssert();
		
		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
