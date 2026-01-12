package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.utils.LogUtils;

import io.qameta.allure.Description;

@Listeners(ChainTestListener.class)
public class BaseTest {

     WebDriver driver;
     DriverFactory df;
    protected Properties prop;
    protected LoginPage loginpage;
    protected AccountsPage accpage;
    protected SearchResultPage searchResultsPage;
    protected ProductInfoPage productInfoPage;
    protected RegisterPage registerPage;
    
	private static final Logger log = LogManager.getLogger(BaseTest.class);
	
    @Description("init the driver and properties")
    @Parameters({"browser" , "browserVersion"})
    @BeforeTest
    public void setup(
            @Optional("chrome") String browserName,
            @Optional("latest") String browserVersion) {
    	

        df = new DriverFactory();
        prop = df.initProp();
        
        // if browserName is passedd from .xml
        if(browserName!=null) {
        	prop.setProperty("browser", browserName);
        	prop.setProperty("browserVersion", browserVersion);

        }
        
        //ChainTestListener.log("properties used :" + prop);
        
       driver = df.initDriver(prop);
       loginpage = new LoginPage(driver);
    }
    
//    @BeforeMethod
//    public void beforeMethod(ITestContext result) {
//    	LogUtils.info("----starting test case----------" + result.getName());
//    }
//    
    @AfterMethod //will be running after @testmethod
    
    public void attachScreenshot(ITestResult result) {
//   	if(!result.isSuccess()) {
//    		ChainTestListener.embed(DriverFactory.getScreenshotByte(), "image/png");
//    		  	}
//    	
    	ChainTestListener.embed(DriverFactory.getScreenshotByte(), "image/png");
    	//LogUtils.info("----ending test case----------" + result.getMethod().getMethodName());
    }

    @Description("close the browser")
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        log.info("closing the browser.......");
    }

}
