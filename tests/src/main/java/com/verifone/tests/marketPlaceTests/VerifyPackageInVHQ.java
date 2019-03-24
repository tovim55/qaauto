package com.verifone.tests.marketPlaceTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.marketPlacePages.*;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.Steps;
import org.testng.annotations.Test;

public class VerifyPackageInVHQ extends BaseTest {

    @Test(testName = "LogIn & verify app package ", description = "log in to VHQ and verify app package added to Download Library")

    public void VHQVerifyPackageTestUI() throws InterruptedException {

        Steps.loginVHQ();

        VHQHomePage vhq = (VHQHomePage) PageFactory.getPage("VHQHomePage");
        if (vhq != null) {
            vhq.verifyCustomer();
        }

    }
}
