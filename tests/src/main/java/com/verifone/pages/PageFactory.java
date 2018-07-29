package com.verifone.pages;

//import com.verifone.utils.CGLoginPage.CGLoginPage;

import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.AddUserPage;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.pages.eoPages.LoginEOPortal;
import com.verifone.pages.eoPages.UserDetailsPage;
import com.verifone.pages.eoPages.UsersPage;

public class PageFactory {


    public static BasePage getPage(String page) {
        switch (page) {
            case "LoginPage":
                return new LoginPage();
            case "LoginEOPortal":
                return new LoginEOPortal();
			case "LoginSSOPage":
				return new LoginSSOPage();

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
    		case "WelcomePage":
    			return new WelcomePage();
    		case "PasswordSetupPage":
    			return new PasswordSetupPage();
    		case "HomePage":
    			return new HomePage();
    		case "UsersPage":
    			return new UsersPage();
    		case "AddUserPage":
    			return new AddUserPage();
    		case "UserDetailsPage":
    			return new UserDetailsPage();
			case "NoAccessPage":
				return new NoAccessPage();
    			
//            case "VerifoneAccountLoginPage":
//                return new VerifoneAccountLoginPage();
//
            case "DevHomePage":
                return new DevHomePage();
//
            case "DevProfilePage":
                return new DevProfilePage();

            default:
                System.out.println("Can not create a Page, missing implementation of class " + page);
        }
        return null;
    }

}
