package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Mail.InboxGetnada;
import org.testng.annotations.Test;

import static com.verifone.utils.Assertions.assertTextContains;

public class CompanyReview extends BaseTest {


//    @Test(testName = "Developer Basic Add Company", description = "After sign up Dev login,  fill personal & company info, Submit application", groups = {"CP-Portal"})
    public User connectWithCompanyUI() throws Exception {
        // Dev SignUp
        User developer = EntitiesFactory.getEntity("NewUser");
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(developer);
        assertTextContains("Thanks for your registration!", signUpPage.getMessege());
        String message = new InboxGetnada().getLastMessage(developer.getUserName());
        assertTextContains(message, "Activate Account");

        // Dev Login
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.clickOmLoginBtn();
        loginPage.loginPageCp(developer);

        // Dev go to info page, fill personal & company info
        DevHomePage homePage = (DevHomePage) PageFactory.getPage("DevHomePage");
        homePage.clickconnectWithCompany();
        DevProfilePage devProfilePage = (DevProfilePage) PageFactory.getPage("DevProfilePage");
        devProfilePage.editUserInfo();
        devProfilePage.fillCompanyInfo(developer);
        assertTextContains("In Review", devProfilePage.getMembershipStatus());
        return developer;

    }


    @Test(testName = "Support Admin Accept Basic Dev", description = "After Dev Registration Dev Admin Reviewing And Accept company", groups = {"CP-Portal"})
    public void acceptCompanyUI() throws Exception {
        // Dev SignUp
//        User developer = EntitiesFactory.getEntity("NewUser");
//        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
//        signUpPage.signUp(developer);
//        assertTextContains("Thanks for your registration!", signUpPage.getMessege());
//        String message = new InboxGetnada().getLastMessage(developer.getUserName());
//        assertTextContains(message, "Activate Account");
//
//        // Dev Login
//        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
//        loginPage.clickOmLoginBtn();
//        loginPage.loginPageCp(developer);
//
//        // Dev go to info page, fill personal & company info
//        DevHomePage homePage = (DevHomePage) PageFactory.getPage("DevHomePage");
//        homePage.clickconnectWithCompany();
//        DevProfilePage devProfilePage = (DevProfilePage) PageFactory.getPage("DevProfilePage");
//        devProfilePage.editUserInfo();
//        devProfilePage.fillCompanyInfo(developer);
//        assertTextContains("In Review", devProfilePage.getMembershipStatus());

        User dev = connectWithCompanyUI();
        User devSupport = EntitiesFactory.getEntity("DevSupportAdmin");
        VerifoneAccountLoginPage loginPage = (VerifoneAccountLoginPage) PageFactory.getPage("VerifoneAccountLoginPage");
        loginPage.login(devSupport);

    }


}
