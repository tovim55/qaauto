package com.verifone.tests.cgTests;

import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.verifone.tests.steps.cgPortal.Steps.loginAndCheck;

public class LoginTest extends BaseTest {


    @Test(testName = "Login to CG ", description = "Login to CG portal successfully", groups = {"CG-Portal"})
    public void login() throws IOException, InterruptedException {
        loginAndCheck();
    }

}
