package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBADashboard;
import com.verifone.pages.mpPages.CBAProducts;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.loginMPPortal;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;

public class DeletePackageUI extends BaseTest
{

    @Test(priority = 1, testName = "LogIn & delete package", description = "log in to MarketPlace and delete package from Staging Catalog")
    public void CBADeletePackageTestUI() throws Exception
    {
        User EOAdminSupport = EntitiesFactory.getEntity("EOAdminSupport");

        String EOAdminSupportMail = EOAdminSupport.getUserName();
        String EOAdminSupportPwd = EOAdminSupport.getPassword();
        String EOAdminSupportAnsw = EOAdminSupport.getSecurityAnswer();

        navigateCBAHome();

        loginMPPortal(EOAdminSupportMail, EOAdminSupportPwd, EOAdminSupportAnsw);
        CBADashboard cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketpace();
        cbaDashboard.stagingCatalog();

        CBAProducts cbaProducts = PageFactory.getCBAProducts();
        cbaProducts.deleteSatgingProduct();
    }
}
