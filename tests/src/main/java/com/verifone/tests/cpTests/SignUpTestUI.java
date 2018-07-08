package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.SignUpPage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Mail.InboxGetnada;
import org.testng.annotations.Test;

import static com.verifone.utils.Assertions.assertTextContains;

public class SignUpTestUI extends BaseTest {


    @Test(testName = "Sign Up ", description = "Sign up with new user successfully", groups = {"CP-Portal"})
    public void signUpTestUI() throws Exception {
        User user = EntitiesFactory.getEntity("NewUser");
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(user);
        assertTextContains("Thanks for your registration!", signUpPage.getMessege());
        String message = new InboxGetnada().getLastMessage(user.getUserName());
        assertTextContains(message, "Activate Account");

    }




    @Test(testName = "Sign up with exist user email ", description = "Sign up with exist email is fail", groups = {"CP-Portal"})
    public void signUpWithExistUserUI() throws Exception {
        String existEmail = EntitiesFactory.getEntity("DevAdmin").getUserName();
        User user = EntitiesFactory.getEntity("NewUser");
        user.setUserName(existEmail);
        SignUpPage signUpPage = (SignUpPage) PageFactory.getPage("SignUpPage");
        signUpPage.signUp(user);
        assertTextContains("Registration has failed.", signUpPage.getMessege());
    }


}
