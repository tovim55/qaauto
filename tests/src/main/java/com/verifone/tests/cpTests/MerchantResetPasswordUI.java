package com.verifone.tests.cpTests;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.entities.EntitiesFactory;

import java.io.IOException;
import java.util.ArrayList;

import com.verifone.pages.eoPages.HomePage;
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
import com.verifone.pages.cpPages.ResetPasswordPage;
import com.verifone.pages.cpPages.ResetThankYou;
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

public class MerchantResetPasswordUI extends BaseTest {
//	private final static String ResetMailText1 = "You recently requested a password change for";
//	private final static String ResetMailText2 = "Please click on the link below to reset your password.";
//	private final static String ResetMailButtonLabel = "Reset Password";
//	private final static String ResetPasswordPageTitle = "Reset Password";
//	private final static String ResetPasswordPagePasswdLabel = "New password";
//	private final static String ResetPasswordPageConfirmPasswdLabel = "Confirm Password";
//	private final static String ResetPasswordPageProceedButton = "Confirm Password";
//	private final static String ResetPasswordPageEmptyError = "Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters.";
//	private final static String ResetPasswordPageMatchError = "Password and Confirmation Password must match";
//	private final static String ThankYouPageTitle = "Thank you!";
//	private final static String ThankYouPageText = "We've reset the password for your Verifone account. You can now log in using your new password.";

	private static String mailActivateButton = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/p[5]/a";
	private static String mailResetButton = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/div[4]/a";
	private final static int timeOut = 2000;
	private static String mId = "";
	private static String ufEmail = "";
	private static Integer rowNumber=0;
	private static Integer getRowNumFromFile = 0;
	final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\merchantResetPassword.xls";
	private static Boolean TestPassFlag = true;
	private static String capScreenShootPath;
	private WebDriver driver = new HomePage().getDriver();

	@BeforeTest
	public void startDDTest() throws Exception{
//	 		Get number of Rows from Data driven
		getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "merchantResetPassword");
	}

//  Data Provider

	@DataProvider(name = "merchantResetPassword")
	public Object[][] dataSupplierLoginData() throws Exception {
		Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "merchantResetPassword");
		return arrayObject;
	}

	@Test(testName = "Merchant Reset Password Test", dataProvider = "merchantResetPassword", groups = { "Localization2" })
	public void MerchantResetPasswordUI(String Lang, String EOAdminMail, String EOAdminPwd, String mailInvText, String actAccountBtnLabel, String ResetMailText1, String ResetMailText2, String ResetMailText3,
										String ResetMailButtonLabel, String ResetPasswordPageTitle, String ResetPasswordPagePasswdLabel,
										String ResetPasswordPageConfirmPasswdLabel, String ResetPasswordPageProceedButton, String ResetPasswordPageEmptyError,
										String ResetPasswordPageMatchError, String ThankYouPageTitle, String ThankYouPageText) throws Exception {

//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");
		rowNumber = rowNumber+1;
		testLog.info("Data Driven line number: " + rowNumber);
		testLog.info("---------------------------------------------Create Merchant by API---------------------------------------------");

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

		driver.navigate().to("https://getnada.com/#");

		// Click Add Inbox

		driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();

		// Put email
		driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
		Thread.sleep(timeOut+2000);
		driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(mId);

		driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
		driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);

		//  Accept
		driver.findElement(By.linkText("ACCEPT")).click();
		testLog.info("Create email inbox: " + mId + "getnada.com: Succesfull");

		//  Open Email
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();
		testLog.info( "Found Invitation mail: Succesfull");

		//   Get email text

		Thread.sleep(timeOut);

		WebElement iFrame = driver.findElement(By.id("idIframe"));
		driver.switchTo().frame(iFrame);
		String mailText = driver.getPageSource();

		Boolean currentResult = mailText.contains(mailInvText);
		if (currentResult == true) {
			testLog.pass( "Invitation mail include text: " + mailInvText + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Invitation mail include text: " + mailText + ". Expected: " + mailInvText);
		}
		System.out.println("Mail text: " + currentResult);

		String btnText = driver.findElement(By.xpath(mailActivateButton)).getText();
		currentResult = btnText.contains(actAccountBtnLabel);
		if (currentResult == true) {
			testLog.pass( "Invitation mail Activate button text: " + actAccountBtnLabel + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Invitation mail Activate button text: " + btnText + ". Expected: " + actAccountBtnLabel);
		}
		driver.findElement(By.xpath(mailActivateButton)).click();

		testLog.info( "Click on: " + btnText + " button: Succesfull");


		//		Setup Password
		testLog.info( "-----------------------------------------------Setup Password page-----------------------------------------------");

		Thread.sleep(timeOut + 2000);
		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(1));

		SetupPasswordPage SetupPasswordPage = (SetupPasswordPage) PageFactory.getPage("SetupPasswordPage");

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("var language = window.navigator.userLanguage || window.navigator.language");

//      Input correct Password and ConfirmPassword, pass to TOS window, verify text
		testLog.info( "Setup page: Input password = 'Veri1234', confirm password = 'Veri1234'");
		SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
		testLog.info( "Display TOS");
		SetupPasswordPage.clickOnchboxTOS();

		Thread.sleep(timeOut);
		testLog.info( "--------------------------------------------------TOS document---------------------------------------------------");

//		Agree with TOS
		testLog.info( "Agree with TOS");
		SetupPasswordPage.clickOnAcceptTOSBtn();
		Thread.sleep(timeOut-1000);
		SetupPasswordPage.clickOnSubmitBtn();

//		Test Login
//    	Setup Login button

		Thread.sleep(timeOut + 2000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(1));

		SetupPasswordPage.loginCpLnk_from_LoginSetup();

		testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

//    	Click on Forgot Password link
		Thread.sleep(timeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(1));
		testLog.info( "Navigate to Forgot Password page");
		SetupPasswordPage.loginForgotLinkClick();

		testLog.info( "----------------------------------------------Forgot Password page-----------------------------------------------");

//    	Forgot Password Page: Email Not Match. Get error and compare with expected
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(1));
		ForgotPasswordPage ForgotPasswordPage = (ForgotPasswordPage) PageFactory.getPage("ForgotPasswordPage");

		Thread.sleep(timeOut - 1000);
		ufEmail = mId + "@getnada.com";
		testLog.info( "Forgot Password page: Input mail: " + ufEmail + " and Send");
		ForgotPasswordPage.InputEmail(ufEmail);
		ForgotPasswordPage.clickBtnSend();

		testLog.info( "-------------------------------------------------Getnada service-------------------------------------------------");

//    	Goto Getnada mail and Open received email
		Thread.sleep(timeOut + 3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		driver.navigate().to("https://getnada.com/#");

// 		Click Add Inbox
		driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();

		// Put email
		driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
		driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(mId);

		driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
		driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);

		//  Accept
		driver.findElement(By.linkText("ACCEPT")).click();

		//  Open Email

		Thread.sleep(timeOut);
		testLog.info( "Open received email");
		driver.findElement(By.xpath("//div[contains(@class, 'subject bold')]")).click();

		//   Get email text

		Thread.sleep(timeOut);

		iFrame = driver.findElement(By.id("idIframe"));
		driver.switchTo().frame(iFrame);
		mailText = driver.getPageSource();

//    	Compare Mail text with expected
		currentResult = mailText.contains(ResetMailText1);
		if (currentResult == true) {
			testLog.pass( "Reset Password mail include text: " + ResetMailText1 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password mail include text: " + mailText + ". Expected: " + ResetMailText1);
		}
		currentResult = mailText.contains(ResetMailText2);
		if (currentResult == true) {
			testLog.pass( "Reset Password mail include text: " + ResetMailText2 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password mail include text: " + mailText + ". Expected: " + ResetMailText2);
		}
		currentResult = mailText.contains(ResetMailText3);
		if (currentResult == true) {
			testLog.pass( "Reset Password mail include text: " + ResetMailText3 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password mail include text: " + mailText + ". Expected: " + ResetMailText3);
		}
//    	Compare Reset Password button label with expected
		mailText = driver.findElement(By.xpath(mailResetButton)).getText();
		currentResult = mailText.contains(ResetMailButtonLabel);
		if (currentResult == true) {
			testLog.pass( "Found Reset Password button label: " + ResetMailButtonLabel + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Reset Password button label: " + mailText + ". Expected: " + ResetMailButtonLabel);
		}

		driver.findElement(By.xpath(mailResetButton)).click();

		testLog.info( "Click on: " + mailResetButton + " button: Succesfull");

//    	Reset Password Page
		testLog.info( "-----------------------------------------------Reset Password page-----------------------------------------------");

		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(2));
		ResetPasswordPage ResetPasswordPage = (ResetPasswordPage) PageFactory.getPage("ResetPasswordPage");

//    	Reset Password Page: Compare title with expected
		String tText = ResetPasswordPage.pageTitle();
		currentResult = tText.contains(ResetPasswordPageTitle);
		if (currentResult == true) {
			testLog.pass( "Reset Password page: Found title: " + ResetPasswordPageTitle + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password page: Found title: " + tText + ". Expected: " + ResetPasswordPageTitle);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Reset Password Page: Compare Password label with expected
		tText = ResetPasswordPage.passwordLabelText();
		currentResult = tText.contains(ResetPasswordPagePasswdLabel);
		if (currentResult == true) {
			testLog.pass( "Reset Password page: Password field hint: " + ResetPasswordPagePasswdLabel + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password page: Password field hint: " + tText + ". Expected: " + ResetPasswordPagePasswdLabel);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Reset Password Page: Compare Confirm Password label with expected
		tText = ResetPasswordPage.confirmPasswordLabelText();
		currentResult = tText.contains(ResetPasswordPageConfirmPasswdLabel);
		if (currentResult == true) {
			testLog.pass( "Reset Password page: Confirm Password field hint: " + ResetPasswordPageConfirmPasswdLabel + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password page: Confirm Password field hint: " + tText + ". Expected: " + ResetPasswordPageConfirmPasswdLabel);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Reset Password Page: Compare Proceed button label with expected
		tText = ResetPasswordPage.btnProceedText();
		currentResult = tText.contains(ResetPasswordPageProceedButton);
		if (currentResult == true) {
			testLog.pass( "Reset Password page: Proceed button label: " + ResetPasswordPageProceedButton + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password page: Proceed button label: " + tText + ". Expected: " + ResetPasswordPageProceedButton);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Reset Password Page: Input empty password, get error and compare with expected
		testLog.info( "Reset Password page: Input password = ' '. Proceed");
		ResetPasswordPage.InputPassword("");
		ResetPasswordPage.clickBtnProceed();
		Thread.sleep(timeOut - 1000);
		tText = ResetPasswordPage.errorEmptyText();
		currentResult = tText.contains(ResetPasswordPageEmptyError);
		if (currentResult == true) {
			testLog.pass( "Reset Password page: Found Mandatory error: " + ResetPasswordPageEmptyError + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password page: Found Mandatory error: " + tText + ". Expected: " + ResetPasswordPageEmptyError);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Reset Password Page: Input empty Confirm password, get error and compare with expected
		testLog.info( "Reset Password page: Input Confirm password = ' '. Proceed");
		ResetPasswordPage.InputConfirmPassword("");
		ResetPasswordPage.clickBtnProceed();
		Thread.sleep(timeOut - 1000);
		tText = ResetPasswordPage.errorConfirmEmptyText();
		currentResult = tText.contains(ResetPasswordPageEmptyError);
		if (currentResult == true) {
			testLog.pass( "Reset Password page: Found Mandatory error: " + ResetPasswordPageEmptyError + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password page: Found Mandatory error: " + tText + ". Expected: " + ResetPasswordPageEmptyError);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Reset Password Page: Input Password and Confirmation Password not match, get error and compare with expected
		testLog.info( "Reset Password page: Input Password = 'Veri1234', Confirm password = 'Veri4321'. Proceed");
		ResetPasswordPage.InputPassword("Veri1234");
		ResetPasswordPage.InputConfirmPassword("Veri4321");
		ResetPasswordPage.clickBtnProceed();
		Thread.sleep(timeOut - 1000);
		tText = ResetPasswordPage.errorConfirmEmptyText();
		currentResult = tText.contains(ResetPasswordPageMatchError);
		if (currentResult == true) {
			testLog.pass( "Reset Password page: Found Match error: " + ResetPasswordPageMatchError + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Reset Password page: Found Match error: " + tText + ". Expected: " + ResetPasswordPageMatchError);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Reset Password Page: Input Same Password and Confirmation Password, click on Proceed button
		testLog.info( "Reset Password page: Input Password = 'Veri1234', Confirm password = 'Veri1234'. Proceed");
		ResetPasswordPage.InputPassword("Veri1234");
		ResetPasswordPage.InputConfirmPassword("Veri1234");
		ResetPasswordPage.clickBtnProceed();

//    	Thank you page
		testLog.info( "-------------------------------------------------Thank You page--------------------------------------------------");

		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(2));
		ResetThankYou ResetThankYou = (ResetThankYou) PageFactory.getPage("ResetThankYou");

//    	Thank you page: Compare title with expected
		tText = ResetThankYou.pageTitle();
		currentResult = tText.contains(ThankYouPageTitle);
		if (currentResult == true) {
			testLog.pass( "Thank You page: Found page Title: " + ThankYouPageTitle + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Thank You page: Found page Title: " + tText + ". Expected: " + ThankYouPageTitle);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Thank you page: Compare page text with expected
		tText = ResetThankYou.pageText();
		currentResult = tText.contains(ThankYouPageText);
		if (currentResult == true) {
			testLog.pass( "Thank You page: Found page Text: " + ThankYouPageText + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Thank You page: Found page Text: " + tText + ". Expected: " + ThankYouPageText);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	Thank you page: Click on LogIn link
		ResetThankYou.clickLoginLnk();

		Thread.sleep(timeOut);
		Assert.assertTrue(TestPassFlag);
		driver.quit();

	}

}


