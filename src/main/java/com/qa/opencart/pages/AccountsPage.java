package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import static com.qa.opencart.constants.AppConstants.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.naming.directory.SearchResult;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final  By headers = By.cssSelector("div#content h2");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button"); 
	
	private static final Logger log = LogManager.getLogger(AccountsPage.class);

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	@Step("getting acc page title")
	public String getAccPageTitle() {		
		String title = eleUtil.waitFotTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		log.info("home page title :" + title);
		return title;
		
	}
	
	@Step("getting acc page url")
	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		log.info("login page url :" + url);
		return url;
	}
	
	@Step("getting acc page headers")
	public List<String> getAccPageHeaders() {
		List<WebElement> headerlist = eleUtil.getElements(headers);
		List<String>headerVlList = new ArrayList<String>();
		for(WebElement e : headerlist ) {
			String text = e.getText();
			headerVlList.add(text);			
		}	
		log.info("Acc page header:" + headerVlList);
		return headerVlList;
		
	}
	
	@Step("perform search :{0}")
	public SearchResultPage dosearch(String searchkey) {
		log.info("searchkey:" + searchkey);
		eleUtil.doSendKeys(search, searchkey );
		eleUtil.doClick(searchIcon);
		return new SearchResultPage( driver);
	}


}
