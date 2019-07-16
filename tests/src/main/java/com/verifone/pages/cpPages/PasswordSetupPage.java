package com.verifone.pages.cpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on Password Setup Final page.
 *
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
     *
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public String pageTitle() throws Exception {

        String a = getText(titleLoc);
        if (isExists(titleLoc, 100)) {
            return getText(titleLoc);
        } else {
            return "Not found!";
        }
//     System.out.println(a);
    }

    //--------------------------------------------------------------------------

    /**
     * Method: Get Page text.
     * Return Page text as String
     *
     * @authors Yana Fridman
     */
    //-------------------------------------------------------------------------- 
    public String pageText() {

        return getText(textLoc);
//    	System.out.println(a);
//    	return getText(textLoc);
    }
}


