package com.verifone.pages;

//import com.verifone.utils.CGLoginPage.CGLoginPage;

import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.utils.Mail.InboxGetnada;

public class PageFactory {


    public static BasePage getPage(String page) {
        switch (page) {
            case "LoginPage":
                return new LoginPage();

            case "CreateMerchantPage":
                return new CreateMerchantPage();

            case "SignUpPage":
                return new SignUpPage();

            case "CGLoginPage":
                return new CGLoginPage();

            case "DevSupportHomePage":
                return new DevSupportHomePage();

            case "DevUsersPage":
                return new DevUsersPage();
//
//            case "VerifoneAccountLoginPage":
//                return new VerifoneAccountLoginPage();


            case "SetupPasswordPage":
                return new SetupPasswordPage();

            case "AcceptMerchantAgreementPage":
                return new AcceptMerchantAgreementPage();

            case "MerchantViewPage":
                return new MerchantViewPage();
            case "ForgotPasswordPage":
                return new ForgotPasswordPage();
            case "EmailConfirmPage":
                return new EmailConfirmPage();
            case "ResetPasswordPage":
                return new ResetPasswordPage();
            case "ResetThankYou":
                return new ResetThankYou();
//            case "VerifoneAccountLoginPage":
//                return new VerifoneAccountLoginPage();
//
            case "DevHomePage":
                return new DevHomePage();
//
            case "DevProfilePage":
                return new DevProfilePage();

            case "InboxGetnada":
                return new InboxGetnada();

            default:
                System.out.println("Can not create a Page, missing implementation of class " + page);
        }
        return null;
    }

}
