package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class PurchaseAppUI extends BaseTest {

    @Test(priority = 1, testName = "LogIn & subscribe Free app", description = "log in to CBA marketPlace and purchase Free application")
    public void CBASubscribeAppTestUI() throws InterruptedException {
        loginCBA(createMerchantUser());
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp();
        market.veryfyListingApps();
        market.buyFreeApp();
    }

    @Test(priority = 2, testName = "Assign User to app", description = "assign user to subscribed application")
    public void CBAAssignToUserAppUI() throws InterruptedException {
        loginCBA(createMerchantUser());

        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.assignUsersToApps();
        assignApp.userAssignment();
    }

    @Test(priority = 3, testName = "LogIn & verify MyApps", description = "log in to CBA MyApps and verify myApps list")
    public void CBAMyAppsTest_AfterPurchaseUI() {
        loginCBA(createMerchantUser());
        verifyMyAppsCBA();
    }

    @Test(priority = 4, testName = "LogIn & unsubscribe an app", description = "log in to CBA account and remove app from apps list")
    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        loginCBA(createMerchantUser());

        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion();
    }


//    @Test(priority=4, testName = "VHQ LogIn & Package Download", description = "log in to VHQ EndPoint and verify a package is downloaded to Library")
//
//    public void VHQ_PackageIsDownloadedUI() throws InterruptedException {
//
//        loginVHQ(createVHQUser());
//
//        VHQHomePage vhq = PageFactory.getVHQHomePage();
//        vhq.verifyCustomer();
//        vhq.verifyDownloadLibrary();
//
//        VHQDownloadLibrary downLab = PageFactory.getVHQDownloadLibrary();
//        downLab.verifyPackageExist(appName);
//    }


}
