package com.verifone.tests.cpTests;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.LoginPageNew;
import com.verifone.pages.cpPages.SignUpPage;
import com.verifone.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    public SignUpTest() {
        propFilePath = "logIn.properties";
    }

    @Test(groups = {"CP-portal"})
    public void cpSignUpTest() throws Exception {
        starTestLog("cpSignUpTest", "CP dev Portal sign up test");
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(prop.getProperty("first_name"), prop.getProperty("last_name"),
                prop.getProperty("email"), prop.getProperty("password"));

//        Assert.assertEquals(signUpPage.getMessege(), "Registration has blanlal");
        String messege = signUpPage.getMessege();
        try {

            Assert.assertEquals(messege, "Registration has failed.");
        }
        catch (AssertionError e){
            testLog.log(LogStatus.ERROR, "text expected: " + "Registration has failed." + "was: " + messege);
            throw new Exception();
        }
    }
}
