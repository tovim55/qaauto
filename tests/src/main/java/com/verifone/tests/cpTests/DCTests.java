package com.verifone.tests.cpTests;

import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;


import static com.verifone.tests.steps.Steps.createDcOrg;
import static com.verifone.tests.steps.Steps.createDcUser;

public class DCTests extends BaseTest {



    @Test(testName = "Create SsoApi org", description = "Create SsoApi org via api", groups = {"CP-portal"})
    public void createDcTest() throws IOException {
        createDcOrg();

    }


    @Test(testName = "Create SsoApi user", description = "Create SsoApi user  under dc org via api", groups = {"CP-portal"})
    public void createDcUserTest() throws IOException {
        createDcUser();

    }


}
