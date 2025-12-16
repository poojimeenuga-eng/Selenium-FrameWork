package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void registerSetup() {
		registerPage = loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getuserRegisterTestDta(){
		return new Object[][] {
			{"Tom","Tom","7896542369","tom@125","yes"},
			{"Jerry","Jerry","8245369742","fuck@111","yes"},
		};
	
	}
	
	public Object[][] getUserRegData() {
	Object regData[][] = 	ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	return regData;
	}
	
	
	
	@Test(dataProvider = "getuserRegisterTestDta")
	public void userRegistersTest(String firstName , String LastName,  String Telephone,
			String password ,
			String Subscribe)  {
		Assert.assertTrue
		(registerPage.
				userRegisters(firstName, LastName,  Telephone,password, Subscribe));
	}

}
