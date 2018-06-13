package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.SignUpPage;
import com.verifone.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTestUI extends BaseTest {



    @Test(groups = {"CP-Portal"})
    public void signUpTestUI() throws Exception {
        starTestLog("Sign Up ", "Sign up with new user successfully");

        User user = EntitiesFactory.getEntity("NewUser");
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(user);
        String message = signUpPage.getMessege();
        String expectedMessage = "Thanks for your registration!";
        if (!message.contains(expectedMessage)) {
            org.testng.Assert.fail("Text expected: " + expectedMessage + " Was: " + message);
        }

    }

    @Test(groups = {"CP-Portal"})
    public void signUpWithExistUserUI() throws Exception {
        starTestLog("Sign up with exist user email", "Sign up with exist email is fail");
        String existEmail = EntitiesFactory.getEntity("DevAdmin").getUserName();
        User user = EntitiesFactory.getEntity("NewUser");
        user.setUserName(existEmail);
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(user);
        String message = signUpPage.getMessege();
        String expectedMessage = "Registration has failed.";
        if (!message.contains(expectedMessage)) {
            org.testng.Assert.fail("Text expected: " + expectedMessage + " Was: " + message);
        }
    }



}
