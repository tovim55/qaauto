package com.verifone.tests.cpTests;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.EnvConfig;
import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.LoginSSOPage;
import com.verifone.pages.cpPages.NoAccessPage;
import com.verifone.pages.cpPages.OktaLogin;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.pages.eoPages.LoginEOPortal;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import com.verifone.utils.DataDrivenUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.verifone.pages.BasePage.testLog;

import java.util.ArrayList;

public class SSOBasicFlowUI extends BaseTest {

    private final static int timeOut = 2000;
    private static Integer rowNumber = 0;
    private static Integer getRowNumFromFile = 0;
    final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\SSOBasic.xls";
    private static Boolean TestPassFlag = true;
    private static String capScreenShootPath;
//	private static String env = "qa";
	private static String EOPortalURI = "estatemanager.verifonecp.com";
	private static String EOSupportPortalURI = "estatemanager.verifonecp.com/estatesupport";
	private static String DeveloperPortalURI = "developer.verifonecp.com/home";
	private static String ulMail = "ValidEmail@getnada.com";
	private static String ulPassword = "Veri1234";
	String env = "";



    @BeforeTest
    public void startDDTest() throws Exception {
//	 		Get number of Rows from Data driven
        getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "loginRoles");
		env = envConfig.getEnv();
    }

//  Data Provider

    @DataProvider(name = "loginRoles")
    public Object[][] dataSupplierLoginData() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "loginRoles");
        return arrayObject;
    }

    @Test(enabled = true, priority = 1, testName = "EO Portal Login", description = "EO Portal Login with different roles", dataProvider = "loginRoles", groups = {"SSOBasic"}, alwaysRun = true)
    public void loginEOPortalUI(String Role, String Mail, String Pwd, String Descript) throws Exception {

//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");
        rowNumber = rowNumber + 1;
        testLog.info( "Data Driven line number: " + rowNumber);

		testLog.info(Descript + ": https://" + env + "." + EOPortalURI);
		testLog.info("User mail: " + Mail);

        testLog.info( "-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

        BasePage.driver.navigate().to("https://" + env + "." + EOPortalURI);

//		Test Login
//    	Setup Login button
    	testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

    	Thread.sleep(timeOut + 2000);
        ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(0));

//    	Compare login Title text with expected
        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
    	String tText = LoginEOPortal.loginTitle();
		TestPassFlag = Assertions.compareValue("Login to your Verifone Account", tText, "Login page: Found title:", testLog);


//    	Compare login Email text with expected
    	tText = LoginEOPortal.loginEmail();
		TestPassFlag = Assertions.compareValue("Email Address", tText, "Login page: Found Email field hint:", testLog);


//    	Compare login Password text with expected
		tText = LoginEOPortal.loginPassword();
		TestPassFlag = Assertions.compareValue("Password", tText, "Login page: Found Password field hint:", testLog);


//    	Compare login Forgot link text with expected
		tText = LoginEOPortal.loginForgotLink();
		TestPassFlag = Assertions.compareValue("Forgot Password?", tText, "Login page: Found Forgot Password link text:", testLog);


//    	Compare login button text with expected
    	tText = LoginEOPortal.loginBtnLabel();
		TestPassFlag = Assertions.compareValue("LOG IN", tText, "Login page: Found Login button label:", testLog);


//    	Click on Email field, click out, get and Compare mandatory error
        ulMail = "";
        LoginEOPortal.loginInputEmail(ulMail);
    	testLog.info( "Login page: Input Email = ' '");
    	Thread.sleep(timeOut - 1000);
    	tText = LoginEOPortal.lerrorMandatoryField();
		TestPassFlag = Assertions.compareValue("This field is required.", tText, "Login page: Found Mandatory error:", testLog);


//    	Click on Password field, click out, get and Compare mandatory error
        ulPassword = "";
        LoginEOPortal.loginInputPassword(ulPassword);
    	testLog.info( "Login page: Input Password = ' '");
    	Thread.sleep(timeOut - 1000);
    	tText = LoginEOPortal.lerrorMandatoryField();
		TestPassFlag = Assertions.compareValue("This field is required.", tText, "Login page: Found Mandatory error:", testLog);


//    	Click on Email field, input invalid email, click out, get and Compare validation error
    	ulMail = "InvalidEmail";
        LoginEOPortal.loginInputEmail(ulMail);
    	testLog.info( "Login page: Input Email = " + ulMail);
    	Thread.sleep(timeOut - 1000);
    	tText = LoginEOPortal.lerrorMandatoryField();
		TestPassFlag = Assertions.compareValue("Email has incorrect format. You can only use letters, numbers and symbols.", tText, "Login page: Found Invalid email error:", testLog);


//    	Input Not Matched Email and Password. Compare validation error
		ulMail = "ValidEmail@getnada.com";
		ulPassword = "Veri1234";
    	testLog.info( "Login page: Input Email = " + ulMail + ". Input Password = " + ulPassword);
        LoginEOPortal.loginInputEmail(ulMail);
        LoginEOPortal.loginInputPassword(ulPassword);
        LoginEOPortal.clickLoginBtn();
    	Thread.sleep(timeOut - 1000);
    	tText = LoginEOPortal.lerrorMatch();
		TestPassFlag = Assertions.compareValue("The information you've entered does not match the information we have on file.", tText, "Login page: Found Match data error:", testLog);


//    	Input Valid Email and Password. Login
    	ulMail = Mail;
    	ulPassword = Pwd;
    	testLog.info( "Login page: Input Email = " + ulMail + ". Input Password = " + ulPassword);
        LoginEOPortal.loginInputEmail(ulMail);
        if (!Role.contains("Verifone")) {
			LoginEOPortal.loginInputPassword(ulPassword);
			LoginEOPortal.clickLoginBtn();
		}
    	Thread.sleep(timeOut - 1000);

    	testLog.info( "Click Login button");

//		Home Page
        testLog.info( "---------------------------------------------------Home page----------------------------------------------------");


		switch(Role) {
			case "EO Admin":
				Thread.sleep(timeOut + 2000);
				availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
				BasePage.driver.switchTo().window(availableWindows.get(0));
//    	Search for Header
				HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
				Assert.assertTrue(HomePage.headerExists());
				HomePage.clickHeaderMenu();
				Thread.sleep(timeOut);
				Assert.assertTrue(HomePage.menuUserExists());
				Assert.assertTrue(HomePage.menuProfileExists());
				Assert.assertTrue(HomePage.menuMailerExists());
				Assert.assertTrue(HomePage.menuSponsorExists());
				Assert.assertTrue(HomePage.menuLogoutExists());
				break;
			case "EO Device and App Manager":
				Thread.sleep(timeOut + 2000);
				availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
				BasePage.driver.switchTo().window(availableWindows.get(0));
//    	Search for Header
				HomePage HomePage1 = (HomePage) PageFactory.getPage("HomePage");
				Assert.assertTrue(HomePage1.headerExists());
				HomePage1.clickHeaderMenu();
				Thread.sleep(timeOut);
//				Assert.assertFalse(HomePage1.menuUserExists());
				Assert.assertTrue(HomePage1.menuProfileExists());
//				Assert.assertFalse(HomePage1.menuMailerExists());
				Assert.assertTrue(HomePage1.menuSponsorExists());
				Assert.assertTrue(HomePage1.menuLogoutExists());
				break;
			case "EO Merchant Manager":
				Thread.sleep(timeOut + 2000);
				availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
				BasePage.driver.switchTo().window(availableWindows.get(0));
//    	Search for Header
				HomePage HomePage2 = (HomePage) PageFactory.getPage("HomePage");
				Assert.assertTrue(HomePage2.headerExists());
				HomePage2.clickHeaderMenu();
				Thread.sleep(timeOut);
//				Assert.assertFalse(HomePage2.menuUserExists());
				Assert.assertTrue(HomePage2.menuProfileExists());
				Assert.assertTrue(HomePage2.menuMailerExists());
//				Assert.assertFalse(HomePage2.menuSponsorExists());
				Assert.assertTrue(HomePage2.menuLogoutExists());
				break;

			case "Merchant":
				Thread.sleep(timeOut + 2000);
				availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
				BasePage.driver.switchTo().window(availableWindows.get(0));

//    	Search for Header
				HomePage HomePage3 = (HomePage) PageFactory.getPage("HomePage");
				Assert.assertTrue(HomePage3.headerExists());
//				Assert.assertFalse(HomePage3.menuAccountExists());

				break;

			case "Basic Developer":
			case "Dev Admin":
				availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
				BasePage.driver.switchTo().window(availableWindows.get(0));
				NoAccessPage NoAccessPage = (NoAccessPage) PageFactory.getPage("NoAccessPage");
				tText = NoAccessPage.pageText();
				TestPassFlag = Assertions.compareValue("You don't have access to the page you requested.", tText, "No Access page: Found error:", testLog);

				break;
			case "Verifone Dev Support Admin":
			case "Verifone EO Support Admin":
				availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
				BasePage.driver.switchTo().window(availableWindows.get(0));
				LoginSSOPage LoginSSOPage = (LoginSSOPage) PageFactory.getPage("LoginSSOPage");
				Assert.assertTrue(LoginSSOPage.formOKTAExists());
		}
        Assert.assertTrue(TestPassFlag);
        BasePage.driver.quit();
    }

	@BeforeTest
	public void startDDTest2() throws Exception {
//	 		Get number of Rows from Data driven
		getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "loginRoles");
		env = envConfig.getEnv();
	}

//  Data Provider

	@DataProvider(name = "loginSupportRoles")
	public Object[][] dataSupplierLoginData1() throws Exception {
		Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "loginRoles");
		return arrayObject;
	}

	@Test(enabled = true, priority = 2, testName = "EO Support Portal Login", description = "EO Support Portal Login with different roles",dataProvider = "loginSupportRoles", groups = {"SSOBasic"}, alwaysRun = true)
	public void loginEOSupportPortalUI(String Role, String Mail, String Pwd, String Descript) throws Exception {

//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");
		rowNumber = rowNumber + 1;
		testLog.info( "Data Driven line number: " + rowNumber);

		testLog.info(Descript + ": https://" + env + "." + EOPortalURI);
		testLog.info("User mail: " + Mail);

		testLog.info( "-------------------------------------------------Navigate to EO Support Portal-------------------------------------------------");

		BasePage.driver.navigate().to("https://" + env + "." + EOSupportPortalURI);

//		Test Login
//    	Setup Login button
		testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

		Thread.sleep(timeOut + 2000);
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));

//    	Compare login Title text with expected
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		String tText = LoginEOPortal.loginTitle();
		TestPassFlag = Assertions.compareValue("Login to your Verifone Account", tText, "Login page: Found title:", testLog);

//    	Compare login Email text with expected
		tText = LoginEOPortal.loginEmail();
		TestPassFlag = Assertions.compareValue("Email Address", tText, "Login page: Found Email field hint:", testLog);

//    	Compare login Password text with expected
		tText = LoginEOPortal.loginPassword();
		TestPassFlag = Assertions.compareValue("Password", tText, "Login page: Found Password field hint:", testLog);

//    	Compare login Forgot link text with expected
		tText = LoginEOPortal.loginForgotLink();
		TestPassFlag = Assertions.compareValue("Forgot Password?", tText, "Login page: Found Password field hint:", testLog);

//    	Compare login button text with expected
		tText = LoginEOPortal.loginBtnLabel();
		TestPassFlag = Assertions.compareValue("LOG IN", tText, "Login page: Found Login button label:", testLog);


//    	Input Valid Email and Password. Login

		testLog.info( "Login page: Input Email = " + Mail + ". Input Password = " + Pwd);
		LoginEOPortal.loginInputEmail(Mail);

		if (!Role.contains("Verifone")) {
			LoginEOPortal.loginInputPassword(Pwd);
			Thread.sleep(timeOut - 1000);
			LoginEOPortal.clickLoginBtn();
		}

		testLog.info( "Click Login button");

//		Home Page
		testLog.info( "---------------------------------------------------Home page----------------------------------------------------");
		switch(Role) {
			case "EO Admin":
			case "EO Device and App Manager":
			case "EO Merchant Manager":
			case "Merchant":
				Thread.sleep(timeOut+2000);
				String url = BasePage.driver.getCurrentUrl();
				TestPassFlag = Assertions.compareValue("verifonecp.com/#home", url, "User redirected to:", testLog);
				break;

			case "Basic Developer":
			case "Dev Admin":
                Thread.sleep(timeOut+2000);
				availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
				BasePage.driver.switchTo().window(availableWindows.get(0));
				NoAccessPage NoAccessPage = (NoAccessPage) PageFactory.getPage("NoAccessPage");
				tText = NoAccessPage.pageText();
				TestPassFlag = Assertions.compareValue("You don't have access to the page you requested.", tText, "No Access page: Found error:", testLog);
				break;

			case "Verifone Dev Support Admin":
                Thread.sleep(timeOut+2000);
				availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
				BasePage.driver.switchTo().window(availableWindows.get(0));
				LoginSSOPage LoginSSOPage = (LoginSSOPage) PageFactory.getPage("LoginSSOPage");
				Assert.assertTrue(LoginSSOPage.formOKTAExists());

//                LoginSSOPage.inputUserName(Mail.replace("@verifone.com",""));
//                LoginSSOPage.inputPassword(Pwd);
//                LoginSSOPage.clickSignInBtn();

//                Thread.sleep(timeOut+2000);
//                availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//                BasePage.driver.switchTo().window(availableWindows.get(0));
//                NoAccessPage NoAccessPage1 = (NoAccessPage) PageFactory.getPage("NoAccessPage");
//                tText = NoAccessPage1.pageText();
//                currentResult = tText.contains("You don't have access to the page you requested.");
//                if (currentResult == true) {
//                    testLog.pass( "No Access page: Found error: " + "You don't have access to the page you requested." + "...: Succesfull");
//                } else {
//                    TestPassFlag = false;
//                    testLog.error( "No Access page: Found error: " + tText + ". Expected: " + "You don't have access to the page you requested.");
//                    capScreenShootPath = SeleniumUtils.getScreenshot();
//                    testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//                    testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//                }
                break;

            case "Verifone EO Support Admin":
                Thread.sleep(timeOut+2000);
                availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
                BasePage.driver.switchTo().window(availableWindows.get(0));
                LoginSSOPage LoginSSOPage1 = (LoginSSOPage) PageFactory.getPage("LoginSSOPage");
                Assert.assertTrue(LoginSSOPage1.formOKTAExists());
//                LoginSSOPage1.inputUserName(Mail.replace("@verifone.com",""));
//                LoginSSOPage1.inputPassword(Pwd);
//                LoginSSOPage1.clickSignInBtn();

//                Thread.sleep(timeOut+2000);
//                String url1 = BasePage.driver.getCurrentUrl();
//                currentResult = url1.contains("verifonecp.com/estatesupport#home");
//                if (currentResult == true) {
//                    testLog.pass( "User redirected to Support Home Page Succesfull");
//                } else {
//                    TestPassFlag = false;
//                    testLog.error( "User redirected to: " + url1 + ". Expected: " + "Support Home Page");
//                    capScreenShootPath = SeleniumUtils.getScreenshot();
//                    testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//                    testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//                }

                break;

		}
		Assert.assertTrue(TestPassFlag);
		BasePage.driver.quit();


	}

    @BeforeTest
    public void startDDTest1() throws Exception {
//	 		Get number of Rows from Data driven
        getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "loginRoles");
		env = envConfig.getEnv();
    }

//  Data Provider

    @DataProvider(name = "loginDeveloperRoles")
    public Object[][] dataSupplierLoginData2() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "loginRoles");
        return arrayObject;
    }

    @Test(enabled = true, priority = 3, testName = "Developer Portal Login", description = "Developer Portal Login with different roles", dataProvider = "loginDeveloperRoles", groups = {"SSOBasic"}, alwaysRun = true)
    public void loginDeveloperPortalUI(String Role, String Mail, String Pwd, String Descript) throws Exception {

//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");
        rowNumber = rowNumber + 1;
        testLog.info( "Data Driven line number: " + rowNumber);

		testLog.info(Descript + ": https://" + env + "." + EOPortalURI);
		testLog.info("User mail: " + Mail);

        testLog.info( "-------------------------------------------------Navigate to Developer Portal-------------------------------------------------");

        BasePage.driver.navigate().to("https://" + env + "." + DeveloperPortalURI);

//		Test Login
//    	Setup Login button
        testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

        Thread.sleep(timeOut + 2000);
        ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));

//    	Compare login Title text with expected
        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        String tText = LoginEOPortal.loginTitle();
		TestPassFlag = Assertions.compareValue("Login to your Verifone Account", tText, "Login page: Found title:", testLog);

//    	Compare login Email text with expected
        tText = LoginEOPortal.loginEmail();
		TestPassFlag = Assertions.compareValue("Email Address", tText, "Login page: Found Email field hint:", testLog);

//    	Compare login Password text with expected
        tText = LoginEOPortal.loginPassword();
		TestPassFlag = Assertions.compareValue("Password", tText, "Login page: Found Password field hint:", testLog);

//    	Compare login Forgot link text with expected
        tText = LoginEOPortal.loginForgotLink();
		TestPassFlag = Assertions.compareValue("Forgot Password?", tText, "Login page: Found Password field hint:", testLog);

//    	Compare login button text with expected
        tText = LoginEOPortal.loginBtnLabel();
		TestPassFlag = Assertions.compareValue("LOG IN", tText, "Login page: Found Login button label:", testLog);


//    	Input Valid Email and Password. Login

        testLog.info( "Login page: Input Email = " + Mail + ". Input Password = " + Pwd);
        LoginEOPortal.loginInputEmail(Mail);
        if (!Role.contains("Verifone")) {
            LoginEOPortal.loginInputPassword(Pwd);
			Thread.sleep(timeOut - 1000);
			LoginEOPortal.clickLoginBtn();
        }

        testLog.info( "Click Login button");

//		Home Page
		testLog.info( "---------------------------------------------------Home page----------------------------------------------------");

        switch(Role) {
            case "EO Admin":
            case "EO Device and App Manager":
            case "EO Merchant Manager":
            case "Merchant":
                Thread.sleep(timeOut+2000);
                availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
                BasePage.driver.switchTo().window(availableWindows.get(0));
                NoAccessPage NoAccessPage = (NoAccessPage) PageFactory.getPage("NoAccessPage");
                tText = NoAccessPage.pageText();
				TestPassFlag = Assertions.compareValue("You don't have access to the page you requested.", tText, "No Access page: Found error:", testLog);
                break;

            case "Verifone EO Support Admin":
                Thread.sleep(timeOut+2000);
                availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
                BasePage.driver.switchTo().window(availableWindows.get(0));
                LoginSSOPage LoginSSOPage = (LoginSSOPage) PageFactory.getPage("LoginSSOPage");
                Assert.assertTrue(LoginSSOPage.formOKTAExists());
//                LoginSSOPage.inputUserName(Mail.replace("@verifone.com",""));
//                LoginSSOPage.inputPassword(Pwd);
//                LoginSSOPage.clickSignInBtn();
//
//                Thread.sleep(timeOut+2000);
//                availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//                BasePage.driver.switchTo().window(availableWindows.get(0));
//                NoAccessPage NoAccessPage1 = (NoAccessPage) PageFactory.getPage("NoAccessPage");
//                tText = NoAccessPage1.pageText();
//                currentResult = tText.contains("You don't have access to the page you requested.");
//                if (currentResult == true) {
//                    testLog.pass( "No Access page: Found error: " + "You don't have access to the page you requested." + "...: Succesfull");
//                } else {
//                    TestPassFlag = false;
//                    testLog.error( "No Access page: Found error: " + tText + ". Expected: " + "You don't have access to the page you requested.");
//                    capScreenShootPath = SeleniumUtils.getScreenshot();
//                    testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//                    testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//                }
                break;
            case "Basic Developer":
            case "Dev Admin":
                Thread.sleep(timeOut+2000);
                String url = BasePage.driver.getCurrentUrl();
				TestPassFlag = Assertions.compareValue("verifonecp.com/home", url, "User redirected to:", testLog);
                Thread.sleep(timeOut);
                availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
                BasePage.driver.switchTo().window(availableWindows.get(0));
//    	Search for Header
                HomePage HomePage3 = (HomePage) PageFactory.getPage("HomePage");
                Assert.assertTrue(HomePage3.headerExists());
                HomePage3.clickHeaderMenu();
                Thread.sleep(timeOut);
                Assert.assertTrue(HomePage3.menuUserExists());
                Assert.assertTrue(HomePage3.menuProfileExists());
                Assert.assertTrue(HomePage3.menuCompanyExists());
                Assert.assertTrue(HomePage3.menuLogoutExists());
                break;
            case "Verifone Dev Support Admin":
                Thread.sleep(timeOut+2000);
                availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
                BasePage.driver.switchTo().window(availableWindows.get(0));
                LoginSSOPage LoginSSOPage1 = (LoginSSOPage) PageFactory.getPage("LoginSSOPage");
                Assert.assertTrue(LoginSSOPage1.formOKTAExists());
//                LoginSSOPage1.inputUserName(Mail.replace("@verifone.com",""));
//                LoginSSOPage1.inputPassword(Pwd);
//                LoginSSOPage1.clickSignInBtn();
//
//                Thread.sleep(timeOut+2000);
//                String url1 = BasePage.driver.getCurrentUrl();
//                currentResult = url1.contains("verifonecp.com/developersupport#home");
//                if (currentResult == true) {
//                    testLog.pass( "User redirected to Support Home Page Succesfull");
//                } else {
//                    TestPassFlag = false;
//                    testLog.error( "User redirected to: " + url1 + ". Expected: " + "Support Home Page");
//                    capScreenShootPath = SeleniumUtils.getScreenshot();
//                    testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//                    testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//                }

                break;
        }
		Assert.assertTrue(TestPassFlag);
		BasePage.driver.quit();
        }
    }