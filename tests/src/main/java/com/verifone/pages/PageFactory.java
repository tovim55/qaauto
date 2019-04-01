package com.verifone.pages;

//import com.verifone.utils.CGLoginPage.CGLoginPage;

import com.verifone.pages.cgPages.CGApplicationPage;
import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.*;
import com.verifone.pages.mpPages.*;
import com.verifone.pages.marketPlacePages.CBAHomePage;
import com.verifone.pages.marketPlacePages.CBALoginPage;
import com.verifone.pages.marketPlacePages.CBAMarketplace;
import com.verifone.pages.marketPlacePages.CBAMyApps;
import com.verifone.utils.Mail.InboxGetnada;

public class PageFactory {


    public static BasePage getPage(String page) {
        switch (page) {
            case "LoginPage":
                return new LoginPage();
            case "LoginEOPortal":
                return new LoginEOPortal();
            case "OktaLogin":
                return new OktaLogin();
            case "LoginSSOPage":
                return new LoginSSOPage();

            case "CreateMerchantPage":
                return new CreateMerchantPage();

            case "SignUpPage":
                return new SignUpPage();

            case "CGLoginPage":
                return new CGLoginPage();

            case "CGApplicationPage":
                return new CGApplicationPage();

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
            case "ChangePasswordPage":
                return new ChangePasswordPage();
            case "HomePage":
                return new HomePage();
            case "MPHomePage":
                return new MPHomePage();
            case "UsersPage":
                return new UsersPage();
            case "AddUserPage":
                return new AddUserPage();
            case "EditUserPage":
                return new EditUserPage();
            case "EditProfilePage":
                return new EditProfilePage();
            case "EditRolePage":
                return new EditRolePage();
            case "EditBusinessPage":
                return new EditBusinessPage();
            case "UserDetailsPage":
                return new UserDetailsPage();
            case "EmailTemplatePage":
                return new EmailTemplatePage();
            case "MerchantsPage":
                return new MerchantsPage();
            case "MerchantDetailsPage":
                return new MerchantDetailsPage();
            case "MerchantThankYouPage":
                return new MerchantThankYouPage();
            case "MyProfilePage":
                return new MyProfilePage();
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

            case "NewAppFormPage":
                return new NewAppFormPage();

            case "InboxGetnada":
                return new InboxGetnada();
            case "ActionRequiredPage":
                return new ActionRequiredPage();
            case "AgreementPage":
                return new AgreementPage();
            case "CBAHomePage":
                return new CBAHomePage();
            case "CBALoginPage":
                return new CBALoginPage();
            case "CBAMyApps":
                return new CBAMyApps();
            case "CBAMarketplace":
                return new CBAMarketplace();
            case "ManageMarketplacePage":
                return new ManageMarketplacePage();
            case "ProductsTab":
                return new ProductsTab();
            case "CreateSegmentGroupPage":
                return new CreateSegmentGroupPage();
            case "EditSegmentGroupPage":
                return new EditSegmentGroupPage();
            case "CreateSegmentPage":
                return new CreateSegmentPage();
            case "EditSegmentPage":
                return new EditSegmentPage();
            case "ProductsTabProductCatalogPage":
                return new ProductsTabProductCatalogPage();
            case "ProductSettingsProductsTab":
                return new ProductSettingsProductsTab();
            case "MarketplacePage":
                return new MarketplacePage();
            case "LoginMPPortal":
                return new LoginMPPortal();

            default:
                System.out.println("Can not create a Page, missing implementation of class " + page);
        }
        return null;
    }

}
