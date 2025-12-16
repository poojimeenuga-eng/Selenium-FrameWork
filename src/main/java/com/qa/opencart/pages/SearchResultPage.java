package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By resultsproduct  = By.cssSelector("div.product-thumb");
	
	public SearchResultPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}

	
	
	public int getResultsProductCount() {
		 int searchCount = eleUtil.waitForAllElementsVisible(resultsproduct, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("total number of search products:" + searchCount);
		return searchCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product "
				+ "name:" + productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
		
	}

}
