package com.verifone.tests.fastLogin;

import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cpPages.OktaLogin;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.pages.eoPages.LoginEOPortal;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;


//public class Login_User_Portal {
    public class Login_User_CGPortal extends BaseTest {

    @BeforeTest
    public void startTest() throws Exception {

    }

    @Parameters({ "env", "mail", "pwd", "portal", "name", "answer"})
    @Test(enabled = true, priority = 1, testName = "CG Portal", groups = {"FastLogin"}, alwaysRun = true)

    public void Login_User_CGPortalUI_Cont(String param1, String param2, String param3, String param4, String param5, String param6) throws Exception {
        WebDriver driver = new HomePage().getDriver();
        String url = "";
        switch(param1) {
            case "Test":
            case "Dev":
                switch (param4) {
                    case "CGPortal":
                        url = "https://" + param1 + ".cgateway-portal.verifone.com";
                        break;
                }
                break;
            case "Prod":
                switch (param4) {
                    case "CGPortal":
                        url = "https:/cgateway-portal.verifone.com";
                        break;
                }
                break;
        }
        driver.navigate().to(url);
        CGLoginPage CGLoginPage = (CGLoginPage) PageFactory.getPage("CGLoginPage");
        CGLoginPage.clickLoginLink();
        CGLoginPage.inputUserName(param2);
        if (param2.contains("@verifone.com")){
            Thread.sleep(2000);
            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));

            OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
            OktaLogin.loginInputName(param5);
            OktaLogin.loginInputPassword(param3);
            OktaLogin.clickSignInBtn();

            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
            OktaLogin.loginInputAnswer(param6);
            testLog.info("Security answer: " + "");
            OktaLogin.clickVerifyBtn();

        }
        else {
            CGLoginPage.inputPassword(param3);
            CGLoginPage.clickLoginBtn();
        }

    }
}
