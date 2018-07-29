package com.verifone.tests.cpTests;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.entities.EntitiesFactory;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.verifone.pages.cpPages.AcceptMerchantAgreementPage;
import com.verifone.pages.cpPages.EmailConfirmPage;
import com.verifone.pages.cpPages.ForgotPasswordPage;
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.cpPages.MerchantViewPage;
import com.verifone.pages.cpPages.SetupPasswordPage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import com.verifone.utils.apiClient.createMerchant.CreateMerchantDE;
import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
import com.verifone.utils.apiClient.getToken.GetTokenApi;

import org.testng.Assert;
import org.testng.annotations.*;

import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;

import static com.verifone.pages.BasePage.testLog;

public class MerchantForgotPasswordUI extends BaseTest {
//    private final static String text = "To create your Verifone Account";
//    private final static String errorFormat = "Use at least eight characters";
//    private final static String errorAccept = "You are required to accept our Terms and Conditions and Privacy Policy.";
//    private final static String errorMatch = "Password and Confirm Password must match.";
//    private final static String textTOS = "These Terms of Service for the Verifone Web Portal";
//    private final static String loginTitle = "Login to your Verifone Account";
//    private final static String loginEmail = "Email Address";
//    private final static String loginPassword = "Password";
//    private final static String loginForgotLink = "Forgot Password?";
//    private final static String loginBtnLabel = "LOG IN";
//    private final static String ForgotPageTitle = "Forgot your password?";
//    private final static String ForgotPageText = "Enter the email address associated with your account and";
//    private final static String ForgotPageMailLabelText = "Email";
//    private final static String ForgotPageLnkLoginText = "Login";
//    private final static String ForgotPageBtnSendText = "Send";
//    private final static String ForgotErrorEmptyText = "This field is required.";
//    private final static String ForgotErrorInvalidText = "Field Email should have valid format!";
//    private final static String EmailConfPageTitle = "Email Confirmed!";
//    private final static String EmailConfPageText = "We''ve sent password reset instructions to login into your";
//    private final static String EmailConfPageMutedText = "Note: Check your spam folder if you don''t see";

	private static String mailActivateButton = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/p[5]/a";
	private final static int timeOut = 2000;
    private static String mId = "";
    private static String ufEmail = "";
    private static Integer rowNumber=0;
    private static Integer getRowNumFromFile = 0;
	final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\merchantForgotPassword.xls";
	private static Boolean TestPassFlag = true;
	private static String capScreenShootPath;

	@BeforeTest
	public void startDDTest() throws Exception{
//	 		Get number of Rows from Data driven
		getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "merchantForgotPassword");
	}

//    Data Provider

	@DataProvider(name = "merchantForgotPassword")
	public Object[][] dataSupplierLoginData() throws Exception {
		Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "merchantForgotPassword");
		return arrayObject;
	}

	@Test(testName = "Merchant Forgot Password Test", dataProvider = "merchantForgotPassword", groups = { "Localization1" })
    public void MerchantForgotPasswordUI(String Lang, String EOAdminMail, String EOAdminPwd, String mailInvText, String actAccountBtnLabel, String setupText, String setupErrorFormat, String setupErrorAccept, String setupErrorMatch, String textTOS,
    		String loginTitle, String loginEmail, String loginPassword, String loginForgotLink, String loginBtnLabel, String ForgotPageTitle,
    		String ForgotPageText, String ForgotPageMailLabelText, String ForgotPageLnkLoginText, String ForgotPageBtnSendText,
    		String ForgotErrorEmptyText, String ForgotErrorInvalidText, String EmailConfPageTitle, String EmailConfPageText, String EmailConfPageMutedText) throws Exception {

//        starTestLog("Merchant Forgot Password Test", "Merchant Forgot Password Test");
        rowNumber = rowNumber+1;
        testLog.info( "Data Driven line number: " + rowNumber);
        testLog.info( "---------------------------------------------Create Merchant by API---------------------------------------------");

        User user = new User(EOAdminMail, EOAdminPwd);
        GetTokenApi getTokenApi = new GetTokenApi("testId");
        String accessToken = getTokenApi.getToken(user);
        GetEoadminDataApi getEoadminDataApi = new GetEoadminDataApi(accessToken,"testId");
        switch (Lang) {
	        case "DE":
	        	mId = new CreateMerchantDE(accessToken, "testId").createMerchant(getEoadminDataApi.getData());
	        	break;
        }
        System.out.println("MID: " + mId);


    	//      Navigate to Getnada

		testLog.info("-------------------------------------------------Getnada service-------------------------------------------------");
    	BasePage.driver.navigate().to("https://getnada.com/#");

    	// Click Add Inbox

    	BasePage.driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();

    	// Put email
    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
    	Thread.sleep(timeOut+2000);
    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(mId);

    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);

    	//  Accept
    	BasePage.driver.findElement(By.linkText("ACCEPT")).click();

    	testLog.info( "Create email inbox: " + mId + "getnada.com: Succesfull");

    	//  Open Email
    	Thread.sleep(timeOut);
    	BasePage.driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();

    	testLog.info( "Found Invitation mail: Succesfull");
    	//   Get email text

    	Thread.sleep(timeOut);

    	WebElement iFrame = BasePage.driver.findElement(By.id("idIframe"));
    	BasePage.driver.switchTo().frame(iFrame);
    	String mailText = BasePage.driver.getPageSource();

    	Boolean currentResult = mailText.contains(mailInvText);
    	if (currentResult == true) {
    		testLog.pass( "Invitation mail include text: " + mailInvText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Invitation mail include text: " + mailText + ". Expected: " + mailInvText);
    	}
    	System.out.println("Mail text: " + currentResult);

    	String btnText = BasePage.driver.findElement(By.xpath(mailActivateButton)).getText();
    	currentResult = btnText.contains(actAccountBtnLabel);
    	if (currentResult == true) {
    		testLog.pass( "Invitation mail Activate button text: " + actAccountBtnLabel + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Invitation mail Activate button text: " + btnText + ". Expected: " + actAccountBtnLabel);
    	}
    	BasePage.driver.findElement(By.xpath(mailActivateButton)).click();

    	testLog.info( "Click on: " + btnText + " button: Succesfull");


    	//		Setup Password
    	testLog.info( "-----------------------------------------------Setup Password page-----------------------------------------------");

    	Thread.sleep(timeOut + 2000);
    	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));

    	SetupPasswordPage SetupPasswordPage = (SetupPasswordPage) PageFactory.getPage("SetupPasswordPage");
    	String pgText = SetupPasswordPage.pageText();
    	System.out.println(pgText);
    	currentResult = pgText.contains(setupText);
    	if (currentResult == true) {
    		testLog.pass( "Found Setup page text: " + setupText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Found Setup page text: " + pgText + ". Expected: " + setupText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}
//    	System.out.println(window.navigator.language);

    	JavascriptExecutor jse = (JavascriptExecutor)BasePage.driver;
    	jse.executeScript("var language = window.navigator.userLanguage || window.navigator.language");

    	//		Input blank space as Password and ConfirmPassword

    	testLog.info( "Setup page: Input password = ' ', confirm password = ' '");
    	SetupPasswordPage.SetupPasswordPageCp(" ", " ");
    	SetupPasswordPage.clickOnSubmitBtn();
    	String errText = SetupPasswordPage.errorFormat();
    	System.out.println(errText);
    	currentResult = errText.contains(setupErrorFormat);
    	if (currentResult == true) {
    		testLog.pass( "Found Format error: " + setupErrorFormat + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Found Format error: " + errText + ". Expected: " + setupErrorFormat);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

    	//		Not agree with TOS

    	testLog.info( "Setup page: Not accept TOS");
    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
    	SetupPasswordPage.clickOnSubmitBtn();
    	errText = SetupPasswordPage.errorTOS();
    	System.out.println(errText);
    	currentResult = errText.contains(setupErrorAccept);
    	if (currentResult == true) {
    		testLog.pass( "Found missing accept TOS error: " + setupErrorAccept + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Found missing accept TOS error: " + errText + ". Expected: " + setupErrorAccept);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

    	//     Input different Password and ConfirmPassword

    	testLog.info( "Setup page: Input password = 'Veri1234', confirm password = 'Veri5234'");
    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri5234");
    	SetupPasswordPage.clickOnSubmitBtn();
    	Thread.sleep(timeOut);
    	errText = SetupPasswordPage.errorMatch();
    	System.out.println(errText);
    	currentResult = errText.contains(setupErrorMatch);
    	if (currentResult == true) {
    		testLog.pass( "Found Match error: " + setupErrorMatch + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Found Match error: " + errText + ". Expected: " + setupErrorMatch);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//      Input correct Password and ConfirmPassword, pass to TOS window, verify text

    	testLog.info( "Setup page: Input password = 'Veri1234', confirm password = 'Veri1234'");
    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
    	testLog.info( "Display TOS");
    	SetupPasswordPage.clickOnchboxTOS();

    	testLog.info( "--------------------------------------------------TOS document---------------------------------------------------");

    	Thread.sleep(timeOut);
//    	Verify TOS page text
    	String tText = SetupPasswordPage.tosText();
    	currentResult = tText.contains(textTOS);
    	if (currentResult == true) {
    		testLog.pass( "TOS doc: Found TOS text: " + textTOS + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "TOS doc: Found TOS text: " + tText + ". Expected: " + textTOS);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}
    	System.out.println(tText);

//		Agree with TOS
    	testLog.info( "Accept TOS");
    	SetupPasswordPage.clickOnAcceptTOSBtn();
    	Thread.sleep(timeOut - 1000);
    	testLog.info( "Submit Setup");
    	SetupPasswordPage.clickOnSubmitBtn();

//		Test Login
//    	Setup Login button

    	Thread.sleep(timeOut + 2000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	testLog.info( "Navigate to Login page");
    	SetupPasswordPage.loginCpLnk_from_LoginSetup();

    	testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

//    	Click on Forgot Password link
    	Thread.sleep(timeOut + 1000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	testLog.info( "Navigate to Forgot Password page");
    	SetupPasswordPage.loginForgotLinkClick();

    	testLog.info( "----------------------------------------------Forgot Password page-----------------------------------------------");

//    	Compare Title, Text, Email text, Send button label, Login link label with expected on Forgot Password page
    	Thread.sleep(timeOut + 3000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	ForgotPasswordPage ForgotPasswordPage = (ForgotPasswordPage) PageFactory.getPage("ForgotPasswordPage");

//    	Compare Forgot page title with expected
    	tText = ForgotPasswordPage.pageTitle();
    	currentResult = tText.contains(ForgotPageTitle);
    	if (currentResult == true) {
    		testLog.pass( "Forgot Password page: Found title: " + ForgotPageTitle + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Forgot Password page: Found title: " + tText + ". Expected: " + ForgotPageTitle);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare Forgot page text with expected
    	tText = ForgotPasswordPage.pageText();
    	currentResult = tText.contains(ForgotPageText);
    	if (currentResult == true) {
    		testLog.pass( "Forgot Password page: Found text: " + ForgotPageText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Forgot Password page: Found text: " + tText + ". Expected: " + ForgotPageText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare Forgot page Mail label with expected
    	tText = ForgotPasswordPage.mailLabelText();
    	currentResult = tText.contains(ForgotPageMailLabelText);
    	if (currentResult == true) {
    		testLog.pass( "Forgot Password page: Found mail field hint: " + ForgotPageMailLabelText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Forgot Password page: Found mail field hint: " + tText + ". Expected: " + ForgotPageMailLabelText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare Forgot page Login link text with expected
    	tText = ForgotPasswordPage.btnSendText();
    	currentResult = tText.contains(ForgotPageBtnSendText);
    	if (currentResult == true) {
    		testLog.pass( "Forgot Password page: Found Send button label: " + ForgotPageBtnSendText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Forgot Password page: Found Send button label: " + tText + ". Expected: " + ForgotPageBtnSendText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare Forgot page Login link text with expected
    	tText = ForgotPasswordPage.lnkLoginText();
    	currentResult = tText.contains(ForgotPageLnkLoginText);
    	if (currentResult == true) {
    		testLog.pass( "Forgot Password page: Found Login link Text: " + ForgotPageLnkLoginText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Forgot Password page: Found Login link Text: " + tText + ". Expected: " + ForgotPageLnkLoginText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Click on Forgot page Login link
    	ForgotPasswordPage.clickLoginLnk();

//    	Login Page
    	testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

    	Thread.sleep(timeOut + 3000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));

//    	LogIn Page: Get Title, Email label, Password Label, button label and Compare with Expected.

//    	Compare login Title text with expected
    	tText = SetupPasswordPage.loginTitle();
    	currentResult = tText.contains(loginTitle);
    	if (currentResult == true) {
    		testLog.pass( "Login page: Found title: " + loginTitle + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Login page: Found title: " + tText + ". Expected: " + loginTitle);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare login Email text with expected
    	tText = SetupPasswordPage.loginEmail();
    	currentResult = tText.contains(loginEmail);
    	if (currentResult == true) {
    		testLog.pass( "Login page: Found Email field hint: " + loginEmail + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Login page: Found Email field hint: " + tText + ". Expected: " + loginEmail);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare login Password text with expected
    	tText = SetupPasswordPage.loginPassword();
    	currentResult = tText.contains(loginPassword);
    	if (currentResult == true) {
    		testLog.pass( "Login page: Found Password field hint: " + loginPassword + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Login page: Found Password field hint: " + tText + ". Expected: " + loginPassword);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare login Forgot link text with expected
    	tText = SetupPasswordPage.loginForgotLink();
    	currentResult = tText.contains(loginForgotLink);
    	if (currentResult == true) {
    		testLog.pass( "Login page: Found Forgot Password link: " + loginForgotLink + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Login page: Found Forgot Password link: " + tText + ". Expected: " + loginForgotLink);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare login button text with expected
    	tText = SetupPasswordPage.loginBtnLabel();
    	currentResult = tText.contains(loginBtnLabel);
    	if (currentResult == true) {
    		testLog.pass( "Login page: Found Login button hint: " + loginBtnLabel + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Login page: Found Login button hint: " + tText + ". Expected: " + loginBtnLabel);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Login Page: Click on Forgot Password link
    	testLog.info( "Click on Forgot link");
    	SetupPasswordPage.loginForgotLinkClick();

    	testLog.info( "----------------------------------------------Forgot Password page-----------------------------------------------");

//    	Forgot Password Page: Email empty. Get error and compare with expected
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	Thread.sleep(timeOut - 1000);
    	testLog.info( "Forgot Password page: Input mail: " + ufEmail + " and Send");
    	ForgotPasswordPage.InputEmail(ufEmail);
    	ForgotPasswordPage.clickBtnSend();

    	Thread.sleep(timeOut - 1000);
    	tText = ForgotPasswordPage.errorEmptyText();
    	currentResult = tText.contains(ForgotErrorEmptyText);
    	if (currentResult == true) {
    		testLog.pass( "Forgot Password page: Found Mandatory error: " + ForgotErrorEmptyText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Forgot Password page: Found Mandatory error: " + tText + ". Expected: " + ForgotErrorEmptyText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Forgot Password Page: Email Invalid. Get error and compare with expected
    	Thread.sleep(timeOut - 1000);
    	ufEmail = "InvalidMail";
    	testLog.info( "Forgot Password page: Input mail: " + ufEmail + " and Send");
    	ForgotPasswordPage.InputEmail(ufEmail);
    	ForgotPasswordPage.clickBtnSend();
    	Thread.sleep(1000);

    	tText = ForgotPasswordPage.errorEmptyText();
    	currentResult = tText.contains(ForgotErrorInvalidText);
    	if (currentResult == true) {
    		testLog.pass( "Forgot Password page: Found Format error: " + ForgotErrorInvalidText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Forgot Password page: Found Format error: " + tText + ". Expected: " + ForgotErrorInvalidText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Forgot Password Page: Email Not Match. Get error and compare with expected
    	Thread.sleep(timeOut - 1000);
    	ufEmail = mId + "@getnada.com";
    	testLog.info( "Forgot Password page: Input mail: " + ufEmail + " and Send");
    	ForgotPasswordPage.InputEmail(ufEmail);
    	ForgotPasswordPage.clickBtnSend();

    	testLog.info( "---------------------------------------------Email Confirmation page---------------------------------------------");

//    	Email Confirmation Page: Get Title text, Page text, Muted text and compare with expected
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	Thread.sleep(timeOut - 1000);
    	EmailConfirmPage EmailConfirmPage = (EmailConfirmPage) PageFactory.getPage("EmailConfirmPage");

//    	Compare Email Confirmation page title with expected
    	tText = EmailConfirmPage.pageTitle();
    	currentResult = tText.contains(EmailConfPageTitle);
    	if (currentResult == true) {
    		testLog.pass( "Email Confirmation page: Found Title: " + EmailConfPageTitle + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Email Confirmation page: Found Title: " + tText + ". Expected: " + EmailConfPageTitle);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare Email Confirmation page text with expected
    	tText = EmailConfirmPage.pageText();
    	currentResult = tText.contains(EmailConfPageText);
    	if (currentResult == true) {
    		testLog.pass( "Email Confirmation page: Found Text: " + EmailConfPageText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Email Confirmation page: Found Text: " + tText + ". Expected: " + EmailConfPageText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

//    	Compare Email Confirmation page muted text with expected
    	tText = EmailConfirmPage.pageTextMuted();
    	currentResult = tText.contains(EmailConfPageMutedText);
    	if (currentResult == true) {
    		testLog.pass( "Email Confirmation page: Found Muted Text: " + EmailConfPageMutedText + "...: Succesfull");
    	} else {
    		TestPassFlag = false;
    		testLog.error( "Email Confirmation page: Found Muted Text: " + tText + ". Expected: " + EmailConfPageMutedText);
    		capScreenShootPath = SeleniumUtils.getScreenshot();
    		testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
    		testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
    	}

    	Thread.sleep(timeOut);
    	Assert.assertTrue(TestPassFlag);
    	BasePage.driver.quit();
    }

}

