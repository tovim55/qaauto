package com.verifone.tests;
//"http://test.cgateway-portal.verifone.com/"

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.Applications.ApplicationsPage;
import com.verifone.utils.Applications.ErrorMsgs;
import com.verifone.utils.General.LoginCGPortal;

/**
* This testLog check Application mandatory fields: Appl. ID and Version.
* Verify error message displayed in different cases of empty mandatory fields,
* wrong input type data, value not in allowed range, value not exists, long string value etc.
* @authors Yana Fridman, Fred Shniper
*/

public class testAppl{
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
final String xlsxFile = System.getProperty("user.dir") + "\\src\\testLog\\resources\\applicationsInputValidation.xls";


@Parameters({ "env", "urlDev", "urlTest", "urlStaging1","urlProduction", "browserType" })
@BeforeTest
/**
 * Login to portal and navigate to Application page
 */
public void startBrowser(String env,String urlDev, String urlTest,
		String urlStaging1, String urlProduction, String browserType) throws Exception {
	
	//webPortal.properties
	FileInputStream ip = new FileInputStream(FilePath);
	prop.load(ip);
	String userName = prop.getProperty("user_id");
	String userPassword = prop.getProperty("password_id");
	
	// starting testLog
	
	try {
		driver = SeleniumUtils.setBrowser(browserType);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	SeleniumUtils.setEnv(env, urlDev, urlTest, urlStaging1, urlProduction, test);
	
//		LOG-IN PORTAL AND NAVIGATE TO APPLICATION PAGE
	LoginCGPortal.LoginCGPortal(driver, userName, userPassword, true);
	
//		Navigate to Application Page
	driver.findElement(By.linkText("Applications")).click();
	
// 		Get number of Rows from Data driven
	getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "inputValidation");


}

//      Data Provider

@DataProvider(name = "inputValidation")
public Object[][] dataSupplierLoginData() throws Exception {
	Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "inputValidation");
	return arrayObject;
}

@Test(dataProvider = "inputValidation", groups = { "cgateway-portal" })

public void testAppl(String applicationsID, String Version, String Name, String Status, String Access, String MaxRequestCount, String msgFieldReqTxtCss, String Error) throws Exception {
	String AddXPath = "//*[@id=\"applications\"]/div[2]/a[1]";
	String CancelXPath = "//*[@id=\"applications\"]/div[2]/a[2]";
	String UpdateXPath = "//*[@id=\"applications\"]/div[2]/a[3]";
	String DeleteXPath = "//div[@id='applications']/div[4]/table/tbody/tr/td[13]/a/span"; // "//td[@id='applications_active_cell']/a/span";
	String filterXPath = "//div[@id='applications']/div[3]/div/table/thead/tr/th[2]/a/span/img";
	String filterCSS = "input.k-textbox";
	String ApplID_id = "appID";
	String Version_id = "version";
	String Name_xpath = "(//input[@type='text'])[5]";
	String Status_xpath = "(//input[@type='text'])[7]";
	String Access_xpath = "(//input[@type='text'])[8]";
	String MaxRequestCount_id = "throttlingMaxRequestCount";
	String Messagetext = "";
	Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));
	
	WebDriverWait wait = new WebDriverWait(driver, timeOut);
	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
	
	String capScreenShootPath;	

	boolean currentResult;
	boolean b;
	
	test.log(LogStatus.INFO, "**************************** Start current Row number: " + Integer.toString(rowNumber+1) + " **********************************");
	
//	Click on ADD button
	currentResult = ApplicationsPage.buttonExists(driver, "Add", AddXPath, timeOut);
	System.out.println("Add button found: " + currentResult);
	currentResult = ApplicationsPage.buttonClick(driver, "Add", AddXPath, timeOut);
	System.out.println("Add button click: " + currentResult);
	
	Assert.assertTrue(currentResult, "Add button click failed!");
	test.log(LogStatus.INFO, "Add button click: " + currentResult);
	
	
//	Fill ApplicationID field
	if (applicationsID.equals("99")) {
		applicationsID="";
	}
	currentResult = ApplicationsPage.textboxInput(driver, "ApplicationID", ApplID_id, applicationsID, timeOut);
	System.out.println("Input Applications ID = " + applicationsID + " " + currentResult);
	
	Assert.assertTrue(currentResult, "Type to ApplicationID field failed!");
	test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + ": " + currentResult);
	
//	Fill Version field
	if (Version.equals("99")) {
		Version="";
	}
	currentResult = ApplicationsPage.textboxInput(driver, "Version", Version_id, Version, timeOut);
	System.out.println("Input Version = " + Version + currentResult);
	
	Assert.assertTrue(currentResult, "Type to Version field failed!");
	test.log(LogStatus.INFO, "Input Version = " + Version + ": " + currentResult);
	
//	Fill Name field
	if (Name.equals("99")) {
		Name="";
	}
	currentResult = ApplicationsPage.textboxInputXPATH(driver, "Name", Name_xpath, Name, timeOut);
	System.out.println("Input Name = " + Name + currentResult);
	
	Assert.assertTrue(currentResult, "Type to Name field failed!");
	test.log(LogStatus.INFO, "Input Name = " + Name + ": " + currentResult);
	
//	Fill Status field
	if (Status.equals("99")) {
		Status="";
	}

	if (!Status.equals("Active")) {
		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Status", Status_xpath, Status, timeOut);
		System.out.println("Input Status = " + Status + currentResult);
		
		Assert.assertTrue(currentResult, "Type to Status field failed!");
		test.log(LogStatus.INFO, "Input Status = " + Status + ": " + currentResult);
	} 
	
//	Fill Throttling Access field
	if (Access.equals("99")) {
		Access="";
	}
	if (!Access.equals("Control") & !Access.equals("Allow") & !Access.equals("Deny")) {
		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", Access_xpath, Access, timeOut);
		System.out.println("Input Access = " + Access + currentResult);
		
		Assert.assertTrue(currentResult, "Type to Throttling Access field failed!");
		if (Access.equals("")) {
			test.log(LogStatus.INFO, "Input Access is empty: " + currentResult);
		} else {
			test.log(LogStatus.INFO, "Input Access = " + Access + ": " + currentResult);
		}
	} 
	
//	Fill Throttling Max Request Count field
	switch (Access) {
		case "Control":
				if (MaxRequestCount.equals("99")) {
					MaxRequestCount = "";
				} 
				if (!MaxRequestCount.equals("1500")) {
					currentResult = ApplicationsPage.textboxInput(driver, "MaxRequestCount", MaxRequestCount_id, MaxRequestCount, timeOut);
					System.out.println("Input MaxRequestCount = " + MaxRequestCount + currentResult);
					
					Assert.assertTrue(currentResult, "Type to Throttling Max Request Count field failed!");
					test.log(LogStatus.INFO, "Input MaxRequestCount = " + MaxRequestCount + ": " + currentResult);
				}
				break;
		case "Allow":
				currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", Access_xpath, Access, timeOut);
				System.out.println("Input Access = " + Access + currentResult);
				currentResult = ApplicationsPage.textboxEnabled(driver, "MaxRequestCount", MaxRequestCount_id, timeOut);
				System.out.println(currentResult);
				if(currentResult)  {
					capScreenShootPath = SeleniumUtils.getScreenshot();
					test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
					test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));;
					test.log(LogStatus.FAIL, "Throttling Max Request Count field expected disabled but found enabled!");
				} else { 
					test.log(LogStatus.PASS, "Throttling Max Request Count field disabled as expected.");
				}
				Assert.assertFalse(currentResult, "Throttling Max Request Count field expected disabled but found enabled!");
				break;
		case "Deny":
				currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", Access_xpath, Access, timeOut);
				System.out.println("Input Access = " + Access + currentResult);
				currentResult = ApplicationsPage.textboxEnabled(driver, "MaxRequestCount", MaxRequestCount_id, timeOut);
				
				if(currentResult)  {
					capScreenShootPath = SeleniumUtils.getScreenshot();
					test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
					test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
					test.log(LogStatus.FAIL, "Throttling Max Request Count field expected disabled but found enabled!");
				} else { 
					test.log(LogStatus.PASS, "Throttling Max Request Count field disabled as expected.");
				}
				Assert.assertFalse(currentResult, "Throttling Max Request Count field expected disabled but found enabled!");
				break;			
	}
	

//	Click on UPDATE button
	currentResult = ApplicationsPage.buttonClick(driver, "Update", UpdateXPath, timeOut);
	System.out.println("Update button click: " + currentResult);
	
	Assert.assertTrue(currentResult, "Update button click failed!");
	test.log(LogStatus.INFO, "Update button click: " + currentResult);

//	Verify Expected error displayed
	Messagetext = ErrorMsgs.msgFieldReqTxt(driver, msgFieldReqTxtCss, timeOut);
	System.out.println("Error message text: " + Messagetext);
	if(!Messagetext.equals(Error))  {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "Error message text: " + Messagetext + ". Expected: " + Error);
	} else { 
		test.log(LogStatus.PASS, "Expected Error got: " + Messagetext);
	}
	Assert.assertEquals(Messagetext, Error);
	
//	Click on CANCEL button
	if(!Messagetext.equals("Succeeded.")) {
		currentResult = ApplicationsPage.buttonClick(driver, "Cancel", CancelXPath, timeOut);
		System.out.println("Cancel button click: " + currentResult);
		Assert.assertTrue(currentResult, "Cancel button click failed!");
		test.log(LogStatus.INFO, "Cancel button click: " + currentResult);
	}
	else {

//  Filter rows by ApplicationID = applicationsID
		try {
			driver.findElement(By.cssSelector(msgFieldReqTxtCss)).click();
		}catch(Exception e){
			System.out.println("Message not found!");
			test.log(LogStatus.INFO, "Message not found!");
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		}
		currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
		Assert.assertTrue(currentResult, "Filter icon click failed!");
		System.out.println("Filter icon click: " + currentResult);
		test.log(LogStatus.INFO, "Filter icon click: " + currentResult);
		System.out.println("Input Applications ID = " + applicationsID);
		test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID);
		currentResult = ApplicationsPage.filterTypeSearch(driver, filterCSS, applicationsID, timeOut);
		Assert.assertTrue(currentResult, "Filter Search action failed!");

//		Delete Added entry
		String alertMessage="";
		Alert alert = null;

		if (currentResult == true) {
			Thread.sleep(timeOut);
			b = ApplicationsPage.buttonClick(driver, "Delete", DeleteXPath, timeOut);
			System.out.println("Delete button click: " + b);
			Assert.assertTrue(b, "Delete button click failed!");
			test.log(LogStatus.INFO, "Delete button click: " + b);
			alert = driver.switchTo().alert();
			alertMessage= driver.switchTo().alert().getText();
			if (alertMessage.contains("delete this record")) {
				alert.accept();	
				driver.findElement(By.cssSelector(".toast-successIcon")).click();
				currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
				Assert.assertTrue(currentResult, "Filter icon click failed!");
				currentResult = ApplicationsPage.filterReset(driver, filterCSS, timeOut);
				Assert.assertTrue(currentResult, "Filter Reset action failed!");
				Thread.sleep(timeOut);
			}
			else {
				System.out.println("Got incorrect alert: " + alertMessage);
				capScreenShootPath = SeleniumUtils.getScreenshot();
				test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
				test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
				test.log(LogStatus.FAIL, "Got incorrect alert: " + alertMessage);
			}
			
		}
		else {
			System.out.println("Filter fail!");
			Assert.assertEquals(currentResult, true);
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			test.log(LogStatus.FAIL, "Filter fail!");
		}
	}                                                  
	
	System.out.println("-------------------------------------------------------------------------");
	test.log(LogStatus.INFO, "**************************** End current Row number: "  + Integer.toString(rowNumber+1) + "***********************************");
	
	}

@AfterMethod
public void closePage(ITestResult result) throws Exception{
	rowNumber++;
	System.out.println("Current Row number: " + rowNumber);
//  REFRESH page in case some failure
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