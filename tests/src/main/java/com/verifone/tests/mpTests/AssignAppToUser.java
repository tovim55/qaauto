package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.pages.vhqPages.VHQDeviceSearch;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

/**
 * This test case described the application assigning to user.
 *
 * @author : Prashant Lokhande
 */

public class AssignAppToUser extends BaseTest {

    private static String getAppName;
    private static String getCmFiveDeviceSerialNo01;
    private static String deviceUserName;

    @Test(priority = 1, testName = "LogIn & Verify App", description = "Login in to CBA MarketPlace & verify availability of the app")
    public void CBAVerifyAvailabilityOfAppTestUI() throws Exception {
        /*Login to CBAMarket Place*/
        loginCBA(createAssignUser());

        getAppName = BaseTest.envConfig.getAppName();

        /* Verify the purchased app is present in the My App*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.getAppToAssignUser(getAppName);
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

    @Test(priority = 3, testName = "LogIn & Verify Subscribed App Job Status", description = "Log into VHQ portal and verify whether the job is created after Subscribing the Application.")
    public void VHQVerifyInstallAppTestUI() throws Exception {
        /*LogIn into VHQ Portal*/
        loginVHQ(createVHQMumbaiUser());

        getCmFiveDeviceSerialNo01 = BaseTest.envConfig.getCmFiveDeviceSerialNo01();

        /* Get VHQ Home Page*/
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();
        vhqDashboard.deviceSearch(getCmFiveDeviceSerialNo01);
        vhqDashboard.deviceProfile();

        VHQDeviceSearch deviceSearch = PageFactory.getVHQDeviceSearch();
        deviceSearch.validateJobInstall(getAppName, "INSTALL", CBAAssignPage.jobCreatedOnSubscription);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App", description = "Log in to CBA account and Unsubscribe the app.")
    public void CBAUnsubscribeYearlyRecurringAppTestUI() throws InterruptedException {

        loginCBA(createAssignUser());

        /*Cancel Subscription of purchased app*/
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(getAppName);
    }

    @Test(priority = 5, testName = "LogIn & Verify Unsubscribed App Job Status", description = "Log into VHQ portal and verify whether the job is created after Unsubscribe the application.")
    public void VHQVerifyUninstallAppTestUI() throws Exception {
        /*LogIn into VHQ Portal*/
        loginVHQ(createVHQMumbaiUser());

        /* Get VHQ Home Page*/
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();
        vhqDashboard.deviceSearch(getCmFiveDeviceSerialNo01);
        vhqDashboard.deviceProfile();

        VHQDeviceSearch deviceSearch = PageFactory.getVHQDeviceSearch();
        deviceSearch.validateJobInstall(getAppName, "UNINSTALL", CBAAccount.jobCreatedOnUnsubscription);
    }
}
