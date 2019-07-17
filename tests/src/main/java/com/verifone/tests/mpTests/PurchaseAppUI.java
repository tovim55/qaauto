package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.pages.vhqPages.VHQDownloadLibrary;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class PurchaseAppUI extends BaseTest
{
    private static String appName = "New Danube";


    @Test(priority=1, testName = "LogIn & subscribe Free app", description = "log in to CBA marketPlace and purchase Free application")
    public void CBASubscribeAppTestUI() throws InterruptedException {

        loginCBA(createMerchantUser());
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(appName);
        market.veryfyListingApps();
        market.buyFreeApp();
    }

    @Test(priority=2, testName = "LogIn & unsubscribe an app", description = "log in to CBA account and remove app from apps list")

    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        loginCBA(createMerchantUser());

        CBAAccount account = PageFactory.getCBAAccount();
        account.manageApps (appName);
        account.cancelSubscribsion(appName);
    }


//    @Test(priority=3, testName = "LogIn & verify MyApps", description = "log in to CBA MyApps and verify myApps list")
//    public void CBAMyAppsTest_AfterPurchaseUI()
//    {
//        loginCBA(createMerchantUser());
//        verifyMyAppsCBA(appName);
//    }

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
