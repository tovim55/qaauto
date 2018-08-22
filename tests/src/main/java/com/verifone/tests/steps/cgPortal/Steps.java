package com.verifone.tests.steps.cgPortal;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cgPages.CGApplicationPage;

public class Steps {

    public static void loginAndCheck(){
        User user = EntitiesFactory.getEntity("CGPortal");
        System.out.println(user.getPassword());
        CGLoginPage page = (CGLoginPage) PageFactory.getPage("CGLoginPage");
        page.openChrome();
        page.doLogin(user);
        page.checkTitle();
    }

    public static void openChromeBrowser(){
        CGLoginPage page = (CGLoginPage) PageFactory.getPage("CGLoginPage");
        page.openChrome();
    }

    public static void appNavigate(){
//        loginAndCheck();
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
//        page.doLogin(user);
        page.nevigateAppPage();
    }

    public static void checkAppFields(String applicationsID, String version, String name, String status, String access,
                                      String maxRequestCount, String error){
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
        page.checkFields(applicationsID, version, name, status,access, maxRequestCount, error);
    }

}
