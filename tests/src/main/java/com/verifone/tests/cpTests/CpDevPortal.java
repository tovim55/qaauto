package com.verifone.tests.cpTests;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.pages.BasePage;
import com.verifone.pages.cpPages.LoginPageNew;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * The purpose of this testLog is to testLog CP Estatemanager portal
 */

public class CpDevPortal extends BaseTest {


    public CpDevPortal() {
        propFilePath = "logIn.properties";
    }

    @Test(groups = {"CP-portal"})
    public void cpLoginTest() throws Exception {
        starTestLog("CpDevPortal", "CP dev Portal log in test");
        LoginPageNew loginPage = (LoginPageNew) PageFactory.getPage("LoginPage");
        loginPage.loginPageCp(prop.getProperty("user_id"), prop.getProperty("password_id"));
//        testLog.log(LogStatus.INFO, "End of page" + "blablabla" + " testing <span class='label info'>info</span>");

    }

    @Test(groups = {"CP-portal2"})
    public void cpLoginTest2() throws Exception {
//        testLog = BasePage.testLog = logger.startTest("CpDevPortal2", "CP dev Portal log in test2");
        starTestLog("blabla", "blablalbalba ");
        LoginPageNew loginPage = (LoginPageNew) PageFactory.getPage("LoginPage");
        loginPage.loginPageCp(prop.getProperty("user_id"), prop.getProperty("password_id"));
//        testLog.log(LogStatus.INFO, "End of page" + "blablabla" + " testing <span class='label info'>info</span>");

    }
}