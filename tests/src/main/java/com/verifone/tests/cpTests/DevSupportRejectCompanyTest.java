package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.Steps.*;

public class DevSupportRejectCompanyTest extends BaseTest {

    public static Company devPublicReject;

    @Test(testName = "Dev Admin accepted company", description = "CP - dev basic add company & admin accepted",
            groups = "CP-portal-createCompanyUI")
    public void createCompanyUI() throws Exception {
        Company dev = devSignUp();
        devLogin(dev);
        devLoginFillCompany(dev);
        devPublicReject = dev;
    }

    @Test(testName = "Dev Admin accepted company", description = "CP - dev basic add company & admin accepted",
            dependsOnMethods = {"createCompanyUI"})
    public void rejectCompanyUI() throws Exception {
        checkRejectCompany(devPublicReject);
        logout();
    }
}
