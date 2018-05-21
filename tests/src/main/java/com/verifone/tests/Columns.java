package com.verifone.tests;
//	"http://test.cgateway-portal.verifone.com/"

import org.testng.annotations.Test;
import java.awt.Robot;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import com.verifone.utils.DataDrivenUtils;

/**
 * This testLog check Columns options data and it's status. Initial State and after update and reset changes
 * @authors Yana Fridman, Fred Shniper

 */

public class Columns{
	//ExtentReports date format and path
	Date date = new Date() ;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
	//ExtentReports path
	public String reportLocation = "C:\\reportTestNgResults\\" + dateFormat.format(date)+".html";	
	//ExtentReports new instance
	//public static ExtentTest testLog;
	public static ExtentTest childTest;
		
	public ExtentReports logger = new ExtentReports(reportLocation, true);		
	Boolean testStepPassed = true;
	public static ExtentTest parentTest;
	String capScreenShootPath;
	//ExtentReports starting testLog
	ExtentTest test = logger.startTest("Verifone", "Verifone cgateway Portal Automation");
		
	//Selenium WebDriver
	public WebDriver driver;

	//webPortal.properties path
	public String FilePath = "C:\\tmp\\eclipse-workspace-testng-maven\\eclipse-workspace-testng-maven\\tests\\src\\main\\java\\com\\verifone\\tests\\webPortal.properties";
	public Properties prop = new Properties();
	
	Integer Slp = 2000;
	Integer rowNumber=0;
	Integer getRowNumFromFile = 0;
	final String xlsxFile = System.getProperty("user.dir") + "\\src\\testLog\\resources\\columns.xls";
	
	@Parameters({ "env", "urlDev", "urlTest", "urlStaging1","urlProduction", "browserType" })
	@BeforeTest
	/**
	 * Login to portal and navigate to Application page
	 */
	public void startBrowser(String env,String urlDev, String urlTest,
			String urlStaging1, String urlProduction, String browserType) throws Exception {
		// starting testLog
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
		driver.findElement(By.cssSelector("[href=\"javascript\\:void\\(gotoSso\\(\\)\\)\"]")).click(); 	
//		Thread.sleep(2000);
//		Type username = devadmin@yopmail.com
		WebElement usern = driver.findElement(By.name("username"));
		usern.click();
//		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.sendKeys(usern, "vfiqabasicdev@getnada.com").build().perform();
//		builder.sendKeys(usern, "devadmin@yopmail.com").build().perform();
//		Thread.sleep(2000);
//		Type password = Veri1234 and ENTER
		WebElement userpw = driver.findElement(By.name("password"));
		userpw.click();
//		Thread.sleep(2000);
		builder.sendKeys(userpw, "Veri1234" + Keys.ENTER).build().perform();
//		Thread.sleep(5000);
//		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", btn);
//		Navigate to Application Page
		driver.findElement(By.linkText("Applications")).click();
		test.log(LogStatus.INFO, "Log in Portal: Successul ");
//	 	Get number of Rows from Data driven
		getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "columns");
	}


	//DataProvider for login
	@DataProvider(name = "columns")
	public Object[][] dataSupplierLoginData() throws Exception {
		Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "columns");
		return arrayObject;
	}
	
	@SuppressWarnings("deprecation")
	@Test(dataProvider = "columns", groups = { "cgateway-portal" })
//	@Test(dataProvider = "columns")
	public void columns(String subMenu, String mainMenu, String pageName, String columnsBtnId, String checkBoxId, String resetId, String columnsNumber, String c1, String c2, String c3, String c4, String c5, String c6, String c7, String c8,
			String c9, String c10, String c11, String c12, String c13, String c14, String c15, String c16, String c17, String c18, String c19, String c20,
			String c21) throws Exception {
		
		logger.startTest("columns", "Verifone cgateway-portal POC Columns testLog ");
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		String columnXpathLocator = "//*[@id=\""+checkBoxId+"\"]/div[@@@@@]/label";
		String menuNodeXpath = "//a[@href=@@@@]";
		String subMenuParrentNodeXpath = "//a[@ui-sref=@@@@]"; 
		boolean testPassed=true;

//		Starting testLog
//		Navigate to Page
		System.out.println("Navigate to "+pageName+" page.");
		test.log(LogStatus.INFO, "Navigate to "+pageName+" page.");
		
//		Open main menu and scroll down if need
		test.log(LogStatus.INFO, "Open main menu " + mainMenu);

		Robot robot = new Robot();
		if(subMenu.equals("yes")) {
//			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			WebElement elem = driver.findElement(By.linkText(mainMenu));


			Thread.sleep(Slp);
			System.out.println("subMenu " + subMenu);
			int x = elem.getLocation().getY();
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			try{
				js2.executeScript("window.scrollTo(0,"+x+")");
				   
				}catch(Exception e){
				   //ignore
				}
			
			Thread.sleep(Slp);
			driver.findElement(By.linkText(mainMenu)).click();

//			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(2000);
		}
//		Click on  sub menu and navigate to page
		
		System.out.println(menuNodeXpath.replaceAll("@@@@", "'#/"+pageName+"'"));
		WebElement elem1 = driver.findElement(By.xpath(menuNodeXpath.replaceAll("@@@@", "'#/"+pageName+"'")));
		Actions actions2 = new Actions(driver);
		String menuNodeXpath1 = menuNodeXpath.replaceAll("@@@@", "'#/"+pageName+"'");

		Thread.sleep(Slp);
		System.out.println(elem1.isEnabled());

		int y = elem1.getLocation().getY();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;

		try{
		js1.executeScript("window.scrollTo(0,"+y+")");
		   
		}catch(Exception e){
		   //ignore
		}
		elem1.click();
		Thread.sleep(Slp);
		
//		Click on COLUMNS button
		test.log(LogStatus.INFO, "Click on COLUMNS button");
		WebElement elem3 = driver.findElement(By.id(columnsBtnId));
		Actions actions3 = new Actions(driver);
		Thread.sleep(Slp);
//		robot.keyPress(KeyEvent.VK_PAGE_UP);
//		robot.keyRelease(KeyEvent.VK_PAGE_UP);
//		robot.keyPress(KeyEvent.VK_PAGE_UP);
//		robot.keyRelease(KeyEvent.VK_PAGE_UP);
		y = elem3.getLocation().getY();
		js1 = (JavascriptExecutor) driver;
		try{
		js1.executeScript("window.scrollTo(0,"+y+")");
		}catch(Exception e){
			   //ignore
			}
		Thread.sleep(Slp*2);
		System.out.println(columnsBtnId);
		driver.findElement(By.id(columnsBtnId)).click();
		Thread.sleep(Slp);
		
//		Create ArrayList of values to validate
		
		String a[] = {c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21};
		
//		Check box list. Compare list of check boxes with ArrayList of expected check box options
		test.log(LogStatus.INFO, "Check box list. Compare list of check boxes with ArrayList of expected check box options");
		
		String tmp=null;
		for (int i=0; i<a.length; i++ ) {
			if(!a[i].equals("99")) {
				if(a[i].startsWith("_"))
					tmp = a[i].substring(1);
				else 
					tmp = a[i];
				System.out.println("Expected: "+ tmp);
				test.log(LogStatus.INFO, "Expected: "+ tmp);
				if( tmp.equals(driver.findElement(By.xpath(columnXpathLocator.replaceAll( "@@@@@", Integer.toString(i+1) ))).getAttribute("innerText"))) {
					System.out.println("Actual: "+ driver.findElement(By.xpath(columnXpathLocator.replaceAll( "@@@@@", Integer.toString(i+1) ))).getAttribute("innerText"));
					test.log(LogStatus.INFO, "Actual: "+ driver.findElement(By.xpath(columnXpathLocator.replaceAll( "@@@@@", Integer.toString(i+1) ))).getAttribute("innerText"));
					testPassed = true; 
				} else {
					testPassed = false;
					System.out.println("Actual: "+ driver.findElement(By.xpath(columnXpathLocator.replaceAll( "@@@@@", Integer.toString(i+1) ))).getAttribute("innerText"));
					test.log(LogStatus.FAIL, "Actual: "+ driver.findElement(By.xpath(columnXpathLocator.replaceAll( "@@@@@", Integer.toString(i+1) ))).getAttribute("innerText"));
					testStepPassed = false;
					capScreenShootPath = SeleniumUtils.getScreenshot();
					test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
					test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
				}
			}
		}
		
		WebElement d;

//		Check box STATUS list. Compare list of check box STATUSES with expected list of check box STATUSES
		test.log(LogStatus.INFO, "Check box STATUS list. Compare list of check box STATUSES with expected list of check box STATUSES");
		
		for (int i=0; i<a.length; i++ ) {
			if(!a[i].equals("99")) {
				if(!a[i].startsWith("_")) {
					d = driver.findElement(By.id(Integer.toString(i)));	
					if(d.isSelected()) {
						testPassed = true;
						System.out.println(a[i] + " Selected as expected");
						test.log(LogStatus.INFO, a[i] + " Selected");
					}
					else {
						testPassed = false;
						System.out.println(a[i] + " Failed");
					}
					
				}
			}
		}
// 		Change GUID checkbox status by click on it
		test.log(LogStatus.INFO, "Change GUID checkbox status by click on it");
		
		driver.findElement(By.id("0")).click();
//		Thread.sleep(1000);
		
//		Reset changes by click on RESET button
		test.log(LogStatus.INFO, "Reset changes by click on RESET button");
		
		driver.findElement(By.id(resetId)).click();
		Thread.sleep(Slp);
		
//		Check box STATUS list. Compare list of check box STATUSES with initial list of check box STATUSES to verify changes were reseted.
		test.log(LogStatus.INFO, "Check box STATUS list. Compare list of check box STATUSES with initial list of check box STATUSES to verify changes were reseted");
		
		for (int i=0; i<a.length; i++ ) {
			if(!a[i].equals("99")) {
				if(!a[i].startsWith("_")) {
					d = driver.findElement(By.id(Integer.toString(i)));	
					if(d.isSelected()) {
						testPassed = true;
						System.out.println(a[i] + " Selected as expected");
					}
					else {
						System.out.println(a[i] + " Failed");
						testStepPassed = false;
						testPassed = false;
					}
				}
			}
		}
		
		driver.findElement(By.id(columnsBtnId)).click();
//		Thread.sleep(2000);

		Assert.assertTrue(testStepPassed);
		
//		Close subMenu list
		test.log(LogStatus.INFO, "Close subMenu list");
		Actions actions4 = new Actions(driver);
		
		if(subMenu.equals("yes")) {
//			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			WebElement elem2 = driver.findElement(By.linkText(mainMenu));
			y = elem2.getLocation().getY();
			js1 = (JavascriptExecutor) driver;
			try{
			js1.executeScript("window.scrollTo(0,"+y+")");
			}catch(Exception e){
				   //ignore
				}
			driver.findElement(By.linkText(mainMenu)).click();
//			Thread.sleep(2000);
		}
		System.out.println("################## End of page " + pageName + " testing ######################");
		test.log(LogStatus.INFO, "End of page" + pageName + " testing");

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
//			String capScreenShootPath = SeleniumUtils.getScreenshot(driver);
			test.log(LogStatus.FAIL, "Test Failed !!! ");
//			testLog.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//			testLog.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + testLog.addBase64ScreenShot(capScreenShootPath));
			break;
		}
		//ending testLog
		logger.endTest(test);
		//writing everything to document
		logger.flush();	
		rowNumber++;
		System.out.println("Current Row number: " + rowNumber);
		if (rowNumber == getRowNumFromFile) {			
			driver.quit(); 
			driver.close();
		}
	}
	@AfterTest(alwaysRun = true)
	public void closePage1(ITestResult result) throws Exception{
		System.out.println("Closing Web Page");
		Reporter.log( "Closing Web Page", true );
		
//		driver.findElement(By.id("username")).click(); 
//		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[1]/div/nav/ul/li[2]/div/ul/li/a")).click(); 
//		testLog.log(LogStatus.INFO, "result.getStatus value is : " + result.getStatus());
//		switch (result.getStatus()) {        
//		case ITestResult.SKIP:
//			testLog.log(LogStatus.SKIP, "Test SKIP");
//			break;
//		case ITestResult.SUCCESS:
//			testLog.log(LogStatus.PASS, "Test is successul");
//			break;
//		case ITestResult.FAILURE:
////			String capScreenShootPath = SeleniumUtils.getScreenshot(driver);
//			testLog.log(LogStatus.FAIL, "Test Failed !!! ");
////			testLog.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
////			testLog.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + testLog.addBase64ScreenShot(capScreenShootPath));
//			break;
//		}
//
//
//		driver.quit(); 
//		driver.close();
	}
}