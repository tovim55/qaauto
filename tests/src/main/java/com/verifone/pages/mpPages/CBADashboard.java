package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CBADashboard extends BasePage {

    private final static String url = "";
    private final static String title = "";

    public CBADashboard() {
        super(url, title);
    }

    ////Manage MarketPlace//////
    private By manage = By.cssSelector("a[class ='ad-component--link ad-component_dropdown--trigger manage-link']");
    private By marketPlace = By.cssSelector(".ad-component_list-item.ad-component_list-item--channel>.ad-component--link ");

    /////MarketPlace Products/////
    private By products = By.xpath("//*[@id=\"subnav-header\"]/div/ul[1]/li[3]");
    //private By addStagingProduct = By.cssSelector("button[class='go-to-import-link adb-button__small'][type='button']");
    private By addStagingProduct = By.xpath("//*[contains(text(),'Add Staging Product')]");


    public void manageMarketpace() throws InterruptedException {
        click(manage);
        click(marketPlace);
        ExpectedConditions.presenceOfElementLocated(products);
        click(products);
        waitForLoader(addStagingProduct);
        hoverAndClickOnElement(addStagingProduct);
        Thread.sleep(2000);
    }
}
