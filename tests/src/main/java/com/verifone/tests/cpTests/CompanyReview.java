package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Mail.InboxGetnada;
import org.testng.annotations.Test;

public class CompanyReview extends BaseTest {



    @Test(testName = "Support Admin Accept Basic Dev", description = "After Dev Registration Dev Admin Reviewing And Accept company", groups = {"CP-Portal"})
    public void connectWithCompanyUI() throws Exception {
//        starTestLog("Support Admin Accept Basic Dev", "After Dev Registration Dev Admin Reviewing And Accept company");
//
//        User developer = EntitiesFactory.getEntity("NewUser");
//        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
//        signUpPage.signUp(developer);
//        assertTextContains("Thanks for your registration!", signUpPage.getMessege());
//        String message = new InboxGetnada().getLastMessage(developer.getUserName());
//        assertTextContains(message, "Activate Account");

        User developer = new User("sbvbqupg@getnada.com", "Veri1234");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.clickOmLoginBtn();
        loginPage.loginPageCp(developer);

        DevHomePage homePage  = (DevHomePage) PageFactory.getPage("DevHomePage");
        homePage.clickconnectWithCompany();
        DevProfilePage devProfilePage = (DevProfilePage) PageFactory.getPage("DevProfilePage");
        devProfilePage.editUserInfo();
        devProfilePage.fillCompanyInfo(developer);


//        User devSupport = EntitiesFactory.getEntity("DevSupportAdmin");
//        VerifoneAccountLoginPage loginPage = (VerifoneAccountLoginPage) PageFactory.getPage("VerifoneAccountLoginPage");
//        loginPage.login(devSupport);

    }
}
