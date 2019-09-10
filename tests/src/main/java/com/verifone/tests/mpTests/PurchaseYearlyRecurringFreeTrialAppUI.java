package com.verifone.tests.mpTests;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;


public class PurchaseYearlyRecurringFreeTrialAppUI extends BaseTest {
    /**
     * This test case described the yearly recurring free trial app purchase on market place
     * @author : Prashant Lokhande
     */

    private static String getYearlyRecurringFreeTrialApp;

    @Test(priority = 1,testName = "LogIn & Search Yearly Recurring Free Trial App",description = "LogIn in to CBA Marketplace & search Yearly Recurring Free Trial App.")
    public void CBASearchYearlyRecurringFreeTrialAppTestUI() throws InterruptedException{

        /*Login in to CBA Marketplace*/
        loginCBA(createAssignUser());

        getYearlyRecurringFreeTrialApp = BaseTest.envConfig.getYearlyRecurringFreeTrialApp();

        /*Move to the Marketplace and Purchase (subscribe) an app - edition Yearly Recurring Pay Free Trial app*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(getYearlyRecurringFreeTrialApp);
        market.veryfyListingApps();
        market.isAppPurchased(getYearlyRecurringFreeTrialApp);
    }

    @Test(priority = 2, testName = "LogIn & Purchase (subscribe) an app", description = "Log in to CBA Marketplace and Purchase (subscribe) Merchant Yearly Recurring Free Trial App.")
    public void CBAPurchaseYearlyRecurringAppTestUI() throws Exception {
        loginCBA(createAssignUser());

        /* Search & Purchase app from the marketplace*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(getYearlyRecurringFreeTrialApp);
        market.buyOneTimeApp();

        /*Assign purchased app to user*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.searchUserToAssign(createAssignUser().getUserName());
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 3, testName = "LogIn & Verify purchased (subscribed) app", description = "Log in to CBA Marketplace and verify purchased Yearly Recurring Free Trial App in the My Apps")
    public void CBAVerifyYearlyRecurringAppTestUI() {
        loginCBA(createAssignUser());

        /*Verify the purchased app is present in the My App*/
        CBAMyApps myApps = PageFactory.getCBAMyApps();
        myApps.verifyPurchasedApp(getYearlyRecurringFreeTrialApp);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App", description = "Log in to CBA account and remove Yearly Recurring Free Trial app from apps list")
    public void CBAUnsubscribeYearlyRecurringAppTestUI() throws InterruptedException {

        loginCBA(createAssignUser());

        /*Cancel Subscription of purchased app*/
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(getYearlyRecurringFreeTrialApp);
    }


}
