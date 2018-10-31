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
* This class described all elements and actions can be executed on Reset Password page.
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------

public class ResetPasswordPage extends BasePage {

    private final static String url = "";
    private final static String title = "Forgot your password?";
    
    private By titleLoc = By.xpath("//*[@class='reset text-center sso-form-title']");  
    private By mailLabelLoc = By.xpath("//*[@class='control-label' and @for='password']");   
    private By confirmMailLabelLoc = By.xpath("//*[@class='control-label' and @for='confirmPassword']");
    
    private By passwordInput = By.id("password");
    private By confirmPasswordInput = By.id("confirmPassword");
    private By btnProceedLoc = By.id("btnSubmit");
    
//    private By errorEmptyLoc = By.xpath("//*[@class='help-block']");
    private By errorEmptyLoc = By.xpath("//*[@id='resetPasswordForm']/div[1]/div[2]");
    private By errorConfirmEmptyLoc = By.xpath("//*[@id='resetPasswordForm']/div[2]/div[2]");
    
    
    public ResetPasswordPage() {
        super(url, title);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Page title.
    * Return Page title as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String pageTitle() {
        
//    	String a = getText(titleLoc);
//    	System.out.println(a);
    	return getText(titleLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Proceed button label.
    * Return Proceed button label as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
        public String btnProceedText() {
//    	String a = getText(btnProceedLoc);
//    	System.out.println(a);
    	return getText(btnProceedLoc);
    }
     //--------------------------------------------------------------------------
     /**
     * Method: Get Password field hint.
     * Return Password field hint as String
     * @authors Yana Fridman
     */
     //-------------------------------------------------------------------------- 
    public String passwordLabelText() {
//    	String a = getText(mailLabelLoc);
//    	System.out.println(a);
    	return getText(mailLabelLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Confirm Password field hint.
    * Return Confirm Password field hint as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String confirmPasswordLabelText() {
//    	String a = getText(confirmMailLabelLoc);
//    	System.out.println(a);
    	return getText(confirmMailLabelLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Password Format validation error text.
    * Return Password Format validation error text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String errorEmptyText() {
//    	String a = getText(errorEmptyLoc);
//    	System.out.println(a);
    	return getText(errorEmptyLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Confirm Password Format validation error text.
    * Return Confirm Password Format validation error text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String errorConfirmEmptyText() {
//    	String a = getText(errorConfirmEmptyLoc);
//    	System.out.println(a);
    	return getText(errorConfirmEmptyLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Proceed button.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickBtnProceed()  throws Exception {
        click(btnProceedLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Input Password data.
    * Get Password as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void InputPassword(String urPassword)  throws Exception {
        sendKeys(passwordInput, urPassword);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Input Confirm Password data.
    * Get Confirm Password as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void InputConfirmPassword(String urPassword)  throws Exception {
        sendKeys(confirmPasswordInput, urPassword);
    }
}


