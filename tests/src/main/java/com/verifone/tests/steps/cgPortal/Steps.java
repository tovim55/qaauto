package com.verifone.tests.steps.cgPortal;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cgPages.CGApplicationPage;
import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.marketPlacePages.CBAHomePage;
import com.verifone.pages.marketPlacePages.CBALoginPage;
import com.verifone.pages.marketPlacePages.CBAMyApps;

public class Steps {

    public static void loginAndCheck() {
        User user = EntitiesFactory.getEntity("CGPortal");
        System.out.println(user.getPassword());
        CGLoginPage page = (CGLoginPage) PageFactory.getPage("CGLoginPage");
        page.openChrome();
        page.doLogin(user);
        page.checkTitle();
    }

    public static void openChromeBrowser() {
        CGLoginPage page = (CGLoginPage) PageFactory.getPage("CGLoginPage");
        page.openChrome();
    }

    public static void appPageNavigate() {
//        loginAndCheck();
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
//        page.doLogin(user);
        page.navigateAppPage();
    }

    public static void connectionsNavigate() {
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
//        page.doLogin(user);
        page.navigateToConnection();
    }

    public static String checkAppFields(String applicationsID, String version, String name, String status,
                                        String access, String maxRequestCount, String error, boolean normalCheck) {
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
        return page.checkFields(applicationsID, version, name, status, access, maxRequestCount, error, normalCheck);
    }

    public static String checkConnectionFields(String Host, String SandBox, String Protocol, String RowNumber,
                                               String Error) {
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
        return page.connectionsFields(Host, SandBox, Protocol, RowNumber, Error);
    }

    public static void checkCancelBtn() {
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
        page.checkCancelButton();
    }

    public static void checkSaveBtn(String appID) {
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
        page.checkSaveButton(appID);
    }

    public static void searchNewConnect(String newHost) {
        CGApplicationPage page = (CGApplicationPage) PageFactory.getPage("CGApplicationPage");
        page.searchConnect(newHost);
    }

}
