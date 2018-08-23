package com.verifone.pages.cgPages;
//"http://test.cgateway-portal.verifone.com/"

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;

import static com.verifone.utils.Assertions.assertTextEqual;

/**
 * This testLog check Application mandatory fields: Appl. ID and Version.
 * Verify error message displayed in different cases of empty mandatory fields,
 * wrong input type data, value not in allowed range, value not exists, long string value etc.
 *
 * @authors Yana Fridman, Fred Shniper
 */

public class CGApplicationPage extends BasePage {
    private final static String url = BaseTest.envConfig.getWebUrl();
    private final static String title = "Commerce gateway portal";

    private By addBtn = By.xpath("//*[@id=\"applications\"]/div[2]/a[1]");
    private By saveBtn = By.xpath("//*[@id=\"applications\"]/div[2]/a[3]");
    private By cancelBtn = By.xpath("//*[@id=\"applications\"]/div[2]/a[2]");
    private By updateBtn = By.xpath("//*[@id=\"applications\"]/div[2]/a[3]");
    private By deleteBtn = By.xpath("//div[@id='applications']/div[4]/table/tbody/tr/td[13]/a/span"); // "//td[@id='applications_active_cell']/a/span";
    private By filterXPath = By.xpath("//div[@id='applications']/div[3]/div/table/thead/tr/th[2]/a/span/img");
    private By appIdField = By.xpath("(//*[@role=\"gridcell\"])[2]");
    private By filterCSS = By.cssSelector("input.k-textbox");
    private By filterInput = By.xpath("(//*[@class=\"k-textbox\"])");
    private By filterBtn = By.xpath("(//*[@class=\"k-button k-primary\"])");
    private By appID = By.id("appID");
    private By versionId = By.id("version");
    private By appName = By.xpath("(//input[@type='text'])[5]");
    private By appStatus = By.xpath("(//input[@type='text'])[7]");
    private By appAccess = By.xpath("(//input[@type='text'])[8]");
    private By maxRequestApp = By.id("throttlingMaxRequestCount");
    private By applicatiobBtn = By.linkText("Applications");
    private By fieldRequired = By.xpath("//*[@class=\"ui red pointing prompt label transition visible vf\"]");
    private String Messagetext = "";
    final String xlsxFile = System.getProperty("user.dir") + "\\src\\testLog\\com.verifone.infra.resources\\applicationsInputValidation.xls";
//	private Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));

    public CGApplicationPage() {
        super(url, title);
//		navigate();
    }

    public void nevigateAppPage() {
        click(applicatiobBtn);
    }

    public String getRandomName() {
        return RandomStringUtils.randomAlphanumeric(8).toUpperCase();
    }


    public void clickOnAddBtn() {
        click(addBtn);
    }

    public String checkFields(String applicationsID, String version, String name, String status, String access,
                              String maxRequestCount, String error, boolean normalCheck) {
        String newAppId = applicationsID.equals("99") ? " " : applicationsID + getRandomName();
        click(addBtn);
        sendKeys(appID, newAppId);
        sendKeys(versionId, version.equals("99") ? " " : version);
        sendKeys(appName, name.equals("99") ? " " : name);
        sendKeys(appStatus, status.equals("99") ? " " : status);
        sendKeys(appAccess, access.equals("99") ? " " : access);
        sendKeys(maxRequestApp, maxRequestCount.equals("99") ? " " : maxRequestCount);
        click(saveBtn);
        waitSimple(2000);
        if (normalCheck) {
            try {
                String errorMsg = getText(fieldRequired);
                testLog.info("TEST 1: Is error display: " + isDisplay(fieldRequired));
                assertTextEqual(true, isDisplay(fieldRequired));
                testLog.info("TEST 2: Error should equal: " + error + ", Actual text: " + errorMsg);
                assertTextEqual(error, errorMsg.replace("\n", " "));
            } catch (Exception e) {
                testLog.fail("TEST Failed : Error should equal: " + error + ", Actual text: " + e);
            }
        }
        return newAppId;
    }

    private void waitSimple(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkCancelButton() {
        click(cancelBtn);
        testLog.info("TEST: Is error display: " + isDisplay(fieldRequired));
        assertTextEqual(false, isDisplay(fieldRequired));
    }

    public void checkSaveButton(String appID) {
        waitSimple(1000);
        click(saveBtn);
        waitSimple(1000);
        click(filterXPath);
        waitSimple(1000);
        sendKeys(filterInput, appID);
        click(filterBtn);
        testLog.info("TEST: Excepted results: " + appID + " Actual results: " + getText(appIdField));
        assertTextEqual(appID, getText(appIdField));
    }
}
//}
////---------------------------------------------------
//	ExtentReports date format and path
//		Date date = new Date() ;
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
//	//ExtentReports path
//	public String reportLocation = "C:\\reportTestNgResults\\" + dateFormat.format(date)+".html";
//	//ExtentReports new instance
//
//	public ExtentReports logger = new ExtentReports(reportLocation, true);
//	ExtentTest test = logger.startTest("Verifone", "Verifone cgateway Portal Automation");
//
//
//Selenium WebDriver
//public WebDriver driver;
//
//webPortal.properties path
//public String FilePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\verifone\\tests\\webPortal.properties";
//public Properties prop = new Properties();
//
//	Integer rowNumber = 0;
//	Integer getRowNumFromFile = 0;
//	final String xlsxFile = System.getProperty("user.dir") + "\\src\\testLog\\com.verifone.infra.resources\\applicationsInputValidation.xls";
//
//
//	@Parameters({"env", "urlDev", "urlTest", "urlStaging1", "urlProduction", "browserType"})
//	@BeforeTest
///**
// * Login to portal and navigate to Application page
//// */
//	public void startBrowser(String env, String urlDev, String urlTest,
//							 String urlStaging1, String urlProduction, String browserType) throws Exception {
//
////	//webPortal.properties
////	FileInputStream ip = new FileInputStream(FilePath);
////	prop.load(ip);
////	String userName = prop.getProperty("user_id");
////	String userPassword = prop.getProperty("password_id");
//
//		// starting testLog
//
////	try {
////		driver = SeleniumUtils.setBrowser(browserType);
////	} catch (Exception e) {
////		// TODO Auto-generated catch block
////		e.printStackTrace();
////	}
////
////	SeleniumUtils.setEnv(env, urlDev, urlTest, urlStaging1, urlProduction, test);
//
////		LOG-IN PORTAL AND NAVIGATE TO APPLICATION PAGE
////	LoginCGPortal.LoginCGPortal(driver, userName, userPassword, true);
//
////		Navigate to Application Page
////	driver.findElement(By.linkText("Applications")).click();
//		//Get number of Rows from Data driven
//		getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "A_MandatoryFields");
//		System.out.println(getRowNumFromFile);
//		System.out.println(xlsxFile);
//
//	}
//
////      Data Provider
//
//	@SuppressWarnings("deprecation")
//	@DataProvider(name = "A_MandatoryFields")
//	public Object[][] dataSupplierLoginData() throws Exception {
//		Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "A_MandatoryFields");
//		return arrayObject;
//	}
//}
//@Test(dataProvider = "A_MandatoryFields", groups = { "cgateway-portal" })
//
//public void CGApplicationPageMandatoryFields(String applicationsID, String Version, String Name, String Status, String Access, String MaxRequestCount, String msgFieldReqTxtCss, String Error) throws Exception {
//	String addBtn = "//*[@id=\"applications\"]/div[2]/a[1]";
//	String cancelBtn = "//*[@id=\"applications\"]/div[2]/a[2]";
//	String updateBtn = "//*[@id=\"applications\"]/div[2]/a[3]";
//	String deleteBtn = "//div[@id='applications']/div[4]/table/tbody/tr/td[13]/a/span";
//	String filterXPath = "//div[@id='applications']/div[3]/div/table/thead/tr/th[2]/a/span/img";
//	String filterCSS = "input.k-textbox";
//	String appID = "appID";
//	String versionId = "version";
//	String appName = "(//input[@type='text'])[5]";
//	String appStatus = "(//input[@type='text'])[7]";
//	String appAccess = "(//input[@type='text'])[8]";
//	String MaxRequestCount_id = "throttlingMaxRequestCount";
//	String Messagetext = "";
//	Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));
////
////		Get number of Rows from Data driven
//	getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "MandatoryFields");
////
////
//	WebDriverWait wait = new WebDriverWait(driver, timeOut);
//	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
////
//	String capScreenShootPath;
//
//	boolean currentResult;
//	boolean b;
//
//	test.log(LogStatus.INFO, "******************************** @testLog: MandatoryFields **************************************");
//	test.log(LogStatus.INFO, "**************************** Start current Row number: " + Integer.toString(rowNumber+1) + " **********************************");
//
////	Click on ADD button
//	currentResult = ApplicationsPage.buttonExists(driver, "Add", addBtn, timeOut);
//	System.out.println("Add button found: " + currentResult);
//	currentResult = ApplicationsPage.buttonClick(driver, "Add", addBtn, timeOut);
//	System.out.println("Add button click: " + currentResult);
//
//	Assert.assertTrue(currentResult, "Add button click failed!");
//	test.log(LogStatus.INFO, "Add button click: " + currentResult  + " <span class='label info'>info</span>");
//
//
////	Fill ApplicationID field
//	if (applicationsID.equals("99")) {
//		applicationsID="";
//	}
//	currentResult = ApplicationsPage.textboxInput(driver, "ApplicationID", appID, applicationsID, timeOut);
//	System.out.println("Input Applications ID = " + applicationsID + " " + currentResult);
//
//	Assert.assertTrue(currentResult, "Type to ApplicationID field failed!");
//	test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + ": " + currentResult + " <span class='label info'>info</span>");
//
////	Fill Version field
//	if (Version.equals("99")) {
//		Version="";
//	}
//	currentResult = ApplicationsPage.textboxInput(driver, "Version", versionId, Version, timeOut);
//	System.out.println("Input Version = " + Version + currentResult);
//
//	Assert.assertTrue(currentResult, "Type to Version field failed!");
//	test.log(LogStatus.INFO, "Input Version = " + Version + ": " + currentResult + " <span class='label info'>info</span>");
//
//
////	Fill Status field
//	if (Status.equals("99")) {
//		Status="";
//		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Status", appStatus, Status, timeOut);
//		System.out.println("Input Status = " + Status + currentResult);
//
//		Assert.assertTrue(currentResult, "Type to Status field failed!");
//		test.log(LogStatus.INFO, "Input Status = " + Status + ": " + currentResult + " <span class='label info'>info</span>");
//	}
//
//
////	Fill Throttling Max Request Count field
//
//		if (MaxRequestCount.equals("99")) {
//			MaxRequestCount = "";
//		}
//		if (!MaxRequestCount.equals("1500")) {
//			currentResult = ApplicationsPage.textboxInput(driver, "MaxRequestCount", MaxRequestCount_id, MaxRequestCount, timeOut);
//			System.out.println("Input MaxRequestCount = " + MaxRequestCount + currentResult + " <span class='label info'>info</span>");
//
//			Assert.assertTrue(currentResult, "Type to Throttling Max Request Count field failed!");
//			test.log(LogStatus.INFO, "Input MaxRequestCount = " + MaxRequestCount + ": " + currentResult + " <span class='label info'>info</span>");
//		}
//
//
////	Click on UPDATE button
//	currentResult = ApplicationsPage.buttonClick(driver, "Update", updateBtn, timeOut);
//	System.out.println("Update button click: " + currentResult);
//
//	Assert.assertTrue(currentResult, "Update button click failed!");
//	test.log(LogStatus.INFO, "Update button click: " + currentResult + " <span class='label info'>info</span>");
//
////	Verify Expected error displayed
//	Messagetext = ErrorMsgs.msgFieldReqTxt(driver, msgFieldReqTxtCss, timeOut);
//	System.out.println("Error message text: " + Messagetext);
//	if(!Messagetext.equals(Error))  {
//		capScreenShootPath = SeleniumUtils.getScreenshot();
//		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//		test.log(LogStatus.FAIL, "Error message text: " + Messagetext + ". Expected: " + Error + " <span class='label failure'>fail</span>");
//	} else {
//		test.log(LogStatus.PASS, "Expected Error got: " + Messagetext + " <span class='label success'>success</span>");
//	}
//	Assert.assertEquals(Messagetext, Error);
//
////	Click on CANCEL button
//	if(!Messagetext.equals("Succeeded.")) {
//		currentResult = ApplicationsPage.buttonClick(driver, "Cancel", cancelBtn, timeOut);
//		System.out.println("Cancel button click: " + currentResult);
//		Assert.assertTrue(currentResult, "Cancel button click failed!");
//		test.log(LogStatus.INFO, "Cancel button click: " + currentResult + " <span class='label info'>info</span>");
//	}
//	else {
//
////  Filter rows by ApplicationID = applicationsID
//		try {
//			driver.findElement(By.cssSelector(msgFieldReqTxtCss)).click();
//		}catch(Exception e){
//			System.out.println("Message not found!");
//			test.log(LogStatus.INFO, "Message not found!" + " <span class='label failure'>fail</span>");
//			capScreenShootPath = SeleniumUtils.getScreenshot();
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//		}
//		currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
//		Assert.assertTrue(currentResult, "Filter icon click failed!");
//		System.out.println("Filter icon click: " + currentResult);
//		test.log(LogStatus.INFO, "Filter icon click: " + currentResult + " <span class='label info'>info</span>");
//		System.out.println("Input Applications ID = " + applicationsID);
//		test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + " <span class='label info'>info</span>");
//		currentResult = ApplicationsPage.filterTypeSearch(driver, filterCSS, applicationsID, timeOut);
//		Assert.assertTrue(currentResult, "Filter Search action failed!");
//
////		Delete Added entry
//		String alertMessage="";
//		Alert alert = null;
//
//		if (currentResult == true) {
//			Thread.sleep(timeOut);
//			b = ApplicationsPage.buttonClick(driver, "Delete", deleteBtn, timeOut);
//			System.out.println("Delete button click: " + b);
//			Assert.assertTrue(b, "Delete button click failed!");
//			test.log(LogStatus.INFO, "Delete button click: " + b + " <span class='label info'>info</span>");
//			alert = driver.switchTo().alert();
//			alertMessage= driver.switchTo().alert().getText();
//			if (alertMessage.contains("delete this record")) {
//				alert.accept();
//				driver.findElement(By.cssSelector(".toast-successIcon")).click();
//				currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
//				Assert.assertTrue(currentResult, "Filter icon click failed!");
//				currentResult = ApplicationsPage.filterReset(driver, filterCSS, timeOut);
//				Assert.assertTrue(currentResult, "Filter Reset action failed!");
//				Thread.sleep(timeOut);
//			}
//			else {
//				System.out.println("Got incorrect alert: " + alertMessage);
//				capScreenShootPath = SeleniumUtils.getScreenshot();
//				test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//				test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//				test.log(LogStatus.FAIL, "Got incorrect alert: " + alertMessage + " <span class='label failure'>fail</span>");
//			}
//
//		}
//		else {
//			System.out.println("Filter fail!");
//			Assert.assertEquals(currentResult, true);
//			capScreenShootPath = SeleniumUtils.getScreenshot();
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.FAIL, "Filter fail!" + " <span class='label failure'>fail</span>");
//		}
//	}
//
//	System.out.println("-------------------------------------------------------------------------");
//	test.log(LogStatus.INFO, "**************************** End current Row number: "  + Integer.toString(rowNumber+1) + "***********************************");
//
//	}
////Data Provider
//
//@DataProvider(name = "B_TypeValidation")
//public Object[][] dataSupplierLoginData1() throws Exception {
//Object[][] arrayObject1 = DataDrivenUtils.getExcelData(xlsxFile, "B_TypeValidation");
//return arrayObject1;
//}
//
//@SuppressWarnings("deprecation")
//@Test(dataProvider = "B_TypeValidation", groups = { "cgateway-portal" })
//
//public void CGApplicationPageTypeValidation(String applicationsID, String Version, String Name, String Status, String Access, String MaxRequestCount, String msgFieldReqTxtCss, String Error) throws Exception {
//	String addBtn = "//*[@id=\"applications\"]/div[2]/a[1]";
//	String cancelBtn = "//*[@id=\"applications\"]/div[2]/a[2]";
//	String updateBtn = "//*[@id=\"applications\"]/div[2]/a[3]";
//	String deleteBtn = "//div[@id='applications']/div[4]/table/tbody/tr/td[13]/a/span"; // "//td[@id='applications_active_cell']/a/span";
//	String filterXPath = "//div[@id='applications']/div[3]/div/table/thead/tr/th[2]/a/span/img";
//	String filterCSS = "input.k-textbox";
//	String appID = "appID";
//	String versionId = "version";
//	String appName = "(//input[@type='text'])[5]";
//	String appStatus = "(//input[@type='text'])[7]";
//	String appAccess = "(//input[@type='text'])[8]";
//	String MaxRequestCount_id = "throttlingMaxRequestCount";
//	String Messagetext = "";
//	Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));
//
//	WebDriverWait wait = new WebDriverWait(driver, timeOut);
//	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
//
//	String capScreenShootPath;
//
//	boolean currentResult;
//	boolean b;
//	test.log(LogStatus.INFO, "******************************** @testLog: TypeValidation **************************************");
//	test.log(LogStatus.INFO, "**************************** Start current Row number: " + Integer.toString(rowNumber+1) + " **********************************");
//
////	Click on ADD button
//	currentResult = ApplicationsPage.buttonExists(driver, "Add", addBtn, timeOut);
//	System.out.println("Add button found: " + currentResult);
//	currentResult = ApplicationsPage.buttonClick(driver, "Add", addBtn, timeOut);
//	System.out.println("Add button click: " + currentResult);
//
//	Assert.assertTrue(currentResult, "Add button click failed!");
//	test.log(LogStatus.INFO, "Add button click: " + currentResult + " <span class='label info'>info</span>");
//
//
////	Fill ApplicationID field
//
//	currentResult = ApplicationsPage.textboxInput(driver, "ApplicationID", appID, applicationsID, timeOut);
//	System.out.println("Input Applications ID = " + applicationsID + " " + currentResult);
//
//	Assert.assertTrue(currentResult, "Type to ApplicationID field failed!");
//	test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + ": " + currentResult + " <span class='label info'>info</span>");
//
////	Fill Version field
//
//	currentResult = ApplicationsPage.textboxInput(driver, "Version", versionId, Version, timeOut);
//	System.out.println("Input Version = " + Version + currentResult);
//
//	Assert.assertTrue(currentResult, "Type to Version field failed!");
//	test.log(LogStatus.INFO, "Input Version = " + Version + ": " + currentResult + " <span class='label info'>info</span>");
//
////	Fill Name field
//
//	currentResult = ApplicationsPage.textboxInputXPATH(driver, "Name", appName, Name, timeOut);
//	System.out.println("Input Name = " + Name + currentResult);
//
//	Assert.assertTrue(currentResult, "Type to Name field failed!");
//	test.log(LogStatus.INFO, "Input Name = " + Name + ": " + currentResult + " <span class='label info'>info</span>");
//
////	Fill Status field
//
//	if (!Status.equals("Active")) {
//		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Status", appStatus, Status, timeOut);
//		System.out.println("Input Status = " + Status + currentResult);
//
//		Assert.assertTrue(currentResult, "Type to Status field failed!");
//		test.log(LogStatus.INFO, "Input Status = " + Status + ": " + currentResult + " <span class='label info'>info</span>");
//	}
//
//
////	Fill Throttling Max Request Count field
//
//		if (!MaxRequestCount.equals("1500")) {
//			currentResult = ApplicationsPage.textboxInput(driver, "MaxRequestCount", MaxRequestCount_id, MaxRequestCount, timeOut);
//			System.out.println("Input MaxRequestCount = " + MaxRequestCount + currentResult + " <span class='label info'>info</span>");
//
//			Assert.assertTrue(currentResult, "Type to Throttling Max Request Count field failed!");
//			test.log(LogStatus.INFO, "Input MaxRequestCount = " + MaxRequestCount + ": " + currentResult + " <span class='label info'>info</span>");
//		}
//
//
//
////	Click on UPDATE button
//	currentResult = ApplicationsPage.buttonClick(driver, "Update", updateBtn, timeOut);
//	System.out.println("Update button click: " + currentResult + " <span class='label info'>info</span>");
//
//	Assert.assertTrue(currentResult, "Update button click failed!");
//	test.log(LogStatus.INFO, "Update button click: " + currentResult + " <span class='label info'>info</span>");
//
////	Verify Expected error displayed
//	Messagetext = ErrorMsgs.msgFieldReqTxt(driver, msgFieldReqTxtCss, timeOut);
//	System.out.println("Error message text: " + Messagetext);
//	if(!Messagetext.equals(Error))  {
//		capScreenShootPath = SeleniumUtils.getScreenshot();
//		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//		test.log(LogStatus.FAIL, "Error message text: " + Messagetext + ". Expected: " + Error + " <span class='label failure'>fail</span>");
//	} else {
//		test.log(LogStatus.PASS, "Expected Error got: " + Messagetext + " <span class='label success'>success</span>");
//	}
//	Assert.assertEquals(Messagetext, Error);
//
////	Click on CANCEL button
//	if(!Messagetext.equals("Succeeded.")) {
//		currentResult = ApplicationsPage.buttonClick(driver, "Cancel", cancelBtn, timeOut);
//		System.out.println("Cancel button click: " + currentResult);
//		Assert.assertTrue(currentResult, "Cancel button click failed!");
//		test.log(LogStatus.INFO, "Cancel button click: " + currentResult + " <span class='label info'>info</span>");
//	}
//	else {
//
////  Filter rows by ApplicationID = applicationsID
//		try {
//			driver.findElement(By.cssSelector(msgFieldReqTxtCss)).click();
//		}catch(Exception e){
//			System.out.println("Message not found!");
//			test.log(LogStatus.INFO, "Message not found!" + " <span class='label failure'>fail</span>");
//			capScreenShootPath = SeleniumUtils.getScreenshot();
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//		}
//		currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
//		Assert.assertTrue(currentResult, "Filter icon click failed!");
//		System.out.println("Filter icon click: " + currentResult);
//		test.log(LogStatus.INFO, "Filter icon click: " + currentResult + " <span class='label info'>info</span>");
//		System.out.println("Input Applications ID = " + applicationsID);
//		test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + " <span class='label info'>info</span>");
//		currentResult = ApplicationsPage.filterTypeSearch(driver, filterCSS, applicationsID, timeOut);
//		Assert.assertTrue(currentResult, "Filter Search action failed!");
//
////		Delete Added entry
//		String alertMessage="";
//		Alert alert = null;
//
//		if (currentResult == true) {
//			Thread.sleep(timeOut);
//			b = ApplicationsPage.buttonClick(driver, "Delete", deleteBtn, timeOut);
//			System.out.println("Delete button click: " + b);
//			Assert.assertTrue(b, "Delete button click failed!");
//			test.log(LogStatus.INFO, "Delete button click: " + b + " <span class='label info'>info</span>");
//			alert = driver.switchTo().alert();
//			alertMessage= driver.switchTo().alert().getText();
//			if (alertMessage.contains("delete this record")) {
//				alert.accept();
//				driver.findElement(By.cssSelector(".toast-successIcon")).click();
//				currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
//				Assert.assertTrue(currentResult, "Filter icon click failed!");
//				currentResult = ApplicationsPage.filterReset(driver, filterCSS, timeOut);
//				Assert.assertTrue(currentResult, "Filter Reset action failed!");
//				Thread.sleep(timeOut);
//			}
//			else {
//				System.out.println("Got incorrect alert: " + alertMessage);
//				capScreenShootPath = SeleniumUtils.getScreenshot();
//				test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//				test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//				test.log(LogStatus.FAIL, "Got incorrect alert: " + alertMessage + " <span class='label failure'>fail</span>");
//			}
//
//		}
//		else {
//			System.out.println("Filter fail!");
//			Assert.assertEquals(currentResult, true);
//			capScreenShootPath = SeleniumUtils.getScreenshot();
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.FAIL, "Filter fail!" + " <span class='label failure'>fail</span>");
//		}
//	}
//
//	System.out.println("-------------------------------------------------------------------------");
//	test.log(LogStatus.INFO, "**************************** End current Row number: "  + Integer.toString(rowNumber+1) + "***********************************");
//
//	}
//
//
////Data Provider
//@SuppressWarnings("deprecation")
//@DataProvider(name = "C_ErrorHandling")
//public Object[][] dataSupplierLoginData2() throws Exception {
//Object[][] arrayObject2 = DataDrivenUtils.getExcelData(xlsxFile, "C_ErrorHandling");
//return arrayObject2;
//}
//
//@Test(dataProvider = "C_ErrorHandling", groups = { "cgateway-portal" })
//
//public void CGApplicationPageErrorHandling(String applicationsID, String Version, String Name, String Status, String Access, String MaxRequestCount, String msgFieldReqTxtCss, String Error) throws Exception {
//String addBtn = "//*[@id=\"applications\"]/div[2]/a[1]";
//String cancelBtn = "//*[@id=\"applications\"]/div[2]/a[2]";
//String updateBtn = "//*[@id=\"applications\"]/div[2]/a[3]";
//String deleteBtn = "//div[@id='applications']/div[4]/table/tbody/tr/td[13]/a/span"; // "//td[@id='applications_active_cell']/a/span";
//String filterXPath = "//div[@id='applications']/div[3]/div/table/thead/tr/th[2]/a/span/img";
//String filterCSS = "input.k-textbox";
//String appID = "appID";
//String versionId = "version";
//String appName = "(//input[@type='text'])[5]";
//String appStatus = "(//input[@type='text'])[7]";
//String appAccess = "(//input[@type='text'])[8]";
//String MaxRequestCount_id = "throttlingMaxRequestCount";
//String Messagetext = "";
//Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));
//
//WebDriverWait wait = new WebDriverWait(driver, timeOut);
//driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
//
//String capScreenShootPath;
//
//boolean currentResult;
//boolean b;
//
//test.log(LogStatus.INFO, "******************************** @testLog: ErrorHandling **************************************");
//test.log(LogStatus.INFO, "**************************** Start current Row number: " + Integer.toString(rowNumber+1) + " **********************************");
//
////Click on ADD button
//currentResult = ApplicationsPage.buttonExists(driver, "Add", addBtn, timeOut);
//System.out.println("Add button found: " + currentResult);
//currentResult = ApplicationsPage.buttonClick(driver, "Add", addBtn, timeOut);
//System.out.println("Add button click: " + currentResult);
//
//Assert.assertTrue(currentResult, "Add button click failed!");
//test.log(LogStatus.INFO, "Add button click: " + currentResult + " <span class='label info'>info</span>");
//
//
//
////Fill ApplicationID field
//if (applicationsID.equals("99")) {
//applicationsID="";
//}
//currentResult = ApplicationsPage.textboxInput(driver, "ApplicationID", appID, applicationsID, timeOut);
//System.out.println("Input Applications ID = " + applicationsID + " " + currentResult);
//
//Assert.assertTrue(currentResult, "Type to ApplicationID field failed!");
//test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + ": " + currentResult + " <span class='label info'>info</span>");
//
////Fill Version field
//if (Version.equals("99")) {
//Version="";
//}
//currentResult = ApplicationsPage.textboxInput(driver, "Version", versionId, Version, timeOut);
//System.out.println("Input Version = " + Version + currentResult);
//
//Assert.assertTrue(currentResult, "Type to Version field failed!");
//test.log(LogStatus.INFO, "Input Version = " + Version + ": " + currentResult + " <span class='label info'>info</span>");
//
////Fill Name field
//if (Name.equals("99")) {
//Name="";
//}
//currentResult = ApplicationsPage.textboxInputXPATH(driver, "Name", appName, Name, timeOut);
//System.out.println("Input Name = " + Name + currentResult);
//
//Assert.assertTrue(currentResult, "Type to Name field failed!");
//test.log(LogStatus.INFO, "Input Name = " + Name + ": " + currentResult + " <span class='label info'>info</span>");
//
////Fill Status field
//if (Status.equals("99")) {
//Status="";
//}
//
//if (!Status.equals("Active")) {
//currentResult = ApplicationsPage.textboxInputXPATH(driver, "Status", appStatus, Status, timeOut);
//System.out.println("Input Status = " + Status + currentResult);
//
//Assert.assertTrue(currentResult, "Type to Status field failed!");
//test.log(LogStatus.INFO, "Input Status = " + Status + ": " + currentResult + " <span class='label info'>info</span>");
//}
//
////Fill Throttling Access field
//if (Access.equals("99")) {
//Access="";
//}
//if (!Access.equals("Control") & !Access.equals("Allow") & !Access.equals("Deny")) {
//currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", appAccess, Access, timeOut);
//System.out.println("Input Access = " + Access + currentResult);
//
//Assert.assertTrue(currentResult, "Type to Throttling Access field failed!");
//if (Access.equals("")) {
//	test.log(LogStatus.INFO, "Input Access is empty: " + currentResult + " <span class='label info'>info</span>");
//} else {
//	test.log(LogStatus.INFO, "Input Access = " + Access + ": " + currentResult + " <span class='label info'>info</span>");
//}
//}
//
////Fill Throttling Max Request Count field
//switch (Access) {
//case "Control":
//		if (MaxRequestCount.equals("99")) {
//			MaxRequestCount = "";
//		}
//		if (!MaxRequestCount.equals("1500")) {
//			currentResult = ApplicationsPage.textboxInput(driver, "MaxRequestCount", MaxRequestCount_id, MaxRequestCount, timeOut);
//			System.out.println("Input MaxRequestCount = " + MaxRequestCount + currentResult);
//
//			Assert.assertTrue(currentResult, "Type to Throttling Max Request Count field failed!");
//			test.log(LogStatus.INFO, "Input MaxRequestCount = " + MaxRequestCount + ": " + currentResult + " <span class='label info'>info</span>");
//		}
//		break;
//case "Allow":
//		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", appAccess, Access, timeOut);
//		System.out.println("Input Access = " + Access + currentResult);
//		currentResult = ApplicationsPage.textboxEnabled(driver, "MaxRequestCount", MaxRequestCount_id, timeOut);
//		System.out.println(currentResult);
//		if(currentResult)  {
//			capScreenShootPath = SeleniumUtils.getScreenshot();
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
//			test.log(LogStatus.FAIL, "Throttling Max Request Count field expected disabled but found enabled!" + " <span class='label failure'>fail</span>");
//		} else {
//			test.log(LogStatus.PASS, "Throttling Max Request Count field disabled as expected." + " <span class='label success'>success</span>");
//		}
//		Assert.assertFalse(currentResult, "Throttling Max Request Count field expected disabled but found enabled!");
//		break;
//case "Deny":
//		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", appAccess, Access, timeOut);
//		System.out.println("Input Access = " + Access + currentResult);
//		currentResult = ApplicationsPage.textboxEnabled(driver, "MaxRequestCount", MaxRequestCount_id, timeOut);
//
//		if(currentResult)  {
//			capScreenShootPath = SeleniumUtils.getScreenshot();
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
//			test.log(LogStatus.FAIL, "Throttling Max Request Count field expected disabled but found enabled!" + " <span class='label failure'>fail</span>");
//		} else {
//			test.log(LogStatus.PASS, "Throttling Max Request Count field disabled as expected." + " <span class='label success'>success</span>");
//		}
//		Assert.assertFalse(currentResult, "Throttling Max Request Count field expected disabled but found enabled!" + " <span class='label failure'>fail</span>");
//		break;
//}
//
//
////Click on UPDATE button
//currentResult = ApplicationsPage.buttonClick(driver, "Update", updateBtn, timeOut);
//System.out.println("Update button click: " + currentResult);
//
//Assert.assertTrue(currentResult, "Update button click failed!");
//test.log(LogStatus.INFO, "Update button click: " + currentResult + " <span class='label info'>info</span>");
//
////Verify Expected error displayed
//Messagetext = ErrorMsgs.msgFieldReqTxt(driver, msgFieldReqTxtCss, timeOut);
//System.out.println("Error message text: " + Messagetext);
//if(!Messagetext.equals(Error))  {
//capScreenShootPath = SeleniumUtils.getScreenshot();
//test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
//test.log(LogStatus.FAIL, "Error message text: " + Messagetext + ". Expected: " + Error + " <span class='label failure'>fail</span>");
//} else {
//test.log(LogStatus.PASS, "Expected Error got: " + Messagetext + " <span class='label success'>success</span>");
//}
//Assert.assertEquals(Messagetext, Error);
//
////Click on CANCEL button
//if(!Messagetext.equals("Succeeded.")) {
//currentResult = ApplicationsPage.buttonClick(driver, "Cancel", cancelBtn, timeOut);
//System.out.println("Cancel button click: " + currentResult);
//Assert.assertTrue(currentResult, "Cancel button click failed!");
//test.log(LogStatus.INFO, "Cancel button click: " + currentResult + " <span class='label info'>info</span>");
//}
//else {
//
////Filter rows by ApplicationID = applicationsID
//try {
//	driver.findElement(By.cssSelector(msgFieldReqTxtCss)).click();
//}catch(Exception e){
//	System.out.println("Message not found!");
//	test.log(LogStatus.INFO, "Message not found!" + " <span class='label failure'>fail</span>");
//	capScreenShootPath = SeleniumUtils.getScreenshot();
//	test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//	test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
//}
//currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
//Assert.assertTrue(currentResult, "Filter icon click failed!");
//System.out.println("Filter icon click: " + currentResult);
//test.log(LogStatus.INFO, "Filter icon click: " + currentResult + " <span class='label info'>info</span>");
//System.out.println("Input Applications ID = " + applicationsID);
//test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + " <span class='label info'>info</span>");
//currentResult = ApplicationsPage.filterTypeSearch(driver, filterCSS, applicationsID, timeOut);
//Assert.assertTrue(currentResult, "Filter Search action failed!");
//
////Delete Added entry
//String alertMessage="";
//Alert alert = null;
//
//if (currentResult == true) {
//	Thread.sleep(timeOut);
//	b = ApplicationsPage.buttonClick(driver, "Delete", deleteBtn, timeOut);
//	System.out.println("Delete button click: " + b);
//	Assert.assertTrue(b, "Delete button click failed!");
//	test.log(LogStatus.INFO, "Delete button click: " + b + " <span class='label info'>info</span>");
//	alert = driver.switchTo().alert();
//	alertMessage= driver.switchTo().alert().getText();
//	if (alertMessage.contains("delete this record")) {
//		alert.accept();
//		driver.findElement(By.cssSelector(".toast-successIcon")).click();
//		currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
//		Assert.assertTrue(currentResult, "Filter icon click failed!");
//		currentResult = ApplicationsPage.filterReset(driver, filterCSS, timeOut);
//		Assert.assertTrue(currentResult, "Filter Reset action failed!");
//		Thread.sleep(timeOut);
//	}
//	else {
//		System.out.println("Got incorrect alert: " + alertMessage);
//		capScreenShootPath = SeleniumUtils.getScreenshot();
//		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
//		test.log(LogStatus.FAIL, "Got incorrect alert: " + alertMessage + " <span class='label failure'>fail</span>");
//	}
//
//}
//else {
//	System.out.println("Filter fail!");
//	Assert.assertEquals(currentResult, true);
//	capScreenShootPath = SeleniumUtils.getScreenshot();
//	test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//	test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
//	test.log(LogStatus.FAIL, "Filter fail!" + " <span class='label failure'>fail</span>");
//}
//}
//
//System.out.println("-------------------------------------------------------------------------");
//test.log(LogStatus.INFO, "**************************** End current Row number: "  + Integer.toString(rowNumber+1) + "***********************************");
//
//}
////Data Provider
//
//@DataProvider(name = "D_MaxLengthValue")
//public Object[][] dataSupplierLoginData3() throws Exception {
//Object[][] arrayObject3 = DataDrivenUtils.getExcelData(xlsxFile, "D_MaxLengthValue");
//return arrayObject3;
//}
//
//@Test(dataProvider = "D_MaxLengthValue", groups = { "cgateway-portal" })
//
//public void CGApplicationPageMaxLengthValue(String applicationsID, String Version, String Name, String Status, String Access, String MaxRequestCount, String msgFieldReqTxtCss, String Error) throws Exception {
//String addBtn = "//*[@id=\"applications\"]/div[2]/a[1]";
//String cancelBtn = "//*[@id=\"applications\"]/div[2]/a[2]";
//String updateBtn = "//*[@id=\"applications\"]/div[2]/a[3]";
//String deleteBtn = "//div[@id='applications']/div[4]/table/tbody/tr/td[13]/a/span"; // "//td[@id='applications_active_cell']/a/span";
//String filterXPath = "//div[@id='applications']/div[3]/div/table/thead/tr/th[2]/a/span/img";
//String filterCSS = "input.k-textbox";
//String appID = "appID";
//String versionId = "version";
//String appName = "(//input[@type='text'])[5]";
//String appStatus = "(//input[@type='text'])[7]";
//String appAccess = "(//input[@type='text'])[8]";
//String MaxRequestCount_id = "throttlingMaxRequestCount";
//String Messagetext = "";
//Integer timeOut = Integer.valueOf(prop.getProperty("time_out"));
//
//WebDriverWait wait = new WebDriverWait(driver, timeOut);
//driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
//
//String capScreenShootPath;
//
//boolean currentResult;
//boolean b;
//
//test.log(LogStatus.INFO, "******************************** @testLog: MaxLengthValue **************************************");
//test.log(LogStatus.INFO, "**************************** Start current Row number: " + Integer.toString(rowNumber+1) + " **********************************");
//
////Click on ADD button
//currentResult = ApplicationsPage.buttonExists(driver, "Add", addBtn, timeOut);
//System.out.println("Add button found: " + currentResult);
//currentResult = ApplicationsPage.buttonClick(driver, "Add", addBtn, timeOut);
//System.out.println("Add button click: " + currentResult);
//
//Assert.assertTrue(currentResult, "Add button click failed!");
//test.log(LogStatus.INFO, "Add button click: " + currentResult + " <span class='label info'>info</span>");
//
//
////Fill ApplicationID field
//
//currentResult = ApplicationsPage.textboxInput(driver, "ApplicationID", appID, applicationsID, timeOut);
//System.out.println("Input Applications ID = " + applicationsID + " " + currentResult);
//
//Assert.assertTrue(currentResult, "Type to ApplicationID field failed!");
//test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + ": " + currentResult + " <span class='label info'>info</span>");
//
////Fill Version field
//
//currentResult = ApplicationsPage.textboxInput(driver, "Version", versionId, Version, timeOut);
//System.out.println("Input Version = " + Version + currentResult);
//
//Assert.assertTrue(currentResult, "Type to Version field failed!");
//test.log(LogStatus.INFO, "Input Version = " + Version + ": " + currentResult + " <span class='label info'>info</span>");
//
////Fill Name field
//
//currentResult = ApplicationsPage.textboxInputXPATH(driver, "Name", appName, Name, timeOut);
//System.out.println("Input Name = " + Name + currentResult);
//
//Assert.assertTrue(currentResult, "Type to Name field failed!");
//test.log(LogStatus.INFO, "Input Name = " + Name + ": " + currentResult + " <span class='label info'>info</span>");
//
////Fill Status field
//
//if (!Status.equals("Active")) {
//currentResult = ApplicationsPage.textboxInputXPATH(driver, "Status", appStatus, Status, timeOut);
//System.out.println("Input Status = " + Status + currentResult);
//
//Assert.assertTrue(currentResult, "Type to Status field failed!");
//test.log(LogStatus.INFO, "Input Status = " + Status + ": " + currentResult + " <span class='label info'>info</span>");
//}
//
////Fill Throttling Access field
//
//if (!Access.equals("Control") & !Access.equals("Allow") & !Access.equals("Deny")) {
//currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", appAccess, Access, timeOut);
//System.out.println("Input Access = " + Access + currentResult);
//
//Assert.assertTrue(currentResult, "Type to Throttling Access field failed!");
//if (Access.equals("")) {
//	test.log(LogStatus.INFO, "Input Access is empty: " + currentResult + " <span class='label info'>info</span>");
//} else {
//	test.log(LogStatus.INFO, "Input Access = " + Access + ": " + currentResult + " <span class='label info'>info</span>");
//}
//}
//
////Fill Throttling Max Request Count field
//switch (Access) {
//case "Control":
//		if (MaxRequestCount.equals("99")) {
//			MaxRequestCount = "";
//		}
//		if (!MaxRequestCount.equals("1500")) {
//			currentResult = ApplicationsPage.textboxInput(driver, "MaxRequestCount", MaxRequestCount_id, MaxRequestCount, timeOut);
//			System.out.println("Input MaxRequestCount = " + MaxRequestCount + currentResult);
//
//			Assert.assertTrue(currentResult, "Type to Throttling Max Request Count field failed!");
//			test.log(LogStatus.INFO, "Input MaxRequestCount = " + MaxRequestCount + ": " + currentResult + " <span class='label info'>info</span>");
//		}
//		break;
//case "Allow":
//		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", appAccess, Access, timeOut);
//		System.out.println("Input Access = " + Access + currentResult);
//		currentResult = ApplicationsPage.textboxEnabled(driver, "MaxRequestCount", MaxRequestCount_id, timeOut);
//		System.out.println(currentResult);
//		if(currentResult)  {
//			capScreenShootPath = SeleniumUtils.getScreenshot();
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.FAIL, "Throttling Max Request Count field expected disabled but found enabled!" + " <span class='label failure'>fail</span>");
//		} else {
//			test.log(LogStatus.PASS, "Throttling Max Request Count field disabled as expected." + " <span class='label success'>success</span>");
//		}
//		Assert.assertFalse(currentResult, "Throttling Max Request Count field expected disabled but found enabled!" + " <span class='label failure'>fail</span>");
//		break;
//case "Deny":
//		currentResult = ApplicationsPage.textboxInputXPATH(driver, "Access", appAccess, Access, timeOut);
//		System.out.println("Input Access = " + Access + currentResult);
//		currentResult = ApplicationsPage.textboxEnabled(driver, "MaxRequestCount", MaxRequestCount_id, timeOut);
//
//		if(currentResult)  {
//			capScreenShootPath = SeleniumUtils.getScreenshot();
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//			test.log(LogStatus.FAIL, "Throttling Max Request Count field expected disabled but found enabled!" + " <span class='label failure'>fail</span>");
//		} else {
//			test.log(LogStatus.PASS, "Throttling Max Request Count field disabled as expected." + " <span class='label success'>success</span>");
//		}
//		Assert.assertFalse(currentResult, "Throttling Max Request Count field expected disabled but found enabled!" + " <span class='label failure'>fail</span>");
//		break;
//}
//
//
////Click on UPDATE button
//currentResult = ApplicationsPage.buttonClick(driver, "Update", updateBtn, timeOut);
//System.out.println("Update button click: " + currentResult);
//
//Assert.assertTrue(currentResult, "Update button click failed!");
//test.log(LogStatus.INFO, "Update button click: " + currentResult + " <span class='label info'>info</span>");
//
////Verify Expected error displayed
//Messagetext = ErrorMsgs.msgFieldReqTxt(driver, msgFieldReqTxtCss, timeOut);
//System.out.println("Error message text: " + Messagetext);
//if(!Messagetext.equals(Error))  {
//capScreenShootPath = SeleniumUtils.getScreenshot();
//test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label info'>info</span>");
//test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//test.log(LogStatus.FAIL, "Error message text: " + Messagetext + ". Expected: " + Error + " <span class='label failure'>fail</span>");
//} else {
//test.log(LogStatus.PASS, "Expected Error got: " + Messagetext + " <span class='label success'>success</span>");
//}
//Assert.assertEquals(Messagetext, Error);
//
////Click on CANCEL button
//if(!Messagetext.equals("Succeeded.")) {
//currentResult = ApplicationsPage.buttonClick(driver, "Cancel", cancelBtn, timeOut);
//System.out.println("Cancel button click: " + currentResult);
//Assert.assertTrue(currentResult, "Cancel button click failed!");
//test.log(LogStatus.INFO, "Cancel button click: " + currentResult + " <span class='label info'>info</span>");
//}
//else {
//
////Filter rows by ApplicationID = applicationsID
//try {
//	driver.findElement(By.cssSelector(msgFieldReqTxtCss)).click();
//}catch(Exception e){
//	System.out.println("Message not found!");
//	test.log(LogStatus.INFO, "Message not found!" + " <span class='label failure'>fail</span>");
//	capScreenShootPath = SeleniumUtils.getScreenshot();
//	test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//	test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//}
//currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
//Assert.assertTrue(currentResult, "Filter icon click failed!");
//System.out.println("Filter icon click: " + currentResult);
//test.log(LogStatus.INFO, "Filter icon click: " + currentResult + " <span class='label info'>info</span>");
//System.out.println("Input Applications ID = " + applicationsID);
//test.log(LogStatus.INFO, "Input Applications ID = " + applicationsID + " <span class='label info'>info</span>");
//currentResult = ApplicationsPage.filterTypeSearch(driver, filterCSS, applicationsID, timeOut);
//Assert.assertTrue(currentResult, "Filter Search action failed!");
//
////Delete Added entry
//String alertMessage="";
//Alert alert = null;
//
//if (currentResult == true) {
//	Thread.sleep(timeOut);
//	b = ApplicationsPage.buttonClick(driver, "Delete", deleteBtn, timeOut);
//	System.out.println("Delete button click: " + b);
//	Assert.assertTrue(b, "Delete button click failed!");
//	test.log(LogStatus.INFO, "Delete button click: " + b + " <span class='label info'>info</span>");
//	alert = driver.switchTo().alert();
//	alertMessage= driver.switchTo().alert().getText();
//	if (alertMessage.contains("delete this record")) {
//		alert.accept();
//		driver.findElement(By.cssSelector(".toast-successIcon")).click();
//		currentResult = ApplicationsPage.filterClick(driver, filterXPath, timeOut);
//		Assert.assertTrue(currentResult, "Filter icon click failed!");
//		currentResult = ApplicationsPage.filterReset(driver, filterCSS, timeOut);
//		Assert.assertTrue(currentResult, "Filter Reset action failed!");
//		Thread.sleep(timeOut);
//	}
//	else {
//		System.out.println("Got incorrect alert: " + alertMessage);
//		capScreenShootPath = SeleniumUtils.getScreenshot();
//		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//		test.log(LogStatus.FAIL, "Got incorrect alert: " + alertMessage + " <span class='label failure'>fail</span>");
//	}
//
//}
//else {
//	System.out.println("Filter fail!");
//	Assert.assertEquals(currentResult, true);
//	capScreenShootPath = SeleniumUtils.getScreenshot();
//	test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath) + " <span class='label failure'>fail</span>");
//	test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath) + " <span class='label failure'>fail</span>");
//	test.log(LogStatus.FAIL, "Filter fail!" + " <span class='label failure'>fail</span>");
//}
//}
//
//System.out.println("-------------------------------------------------------------------------");
//test.log(LogStatus.INFO, "**************************** End current Row number: "  + Integer.toString(rowNumber+1) + "***********************************");
//
//}
//
//@AfterMethod
//public void closePage(ITestResult result) throws Exception{
//	rowNumber++;
//	System.out.println("Current Row number: " + rowNumber);
////  REFRESH page in case some failure
//	if(result.getStatus() == ITestResult.FAILURE)
//    {
//		try{
//			driver.navigate().refresh();
//		}catch(Exception e){
//			   //ignore
//		}
//    }
//	logger.endTest(test);
//	logger.flush();
//	if (rowNumber == getRowNumFromFile) {
////		driver.close();
//	}
//}
//}