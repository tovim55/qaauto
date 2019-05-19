package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.vhqPages.VHQDeviceSearch;
import com.verifone.pages.vhqPages.VHQDownloadLibrary;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.createVHQMumbaiUser;
import static com.verifone.tests.steps.mpPortal.Steps.createVHQMumbaiUser;
import static com.verifone.tests.steps.mpPortal.Steps.loginVHQ;

public class VerifyJobInVHQ extends BaseTest
{
    private static String appName = "Tab - Free - MultiUser - Hello World";

    @Test(testName = "LogIn & verify job in VHQ ", description = "log in to VHQ and verify job is created ")

    public void VHQVerifyPackageTestUI() throws InterruptedException {

        loginVHQ(createVHQMumbaiUser());

        VHQHomePage vhq = PageFactory.getVHQHomePage();

        vhq.deviceSearch();

        VHQDeviceSearch deviceSearch = PageFactory.getVHQDeviceSearch();
        deviceSearch.deviceProfile();

    }


}
