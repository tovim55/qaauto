package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class verifyMyAppsTest extends BaseTest
{
    private static String appName = "TestDevport4839";

    @Test(testName = "LogIn & verify MyApps", description = "log in to CBA MyApps and verify myApps list")
    public void CBAMyAppsTestUI()
    {

        User merchant = EntitiesFactory.getEntity("MPMerchantAdmin");
        loginCBA(merchant);
        verifyMyAppsCBA(appName);
    }

}
