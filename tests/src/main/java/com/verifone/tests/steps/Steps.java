package com.verifone.tests.steps;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.DevHomePage;
import com.verifone.pages.cpPages.DevProfilePage;
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.cpPages.SignUpPage;
import com.verifone.utils.Mail.InboxGetnada;

import java.awt.*;
import java.io.IOException;

//import static com.verifone.infra.SeleniumUtils.restartDriver;
import static com.verifone.pages.cpPages.LoginPage.restartDriver;
import static com.verifone.utils.Assertions.assertTextContains;

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


    public static LoginPage devSupportAdminLogin() {
        User dev = EntitiesFactory.getEntity("DevSupportAdmin");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.supportLogin(dev);
        return loginPage;
    }

    public static void checkCompaniesList(Company dev) {
//        restartSession();
        LoginPage loginPage = devSupportAdminLogin();
        loginPage.checkExistCompanies(dev);
    }

    public static void restartSession (){ restartDriver();}

    public static void checkAcceptCompany(Company dev){
        LoginPage loginPage = devSupportAdminLogin();
        loginPage.acceptCompany(dev);
    }

}

