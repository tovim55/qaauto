package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class SubscribeAppTest extends BaseTest {

    private static String appName = "Tab - Free - MultiUser - Hello World";

    @Test(testName = "LogIn & subscribe an app", description = "log in to CBA marketPlace and purchase an application")
    public void CBASubscribeAppTestUI() throws InterruptedException {

        loginCBA(createMerchantUser());

        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.searchForApp(appName);
        market.veryfyListingApps();
        market.buyFreeApp();

//        CBAMyApps myApps = PageFactory.getCBAMyApps();
//        myApps.verifyMessage();

    }
}
