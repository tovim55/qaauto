package com.verifone.tests.oldTests;
//	"http://test.cgateway-portal.verifone.com/"

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import com.verifone.utils.DataDrivenUtils;


public class LoginDataDriven{
	//ExtentReports date format and path
	Date date = new Date() ;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
	//ExtentReports path
	public String reportLocation = "C:\\reportTestNgResults\\" + dateFormat.format(date)+".html";	
	//ExtentReports new instance
	public ExtentReports logger = new ExtentReports(reportLocation, true);		
	//ExtentReports starting testLog
	ExtentTest test = logger.startTest("Verifone", "Verifone cgateway Portal Automation");
	//Selenium WebDriver
	public WebDriver driver;
	
	//webPortal.properties path
	public String FilePath = "C:\\Users\\giorat1\\eclipse-workspace-testng-maven\\tests\\src\\main\\java\\com\\verifone\\tests\\webPortal.properties";
	public Properties prop = new Properties();

	
	@Parameters({ "env", "urlDev", "urlTest", "urlStaging1","urlProduction", "browserType" })
	@BeforeMethod
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

			
		SeleniumUtils.setEnv(env, urlDev, urlTest, urlStaging1, urlProduction, test);
	}
	

	//DataProvider for login
    @DataProvider(name = "LogInData")
    public Object[][] dataSupplierLoginData() throws Exception {
          final String xlsxFile = System.getProperty("user.dir") + "\\src\\testLog\\resources\\login.xls";
          Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "login");
          return arrayObject;
    }

    @Test(dataProvider = "LogInData", groups = { "cgateway-portal" })
	public void loginTest(String username, String pwd) throws Exception {
	
		
	//starting testLog
	logger.startTest("loginTestPoc", "Verifone cgateway-portal POC login testLog ");
	System.out.println("username:  "+ username + " password: " + pwd);
	
	
//	//webPortal.properties
//		FileInputStream ip = new FileInputStream(FilePath);
//		prop.load(ip);
	
//	String textToFind = prop.getProperty("textToFindInWebPage");
//	SeleniumUtils.findTextInPage(driver, textToFind);
//	testLog.log(LogStatus.PASS, "Step details - The text " + textToFind + " was found successfully in the Page");
	}
	
	
	
	
	@AfterMethod
	public void closePage(ITestResult result) throws Exception{
	Reporter.log( "Closing Web Page", true );

		test.log(LogStatus.INFO, "result.getStatus value is : " + result.getStatus());
		switch (result.getStatus()) {        
		case ITestResult.SKIP:
			test.log(LogStatus.SKIP, "Test SKIP");			
			break;
		case ITestResult.SUCCESS:
			test.log(LogStatus.PASS, "Test is successul");			
			break;
		case ITestResult.FAILURE:
			String capScreenShootPath = SeleniumUtils.getScreenshot();
			test.log(LogStatus.FAIL, "Test Failed !!! - Did not found text: " + prop.getProperty("pageTitleToFind") + " in title page");
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
			break;
	}
			
	//ending testLog
	logger.endTest(test);
	//writing everything to document
	logger.flush();	
	driver.quit();
	//Open ExtentReports
//	driver = SeleniumUtils.setBrowser("CHROME");
//	driver.get("file:///" + reportLocation);
	
	}
	

}
	

