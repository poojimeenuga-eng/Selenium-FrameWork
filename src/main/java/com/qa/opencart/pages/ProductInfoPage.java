package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1 private By Locators
	
		private  final By  productHeader = By.tagName("h1");
		private final By productimages = By.cssSelector("ul.thumbnails img");
		private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
		private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
		
		private Map<String, String> productMap;
		
		
		// 2 public page Constr..
		
		public ProductInfoPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
			
		}
		
		public String getProductHeader() {
			 String header = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();
			System.out.println("product header: " + header);
			return header;
			
		}
		
		public int getProductImagesCount() {
			int imageCount =  eleUtil.waitForAllElementsVisible(productimages,AppConstants.MEDIUM_DEFAULT_TIMEOUT ).size();
			System.out.println("total number of images: " + imageCount);
			return imageCount;
			
		}
		
		public Map<String, String> getProductDetailsMap(){
			//productMap = new HashMap<String, String>();
			productMap = new LinkedHashMap<String, String>();
			//productMap = new TreeMap<String, String>();
			
			productMap.put("productHeader",getProductHeader());
			productMap.put("productimages",String.valueOf(getProductImagesCount()));
			getproductMetaData();
			getproductPriceData();
			System.out.println("Full product details:" + productMap);
			return productMap;
			
		}
		
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
		
		private void getproductMetaData() {
			
			List<WebElement> MetaList = eleUtil.waitForAllElementsVisible(productMetaData, AppConstants.DEFAULT_TIMEOUT);
			for(WebElement e : MetaList) {
			String metaData = 	e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaVal = meta[1].trim();
			productMap.put(metaKey, metaVal);
				
			}
		}
		
//		$2,000.00
//		Ex Tax: $2,000.00
		
		private void getproductPriceData() {
			List<WebElement> priceList = eleUtil.waitForAllElementsVisible(productPriceData, AppConstants.DEFAULT_TIMEOUT);
			String ProductPrice = priceList.get(0).getText();
			String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
			productMap.put("ProductPrice",ProductPrice );
			productMap.put("exTaxPrice", exTaxPrice);
		}
		
		

}
