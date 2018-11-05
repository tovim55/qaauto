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
* This class described all elements and actions can be executed on Merchants page. 
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------

public class MerchantViewPage extends BasePage {

    private final static String url = "";
    private final static String title = "Action Required";
    
    private By titleLoc = By.xpath("//*[@class='top-container']");
    private By textLoc1 = By.xpath("//*[@class='panel-heading panel-no-border']");
    private By textLoc2 = By.xpath("//*[@class='panel-body']");


    public MerchantViewPage() {
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
        
    	String a = getText(textLoc1);
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
        
    	String a = getText(textLoc2);
//    	System.out.println(a);
    	return getText(textLoc2);
    }
}



