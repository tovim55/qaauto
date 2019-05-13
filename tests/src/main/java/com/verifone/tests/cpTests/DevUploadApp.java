package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.verifone.tests.steps.Steps.*;


public class DevUploadApp extends BaseTest {


    @Test(testName = "Dev admin upload an Engage app", description = "Engage app for CP")
    public void devUploadAppAndApprovalUI() throws Exception {
        Company dev = new Company();
        dev.setUserName("doba@cmail.club");
        dev.setPassword("Welcome3#");
        devLogin(dev);
        String appName = createEngageApp();
//        appsApproval("ewewe", 1);
//        appsApproval(appName, 2);
    }


//    @Test(testName = "Dev admin upload an Android app", description = "Android app for CP")
//    public void devUploadAppAndroidUI() throws IOException, InterruptedException, AWTException {
//        User dev = EntitiesFactory.getEntity("DevAdmin");
//        devLogin(dev);
//        createAndroidApp();
//    }

//    @Test(testName = "Dev support admin reject app", description = "After Dev admin upload an app",
//            dependsOnMethods = {"devUploadAppAndApprovalUI"})
//    public void devSupportRejectAppUI() throws Exception {
//        devSupportAdminLogin();
//        System.out.println("sdfsdf");
//    }
}
