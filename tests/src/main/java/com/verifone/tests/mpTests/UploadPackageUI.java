package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAMainMenu;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps.*;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.loginCBA;
import static com.verifone.tests.steps.mpPortal.Steps.loginMPPortal;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;

public class UploadPackageUI extends BaseTest {

    @Test(priority=1, testName = "LogIn & upload App", description = "log in to MarketPlace and upload package")
    public void CBAUploadAppTestUI() throws Exception {

        User EOAdminSupport = EntitiesFactory.getEntity("EOAdminSupport");

        String EOAdminSupportMail = EOAdminSupport.getUserName();
        String EOAdminSupportPwd = EOAdminSupport.getPassword();
        String EOAdminSupportAnsw = EOAdminSupport.getSecurityAnswer();

        navigateCBAHome();
        loginMPPortal(EOAdminSupportMail, EOAdminSupportPwd, EOAdminSupportAnsw);
        CBAMainMenu mainMenu = PageFactory.getCBAMainMenu();
        mainMenu.createStagingProduct();
        mainMenu.listingInfoProduct();
        mainMenu.profileProduct();
        mainMenu.editIntegration();

    }
}
