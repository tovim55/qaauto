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
* This class described all elements and actions can be executed on Password Setup Final page. 
* @authors Yana Fridman
*/
//--------------------------------------------------------------------------

public class PasswordSetupPage extends BasePage {

    private final static String url = "";
    private final static String title = "Action Required";
    
    private By titleLoc = By.xpath("//*[@class='title']");
    private By textLoc = By.xpath("//*[@class='instruction']");


    public PasswordSetupPage() {
        super(url, title);
    }
    //--------------------------------------------------------------------------
    /**
    * Method: Get Page title.
    * Return Page title as String
    * @authors Yana Fridman
    */
    //--------------------------------------------------------------------------
    public String pageTitle() throws Exception {
        
    	String a = getText(titleLoc);
        if (isExists(titleLoc,5)) {
            return getText(titleLoc);
        } else {
            return "Not found!";
        }
//    	System.out.println(a);
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
}


