package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class PurchaseOneTimeAppUI extends BaseTest {

    private static String oneTimeAppName;

    /**
     * This test case described the one time pay app purchase on market place
     * @author : Prashant Lokhande
     */

    @Test(priority = 1, testName = "LogIn & Search One Time Pay app", description = "Login in to CBA Marketplace and search edition One Time Pay app")
    public void CBASearchOneTimePayAppTestUI()throws Exception {

        /* Login in to CBA Marketplace*/
        loginCBA(createAssignUser());

        oneTimeAppName = BaseTest.envConfig.getOneTimeAppName();

        /*Move to the Marketplace and Purchase (subscribe) an app - edition One Time Pay app*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(oneTimeAppName);
        market.veryfyListingApps();
    }

    @Test(priority = 2, testName = "LogIn & Purchase (subscribe) an app", description = "Log in to CBA Marketplace and Purchase (subscribe) One Time Pay App")
    public void CBAPurchaseOneTimePayAppUI() throws Exception {
        loginCBA(createAssignUser());

         /*Search & Purchase app from the marketplace*/
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(oneTimeAppName);
        market.buyOneTimeApp();

        /*Assign purchased app to user*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.searchUserToAssign();
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 3, testName = "LogIn & Verify purchased (subscribed) app", description = "Log in to CBA Marketplace and verify purchased One Time Pay App in the My Apps")
    public void CBAVerifyOneTimePayAppTestUI() {
        loginCBA(createAssignUser());

        /* Verify the purchased app is present in the My App */
        CBAMyApps myApps = PageFactory.getCBAMyApps();
        myApps.verifyPurchasedApp(oneTimeAppName);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App", description = "Log in to CBA account and remove app from apps list")
    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        loginCBA(createAssignUser());

        /*Remove purchased app */
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(oneTimeAppName);
    }

}
