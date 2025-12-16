package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.Arrays;
import java.util.List;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Description("checking opencart Acc page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Poojitha")
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accpage.getAccPageTitle(), HOME_PAGE_TITLE) ;
	}
	
	@Description("checking opencart Acc page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Poojitha")
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accpage.getAccPageURL().contains(HOME_PAGE_FRACTION_URL));
	}
	
	@Description("checking opencart Acc page headers...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Poojitha")
	@Test
	public void accPageHeadrsTest() {
		List<String> actHeaderList = accpage.getAccPageHeaders();
		Assert.assertEquals(actHeaderList, AppConstants.expectedAccPageHeadersList);
		}

}
