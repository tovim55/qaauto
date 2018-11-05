package com.verifone.tests.cpTests;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import com.verifone.utils.DataDrivenUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.verifone.pages.BasePage.testLog;

import java.util.ArrayList;
//--------------------------------------------------------------------------
/**
 * Portal: EstateManager
 * This tests set verify user can Reset Password and LogIn with new Password.
 * Disabled Merchant Manager not allowed to LogIn.
 * Disabled Merchant not allowed to LogIn.
 * Enabled Merchant Manager allowed to LogIn.
 * Enabled Merchant allowed to LogIn.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class SSOBasicFlow_ResetPasswordStatusUI extends BaseTest {
    private final static int timeOut = 2000;
    private static Boolean TestPassFlag = true;
    private static String capScreenShootPath;
    private static String ForgotEmail = ""; //"gemerchantx@getnada.com";
    private static String NewPwd = "Veri4321";
    private static String mailResetButton = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/div[4]/a";
    private static String portalEOURI = "";
    private static String EOAdminEmail = ""; //"User20180722T132418.524MerchMan@getnada.com";
    private static String EOAdminPwd = ""; //"Veri1234";
    private static String DisableUserEmail = "";
    private static String DisableUserPwd = "Veri1234";
    private static String DisableMerchantEmail = "";
    private static String DisableMerchantPwd = "Veri1234";
    private static String EnableUserEmail = "";
    private static String EnableUserPwd = "Veri1234";
    private static String EnableMerchantEmail = "";
    private static String EnableMerchantPwd = "Veri1234";
    private static String env = "";

    @BeforeTest
    public void startDDTest() throws Exception {
        env = envConfig.getEnv();
        portalEOURI = "https://" + env + "." + "estatemanager.verifonecp.com";
        TestPassFlag = true;
    }


    @Test(enabled = true, priority=1, testName = "User Forgot/Reset Password", description = "User Merchant Manager forgot password, reset password login EO Admin portal", groups = {"SSOBasic"}, alwaysRun = true)
    public void userForgotResetPasswordUI() throws Exception {

        User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
        EOAdminEmail = EOAdmin.getUserName();
        EOAdminPwd = EOAdmin.getPassword();
        User EOMerchantForgotPassword = EntitiesFactory.getEntity("EOMerchantForgotPassword");
        ForgotEmail = EOMerchantForgotPassword.getUserName();
//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");
        testLog.info( "This test verify that User Merchant Manager can Reset Password, setup New and Log In using New Password");
        testLog.info("User mail: " + EOAdminEmail);
        testLog.info( "-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

        BasePage.driver.navigate().to(portalEOURI);

//		Test Login
//    	Setup Login button
        testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

        //    	Click on Forgot Password link
        Thread.sleep(timeOut + 1000);
        ArrayList<String>
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        testLog.info( "Navigate to Forgot Password page");
        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginForgotLinkClick();

        testLog.info( "----------------------------------------------Forgot Password page-----------------------------------------------");
        //    	Compare Title, Text, Email text, Send button label, Login link label with expected on Forgot Password page
        Thread.sleep(timeOut + 3000);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        ForgotPasswordPage ForgotPasswordPage = (ForgotPasswordPage) PageFactory.getPage("ForgotPasswordPage");

//    	Compare Forgot page title with expected
        String tText = ForgotPasswordPage.pageTitle();
        if (!Assertions.compareValue("Forgot your password?", tText, "Forgot Password page: Found title:", testLog)){
            TestPassFlag = false;
        }
//    	Compare Forgot page text with expected
        tText = ForgotPasswordPage.pageText();
        if (!Assertions.compareValue("Enter the email address associated with your account and we'll send you an email with password reset instructions.", tText, "Forgot Password page: Found text:", testLog)){
            TestPassFlag = false;
        }
//    	Compare Forgot page Mail label with expected
        tText = ForgotPasswordPage.mailLabelText();
        if (!Assertions.compareValue("Email", tText, "Forgot Password page: Found mail field hint:", testLog)){
            TestPassFlag = false;
        }
//    	Compare Forgot page Login link text with expected
        tText = ForgotPasswordPage.btnSendText();
        if (!Assertions.compareValue("Send", tText, "Forgot Password page: Found Send button label:", testLog)){
            TestPassFlag = false;
        }
//    	Compare Forgot page Login link text with expected
        tText = ForgotPasswordPage.lnkLoginText();
        if (!Assertions.compareValue("Login", tText, "Forgot Password page: Found Login link Text:", testLog)){
            TestPassFlag = false;
        }
        //    	Forgot Password Page: Email empty. Get error and compare with expected
        Thread.sleep(timeOut - 1000);
        testLog.info( "Forgot Password page: Input mail: " + "<empty>" + " and Send");
        ForgotPasswordPage.InputEmail("");
        ForgotPasswordPage.clickBtnSend();

        Thread.sleep(timeOut - 1000);
        tText = ForgotPasswordPage.errorEmptyText();
        if (!Assertions.compareValue("This field is required.", tText, "Forgot Password page: Found Mandatory error:", testLog)){
            TestPassFlag = false;
        }
//    	Forgot Password Page: Email Invalid. Get error and compare with expected
        Thread.sleep(timeOut - 1000);
        testLog.info( "Forgot Password page: Input mail: " + "InvalidMail" + " and Send");
        ForgotPasswordPage.InputEmail("InvalidMail");
        ForgotPasswordPage.clickBtnSend();
        Thread.sleep(1000);

        tText = ForgotPasswordPage.errorEmptyText();
        if (!Assertions.compareValue("Field Email should have valid format!", tText, "Forgot Password page: Found Format error:", testLog)){
            TestPassFlag = false;
        }
//    	Forgot Password Page: Email Not Match. Get error and compare with expected

        Thread.sleep(timeOut - 1000);
        testLog.info( "Forgot Password page: Input mail: " + ForgotEmail + " and Send");
        ForgotPasswordPage.InputEmail(ForgotEmail);
        ForgotPasswordPage.clickBtnSend();

        testLog.info( "-------------------------------------------------Getnada service-------------------------------------------------");

//    	Goto Getnada mail and Open received email
        Thread.sleep(timeOut + 3000);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        BasePage.driver.navigate().to("https://getnada.com/#");

// 		Click Add Inbox
        BasePage.driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();

        // Put email
        BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
        BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(ForgotEmail.replace("@getnada.com",""));

        BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
        BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);

        //  Accept
        BasePage.driver.findElement(By.linkText("ACCEPT")).click();

        //  Open Email

        Thread.sleep(timeOut);
        testLog.info( "Open received email");
        BasePage.driver.findElement(By.xpath("//div[contains(@class, 'subject bold')]")).click();

        //   Get email text

        Thread.sleep(timeOut);

        WebElement iFrame = BasePage.driver.findElement(By.id("idIframe"));
        BasePage.driver.switchTo().frame(iFrame);
        String mailText = BasePage.driver.getPageSource();

//    	Compare Mail text with expected
        if (!Assertions.compareValue("You recently requested a password change for", mailText, "Reset Password mail include text:", testLog)){
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("If you didn't make this request, please ignore this email. For any questions, please get in touch with us", mailText, "Reset Password mail include text:", testLog)){
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("Thank you,", mailText, "Reset Password mail include text:", testLog)){
            TestPassFlag = false;
        }
//    	Compare Reset Password button label with expected
        mailText = BasePage.driver.findElement(By.xpath(mailResetButton)).getText();
        if (!Assertions.compareValue("Reset Password", mailText, "Found Reset Password button label:", testLog)){
            TestPassFlag = false;
        }
        BasePage.driver.findElement(By.xpath(mailResetButton)).click();

        testLog.info( "Click on: " + mailResetButton + " button: Succesfull");

//    	Reset Password Page
        testLog.info( "-----------------------------------------------Reset Password page-----------------------------------------------");

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(1));
        ResetPasswordPage ResetPasswordPage = (ResetPasswordPage) PageFactory.getPage("ResetPasswordPage");

//    	Reset Password Page: Compare title with expected
        tText = ResetPasswordPage.pageTitle();
        if (!Assertions.compareValue("Reset Password", tText, "Reset Password page: Found title:", testLog)){
            TestPassFlag = false;
        }
//    	Reset Password Page: Compare Password label with expected
        tText = ResetPasswordPage.passwordLabelText();
        if (!Assertions.compareValue("New password", tText, "Reset Password page: Password field hint:", testLog)){
            TestPassFlag = false;
        }
//    	Reset Password Page: Compare Confirm Password label with expected
        tText = ResetPasswordPage.confirmPasswordLabelText();
        if (!Assertions.compareValue("Confirm Password", tText, "Reset Password page: Confirm Password field hint:", testLog)){
            TestPassFlag = false;
        }
//    	Reset Password Page: Compare Proceed button label with expected
        tText = ResetPasswordPage.btnProceedText();
        if (!Assertions.compareValue("PROCEED", tText, "Reset Password page: Proceed button label:", testLog)){
            TestPassFlag = false;
        }
//    	Reset Password Page: Input empty password, get error and compare with expected
        testLog.info( "Reset Password page: Input password = ' '. Proceed");
        ResetPasswordPage.InputPassword("");
        ResetPasswordPage.clickBtnProceed();
        Thread.sleep(timeOut - 1000);
        tText = ResetPasswordPage.errorEmptyText();
        if (!Assertions.compareValue("Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters.", tText, "Reset Password page: Found Mandatory error:", testLog)){
            TestPassFlag = false;
        }
//    	Reset Password Page: Input empty Confirm password, get error and compare with expected
        testLog.info( "Reset Password page: Input Confirm password = ' '. Proceed");
        ResetPasswordPage.InputConfirmPassword("");
        ResetPasswordPage.clickBtnProceed();
        Thread.sleep(timeOut - 1000);
        tText = ResetPasswordPage.errorConfirmEmptyText();
        if (!Assertions.compareValue("Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters.", tText, "Reset Password page: Found Mandatory error:", testLog)){
            TestPassFlag = false;
        }

//    	Reset Password Page: Input Password and Confirmation Password not match, get error and compare with expected
        testLog.info( "Reset Password page: Input Password = 'Veri1234', Confirm password = 'Veri4321'. Proceed");
        ResetPasswordPage.InputPassword(NewPwd);
        ResetPasswordPage.InputConfirmPassword(NewPwd + "4321");
        ResetPasswordPage.clickBtnProceed();
        Thread.sleep(timeOut - 1000);
        tText = ResetPasswordPage.errorConfirmEmptyText();
        if (!Assertions.compareValue("Password and Confirmation Password must match.", tText, "Reset Password page: Found Match error:", testLog)){
            TestPassFlag = false;
        }
//    	Reset Password Page: Input Same Password and Confirmation Password, click on Proceed button
        testLog.info( "Reset Password page: Input Password = 'Veri1234', Confirm password = 'Veri1234'. Proceed");
        System.out.println(NewPwd);
        ResetPasswordPage.InputPassword(NewPwd);
        ResetPasswordPage.InputConfirmPassword(NewPwd);
        ResetPasswordPage.clickBtnProceed();

//    	Thank you page
        testLog.info( "-------------------------------------------------Thank You page--------------------------------------------------");
        Thread.sleep(timeOut + 2000);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(1));
        ResetThankYou ResetThankYou = (ResetThankYou) PageFactory.getPage("ResetThankYou");

//    	Thank you page: Compare title with expected
        tText = ResetThankYou.pageTitle();
        if (!Assertions.compareValue("Thank you!", tText, "Thank You page: Found page Title", testLog)){
            TestPassFlag = false;
        }

//    	Thank you page: Compare page text with expected
        tText = ResetThankYou.pageText();
        if (!Assertions.compareValue("We've reset the password for your Verifone account. You can now log in using your new password.", tText, "Thank You page: Found page Text:", testLog)){
            TestPassFlag = false;
        }
//    	Thank you page: Click on LogIn link
        ResetThankYou.clickLoginLnk();

        testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

        Thread.sleep(timeOut + 2000);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(1));

//    	Compare loginAndCheck Title text with expected
        LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

        Thread.sleep(timeOut);

        //    	Input Valid Email and Password. Login

        testLog.info( "Login page: Input Email = " + ForgotEmail + ". Input Password = " + NewPwd);
        LoginEOPortal.loginInputEmail(ForgotEmail);
        LoginEOPortal.loginInputPassword(NewPwd);
        Thread.sleep(timeOut - 1000);
        LoginEOPortal.clickLoginBtn();
        testLog.info( "Click Login button");

//		Home Page
        testLog.info( "---------------------------------------------------Home page----------------------------------------------------");
        Thread.sleep(timeOut+3000);
//        WebDriverWait wait = new WebDriverWait(BasePage.driver, 30);
//
//        wait.until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver wdriver) {
//                return ((JavascriptExecutor) BasePage.driver).executeScript(
//                        "return document.readyState"
//                ).equals("complete");
//            }
//        });
        String url = BasePage.driver.getCurrentUrl();
        if (!Assertions.compareValue("verifonecp.com/#home", url, "User redirected to:", testLog)){
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
        //BasePage.driver.quit();
    }

    @BeforeTest
    public void startDD1Test() throws Exception {
        env = envConfig.getEnv();
        portalEOURI = "https://" + env + "." + "estatemanager.verifonecp.com";
        TestPassFlag = true;
    }


    @Test(enabled = true, priority=2, testName = "Disable Merchant Manager", description = "Disable Merchant Manager blocked login EO Portal", groups = {"SSOBasic"}, alwaysRun = true)
    public void DisableUserUI() throws Exception {

        User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
        EOAdminEmail = EOAdmin.getUserName();
        EOAdminPwd = EOAdmin.getPassword();
//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");
        testLog.info( "This test check that disabled Merchant Manager blocked to Log In EO Portal");


        testLog.info( "-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

        BasePage.driver.navigate().to(portalEOURI);

        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(EOAdminEmail);
        LoginEOPortal.loginInputPassword(EOAdminPwd);
        LoginEOPortal.clickLoginBtn();

        testLog.info( "-------------------------------------------------EO Admin Home page-------------------------------------------------");

        ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

        Thread.sleep(timeOut);
        HomePage.clickHeaderMenu();

        boolean UserMenu = HomePage.menuUserExists();
        AssertJUnit.assertEquals(true, UserMenu);

        testLog.info( "-------------------------------------------------Users list-------------------------------------------------");

        HomePage.clickUserMenu();
        Thread.sleep(timeOut);

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
        AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

        testLog.info( "-------------------------------------Edit Active Merchant Manager and disable it-------------------------------------------------");

        int a = UsersPage.activeEOMerchantManRow_ParameterSearch("@getnada.com");
        System.out.println(a);
        UsersPage.clickOnRow(a);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
        DisableUserEmail = UserDetailsPage.getUserEmail();
        testLog.info( "Disabled user: " + DisableUserEmail);

        Thread.sleep(timeOut);
        UserDetailsPage.clickLnkDisable();
        UserDetailsPage.clickDoDisable();
        Thread.sleep(timeOut+2000);

        testLog.info( "------------------------------------------------Log out as EO Admin-------------------------------------------------");

        HomePage = (HomePage) PageFactory.getPage("HomePage");
        Thread.sleep(timeOut);
        HomePage.clickHeaderMenu();
        Thread.sleep(timeOut);
        HomePage.clickLogoutMenu();
        Thread.sleep(timeOut);

        testLog.info( "-----------------------------------------Try to Log in as disabled Merchant Manager-------------------------------------------------");

        BasePage.driver.navigate().to(portalEOURI);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(DisableUserEmail);
        LoginEOPortal.loginInputPassword(DisableUserPwd);
        LoginEOPortal.clickLoginBtn();
        Thread.sleep(timeOut);

        String tText = LoginEOPortal.lerrorMatch();
        if (!Assertions.compareValue("The information you've entered does not match the information we have on file.", tText, "Login page: Found error:", testLog)){
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
        //BasePage.driver.quit();
    }

    @BeforeTest
    public void startDD2Test() throws Exception {
        env = envConfig.getEnv();
        portalEOURI = "https://" + env + "." + "estatemanager.verifonecp.com";
        TestPassFlag = true;
    }
    @Test(enabled = true, priority=3, testName = "Disable Merchant", description = "Disable Merchant blocked login merchant portal", groups = {"SSOBasic"}, alwaysRun = true)
    public void DisableMerchantUI() throws Exception {
        User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
        EOAdminEmail = EOAdmin.getUserName();
        EOAdminPwd = EOAdmin.getPassword();
//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");
        testLog.info( "This test check that disabled Merchant blocked to Log In Merchant Portal");
        testLog.info( "-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

        BasePage.driver.navigate().to(portalEOURI);

        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(EOAdminEmail);
        LoginEOPortal.loginInputPassword(EOAdminPwd);
        LoginEOPortal.clickLoginBtn();

        testLog.info( "-------------------------------------------------EO Admin Home page-------------------------------------------------");

        ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

        testLog.info( "-------------------------------------------------Merchants list-------------------------------------------------");
        Thread.sleep(timeOut);
        HomePage.clickMerchantsMenu();
        Thread.sleep(timeOut);

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
        AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

        int a = MerchantsPage.activeMerchantRow_ParameterSearch("@getnada.com");
        System.out.println(a);
        MerchantsPage.clickOnRow(a);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        MerchantDetailsPage MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");
        DisableMerchantEmail = MerchantDetailsPage.getMerchantEmail();
        testLog.info( "Disabled Merchant: " + DisableMerchantEmail);

        testLog.info( "-----------------------------------------Edit Active Merchant  and disable it-------------------------------------------------");

        Thread.sleep(timeOut);
        MerchantDetailsPage.clickLnkDisable();
        MerchantDetailsPage.clickDoDisable();
        Thread.sleep(timeOut+2000);

        testLog.info( "------------------------------------------------Log out as EO Admin-------------------------------------------------");

        HomePage = (HomePage) PageFactory.getPage("HomePage");
        Thread.sleep(timeOut);
        HomePage.clickHeaderMenu();
        Thread.sleep(timeOut);
        HomePage.clickLogoutMenu();

        testLog.info( "--------------------------------------------Try to Log in as disabled Merchant -------------------------------------------------");

        Thread.sleep(timeOut);
        BasePage.driver.navigate().to(portalEOURI);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(DisableMerchantEmail);
        LoginEOPortal.loginInputPassword(DisableMerchantPwd);
        LoginEOPortal.clickLoginBtn();
        Thread.sleep(timeOut);

        String tText = LoginEOPortal.lerrorMatch();
        if (!Assertions.compareValue("The information you've entered does not match the information we have on file.", tText, "Login page: Found error:", testLog)){
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
        //BasePage.driver.quit();
    }

    @BeforeTest
    public void startDD3Test() throws Exception {
        env = envConfig.getEnv();
        portalEOURI = "https://" + env + "." + "estatemanager.verifonecp.com";
        TestPassFlag = true;
    }
    @Test(enabled = true, priority=4, testName = "Enable Merchant Manager", description = "Enable Merchant Manager logged in merchant portal", groups = {"SSOBasic"}, alwaysRun = true)
    public void EnableUserUI() throws Exception {
        User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
        EOAdminEmail = EOAdmin.getUserName();
        EOAdminPwd = EOAdmin.getPassword();
        testLog.info( "This test check that enabled Merchant Manager Logged In EO Admin Portal successfully");
//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");

        testLog.info( "-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

        BasePage.driver.navigate().to(portalEOURI);

        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(EOAdminEmail);
        LoginEOPortal.loginInputPassword(EOAdminPwd);
        LoginEOPortal.clickLoginBtn();

        testLog.info( "-------------------------------------------------EO Admin Home page-------------------------------------------------");

        ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

        Thread.sleep(timeOut);
        HomePage.clickHeaderMenu();

        boolean UserMenu = HomePage.menuUserExists();
        AssertJUnit.assertEquals(true, UserMenu);

        testLog.info( "-----------------------------------------------------Users list-------------------------------------------------");

        HomePage.clickUserMenu();
        Thread.sleep(timeOut);

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
        AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

        int a = UsersPage.disableEOMerchantManRow_ParameterSearch("@getnada.com");
        System.out.println(a);
        UsersPage.clickOnRow(a);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
        EnableUserEmail = UserDetailsPage.getUserEmail();
        testLog.info( "Enabled user: " + EnableUserEmail);

        testLog.info( "---------------------------------------Edit Disable Merchant Manager and enable it-------------------------------------------------");
        Thread.sleep(timeOut);
        UserDetailsPage.clickLnkEnable();
        UserDetailsPage.clickDoEnable();
        Thread.sleep(timeOut+2000);

        testLog.info( "------------------------------------------------Log out as EO Admin-------------------------------------------------");

        HomePage = (HomePage) PageFactory.getPage("HomePage");
        Thread.sleep(timeOut);
        HomePage.clickHeaderMenu();
        Thread.sleep(timeOut);
        HomePage.clickLogoutMenu();
        Thread.sleep(timeOut);

        testLog.info( "----------------------------------------Log in EO portal as enabled Merchant Manager-------------------------------------------------");

        BasePage.driver.navigate().to(portalEOURI);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        Thread.sleep(timeOut);
        LoginEOPortal.loginInputEmail(EnableUserEmail);
        LoginEOPortal.loginInputPassword(EnableUserPwd);
        LoginEOPortal.clickLoginBtn();
        Thread.sleep(timeOut);

        testLog.info( "-------------------------------------------------Merchant Manager Home page-------------------------------------------------");

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        HomePage = (HomePage) PageFactory.getPage("HomePage");
        AssertJUnit.assertEquals(true, HomePage.headerExists());

        Assert.assertTrue(TestPassFlag);
        //BasePage.driver.quit();
    }

    @BeforeTest
    public void startDD4Test() throws Exception {
        env = envConfig.getEnv();
        portalEOURI = "https://" + env + "." + "estatemanager.verifonecp.com";
        TestPassFlag = true;
    }
    @Test(enabled = true, priority=5, testName = "Enable Merchant", description = "Enable Merchant logged in merchant portal", groups = {"SSOBasic"}, alwaysRun = true)
    public void EnableMerchantUI() throws Exception {
        User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
        EOAdminEmail = EOAdmin.getUserName();
        EOAdminPwd = EOAdmin.getPassword();
        testLog.info( "This test check that enabled Merchant Logged In EO Merchant Portal successfully");
//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");

        testLog.info( "-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

        BasePage.driver.navigate().to(portalEOURI);

        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(EOAdminEmail);
        LoginEOPortal.loginInputPassword(EOAdminPwd);
        LoginEOPortal.clickLoginBtn();

        testLog.info( "-------------------------------------------------EO Admin Home page-------------------------------------------------");

        ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

        testLog.info( "-----------------------------------------------------Merchants list-------------------------------------------------");

        Thread.sleep(timeOut);
        HomePage.clickMerchantsMenu();
        Thread.sleep(timeOut);

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
        AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

        int a = MerchantsPage.disableMerchantRow_ParameterSearch("@getnada.com");
        System.out.println(a);
        MerchantsPage.clickOnRow(a);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        MerchantDetailsPage MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");
        EnableMerchantEmail = MerchantDetailsPage.getMerchantEmail();
        testLog.info( "Enabled Merchant: " + EnableMerchantEmail);

        testLog.info( "---------------------------------------------Edit Disable Merchant and enable it-------------------------------------------------");
        Thread.sleep(timeOut);
        MerchantDetailsPage.clickLnkEnable();
        MerchantDetailsPage.clickDoEnable();
        Thread.sleep(timeOut+2000);

        testLog.info( "------------------------------------------------Log out as EO Admin-------------------------------------------------");

        HomePage = (HomePage) PageFactory.getPage("HomePage");
        Thread.sleep(timeOut);
        HomePage.clickHeaderMenu();
        Thread.sleep(timeOut);
        HomePage.clickLogoutMenu();

        testLog.info( "-----------------------------------------------Log in EO portal as enabled Merchant-------------------------------------------------");

        Thread.sleep(timeOut);
        BasePage.driver.navigate().to(portalEOURI);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        Thread.sleep(timeOut);
        LoginEOPortal.loginInputEmail(EnableMerchantEmail);
        LoginEOPortal.loginInputPassword(EnableMerchantPwd);
        LoginEOPortal.clickLoginBtn();
        Thread.sleep(timeOut);

        testLog.info( "---------------------------------------------------------Merchant Home page-------------------------------------------------");

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        MerchantThankYouPage MerchantThankYouPage = (MerchantThankYouPage) PageFactory.getPage("MerchantThankYouPage");
        AssertJUnit.assertEquals("Thank you!",MerchantThankYouPage.pageTitle());

        Assert.assertTrue(TestPassFlag);
        //BasePage.driver.quit();
    }

}
