package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class PurchaseMonthlyRecurringFreeTrialAppUI extends BaseTest {

    /**
     * This test case described the monthly recurring free trial app purchase on market place
     * @author : Prashant Lokhande
     */

    private static String getMonthlyRecurringFreeTrialApp;

    @Test(priority = 1,testName = "LogIn & Search Monthly Recurring Free Trial App",description = "Login in to CBA Marketplace & search Monthly Recurring Free Trial App.")
    public void CBASearchMonthlyRecurringFreeTrialAppTestUI() throws InterruptedException{
        /* Login into CBA MarketPlace*/
        loginCBA(createAssignUser());

        getMonthlyRecurringFreeTrialApp = BaseTest.envConfig.getMonthlyRecurringFreeTrialApp();

        /*Move to the Marketplace and search app - edition Monthly Recurring Free Trial app*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(getMonthlyRecurringFreeTrialApp);
        market.veryfyListingApps();
        market.isAppPurchased(getMonthlyRecurringFreeTrialApp);
    }

    @Test(priority = 2, testName = "LogIn & Purchase (subscribe) an app", description = "Log in to CBA Marketplace and Purchase (subscribe) Monthly Recurring Free Trial App.")
    public void CBAPurchaseMonthlyRecurringFreeTrialAppUI() throws Exception {
        loginCBA(createAssignUser());

        /*Search & Purchase app from the marketplace*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(getMonthlyRecurringFreeTrialApp);
        market.buyOneTimeApp();

        /*Assign purchased app to user*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.searchUserToAssign();
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 3, testName = "LogIn & Verify purchased (subscribed) app", description = "Log in to CBA Marketplace and verify purchased Monthly Recurring Free Trial App in the My Apps.")
    public void CBAVerifyMonthlyRecurringFreeTrialAppTestUI() {
        loginCBA(createAssignUser());

        /*Verify the purchased app is present in the My App*/
        CBAMyApps myApps = PageFactory.getCBAMyApps();
        myApps.verifyPurchasedApp(getMonthlyRecurringFreeTrialApp);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App", description = "Log in to CBA account and remove Monthly Recurring Free Trial app from apps list.")
    public void CBAUnsubscribeMonthlyRecurringFreeTrialAppTestUI() throws InterruptedException {

        loginCBA(createAssignUser());

        /*Cancel Subscription of purchased app*/
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(getMonthlyRecurringFreeTrialApp);
    }
}


