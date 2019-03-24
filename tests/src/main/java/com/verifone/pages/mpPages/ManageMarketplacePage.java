package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class ManageMarketplacePage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";

    private By tabProductLoc = By.xpath("//*[@id='subnav-header']/div/ul[1]/li[3]/a");

    public ManageMarketplacePage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Click on Product tab.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickTabProduct () throws InterruptedException {

        click(tabProductLoc);
    }
//--------------------------------------------------------------------------

}

