package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

public class subscribeAppTest extends BaseTest {

    private static String appName = "TestDevport4839";

    @Test(testName = "LogIn & subscribe an app", description = "log in to CBA marketPlace and purchase an application")
    public void CBASubscribeAppTestUI() throws InterruptedException {

        Steps.loginCBA();

        CBAMarketplace market = (CBAMarketplace) PageFactory.getPage("CBAMarketplace");
        market.searchForApp(appName);
        market.veryfyListingApps();
        market.buyApp();

        CBAMyApps myApps = (CBAMyApps) PageFactory.getPage("CBAMyApps");
        myApps.verifyMessage();



    }
}
