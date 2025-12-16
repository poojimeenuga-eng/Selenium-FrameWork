package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkExceptions; 

public class DriverFactory {
	
	static  WebDriver driver;	 
	 Properties prop;
	 OptionsManager optionsManager;
	 
	 public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	 public static String highlight;
	 
	 private static final Logger log = LogManager.getLogger(DriverFactory.class);
	// log has 4 diff states i.e warn,error,fatal,info
	 
	 public WebDriver initDriver(Properties prop) {
		 
		log.info("properties:" +prop);
		 String browsername =  prop.getProperty("browser");
		 //System.out.println("browser name :" + browsername);
		 
		 log.info("browser name :" + browsername);
		 ChainTestListener.log("browser name :" + browsername);
		 optionsManager = new OptionsManager(prop);
		 
		 highlight= prop.getProperty("highlight");
		 
		 switch (browsername.toLowerCase().trim()) {
			case "chrome":
				tlDriver .set(new ChromeDriver(optionsManager.getChromeOptions()));
				break;
			case "edge":
				tlDriver .set(new EdgeDriver(optionsManager.getEdgeOptions()));
				break;
			case "firefox":
				tlDriver .set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				break;
			case "safari":
				tlDriver .set( new SafariDriver());
				break;

			default:
				//System.out.println("plz pass the valid browser name..." + browsername);
				log.error("plz pass the valid browser name..." + browsername);
				throw new BrowserException("===INVALID BROWSER===");
			}

		 getDriver().get(prop.getProperty("url"));//login page url
		 getDriver().manage().window().maximize();
		 getDriver().manage().deleteAllCookies();
			return  getDriver();
		}
	 
	 
	 public static WebDriver getDriver() {
		 return tlDriver.get();
	 }
	 
	 // maven clean install -Denv = "stage"
	 
	 public Properties initProp() {	
		 
		 prop = new Properties();
		 
		String envName =  System.getProperty("env");
		 FileInputStream ip = null;
		 
		 try {			 
		 
		 if(envName == null) {
			// System.out.println("env is null, hence running the tests on QA env...");
			 log.warn("env is null, hence running the tests on QA env...");
			 ip = new FileInputStream("./src/test/resources/config/qa.config.properties"); 
		 }else {
			 //System.out.println("Running tests on env: " + envName);
			 log.info("Running tests on env: " + envName);
			 
			 switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
				
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
				
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
				
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
				
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
				break;

			default:
				log.error("-----INVALID MSG------ :" + envName);
				throw new FrameWorkExceptions("====INVALID ENV NAME========" + envName);
				
			}
		 }
		 
		 
	 } catch(FileNotFoundException e) {
		 e.printStackTrace();
	 }
	 
	 try {
		 prop.load(ip);
	 }catch(IOException e) {
		 e.printStackTrace();
	 }
	 
	
		 return prop;
	 }
	 
	 
	 /// ************ takescreenshots************////
	 
	 public static File getScreenshot() {
		 File srcFile = ((TakesScreenshot) getDriver() ).getScreenshotAs(OutputType.FILE);
		return srcFile; 
	 }
 
	 public static byte[] getScreenshotByte() {
	return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);	 	 
	 }
	 
	 public static String  getScreenshotBASE64() {
		 return ((TakesScreenshot) getDriver() ).getScreenshotAs(OutputType.BASE64);	 
	 }
	 
//	 /**
//		 * this is used to init the config properties
//		 * @return
//		 */
//		public Properties initProp() {
//			prop = new Properties();
//			try {
//				FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
//				prop.load(ip);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			return prop;
//		}

}
