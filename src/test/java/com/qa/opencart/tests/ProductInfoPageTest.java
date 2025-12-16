package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest  extends BaseTest{
	
   @BeforeClass
	
	public void productInfoSetUp() {
	   accpage =  loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
   }
   
   @DataProvider
   public Object[][] getProductTestData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"macbook", "MacBook Air"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	
   
   @Test(dataProvider ="getProductTestData" )
   
   public void ProductHeaderTest(String searchKey, String productName) {
	   searchResultsPage =  accpage.dosearch(searchKey);
	   productInfoPage = searchResultsPage.selectProduct(productName);
	   String actHeader = productInfoPage.getProductHeader();
	   Assert.assertEquals(actHeader, productName);
   }

   @DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"macbook", "MacBook Air", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7}
		};
	}
   
   public Object[][] getProductSheetData() {
	  return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
   }
   
   public Object[][] getProductCSVData() {
		  return CSVUtil.csvData("product");
	   }
   
   
   @Test(dataProvider = "getProductImagesTestData")
   
   public void ProductImagesCountTest(String searchKey, String productName , int imgCount) {
	   searchResultsPage = accpage.dosearch(searchKey);
	   productInfoPage = searchResultsPage.selectProduct(productName);
		int actImageCount = productInfoPage.getProductImagesCount();
	    Assert.assertEquals(actImageCount, imgCount);
   }
   
   @Test
   
   public void productInfoTest() {
	   searchResultsPage = accpage.dosearch("macbook");
	   productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
	   Map<String, String> actualProductDetailsMap= productInfoPage.getProductDetailsMap();
	   
	   SoftAssert softAssert = new SoftAssert();
	   
	   softAssert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
	   softAssert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
	   softAssert.assertEquals(actualProductDetailsMap.get("Reward Points"), "800");
	   softAssert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
	   softAssert.assertEquals(actualProductDetailsMap.get("ProductPrice"), "$2,000.00");
	   softAssert.assertEquals(actualProductDetailsMap.get("exTaxPrice"), "$2,000.00");
	   
	   softAssert.assertAll();
   }
   

}
