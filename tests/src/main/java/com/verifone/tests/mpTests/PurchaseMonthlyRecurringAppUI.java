package com.verifone.tests.mpTests;

import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;
import static com.verifone.tests.steps.mpPortal.Steps.*;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.PageFactory;

public class PurchaseMonthlyRecurringAppUI extends BaseTest {
    /**
     * This test case described the monthly recurring app purchase on market place
     * @author : Prashant Lokhande
     */

    private static String mothlyRecurringApp;

    @Test(priority = 1,testName = "Log In & Search Merchant Recurring App",description = "Login in to CBA Marketplace & search Monthly Recurring App.")
    public void CBASearchMonthlyRecurringAppTestUI() throws InterruptedException{

        /* Login in to CBA Marketplace*/
        loginCBA(createAssignUser());

        mothlyRecurringApp = BaseTest.envConfig.getMonthlyRecurringApp();

        /*Move to the Marketplace and Purchase (subscribe) an app - edition Monthly Recurring Pay app*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(mothlyRecurringApp);
        market.veryfyListingApps();
        market.isAppPurchased(mothlyRecurringApp);
    }

    @Test(priority = 2, testName = "LogIn & Purchase (subscribe) an app", description = "Log in to CBA Marketplace and Purchase (subscribe) Monthly Recurring Pay App")
    public void CBAPurchaseMonthlyRecurringAppTestUI() throws Exception {
        loginCBA(createAssignUser());

        /*Search & Purchase app from the marketplace*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(mothlyRecurringApp);
        market.buyOneTimeApp();

        /*Assign purchased app to user*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.searchUserToAssign(createAssignUser().getUserName());
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 3, testName = "LogIn & Verify purchased (subscribed) app", description = "Log in to CBA Marketplace and verify purchased Monthly Recurring Pay App in the My Apps")
    public void CBAVerifyMonthlyRecurringAppTestUI() {
        loginCBA(createAssignUser());

        /*Verify the purchased app is present in the My App*/
        CBAMyApps myApps = PageFactory.getCBAMyApps();
        myApps.verifyPurchasedApp(mothlyRecurringApp);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App", description = "Log in to CBA account and remove Monthly Recurring Pay app from apps list")
    public void CBAUnsubscribeMonthlyRecurringAppTestUI() throws InterruptedException {

        loginCBA(createAssignUser());

        /*Cancel Subscription of purchased app*/
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(mothlyRecurringApp);
    }


}
