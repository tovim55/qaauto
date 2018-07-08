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
* This class described all elements and actions can be executed on Confirmation page. 
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------

public class ForgotPasswordPage extends BasePage {

    private final static String url = "";
    private final static String title = "Forgot your password?";
    
    private By titleLoc = By.xpath("//*[@id='local_auth_div']/div[2]/div/div/div/h1");  
    private By textLoc = By.xpath("//*[@class='text-center']");
    private By mailLabelLoc = By.xpath("//*[@class='control-label']");   
    private By mailInput = By.id("username");
    private By btnSendLoc = By.id("btnSubmit");
    private By lnkLoginLoc = By.id("btnCancel");
    private By errorEmptyLoc = By.xpath("//*[@class='help-block']");
    
    
    public ForgotPasswordPage() {
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
        
    	String a = getText(titleLoc);
    	System.out.println(a);
    	return getText(titleLoc);
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
    * Method: Get Send button label.
    * Return Send button label as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
    public String btnSendText() {
    	String a = getText(btnSendLoc);
    	System.out.println(a);
    	return getText(btnSendLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Login link text.
    * Return Login link text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
    public String lnkLoginText() {
    	String a = getText(lnkLoginLoc);
    	System.out.println(a);
    	return getText(lnkLoginLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Mail field hint.
    * Return Mail field hint as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String mailLabelText() {
    	String a = getText(mailLabelLoc);
    	System.out.println(a);
    	return getText(mailLabelLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Format validation error text.
    * Return Format validation error text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String errorEmptyText() {
    	String a = getText(errorEmptyLoc);
    	System.out.println(a);
    	return getText(errorEmptyLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Send button.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickBtnSend()  throws Exception {
        click(btnSendLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Login link.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickLoginLnk()  throws Exception {
        click(lnkLoginLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Input email data.
    * Get Email as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void InputEmail(String ufEmail)  throws Exception {
        sendKeys(mailInput, ufEmail);
    }

}

