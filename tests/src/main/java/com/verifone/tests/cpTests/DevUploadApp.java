package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.verifone.tests.steps.Steps.createApp;
import static com.verifone.tests.steps.Steps.devLogin;
import static com.verifone.tests.steps.Steps.devSupportAdminLogin;


public class DevUploadApp extends BaseTest {


    @Test(testName = "Dev admin upload an app", description = "Dev admin upload an app")
    public void devUploadAppUI() throws IOException, InterruptedException, AWTException {
        Company dev = new Company();
        dev.setUserName("doba@cmail.club");
        dev.setPassword("Welcome3#");
        devLogin(dev);
        createApp();
    }


//    @Test(testName = "Dev support admin reject app", description = "After Dev admin upload an app",
//            dependsOnMethods = {"devUploadAppUI"})
//    public void devSupportRejectAppUI() throws Exception {
//        devSupportAdminLogin();
//        System.out.println("sdfsdf");
//    }
}
