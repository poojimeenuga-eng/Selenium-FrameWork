package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic 100: design pages for opencart applications")
  public class LoginPageTest extends BaseTest {
	 
	@Description("checking opencart login page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Poojitha")
	@Test (description = "checking the page title")
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		ChainTestListener.log("checking login page title:" + actTitle);
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}
	
	@Description("checking opencart login page url...")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Poojitha")

	@Test  (description = "checking the page url")
	public void loginPageTitleURL() {
		String actURL = loginpage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("check user is able to login with user credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Poojitha")
	
	@Test (description = "login with credentials")
	public void loginTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accpage.getAccPageTitle(), HOME_PAGE_TITLE );
	}
	


}