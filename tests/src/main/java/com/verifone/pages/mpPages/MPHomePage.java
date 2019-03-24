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
    public class MPHomePage extends BasePage {

        private final static String url = "";
        private final static String title = "Sign Up with Verifone Identity Server";

        private By headerMenuManageLoc = By.xpath("//*[@class='ad-component--link ad-component_dropdown--trigger manage-link']");

        private By marketplaceSubMenuLoc = By.xpath("//*[@class='ad-component_list-item ad-component_list-item--channel']");
        private By marketplaceMenuLoc = By.xpath("//a[text()='Marketplace']");

        public MPHomePage() {
            super(url, title);
        }

//--------------------------------------------------------------------------
        /**
         * Method: Wait 20 sec. for Account menu displayed and click on Account menu.
         * @authors Yana Fridman
         */
//--------------------------------------------------------------------------
        public void clickHeaderManageMenu() throws InterruptedException {
            boolean f1 = false;
            int t = 0;
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(headerMenuManageLoc));
            Thread.sleep(3000);
            while (f1 == false & t < 20000){
                f1 = driver.findElement(headerMenuManageLoc).isEnabled();
                t=t+1000;
            }
            click(headerMenuManageLoc);
        }
//--------------------------------------------------------------------------
        /**
         * Method: Click on Marketplace submenu.
         * @authors Yana Fridman
         */
//--------------------------------------------------------------------------
        public void clickMarketplaceSubMenu () throws InterruptedException {

            click(marketplaceSubMenuLoc);
        }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
        /**
         * Method: Click on Marketplace main menu.
         * @authors Yana Fridman
         */
//--------------------------------------------------------------------------
        public void clickMarketplaceMenu () throws InterruptedException {

            click(marketplaceMenuLoc);
        }
//--------------------------------------------------------------------------

}

