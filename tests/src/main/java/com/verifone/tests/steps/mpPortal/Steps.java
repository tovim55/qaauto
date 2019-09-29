package com.verifone.tests.steps.mpPortal;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.OktaLogin;
import com.verifone.pages.mpPages.*;
import com.verifone.pages.vhqPages.VHQLogin;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;

public class Steps {

    public static void loginMPPortal(String Email, String Password, String SecAnswer, String Env) throws Exception {
        WebDriver driver = new MPHomePage().getDriver();
        String update = " updated";

//        String Env = "https://testverifone.appdirect.com/login";
        Boolean TestPassFlag = true;

        testLog.info("-------------------------------------------------Navigate to MP Portal-------------------------------------------------");

        driver.navigate().to(Env);
        LoginMPPortal LoginMPPortal = (LoginMPPortal) PageFactory.getPage("LoginMPPortal");
        //LoginMPPortal.clickLoginBtn();
        String Name;
        Name = Email.substring(0, Email.indexOf("@"));
        String Domain = Email.substring(Email.indexOf("@") + 1);

        if (Domain.contains("verifone")) {
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);

            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
            Thread.sleep(2000);

            if (OktaLogin.loginOktaTitleExists()) {
                if (OktaLogin.loginOktaTitle().contains("Sign In")) {
                    OktaLogin.loginInputName(Name);
                    OktaLogin.loginInputPassword(Password);
                    OktaLogin.clickSignInBtn();
                }
                availableWindows = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(availableWindows.get(0));
                OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
                OktaLogin.loginInputAnswer(SecAnswer);
                testLog.info("Security answer: " + "");
                OktaLogin.clickVerifyBtn();

                Thread.sleep(2000);
            }
        } else {
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);
            LoginMPPortal.loginInputPassword(Password);
            LoginMPPortal.clickLoginBtn();
        }
    }

    public static void loginMPPortal(String Email, String Password, String SecAnswer) throws Exception {

        WebDriver driver = new MPHomePage().getDriver();

        LoginMPPortal LoginMPPortal = PageFactory.getLoginMPPortal();

        String Name = Email.substring(0, Email.indexOf("@"));
        String Domain = Email.substring(Email.indexOf("@") + 1);

        if (Domain.contains("verifone")) {
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);

            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
            Thread.sleep(2000);

            if (OktaLogin.loginOktaTitleExists()) {
                if (OktaLogin.loginOktaTitle().contains("Sign In")) {
                    OktaLogin.loginInputName(Name);
                    OktaLogin.loginInputPassword(Password);
                    OktaLogin.clickSignInBtn();
                }
                availableWindows = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(availableWindows.get(0));
                OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
                OktaLogin.loginInputAnswer(SecAnswer);
                testLog.info("Security answer: " + "");
                OktaLogin.clickVerifyBtn();

                Thread.sleep(2000);
            }
        } else {
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);
            LoginMPPortal.loginInputPassword(Password);
            LoginMPPortal.clickLoginBtn();
        }
    }

    public static User createMerchantUser() {
        User merchant = EntitiesFactory.getEntity("MPMerchantAdmin");
        return merchant;
    }

    public static User createVHQTestUser() {
        User vhqTestAdmin = EntitiesFactory.getEntity("VHQTestUserAdmin");
        return vhqTestAdmin;
    }

    public static User createVHQMumbaiUser() {
        User vhqMumbaiAdmin = EntitiesFactory.getEntity("VHQMumbaiUserAdmin");
        return vhqMumbaiAdmin;
    }

    /**
     * @author : Prashant Lokhande
     * This method described to create new assign user.
     */
    public static User createAssignUser() {
        User assignUser = EntitiesFactory.getEntity("MPAssignUser");
        return assignUser;
    }

    public static User createAssignUser(String userName) {
        User assignUser = EntitiesFactory.getEntity(userName);
        return assignUser;
    }

    public static void loginCBA(User user) {
        navigateCBAHome();

        CBALoginPage loginPage = PageFactory.getCBALoginPage();
        loginPage.LogInToCBAAccount(user);
    }

    public static void navigateCBAHome() {
        CBAHomePage homePage = PageFactory.getCBAHomePage();
        homePage.clickOnLogInLink();
    }

    public static void verifyMyAppsCBA(String appName) {
        CBAMyApps myApps = PageFactory.getCBAMyApps();
        myApps.verifyAppSubcribed(appName);
    }

    public static void loginVHQ(User user) {
        VHQLogin vhqLogin = PageFactory.getVHQLogin();
        vhqLogin.LoginInVhq(user);

    }

}
