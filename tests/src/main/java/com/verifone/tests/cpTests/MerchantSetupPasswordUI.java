package com.verifone.tests.cpTests;

import com.mongodb.assertions.Assertions;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.entities.EntitiesFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import com.verifone.utils.apiClient.createMerchant.CreateMerchantDE;
import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
import com.verifone.utils.apiClient.getToken.GetTokenApi;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.utils.DataDrivenUtils;

import static com.verifone.pages.BasePage.testLog;

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
	private static String mailActivateButton = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/p[5]/a";
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
	private static Boolean TestPassFlag = true;
	private static String capScreenShootPath;

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

	@Test(testName = "Merchant Setup Password Test", dataProvider = "merchantSetupPassword", groups = { "Localization" })
//    @Test(groups = {"CP-portal"})
	public void MerchantSetupPasswordUI(String Lang, String EOAdminMail, String EOAdminPwd, String mailInvText, String actAccountBtnLabel, String setupTitle, String setupText, String setupPasswordHint,
										String setupConfirmPasswordHint, String setupCheckBox, String setupCheckBoxAgr, String setupSubmitBtn, String setupErrorFormat, String setupErrorAccept, String setupErrorAcceptAgr, String setupErrorMatch, String textTOS1,
										String textTOS2, String textTOS3, String textTOS4, String textTOS5, String TOSLnk, String TOSDeclineBtn, String TOSAgreeBtn,
										String textAgreement1,
										String textAgreement2, String textAgreement3, String textAgreement4, String textAgreement5, String AgreementLnk, String AgreementDeclineBtn, String AgreementAgreeBtn,
										String PasswordSetupFinalTitle, String PasswordSetupFinalText) throws Exception {
		WebDriver driver = new HomePage().getDriver();
//        starTestLog("Merchant Setup Password Test", "Merchant Setup Password Test");
		rowNumber = rowNumber+1;
		testLog.pass( "Data Driven line number: " + rowNumber);

		testLog.pass( "---------------------------------------------Create Merchant by API---------------------------------------------");

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
//    	BasePage.driver = SeleniumUtils.getDriver("CHROME");
		Thread.sleep(timeOut + 2000);
		testLog.pass( "-------------------------------------------------Getnada service-------------------------------------------------");
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
		testLog.pass( "Create email inbox: " + mId + "getnada.com: Succesfull");

		//  Open Email
		Thread.sleep(timeOut);
		driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();
		testLog.pass( "Found Invitation mail: Succesfull");

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

		testLog.pass("Click on: " + btnText + " button: Succesfull");


//		Setup Password
		testLog.pass( "-----------------------------------------------Setup Password page-----------------------------------------------");

		Thread.sleep(timeOut + 2000);
		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(1));

		SetupPasswordPage SetupPasswordPage = (SetupPasswordPage) PageFactory.getPage("SetupPasswordPage");

		String pgText = SetupPasswordPage.pageTitle();
		System.out.println(pgText);
		currentResult = pgText.contains(setupTitle);
		if (currentResult == true) {
			testLog.pass( "Found Setup page title: " + setupTitle + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Setup page title: " + pgText + ". Expected: " + setupTitle);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		pgText = SetupPasswordPage.pageText();
		System.out.println(pgText);
		currentResult = pgText.contains(setupText);
		if (currentResult == true) {
			testLog.pass( "Found Setup page text: " + setupText + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Setup page text: " + pgText + ". Expected: " + setupText);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		pgText = SetupPasswordPage.setupPasswordHint();
		System.out.println(pgText);
		currentResult = pgText.contains(setupPasswordHint);
		if (currentResult == true) {
			testLog.pass( "Found Setup page Password Hint: " + setupPasswordHint + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Setup page Password Hint: " + pgText + ". Expected: " + setupPasswordHint);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		pgText = SetupPasswordPage.setupConfirmPasswordHint();
		System.out.println(pgText);
		currentResult = pgText.contains(setupConfirmPasswordHint);
		if (currentResult == true) {
			testLog.pass( "Found Setup page Confirm Password Hint: " + setupConfirmPasswordHint + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Setup page Confirm Password Hint: " + pgText + ". Expected: " + setupConfirmPasswordHint);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		pgText = SetupPasswordPage.setupCheckBoxText();
		System.out.println(pgText);
		currentResult = pgText.contains(setupCheckBox);
		if (currentResult == true) {
			testLog.pass( "Found Setup page check box label: " + setupCheckBox + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Setup page check box label: " + pgText + ". Expected: " + setupCheckBox);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	pgText = SetupPasswordPage.setupCheckBoxAgrText();
//    	System.out.println(pgText);
//    	currentResult = pgText.contains(setupCheckBoxAgr);
//    	if (currentResult == true) {
//    		testLog.pass( "Found Setup page check box label: " + setupCheckBoxAgr + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Found Setup page check box label: " + pgText + ". Expected: " + setupCheckBoxAgr);
//    		capScreenShootPath = seleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}

		pgText = SetupPasswordPage.setupSubmitBtnLabel();
		System.out.println(pgText);
		currentResult = pgText.contains(setupSubmitBtn);
		if (currentResult == true) {
			testLog.pass( "Found Setup page Submit btn label: " + setupSubmitBtn + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Setup page Submit btn label: " + pgText + ". Expected: " + setupSubmitBtn);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		//		Input blank space as Password and ConfirmPassword

		testLog.pass( "Setup page: Input password = ' ', confirm password = ' '");
		SetupPasswordPage.SetupPasswordPageCp(" ", " ");
		SetupPasswordPage.clickOnSubmitBtn();
		String errText = SetupPasswordPage.errorFormat();
		System.out.println(errText);
		currentResult = errText.contains(setupErrorFormat);
		if (currentResult == true) {
			testLog.pass( "Found Format Error: " + setupErrorFormat + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Format Error: " + errText + ". Expected: " + setupErrorFormat);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		//		Not agree with TOS and aGREEMENT

		testLog.pass( "Setup page: Not accept TOS");
		SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
		SetupPasswordPage.clickOnSubmitBtn();
		errText = SetupPasswordPage.errorTOS();
		System.out.println(errText);
		currentResult = errText.contains(setupErrorAccept);
		if (currentResult == true) {
			testLog.pass( "Found Accept TOS Error: " + setupErrorAccept + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Accept TOS Error: " + errText + ". Expected: " + setupErrorAccept);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//    	errText = SetupPasswordPage.errorAgr();
//    	System.out.println(errText);
//    	currentResult = errText.contains(setupErrorAcceptAgr);
//    	if (currentResult == true) {
//    		testLog.pass( "Found Accept TOS Error: " + setupErrorAcceptAgr + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Found Accept TOS Error: " + errText + ". Expected: " + setupErrorAcceptAgr);
//    		capScreenShootPath = seleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}


//     Input different Password and ConfirmPassword

		testLog.pass( "Setup page: Input password = 'Veri1234', confirm password = 'Veri5234'");
		SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri5234");
		SetupPasswordPage.clickOnSubmitBtn();
		Thread.sleep(timeOut);
		errText = SetupPasswordPage.errorMatch();
		System.out.println(errText);
		currentResult = errText.contains(setupErrorMatch);
		if (currentResult == true) {
			testLog.pass( "Found Match Error: " + setupErrorMatch + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Found Match Error: " + errText + ". Expected: " + setupErrorMatch);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//      Input correct Password and ConfirmPassword, pass to TOS window, verify text

		testLog.pass( "Setup page: Input password = 'Veri1234', confirm password = 'Veri1234'");
		SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
		testLog.pass( "Display TOS");
//    	SetupPasswordPage.clickOnchboxTOS();
		SetupPasswordPage.setupTOSLinkClick();
		testLog.pass( "--------------------------------------------------TOS document---------------------------------------------------");

		Thread.sleep(timeOut);

//    	Verify TOS page text
		String tText = SetupPasswordPage.tosText();
		currentResult = tText.contains(textTOS1);
		if (currentResult == true) {
			testLog.pass( "TOS doc: Found TOS text: " + textTOS1 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "TOS doc: Found TOS text: " + tText + ". Expected: " + textTOS1);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		currentResult = tText.contains(textTOS2);
		if (currentResult == true) {
			testLog.pass( "TOS doc: Found TOS text: " + textTOS2 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "TOS doc: Found TOS text: " + tText + ". Expected: " + textTOS2);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		currentResult = tText.contains(textTOS3);
		if (currentResult == true) {
			testLog.pass( "TOS doc: Found TOS text: " + textTOS3 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "TOS doc: Found TOS text: " + tText + ". Expected: " + textTOS3);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		currentResult = tText.contains(textTOS4);
		if (currentResult == true) {
			testLog.pass( "TOS doc: Found TOS text: " + textTOS4 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "TOS doc: Found TOS text: " + tText + ". Expected: " + textTOS4);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		currentResult = tText.contains(textTOS5);
		if (currentResult == true) {
			testLog.pass( "TOS doc: Found TOS text: " + textTOS5 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "TOS doc: Found TOS text: " + tText + ". Expected: " + textTOS5);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}
		System.out.println(tText);

//    	tText = SetupPasswordPage.tosLnkText();
//    	currentResult = tText.contains(TOSLnk);
//    	if (currentResult == true) {
//    		testLog.pass( "TOS doc: Found link text: " + TOSLnk + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "TOS doc: Found link text: " + tText + ". Expected: " + TOSLnk);
//    		capScreenShootPath = seleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//    	System.out.println(tText);

//    	tText = SetupPasswordPage.tosDeclineBtnLabel();
//    	currentResult = tText.contains(TOSDeclineBtn);
//    	if (currentResult == true) {
//    		testLog.pass( "TOS doc: Found Decline button label: " + TOSDeclineBtn + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "TOS doc: Found Decline button label: " + tText + ". Expected: " + TOSDeclineBtn);
//    		capScreenShootPath = seleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//    	System.out.println(tText);

//    	tText = SetupPasswordPage.tosAgreeBtnLabel();
//    	currentResult = tText.contains(TOSAgreeBtn);
//    	if (currentResult == true) {
//    		testLog.pass( "TOS doc: Found Agree button label: " + TOSAgreeBtn + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "TOS doc: Found Agree button label: " + tText + ". Expected: " + TOSAgreeBtn);
//    		capScreenShootPath = seleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//    	System.out.println(tText);

//		Agree with TOS
		testLog.pass( "Accept TOS");
		SetupPasswordPage.tosClose();
		Thread.sleep(timeOut - 1000);

//      Pass to Agreement window, verify text

		testLog.pass( "Display Agreement");
		SetupPasswordPage.setupAgreementLinkClick();

		testLog.pass( "-----------------------------------------------Agreement document------------------------------------------------");

		Thread.sleep(timeOut);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(2));

		AgreementPage AgreementPage = (AgreementPage) PageFactory.getPage("AgreementPage");
//    	Verify Agreement page text
		tText = AgreementPage.pageText();
		currentResult = tText.contains(textAgreement1);
		if (currentResult == true) {
			testLog.pass( "Agreement doc: Found Agreement text: " + textAgreement1 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Agreement doc: Found Agreement text: " + tText + ". Expected: " + textAgreement1);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		currentResult = tText.contains(textAgreement2);
		if (currentResult == true) {
			testLog.pass( "Agreement doc: Found Agreement text: " + textAgreement2 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Agreement doc: Found Agreement text: " + tText + ". Expected: " + textAgreement2);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		currentResult = tText.contains(textAgreement3);
		if (currentResult == true) {
			testLog.pass( "Agreement doc: Found Agreement text: " + textAgreement3 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Agreement doc: Found Agreement text: " + tText + ". Expected: " + textAgreement3);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		currentResult = tText.contains(textAgreement4);
		if (currentResult == true) {
			testLog.pass( "Agreement doc: Found Agreement text: " + textAgreement4 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Agreement doc: Found Agreement text: " + tText + ". Expected: " + textAgreement4);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

		currentResult = tText.contains(textAgreement5);
		if (currentResult == true) {
			testLog.pass( "Agreement doc: Found Agreement text: " + textAgreement5 + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Agreement doc: Found Agreement text: " + tText + ". Expected: " + textAgreement5);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}
		System.out.println(tText);

//    	tText = SetupPasswordPage.agreementLnkText();
//    	currentResult = tText.contains(AgreementLnk);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement doc: Found link text: " + AgreementLnk + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement doc: Found link text: " + tText + ". Expected: " + AgreementLnk);
//    		capScreenShootPath = seleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//    	System.out.println(tText);

//    	tText = SetupPasswordPage.agreementDeclineBtnLabel();
//    	currentResult = tText.contains(AgreementDeclineBtn);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement doc: Found Decline button label: " + AgreementDeclineBtn + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement doc: Found Decline button label: " + tText + ". Expected: " + AgreementDeclineBtn);
//    		capScreenShootPath = seleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//    	System.out.println(tText);

//    	tText = SetupPasswordPage.agreementAgreeBtnLabel();
//    	currentResult = tText.contains(AgreementAgreeBtn);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement doc: Found Agree button label: " + AgreementAgreeBtn + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement doc: Found Agree button label: " + tText + ". Expected: " + AgreementAgreeBtn);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//    	System.out.println(tText);

//		Agree with Agreement
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(1));

		SetupPasswordPage = (SetupPasswordPage) PageFactory.getPage("SetupPasswordPage");
		testLog.pass( "Accept Agreement");
		SetupPasswordPage.clickOnchboxTOS();
		Thread.sleep(timeOut - 1000);
		testLog.pass( "Submit Setup");
		SetupPasswordPage.clickOnSubmitBtn();

//		Test Password Setup Final Page

		testLog.pass( "---------------------------------------Password Setup Final page------------------------------------------");

		Thread.sleep(timeOut + 2000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(1));

		PasswordSetupPage PasswordSetupPage = (PasswordSetupPage) PageFactory.getPage("PasswordSetupPage");

//	Compare Password Setup Page Title with expected
		tText = PasswordSetupPage.pageTitle();
		currentResult = tText.contains(PasswordSetupFinalTitle);
		if (currentResult == true) {
			testLog.pass( "Password Setup Final page: Found title: " + PasswordSetupFinalTitle + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Password Setup Final page: Found title: " + tText + ". Expected: " + PasswordSetupFinalTitle);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}

//	Compare Password Setup Page text with expected
		tText = PasswordSetupPage.pageText();
		currentResult = tText.contains(PasswordSetupFinalText);
		if (currentResult == true) {
			testLog.pass( "Password Setup Final pag: Found text: " + PasswordSetupFinalText + "...: Succesfull");
		} else {
			TestPassFlag = false;
			testLog.error( "Password Setup Final pag: Found text: " + tText + ". Expected: " + PasswordSetupFinalText);
			capScreenShootPath = seleniumUtils.getScreenshot();
			testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
			testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
		}


////		Test Welcome Page
//
//    	testLog.pass( "-------------------------------------------------Welcome page----------------------------------------------------");
//
//    	Thread.sleep(timeOut + 2000);
//    	availableWindows = new ArrayList<String>(driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//
//    	WelcomePage WelcomePage = (WelcomePage) PageFactory.getPage("WelcomePage");
//
////    	Compare Welcome Title with expected
//    	tText = WelcomePage.pageTitle();
//    	currentResult = tText.contains(welcomeTitle);
//    	if (currentResult == true) {
//    		testLog.pass( "Welcome page: Found title: " + welcomeTitle + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Welcome page: Found title: " + tText + ". Expected: " + welcomeTitle);
//    		capScreenShootPath = seleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Compare Welcome text with expected
//    	tText = WelcomePage.pageText1();
//    	currentResult = tText.contains(welcomeText1);
//    	if (currentResult == true) {
//    		testLog.pass( "Welcome page: Found text: " + welcomeText1 + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Welcome page: Found text: " + tText + ". Expected: " + welcomeText1);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Compare Welcome text with expected
//    	tText = WelcomePage.pageText2();
//    	currentResult = tText.contains(welcomeText2);
//    	if (currentResult == true) {
//    		testLog.pass( "Welcome page: Found text: " + welcomeText2 + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Welcome page: Found text: " + tText + ". Expected: " + welcomeText2);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Compare Welcome page LogIn button label with expected
//    	tText = WelcomePage.loginBtnLabel();
//    	currentResult = tText.contains(welcomeLoginBtn);
//    	if (currentResult == true) {
//    		testLog.pass( "Welcome page: Found LogIn button label: " + welcomeLoginBtn + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Welcome page: Found LogIn button label: " + tText + ". Expected: " + welcomeLoginBtn);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Click Login Button
//    	WelcomePage.clickLoginBtn();
//
////		Test Login
////    	Setup Login button
//    	testLog.pass( "---------------------------------------------------Login page----------------------------------------------------");
//
//    	Thread.sleep(timeOut + 2000);
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//
////    	Compare loginAndCheck Title text with expected
//    	tText = SetupPasswordPage.loginTitle();
//    	currentResult = tText.contains(loginTitle);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found title: " + loginTitle + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found title: " + tText + ". Expected: " + loginTitle);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Compare loginAndCheck Email text with expected
//    	tText = SetupPasswordPage.loginEmail();
//    	currentResult = tText.contains(loginEmail);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found Email field hint: " + loginEmail + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found Email field hint: " + tText + ". Expected: " + loginEmail);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Compare loginAndCheck Password text with expected
//    	tText = SetupPasswordPage.loginPassword();
//    	currentResult = tText.contains(loginPassword);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found Password field hint: " + loginPassword + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found Password field hint: " + tText + ". Expected: " + loginPassword);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Compare loginAndCheck Forgot link text with expected
//    	tText = SetupPasswordPage.loginForgotLink();
//    	currentResult = tText.contains(loginForgotLink);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found Password field hint: " + loginForgotLink + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found Password field hint: " + tText + ". Expected: " + loginForgotLink);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Compare loginAndCheck button text with expected
//    	tText = SetupPasswordPage.loginBtnLabel();
//    	currentResult = tText.contains(loginBtnLabel);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found Login button label: " + loginBtnLabel + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found Login button label: " + tText + ". Expected: " + loginBtnLabel);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Click on Email field, click out, get and Compare mandatory error
//    	SetupPasswordPage.loginInputEmail(ulMail);
//    	testLog.pass( "Login page: Input Email = ' '");
//    	Thread.sleep(timeOut - 1000);
//    	tText = SetupPasswordPage.lerrorMandatoryField();
//    	currentResult = tText.contains(loginErrorMandatoryField);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found Mandatory error: " + loginErrorMandatoryField + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found Mandatory error: " + tText + ". Expected: " + loginErrorMandatoryField);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Click on Password field, click out, get and Compare mandatory error
//    	SetupPasswordPage.loginInputPassword(ulPassword);
//    	testLog.pass( "Login page: Input Password = ' '");
//    	Thread.sleep(timeOut - 1000);
//    	tText = SetupPasswordPage.lerrorMandatoryField();
//    	currentResult = tText.contains(loginErrorMandatoryField);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found Mandatory error: " + loginErrorMandatoryField + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found Mandatory error: " + tText + ". Expected: " + loginErrorMandatoryField);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Click on Email field, input invalid email, click out, get and Compare validation error
//    	ulMail = "InvalidEmail";
//    	SetupPasswordPage.loginInputEmail(ulMail);
//    	testLog.pass( "Login page: Input Email = " + ulMail);
//    	Thread.sleep(timeOut - 1000);
//    	tText = SetupPasswordPage.lerrorMandatoryField();
//    	currentResult = tText.contains(loginErrorValidationMail);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found Invalid email error: " + loginErrorValidationMail + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found Invalid email error: " + tText + ". Expected: " + loginErrorValidationMail);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Input Not Matched Email and Password. Compare validation error
//    	ulMail = "ValidEmail@getnada.com";
//    	ulPassword = "Veri1234";
//    	testLog.pass( "Login page: Input Email = " + ulMail + ". Input Password = " + ulPassword);
//    	SetupPasswordPage.loginInputEmail(ulMail);
//    	SetupPasswordPage.loginInputPassword(ulPassword);
//    	SetupPasswordPage.loginCpBtn_from_LoginSetup();
//    	Thread.sleep(timeOut - 1000);
//    	tText = SetupPasswordPage.lerrorMatch();
//    	currentResult = tText.contains(loginErrorMatch);
//    	if (currentResult == true) {
//    		testLog.pass( "Login page: Found Match data error: " + loginErrorMatch + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Login page: Found Match data error: " + tText + ". Expected: " + loginErrorMatch);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Input Valid Email and Password. Login
//    	ulMail = mId + "@getnada.com";
//    	ulPassword = "Veri1234";
//    	testLog.pass( "Login page: Input Email = " + ulMail + ". Input Password = " + ulPassword);
//    	SetupPasswordPage.loginInputEmail(ulMail);
//    	SetupPasswordPage.loginInputPassword(ulPassword);
//    	Thread.sleep(timeOut - 1000);
//    	SetupPasswordPage.loginCpBtn_from_LoginSetup();
//    	testLog.pass( "Click Login button");
//
//    	testLog.pass( "-------------------------------------------------Agreement page--------------------------------------------------");
//
////    	Get Title, Text, button label from Agreement page. Compare with Expected.
//    	Thread.sleep(timeOut - 1000);
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//    	AcceptMerchantAgreementPage AcceptMerchantAgreementPage = (AcceptMerchantAgreementPage) PageFactory.getPage("AcceptMerchantAgreementPage");
//    	tText = AcceptMerchantAgreementPage.pageTitle();
//    	currentResult = tText.contains(pageAgreementTitle);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement page: Found title: " + pageAgreementTitle + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement page: Found title: " + tText + ". Expected: " + pageAgreementTitle);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = AcceptMerchantAgreementPage.pageText();
//    	currentResult = tText.contains(pageAgreementText);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement page: Found text: " + pageAgreementText + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement page: Found text: " + tText + ". Expected: " + pageAgreementText);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = AcceptMerchantAgreementPage.acceptBtnText();
//    	currentResult = tText.contains(pageAgreementBtnText);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement page: Found Accept button label: " + pageAgreementBtnText + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement page: Found Accept button label: " + tText + ". Expected: " + pageAgreementBtnText);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
////    	Click on Accept Merchant Agreement
//    	AcceptMerchantAgreementPage.clickAcceptBtn();
//    	testLog.pass( "Click Accept button");
//
//    	testLog.pass( "-----------------------------------------------Agreement document------------------------------------------------");
//
////    	Get Verifone Merchant Agreement, Compare header, text, button text with expected and Agree
//    	Thread.sleep(timeOut - 1000);
//    	tText = AcceptMerchantAgreementPage.docHeader();
//    	currentResult = tText.contains(docHeader);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement document: Found document Header: " + docHeader + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement document: Found document Header: " + tText + ". Expected: " + docHeader);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = AcceptMerchantAgreementPage.docHeader2();
//    	currentResult = tText.contains(docHeader2);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement document: Found document Header2: " + docHeader2 + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement document: Found document Header2: " + tText + ". Expected: " + docHeader2);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = AcceptMerchantAgreementPage.docHeader3();
//    	currentResult = tText.contains(docHeader3);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement document: Found document Header3: " + docHeader3 + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement document: Found document Header3: " + tText + ". Expected: " + docHeader3);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = AcceptMerchantAgreementPage.docText();
//    	currentResult = tText.contains(docText);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement document: Found document Text: " + docText + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement document: Found document Text: " + tText + ". Expected: " + docText);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = AcceptMerchantAgreementPage.docDeclineBtnText();
//    	currentResult = tText.contains(docDeclineBtnText);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement document: Found Decline button label: " + docDeclineBtnText + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement document: Found Decline button label: " + tText + ". Expected: " + docDeclineBtnText);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = AcceptMerchantAgreementPage.docAgreeBtnText();
//    	currentResult = tText.contains(docAgreeBtnText);
//    	if (currentResult == true) {
//    		testLog.pass( "Agreement document: Found Accept button label: " + docAgreeBtnText + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Agreement document: Found Accept button label: " + tText + ". Expected: " + docAgreeBtnText);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//    	testLog.pass( "Agreement document: Accept");
//    	AcceptMerchantAgreementPage.clickDocAcceptBtn();
//
//    	testLog.pass( "--------------------------------------------------Merchant page--------------------------------------------------");
//
////		Get Merchant View Title and Text
//    	Thread.sleep(timeOut - 1000);
//    	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//    	BasePage.driver.switchTo().window(availableWindows.get(1));
//    	MerchantViewPage MerchantViewPage = (MerchantViewPage) PageFactory.getPage("MerchantViewPage");
//    	tText = MerchantViewPage.pageTitle();
//    	currentResult = tText.contains(merchantPageTitle);
//    	if (currentResult == true) {
//    		testLog.pass( "Merchant page: Found page Title: " + merchantPageTitle + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Merchant page: Found page Title: " + tText + ". Expected: " + merchantPageTitle);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = MerchantViewPage.pageText1();
//    	currentResult = tText.contains(merchantPageText1);
//    	if (currentResult == true) {
//    		testLog.pass( "Merchant page: Found page Text: " + merchantPageText1 + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Merchant page: Found page Text: " + tText + ". Expected: " + merchantPageText1);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
//    	tText = MerchantViewPage.pageText2();
//    	currentResult = tText.contains(merchantPageText2);
//    	if (currentResult == true) {
//    		testLog.pass( "Merchant page: Found page Text: " + merchantPageText2 + "...: Succesfull");
//    	} else {
//    		TestPassFlag = false;
//    		testLog.error( "Merchant page: Found page Text: " + tText + ". Expected: " + merchantPageText2);
//    		capScreenShootPath = SeleniumUtils.getScreenshot();
//    		testLog.pass( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
//    		testLog.pass( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
//    	}
//
		Thread.sleep(timeOut);
		Assert.assertTrue(TestPassFlag);
	}

}
