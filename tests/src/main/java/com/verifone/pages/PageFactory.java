package com.verifone.pages;

//import com.verifone.utils.LoginPage.LoginPage;

import com.verifone.pages.cpPages.LoginPageNew;

public class PageFactory {


	public static BasePage getPage(String page) {
		switch (page) {
		case "LoginPage":
			return new LoginPageNew();
			
//		case "Applications":
//			return new ActivationsPage();
////
//		case "Activations":
//			return new ActivationsPage(page);

		default:
			System.out.println("Can not create a Page, missing implementation of class "+page );
		}
        return null;
    }
	
}
