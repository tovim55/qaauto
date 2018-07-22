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
    public void loginInputEmail(String email)  throws Exception {
        sendKeys(emailLoc, email);
        click(passwordLoc);
    }
    public void loginInputPassword(String password)  throws Exception {
        sendKeys(passwordLoc, password);
        click(emailLoc);
    }
    /**
    * Method: Click on Login link.
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public void clickLoginBtn()  throws Exception {
        click(loginBtnLoc);
    }

}

