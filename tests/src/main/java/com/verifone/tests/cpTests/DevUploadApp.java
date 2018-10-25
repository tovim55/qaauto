package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.verifone.tests.steps.Steps.*;


public class DevUploadApp extends BaseTest {

    private static String appName;

    @Test(testName = "Dev admin upload an app", description = "Dev admin upload an app")
    public void devUploadAppUI() throws IOException, InterruptedException, AWTException {
        Company dev = new Company();
        appName = "QA-TEST" + RandomStringUtils.randomAlphanumeric(4).toLowerCase();
        dev.setUserName("doba@cmail.club");
        dev.setPassword("Welcome3#");
        devLogin(dev);
        createApp(appName);
    }

    @Test(testName = "Dev support admin reject app", description = "After dev upload an app, dev support reject it",
            dependsOnMethods = {"devUploadAppUI"})
    public void DevSupportRejectAppUI() throws Exception {
        devSupportAdminLogin();
        validateApp(appName);

    }
}
