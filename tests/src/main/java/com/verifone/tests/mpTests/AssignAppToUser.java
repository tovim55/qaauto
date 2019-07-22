package com.verifone.tests.mpTests;

import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class AssignAppToUser extends BaseTest {

    private static String getAppName;
    @Test(priority = 0, testName = "Login & Assign App to user", description = "Login in to CBA MarketPlace and assign application to the user")
    public void CBAAssignToUserAppUI() throws InterruptedException {

        /* Login to CBAMarket Place */
        loginCBA(createAssignUser());
        getAppName = BaseTest.envConfig.getAppName();
        /* Move to Assign Apps to User */
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();
        assignApp.btnSelectAssignAppsToUser();
        assignApp.assignAppToUser(getAppName);
        assignApp.userAssignment();
//        assignApp.isAssignUpdated();
    }

}
