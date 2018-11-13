package com.verifone.tests.fastLogin;
import com.verifone.pages.cpPages.OktaLogin;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.eoPages.LoginEOPortal;
import com.verifone.tests.BaseTest;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;


//public class Login_User_Portal {
    public class Login_User_Portal extends BaseTest {

    @BeforeTest
    public void startTest() throws Exception {

    }

    @Parameters({ "env", "mail", "pwd", "portal", "name", "answer"})
    @Test(enabled = true, priority = 1, testName = "EOAdmin EOPortal", groups = {"FastLogin"}, alwaysRun = true)

    public void Login_User_PortalUI_Cont(String param1, String param2, String param3, String param4, String param5, String param6) throws Exception {
        String url = "";
        switch(param1) {
            case "QA":
            case "Dev":
                switch (param4) {
                    case "EOPortal":
                        url = "https://" + param1 + ".estatemanager.verifonecp.com/";
                        break;
                    case "EOSupportPortal":
                        url = "https://" + param1 + ".estatemanager.verifonecp.com/estatesupport";
                        break;
                    case "CPPortal":
                        url = "https://" + param1 + ".developer.verifonecp.com/home";
                        break;
                }
                break;
            case "Prod":
                switch (param4) {
                    case "EOPortal":
                        url = "https://estatemanager.verifone.com/";
                        break;
                    case "EOSupportPortal":
                        url = "https://estatemanager.verifone.com/estatesupport";
                        break;
                    case "DevPortal":
                        url = "https://developer.verifone.com/home";
                        break;
                    case "AppDirectPortal":
                        url = "https://testverifone.appdirect.com/login";
                        break;
                }
                break;
        }
        BasePage.driver.navigate().to(url);
        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
        LoginEOPortal.loginInputEmail(param2);
        if (param2.contains("@verifone.com")){
            Thread.sleep(2000);
            ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
            BasePage.driver.switchTo().window(availableWindows.get(0));

            OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
            OktaLogin.loginInputName(param5);
            OktaLogin.loginInputPassword(param3);
            OktaLogin.clickSignInBtn();

            availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
            BasePage.driver.switchTo().window(availableWindows.get(0));
            OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
            OktaLogin.loginInputAnswer(param6);
            testLog.info("Security answer: " + "");
            OktaLogin.clickVerifyBtn();

        }
        else {
            LoginEOPortal.loginInputPassword(param3);
            LoginEOPortal.clickLoginBtn();
        }

    }
}
