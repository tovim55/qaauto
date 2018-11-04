package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on Email Template page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EmailTemplatePage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By titleLoc = By.xpath("//*[@id='MODULE_ID_UNDEFINED']/div[1]/div/h2");
    private By subTitleLoc = By.xpath("//*[@class='panel-title pull-left']");
    private By lnkCustomizeLoc = By.id("customizeBtn");
    private By previewMailerLoc = By.xpath("//*[@class='mailer-preview row ']");

    public EmailTemplatePage() {
        super(url, title);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Wait 5 sec for Email value displayed and Get Email value.
     * Return Email value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getPreviewMailer() throws InterruptedException {
        int t = 0;
        while (getText(previewMailerLoc).length()<=3 & t < 5000){
            Thread.sleep(500);
            t = t + 500;
        }
        return getText(previewMailerLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Name value.
     * Return Name value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getSubTitle() throws InterruptedException {
        return getText(subTitleLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get page Title.
     * Return page Title as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getTitle() throws InterruptedException {
        return getText(titleLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Role value.
     * Return Role value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getCustomizeLnk() throws InterruptedException {
        return getText(lnkCustomizeLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Wait 5 sec for Business Name value displayed and Get Business Name value.
     * Return Business Name value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean elementPreviewMailerExists() throws Exception {
        return isExists(previewMailerLoc, 6);
    }

    public void clickLnkCustomize() throws InterruptedException {
        click(lnkCustomizeLoc);
    }
}


