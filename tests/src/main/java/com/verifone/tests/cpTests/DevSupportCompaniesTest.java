package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.Steps.*;
import static com.verifone.tests.steps.Steps.restartSession;

public class DevSupportCompaniesTest extends BaseTest {

//    @Test(testName = "Dev Admin check list companies", description = "CP - dev basic add company & admin check is exist" +
//            "in the list", groups = {"CP-portal"})
//    public void checkCompaniesListUI() throws Exception {
//        Company dev = devSignUp();
//        devLogin(dev);
//        devLoginFillCompany(dev);
//        restartSession();
//        checkCompaniesList(dev);
//    }

    @Test(testName = "Dev Admin accepted company", description = "CP - dev basic add company & admin accepted",
            groups = {"CP-portal"})
    public void acceptedCompanyUI() throws Exception {
        Company dev = devSignUp();
//        devLogin(dev);
//        devLoginFillCompany(dev);
//        restartSession();
//        devSupportAdminLogin();
        checkAcceptCompany(dev);

    }
}
