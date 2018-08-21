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
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.verifone.pages.BasePage.testLog;

import java.util.ArrayList;

public class SSOBasicFlow3UI extends BaseTest {
    private final static int timeOut = 2000;
    private static Boolean TestPassFlag = true;
    private static String capScreenShootPath;
    private static String ForgotEmail = "gemerchantx@getnada.com";
    private static String NewPwd = "Veri1234";
    private static String mailResetButton = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/div[4]/a";
    private static String portalURI = "";
    private static String EOAdminEmail = "User20180722T132418.524MerchMan@getnada.com";
    private static String EOAdminPwd = "Veri1234";
    private static String DisableUserEmail = "";
    private static String DisableUserPwd = "Veri1234";
    private static String DisableMerchantEmail = "";
    private static String DisableMerchantPwd = "Veri1234";
    private static String EnableUserEmail = "";
    private static String EnableUserPwd = "Veri1234";
    private static String EnableMerchantEmail = "";
    private static String EnableMerchantPwd = "Veri1234";
    final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\oktaLogin.xls";
    private static Integer getRowNumFromFile = 0;
    private static String env = "";

    @BeforeTest
    public void startDDTest() throws Exception{
//	 		Get number of Rows from Data driven
        getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "oktaLogin");
        env = envConfig.getEnv();
    }

//	      Data Provider

    @DataProvider(name = "oktaLogin")
    public Object[][] dataSupplierLoginData() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "oktaLogin");
        return arrayObject;
    }

    @Test(enabled = true, priority = 1, testName = "okta login", dataProvider = "oktaLogin", groups = {"SSOBasic"}, alwaysRun = true)
    public void oktaLoginUI(String env, String portal, String role, String email, String name, String password,
            String answer, String sectionTitle) throws Exception {

        testLog.info("This test verify user: " + role + ", email: " + email + ", name: " + name + " logged in: " + portal + ", using OKTA verification");
        testLog.info("-------------------------------------------------Navigate to Portal-------------------------------------------------");

        portalURI = "https://" + env + "." + portal;
        BasePage.driver.navigate().to(portalURI);

//		Test LoginportalURI
//    	Setup Login button
        testLog.info("---------------------------------------------------Login page----------------------------------------------------");

        Thread.sleep(timeOut + 1000);
        ArrayList<String>
                availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        testLog.info("Navigate to Forgot Password page");
        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

        testLog.info("Portal: " + portalURI);
        testLog.info("Role: " + role);
        LoginEOPortal.loginInputEmail(email);
        testLog.info("Email: " + email);

        testLog.info("---------------------------------------------------OKTA page----------------------------------------------------");

        Thread.sleep(timeOut);
        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        testLog.info("Navigate to OKTA Login page");
        OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
        OktaLogin.loginInputName(name);
        testLog.info("Name: " + name);
        OktaLogin.loginInputPassword(password);
        testLog.info("Password: " + password);
        OktaLogin.clickSignInBtn();

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
        OktaLogin.loginInputAnswer(answer);
        testLog.info("Security answer: " + answer);
        OktaLogin.clickVerifyBtn();

        testLog.info("---------------------------------------------------Portal page----------------------------------------------------");

        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
        BasePage.driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

        String tText = HomePage.sectionTitle();
        if (!Assertions.compareValue(sectionTitle, tText, "Home page: Found section title:", testLog)){
            TestPassFlag = false;
        }

        Thread.sleep(timeOut);
        Assert.assertTrue(TestPassFlag);
//        BasePage.driver.quit();
    }
}