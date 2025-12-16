package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1 private By Locators
	
	private  final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginbtn = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input");
	private final By RegiLink = By.linkText("Register");
	
	// 2 public page Constr..
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	// 3 public page methods
	
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitFotTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("login page title :" + title);
		return title;
	}
	
	@Step("getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("login page url :" + url);
		return url;
	}
	
	//@Step("login with username: {0} and password: {1}")
	public AccountsPage doLogin(String username , String pswd) {
		System.out.println("user credentials: " + username + pswd);
		eleUtil.waitForElementVisible(email,MEDIUM_DEFAULT_TIMEOUT ).sendKeys(username);
		eleUtil.doSendKeys(password, pswd);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	
	@Step("navigate to the next page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(RegiLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
		
	}


}
