package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.Steps.*;

public class DevSupportAcceptCompanyTest extends BaseTest {

    public static Company devPublic;

    @Test(testName = "Dev Admin accepted company", description = "CP - dev basic add company & admin accepted",
            groups = {"CP-portal-createCompany2UI"})
    public void createCompanyUI() throws Exception {
        Company dev = devSignUp();
        devLogin(dev);
        devLoginFillCompany(dev);
        devPublic = dev;
    }

    @Test(testName = "Dev Admin accepted company", description = "CP - dev basic add company & admin accepted",
            dependsOnGroups = {"CP-portal-createCompany2UI"})
    public void acceptedCompanyUI() throws Exception {
        checkAcceptCompany(devPublic);
    }
}
