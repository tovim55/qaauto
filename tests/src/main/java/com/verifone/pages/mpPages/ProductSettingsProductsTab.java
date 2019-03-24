package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class ProductSettingsProductsTab extends BasePage {

    private final static String url = "";
    private final static String title = "Segment Groups";

    private By menuProductsLoc = By.xpath("//a[text()='Product']");
    private By btnSaveLoc = By.xpath("//span[text()='Save']");

    public ProductSettingsProductsTab() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Click Products tab.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuProducts()  throws InterruptedException {
        click(menuProductsLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Segment row contain Text.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void getTblGroupSegmentsRowByText(String SearchStr) throws InterruptedException {
        String a = "//span[text()='"+SearchStr+"']";
        click(By.xpath(a));
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Segment row contain Text.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void getTblSegmentsRowByText(String SearchStr) throws InterruptedException {
        String a = "//span[text()='"+SearchStr+"']";
        click(By.xpath(a));
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click Save button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSave()  throws InterruptedException {
        click(btnSaveLoc);
    }
//--------------------------------------------------------------------------
}

