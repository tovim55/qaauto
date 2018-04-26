//package com.verifone.tests;
//
//import org.testng.annotations.Test;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.HTMLReporter;
//
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeMethod;
//
//import static org.testng.Assert.assertEquals;
//
//import javax.net.ssl.SSLEngineResult.Status;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.BeforeTest;
//
//
//
//public class HtmlReports {
//
//	public static WebDriver driver;
//	public static HTMLReporter htmlReports;
//	public static ExtentReports extent;
//	public static ExtentTest test;
//	public static ExtentTest parentTest;
//	public static ExtentTest childTest;
//	String filename = System.getProperty("user.dir") + "/test-output/HtmlTestResults.html";
//	
//	@BeforeMethod
//	@BeforeTest
//	public void setUp() {
//		
//		htmlReports = new HTMLReporter(filename);
//		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/ExtentReport.html", true); //true - overwrite if exists
//		extent.attachReporter(htmlReports);
//		htmlReports.config().setReportName("Regression Testing");
//		htmlReports.config().setTheme(Theme.STANDARD);
//		htmlReports.config().setTestViewCharLocation(CharLocation.TOP);
//		htmlReports.config().setDocumentTitle("HtmlReportsTestResults");
//		
//	}
//	
//	@Test(priority = 0)
//	public void openBrowser() throws Exception{
//		parentTest = extent.createTest("Open Chrome");
//		childTest = parentTest.createNode("Open URL");
//		childTest.log(status.PASS, MarkupHelper.createLabel("Browser is opens"), ExtentColor.BLUE);
//		
//		driver.navigate().to("http://dev.cgateway-portal.verifone.com");
//		childTest.log(Status.PASS, MarkupHelper.createLabel("URL is open", ExtentColor.BLUE));
//	}
//	
//	@Test(priority = 1)
//	public void verifyPageTitle() throws Exception{
//		
//		childTest = parentTest.createNode("Verify open Page");
//		childTest.info("Get page title");
//		String actualPageTitle = driver.getTitle();
//		assertEquals(actualPageTitle, "googles");
//	}
//	
//	@Test(priority = 2)
//	public void verifyTitle() throws Exception{
//		
//		test = childTest.createNode("Verify Open Page Title");
//		test.info("Get the page title");
//		//String actualPageTitle = driver.getTitle();
//		
//		//assertEquals(actualPageTitle, "googles");
//	}
//	
//	@AfterTest
//	public void tearDown() {
//		extent.flush();
//		System.out.println("Test method exec is done");
//	}
//	@AfterMethod
//	public void checkResults(ITestResult testResults) {
//		if(testResults.getStatus() == ITestResult.FAILURE) {
//			childTest.log(Status.FAIL, MarkupHelper.createLabel("Test failed.Reason below"), ExtentColor.RED);
//			childTest.log(Status.FAIL, testResults.getThrowable());
//		} else if (testResults.getStatus() == ITestResult.SKIP) {
//			childTest.log(Status.SKIP, testResults.getThrowable());
//		} else {
//			childTest.log(Status.PASS, "Test Case is Passed");
//		}
//			
//	}
//	
//}
