package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.createAssignUser;
import static com.verifone.tests.steps.mpPortal.Steps.loginCBA;

public class PurchaseOneTimeFreeTrialAppUI extends BaseTest {

    /**
     * This test case described the one time pay free trial app purchase on market place
     * @author : Prashant Lokhande
     */

    private static String getOneTimePayFreeTrialApp;

    @Test(priority = 1,testName = "Log In & Search One Time Pay Free Trial App", description = "Login in to CBA Marketplace & search One Time Pay Free Trial App.")
    public void CBASearchOneTimePayFreeTrialAppTestUI() throws InterruptedException{

        /*Login in to CBA Marketplace*/
        loginCBA(createAssignUser());

        getOneTimePayFreeTrialApp = BaseTest.envConfig.getOneTimePayFreeTrialApp();

        /*Move to the Marketplace and Purchase (subscribe) an app - edition Yearly Recurring Pay Free Trial app*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(getOneTimePayFreeTrialApp);
        market.veryfyListingApps();
        market.isAppPurchased(getOneTimePayFreeTrialApp);
    }

    @Test(priority = 2, testName = "LogIn & Purchase (subscribe) an app", description = "Log in to CBA Marketplace and Purchase (subscribe) One Time Pay Free Trial App")
    public void CBAPurchaseOneTimePayFreeTrialAppUI() throws Exception {
        loginCBA(createAssignUser());

        /* Search & Purchase app from the marketplace*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(getOneTimePayFreeTrialApp);
        market.buyOneTimeApp();

        /*Assign purchased app to user*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.searchUserToAssign(createAssignUser().getUserName());
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 3, testName = "LogIn & Verify purchased (subscribed) app", description = "Log in to CBA Marketplace and verify purchased One Time Pay Free Trial App in the My Apps")
    public void CBAVerifyOneTimePayFreeTrialAppTestUI() {
        loginCBA(createAssignUser());

        /*Verify the purchased app is present in the My App*/
        CBAMyApps myApps = PageFactory.getCBAMyApps();
        myApps.verifyPurchasedApp(getOneTimePayFreeTrialApp);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App", description = "Log in to CBA account and remove One Time Pay Free Trial app from apps list")
    public void CBAUnsubscribeOneTimePayFreeTrialAppTestUI() throws InterruptedException {

        loginCBA(createAssignUser());

        /*Cancel Subscription of purchased app*/
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(getOneTimePayFreeTrialApp);
    }

}
