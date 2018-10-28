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
* This class described all elements and actions can be executed on Setup Password page. 
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------

public class SetupPasswordPage extends BasePage {

    private final static String url = "";
    private final static String title = "Setup your Password";
    private By password = By.id("confirmPassword");
    private By confpassword = By.id("confirmPassword2");
    private By chboxTOS = By.xpath("(//*[@class='check'])[1]");
    private By chboxAgreement = By.xpath("(//*[@class='check'])[2]");
    private By SubmitBtn = By.id("btnSubmit");
    private By titleLoc = By.xpath("//*[@class='text-center sso-form-title nomargin']");
    private By textLoc = By.xpath("//*[@class='text-center']");
    //private By textLoc = By.xpath("//*[@id='local_auth_div']/div[2]/div/div/div[2]/div/div[2]");
    private By setupPasswordHintLoc = By.xpath("//*[@class='control-label' and @for='confirmPassword']");
    private By setupConfirmPasswordHintLoc = By.xpath("//*[@class='control-label' and @for='confirmPassword2']");
    private By setupCheckBoxLoc = By.xpath("(//*[@class='checkbox'])[1]");
    private By setupCheckBoxAgrLoc = By.xpath("(//*[@class='checkbox'])[2]");
    private By setupTOSLinkLoc = By.id("tandc_container");
    private By setupAgreementLinkLoc = By.xpath("//*[@class='external-link']");
    
    private By errorFormatLoc = By.xpath("//*[@class='help-block']");
    private By errorMatchLoc = By.xpath("//*[@id='resetPasswordForm']/div[2]/div[2]");
    private By errorTOSLoc = By.xpath("(//*[@class='help-block years-old-marging'])[1]"); 
    private By errorAgrLoc = By.xpath("(//*[@class='help-block years-old-marging'])[2]");
    
    private By wndTOSLoc = By.xpath("//*[@class='full reset modal-open']");
//    private By tosLoc = By.xpath("//*[@class='full reset modal-open']");
    private By tosLoc = By.xpath("//*[@class='modal-content']");
    private By TOSLnk = By.xpath("//*[@class='pull-left']");
    private By AgreementLnk = By.xpath("//*[@class='external-link']");
    private By declineTOSBtn = By.xpath("(//*[@class='btn btn-primary btn-cancel'])[1]");
    private By acceptTOSBtn = By.xpath("(//*[@class='btn btn-primary btn-raised btn-accept'])[1]");
    private By declineAgreementBtn = By.xpath("(//*[@class='btn btn-primary btn-cancel'])[2]");
    private By acceptAgreementBtn = By.xpath("(//*[@class='btn btn-primary btn-raised btn-accept'])[2]");
    private By tosXBtn = By.xpath("//*[@class='close']");
    
    
    
    private By loginBtn = By.id("btnPrimaryLogin");
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
    


    public SetupPasswordPage() {
        super(url, title);
//        validateTitle();
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Fill Password and Confirm Password data.
    * Get Password and Confirm Password data as Strings
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public void SetupPasswordPageCp(String uPassword, String uConfPassword) throws InterruptedException {
        sendKeys(password, uPassword);
        click(confpassword);
        sendKeys(confpassword, uConfPassword);

    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on TOS check element.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickOnchboxTOS() {
    	click(chboxTOS);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Click on Agreement check element.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickOnchboxAgreement() {
    	click(chboxAgreement);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Submit button.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickOnSubmitBtn() {
        click(SubmitBtn);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: TOS frame: Click on Accept button.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickOnAcceptTOSBtn() throws Exception {
//    	actionClick(acceptTOSBtn, 10);
        WebElement webEl = driver.findElement(acceptTOSBtn);
        //scrollToElement(locator);
        Actions builder = new Actions(driver);
        builder.moveToElement(webEl).click().perform();
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Agreement frame: Click on Accept button.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickOnAcceptAgrBtn() throws Exception {
//    	actionClick(acceptTOSBtn, 10);
        WebElement webEl = driver.findElement(acceptAgreementBtn);
        //scrollToElement(locator);
        Actions builder = new Actions(driver);
        builder.moveToElement(webEl).click().perform();
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Page text.
    * Return Page text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String pageText() {
        
    	String a = getText(textLoc);
    	System.out.println(a);
    	return getText(textLoc);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Page title.
    * Return Page title as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String pageTitle() {
        
    	String a = getText(titleLoc);
    	System.out.println(a);
    	return getText(titleLoc);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Password field hint.
    * Return Password field hint as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String setupPasswordHint() {
        
    	String a = getText(setupPasswordHintLoc);
    	System.out.println(a);
    	return getText(setupPasswordHintLoc);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Confirm Password field hint.
    * Return Confirm Password field hint as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String setupConfirmPasswordHint() {
        
    	String a = getText(setupConfirmPasswordHintLoc);
    	System.out.println(a);
    	return getText(setupConfirmPasswordHintLoc);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get TOS check box text.
    * Return TOS check box text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String setupCheckBoxText() {
        
    	String a = getText(setupCheckBoxLoc);
    	System.out.println(a);
    	return getText(setupCheckBoxLoc);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Agreement check box text.
    * Return Agreement check box text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String setupCheckBoxAgrText() {
        
    	String a = getText(setupCheckBoxAgrLoc);
    	System.out.println(a);
    	return getText(setupCheckBoxAgrLoc);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Click TOS link.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void setupTOSLinkClick() throws Exception {

        click(setupTOSLinkLoc);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Click Agreement link.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void setupAgreementLinkClick() throws Exception {

        click(setupAgreementLinkLoc);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Submit button label.
    * Return Submit button label as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String setupSubmitBtnLabel() {
        
    	String a = getText(SubmitBtn);
    	System.out.println(a);
    	return getText(SubmitBtn);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get TOS text.
    * Return TOS text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String tosText() {
        
    	String a = getText(tosLoc);
    	System.out.println(a);
    	return getText(tosLoc);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get TOS Agree button label.
    * Return TOS Agree button label as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String tosAgreeBtnLabel() {
        
    	String a = getText(acceptTOSBtn);
    	System.out.println(a);
    	return getText(acceptTOSBtn);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get TOS Decline button label.
    * Return TOS Decline button label as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String tosDeclineBtnLabel() {
        
    	String a = getText(declineTOSBtn);
    	System.out.println(a);
    	return getText(declineTOSBtn);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get TOS link text.
    * Return TOS link text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String tosLnkText() {
        
    	String a = getText(TOSLnk);
    	System.out.println(a);
    	return getText(TOSLnk);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Click on TOS x button.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void tosClose() {

        click(tosXBtn);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Agreement Agree button label.
    * Return Agreement Agree button label as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String agreementAgreeBtnLabel() {
        
    	String a = getText(acceptAgreementBtn);
    	System.out.println(a);
    	return getText(acceptAgreementBtn);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Agreement Decline button label.
    * Return Agreement Decline button label as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String agreementDeclineBtnLabel() {
        
    	String a = getText(declineAgreementBtn);
    	System.out.println(a);
    	return getText(declineAgreementBtn);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Agreement link text.
    * Return Agreement link text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String agreementLnkText() {
        
    	String a = getText(AgreementLnk);
    	System.out.println(a);
    	return getText(AgreementLnk);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Email Format validation error text.
    * Return Email Format validation error text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String errorFormat() {
    	return getText(errorFormatLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Email\Password Match error text.
    * Return Email\Password Match error text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String errorMatch() {
    	return getText(errorMatchLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Missing TOS accept error text.
    * Return Missing TOS accept error text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String errorTOS() {
    	return getText(errorTOSLoc);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Missing Agreement accept error text.
    * Return Missing Agreement accept error text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String errorAgr() {
    	return getText(errorAgrLoc);
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
    * Method: Click on Login button.
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public void loginCpBtn_from_Setup()  throws Exception {
        click(loginBtn);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Login: Click on Login button.
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public void loginCpBtn_from_LoginSetup()  throws Exception {
        click(loginSetupBtn);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Login: Click on Setup link.
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public void loginCpLnk_from_LoginSetup()  throws Exception {
        click(loginSetupLnk);
    }
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
}

