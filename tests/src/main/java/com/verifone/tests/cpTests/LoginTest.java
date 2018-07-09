package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import org.testng.annotations.*;



public class LoginTest extends BaseTest {



    @Test(testName = "LoginTest", description = "CP dev Portal log in test", groups = {"CP-portal"})
    public void cpLoginTestUI() throws Exception {
        User dev = EntitiesFactory.getEntity("DevAdmin");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.clickOmLoginBtn();
        loginPage.loginPageCp(dev);

    }

}