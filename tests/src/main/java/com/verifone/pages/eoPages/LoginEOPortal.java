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
* This class described all elements and actions can be executed on LogIn page.
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
    private By loginPassword = By.xpath("//*[@class='control-label' and @for='ipassword']");
    private By loginForgotLink = By.xpath("//*[@id=\"loginForm\"]/div[2]/a");
    private By loginBtnLabel = By.id("btnPrimaryLogin");
    private By lEmail = By.id("username");
    private By lPassword = By.id("ipassword");
    private By lPassword1 = By.xpath("//*[@class='form-group label-floating required is-empty']");
    private By lerrorMandatoryField = By.xpath("//*[@class='help-block']");
    private By loginSetupBtn = By.id("btnPrimaryLogin");
    private By lerrorMatch = By.xpath("//*[@class='alert alert-danger']");
    private By loginSetupLnk = By.xpath("//*[@id='loginForm']/div/div[3]/a");
    private By panel = By.xpath("//*[@class='panel-body']");


    
    
    public LoginEOPortal() {
        super(url, title);
    }

    //--------------------------------------------------------------------------
    /**
     * Method: Input Email value. Blur Email field.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginInputEmail(String ulEmail)  throws Exception {
        sendKeys(lEmail, ulEmail);
        click(panel);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Input Password value.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginInputPassword(String ulPassword)  throws Exception {
//        sendKeys(lPassword1, ulPassword);
        WebElement iFrame = BasePage.driver.findElement(By.id("veriPassFrame"));
        BasePage.driver.switchTo().frame(iFrame);
        BasePage.driver.findElement(lPassword).sendKeys(ulPassword);
        driver.switchTo().defaultContent();
        click(lEmail);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Login button.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickLoginBtn()  throws Exception {
        click(loginBtnLoc);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Get Page title.
     * Return Page title as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginTitle() {
        return getText(loginTitle);
    }

    //--------------------------------------------------------------------------
    /**
     * Method: Get Email field hint.
     * Return Email field hint as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginEmail() {
        return getText(loginEmail);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Get Password field hint.
     * Return Password field hint as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginPassword() {
        WebElement iFrame = BasePage.driver.findElement(By.id("veriPassFrame"));
        BasePage.driver.switchTo().frame(iFrame);
        String a = getText(loginPassword);
        driver.switchTo().defaultContent();
        return a;
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
     * Method: Get Login button label.
     * Return Login button label as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String loginBtnLabel() {
        return getText(loginBtnLabel);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Click on Forgot link.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void loginForgotLinkClick() throws Exception {
        click(loginForgotLink);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Get Mandatory field error text.
     * Return Mandatory field error text as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String lerrorMandatoryField() {
        return getText(lerrorMandatoryField);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Get Match data error text.
     * Return Match data error text as String
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String lerrorMatch() {
        return getText(lerrorMatch);
    }

}

