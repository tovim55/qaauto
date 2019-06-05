package com.verifone.tests.steps;

import com.google.gson.JsonObject;
import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.*;
import com.verifone.utils.Mail.InboxGetnada;
import com.verifone.utils.apiClient.dc.SsoApi;
import com.verifone.utils.apiClient.getToken.GetTokenApi;
import com.verifone.utils.appUtils.Application;
import com.verifone.utils.appUtils.ApplicationUtils;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.verifone.tests.BaseTest.envConfig;
import static com.verifone.utils.Assertions.assertTextContains;
import static com.verifone.utils.apiClient.BaseDDTApi.getRequestWithHeaders;

//import static com.verifone.pages.cpPages.LoginPage.restartDriver;

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

    public static void devLogin(User dev) {
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


    public static String getVersions() throws IOException {
        WebDriver driver = new DevHomePage().getDriver();
        LoginPage loginPage = (com.verifone.pages.cpPages.LoginPage) PageFactory.getPage("LoginPage");
        loginPage.clickOmLoginBtn();
        loginPage.loginPageCp(envConfig.getCredentials().getDevAdmin());
//        System.out.println("CP_SESSIONID=" + BasePage.driver.manage().getCookieNamed("CP_SESSIONID").getValue());
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Cookie", "CP_SESSIONID=" + driver.manage().getCookieNamed("CP_SESSIONID").getValue());
        headers.put("X-Requested-With", "XMLHttpRequest");
        System.out.println(driver.manage().getCookieNamed("CP_SESSIONID").getValue());
        JsonObject response = getRequestWithHeaders(envConfig.getApiUrls().getGetVersions(), "get", "", headers, 200);
        System.out.println(response.toString());
        return response.toString();
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

    public static void appsApproval(String appName, Integer approvalNumber) throws Exception {
        if (approvalNumber != 2) {
            LoginPage page = devSupportAdminLogin();
            page.appApproval(appName);
        }
        LoginPage page = devSupportAdminLogin();
        page.appApproval(appName);
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


    public static String getToken(User user) throws IOException {
        GetTokenApi getTokenApi = new GetTokenApi("testId");
        return getTokenApi.getToken(user);
    }


    public static User createDcOrg() throws IOException {
        User eoAdmin = EntitiesFactory.getEntity("EOAdmin");
        User dcOrg = EntitiesFactory.getEntity("NewUser");
        String token = getToken(eoAdmin);
        new SsoApi(token).createDcOrg(dcOrg);
        return dcOrg;
    }


    public static User createDcUser() throws IOException {
        User eoAdmin = EntitiesFactory.getEntity("EOAdmin");
        User dcOrg = createDcOrg();
        User dcUser = EntitiesFactory.getEntity("NewUser");
        SsoApi ssoApi = new SsoApi(getToken(eoAdmin));
        ssoApi.createDcUser(dcUser, dcOrg);
        return dcUser;
    }

    public static User createDcUserGivenOrg(User dcOrg) throws IOException {
        User eoAdmin = EntitiesFactory.getEntity("EOAdmin");
        User dcUser = EntitiesFactory.getEntity("NewUser");
        SsoApi ssoApi = new SsoApi(getToken(eoAdmin));
        ssoApi.createDcUser(dcUser, dcOrg);
        return dcUser;
    }


    public static void checkAcceptCompany(Company dev) throws Exception {//Company dev
        LoginPage loginPage = devSupportAdminLogin();
        loginPage.acceptCompany(dev);//dev
    }

    public static void checkRejectCompany(Company dev) throws Exception {//Company dev
        LoginPage loginPage = devSupportAdminLogin();
        loginPage.rejectCompany(dev);//dev
    }

    public static void logout() {
        new DevHomePage().logout();
    }

    public static String createEngageApp() throws InterruptedException, IOException, AWTException {
        ApplicationUtils.deleteDirectory();
        DevHomePage homePage = (DevHomePage) PageFactory.getPage("DevHomePage");
        NewAppFormPage newAppFormPage = (NewAppFormPage) PageFactory.getPage("NewAppFormPage");
        homePage.createAppBtn();
        Application app = new Application();
        String id = newAppFormPage.fillGetStartedForm(app);
        ApplicationUtils.createZipApp(id, app.getAppName());
        newAppFormPage.fillUploadPackageForm(app.appPath + File.separator + id + ".zip");
        newAppFormPage.fillAppIconScreenshots(app.iconPath);
        newAppFormPage.fillPriceForm();
        newAppFormPage.fillLegalAndSupportForm();
        newAppFormPage.clickOnSubmitBtn();
        ApplicationUtils.deleteDirectory();
        return app.getAppName();
//        return "";

    }

    public static void createAndroidApp() throws InterruptedException, IOException, AWTException {
        ApplicationUtils.deleteDirectory();
        DevHomePage homePage = (DevHomePage) PageFactory.getPage("DevHomePage");
        NewAppFormPage newAppFormPage = (NewAppFormPage) PageFactory.getPage("NewAppFormPage");
        homePage.createAppBtn();
        Application app = new Application();
        String id = newAppFormPage.fillGetStartedForm(app);
        ApplicationUtils.createZipAppAndroid(id, app.getAppName());
        newAppFormPage.fillUploadPackageForm(app.appPath + File.separator + id + ".apk");
        newAppFormPage.fillAppIconScreenshots(app.iconPath);
        newAppFormPage.fillPriceForm();
        newAppFormPage.fillLegalAndSupportForm();
        newAppFormPage.clickOnSubmitBtn();
        ApplicationUtils.deleteDirectory();

    }


}

