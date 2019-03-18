package com.verifone.tests.marketPlaceTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.marketPlacePages.CBAHomePage;
import com.verifone.pages.marketPlacePages.CBALoginPage;
import com.verifone.pages.marketPlacePages.CBAMarketplace;
import com.verifone.pages.marketPlacePages.CBAMyApps;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

public class DownloadAppTest extends BaseTest
{

    @Test(testName = "LogInTest", description = "log in to CBA marketPlace")
    public void CBALoginTestUI()
    {
        CBAHomePage homePage = (CBAHomePage) PageFactory.getPage("CBAHomePage");
        homePage.clickOnLogInLink();

        CBALoginPage loginPage = (CBALoginPage) PageFactory.getPage("CBALoginPage");
        loginPage.LogInToCBAAccount();

        CBAMyApps myApps = (CBAMyApps) PageFactory.getPage("CBAMyApps");
        myApps.verifyAppSubcribed("iphone");

        CBAMarketplace marketplace = (CBAMarketplace) PageFactory.getPage("CBAMarketplace");


    }


}
