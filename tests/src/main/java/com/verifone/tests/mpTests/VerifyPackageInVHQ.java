package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.vhqPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class VerifyPackageInVHQ extends BaseTest {

    private String appName = "TestDevport4839";

    @Test(testName = "LogIn & verify app package ", description = "log in to VHQ and verify app package added to Download Library")

    public void VHQVerifyPackageTestUI() throws InterruptedException {

        loginVHQ(createVHQUser());

        VHQHomePage vhq = (VHQHomePage) PageFactory.getPage("VHQHomePage");
        if (vhq != null) {
            vhq.verifyCustomer();
        }

        vhq.verifyDownloadLibrary();

        VHQDownloadLibrary downLab = (VHQDownloadLibrary) PageFactory.getPage("VHQDownloadLibrary");
        downLab.verifyPackageExist(appName);
    }
}
