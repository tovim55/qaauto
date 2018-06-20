package com.verifone.pages;

//import com.verifone.utils.CGLoginPage.CGLoginPage;

import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.HomePage;

public class PageFactory {


    public static BasePage getPage(String page) {
        switch (page) {
            case "LoginPage":
                return new LoginPage();

            case "SignUpPage":
                return new SignUpPage();

            case "CGLoginPage":
                return new CGLoginPage();
//
            case "VerifoneAccountLoginPage":
                return new VerifoneAccountLoginPage();

            case "DevHomePage":
                return new DevHomePage();

            case "DevProfilePage":
                return new DevProfilePage();

            default:
                System.out.println("Can not create a Page, missing implementation of class " + page);
        }
        return null;
    }

}
