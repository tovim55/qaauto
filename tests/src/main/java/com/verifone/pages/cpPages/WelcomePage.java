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
* This class described all elements and actions can be executed on Welcome page.
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------

public class WelcomePage extends BasePage {

    private final static String url = "";
    private final static String title = "Forgot your password?";
    
    private By titleLoc = By.xpath("//*[@class='title']"); 
    private By textLoc1 = By.xpath("//*[@id=\"loginForm\"]/div/div[2]");
    private By textLoc2 = By.xpath("//*[@id=\"loginForm\"]/div/div[3]");
    private By loginBtnLoc = By.xpath("//*[@class='btn btn-raised btn-primary btn-block']"); 

    
    
    public WelcomePage() {
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
    * Method: Get Page text.
    * Return Page text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String pageText1() {
//    	String a = getText(textLoc1);
//    	System.out.println(a);
    	return getText(textLoc1);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Page text.
    * Return Page text as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String pageText2() {
//    	String a = getText(textLoc2);
//    	System.out.println(a);
    	return getText(textLoc2);
    }
  //--------------------------------------------------------------------------
    /**
    * Method: Get Login button Label.
    * Return Login button Label as String
    * @authors Yana Fridman
    */
    //-------------------------------------------------------------------------- 
    public String loginBtnLabel() {
//    	String a = getText(loginBtnLoc);
//    	System.out.println(a);
    	return getText(loginBtnLoc);
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
}


