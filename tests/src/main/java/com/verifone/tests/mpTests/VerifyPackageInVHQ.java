package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.vhqPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class VerifyPackageInVHQ extends BaseTest {

    private String appName = "cp_Connect";

    @Test(testName = "LogIn & verify app package ", description = "log in to VHQ and verify app package added to Download Library")

    public void VHQVerifyPackageTestUI() throws InterruptedException {

        loginVHQ(createVHQMumbaiUser());

        VHQHomePage vhq = PageFactory.getVHQHomePage();

        vhq.verifyDownloadLibrary();

        VHQDownloadLibrary downLab = PageFactory.getVHQDownloadLibrary();
        downLab.verifyPackageExist(appName);
    }
}
