package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class ProductsTabProductCatalogPage extends BasePage {

    private final static String url = "";
    private final static String title = "Segment Groups";

    private By fldSearchLoc = By.xpath("//*[@placeholder='Search']");
    private By btnSearchProductLoc = By.xpath("//*[@class='adb-icon__search']");
    private By btnConfigProductLoc = By.xpath("//*[@class='adb-js-context_menu adb-context_menu']");
    private By menuEditMarketplaceSettingsLoc = By.xpath("//span[text()='Edit Marketplace Settings']");

    public ProductsTabProductCatalogPage() {
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
     * Method: Click Config Product.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnConfigProduct()  throws InterruptedException {
        click(btnConfigProductLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click Edit Marketplace Settings menu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuEditMarketplaceSettings()  throws InterruptedException {
        click(menuEditMarketplaceSettingsLoc);
    }
//--------------------------------------------------------------------------
}

