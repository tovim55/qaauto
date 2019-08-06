package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.createAssignUser;
import static com.verifone.tests.steps.mpPortal.Steps.createMerchantUser;
import static com.verifone.tests.steps.mpPortal.Steps.loginCBA;

public class PurchaseFreeAppUI extends BaseTest {

    /**
     * This test case described the free pay app purchase on market place
     * @author : Prashant Lokhande
     */

    public static String freeEditionApp;

    @Test(priority = 1, testName = "LogIn & Search Free App", description = "Login in to CBA Marketplace and search Free Edition App")
    public void CBASearchEditionFreeAppTestUI()throws Exception {

        /* Login in to CBA Marketplace*/
        loginCBA(createAssignUser());

        freeEditionApp = BaseTest.envConfig.getFreeEditionApp();

        /*Move to the Marketplace and Purchase (subscribe) an app - edition One Time Pay app*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(freeEditionApp);
        market.veryfyListingApps();
        market.isAppPurchased(freeEditionApp);
    }

    @Test(priority = 2, testName = "LogIn & Purchase (subscribe) an app", description = "Log in to CBA Marketplace and Purchase (subscribe) Free Edition App")
    public void CBAPurchaseOneTimePayAppUI() throws Exception {
        loginCBA(createAssignUser());

        /*Search & Purchase app from the marketplace*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(freeEditionApp);
        market.buyFreeApp();

        /*Assign purchased app to user*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();
        assignApp.btnSelectAssignAppsToUser();
        assignApp.searchAppToAssign(freeEditionApp);
        assignApp.searchUserToAssign();
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 3, testName = "LogIn & Verify purchased (subscribed) app", description = "Log in to CBA Marketplace and verify purchased free app in the My Apps")
    public void CBAVerifyOneTimePayAppTestUI() {
        loginCBA(createAssignUser());

        /* Verify the purchased app is present in the My App */
        CBAMyApps myApps = PageFactory.getCBAMyApps();
        myApps.verifyPurchasedApp(freeEditionApp);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App", description = "Log in to CBA account and Cancel Subscription of app from the apps list")
    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        loginCBA(createAssignUser());

        /*Remove purchased app */
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(freeEditionApp);
    }
}
