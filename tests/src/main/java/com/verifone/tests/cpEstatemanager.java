package com.verifone.tests;
//	"http://test.cgateway-portal.verifone.com/"

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import com.verifone.utils.DataDrivenUtils;

/**
 * The purpose of this test is to test CP Estatemanager portal
 * @authors Giora Tovim
 */

public class cpEstatemanager{
	//ExtentReports date format and path
	Date date = new Date() ;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
	//ExtentReports path
	public String reportLocation = "C:\\reportTestNgResults\\" + dateFormat.format(date)+".html";	
	//ExtentReports new instance
	//public static ExtentTest test;
	public static ExtentTest childTest, parentTest;
		
	public ExtentReports logger = new ExtentReports(reportLocation, true);		
	Boolean testStepPassed = true;
	String capScreenShootPath;
	//ExtentReports starting test
	ExtentTest test = logger.startTest("Verifone", "Verifone CP Estatemanager Portal Automation");
		
	//Selenium WebDriver
	public WebDriver driver;

	//webPortal.properties path
	public String FilePath = "C:\\tmp\\eclipse-workspace-testng-maven\\eclipse-workspace-testng-maven\\tests\\src\\main\\java\\com\\verifone\\tests\\webPortal.properties";
	public Properties prop = new Properties();
	
	Integer Slp = 2000;
	Integer rowNumber=0;
	Integer getRowNumFromFile = 0;
//	final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\columns.xls";
	
	@Parameters({ "env", "urlDev", "urlTest", "urlStaging1","urlProduction", "browserType"})
	@BeforeTest
	/**
	 * Login to portal and navigate to Application page
	 */
	public void startBrowser(String env,String urlDev, String urlTest,
			String urlStaging1, String urlProduction, String browserType) throws Exception {
		// starting test		
		test.log(LogStatus.INFO, "setBrowser with " + browserType + " browser in " + env + " environment");
		
		try {
			driver = SeleniumUtils.setBrowser(browserType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

// 		LOG-IN PORTAL AND NAVIGATE TO APPLICATION PAGE
		SeleniumUtils.setEnv(env, urlDev, urlTest, urlStaging1, urlProduction, test);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		Thread.sleep(2000);
//		Click Log-in button
//		driver.findElement(By.cssSelector("[href=\"javascript\\:void\\(gotoSso\\(\\)\\)\"]")).click(); 	
//		Thread.sleep(2000);
//		Type username = devadmin@yopmail.com
		WebElement usern = driver.findElement(By.name("username"));
		usern.click();
//		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.sendKeys(usern, "vfieoustest@getnada.com").build().perform();
//		Thread.sleep(2000);
//		Type password = Veri1234 and ENTER
		WebElement userpw = driver.findElement(By.name("password"));
		userpw.click();
//		Thread.sleep(2000);
		builder.sendKeys(userpw, "Veri1234" + Keys.ENTER).build().perform();
//		Thread.sleep(5000);
	
		test.log(LogStatus.INFO, "Log in Portal: Successul ");
	}
	
	@Test (groups = {"CP-portal"})
	public void cpEstatemanager() throws Exception {
		
		logger.startTest("cpEstatemanager", "Verifone CP-portal POC Estate Manager test ");
		
		String title = driver.getTitle();
		System.out.println("Page Title is: " + title);
		test.log(LogStatus.INFO, "The Page Title is: " + title);
		Reporter.log(title, true );		
				
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
//		boolean testPassed=true;



		test.log(LogStatus.INFO, "End of page" + title + " testing");

	}

	
	
	@AfterMethod
	public void closePage(ITestResult result) throws Exception{

		test.log(LogStatus.INFO, "result.getStatus value is : " + result.getStatus());
		switch (result.getStatus()) {        
		case ITestResult.SKIP:
			test.log(LogStatus.SKIP, "Test SKIP");			
			break;
		case ITestResult.SUCCESS:
			test.log(LogStatus.PASS, "Test is successul");			
			break;
		case ITestResult.FAILURE:
			String capScreenShootPath = SeleniumUtils.getscreenshot(driver);
			test.log(LogStatus.FAIL, "Test Failed !!! ");
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			break;
		}
		//ending test
		logger.endTest(test);
		//writing everything to document
		logger.flush();	
//		driver.quit(); 
//		driver.close();
		
	}
	@AfterTest(alwaysRun = true)
	public void closePage1(ITestResult result) throws Exception{
		System.out.println("Closing Web Page");
		Reporter.log( "Closing Web Page", true );
		

	}
}