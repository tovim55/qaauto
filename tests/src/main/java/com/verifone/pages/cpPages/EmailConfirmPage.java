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
public class EmailConfirmPage extends BasePage {

    private final static String url = "";
    private final static String title = "Email Confirmed!";
    
    private By titleLoc = By.xpath("//*[@class='title']");  
    private By textLoc = By.xpath("//*[@class='instruction']");
    private By textMutedLoc = By.xpath("//*[@class='note text-muted']");
    
    
    public EmailConfirmPage() {
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
    public String pageText() {
        
    	String a = getText(textLoc);
//    	System.out.println(a);
    	return getText(textLoc);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Page Muted text.
    * Return Page Muted text as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------  
    public String pageTextMuted() {
        
    	String a = getText(textMutedLoc);
//    	System.out.println(a);
    	return getText(textMutedLoc);
    }

}


