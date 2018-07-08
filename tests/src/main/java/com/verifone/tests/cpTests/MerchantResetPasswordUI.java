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
import com.verifone.pages.cpPages.ResetPasswordPage;
import com.verifone.pages.cpPages.ResetThankYou;
import com.verifone.pages.cpPages.SetupPasswordPage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
import com.verifone.utils.apiClient.getToken.GetTokenApi;

import org.testng.Assert;
import org.testng.annotations.*;

import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;

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
	
	private final static int timeOut = 2000;
    private static String mId = "";
    private static String ufEmail = "";
    private static Integer rowNumber=0;
    private static Integer getRowNumFromFile = 0;
	final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\merchantResetPassword.xls";
	
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
	
	@Test(dataProvider = "merchantResetPassword", groups = { "Localization" })
	public void MerchantResetPasswordUI(String EOAdminMail, String EOAdminPwd, String mailInvText, String actAccountBtnLabel, String ResetMailText1, String ResetMailText2, String ResetMailButtonLabel, String ResetPasswordPageTitle, String ResetPasswordPagePasswdLabel,
  		String ResetPasswordPageConfirmPasswdLabel, String ResetPasswordPageProceedButton, String ResetPasswordPageEmptyError, 
  		String ResetPasswordPageMatchError, String ThankYouPageTitle, String ThankYouPageText) throws Exception {
    	
        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");
        rowNumber = rowNumber+1;
        testLog.log(LogStatus.INFO, "Data Driven line number: " + rowNumber);
        testLog.log(LogStatus.INFO, "---------------------------------------------Create Merchant by API---------------------------------------------");
    	
        User user = new User(EOAdminMail, EOAdminPwd);
        GetTokenApi getTokenApi = new GetTokenApi("testId");
        String accessToken = getTokenApi.getToken(user);
        GetEoadminDataApi getEoadminDataApi = new GetEoadminDataApi(accessToken,"testId");
        mId = new CreateMerchant(accessToken, "testId").createMerchant(getEoadminDataApi.getData());
        System.out.println("MID: " + mId);

        
    	//      Navigate to Getnada
        testLog.log(LogStatus.INFO, "-------------------------------------------------Getnada service-------------------------------------------------");

    	BasePage.driver.navigate().to("https://getnada.com/#");
    	
    	// Click Add Inbox

    	BasePage.driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();  
    	
    	// Put email
    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
    	Thread.sleep(timeOut);
    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(mId);
    	
    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);
    	
    	//  Accept
    	BasePage.driver.findElement(By.linkText("ACCEPT")).click(); 
    	testLog.log(LogStatus.INFO, "Create email inbox: " + mId + "getnada.com: Succesfull");
    	
    	//  Open Email
    	Thread.sleep(2000);
    	BasePage.driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();
    	testLog.log(LogStatus.INFO, "Found Invitation mail: Succesfull");
    	
    	//   Get email text    	
    	
    	Thread.sleep(timeOut);
    	
    	WebElement iFrame = BasePage.driver.findElement(By.id("idIframe"));
    	BasePage.driver.switchTo().frame(iFrame);
    	String mailText = BasePage.driver.getPageSource();
    	
    	Boolean langFlag = mailText.contains(mailInvText);
    	
    	System.out.println("Mail text: " + langFlag);	
    	testLog.log(LogStatus.INFO, "Invitation mail include text: " + mailInvText + "...: Succesfull");
    	
    	BasePage.driver.findElement(By.linkText(actAccountBtnLabel)).click(); 
    	testLog.log(LogStatus.INFO, "Invitation mail include button: " + actAccountBtnLabel + "...: Succesfull");
    	testLog.log(LogStatus.INFO, "Click on: " + actAccountBtnLabel + " button: Succesfull");
    
    	
    	//		Setup Password
    	testLog.log(LogStatus.INFO, "-----------------------------------------------Setup Password page-----------------------------------------------");
    	
    	Thread.sleep(timeOut + 2000);
    	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));

    	SetupPasswordPage SetupPasswordPage = (SetupPasswordPage) PageFactory.getPage("SetupPasswordPage");   

    	JavascriptExecutor jse = (JavascriptExecutor)BasePage.driver;
    	jse.executeScript("var language = window.navigator.userLanguage || window.navigator.language");
    	
//      Input correct Password and ConfirmPassword, pass to TOS window, verify text
    	testLog.log(LogStatus.INFO, "Setup page: Input password = 'Veri1234', confirm password = 'Veri1234'");
    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");  
    	testLog.log(LogStatus.INFO, "Display TOS");
    	SetupPasswordPage.clickOnchboxTOS();
    	
    	Thread.sleep(timeOut);
    	testLog.log(LogStatus.INFO, "--------------------------------------------------TOS document---------------------------------------------------");
    	
//		Agree with TOS
    	testLog.log(LogStatus.INFO, "Agree with TOS");
    	SetupPasswordPage.clickOnAcceptTOSBtn();
    	Thread.sleep(timeOut-1000);
    	SetupPasswordPage.clickOnSubmitBtn();
    	
//		Test Login
//    	Setup Login button
    	
    	Thread.sleep(timeOut + 2000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	    	
    	SetupPasswordPage.loginCpLnk_from_LoginSetup();
    	
    	testLog.log(LogStatus.INFO, "---------------------------------------------------Login page----------------------------------------------------");

//    	Click on Forgot Password link
    	Thread.sleep(timeOut + 1000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	testLog.log(LogStatus.INFO, "Navigate to Forgot Password page");
    	SetupPasswordPage.loginForgotLinkClick();
    	
    	testLog.log(LogStatus.INFO, "----------------------------------------------Forgot Password page-----------------------------------------------");
    	
//    	Forgot Password Page: Email Not Match. Get error and compare with expected
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	ForgotPasswordPage ForgotPasswordPage = (ForgotPasswordPage) PageFactory.getPage("ForgotPasswordPage");
      	
    	Thread.sleep(timeOut - 1000);
    	ufEmail = mId + "@getnada.com";
    	testLog.log(LogStatus.INFO, "Forgot Password page: Input mail: " + ufEmail + " and Send");
    	ForgotPasswordPage.InputEmail(ufEmail);
    	ForgotPasswordPage.clickBtnSend();
    	
        testLog.log(LogStatus.INFO, "-------------------------------------------------Getnada service-------------------------------------------------");
    	
//    	Goto Getnada mail and Open received email
    	Thread.sleep(timeOut + 3000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(0));
    	BasePage.driver.navigate().to("https://getnada.com/#");
    	
// 		Click Add Inbox
    	BasePage.driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();  
    	
    	// Put email
    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(mId);
    	
    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);
    	
    	//  Accept
    	BasePage.driver.findElement(By.linkText("ACCEPT")).click(); 
    	
    	//  Open Email

    	Thread.sleep(timeOut);
    	testLog.log(LogStatus.INFO, "Open received email");
    	BasePage.driver.findElement(By.xpath("//div[contains(@class, 'subject bold')]")).click();
    	
    	//   Get email text    	
    	
    	Thread.sleep(timeOut);
    	
    	iFrame = BasePage.driver.findElement(By.id("idIframe"));
    	BasePage.driver.switchTo().frame(iFrame);
    	mailText = BasePage.driver.getPageSource();
    	
//    	Compare Mail text with expected 	
    	Boolean currentResult = mailText.contains(ResetMailText1);
    	Assert.assertTrue(currentResult, "Correct mail text not found"); 
    	testLog.log(LogStatus.INFO, "Reset Password mail include text: " + ResetMailText1 + "...: Succesfull");
    	currentResult = mailText.contains(ResetMailText2);
    	Assert.assertTrue(currentResult, "Correct mail text not found"); 
    	testLog.log(LogStatus.INFO, "Reset Password mail include text: " + ResetMailText2 + "...: Succesfull");
    	
    	BasePage.driver.findElement(By.linkText(ResetMailButtonLabel)).click(); 
    	testLog.log(LogStatus.INFO, "Reset Password mail include button: " + ResetMailButtonLabel + "...: Succesfull");
    	testLog.log(LogStatus.INFO, "Click on: " + ResetMailButtonLabel + " button: Succesfull");
    	
//    	Reset Password Page
    	testLog.log(LogStatus.INFO, "-----------------------------------------------Reset Password page-----------------------------------------------");
    	
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(2));
    	ResetPasswordPage ResetPasswordPage = (ResetPasswordPage) PageFactory.getPage("ResetPasswordPage");
    	
//    	Reset Password Page: Compare title with expected
    	String tText = ResetPasswordPage.pageTitle();
    	currentResult = tText.contains(ResetPasswordPageTitle);
    	Assert.assertTrue(currentResult, "Correct Reset passweord page Title not found");
    	testLog.log(LogStatus.INFO, "Reset Password page: Found title: " + ResetPasswordPageTitle + "...: Succesfull");
    	
//    	Reset Password Page: Compare Password label with expected
    	tText = ResetPasswordPage.passwordLabelText();
    	currentResult = tText.contains(ResetPasswordPagePasswdLabel);
    	Assert.assertTrue(currentResult, "Correct Reset password page -> password label not found");
    	testLog.log(LogStatus.INFO, "Reset Password page: Password field hint: " + ResetPasswordPagePasswdLabel + ": Succesfull");
    	
//    	Reset Password Page: Compare Confirm Password label with expected
    	tText = ResetPasswordPage.confirmPasswordLabelText();
    	currentResult = tText.contains(ResetPasswordPageConfirmPasswdLabel);
    	Assert.assertTrue(currentResult, "Correct Reset password page -> confirm password label not found");
    	testLog.log(LogStatus.INFO, "Reset Password page: Confirm Password field hint: " + ResetPasswordPageConfirmPasswdLabel + ": Succesfull");
    	
//    	Reset Password Page: Compare Proceed button label with expected
    	tText = ResetPasswordPage.btnProceedText();
    	currentResult = tText.contains(ResetPasswordPageProceedButton);
    	Assert.assertTrue(currentResult, "Correct Reset password page -> proceed button label not found");
    	testLog.log(LogStatus.INFO, "Reset Password page: Proceed button label: " + ResetPasswordPageProceedButton + ": Succesfull");
    	

//    	Reset Password Page: Input empty password, get error and compare with expected
    	testLog.log(LogStatus.INFO, "Reset Password page: Input password = ' '. Proceed");
    	ResetPasswordPage.InputPassword("");
    	ResetPasswordPage.clickBtnProceed();
    	Thread.sleep(timeOut - 1000);
    	tText = ResetPasswordPage.errorEmptyText();
    	currentResult = tText.contains(ResetPasswordPageEmptyError);
    	Assert.assertTrue(currentResult, "Correct Reset password page -> Error on empty input");
    	testLog.log(LogStatus.INFO, "Reset Password page: Found Mandatory error: : " + ResetPasswordPageEmptyError + ": Succesfull");
    	
//    	Reset Password Page: Input empty Confirm password, get error and compare with expected
    	testLog.log(LogStatus.INFO, "Reset Password page: Input Confirm password = ' '. Proceed");
    	ResetPasswordPage.InputConfirmPassword("");
    	ResetPasswordPage.clickBtnProceed();
    	Thread.sleep(timeOut - 1000);
    	tText = ResetPasswordPage.errorConfirmEmptyText();
    	currentResult = tText.contains(ResetPasswordPageEmptyError);
    	Assert.assertTrue(currentResult, "Correct Reset password page -> Error on empty input");
    	testLog.log(LogStatus.INFO, "Reset Password page: Found Mandatory error: : " + ResetPasswordPageEmptyError + ": Succesfull");
    	
//    	Reset Password Page: Input Password and Confirmation Password not match, get error and compare with expected
    	testLog.log(LogStatus.INFO, "Reset Password page: Input Password = 'Veri1234', Confirm password = 'Veri4321'. Proceed");
    	ResetPasswordPage.InputPassword("Veri1234");
    	ResetPasswordPage.InputConfirmPassword("Veri4321");
    	ResetPasswordPage.clickBtnProceed();
    	Thread.sleep(timeOut - 1000);
    	tText = ResetPasswordPage.errorConfirmEmptyText();
    	currentResult = tText.contains(ResetPasswordPageMatchError);
    	Assert.assertTrue(currentResult, "Correct Reset password page -> Error on not match input");
    	testLog.log(LogStatus.INFO, "Reset Password page: Found Match error: : " + ResetPasswordPageMatchError + ": Succesfull");
    	
//    	Reset Password Page: Input Same Password and Confirmation Password, click on Proceed button
    	testLog.log(LogStatus.INFO, "Reset Password page: Input Password = 'Veri1234', Confirm password = 'Veri1234'. Proceed");
    	ResetPasswordPage.InputPassword("Veri1234");
    	ResetPasswordPage.InputConfirmPassword("Veri1234");
    	ResetPasswordPage.clickBtnProceed();
    	
//    	Thank you page
    	testLog.log(LogStatus.INFO, "-------------------------------------------------Thank You page--------------------------------------------------");
    	
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(2));
    	ResetThankYou ResetThankYou = (ResetThankYou) PageFactory.getPage("ResetThankYou");
    	
//    	Thank you page: Compare title with expected
    	tText = ResetThankYou.pageTitle();
    	currentResult = tText.contains(ThankYouPageTitle);
    	Assert.assertTrue(currentResult, "Correct Thank You page Title not found");
    	testLog.log(LogStatus.INFO, "Thank You page: Found page Title: " + ThankYouPageTitle + ": Succesfull");
    	
//    	Thank you page: Compare page text with expected
    	tText = ResetThankYou.pageText();
    	currentResult = tText.contains(ThankYouPageText);
    	Assert.assertTrue(currentResult, "Correct Thank You page Text not found");
    	testLog.log(LogStatus.INFO, "Thank You page: Found page Text: " + ThankYouPageText + ": Succesfull");
    	
//    	Thank you page: Click on LogIn link
    	ResetThankYou.clickLoginLnk();
    	
    	Thread.sleep(timeOut);
    	BasePage.driver.quit();
    }

}


