package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	// 1 private By Locators
	
	private    By firstName = By.id("input-firstname");
	private    By LastName = By.id("input-lastname");
	private    By Email = By.id("input-email");
	private    By Telephone = By.id("input-telephone");
	private    By password = By.id("input-password");
	private    By Confrmpassword = By.id("input-confirm");
	
	private By SubscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input[@type='radio']");
	private By SubscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input[@type='radio']");
	
	private By Checkbox = By.name("agree");
	private By Continuebtn = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By SucessMsg = By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	
	// 2 public page Constr..
	
		public RegisterPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
			
		}
		
		// 3 methods
		
		public boolean userRegisters(String firstName , String LastName, String Telephone,
				String password ,
				String Subscribe) {
			eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_TIMEOUT).sendKeys(firstName);
			eleUtil.doSendKeys(this.LastName,LastName );
			eleUtil.doSendKeys(this.Email,StringUtils.getRandomEmailId() );
			eleUtil.doSendKeys(this.Telephone,Telephone );
			eleUtil.doSendKeys(this.password,password );
			eleUtil.doSendKeys(this.Confrmpassword,password );
			
			if(Subscribe.equalsIgnoreCase("yes")) {
				eleUtil.doClick(SubscribeYes);				
			}else {
				eleUtil.doClick(SubscribeNo);
			}
			
			eleUtil.doClick(Checkbox);
			eleUtil.doClick(Continuebtn);
			
			if(eleUtil.waitForElementVisible(SucessMsg, AppConstants.MEDIUM_DEFAULT_TIMEOUT).getText().contains(AppConstants.REGISTER_SUCESS_MESG))
			{
				eleUtil.doClick(logoutLink);
				eleUtil.doClick(registerLink);
				return true;		
			}
			return false;	
			
		}

}


