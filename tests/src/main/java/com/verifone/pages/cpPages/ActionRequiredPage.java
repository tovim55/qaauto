package com.verifone.pages.cpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
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

public class ActionRequiredPage extends BasePage {

    private final static String url = "";
    private final static String title = "Action Required";

    private By titleLoc = By.xpath("//*[@class='title']");
    private By textLoc = By.xpath("//*[@class='instruction']");
    private By loginLnkLoc = By.xpath("//*[@id='local_auth_div']/div[2]/div/div/div/div[2]/a");



    public ActionRequiredPage() {
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
     * Method: Click on LogIn link.
     * Method: Click on Back to Verifone Developer Central link.
     * @authors Yana Fridman
     */
    //--------------------------------------------------------------------------
    public void clickLoginLnk()  throws Exception {
        click(loginLnkLoc);
    }

}


