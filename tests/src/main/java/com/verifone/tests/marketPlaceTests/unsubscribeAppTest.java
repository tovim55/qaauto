package com.verifone.tests.marketPlaceTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.marketPlacePages.CBAAccount;
import com.verifone.pages.marketPlacePages.CBAHomePage;
import com.verifone.pages.marketPlacePages.CBALoginPage;
import com.verifone.pages.marketPlacePages.CBAMyApps;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.Steps;
import org.testng.annotations.Test;

public class unsubscribeAppTest extends BaseTest {

    private static String appName = "TestDevport4839";

    @Test(testName = "LogIn & unsubscribe an app", description = "log in to CBA account and remove app from apps list")

    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        Steps.loginCBA();

        CBAAccount account = (CBAAccount) PageFactory.getPage("CBAAccount");
        account.manageApps (appName);
        account.unsubscribeApp(appName);

    }
}
