package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class UnsubscribeAppTest extends BaseTest {

    private static String getAppName;
    @Test(testName = "LogIn & unsubscribe an app", description = "log in to CBA account and remove app from apps list")

    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        //loginCBA(createMerchantUser());
        loginCBA(createAssignUser());
        getAppName = BaseTest.envConfig.getAppName();
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(getAppName);

    }
}
