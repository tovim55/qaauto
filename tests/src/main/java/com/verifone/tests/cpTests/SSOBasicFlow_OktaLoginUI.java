package com.verifone.tests.cpTests;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import com.verifone.utils.DataDrivenUtils;
import org.openqa.selenium.WebDriver;
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
 * This test use in DataDriven data. Different roles Login to Different Portals via OKTA verification.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class SSOBasicFlow_OktaLoginUI extends BaseTest {
    private final static int timeOut = 2000;
    private static Boolean TestPassFlag = true;
    private static String portalURI = "";
    final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\oktaLogin.xls";
    private static Integer getRowNumFromFile = 0;
    private static String env = "";

    @BeforeTest
    public void startDDTest() throws Exception{
//	 		Get number of Rows from Data driven
        env = envConfig.getEnv();
        if (env.contains("QA")) {
            getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "oktaLoginQA");
        }
        if (env.contains("DEV")) {
            getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "oktaLoginDEV");
        }
    }

//	      Data Provider

    @DataProvider(name = "oktaLogin")
    public Object[][] dataSupplierLoginData() throws Exception {
        Object[][] arrayObject = null;
        if (env.contains("QA")) {
            arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "oktaLoginQA");
        }
        if (env.contains("DEV")) {
            arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "oktaLoginDEV");
        }
        return arrayObject;
    }

    @Test(enabled = true, priority = 1, testName = "okta login", dataProvider = "oktaLogin", groups = {"SSOBasic"}, alwaysRun = true)
    public void oktaLoginUI(String portal, String role, String email, String name, String password,
            String answer, String sectionTitle) throws Exception {
        WebDriver driver = new HomePage().getDriver();
        testLog.info("This test verify user: " + role + ", email: " + email + ", name: " + name + " logged in: " + portal + ", using OKTA verification");
        testLog.info("-------------------------------------------------Navigate to Portal-------------------------------------------------");

        portalURI = "https://" + env + "." + portal;
        String OktaHome_url = "https://verifone.okta.com/";
        driver.navigate().to(portalURI);

//		Test LoginportalURI
//    	Setup Login button
        testLog.info("---------------------------------------------------Login page----------------------------------------------------");

        Thread.sleep(timeOut + 1000);
        ArrayList<String>
                availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        testLog.info("Navigate to Forgot Password page");
        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

        testLog.info("Portal: " + portalURI);
        testLog.info("Role: " + role);
        LoginEOPortal.loginInputEmail(email);
        testLog.info("Email: " + email);

        testLog.info("---------------------------------------------------OKTA page----------------------------------------------------");

        Thread.sleep(2000);
        driver.navigate().to(OktaHome_url);
        LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
        if (!OktaLogin.loginOktaTitleExists()) {
            OktaLogin.SignOut();
        }

        Thread.sleep(2000);
        driver.navigate().to(portalURI);
        LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(email);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        Thread.sleep(2000);
        OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");

        if (OktaLogin.loginOktaTitle().contains("Okta Verify")){
            OktaLogin.SignOutOktaVerify();
        }
        Thread.sleep(2000);
        OktaLogin.loginInputName(name);
        OktaLogin.loginInputPassword(password);
        OktaLogin.clickSignInBtn();

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
        OktaLogin.loginInputAnswer(answer);
        testLog.info("Security answer: " + "");
        OktaLogin.clickVerifyBtn();

        Thread.sleep(2000);
        if (!OktaLogin.loginOktaTitleExists()) {
            driver.navigate().to(portalURI);
            LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
            LoginEOPortal.loginInputEmail(email);
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));

            Thread.sleep(2000);
            if (OktaLogin.loginOktaTitleExists()) {
                if (OktaLogin.loginOktaTitle().contains("Sign In")) {
                    OktaLogin.loginInputName(name);
                    OktaLogin.loginInputPassword(password);
                    OktaLogin.clickSignInBtn();
                }
            }
        }
        Thread.sleep(timeOut);
//        availableWindows = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(availableWindows.get(0));
//        testLog.info("Navigate to OKTA Login page");
//        OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
//        OktaLogin.loginInputName(name);
//        testLog.info("Name: " + name);
//        OktaLogin.loginInputPassword(password);
//        testLog.info("Password: " + password);
//        OktaLogin.clickSignInBtn();
//
//        availableWindows = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(availableWindows.get(0));
//        OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
//        OktaLogin.loginInputAnswer(answer);
//        testLog.info("Security answer: " + answer);
//        OktaLogin.clickVerifyBtn();

        testLog.info("---------------------------------------------------Portal page----------------------------------------------------");

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

        String tText = HomePage.sectionTitle();
        if (!Assertions.compareValue(sectionTitle, tText, "Home page: Found section title:", testLog, driver)){
            TestPassFlag = false;
        }

        Thread.sleep(timeOut);
        Assert.assertTrue(TestPassFlag);
//        driver.quit();
    }
}