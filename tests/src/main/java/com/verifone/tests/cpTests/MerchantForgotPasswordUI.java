//package com.verifone.tests.cpTests;
//
//import com.relevantcodes.extentreports.LogStatus;
//import com.verifone.entities.EntitiesFactory;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.Select;
//
//import com.verifone.pages.cpPages.AcceptMerchantAgreementPage;
//import com.verifone.pages.cpPages.EmailConfirmPage;
//import com.verifone.pages.cpPages.ForgotPasswordPage;
//import com.verifone.pages.cpPages.LoginPage;
//import com.verifone.pages.cpPages.MerchantViewPage;
//import com.verifone.pages.cpPages.SetupPasswordPage;
//import com.verifone.pages.PageFactory;
//import com.verifone.tests.BaseTest;
//import com.verifone.utils.DataDrivenUtils;
//import com.verifone.utils.apiClient.createMerchant.CreateMerchantTest;
//import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
//import com.verifone.utils.apiClient.getToken.GetTokenApi;
//
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import com.verifone.infra.SeleniumUtils;
//import com.verifone.infra.User;
//import com.verifone.pages.BasePage;
//
//public class MerchantForgotPasswordUI extends BaseTest {
////    private final static String text = "To create your Verifone Account";
////    private final static String errorFormat = "Use at least eight characters";
////    private final static String errorAccept = "You are required to accept our Terms and Conditions and Privacy Policy.";
////    private final static String errorMatch = "Password and Confirm Password must match.";
////    private final static String textTOS = "These Terms of Service for the Verifone Web Portal";
////    private final static String loginTitle = "Login to your Verifone Account";
////    private final static String loginEmail = "Email Address";
////    private final static String loginPassword = "Password";
////    private final static String loginForgotLink = "Forgot Password?";
////    private final static String loginBtnLabel = "LOG IN";
////    private final static String ForgotPageTitle = "Forgot your password?";
////    private final static String ForgotPageText = "Enter the email address associated with your account and";
////    private final static String ForgotPageMailLabelText = "Email";
////    private final static String ForgotPageLnkLoginText = "Login";
////    private final static String ForgotPageBtnSendText = "Send";
////    private final static String ForgotErrorEmptyText = "This field is required.";
////    private final static String ForgotErrorInvalidText = "Field Email should have valid format!";
////    private final static String EmailConfPageTitle = "Email Confirmed!";
////    private final static String EmailConfPageText = "We''ve sent password reset instructions to login into your";
////    private final static String EmailConfPageMutedText = "Note: Check your spam folder if you don''t see";
//
//	private final static int timeOut = 2000;
//    private static String mId = "";
//    private static String ufEmail = "";
//    private static Integer rowNumber=0;
//    private static Integer getRowNumFromFile = 0;
//	final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\merchantForgotPassword.xls";
//
//	@BeforeTest
//	public void startDDTest() throws Exception{
////	 		Get number of Rows from Data driven
//		getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "merchantForgotPassword");
//	}
//
////    Data Provider
//
//	@DataProvider(name = "merchantForgotPassword")
//	public Object[][] dataSupplierLoginData() throws Exception {
//		Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "merchantForgotPassword");
//		return arrayObject;
//	}
//
//	@Test(dataProvider = "merchantForgotPassword", groups = { "Localization" })
//    public void MerchantForgotPasswordUI(String EOAdminMail, String EOAdminPwd, String mailInvText, String actAccountBtnLabel, String setupText, String setupErrorFormat, String setupErrorAccept, String setupErrorMatch, String textTOS,
//    		String loginTitle, String loginEmail, String loginPassword, String loginForgotLink, String loginBtnLabel, String ForgotPageTitle,
//    		String ForgotPageText, String ForgotPageMailLabelText, String ForgotPageLnkLoginText, String ForgotPageBtnSendText,
//    		String ForgotErrorEmptyText, String ForgotErrorInvalidText, String EmailConfPageTitle, String EmailConfPageText, String EmailConfPageMutedText) throws Exception {
//
//        starTestLog("Merchant Forgot Password Test", "Merchant Forgot Password Test");
//        rowNumber = rowNumber+1;
//        testLog.log(LogStatus.INFO, "Data Driven line number: " + rowNumber);
//        testLog.log(LogStatus.INFO, "---------------------------------------------Create Merchant by API---------------------------------------------");
//
//        User user = new User(EOAdminMail, EOAdminPwd);
//        GetTokenApi getTokenApi = new GetTokenApi("testId");
//        String accessToken = getTokenApi.getToken(user);
//        GetEoadminDataApi getEoadminDataApi = new GetEoadminDataApi(accessToken,"testId");
//        mId = new CreateMerchantTest(accessToken, "testId").createMerchant(getEoadminDataApi.getData());
//        System.out.println("MID: " + mId);
//
//
//    	//      Navigate to Getnada
//
//        testLog.log(LogStatus.INFO, "-------------------------------------------------Getnada service-------------------------------------------------");
//    	BasePage.driver.navigate().to("https://getnada.com/#");
//
//    	// Click Add Inbox
//
//    	BasePage.driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();
//
//    	// Put email
//    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
//    	Thread.sleep(timeOut);
//    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(mId);
//
//    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
//    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);
//
//    	//  Accept
//    	BasePage.driver.findElement(By.linkText("ACCEPT")).click();
//
//    	testLog.log(LogStatus.INFO, "Create email inbox: " + mId + "getnada.com: Succesfull");
//
//    	//  Open Email
//    	Thread.sleep(timeOut);
//    	BasePage.driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();
//
//    	testLog.log(LogStatus.INFO, "Found Invitation mail: Succesfull");
//    	//   Get email text
//
//    	Thread.sleep(timeOut);
//
//    	WebElement iFrame = BasePage.driver.findElement(By.id("idIframe"));
//    	BasePage.driver.switchTo().frame(iFrame);
//    	String mailText = BasePage.driver.getPageSource();
//
//    	Boolean langFlag = mailText.contains(mailInvText);
//
//    	System.out.println("Mail text: " + langFlag);
//    	testLog.log(LogStatus.INFO, "Invitation mail include text: " + mailInvText + "...: Succesfull");
//
//    	BasePage.driver.findElement(By.linkText(actAccountBtnLabel)).click();
//    	testLog.log(LogStatus.INFO, "Invitation mail include button: " + actAccountBtnLabel + "...: Succesfull");
//    	testLog.log(LogStatus.INFO, "Click on: " + actAccountBtnLabel + " button: Succesfull");
//
//
//    	//		Setup Password
//    	testLog.log(LogStatus.INFO, "-----------------------------------------------Setup Password page-----------------------------------------------");
//
//    	Thread.sleep(timeOut + 2000);
//    	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//
//    	SetupPasswordPage SetupPasswordPage = (SetupPasswordPage) PageFactory.getPage("SetupPasswordPage");
//    	String pgText = SetupPasswordPage.pageText();
//    	System.out.println(pgText);
//    	boolean currentResult = pgText.contains(setupText);
//    	Assert.assertTrue(currentResult, "Correct text not found");
//    	testLog.log(LogStatus.INFO, "Found Setup page text: " + setupText + "...: Succesfull");
////    	System.out.println(window.navigator.language);
//
//    	JavascriptExecutor jse = (JavascriptExecutor)BasePage.driver;
//    	jse.executeScript("var language = window.navigator.userLanguage || window.navigator.language");
//
//    	//		Input blank space as Password and ConfirmPassword
//
//    	testLog.log(LogStatus.INFO, "Setup page: Input password = ' ', confirm password = ' '");
//    	SetupPasswordPage.SetupPasswordPageCp(" ", " ");
//    	SetupPasswordPage.clickOnSubmitBtn();
//    	String errText = SetupPasswordPage.errorFormat();
//    	System.out.println(errText);
//    	currentResult = errText.contains(setupErrorFormat);
//    	Assert.assertTrue(currentResult, "Correct format error not found");
//    	testLog.log(LogStatus.INFO, "Found Format error: " + setupErrorFormat + "...: Succesfull");
//
//    	//		Not agree with TOS
//
//    	testLog.log(LogStatus.INFO, "Setup page: Not accept TOS");
//    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
//    	SetupPasswordPage.clickOnSubmitBtn();
//    	errText = SetupPasswordPage.errorTOS();
//    	System.out.println(errText);
//    	currentResult = errText.contains(setupErrorAccept);
//    	Assert.assertTrue(currentResult, "Correct TOS error not found");
//    	testLog.log(LogStatus.INFO, "Found missing accept TOS error: " + setupErrorAccept + "...: Succesfull");
//
//    	//     Input different Password and ConfirmPassword
//
//    	testLog.log(LogStatus.INFO, "Setup page: Input password = 'Veri1234', confirm password = 'Veri5234'");
//    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri5234");
//    	SetupPasswordPage.clickOnSubmitBtn();
//    	Thread.sleep(timeOut);
//    	errText = SetupPasswordPage.errorMatch();
//    	System.out.println(errText);
//    	currentResult = errText.contains(setupErrorMatch);
//    	Assert.assertTrue(currentResult, "Correct password match error not found");
//    	testLog.log(LogStatus.INFO, "Found Match error: " + setupErrorMatch + "...: Succesfull");
//
////      Input correct Password and ConfirmPassword, pass to TOS window, verify text
//
//    	testLog.log(LogStatus.INFO, "Setup page: Input password = 'Veri1234', confirm password = 'Veri1234'");
//    	SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
//    	testLog.log(LogStatus.INFO, "Display TOS");
//    	SetupPasswordPage.clickOnchboxTOS();
//
//    	testLog.log(LogStatus.INFO, "--------------------------------------------------TOS document---------------------------------------------------");
//
//    	Thread.sleep(timeOut);
////    	Verify TOS page text
//    	String tText = SetupPasswordPage.tosText();
//    	currentResult = tText.contains(textTOS);
//    	Assert.assertTrue(currentResult, "Correct TOS text not found");
//    	testLog.log(LogStatus.INFO, "TOS doc: Found TOS text: " + textTOS + "...: Succesfull");
//    	System.out.println(tText);
//
////		Agree with TOS
////    	WebElement elem = BasePage.driver.findElement(By.xpath("//*[@class='btn btn-primary btn-raised btn-accept']"));
////    	elem.click();
//    	testLog.log(LogStatus.INFO, "Accept TOS");
//    	SetupPasswordPage.clickOnAcceptTOSBtn();
//    	Thread.sleep(timeOut - 1000);
//    	testLog.log(LogStatus.INFO, "Submit Setup");
//    	SetupPasswordPage.clickOnSubmitBtn();
//
////		Test Login
////    	Setup Login button
//
//    	Thread.sleep(timeOut + 2000);
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//    	testLog.log(LogStatus.INFO, "Navigate to Login page");
//    	SetupPasswordPage.loginCpLnk_from_LoginSetup();
//
//    	testLog.log(LogStatus.INFO, "---------------------------------------------------Login page----------------------------------------------------");
//
////    	Click on Forgot Password link
//    	Thread.sleep(timeOut + 1000);
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//    	testLog.log(LogStatus.INFO, "Navigate to Forgot Password page");
//    	SetupPasswordPage.loginForgotLinkClick();
//
//    	testLog.log(LogStatus.INFO, "----------------------------------------------Forgot Password page-----------------------------------------------");
//
////    	Compare Title, Text, Email text, Send button label, Login link label with expected on Forgot Password page
//    	Thread.sleep(timeOut + 3000);
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//    	ForgotPasswordPage ForgotPasswordPage = (ForgotPasswordPage) PageFactory.getPage("ForgotPasswordPage");
//
////    	Compare Forgot page title with expected
//    	tText = ForgotPasswordPage.pageTitle();
//    	currentResult = tText.contains(ForgotPageTitle);
//    	Assert.assertTrue(currentResult, "Correct Forgot Password page Title not found");
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Found title: " + ForgotPageTitle + "...: Succesfull");
//
////    	Compare Forgot page text with expected
//    	tText = ForgotPasswordPage.pageText();
//    	currentResult = tText.contains(ForgotPageText);
//    	Assert.assertTrue(currentResult, "Correct Forgot Password page Text not found");
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Found text: " + ForgotPageText + "...: Succesfull");
//
////    	Compare Forgot page Mail label with expected
//    	tText = ForgotPasswordPage.mailLabelText();
//    	currentResult = tText.contains(ForgotPageMailLabelText);
//    	Assert.assertTrue(currentResult, "Correct Forgot Password page Mail Label Text not found");
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Found mail field hint: " + ForgotPageMailLabelText + "...: Succesfull");
//
////    	Compare Forgot page Login link text with expected
//    	tText = ForgotPasswordPage.btnSendText();
//    	currentResult = tText.contains(ForgotPageBtnSendText);
//    	Assert.assertTrue(currentResult, "Correct Forgot Password page Send button Text not found");
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Found Send button label: " + ForgotPageBtnSendText + "...: Succesfull");
//
////    	Compare Forgot page Login link text with expected
//    	tText = ForgotPasswordPage.lnkLoginText();
//    	currentResult = tText.contains(ForgotPageLnkLoginText);
//    	Assert.assertTrue(currentResult, "Correct Forgot Password page Login link Text not found");
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Found Login link Text: " + ForgotPageLnkLoginText + "...: Succesfull");
//
////    	Click on Forgot page Login link
//    	ForgotPasswordPage.clickLoginLnk();
//
////    	Login Page
//    	testLog.log(LogStatus.INFO, "---------------------------------------------------Login page----------------------------------------------------");
//
//    	Thread.sleep(timeOut + 3000);
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//
////    	LogIn Page: Get Title, Email label, Password Label, button label and Compare with Expected.
//
////    	Compare login Title text with expected
//    	tText = SetupPasswordPage.loginTitle();
//    	currentResult = tText.contains(loginTitle);
//    	Assert.assertTrue(currentResult, "Correct login Title not found");
//    	testLog.log(LogStatus.INFO, "Login page: Found title: " + loginTitle + "...: Succesfull");
//
////    	Compare login Email text with expected
//    	tText = SetupPasswordPage.loginEmail();
//    	currentResult = tText.contains(loginEmail);
//    	Assert.assertTrue(currentResult, "Correct login Email text not found");
//    	testLog.log(LogStatus.INFO, "Login page: Found Email field hint: " + loginEmail + "...: Succesfull");
//
////    	Compare login Password text with expected
//    	tText = SetupPasswordPage.loginPassword();
//    	currentResult = tText.contains(loginPassword);
//    	Assert.assertTrue(currentResult, "Correct login Password text not found");
//    	testLog.log(LogStatus.INFO, "Login page: Found Password field hint: " + loginPassword + "...: Succesfull");
//
////    	Compare login Forgot link text with expected
//    	tText = SetupPasswordPage.loginForgotLink();
//    	currentResult = tText.contains(loginForgotLink);
//    	Assert.assertTrue(currentResult, "Correct login Forgot Link text not found");
//    	testLog.log(LogStatus.INFO, "Login page: Found Forgot Password link: " + loginForgotLink + "...: Succesfull");
//
////    	Compare login button text with expected
//    	tText = SetupPasswordPage.loginBtnLabel();
//    	currentResult = tText.contains(loginBtnLabel);
//    	Assert.assertTrue(currentResult, "Correct login Button text not found");
//    	testLog.log(LogStatus.INFO, "Login page: Found Login button hint: " + loginBtnLabel + "...: Succesfull");
//
////    	Login Page: Click on Forgot Password link
//    	testLog.log(LogStatus.INFO, "Click on Forgot link");
//    	SetupPasswordPage.loginForgotLinkClick();
//
//    	testLog.log(LogStatus.INFO, "----------------------------------------------Forgot Password page-----------------------------------------------");
//
////    	Forgot Password Page: Email empty. Get error and compare with expected
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//    	Thread.sleep(timeOut - 1000);
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Input mail: " + ufEmail + " and Send");
//    	ForgotPasswordPage.InputEmail(ufEmail);
//    	ForgotPasswordPage.clickBtnSend();
//
//    	Thread.sleep(timeOut - 1000);
//    	tText = ForgotPasswordPage.errorEmptyText();
//    	currentResult = tText.contains(ForgotErrorEmptyText);
//    	Assert.assertTrue(currentResult, "Correct Error Text not found");
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Found Mandatory error: " + ForgotErrorEmptyText + " : Succesfull");
//
////    	Forgot Password Page: Email Invalid. Get error and compare with expected
//    	Thread.sleep(timeOut - 1000);
//    	ufEmail = "InvalidMail";
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Input mail: " + ufEmail + " and Send");
//    	ForgotPasswordPage.InputEmail(ufEmail);
//    	ForgotPasswordPage.clickBtnSend();
//    	Thread.sleep(1000);
//
//    	tText = ForgotPasswordPage.errorEmptyText();
//    	currentResult = tText.contains(ForgotErrorInvalidText);
//    	Assert.assertTrue(currentResult, "Correct Error Text not found");
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Found Format error: " + ForgotErrorInvalidText + " : Succesfull");
//
////    	Forgot Password Page: Email Not Match. Get error and compare with expected
//    	Thread.sleep(timeOut - 1000);
//    	ufEmail = mId + "@getnada.com";
//    	testLog.log(LogStatus.INFO, "Forgot Password page: Input mail: " + ufEmail + " and Send");
//    	ForgotPasswordPage.InputEmail(ufEmail);
//    	ForgotPasswordPage.clickBtnSend();
//
//    	testLog.log(LogStatus.INFO, "---------------------------------------------Email Confirmation page---------------------------------------------");
//
////    	Email Confirmation Page: Get Title text, Page text, Muted text and compare with expected
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//    	Thread.sleep(timeOut - 1000);
//    	EmailConfirmPage EmailConfirmPage = (EmailConfirmPage) PageFactory.getPage("EmailConfirmPage");
//
////    	Compare Email Confirmation page title with expected
//    	tText = EmailConfirmPage.pageTitle();
//    	currentResult = tText.contains(EmailConfPageTitle);
//    	Assert.assertTrue(currentResult, "Correct Email Cobfirmation page Title not found");
//    	testLog.log(LogStatus.INFO, "Email Confirmation page: Found Title: " + EmailConfPageTitle + " : Succesfull");
//
////    	Compare Email Confirmation page text with expected
//    	tText = EmailConfirmPage.pageText();
//    	currentResult = tText.contains(EmailConfPageText);
//    	Assert.assertTrue(currentResult, "Correct Email Cobfirmation page Text not found");
//    	testLog.log(LogStatus.INFO, "Email Confirmation page: Found Text: " + EmailConfPageText + "... : Succesfull");
//
////    	Compare Email Confirmation page muted text with expected
//    	tText = EmailConfirmPage.pageTextMuted();
//    	currentResult = tText.contains(EmailConfPageMutedText);
//    	Assert.assertTrue(currentResult, "Correct Email Cobfirmation page Muted Text not found");
//    	testLog.log(LogStatus.INFO, "Email Confirmation page: Found Muted Text: " + EmailConfPageMutedText + "... : Succesfull");
//
//    	Thread.sleep(timeOut);
//    	BasePage.driver.quit();
//    }
//
//}
//
