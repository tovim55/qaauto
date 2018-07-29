package com.verifone.tests.cpTests;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.pages.eoPages.LoginEOPortal;
import com.verifone.pages.eoPages.UserDetailsPage;
import com.verifone.pages.eoPages.UsersPage;
import com.verifone.tests.BaseTest;
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

public class SSOBasicFlow2UI extends BaseTest {
    private final static int timeOut = 2000;
    private static Boolean TestPassFlag = true;
    private static String capScreenShootPath;
    private static String ForgotEmail = "gemerchantx@getnada.com";
    private static String NewPwd = "Veri1234";
    private static String mailResetButton = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/div[4]/a";
    private static String portalEOURI = "https://qa.estatemanager.verifonecp.com";
    private static String EOAdminEmail = "User20180722T132418.524MerchMan@getnada.com";
    private static String EOAdminPwd = "Veri1234";
    private static String DisableUserEmail = "";
    private static String DisableUserPwd = "Veri1234";

    @BeforeTest
    public void startDDTest() throws Exception {

    }


    @Test(enabled = false, testName = "User Forgot/Reset Password", groups = {"SSOBasic"}, alwaysRun = true)
    public void userForgotResetPasswordUI() throws Exception {

//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");

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
        boolean currentResult = tText.contains("Forgot your password?");
        if (currentResult == true) {
            testLog.pass( "Forgot Password page: Found title: " + "Forgot your password?" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Forgot Password page: Found title: " + tText + ". Expected: " + "Forgot your password?");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Compare Forgot page text with expected
        tText = ForgotPasswordPage.pageText();
        currentResult = tText.contains("Enter the email address associated with your account and we'll send you an email with password reset instructions.");
        if (currentResult == true) {
            testLog.pass( "Forgot Password page: Found text: " + "Enter the email address associated with your account and we'll send you an email with password reset instructions." + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Forgot Password page: Found text: " + tText + ". Expected: " + "Enter the email address associated with your account and we'll send you an email with password reset instructions.");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Compare Forgot page Mail label with expected
        tText = ForgotPasswordPage.mailLabelText();
        currentResult = tText.contains("Email");
        if (currentResult == true) {
            testLog.pass( "Forgot Password page: Found mail field hint: " + "Email" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Forgot Password page: Found mail field hint: " + tText + ". Expected: " + "Email");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Compare Forgot page Login link text with expected
        tText = ForgotPasswordPage.btnSendText();
        currentResult = tText.contains("Send");
        if (currentResult == true) {
            testLog.pass("Forgot Password page: Found Send button label: " + "Send" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Forgot Password page: Found Send button label: " + tText + ". Expected: " + "Send");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Compare Forgot page Login link text with expected
        tText = ForgotPasswordPage.lnkLoginText();
        currentResult = tText.contains("Login");
        if (currentResult == true) {
            testLog.pass( "Forgot Password page: Found Login link Text: " + "Login" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Forgot Password page: Found Login link Text: " + tText + ". Expected: " + "Login");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

        //    	Forgot Password Page: Email empty. Get error and compare with expected
        Thread.sleep(timeOut - 1000);
        testLog.info( "Forgot Password page: Input mail: " + "<empty>" + " and Send");
        ForgotPasswordPage.InputEmail("");
        ForgotPasswordPage.clickBtnSend();

        Thread.sleep(timeOut - 1000);
        tText = ForgotPasswordPage.errorEmptyText();
        currentResult = tText.contains("This field is required.");
        if (currentResult == true) {
            testLog.pass( "Forgot Password page: Found Mandatory error: " + "This field is required." + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Forgot Password page: Found Mandatory error: " + tText + ". Expected: " + "This field is required.");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Forgot Password Page: Email Invalid. Get error and compare with expected
        Thread.sleep(timeOut - 1000);
        testLog.info( "Forgot Password page: Input mail: " + "InvalidMail" + " and Send");
        ForgotPasswordPage.InputEmail("InvalidMail");
        ForgotPasswordPage.clickBtnSend();
        Thread.sleep(1000);

        tText = ForgotPasswordPage.errorEmptyText();
        currentResult = tText.contains("Field Email should have valid format!");
        if (currentResult == true) {
            testLog.pass( "Forgot Password page: Found Format error: " + "Field Email should have valid format!" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Forgot Password page: Found Format error: " + tText + ". Expected: " + "Field Email should have valid format!");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
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
        currentResult = mailText.contains("You recently requested a password change for");
        if (currentResult == true) {
            testLog.pass( "Reset Password mail include text: " + "You recently requested a password change for" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password mail include text: " + mailText + ". Expected: " + "You recently requested a password change for");
        }
        currentResult = mailText.contains("If you didn't make this request, please ignore this email. For any questions, please get in touch with us");
        if (currentResult == true) {
            testLog.pass( "Reset Password mail include text: " + "If you didn't make this request, please ignore this email. For any questions, please get in touch with us" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password mail include text: " + mailText + ". Expected: " + "If you didn't make this request, please ignore this email. For any questions, please get in touch with us");
        }
        currentResult = mailText.contains("Thank you,");
        if (currentResult == true) {
            testLog.pass( "Reset Password mail include text: " + "Thank you," + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password mail include text: " + mailText + ". Expected: " + "Thank you,");
        }
//    	Compare Reset Password button label with expected
        mailText = BasePage.driver.findElement(By.xpath(mailResetButton)).getText();
        currentResult = mailText.contains("Reset Password");
        if (currentResult == true) {
            testLog.pass( "Found Reset Password button label: " + "Reset Password" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Found Reset Password button label: " + mailText + ". Expected: " + "Reset Password");
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
        currentResult = tText.contains("Reset Password");
        if (currentResult == true) {
            testLog.pass( "Reset Password page: Found title: " + "Reset Password" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password page: Found title: " + tText + ". Expected: " + "Reset Password");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Reset Password Page: Compare Password label with expected
        tText = ResetPasswordPage.passwordLabelText();
        currentResult = tText.contains("New password");
        if (currentResult == true) {
            testLog.pass( "Reset Password page: Password field hint: " + "New password" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password page: Password field hint: " + tText + ". Expected: " + "New password");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Reset Password Page: Compare Confirm Password label with expected
        tText = ResetPasswordPage.confirmPasswordLabelText();
        currentResult = tText.contains("Confirm Password");
        if (currentResult == true) {
            testLog.pass( "Reset Password page: Confirm Password field hint: " + "Confirm Password" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password page: Confirm Password field hint: " + tText + ". Expected: " + "Confirm Password");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Reset Password Page: Compare Proceed button label with expected
        tText = ResetPasswordPage.btnProceedText();
        currentResult = tText.contains("PROCEED");
        if (currentResult == true) {
            testLog.pass( "Reset Password page: Proceed button label: " + "PROCEED" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password page: Proceed button label: " + tText + ". Expected: " + "PROCEED");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Reset Password Page: Input empty password, get error and compare with expected
        testLog.info( "Reset Password page: Input password = ' '. Proceed");
        ResetPasswordPage.InputPassword("");
        ResetPasswordPage.clickBtnProceed();
        Thread.sleep(timeOut - 1000);
        tText = ResetPasswordPage.errorEmptyText();
        currentResult = tText.contains("Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters.");
        if (currentResult == true) {
            testLog.pass( "Reset Password page: Found Mandatory error: " + "Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters." + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password page: Found Mandatory error: " + tText + ". Expected: " + "Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters.");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Reset Password Page: Input empty Confirm password, get error and compare with expected
        testLog.info( "Reset Password page: Input Confirm password = ' '. Proceed");
        ResetPasswordPage.InputConfirmPassword("");
        ResetPasswordPage.clickBtnProceed();
        Thread.sleep(timeOut - 1000);
        tText = ResetPasswordPage.errorConfirmEmptyText();
        currentResult = tText.contains("Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters.");
        if (currentResult == true) {
            testLog.pass( "Reset Password page: Found Mandatory error: " + "Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters." + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password page: Found Mandatory error: " + tText + ". Expected: " + "Use at least eight characters, one number or special character, one UPPER case, and one lower case character. Must not have more than 30 characters.");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Reset Password Page: Input Password and Confirmation Password not match, get error and compare with expected
        testLog.info( "Reset Password page: Input Password = 'Veri1234', Confirm password = 'Veri4321'. Proceed");
        ResetPasswordPage.InputPassword(NewPwd);
        ResetPasswordPage.InputConfirmPassword(NewPwd + "4321");
        ResetPasswordPage.clickBtnProceed();
        Thread.sleep(timeOut - 1000);
        tText = ResetPasswordPage.errorConfirmEmptyText();
        currentResult = tText.contains("Password and Confirmation Password must match.");
        if (currentResult == true) {
            testLog.pass( "Reset Password page: Found Match error: " + "Password and Confirmation Password must match." + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Reset Password page: Found Match error: " + tText + ". Expected: " + "Password and Confirmation Password must match.");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
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
        currentResult = tText.contains("Thank you!");
        if (currentResult == true) {
            testLog.pass( "Thank You page: Found page Title: " + "Thank you!" + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Thank You page: Found page Title: " + tText + ". Expected: " + "Thank you!");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Thank you page: Compare page text with expected
        tText = ResetThankYou.pageText();
        currentResult = tText.contains("We've reset the password for your Verifone account. You can now log in using your new password.");
        if (currentResult == true) {
            testLog.pass( "Thank You page: Found page Text: " + "We've reset the password for your Verifone account. You can now log in using your new password." + "...: Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "Thank You page: Found page Text: " + tText + ". Expected: " + "We've reset the password for your Verifone account. You can now log in using your new password.");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }

//    	Thank you page: Click on LogIn link
        ResetThankYou.clickLoginLnk();

        testLog.info( "---------------------------------------------------Login page----------------------------------------------------");

        Thread.sleep(timeOut + 2000);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(1));

//    	Compare login Title text with expected
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
        currentResult = url.contains("verifonecp.com/#home");
        if (currentResult == true) {
            testLog.pass( "User redirected to Home Page Succesfull");
        } else {
            TestPassFlag = false;
            testLog.error( "User redirected to: " + url + ". Expected: " + "Home Page");
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }
        Assert.assertTrue(TestPassFlag);
    }

    @BeforeTest
    public void startDD1Test() throws Exception {

    }


    @Test(enabled = true, testName = "Disable User", groups = {"SSOBasic"}, alwaysRun = true)
    public void DisableUserUI() throws Exception {

//        starTestLog("Merchant Reset Password Test", "Merchant Reset Password Test");

        testLog.info( "-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

        BasePage.driver.navigate().to(portalEOURI);

        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(EOAdminEmail);
        LoginEOPortal.loginInputPassword(EOAdminPwd);
        LoginEOPortal.clickLoginBtn();


        ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

        HomePage.clickHeaderMenu();

        boolean UserMenu = HomePage.menuUserExists();
        AssertJUnit.assertEquals(true, UserMenu);

        HomePage.clickUserMenu();

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
        AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

        int a = UsersPage.activeEOMerchantManRow_ParameterSearch("@getnada.com");
        System.out.println(a);
        UsersPage.clickOnRow(a);
        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
        DisableUserEmail = UserDetailsPage.getUserEmail();

        UserDetailsPage.clickLnkDisable();
        UserDetailsPage.clickDoDisable();
        Thread.sleep(timeOut+2000);

        HomePage = (HomePage) PageFactory.getPage("HomePage");
        HomePage.clickHeaderMenu();
        Thread.sleep(timeOut);
        HomePage.clickLogoutMenu();

        BasePage.driver.navigate().to(portalEOURI);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(DisableUserEmail);
        LoginEOPortal.loginInputPassword(DisableUserPwd);
        LoginEOPortal.clickLoginBtn();
        Thread.sleep(timeOut);
    }
}
