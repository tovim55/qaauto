package com.verifone.tests.mpTests;

import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAHomePage;
import com.verifone.pages.mpPages.CBALoginPage;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.createMerchantUser;
import static com.verifone.tests.steps.mpPortal.Steps.loginCBA;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;


public class WhiteLabelingUI extends BaseTest {

    @Test(priority=0, testName = "Verify CCS Values on Home Page", description = "open CBA marketPlace Home Page and validate color, background-color, font-family and font-size of elements")
    public void CBAHomePageUI() throws InterruptedException {

        CBAHomePage cbaHomePage = PageFactory.getCBAHomePage();
        cbaHomePage.whiteLabelingHome();

    }

    @Test(priority=1, testName = "Verify CCS Values on Login Page", description = "open CBA marketPlace and go to Login Page and validate color, background-color, font-family and font-size of elements")
    public void CBALoginPageUI() throws InterruptedException {

        navigateCBAHome ();
        CBALoginPage cbaLoginPage = PageFactory.getCBALoginPage();
        cbaLoginPage.whiteLabelingLogin();

    }

}
