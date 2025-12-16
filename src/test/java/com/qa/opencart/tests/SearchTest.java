package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class SearchTest extends BaseTest {
	
	@BeforeClass
	public void searchSetUp() {
		accpage = 	loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));	
	}
	
	
	@Description("check search feature test...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Poojitha")
	@Test
	public void SearchTest() {
		
		searchResultsPage = accpage.dosearch("macbook");
		int actResultCount = searchResultsPage.getResultsProductCount();
		Assert.assertEquals(actResultCount, 3);
		
	}

}
