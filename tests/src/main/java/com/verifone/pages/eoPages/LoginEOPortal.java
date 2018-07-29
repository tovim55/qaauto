package com.verifone.pages.eoPages;
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
* This class described all elements and actions can be executed on Thank You page displayed after Reset Password. 
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------

public class LoginEOPortal extends BasePage {

    private final static String url = "";
    private final static String title = "Forgot your password?";
    
    private By emailLoc = By.id("username"); 
    private By passwordLoc = By.id("password");
    private By loginBtnLoc = By.xpath("//*[@class='btn btn-raised btn-primary btn-block']");
    private By loginEmail = By.xpath("//*[@class='control-label' and @for='username']");
    private By loginTitle = By.xpath("//*[@class='account-name verifone']");
    private By loginPassword = By.xpath("//*[@class='control-label' and @for='password']");
    private By loginForgotLink = By.xpath("//*[@id=\"loginForm\"]/div[3]/a");
    private By loginBtnLabel = By.id("btnPrimaryLogin");
    private By lEmail = By.id("username");
    private By lPassword = By.id("password");
    private By lerrorMandatoryField = By.xpath("//*[@class='help-block']");
    private By loginSetupBtn = By.id("btnPrimaryLogin");
    private By lerrorMatch = By.xpath("//*[@class='alert alert-danger']");
    private By loginSetupLnk = By.xpath("//*[@id='loginForm']/div/div[3]/a");

    
    
    public LoginEOPortal() {
        super(url, title);
    }
//    //--------------------------------------------------------------------------
//    /**
//    * Method: Get Page title.
//    * Return Page title as String
//    * @authors Yana Fridman
//    */
//    //--------------------------------------------------------------------------
//    public String pageTitle() {
//        
//    	String a = getText(titleLoc);
//    	System.out.println(a);
//    	return getText(titleLoc);
//    }
//    //--------------------------------------------------------------------------
//    /**
//    * Method: Get Page text.
//    * Return Page text as String
//    * @authors Yana Fridman
//    */
//    //-------------------------------------------------------------------------- 
//    public String pageText() {
//        
//    	String a = getText(textLoc);
//    	System.out.println(a);
//    	return getText(textLoc);
//    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Input Email data.
     * Get Email data as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginInputEmail(String ulEmail)  throws Exception {
        sendKeys(lEmail, ulEmail);
        click(lPassword);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Input Password data.
     * Get Password data as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginInputPassword(String ulPassword)  throws Exception {
        sendKeys(lPassword, ulPassword);
        click(lEmail);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Login link.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickLoginBtn()  throws Exception {
        click(loginBtnLoc);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Page title.
     * Return Page title as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginTitle() {
        return getText(loginTitle);
    }

    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Email field hint.
     * Return Email field hint as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginEmail() {
        return getText(loginEmail);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Login: Get Password field hint.
     * Return Password field hint as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginPassword() {
        return getText(loginPassword);
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

}

