package com.verifone.pages.cpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on OKTA login page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class LoginSSOPage extends BasePage {

    private final static String url = "";
    private final static String title = "Verifone";

    private By formOKTALoc = By.id("okta-sign-in");
    private By userLoc = By.id("Username");
    private By passwordLoc = By.id("Passwd");
    private By btnSignInLoc = By.id("signIn");



    public LoginSSOPage() {
        super(url, title);
    }

    //--------------------------------------------------------------------------
    /**
     * Method: Check if OKTA form exists.
     * Return true / false
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public boolean formOKTAExists () throws Exception {
        return isExists(formOKTALoc, 20);
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
