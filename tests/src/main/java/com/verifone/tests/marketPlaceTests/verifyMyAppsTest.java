package com.verifone.tests.marketPlaceTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.marketPlacePages.CBAHomePage;
import com.verifone.pages.marketPlacePages.CBALoginPage;
import com.verifone.pages.marketPlacePages.CBAMarketplace;
import com.verifone.pages.marketPlacePages.CBAMyApps;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.Steps;
import org.testng.annotations.Test;

public class verifyMyAppsTest extends BaseTest
{
    private static String appName = "TestDevport4839";

    @Test(testName = "LogIn & verify MyApps", description = "log in to CBA MyApps and verify myApps list")
    public void CBAMyAppsTestUI()
    {

        Steps.loginCBA();
        Steps.verifyMyAppsCBA(appName);
    }

}
