package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.DevSupportHomePage;
import com.verifone.pages.cpPages.DevUsersPage;
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

public class DevSupportTest extends BaseTest {

    @Test(testName = "checkUsersBtnTest", description = "CP dev Portal - check users button", groups = {"CP-portal"})
    public void checkUsersBtnUI() throws Exception {
//        starTestLog("checkUsersBtnTest", "CP dev Portal - check users button");
        User dev = EntitiesFactory.getEntity("DevSupportAdmin");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.supportLogin(dev);
        DevSupportHomePage devHome = (DevSupportHomePage) PageFactory.getPage("DevSupportHomePage");
        devHome.goToUsersPage();
        DevUsersPage devUsers = (DevUsersPage) PageFactory.getPage("DevUsersPage");
        devUsers.getSubTitle();
    }

    @Test(testName = "checkAddUserBtnTest", description = "CP dev Portal - check addUser button", groups = {"CP-portal"})
    public void checkAddUserBtnUI() throws Exception {
//        starTestLog("checkAddUserBtnTest", "CP dev Portal - check addUser button");
        User dev = EntitiesFactory.getEntity("DevSupportAdmin");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.supportLogin(dev);
        DevSupportHomePage devHome = (DevSupportHomePage) PageFactory.getPage("DevSupportHomePage");
        devHome.goToUsersPage();
        DevUsersPage devUsers = (DevUsersPage) PageFactory.getPage("DevUsersPage");
        devUsers.addUser();
    }

    @Test(testName = "checkMandatoryFieldUI", description = "CP dev Portal - check mandatory fields", groups = {"CP-portal"})
    public void checkMandatoryFieldUI() throws Exception {
//        starTestLog("checkAddUserBtnTest", "CP dev Portal - check mandatory fields");
        User dev = EntitiesFactory.getEntity("DevSupportAdmin");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.supportLogin(dev);
        DevSupportHomePage devHome = (DevSupportHomePage) PageFactory.getPage("DevSupportHomePage");
        devHome.goToUsersPage();
        DevUsersPage devUsers = (DevUsersPage) PageFactory.getPage("DevUsersPage");
        devUsers.addUser();
        devUsers.mandatoryFields();
    }

    @Test(testName = "checkAppsNumberUI", description = "check apps number", groups = {"CP-portal"})
    public void checkAppsNumberUI() throws Exception {
//        starTestLog("checkAddUserBtnTest", "checkAddUserBtnTest");
        User dev = EntitiesFactory.getEntity("DevSupportAdmin");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.supportLogin(dev);
        DevSupportHomePage devHome = (DevSupportHomePage) PageFactory.getPage("DevSupportHomePage");
        devHome.validateAppsNum();
        devHome.goToUsersPage();

    }
}
