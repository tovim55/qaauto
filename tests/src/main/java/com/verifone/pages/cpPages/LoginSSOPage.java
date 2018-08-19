package com.verifone.pages.cpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

public class LoginSSOPage extends BasePage {

    private final static String url = "";
    private final static String title = "Verifone";

    private By formSSOLoc = By.xpath("//*[@class='sso-absolute-center loginAndCheck sso-form']");
    private By userLoc = By.id("Username");
    private By passwordLoc = By.id("Passwd");
    private By btnSignInLoc = By.id("signIn");



    public LoginSSOPage() {
        super(url, title);
    }

    //--------------------------------------------------------------------------
    /**
     * Method: Check if sso form exists.
     * Return true / false
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public boolean formSSOExists () throws Exception {
        return isExists(formSSOLoc, 20);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Input UserName data.
     * Get UserName data as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void inputUserName(String ulUserName)  throws Exception {
        click(userLoc);
        sendKeys(userLoc, ulUserName);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Input Password data.
     * Get Password data as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void inputPassword(String ulPassword)  throws Exception {
        click(passwordLoc);
        sendKeys(passwordLoc, ulPassword);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Click on SignIn button.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void clickSignInBtn()  throws Exception {
        click(btnSignInLoc);
    }
}
