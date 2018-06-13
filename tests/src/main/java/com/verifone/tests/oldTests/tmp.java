package com.verifone.tests.oldTests;
//"http://test.cgateway-portal.verifone.com/"

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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
import com.verifone.utils.General.LoginCGPortal;

/**
* This testLog check Application mandatory fields: Appl. ID and Version.
* Verify error message displayed in different cases of empty mandatory fields,
* wrong input type data, value not in allowed range, value not exists, long string value etc.
* @authors Yana Fridman, Fred Shniper
*/

public class tmp{
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
final String xlsxFile = System.getProperty("user.dir") + "\\src\\testLog\\resources\\applicationsGrid.xls";


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
	getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "A_Grid");


}

//      Data Provider

@DataProvider(name = "A_Grid")
public Object[][] dataSupplierLoginData() throws Exception {
	Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "A_Grid");
	return arrayObject;
}

@Test(dataProvider = "A_Grid", groups = { "cgateway-portal" })

public void testAppl(String applicationsID, String Version, String Name, String Status, String Access, String MaxRequestCount) throws Exception {
	String AddXPath = "//*[@id=\"applications\"]/div[2]/a[1]";
	String CancelXPath = "//*[@id=\"applications\"]/div[2]/a[2]";
	String UpdateXPath = "//*[@id=\"applications\"]/div[2]/a[3]";
	String DeleteXPath = "//div[@id='applications']/div[4]/table/tbody/tr/td[13]/a/span"; // "//td[@id='applications_active_cell']/a/span";
	String filterXPath = "//div[@id='applications']/div[3]/div/table/thead/tr/th[2]/a/span/img";
	String filterCSS = "input.k-textbox";
	String ApplID_id = "appID";
	String ApplID_XPath = "//*[contains(@id, 'appID')]";
	String Version_id = "version";
	String Version_xpath = "//*[contains(@id, 'version')]";
	String Name_xpath = "//*[contains(@ng-model, 'vm.currentGridRow.name')]";  //"(//input[@type='text'])[5]";
	String Status_xpath = "//*[contains(@aria-owns, 'status_privateApplications_listbox')]";   //"(//input[@type='text'])[7]";
	String Access_xpath = "//*[contains(@aria-owns, 'throttlingAccess_privateApplications_listbox')]";   //"(//input[@type='text'])[8]";
	String MaxRequestCount_id = "throttlingMaxRequestCount";
	String ApplIDGrid_xpath = "//*[@id=\"applications\"]/div[4]/table/tbody/tr[1]/td[2]";
	String NameGrid_xpath = "//*[@id=\"applications\"]/div[4]/table/tbody/tr[1]/td[3]";
	String VersionGrid_xpath = "//*[@id=\"applications\"]/div[4]/table/tbody/tr[1]/td[4]";
	String StatusGrid_xpath = "//*[@id=\"applications\"]/div[4]/table/tbody/tr[1]/td[5]";
	String AccessGrid_xpath = "//*[@id=\"applications\"]/div[4]/table/tbody/tr[1]/td[6]";
	String MaxRequestCountGrid_xpath = "//*[@id=\"applications\"]/div[4]/table/tbody/tr[1]/td[7]";
	String Messagetext = "";
	Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));
	
	WebDriverWait wait = new WebDriverWait(driver, timeOut);
	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
	
	String capScreenShootPath;	
	String applicationsIDGrid = "";
	String VersionGrid = "";
	String NameGrid = "";
	String StatusGrid = "";
	String AccessGrid = "";
	String MaxRequestCountGrid = "";

	boolean currentResult;
	boolean b;
	
	test.log(LogStatus.INFO, "**************************** Start current Row number: " + Integer.toString(rowNumber+1) + " **********************************");
	
//	Click on ADD button

	currentResult = ApplicationsPage.buttonClick(driver, "Add", AddXPath, timeOut);
	
	System.out.println("Add button click: " + currentResult);
	try {
		Assert.assertTrue(currentResult, "Add button click failed!");
	} catch (Throwable t) {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "Add button click failed!");
	}
	test.log(LogStatus.INFO, "Add button click: " + currentResult + " <span class='label info'>info</span>");
	
//---------------------------FILL FORM and VERIFY GRID UPDATED	
//	Fill ApplicationID field

	currentResult = ApplicationsPage.textboxInput(driver, "ApplicationID", ApplID_id, applicationsID, timeOut);

	System.out.println("Input Applications ID = " + applicationsID + " " + currentResult);	
	try {
		Assert.assertTrue(currentResult, "Type to ApplicationID field failed!");
	} catch (Throwable t) {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "Type to ApplicationID field failed!");
	}
	test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + ": " + currentResult + " <span class='label info'>info</span>");
	
//	Get ApplicationID from GRID
	applicationsIDGrid = ApplicationsPage.getTextXPATH(driver, ApplIDGrid_xpath, timeOut);
	
	System.out.println("Read Applications ID from Grid = " + applicationsIDGrid + " " + currentResult);
	test.log(LogStatus.INFO, "Read Applications ID from Grid = " + applicationsIDGrid + " " + currentResult+ " <span class='label info'>info</span>");
	try {
		Assert.assertEquals(applicationsID, applicationsIDGrid, "ApplicationId on grid not matched with ApplicationId on form");
	} catch (Throwable t) {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "ApplicationId on grid not matched with ApplicationId on form");
	}
//	Fill Version field

	currentResult = ApplicationsPage.textboxInput(driver, "Version", Version_id, Version, timeOut);
	
	System.out.println("Input Version = " + Version + currentResult);	
	try {
		Assert.assertTrue(currentResult, "Type to Version field failed!");
	} catch (Throwable t) {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "Type to Version field failed!");
	}
	test.log(LogStatus.INFO, "Input Version = " + Version + ": " + currentResult+ " <span class='label info'>info</span>");
	
//	Get Version from GRID
	VersionGrid = ApplicationsPage.getTextXPATH(driver, VersionGrid_xpath, timeOut);
	
	System.out.println("Read Version from Grid = " + VersionGrid + " " + currentResult);
	test.log(LogStatus.INFO, "Read Version from Grid = " + VersionGrid + " " + currentResult + " <span class='label info'>info</span>");
	try {
		Assert.assertEquals(Version, VersionGrid, "Version on grid not matched with Version on form");
	} catch (Throwable t) {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "Version on grid not matched with Version on form");
	}
	
//	Fill Status field


	if (!Status.equals("Active")) {
		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Status", Status_xpath, Status, timeOut);
	} 
	
	
//	Fill Throttling Access field

	if (!Access.equals("Control")) {
		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", Access_xpath, Access, timeOut);
	} 
	
	
//	Fill Throttling Max Request Count field
	
	if (Access.equals("Control")) {

			currentResult = ApplicationsPage.textboxInput(driver, "MaxRequestCount", MaxRequestCount_id, MaxRequestCount, timeOut);
			System.out.println("Input MaxRequestCount = " + MaxRequestCount + currentResult);
			test.log(LogStatus.INFO, "Input MaxRequestCount = " + MaxRequestCount + ": " + currentResult + " <span class='label info'>info</span>");
	}

//	Get Throttling Max Request Count from GRID
	if (Access.equals("Control")) {
		MaxRequestCountGrid = ApplicationsPage.getTextXPATH(driver, MaxRequestCountGrid_xpath, timeOut);
		
		System.out.println("Read Max Request Count from Grid = " + MaxRequestCountGrid + " " + currentResult);
		test.log(LogStatus.INFO, "Read Max Request Count from Grid = " + MaxRequestCountGrid + " " + currentResult + " <span class='label info'>info</span>");
		try {
			Assert.assertEquals(MaxRequestCount, MaxRequestCountGrid, "Throttling Max Request Count on grid not matched with Throttling Max Request Count on form");
		} catch (Throwable t) {
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			test.log(LogStatus.FAIL, "Throttling Max Request Count on grid not matched with Throttling Max Request Count on form");
		}
	}
	
//	Fill Name field

	currentResult = ApplicationsPage.textboxInputXPATH(driver, "Name", Name_xpath, Name, timeOut);
		
//	Get Status from GRID
//	Thread.sleep(000);
	StatusGrid = ApplicationsPage.getTextXPATH(driver, StatusGrid_xpath, timeOut);
	
	System.out.println("Input Status = " + Status + currentResult);	
	test.log(LogStatus.INFO, "Input Status = " + Status + ": " + currentResult + " <span class='label info'>info</span>");

	System.out.println("Read Status from Grid = " + StatusGrid + " " + currentResult);
	test.log(LogStatus.INFO, "Read Status from Grid = " + StatusGrid + " " + currentResult + " <span class='label info'>info</span>");
	try {
		Assert.assertEquals(Status, StatusGrid, "Status on grid not matched with Status on table");
	} catch (Throwable t) {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "Status on grid not matched with Status on table");
	}
	
//	Get Throttling Access from GRID

	AccessGrid = ApplicationsPage.getTextXPATH(driver, AccessGrid_xpath, timeOut);
	
	System.out.println("Input Access = " + Access + currentResult);		
	test.log(LogStatus.INFO, "Input Access = " + Access + ": " + currentResult + " <span class='label info'>info</span>");

	System.out.println("Read Access from Grid = " + AccessGrid + " " + currentResult);
	test.log(LogStatus.INFO, "Read Access from Grid = " + AccessGrid + " " + currentResult + " <span class='label info'>info</span>");
	try {
		Assert.assertEquals(Access, AccessGrid, "Throttling Access on grid not matched with Throttling Access on form");
	} catch (Throwable t) {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "Throttling Access on grid not matched with Throttling Access on form");
	}
	
//	Get Name from GRID
	NameGrid = ApplicationsPage.getTextXPATH(driver, NameGrid_xpath, timeOut);
	
	System.out.println("Input Name = " + Name + currentResult);	
	test.log(LogStatus.INFO, "Input Name = " + Name + ": " + currentResult + " <span class='label info'>info</span>");
	
	System.out.println("Read Name from Grid = " + NameGrid + " " + currentResult);
	test.log(LogStatus.INFO, "Read Name from Grid = " + NameGrid + " " + currentResult + " <span class='label info'>info</span>");
	try {
		Assert.assertEquals(Name, NameGrid, "Name on grid not matched with Name on table");
	} catch (Throwable t) {
		capScreenShootPath = SeleniumUtils.getScreenshot();
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		test.log(LogStatus.FAIL, "Name on grid not matched with Name on table");
	}
	
//	Click on CANCEL button

		currentResult = ApplicationsPage.buttonClick(driver, "Cancel", CancelXPath, timeOut);
		System.out.println("Cancel button click: " + currentResult);
		try {
			Assert.assertTrue(currentResult, "Cancel button click failed!");
		} catch (Throwable t) {
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			test.log(LogStatus.FAIL, "Cancel button click failed!");
		}
		test.log(LogStatus.INFO, "Cancel button click: " + currentResult + " <span class='label info'>info</span>");
		
//---------------------------FILL GRID and VERIFY FORM UPDATED	
		
//		Click on ADD button

		currentResult = ApplicationsPage.buttonClick(driver, "Add", AddXPath, timeOut);
		
		System.out.println("Add button click: " + currentResult);		
		test.log(LogStatus.INFO, "Add button click: " + currentResult + " <span class='label info'>info</span>");
		

//		Fill ApplicationID field

		currentResult = ApplicationsPage.textboxInputXPATH(driver, "ApplicationID", ApplIDGrid_xpath, applicationsID, timeOut);
		driver.findElement(By.xpath(NameGrid_xpath)).click();
		
		System.out.println("Input Applications ID = " + applicationsID + " " + currentResult);		
		test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + ": " + currentResult + " <span class='label info'>info</span>");
		
//		Get ApplicationID from FORM
		Thread.sleep(timeOut-1000);
		applicationsIDGrid = driver.findElement(By.id("appID")).getAttribute("value");
		
		System.out.println("Read applicationsID from Form = " + applicationsIDGrid + " " + currentResult);
		test.log(LogStatus.INFO, "Read applicationsID from Form = " + applicationsIDGrid + " " + currentResult + " <span class='label info'>info</span>");
		try {
			Assert.assertEquals(applicationsIDGrid,applicationsID, "ApplicationId on form not matched with ApplicationId on grid");
		} catch (Throwable t) {
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			test.log(LogStatus.FAIL, "ApplicationId on form not matched with ApplicationId on grid");
		}
//		Fill Version field

		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Version", VersionGrid_xpath, Version, timeOut);
		driver.findElement(By.xpath(NameGrid_xpath)).click();
		
		System.out.println("Input Version = " + Version + currentResult);
		test.log(LogStatus.INFO, "Input Version = " + Version + ": " + currentResult + " <span class='label info'>info</span>");
		
//		Get Version from FORM
		VersionGrid = driver.findElement(By.id("version")).getAttribute("value");
		
		System.out.println("Read Version from Form = " + VersionGrid + " " + currentResult);
		test.log(LogStatus.INFO, "Read Version from Form = " + VersionGrid + " " + currentResult + " <span class='label info'>info</span>");
		try {
			Assert.assertEquals(Version, VersionGrid, "Version on grid not matched with Version on table");
		} catch (Throwable t) {
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			test.log(LogStatus.FAIL, "Version on grid not matched with Version on table");
		}
//		Fill Status field

		if (!Status.equals("Active")) {
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath(StatusGrid_xpath)));
			actions.click();
			actions.sendKeys(Keys.chord(Keys.CONTROL,"a") + Keys.DELETE + Status);
			actions.build().perform();
			
			driver.findElement(By.xpath(NameGrid_xpath)).click();
		} 
		
		System.out.println("Input Status = " + Status + currentResult);
		test.log(LogStatus.INFO, "Input Status = " + Status + ": " + currentResult + " <span class='label info'>info</span>");
		
//		Get Status from FORM
		StatusGrid = driver.findElement(By.xpath(Status_xpath)).getAttribute("value");
		
		System.out.println("Read Status from Form = " + StatusGrid + " " + currentResult);
		test.log(LogStatus.INFO, "Read Status from Form = " + StatusGrid + " " + currentResult + " <span class='label info'>info</span>");
		try {
			Assert.assertEquals(Status, StatusGrid, "Status on grid not matched with Status on table");
		} catch (Throwable t) {
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			test.log(LogStatus.FAIL, "Status on grid not matched with Status on table");
		}	
//		Fill Throttling Access field

		if (!Access.equals("Control")) {
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath(AccessGrid_xpath)));
			actions.click();
			actions.sendKeys(Keys.chord(Keys.CONTROL,"a") + Keys.DELETE + Access);
			actions.build().perform();
			Thread.sleep(timeOut);
			
			System.out.println("Input Access = " + Access + currentResult);
			test.log(LogStatus.INFO, "Input Access = " + Access + ": " + currentResult + " <span class='label info'>info</span>");
			driver.findElement(By.xpath(NameGrid_xpath)).click();
		} 
		
//		Get Access from FORM
		Thread.sleep(timeOut);
		AccessGrid = driver.findElement(By.xpath(Access_xpath)).getAttribute("value");
		
		System.out.println("Read Access from Form = " + AccessGrid + " " + currentResult);
		test.log(LogStatus.INFO, "Read Access from Form = " + AccessGrid + " " + currentResult + " <span class='label info'>info</span>");
		try {
			Assert.assertEquals(Access, AccessGrid, "Access on grid not matched with Access on table");
		} catch (Throwable t) {
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			test.log(LogStatus.FAIL, "Access on grid not matched with Access on table");
		}	
		
//		Fill Throttling Max Request Count field
		
		if (Access.equals("Control")) {
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath(MaxRequestCountGrid_xpath)));
			actions.click();
			actions.sendKeys(Keys.chord(Keys.CONTROL,"a") + Keys.DELETE + MaxRequestCount);
			actions.build().perform();

			System.out.println("Input Max Request Count = " + MaxRequestCount + currentResult);
			test.log(LogStatus.INFO, "Input Max Request Count = " + MaxRequestCount + ": " + currentResult + " <span class='label info'>info</span>");
			driver.findElement(By.xpath(NameGrid_xpath)).click();
				
//			Get Throttling Max Request Count from FORM
//				Thread.sleep(1000);
			MaxRequestCountGrid = driver.findElement(By.id(MaxRequestCount_id)).getAttribute("value");
			
			System.out.println("Read Max Request Count from Form = " + MaxRequestCountGrid + " " + currentResult);
			test.log(LogStatus.INFO, "Read Max Request Count from Form = " + MaxRequestCountGrid + " " + currentResult + " <span class='label info'>info</span>");
			try {
				Assert.assertEquals(MaxRequestCount, MaxRequestCountGrid, "Max Request Count on grid not matched with Max Request Count on table");
			} catch (Throwable t) {
				capScreenShootPath = SeleniumUtils.getScreenshot();
				test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
				test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
				test.log(LogStatus.FAIL, "Max Request Count on grid not matched with Max Request Count on table");
			}	
		}


		
		
//		Fill Name field

		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Name", NameGrid_xpath, Name, timeOut);
		driver.findElement(By.xpath(ApplIDGrid_xpath)).click();
		
		System.out.println("Input Name = " + Name + currentResult);
		test.log(LogStatus.INFO, "Input Name = " + Name + ": " + currentResult + " <span class='label info'>info</span>");
		
		
//		Get Name from FORM
		NameGrid = driver.findElement(By.xpath(Name_xpath)).getAttribute("value");
		
		System.out.println("Read Name from Form = " + NameGrid + " " + currentResult);
		test.log(LogStatus.INFO, "Read Name from Form = " + NameGrid + " " + currentResult + " <span class='label info'>info</span>");
		try {
			Assert.assertEquals(Name, NameGrid, "Name on grid not matched with Name on table");
		} catch (Throwable t) {
			capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			test.log(LogStatus.FAIL, "Name on grid not matched with Name on table");
		}
		
//		Click on CANCEL button

			currentResult = ApplicationsPage.buttonClick(driver, "Cancel", CancelXPath, timeOut);
			System.out.println("Cancel button click: " + currentResult);
			Assert.assertTrue(currentResult, "Cancel button click failed!");
			test.log(LogStatus.INFO, "Cancel button click: " + currentResult + " <span class='label info'>info</span>");


                                     
	
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