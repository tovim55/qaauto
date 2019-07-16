package com.verifone.tests.mpTests;

import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class AssignAppToUser extends BaseTest  {

    @Test(priority = 0,testName = "Login & Assign App to user",description = "Login in to CBA MarketPlace and assign application to the user")
    public void CBAAssignToUserAppUI()throws InterruptedException{

        /* Login to CBAMarket Place */
        loginCBA(createAssignUser());

        /* Move to Assign Apps to User */
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();
        assignApp.btnSelectAssignAppsToUser();
        assignApp.assignAppToUser();
        assignApp.userAssignment();
       // assignApp.isAssignUpdated();
    }

}
