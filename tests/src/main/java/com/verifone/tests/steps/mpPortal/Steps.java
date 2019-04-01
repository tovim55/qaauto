package com.verifone.tests.steps.mpPortal;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cgPages.CGApplicationPage;
import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.cpPages.OktaLogin;
import com.verifone.pages.mpPages.LoginMPPortal;
import com.verifone.pages.mpPages.MPHomePage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;

public class Steps {

    public static void loginMPPortal(String Email, String Password, String SecAnswer, String Env) throws Exception {
        WebDriver driver = new MPHomePage().getDriver();
        String update = " updated";

//        String Env = "https://testverifone.appdirect.com/login";
        Boolean TestPassFlag = true;

        testLog.info("-------------------------------------------------Navigate to MP Portal-------------------------------------------------");

        driver.navigate().to(Env);
        LoginMPPortal LoginMPPortal = (LoginMPPortal) PageFactory.getPage("LoginMPPortal");

        String Name;
        Name = Email.substring(0,Email.indexOf("@"));
        String Domain = Email.substring(Email.indexOf("@")+1);

        if (Domain.contains("verifone")) {
                testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
                Thread.sleep(2000);
                LoginMPPortal.loginInputEmail(Email);

                ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(availableWindows.get(0));
                OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
                Thread.sleep(2000);

                if (OktaLogin.loginOktaTitleExists()) {
                    if (OktaLogin.loginOktaTitle().contains("Sign In")) {
                        OktaLogin.loginInputName(Name);
                        OktaLogin.loginInputPassword(Password);
                        OktaLogin.clickSignInBtn();
                    }
                    availableWindows = new ArrayList<String>(driver.getWindowHandles());
                    driver.switchTo().window(availableWindows.get(0));
                    OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
                    OktaLogin.loginInputAnswer(SecAnswer);
                    testLog.info("Security answer: " + "");
                    OktaLogin.clickVerifyBtn();

                    Thread.sleep(2000);
                }
        }
        else{
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);
            LoginMPPortal.loginInputPassword(Password);
            LoginMPPortal.clickLoginBtn();
        }
    }

}