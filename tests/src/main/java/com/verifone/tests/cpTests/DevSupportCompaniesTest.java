package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.Steps.*;

public class DevSupportCompaniesTest extends BaseTest {

    public static Company devPublic;

    @Test(testName = "Dev Admin check list companies", description = "CP - dev basic add company",
            groups = "CP-portal-createNewCompanyUI")
    public void createNewCompanyUI() throws Exception {
        Company dev = devSignUp();
        devLogin(dev);
        devLoginFillCompany(dev);
        devPublic = dev;
    }

    @Test(testName = "Dev Admin check list companies", description = "CP - dev basic add company & admin check is exist" +
            "in the list", dependsOnMethods = {"createNewCompanyUI"})
    public void checkIsNewCompanyExistUI() throws Exception {
        checkCompaniesList(devPublic);
    }
}
