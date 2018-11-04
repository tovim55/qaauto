package com.verifone.pages.cpPages;
import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
* This class described all elements and actions can be executed on Change Password page.
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------

public class ChangePasswordPage extends BasePage {

    private final static String url = "";
    private final static String title = "Change Password?";

    private By titleLoc = By.xpath("//*[@class='text-center sso-form-title']");
    private By currentPasswordLabelLoc = By.xpath("//*[@class='control-label' and @for='current_password']");
    private By newPasswordLabelLoc = By.xpath("//*[@class='control-label' and @for='new_password']");

    private By currentPasswordInput = By.id("current_password");
    private By newPasswordInput = By.id("new_password");
    private By btnSubmitLoc = By.id("btnSubmit");
    private By btnCancelLoc = By.id("linkCancel");

//    private By errorEmptyLoc = By.xpath("//*[@class='help-block']");
    private By errorEmptyPasswordLoc = By.xpath("//*[@id='resetPasswordForm']/div[1]/div[2]");
    private By errorEmptyNewPasswordLoc = By.xpath("//*[@id='resetPasswordForm']/div[2]/div[2]");
    private By errorMatchLoc = By.xpath("//*[@class='text-center text-danger global-error']");

    private By notifyTextLoc = By.xpath("//*[@class='notification-container success']");
    private By btnOkLoc = By.xpath("//*[@class='btn btn-primary btn-raised btn-accept']");


    public ChangePasswordPage() {
        super(url, title);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Page title.
    * Return Page title as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String getTitle() {
    	return getText(titleLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Proceed button label.
    * Return Proceed button label as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
        public String btnSubmitText() {
    	return getText(btnSubmitLoc);
    }
     //--------------------------------------------------------------------------
     /**
     * Method: Get Password field hint.
     * Return Password field hint as String
     * @authors Yana Fridman
     */
     //-------------------------------------------------------------------------- 
    public String currentPasswordLabel() {
    	return getText(currentPasswordLabelLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Confirm Password field hint.
    * Return Confirm Password field hint as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String newPasswordLabel() {
    	return getText(newPasswordLabelLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Password Format validation error text.
    * Return Password Format validation error text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String errorEmptyPassword() {
    	return getText(errorEmptyPasswordLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Confirm Password Format validation error text.
    * Return Confirm Password Format validation error text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String errorEmptyNewPassword() {
    	return getText(errorEmptyNewPasswordLoc);
    }
    public String errorMatch() {
        return getText(errorMatchLoc);
    }
    public boolean errorCurrentPasswordExists() throws Exception {

        if (isExists(errorEmptyPasswordLoc, 6)) {
            return true;
        }
        return false;
    }
    public boolean errorNewPasswordExists() throws Exception {

        if (isExists(errorEmptyNewPasswordLoc, 6)) {
            return true;
        }
        return false;
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Proceed button.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickBtnSubmit()  throws Exception {
        click(btnSubmitLoc);
    }
    public void clickBtnCancel()  throws Exception {
        click(btnCancelLoc);
    }
    public void clickNewPasswordFld()  throws Exception {
        click(newPasswordInput);
    }
    public void clickCurrentPasswordFld()  throws Exception {
        click(currentPasswordInput);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Input Password data.
    * Get Password as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void InputCurrentPassword(String urPassword)  throws Exception {
        sendKeys(currentPasswordInput, urPassword);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Input Confirm Password data.
    * Get Confirm Password as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void InputNewPassword(String urPassword)  throws Exception {
        sendKeys(newPasswordInput, urPassword);
    }

    public String notifyText() throws Exception {
        int t = 0;
        while (getText(notifyTextLoc).length()< 1 & t < 10000){
            Thread.sleep(500);
            t = t + 500;
        };
        return getText(notifyTextLoc);
    }

    public void clickOkBtn()  throws Exception {
        click(btnOkLoc);
    }
}


