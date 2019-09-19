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

public class VerifyJobInVHQ extends BaseTest {
    private static String getAppName;
    private static String getCmFiveDeviceSerialNo01;
    private static String deviceIPAddress;

    @Test(testName = "LogIn & Verify Subscribed/Unsubscribe Job Status in VHQ", description = "Log in to VHQ and verify job is created.")
    public void VHQVerifySubscribedPackageTestUI() throws Exception {

        loginVHQ(createVHQMumbaiUser());

        deviceIPAddress = BaseTest.envConfig.getDeviceIPAddress();
        getCmFiveDeviceSerialNo01 = BaseTest.envConfig.getCmFiveDeviceSerialNo01();
        getAppName = BaseTest.envConfig.getAppName();

        VHQHomePage vhq = PageFactory.getVHQHomePage();
        vhq.getDeviceStatus(deviceIPAddress, getCmFiveDeviceSerialNo01);
        vhq.deviceSearch(getCmFiveDeviceSerialNo01);

        VHQDeviceSearch deviceSearch = PageFactory.getVHQDeviceSearch();
        deviceSearch.deviceProfile();
        deviceSearch.searchJobStatus(getAppName, "INSTALL");
        deviceSearch.searchJobStatus(getAppName, "UNINSTALL");
    }
}
