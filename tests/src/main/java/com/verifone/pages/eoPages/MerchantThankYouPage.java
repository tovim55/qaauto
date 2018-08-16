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

    public class MerchantThankYouPage extends BasePage {

        private final static String url = "";
        private final static String title = "Thank you!";

        private By titleLoc = By.xpath("//*[@class='top-container']");



        public MerchantThankYouPage() {
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


}
