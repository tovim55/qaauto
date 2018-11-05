package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on Merchants page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class MerchantsPage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";

    private By titleMerchantsLoc = By.xpath("//*[@class='section-title']");

    public MerchantsPage() {
        super(url, title);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Search for Pending Merchant.
     * Return Row Number
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public int pendingMerchantRow() throws InterruptedException {
        int row = 1000;
        int i;
        for (i = 1; i<11; i++) {
            By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
            if (getText(rowLoc).contains("Pending")) {
                row = i;
                break;
            }
        }
        return row;
    }

//--------------------------------------------------------------------------
    /**
     * Method: Search for Active Merchant.
     * Return Row Number
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public int activeMerchantRow() throws InterruptedException {
        int row = 1000;
        int i;
        for (i = 1; i<11; i++) {
            By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
            if (getText(rowLoc).contains("Active")) {
                row = i;
                break;
            }
        }

        return row;
    }
//--------------------------------------------------------------------------
    /**
     * Method: Search for Active Merchant by additional parameter.
     * Return Row Number
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public int activeMerchantRow_ParameterSearch(String param) throws InterruptedException {
        int row = 1000;
        int i;
        for (i = 1; i<11; i++) {
            By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
            if (getText(rowLoc).contains("Active") & getText(rowLoc).contains(param)) {
                row = i;
                break;
            }
        }
        return row;
    }
//--------------------------------------------------------------------------
    /**
     * Method: Search for Disable Merchant.
     * Return Row Number
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public int disableMerchantRow() throws InterruptedException {
        int row = 1000;
        int i;
        for (i = 1; i<11; i++) {
            By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
            if (getText(rowLoc).contains("Disabled")) {
                row = i;
                break;
            }
        }
        return row;
    }
//--------------------------------------------------------------------------
    /**
     * Method: Search for Disable Merchant by additional parameter.
     * Return Row Number
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public int disableMerchantRow_ParameterSearch(String param) throws InterruptedException {
        int row = 1000;
        int i;
        for (i = 1; i<11; i++) {
            By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
            if (getText(rowLoc).contains("Disabled") & getText(rowLoc).contains(param)) {
                row = i;
                break;
            }
        }
        return row;
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Row in Merchants table.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickOnRow(int r) throws InterruptedException {
        By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + r + "]");
        click(rowLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get page Title.
     * Return page Title as string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String titleMerchants () throws InterruptedException {
        return getText(titleMerchantsLoc);
    }

}


