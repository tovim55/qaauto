package com.verifone.tests.steps;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.*;
import com.verifone.utils.Mail.InboxGetnada;
import com.verifone.utils.appUtils.Application;
import com.verifone.utils.appUtils.ApplicationUtils;

import java.awt.*;
import java.io.IOException;

import static com.verifone.pages.cpPages.LoginPage.restartDriver;
import static com.verifone.utils.Assertions.assertTextContains;

//import static com.verifone.infra.SeleniumUtils.restartDriver;

public class Steps {

    public static Company devSignUp() {
        Company developer = (Company) EntitiesFactory.getEntity("Company");
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(developer);
        assertTextContains("Thanks for your registration!", signUpPage.getMessege());
        String message = new InboxGetnada().getLastMessage(developer.getUserName(), "Previous");
        assertTextContains(message, "Activate Account");
        return developer;
    }

    public static void devLogin(Company dev) {
        LoginPage loginPage = (com.verifone.pages.cpPages.LoginPage) PageFactory.getPage("LoginPage");
        loginPage.clickOmLoginBtn();
        loginPage.loginPageCp(dev);
    }

    public static void devLogin() {
        Company developer = (Company) EntitiesFactory.getEntity("Company");
        LoginPage loginPage = (com.verifone.pages.cpPages.LoginPage) PageFactory.getPage("LoginPage");
        loginPage.clickOmLoginBtn();
        loginPage.loginPageCp(developer);
    }

    public static void devLoginFillCompany(Company dev) throws AWTException, InterruptedException, IOException {
        // Dev go to info page, fill personal & company info
        DevHomePage homePage = (DevHomePage) PageFactory.getPage("DevHomePage");
        homePage.clickconnectWithCompany();
        DevProfilePage devProfilePage = (DevProfilePage) PageFactory.getPage("DevProfilePage");
        devProfilePage.editUserInfo();
        devProfilePage.fillCompanyInfo(dev);
        assertTextContains("In Review", devProfilePage.getMembershipStatus());
    }


    public static LoginPage devSupportAdminLogin() throws Exception {
        User dev = EntitiesFactory.getEntity("DevSupportAdmin");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.supportLogin(dev);
        return loginPage;
    }

//    public static LoginPage devSupportAdminLogin(Company dev) {
////        User dev = EntitiesFactory.getEntity("DevSupportAdmin");
//        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
//        loginPage.supportLogin(dev);
//        return loginPage;
//    }

    public static void checkCompaniesList(Company dev) throws Exception {
//        restartSession();
        LoginPage loginPage = devSupportAdminLogin();
        loginPage.checkExistCompanies(dev);
    }

    public static void restartSession() {
        // Not recommended to use this method
        restartDriver();
    }

    public static void checkAcceptCompany(Company dev) throws Exception {//Company dev
        LoginPage loginPage = devSupportAdminLogin();
        loginPage.acceptCompany(dev);//dev
    }

    public static void checkRejectCompany(Company dev) throws Exception {//Company dev
        LoginPage loginPage = devSupportAdminLogin();
        loginPage.rejectCompany(dev);//dev
    }

    public static void logout(){
        new DevHomePage().logout();
    }

    public static void createApp() throws InterruptedException, IOException, AWTException {
        DevHomePage homePage = (DevHomePage) PageFactory.getPage("DevHomePage");
        NewAppFormPage newAppFormPage = (NewAppFormPage) PageFactory.getPage("NewAppFormPage");
        homePage.createAppBtn();
        Application app = new Application("ppppoooo", "", "1.0.0 ", "this test", "this is veri important!!");
        String id = newAppFormPage.fillGetStartedForm(app);
        ApplicationUtils.createZipApp(id, app.getAppName());
        newAppFormPage.fillUploadPackageForm(app.appPath + "\\" + id + ".zip");
        newAppFormPage.fillAppIconScreenshots(app.iconPath);
        ApplicationUtils.deleteDirectory();
        newAppFormPage.fillPriceForm();
        newAppFormPage.fillLegalAndSupportForm();
        newAppFormPage.clickOnSubmitBtn();

    }


}

