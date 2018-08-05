package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.Steps.*;

public class CompanyReview extends BaseTest {


    @Test(testName = "Developer Basic Add Company", description = "After sign up Dev login,  fill personal & company info, Submit application", groups = {"CP-Portal"})
    public void connectWithCompanyUI() throws Exception {
        Company dev = devSignUp();
        devLogin(dev);
        devLoginFillCompany(dev);
    }


}

