package com.verifone.tests;
//"http://test.cgateway-portal.verifone.com/"

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
import com.verifone.utils.BlackList.ErrorMsgs;
import com.verifone.utils.BlackList.BlackListPage;
import com.verifone.utils.General.LoginCGPortal;

/**
* This test check Application mandatory fields: Appl. ID and Version. 
* Verify error message displayed in different cases of empty mandatory fields,
* wrong input type data, value not in allowed range, value not exists, long string value etc.
* @authors Yana Fridman, Fred Shniper
*/

public class testBlackList{
	//ExtentReports date format and path
	Date date = new Date() ;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
	//ExtentReports path
	public String reportLocation = "C:\\reportTestNgResults\\" + dateFormat.format(date)+".html";	
	//ExtentReports new instance
		
	public ExtentReports logger = new ExtentReports(reportLocation, true);		
	ExtentTest test = logger.startTest("Verifone", "Verifone cgateway Portal Automation");


//Selenium WebDriver
public WebDriver driver;

//webPortal.properties path
public String FilePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\verifone\\tests\\webPortal.properties";
public Properties prop = new Properties();

Integer rowNumber=0;
Integer getRowNumFromFile = 0;
final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\blacklistInputValidation.xls";


@Parameters({ "env", "urlDev", "urlTest", "urlStaging1","urlProduction", "browserType" })
@BeforeTest
/**
* Login to portal and navigate to BlackList page
*/
public void startBrowser(String env,String urlDev, String urlTest,
		String urlStaging1, String urlProduction, String browserType) throws Exception {
	
	//webPortal.properties
	FileInputStream ip = new FileInputStream(FilePath);
	prop.load(ip);
	String userName = prop.getProperty("user_id");
	String userPassword = prop.getProperty("password_id");
	
	// starting test		
	
	try {
		driver = SeleniumUtils.setBrowser(browserType);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	SeleniumUtils.setEnv(env, urlDev, urlTest, urlStaging1, urlProduction, test);
	
//		LOG-IN PORTAL AND NAVIGATE TO BLACKLIST PAGE
	LoginCGPortal.LoginCGPortal(driver, userName, userPassword, true);
	
//		Navigate to Application Page
	driver.findElement(By.linkText("Blacklist")).click();
	
//		Get number of Rows from Data driven
	getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "inputValidation");


}

//    Data Provider

@DataProvider(name = "inputValidation")
public Object[][] dataSupplierLoginData() throws Exception {
	Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "inputValidation");
	return arrayObject;
}

@Test(dataProvider = "inputValidation", groups = { "cgateway-portal" })

public void testBlackList(String Type, String Value, String msgFieldReqTxtCss, String Error) throws Exception {
	String AddXPath = "//div[@id='filters']/div[2]/a[1]";
	String CancelXPath = "//*[@id=\"filters\"]/div[2]/a[2]";
	String UpdateXPath = "//*[@id=\"filters\"]/div[2]/a[3]";
	String DeleteXPath = "//div[@id='filters']/div[4]/table/tbody/tr/td[6]/a/span"; // "//td[@id='applications_active_cell']/a/span";
	String filterXPath = "//div[@id='filters']/div[3]/div/table/thead/tr/th[3]/a/span/img";
	String filterCSS = "input.k-textbox";
	String Type_XPath = "(//input[@type='text'])[4]";
	String Value_XPath = "(//input[@type='text'])[5]";
	
	String Messagetext = "";
	Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));
	
	WebDriverWait wait = new WebDriverWait(driver, timeOut);
	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	

	boolean currentResult;
	boolean b;
	
//	Click on ADD button
	currentResult = BlackListPage.buttonExists(driver, "Add", AddXPath, timeOut*100);
	System.out.println("Add button found: " + currentResult);
	currentResult = BlackListPage.buttonClick(driver, "Add", AddXPath, timeOut*100);
	System.out.println("Add button click: " + currentResult);
	
	Assert.assertTrue(currentResult, "Add button click failed!");
	test.log(LogStatus.INFO, "Add button click: " + currentResult);
	
//	Fill Type field
	if (Type.equals("99")) {
		Type="";
	}
	WebElement n = driver.findElement(By.xpath("(//input[@type='text'])[4]"));
//	n.
	currentResult = BlackListPage.textboxInputXPATH(driver, "Type", Type_XPath, Type, timeOut*100);
	System.out.println("Input Type = " + Type + " " + currentResult);
	
	Assert.assertTrue(currentResult, "Type to Type field failed!");
	test.log(LogStatus.INFO, "Input Type = " + Type + ": " + currentResult);
	
//	Fill Value field
	if (Value.equals("99")) {
		Value ="";
	}
	currentResult = BlackListPage.textboxInputXPATH(driver, "Value", Value_XPath, Value, timeOut*100);
	System.out.println("Input Value = " + Value + currentResult);
	
	Assert.assertTrue(currentResult, "Type to Value field failed!");
	test.log(LogStatus.INFO, "Input Value = " + Value + ": " + currentResult);
	

//	Click on UPDATE button
	currentResult = BlackListPage.buttonClick(driver, "Update", UpdateXPath, timeOut*100);
	System.out.println("Update button click: " + currentResult);
	
	Assert.assertTrue(currentResult, "Update button click failed!");
	test.log(LogStatus.INFO, "Update button click: " + currentResult);

//	Verify Expected error displayed
	Messagetext = ErrorMsgs.msgFieldReqTxt(driver, msgFieldReqTxtCss, timeOut*100);
	System.out.println("Error message text: " + Messagetext);
	if(!Messagetext.equals(Error))  {
		test.log(LogStatus.FAIL, "Error message text: " + Messagetext + ". Expected: " + Error);
	} else { 
		test.log(LogStatus.PASS, "Expected Error got: " + Messagetext);
	}
	Assert.assertEquals(Messagetext, Error);
	
//	Click on CANCEL button
	if(!Messagetext.equals("Succeeded.")) {
		currentResult = BlackListPage.buttonClick(driver, "Cancel", CancelXPath, timeOut*100);
		System.out.println("Cancel button click: " + currentResult);
		Assert.assertTrue(currentResult, "Cancel button click failed!");
		test.log(LogStatus.INFO, "Cancel button click: " + currentResult);
	}
	else {

//Filter rows by ApplicationID = applicationsID
		try {
			driver.findElement(By.cssSelector(msgFieldReqTxtCss)).click();
		}catch(Exception e){
			System.out.println("Message not found!");
			test.log(LogStatus.INFO, "Message not found!");
		}
		currentResult = BlackListPage.filterClick(driver, filterXPath, timeOut*100);
		Assert.assertTrue(currentResult, "Filter icon click failed!");
		System.out.println("Filter icon click: " + currentResult);
		test.log(LogStatus.INFO, "Filter icon click: " + currentResult);
		System.out.println("Input Value = " + Value);
		test.log(LogStatus.INFO, "Input Value = " + Value);
		currentResult = BlackListPage.filterTypeSearch(driver, filterCSS, Value, timeOut*100);
		Assert.assertTrue(currentResult, "Filter Search action failed!");

//		Delete Added entry
		String alertMessage="";
		Alert alert = null;

		if (currentResult == true) {
			Thread.sleep(timeOut*100);
			b = BlackListPage.buttonClick(driver, "Delete", DeleteXPath, timeOut*100);
			System.out.println("Delete button click: " + b);
			Assert.assertTrue(b, "Delete button click failed!");
			test.log(LogStatus.INFO, "Delete button click: " + b);
			alert = driver.switchTo().alert();
			alertMessage= driver.switchTo().alert().getText();
			if (alertMessage.contains("delete this record")) {
				alert.accept();	
				driver.findElement(By.cssSelector(".toast-successIcon")).click();
				currentResult = BlackListPage.filterClick(driver, filterXPath, timeOut*100);
				Assert.assertTrue(currentResult, "Filter icon click failed!");
				currentResult = BlackListPage.filterReset(driver, filterCSS, timeOut*100);
				Assert.assertTrue(currentResult, "Filter Reset action failed!");
				Thread.sleep(timeOut*100);
			}
			else {
				System.out.println("Got incorrect alert: " + alertMessage);
				test.log(LogStatus.FAIL, "Got incorrect alert: " + alertMessage);
			}
			
		}
		else {
			System.out.println("Filter fail!");
			Assert.assertEquals(currentResult, true);
			test.log(LogStatus.FAIL, "Filter fail!");
		}
	}                                                  
	
	System.out.println("-------------------------------------------------------------------------");
	
	}

@AfterMethod
public void closePage(ITestResult result) throws Exception{
	
	rowNumber++;
	System.out.println("Current Row number: " + rowNumber);
//REFRESH page in case some failure
	if(result.getStatus() == ITestResult.FAILURE)
  {
		try{
			driver.navigate().refresh();
		}catch(Exception e){
			   //ignore
		}
  }
	logger.endTest(test);
	logger.flush();
	if (rowNumber == getRowNumFromFile) {			
//		driver.close();
	}
}
}

