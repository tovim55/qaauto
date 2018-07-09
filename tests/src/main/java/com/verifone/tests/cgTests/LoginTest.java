package com.verifone.tests.cgTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginTest extends BaseTest {


    @Test(testName = "Login to CG ", description = "", groups = {"CG-Portal"})
    public void loginUI() throws IOException, InterruptedException {
        User user = EntitiesFactory.getEntity("EOAdmin");
        CGLoginPage page = (CGLoginPage) PageFactory.getPage("CGLoginPage");
        page.doLogin();

    }
}
