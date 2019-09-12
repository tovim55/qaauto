package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class SubscribeAppTest extends BaseTest {

    private static String getAppName;
    private static String deviceUserName;
    @Test(testName = "LogIn & subscribe an app", description = "log in to CBA marketPlace and purchase an application")
    public void CBASubscribeAppTestUI() {

        //loginCBA(createMerchantUser());

        loginCBA(createAssignUser());
        getAppName = BaseTest.envConfig.getAppName();
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(getAppName);
        market.veryfyListingApps();
        market.buyFreeApp();
    }

    @Test(priority = 2, testName = "Login & Assign App to user", description = "Login in to CBA MarketPlace and assign application to the user")
    public void CBAAssignToUserAppUI() throws InterruptedException {

        /* Login to CBAMarket Place */
        loginCBA(createAssignUser());

        getAppName = BaseTest.envConfig.getAppName();
        deviceUserName = BaseTest.envConfig.getDeviceUserName();

        System.out.println("get App name :" + getAppName);

        /* Move to Assign Apps to User */
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();
        assignApp.btnSelectAssignAppsToUser();
        assignApp.searchAppToAssign(getAppName);
        assignApp.searchUserToAssign(deviceUserName);
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }
}
