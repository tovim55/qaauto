package com.verifone.tests.oldTests;
import com.verifone.pages.eoPages.HomePage;
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
import com.verifone.utils.DevPortal.DevPortal;
import com.verifone.utils.General.LoginDevPortal;

/**
* This testLog check Application mandatory fields: Appl. ID and Version.
* Verify error message displayed in different cases of empty mandatory fields,
* wrong input type data, value not in allowed range, value not exists, long string value etc.
* @authors Yana Fridman, Fred Shniper
*/

public class SignUpTest{
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
public String FilePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\verifone\\tests\\loginAndCheck.properties";
public Properties prop = new Properties();

Integer rowNumber=0;
Integer getRowNumFromFile = 0;
final String xlsxFile = System.getProperty("user.dir") + "\\src\\testLog\\com.verifone.infra.resources\\loginAndCheck.xls";


@Parameters({ "env", "urlDev", "urlTest", "urlStaging1","urlProduction", "browserType" })
@BeforeTest
/**
* Login to portal 
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
		driver = new HomePage().getDriver();;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	SeleniumUtils.setEnv(env, urlDev, urlTest, urlStaging1, urlProduction, test);	

}
@DataProvider(name = "signUp")
public Object[][] dataSupplierLoginData() throws Exception {
	Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "signUp");
	return arrayObject;
}

@Test(dataProvider = "signUp", groups = { "developer-portal" })

public void SignUpTest(String fName, String lName, String eMail, String uPassword) throws Exception {
	
	String SignUpButton_XPath = "(//*[@class='btn btn-default signup' and @data-text='Sign up'])";

	Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));
	
	WebDriverWait wait = new WebDriverWait(driver, timeOut);
	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	

	boolean currentResult;

//  Navigate to Developer Portal
	driver.get("https://qa.developer.verifonecp.com/docs/overview/get-started");
	
//	Click on Sign Up button
	currentResult = DevPortal.buttonClick(driver, SignUpButton_XPath, timeOut*100);
	System.out.println("Sign Up button click: " + currentResult);
	
//	Fill data on SingUp page
//	currentResult = LoginDevPortal.SignUpDevPortal(driver, "MerchantTestFour", "TestFour", "MerchantTest4@getnada.com", "Veri1234");
	currentResult = LoginDevPortal.SignUpDevPortal(driver, fName, lName, eMail, uPassword);
	System.out.println("Sign Up button click: " + currentResult);

//  Get Page text
	String signUpResult = LoginDevPortal.SignUpDevPortalResult(driver);
	System.out.println("Sign Up Result: " + signUpResult);
	}
}