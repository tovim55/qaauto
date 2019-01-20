package com.verifone.pages.cpPages;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on OKTA login page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------

public class OktaLogin extends BasePage {

    private final static String url = "";
    private final static String title = "Forgot your password?";

    private By SihnInBtnLoc = By.id("okta-signin-submit");
    private By loginNameLoc = By.id("okta-signin-username");
    private By loginOktaTitle = By.xpath("//*[@class='okta-form-title o-form-head']");
    private By loginPwdLoc = By.id("okta-signin-password");
    private By loginAnswerLoc = By.id("input67");
    private By loginForgotLink = By.xpath("//*[@id=\"loginForm\"]/div[3]/a");
    private By loginBtnLabel = By.id("btnPrimaryLogin");
    private By verifyBtnLoc = By.xpath("//*[@class='button button-primary']");
    private By btnOktaAccount = By.xpath("//*[@class='link-button link-button-icon option-selected center notranslate h-nav-href']");
    private By clickSignOut = By.xpath("//*[@class='option-title' and @data-se = 'logout-link']");
    private By clickSignOutOktaVerify = By.xpath("//*[@class='link goto']");

    private By lerrorMandatoryField = By.xpath("//*[@class='help-block']");
    private By loginSetupBtn = By.id("btnPrimaryLogin");
    private By lerrorMatch = By.xpath("//*[@class='alert alert-danger']");
    private By loginSetupLnk = By.xpath("//*[@id='loginForm']/div/div[3]/a");
    private By panel = By.xpath("//*[@class='panel-body']");




    public OktaLogin() {
        super(url, title);
    }

    //--------------------------------------------------------------------------
    /**
     * Method: Login: Input Email data.
     * Get Email data as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginInputName(String ulName)  throws Exception {
        sendKeys(loginNameLoc, ulName);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Input Password data.
     * Get Password data as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginInputPassword(String ulPassword)  throws Exception {
        sendKeys(loginPwdLoc, ulPassword);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Click on Sign In button.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void clickSignInBtn()  throws Exception {
        click(SihnInBtnLoc);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Input Security answer data.
     * Get Security answer data as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginInputAnswer(String ulAnswer)  throws Exception {
        sendKeys(loginAnswerLoc, ulAnswer);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Click on Verify button.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void clickVerifyBtn()  throws Exception {
        click(verifyBtnLoc);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Okta Login page Title.
     * Return Okta Login page Title as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public boolean loginOktaTitleExists () throws Exception {
        return isExists(loginOktaTitle, 6);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Okta Login page Title.
     * Return Okta Login page Title as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginOktaTitle() {
        return getText(loginOktaTitle);
    }

    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Forgot link text.
     * Return Forgot link text as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginForgotLink() {
        return getText(loginForgotLink);
    }

    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Login button label.
     * Return Login button label as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginBtnLabel() {
        return getText(loginBtnLabel);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Click on Forgot link.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginForgotLinkClick() throws Exception {
        click(loginForgotLink);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Mandatory field error text.
     * Return Mandatory field error text as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String lerrorMandatoryField() {
        return getText(lerrorMandatoryField);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Match data error text.
     * Return Match data error text as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String lerrorMatch() {
        return getText(lerrorMatch);
    }

    //--------------------------------------------------------------------------
    public void SignOut() throws Exception {
        click(btnOktaAccount);
        click(clickSignOut);
    }
    //--------------------------------------------------------------------------

    public void SignOutOktaVerify() throws Exception {
        click(clickSignOutOktaVerify);
    }
    //--------------------------------------------------------------------------

}

