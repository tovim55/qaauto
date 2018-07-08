package com.verifone.tests.cpTests;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.entities.EntitiesFactory;

import java.io.FileInputStream;
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
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.cpPages.MerchantViewPage;
import com.verifone.pages.cpPages.SetupPasswordPage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
import com.verifone.utils.apiClient.getToken.GetTokenApi;

import org.testng.Assert;
import org.testng.annotations.*;

import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.General.LoginCGPortal;

public class MerchantSetupPasswordUI extends BaseTest {
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
    private static String ulMail = "";
    private static String ulPassword = "";
//    private final static String lerrorMandatoryField = "This field is required.";
//    private final static String lerrorValidationMail = "Email has incorrect format. You can only use letters, numbers and symbols.";
//    private final static String lerrorMatch = "The information you''ve entered does not match the information we have on file.";
//    private final static String pageAgreementTitle = "Action Required";
//    private final static String pageAgreementText = "Your Verifone activation account is not yet completed";
//    private final static String pageBtnText = "Accept Merchant Agreement";
//    private final static String docHeader = "Standard Terms and Conditions for";
//    private final static String docText = "These Standard Terms and Conditions for Verifone Services and Solutions";
//    private final static String docAgreeBtnText = "Agree";
//    private final static String merchantPageTitle = "Thank you!";
    
    private final static int timeOut = 2000;
    private static String mId = "";
    private static Integer rowNumber=0;
    private static Integer getRowNumFromFile = 0;
	final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\merchantSetupPassword.xls";
	
	@BeforeTest
	public void startDDTest() throws Exception{
//	 		Get number of Rows from Data driven
		getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "merchantSetupPassword");
	}

//	      Data Provider

	@DataProvider(name = "merchantSetupPassword")
	public Object[][] dataSupplierLoginData() throws Exception {
		Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "merchantSetupPassword");
		return arrayObject;
	}

	@Test(dataProvider = "merchantSetupPassword", groups = { "Localization" })	
//    @Test(groups = {"CP-portal"})
    public void MerchantSetupPasswordUI(String EOAdminMail, String EOAdminPwd, String mailInvText, String actAccountBtnLabel, String setupText, String setupErrorFormat, String setupErrorAccept, String setupErrorMatch, String textTOS,
    		String loginTitle, String loginEmail, String loginPassword, String loginForgotLink, String loginBtnLabel, String loginErrorMandatoryField,
    		String loginErrorValidationMail, String loginErrorMatch, String pageAgreementTitle, String pageAgreementText,
    		String pageAgreementBtnText, String docHeader, String docText, String docAgreeBtnText, String merchantPageTitle) throws Exception {
    	
        starTestLog("Merchant Setup Password Test", "Merchant Setup Password Test");
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
//    	BasePage.driver = SeleniumUtils.getDriver("CHROME");
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
    	Thread.sleep(timeOut);
    	BasePage.driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();
    	testLog.log(LogStatus.INFO, "Found Invitation mail: Succesfull");
    	
    	//   Get email text    	
    	
    	Thread.sleep(timeOut);
    	
    	WebElement iFrame = BasePage.driver.findElement(By.id("idIframe"));
    	BasePage.driver.switchTo().frame(iFrame);
    	String mailText = BasePage.driver.getPageSource();
    	
    	Boolean langFlag = mailText.contains(mailInvText);
    	testLog.log(LogStatus.INFO, "Invitation mail include text: " + mailInvText + "...: Succesfull");
    	
    	System.out.println("Mail text: " + langFlag);	
    	
    	BasePage.driver.findElement(By.linkText(actAccountBtnLabel)).click(); 
    	testLog.log(LogStatus.INFO, "Invitation mail include button: " + actAccountBtnLabel + "...: Succesfull");
    	testLog.log(LogStatus.INFO, "Click on: " + actAccountBtnLabel + " button: Succesfull"); 
    
    	
//		Setup Password
    	testLog.log(LogStatus.INFO, "-----------------------------------------------Setup Password page-----------------------------------------------");
    	
    	Thread.sleep(timeOut + 2000);
    	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));

    	SetupPasswordPage SetupPasswordPage = (SetupPasswordPage) PageFactory.getPage("SetupPasswordPage");   
    	String pgText = SetupPasswordPage.pageText();
    	System.out.println(pgText);
    	boolean currentResult = pgText.contains(setupText);
    	Assert.assertTrue(currentResult, "Correct text not found");
    	testLog.log(LogStatus.INFO, "Found Setup page text: " + setupText + "...: Succesfull");
    	
    	//		Input blank space as Password and ConfirmPassword
    	
    	testLog.log(LogStatus.INFO, "Setup page: Input password = ' ', confirm password = ' '");
    	SetupPasswordPage.SetupPasswordPageCp(" ", " ");
    	SetupPasswordPage.clickOnSubmitBtn();
    	String errText = SetupPasswordPage.errorFormat();
    	System.out.println(errText);
    	currentResult = errText.contains(setupErrorFormat);
    	Assert.assertTrue(currentResult, "Correct format error not found");
    	testLog.log(LogStatus.INFO, "Found Format error: " + setupErrorFormat + "...: Succesfull");
    	
    	//		Not agree with TOS
    	
    	testLog.log(LogStatus.INFO, "Setup page: Not accept TOS");
    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
    	SetupPasswordPage.clickOnSubmitBtn();
    	errText = SetupPasswordPage.errorTOS();
    	System.out.println(errText);
    	currentResult = errText.contains(setupErrorAccept);
    	Assert.assertTrue(currentResult, "Correct TOS error not found");
    	testLog.log(LogStatus.INFO, "Found missing accept TOS error: " + setupErrorAccept + "...: Succesfull");
    	
//     Input different Password and ConfirmPassword
    	
    	testLog.log(LogStatus.INFO, "Setup page: Input password = 'Veri1234', confirm password = 'Veri5234'");
    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri5234");   	
    	SetupPasswordPage.clickOnSubmitBtn();
    	Thread.sleep(timeOut);
    	errText = SetupPasswordPage.errorMatch();
    	System.out.println(errText);
    	currentResult = errText.contains(setupErrorMatch);
    	Assert.assertTrue(currentResult, "Correct password match error not found");
    	testLog.log(LogStatus.INFO, "Found Match error: " + setupErrorMatch + "...: Succesfull");
    	
//      Input correct Password and ConfirmPassword, pass to TOS window, verify text
    	
    	testLog.log(LogStatus.INFO, "Setup page: Input password = 'Veri1234', confirm password = 'Veri1234'");
    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");   
    	testLog.log(LogStatus.INFO, "Display TOS");
    	SetupPasswordPage.clickOnchboxTOS();
    	
    	testLog.log(LogStatus.INFO, "--------------------------------------------------TOS document---------------------------------------------------");
    	
    	Thread.sleep(timeOut);
//    	Verify TOS page text
    	String tText = SetupPasswordPage.tosText();   	
    	currentResult = tText.contains(textTOS);
    	Assert.assertTrue(currentResult, "Correct TOS text not found");  
    	testLog.log(LogStatus.INFO, "TOS doc: Found TOS text: " + textTOS + "...: Succesfull");
    	System.out.println(tText);
    	
//		Agree with TOS
    	testLog.log(LogStatus.INFO, "Accept TOS");
    	SetupPasswordPage.clickOnAcceptTOSBtn();
    	Thread.sleep(timeOut - 1000);
    	testLog.log(LogStatus.INFO, "Submit Setup");
    	SetupPasswordPage.clickOnSubmitBtn();
    	
//		Test Login
//    	Setup Login button
    	testLog.log(LogStatus.INFO, "---------------------------------------------------Login page----------------------------------------------------");
    	
    	Thread.sleep(timeOut + 2000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	    	
    	SetupPasswordPage.loginCpBtn_from_Setup();
    	
//    	Compare login Title text with expected
    	tText = SetupPasswordPage.loginTitle();   	
    	currentResult = tText.contains(loginTitle);
    	Assert.assertTrue(currentResult, "Correct login Title not found"); 
    	testLog.log(LogStatus.INFO, "Login page: Found title: " + loginTitle + "...: Succesfull");
    	
//    	Compare login Email text with expected    	
    	tText = SetupPasswordPage.loginEmail();   	
    	currentResult = tText.contains(loginEmail);
    	Assert.assertTrue(currentResult, "Correct login Email text not found"); 
    	testLog.log(LogStatus.INFO, "Login page: Found Email field hint: " + loginEmail + "...: Succesfull");
    	
//    	Compare login Password text with expected  
    	tText = SetupPasswordPage.loginPassword();   	
    	currentResult = tText.contains(loginPassword);
    	Assert.assertTrue(currentResult, "Correct login Password text not found"); 
    	testLog.log(LogStatus.INFO, "Login page: Found Password field hint: " + loginPassword + "...: Succesfull");
    	
//    	Compare login Forgot link text with expected  
    	tText = SetupPasswordPage.loginForgotLink();   	
    	currentResult = tText.contains(loginForgotLink);
    	Assert.assertTrue(currentResult, "Correct login Forgot Link text not found"); 
    	testLog.log(LogStatus.INFO, "Login page: Found Password field hint: " + loginForgotLink + "...: Succesfull");
    	
//    	Compare login button text with expected  
    	tText = SetupPasswordPage.loginBtnLabel();   	
    	currentResult = tText.contains(loginBtnLabel);
    	Assert.assertTrue(currentResult, "Correct login Button text not found"); 
    	testLog.log(LogStatus.INFO, "Login page: Found Login button labelt: " + loginBtnLabel + "...: Succesfull");
    	
//    	Click on Email field, click out, get and Compare mandatory error
    	SetupPasswordPage.loginInputEmail(ulMail);
    	testLog.log(LogStatus.INFO, "Login page: Input Email = ' '");
    	Thread.sleep(timeOut - 1000);
    	tText = SetupPasswordPage.lerrorMandatoryField();   	
    	currentResult = tText.contains(loginErrorMandatoryField);
    	Assert.assertTrue(currentResult, "Correct error on Mandatory email not found"); 
    	testLog.log(LogStatus.INFO, "Login page: Found Mandatory error: : " + loginErrorMandatoryField + ": Succesfull");
     	
//    	Click on Password field, click out, get and Compare mandatory error
    	SetupPasswordPage.loginInputPassword(ulPassword);
    	testLog.log(LogStatus.INFO, "Login page: Input Password = ' '");
    	Thread.sleep(timeOut - 1000);
    	tText = SetupPasswordPage.lerrorMandatoryField();   	
    	currentResult = tText.contains(loginErrorMandatoryField);
    	Assert.assertTrue(currentResult, "Correct error on Mandatory password not found");  
    	testLog.log(LogStatus.INFO, "Login page: Found Mandatory error: : " + loginErrorMandatoryField + ": Succesfull");
    	
//    	Click on Email field, input invalid email, click out, get and Compare validation error
    	ulMail = "InvalidEmail";
    	SetupPasswordPage.loginInputEmail(ulMail);
    	testLog.log(LogStatus.INFO, "Login page: Input Email = " + ulMail);
    	Thread.sleep(timeOut - 1000);
    	tText = SetupPasswordPage.lerrorMandatoryField();   	
    	currentResult = tText.contains(loginErrorValidationMail);
    	Assert.assertTrue(currentResult, "Correct error on Invalid email not found"); 
    	testLog.log(LogStatus.INFO, "Login page: Found Invalid email error: : " + loginErrorValidationMail + ": Succesfull");
    	
//    	Input Not Matched Email and Password. Compare validation error
    	ulMail = "ValidEmail@getnada.com";
    	ulPassword = "Veri1234";
    	testLog.log(LogStatus.INFO, "Login page: Input Email = " + ulMail + ". Input Password = " + ulPassword);
    	SetupPasswordPage.loginInputEmail(ulMail);
    	SetupPasswordPage.loginInputPassword(ulPassword);
    	SetupPasswordPage.loginCpBtn_from_LoginSetup();
    	Thread.sleep(timeOut - 1000);
    	tText = SetupPasswordPage.lerrorMatch();
    	currentResult = tText.contains(loginErrorMatch);
    	Assert.assertTrue(currentResult, "Correct error on Data Not Match not found"); 
    	testLog.log(LogStatus.INFO, "Login page: Found Match data error: : " + loginErrorMatch + ": Succesfull");
    	
//    	Input Valid Email and Password. Login
    	ulMail = mId + "@getnada.com";
    	ulPassword = "Veri1234";
    	testLog.log(LogStatus.INFO, "Login page: Input Email = " + ulMail + ". Input Password = " + ulPassword);
    	SetupPasswordPage.loginInputEmail(ulMail);
    	SetupPasswordPage.loginInputPassword(ulPassword);
    	Thread.sleep(timeOut - 1000);
    	SetupPasswordPage.loginCpBtn_from_LoginSetup();
    	testLog.log(LogStatus.INFO, "Click Login button");
    	
    	testLog.log(LogStatus.INFO, "-------------------------------------------------Agreement page--------------------------------------------------");
    	
//    	Get Title, Text, button label from Agreement page. Compare with Expected.
    	Thread.sleep(timeOut - 1000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	AcceptMerchantAgreementPage AcceptMerchantAgreementPage = (AcceptMerchantAgreementPage) PageFactory.getPage("AcceptMerchantAgreementPage");
    	tText = AcceptMerchantAgreementPage.pageTitle();
    	currentResult = tText.contains(pageAgreementTitle);
    	Assert.assertTrue(currentResult, "Correct Agreement page Title not found"); 
    	testLog.log(LogStatus.INFO, "Agreement page: Found title: " + pageAgreementTitle + "...: Succesfull");
    	
    	tText = AcceptMerchantAgreementPage.pageText();
    	currentResult = tText.contains(pageAgreementText);
    	Assert.assertTrue(currentResult, "Correct Agreement page Text not found"); 
    	testLog.log(LogStatus.INFO, "Agreement page: Found text: " + pageAgreementText + "...: Succesfull");
    	
    	tText = AcceptMerchantAgreementPage.acceptBtnText();
    	currentResult = tText.contains(pageAgreementBtnText);
    	Assert.assertTrue(currentResult, "Correct Agreement button Text not found");
    	testLog.log(LogStatus.INFO, "Agreement page: Found Accept button label: " + pageAgreementBtnText + "...: Succesfull");
    	
//    	Click on Accept Merchant Agreement
    	AcceptMerchantAgreementPage.clickAcceptBtn();
    	testLog.log(LogStatus.INFO, "Click Accept button");
    	
    	testLog.log(LogStatus.INFO, "-----------------------------------------------Agreement document------------------------------------------------");
    	
//    	Get Verifone Merchant Agreement, Compare header, text, button text with expected and Agree
    	Thread.sleep(timeOut - 1000);
    	tText = AcceptMerchantAgreementPage.docHeader();
    	currentResult = tText.contains(docHeader);
    	Assert.assertTrue(currentResult, "Correct document Header not found"); 
    	testLog.log(LogStatus.INFO, "Agreement document: Found document Header: " + docHeader + "...: Succesfull");
    	
    	tText = AcceptMerchantAgreementPage.docText();
    	currentResult = tText.contains(docText);
    	Assert.assertTrue(currentResult, "Correct document Text not found"); 
    	testLog.log(LogStatus.INFO, "Agreement document: Found document Text: " + docText + "...: Succesfull");
    	
    	tText = AcceptMerchantAgreementPage.docAgreeBtnText();
    	currentResult = tText.contains(docAgreeBtnText);
    	Assert.assertTrue(currentResult, "Correct document Text not found"); 
    	testLog.log(LogStatus.INFO, "Agreement document: Found Accept button label: " + docAgreeBtnText + "...: Succesfull");
    	testLog.log(LogStatus.INFO, "Agreement document: Accept");
    	AcceptMerchantAgreementPage.clickDocAcceptBtn();
    	
    	testLog.log(LogStatus.INFO, "--------------------------------------------------Merchant page--------------------------------------------------");
    	
//		Get Merchant View Title and Text
    	Thread.sleep(timeOut - 1000);
    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(1));
    	MerchantViewPage MerchantViewPage = (MerchantViewPage) PageFactory.getPage("MerchantViewPage");
    	tText = MerchantViewPage.pageTitle();
    	currentResult = tText.contains(merchantPageTitle);
    	Assert.assertTrue(currentResult, "Correct document Text not found"); 
    	testLog.log(LogStatus.INFO, "Merchant page: Found page Text: " + merchantPageTitle + "...: Succesfull");
    	
    	Thread.sleep(timeOut);
    	BasePage.driver.quit();
    }

}
