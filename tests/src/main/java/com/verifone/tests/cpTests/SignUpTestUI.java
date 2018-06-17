package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.infra.connectors.GmailApiClient;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.SignUpPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

public class SignUpTestUI extends BaseTest {


    @Test(groups = {"CP-Portal"})
    public void signUpTestUI() throws Exception {
        starTestLog("Sign Up ", "Sign up with new user successfully");

        User user = EntitiesFactory.getEntity("NewUser");
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(user);
        assertTextContains("Thanks for your registration!", signUpPage.getMessege());
        GmailApiClient.validateMessage("Thank you for completing your Verifone Developer Central registration");
    }

    @Test(groups = {"CP-Portal"})
    public void signUpWithExistUserUI() throws Exception {
        starTestLog("Sign up with exist user email", "Sign up with exist email is fail");
        String existEmail = EntitiesFactory.getEntity("DevAdmin").getUserName();
        User user = EntitiesFactory.getEntity("NewUser");
        user.setUserName(existEmail);
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(user);
        assertTextContains("Registration has failed.", signUpPage.getMessege());
    }


}
