package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.vhqPages.VHQDeviceSearch;
import com.verifone.pages.vhqPages.VHQDownloadLibrary;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.verifone.tests.steps.mpPortal.Steps.createVHQMumbaiUser;
import static com.verifone.tests.steps.mpPortal.Steps.createVHQMumbaiUser;
import static com.verifone.tests.steps.mpPortal.Steps.loginVHQ;

public class VerifyJobInVHQ extends BaseTest {
    private static String getAppName;
    private static String getCmFiveDeviceSerialNo01;
    private static String deviceIPAddress;

    @Test(priority = 1, testName = "LogIn & Verify Subscribed Job Status in VHQ", description = "Log in to VHQ and verify job is created.")
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
        deviceSearch.validateJobInstall(getAppName, "INSTALL", CBAAssignPage.jobCreatedOnSubscription);
        //deviceSearch.validateJobInstall(getAppName, "INSTALL", "07/Oct/2019 01:15");

    }

    @Test(priority = 2, testName = "LogIn & Verify Unsubscribe Job Status in VHQ", description = "Log in to VHQ and verify job is created.")
    public void VHQVerifyUnsubscribePackageTestUI() throws Exception {

        loginVHQ(createVHQMumbaiUser());

        deviceIPAddress = BaseTest.envConfig.getDeviceIPAddress();
        getCmFiveDeviceSerialNo01 = BaseTest.envConfig.getCmFiveDeviceSerialNo01();
        getAppName = BaseTest.envConfig.getAppName();

        VHQHomePage vhq = PageFactory.getVHQHomePage();
        vhq.getDeviceStatus(deviceIPAddress, getCmFiveDeviceSerialNo01);
        vhq.deviceSearch(getCmFiveDeviceSerialNo01);

        VHQDeviceSearch deviceSearch = PageFactory.getVHQDeviceSearch();
        deviceSearch.deviceProfile();
        deviceSearch.validateJobInstall(getAppName, "UNINSTALL", CBAAccount.jobCreatedOnUnsubscription);
    }
}
