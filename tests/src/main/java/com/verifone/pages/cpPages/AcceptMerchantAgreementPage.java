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
* This class described all elements and actions can be executed on Accept Merchant Agreement page. 
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------
public class AcceptMerchantAgreementPage extends BasePage {

    private final static String url = "";
    private final static String title = "Action Required";
    
    private By titleLoc = By.xpath("//*[@class='top-container']");
    private By textLoc = By.xpath("//*[@class='panel panel-default']");
    private By acceptBtnLoc = By.xpath("//*[@class='btn btn-raised btn-primary accept-agreement']");
    private By docHeaderLoc = By.xpath("//*[@class='doc-header']");
    private By docTextLoc = By.xpath("//*[@id='agreementModal']/div/div/div[2]/div/div/p[1]/span[1]");
    private By docAgreeBtnLoc = By.id("agreementModalAffirmId");
    
    public AcceptMerchantAgreementPage() {
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
* Method: Get Accept button label.
* Return Accept button label as String
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------  
    public String acceptBtnText() {
    	String a = getText(acceptBtnLoc);
    	System.out.println(a);
    	return getText(acceptBtnLoc);
    }
//--------------------------------------------------------------------------
/**
* Method: Get Accept merchant agreement header text.
* Return header text as String
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------  
    public String docHeader() {
    	String a = getText(docHeaderLoc);
    	System.out.println(a);
    	return getText(docHeaderLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Accept merchant agreement body text.
    * Return body text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
    public String docText() {
    	String a = getText(docTextLoc);
    	System.out.println(a);
    	return getText(docTextLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Agree button label.
    * Return Agree button label as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
    public String docAgreeBtnText() {
    	String a = getText(docAgreeBtnLoc);
    	System.out.println(a);
    	return getText(docAgreeBtnLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Agreement page Accept button.
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
    public void clickAcceptBtn()  throws Exception {
        click(acceptBtnLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Click on Agreement document Accept button.
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
    public void clickDocAcceptBtn()  throws Exception {
        click(docAgreeBtnLoc);
    }

}