package com.verifone.tests.cpTests;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.Steps.checkCompaniesList;
import static com.verifone.tests.steps.Steps.devSupportAdminLogin;

public class GioraTest extends BaseTest {

    @Test(testName = "sdf", description = "sdfsdf")
    public void checkIsNewCompanyExistUI() throws Exception {
        devSupportAdminLogin();
    }

}
