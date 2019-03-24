package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class MarketplacePage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By fldSearchLoc = By.xpath("//*[@placeholder='Find Applications']");
    private By btnSearchProductLoc = By.xpath("//*[@class='adb-icon__search adb-button--icon']");
//    private By rowProductLoc = By.xpath("//a[text()='Tab - Free - MultiUser - Hello World ']");

    public MarketplacePage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Input Product.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputSearchProduct(String SName)  throws Exception {
        sendKeys(fldSearchLoc, SName);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click Search.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSearchProduct()  throws InterruptedException {
        click(btnSearchProductLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Search Product by Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsProductByName(String SearchStr) throws Exception {
//        private By rowProductLoc = By.xpath("//a[text()='Tab - Free - MultiUser - Hello World ']");
        String a = "//a[text()='"+SearchStr+"']";
//        click(By.xpath(a));
        return isExists(By.xpath(a), 20);
    }
//--------------------------------------------------------------------------

}

