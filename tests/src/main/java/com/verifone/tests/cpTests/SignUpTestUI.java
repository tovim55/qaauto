package com.verifone.tests.cpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.SignUpPage;
import com.verifone.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTestUI extends BaseTest {

    public SignUpTestUI() {
        propFilePath = "logIn.properties";
    }

    @Test(groups = {"UI"})
    public void cpSignUpTest() throws Exception {
        starTestLog("cpSignUpTest", "CP dev Portal sign up test");

        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(prop.getProperty("first_name"), prop.getProperty("last_name"),
                prop.getProperty("email"), prop.getProperty("password"));

        String messege = signUpPage.getMessege();
        try {

            Assert.assertEquals(messege, "Registration has failed.");
        }
        catch (AssertionError e){
//            testLog.log(LogStatus.FAIL, "text expected: " + "blabla " + "was: " + messege);
            org.testng.Assert.fail("Text expected: " + "Registration has failed." + " Was: " + messege);
        }
    }
}
