package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.Steps.*;

public class DevSupportAcceptCompanyTest extends BaseTest {

    public static Company devPublicAccept;

    @Test(testName = "Dev Admin accepted company", description = "CP - dev basic add company & admin accepted")
    public void createCompanyUI() throws Exception {
        Company dev = devSignUp();
        devLogin(dev);
        devLoginFillCompany(dev);
        devPublicAccept = dev;
    }

    @Test(testName = "Dev Admin accepted company", description = "CP - dev basic add company & admin accepted",
            dependsOnMethods = {"createCompanyUI"})
    public void acceptedCompanyUI() throws Exception {
        checkAcceptCompany(devPublicAccept);
        logout();
    }
}
