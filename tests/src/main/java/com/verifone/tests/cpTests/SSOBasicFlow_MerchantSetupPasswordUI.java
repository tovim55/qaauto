package com.verifone.tests.cpTests;

import com.mongodb.assertions.Assertions;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.entities.EntitiesFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
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
import com.verifone.utils.General.LoginCGPortal;

import static com.verifone.pages.BasePage.testLog;
//--------------------------------------------------------------------------
/**
 * Portal: EstateManager
 * This test verify Merchant Merchant setup Password. Merchant added by PabSub APIs.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class SSOBasicFlow_MerchantSetupPasswordUI extends BaseTest {

    private static String ulMail = "";
    private static String ulPassword = "";
    private static String mailActivateButton = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/p[5]/a";

    private final static int timeOut = 2000;
    private static String mId = "";
    private static Integer rowNumber = 0;
    private static Integer getRowNumFromFile = 0;
//    final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\SSOBasic.xls";
    final String xlsxFile = java.nio.file.Paths.get(
        System.getProperty("user.dir"),
        "src", "test", "resources").toString() + File.separator + "SSOBasic.xls";
    private static Boolean TestPassFlag = true;
    private static String capScreenShootPath;
    private static String DeveloperPortalURI = "";
    private static String env = "";

    @BeforeTest
    public void startDDTest() throws Exception {
        env = envConfig.getEnv();
        if (env.contains("QA")) {
            getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "merchantSetupPasswordQA");
        }
        if (env.contains("DEV")) {
            getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "merchantSetupPasswordDEV");
        }
        if (env.contains("STAGING1")) {
            getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "merchantSetupPasswordSTAGING1");
        }
    }

//	      Data Provider

    @DataProvider(name = "merchantSetupPassword")
    public Object[][] dataSupplierLoginData() throws Exception {
        Object[][] arrayObject = null;
        if (env.contains("QA")) {
            arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "merchantSetupPasswordQA");
        }
        if (env.contains("DEV")) {
            arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "merchantSetupPasswordDEV");
        }
        if (env.contains("STAGING1")) {
            arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "merchantSetupPasswordSTAGING1");
        }
        return arrayObject;
    }

    @Test(enabled = true, testName = "Merchant Setup Password Test", description = "Merchant Setup Password Test", dataProvider = "merchantSetupPassword", groups = {"SSOBasicFlow"})
//    @Test(groups = {"CP-portal"})
    public void MerchantSetupPasswordSSOUI(String EOAdminMail, String EOAdminPwd, String mailInvText, String actAccountBtnLabel, String setupTitle, String setupText, String setupPasswordHint,
                                           String setupConfirmPasswordHint, String setupCheckBox, String setupCheckBoxAgr, String setupSubmitBtn, String setupErrorFormat, String setupErrorAccept, String setupErrorAcceptAgr, String setupErrorMatch, String textTOS1,
                                           String textTOS2, String textTOS3, String textTOS4, String textTOS5, String TOSLnk, String TOSDeclineBtn, String TOSAgreeBtn,
                                           String textAgreement1,
                                           String textAgreement2, String textAgreement3, String textAgreement4, String textAgreement5, String AgreementLnk, String AgreementDeclineBtn, String AgreementAgreeBtn,
                                           String PasswordSetupFinalTitle, String PasswordSetupFinalText) throws Exception {

//        starTestLog("Merchant Setup Password Test", "Merchant Setup Password Test");
        WebDriver driver = new HomePage().getDriver();
        rowNumber = rowNumber + 1;
        testLog.pass("Data Driven line number: " + rowNumber);
        testLog.info("This test check Merchant Setup Password. EO Admin: " + EOAdminMail);
        testLog.pass("---------------------------------------------Create Merchant by API---------------------------------------------");

        User user = new User(EOAdminMail, EOAdminPwd);
        GetTokenApi getTokenApi = new GetTokenApi("testId");
        String accessToken = getTokenApi.getToken(user);
        GetEoadminDataApi getEoadminDataApi = new GetEoadminDataApi(accessToken, "testId");
        mId = new CreateMerchant(accessToken, "testId").createMerchant(getEoadminDataApi.getData());
//        mId = new CreateMerchant(accessToken, "testId12").createMerchant("7e77011e-b31b-47ee-8607-aa0f8db3d125");
        System.out.println("MID: " + mId);

//      Navigate to Getnada
        Thread.sleep(timeOut + 2000);
        testLog.pass("-------------------------------------------------Getnada service-------------------------------------------------");
        driver.navigate().to("https://getnada.com/#");

        // Click Add Inbox
        Thread.sleep(5000); // Giora adding timeout for Microland GVCCA-1745
        driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();

        // Put email
        Thread.sleep(timeOut);
        driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
        driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(mId);
        Thread.sleep(timeOut);
        driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
        driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);

        //  Accept
        driver.findElement(By.linkText("ACCEPT")).click();
        testLog.pass("Create email inbox: " + mId + "getnada.com: Successful");

        //  Open Email
        Thread.sleep(timeOut);
        driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();
        testLog.pass("Found Invitation mail: Successful");

        //   Get email text

        Thread.sleep(timeOut);

        WebElement iFrame = driver.findElement(By.id("idIframe"));
        driver.switchTo().frame(iFrame);
        String mailText = driver.getPageSource();
        if (!com.verifone.utils.Assertions.compareValue(mailInvText, mailText, "Invitation mail include text:", testLog, driver)){
            TestPassFlag = false;
        }
        System.out.println("Mail text: " + TestPassFlag);

        String btnText = driver.findElement(By.xpath(mailActivateButton)).getText();
        if (!com.verifone.utils.Assertions.compareValue(actAccountBtnLabel, btnText, "Invitation mail Activate button text:", testLog, driver)){
            TestPassFlag = false;
        }
        driver.findElement(By.xpath(mailActivateButton)).click();

        testLog.pass("Click on: " + btnText + " button: Successful");


//		Setup Password
        testLog.pass("-----------------------------------------------Setup Password page-----------------------------------------------");

        Thread.sleep(timeOut + 2000);
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(1));

        SetupPasswordPage SetupPasswordPage = (SetupPasswordPage) PageFactory.getPage("SetupPasswordPage");

        String pgText = SetupPasswordPage.pageTitle();
        System.out.println(pgText);
        if (!com.verifone.utils.Assertions.compareValue(setupTitle, pgText, "Found Setup page title:", testLog, driver)){
            TestPassFlag = false;
        }
        pgText = SetupPasswordPage.pageText();
        if (!com.verifone.utils.Assertions.compareValue(setupText, pgText, "Found Setup page text:", testLog, driver)){
            TestPassFlag = false;
        }
        System.out.println(pgText);

        pgText = SetupPasswordPage.setupPasswordHint();
        if (!com.verifone.utils.Assertions.compareValue(setupPasswordHint, pgText, "Found Setup page Password Hint:", testLog, driver)){
            TestPassFlag = false;
        }
        System.out.println(pgText);

        pgText = SetupPasswordPage.setupConfirmPasswordHint();
        if (!com.verifone.utils.Assertions.compareValue(setupConfirmPasswordHint, pgText, "Found Setup page Confirm Password Hint:", testLog, driver)){
            TestPassFlag = false;
        }
        System.out.println(pgText);

        pgText = SetupPasswordPage.setupCheckBoxText();
        if (!com.verifone.utils.Assertions.compareValue(setupCheckBox, pgText, "Found Setup page check box label:", testLog, driver)){
            TestPassFlag = false;
        }
        System.out.println(pgText);

//        pgText = SetupPasswordPage.setupCheckBoxAgrText();
//        if (!com.verifone.utils.Assertions.compareValue(setupCheckBoxAgr, pgText, "Found Setup page check box label:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        System.out.println(pgText);

        pgText = SetupPasswordPage.setupSubmitBtnLabel();
        System.out.println(pgText);
        if (!com.verifone.utils.Assertions.compareValue(setupSubmitBtn, pgText, "Found Setup page Submit btn label:", testLog, driver)){
            TestPassFlag = false;
        }
        //		Input blank space as Password and ConfirmPassword

        testLog.pass("Setup page: Input password = ' ', confirm password = ' '");
        SetupPasswordPage.SetupPasswordPageCp(" ", " ");
        SetupPasswordPage.clickOnSubmitBtn();
        String errText = SetupPasswordPage.errorFormat();
        System.out.println(errText);
        if (!com.verifone.utils.Assertions.compareValue(setupErrorFormat, errText, "Found Format Error:", testLog, driver)){
            TestPassFlag = false;
        }
        //		Not agree with TOS and Agreement

        testLog.pass("Setup page: Not accept TOS");
        SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
        SetupPasswordPage.clickOnSubmitBtn();
        errText = SetupPasswordPage.errorTOS();
        System.out.println(errText);
        if (!com.verifone.utils.Assertions.compareValue(setupErrorAccept, errText, "Found Accept TOS Error:", testLog, driver)){
            TestPassFlag = false;
        }
//        errText = SetupPasswordPage.errorAgr();
//        System.out.println(errText);
//        if (!com.verifone.utils.Assertions.compareValue(setupErrorAcceptAgr, errText, "Found Accept TOS Error:", testLog, driver)){
//            TestPassFlag = false;
//        }

//     Input different Password and ConfirmPassword

        testLog.pass("Setup page: Input password = 'Veri1234', confirm password = 'Veri5234'");
        SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri5234");
        SetupPasswordPage.clickOnSubmitBtn();
        Thread.sleep(timeOut);
        errText = SetupPasswordPage.errorMatch();
        System.out.println(errText);
        if (!com.verifone.utils.Assertions.compareValue(setupErrorMatch, errText, "Found Match Error:", testLog, driver)){
            TestPassFlag = false;
        }
//      Input correct Password and ConfirmPassword, pass to TOS window, verify text

        testLog.pass("Setup page: Input password = 'Veri1234', confirm password = 'Veri1234'");
        SetupPasswordPage.SetupPasswordPageCp("Veri1234", "Veri1234");
        testLog.pass("Display TOS");
        SetupPasswordPage.clickOnchboxTOS();

//        testLog.pass("--------------------------------------------------TOS document---------------------------------------------------");
//
//        Thread.sleep(timeOut);
//
////    	Verify TOS page text
//        String tText = SetupPasswordPage.tosText();
//        if (!com.verifone.utils.Assertions.compareValue(textTOS1, tText, "TOS doc: Found TOS text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        if (!com.verifone.utils.Assertions.compareValue(textTOS2, tText, "TOS doc: Found TOS text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        if (!com.verifone.utils.Assertions.compareValue(textTOS3, tText, "TOS doc: Found TOS text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        if (!com.verifone.utils.Assertions.compareValue(textTOS4, tText, "TOS doc: Found TOS text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        if (!com.verifone.utils.Assertions.compareValue(textTOS5, tText, "TOS doc: Found TOS text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        System.out.println(tText);
//
//        tText = SetupPasswordPage.tosLnkText();
//        if (!com.verifone.utils.Assertions.compareValue(TOSLnk, tText, "TOS doc: Found link text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        System.out.println(tText);
//
//        tText = SetupPasswordPage.tosDeclineBtnLabel();
//        if (!com.verifone.utils.Assertions.compareValue(TOSDeclineBtn, tText, "TOS doc: Found Decline button label:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        System.out.println(tText);
//
//        tText = SetupPasswordPage.tosAgreeBtnLabel();
//        if (!com.verifone.utils.Assertions.compareValue(TOSAgreeBtn, tText, "TOS doc: Found Agree button label:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        System.out.println(tText);
//
////		Agree with TOS
//        testLog.pass("Accept TOS");
//        SetupPasswordPage.clickOnAcceptTOSBtn();
//        Thread.sleep(timeOut - 1000);
//
////      Pass to Agreement window, verify text
//
//        testLog.pass("Display Agreement");
//        SetupPasswordPage.clickOnchboxAgreement();
//
//        testLog.pass("-----------------------------------------------Agreement document------------------------------------------------");
//
//        Thread.sleep(timeOut);
//
////    	Verify Agreement page text
//        tText = SetupPasswordPage.tosText();
//        if (!com.verifone.utils.Assertions.compareValue(textAgreement1, tText, "Agreement doc: Found Agreement text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        if (!com.verifone.utils.Assertions.compareValue(textAgreement2, tText, "Agreement doc: Found Agreement text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        if (!com.verifone.utils.Assertions.compareValue(textAgreement3, tText, "Agreement doc: Found Agreement text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        if (!com.verifone.utils.Assertions.compareValue(textAgreement4, tText, "Agreement doc: Found Agreement text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        if (!com.verifone.utils.Assertions.compareValue(textAgreement5, tText, "Agreement doc: Found Agreement text:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        System.out.println(tText);
//
//
//        tText = SetupPasswordPage.agreementDeclineBtnLabel();
//        if (!com.verifone.utils.Assertions.compareValue(AgreementDeclineBtn, tText, "Agreement doc: Found Decline button label:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        System.out.println(tText);
//
//        tText = SetupPasswordPage.agreementAgreeBtnLabel();
//        if (!com.verifone.utils.Assertions.compareValue(AgreementAgreeBtn, tText, "Agreement doc: Found Agree button label:", testLog, driver)){
//            TestPassFlag = false;
//        }
//        System.out.println(tText);
//
////		Agree with Agreement
//        testLog.pass("Accept Agreement");
//        SetupPasswordPage.clickOnAcceptAgrBtn();
        Thread.sleep(10000);
        testLog.pass("Submit Setup");
        SetupPasswordPage.clickOnSubmitBtn();

//		Test Password Setup Final Page

        testLog.pass("---------------------------------------Password Setup Final page------------------------------------------");

        Thread.sleep(timeOut + 2000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(1));
        Thread.sleep(120000);
        PasswordSetupPage PasswordSetupPage = (PasswordSetupPage) PageFactory.getPage("PasswordSetupPage");

//	Compare Password Setup Page Title with expected
        String tText = PasswordSetupPage.pageTitle();
//        assertTextContains()
        if (!com.verifone.utils.Assertions.compareValue(PasswordSetupFinalTitle, tText, "Password Setup Final page: Found title:", testLog, driver)){
            TestPassFlag = false;
        }
//	Compare Password Setup Page text with expected
        tText = PasswordSetupPage.pageText();
        if (!com.verifone.utils.Assertions.compareValue(PasswordSetupFinalText, tText, "Password Setup Final pag: Found text:", testLog, driver)){
            TestPassFlag = false;
        }

        Thread.sleep(timeOut);
        Assert.assertTrue(TestPassFlag);
        //driver.quit();
    }

    @BeforeTest
    public void startDD1Test() throws Exception {
        env = envConfig.getEnv();
    }

    @Test(enabled = false, testName = "Developer Self SignUp Test", description = "Developer Self SignUp Test", groups = {"SSOBasicFlow"})
//    @Test(groups = {"CP-portal"})
    public void DeveloperSetupPasswordSSOUI() throws Exception {
        WebDriver driver = new HomePage().getDriver();
//        starTestLog("Merchant Setup Password Test", "Merchant Setup Password Test");
        testLog.info("This test check Developer Self SignUp Test.");
        testLog.pass("---------------------------------------------Developer Portal Sign Up---------------------------------------------");
        DeveloperPortalURI = "https://" + env + ".developer.verifonecp.com/docs/overview/get-started";
        driver.navigate().to(DeveloperPortalURI);

        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        SignUpPage SignUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        String BasicDevEmail = "BasicDev" + LocalDateTime.now() + "@getnada.com";
        BasicDevEmail = BasicDevEmail.replace("-", "");
        BasicDevEmail = BasicDevEmail.replace(":", "");
        testLog.info("Developer email: " + BasicDevEmail);
        String BasicDevPwd = "Veri1234";
        User user = new User(BasicDevEmail, BasicDevPwd);
        SignUpPage.signUp(user);

        testLog.pass("---------------------------------------------Thank You page---------------------------------------------");

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        ActionRequiredPage ActionRequiredPage = (ActionRequiredPage) PageFactory.getPage("ActionRequiredPage");
        String tText = ActionRequiredPage.pageTitle();
        if (!com.verifone.utils.Assertions.compareValue("Action Required", tText, "Password Setup Final pag: Found text:", testLog, driver)){
            TestPassFlag = false;
        }
        tText = ActionRequiredPage.pageText();
        if (!com.verifone.utils.Assertions.compareValue("Thanks for your registration!", tText, "Password Setup Final pag: Found text:", testLog, driver)){
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
        //driver.quit();
    }
}