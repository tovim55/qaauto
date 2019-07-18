package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CBAMarketplace extends BasePage {
    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Verifone Australia Sandbox | Verifone Australia";

    //private By marketPlace = By.xpath("//*[@id=\"header\"]/header/div/nav/ul/li[3]");
    private By marketPlace = By.xpath("//*[@id='shop']");
    private By findApp = By.xpath("//input[@class='adb-input_row--item_content adb-search_field--input adb-text__small'][@placeholder='Find Applications']");
    private By searchBtn = By.xpath("//*[@class='adb-input_row--item_content adb-search_field--button adb-button adb-button__neutral adb-button__small'][@type='button']");

    private By listing = By.cssSelector("a[class='listing-row-content-title js-title--link']");
    private By continueBtn = By.cssSelector("button[class='adb-button__primary continue buttonResponse continue-to-next']");
    private By placeOrderBtn = By.cssSelector("button[id='placeOrder']");
    private By goToMyAppsBtn = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary']");
    private By tryFree = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__emphasis adb-button__large'][1]");

    //private By btnStartFreeTrial = By.xpath("//*[@class='adb-button adb-toolbar--item adb-button__emphasis adb-button__large'] //*[text() = 'Start a Free Trial']");
    //private By btnContinue = By.xpath("//*[@class='adb-button__primary continue buttonResponse continue-to-next']//*[text()='Continue']");
    //private By btnPlaceOrder = By.xpath("//*[@id='placeOrder']//*[text()='Place Order']");
    //private By btnBuyNow = By.xpath("//*[@type='button'] //*[text()='Buy Now']");
    //private By txtAppAlreadyPurchase = By.xpath("//*[@class = 'adb-caption adb-profile_header--caption js-header-caption js-scroll-hide']");
    //private By btnManage = By.xpath("//*[text()='Manage App']");
    //private By btnRemoveApp = By.xpath("//*[@id = 'cancel']");
    //private By btnYes = By.xpath("//*[@id = 'posLabel']");

    private By btnManageApp = By.xpath("//*[@id='return']");
    private By btnAssignApp = By.xpath("//*[@class = 'adb-button adb-button__primary adb-toolbar--item']");
    private By appToUsers = By.xpath("//*[contains(@aria-label,'appToUsers')]");
    private By feedBack = By.xpath("//*[@id='orderReceipt']/child::div/child::p");


    private List<WebElement> listingApps;

    public CBAMarketplace() {
        super(url, title);
    }

    public void searchForApp(String appName) {
        System.out.println(appName);
        click(marketPlace);
        sendKeys(findApp, appName);
        click(searchBtn);
    }

    public void veryfyListingApps() {
        listingApps = getWebElements(listing, 500, ExpectedConditions.presenceOfElementLocated(listing));
        int appsNumber = listingApps.size();
        testLog.info(appsNumber + " apps in listing");

        for (WebElement listingApp : listingApps) {

            testLog.info(listingApp.getText() + " in listing");

        }
    }

    public void buyFreeApp() {
        click(listing);
        click(tryFree);
        click(goToMyAppsBtn);
        //waitForLoader(buyNowBtn);
        //click(buyNowBtn);
        //click(continueBtn);
        //click(placeOrderBtn);
    }

    /**
     * Method : This method described all the actions and elements to buy the One Time Pay App
     * @author: Prashant Lokhande
     */

    public void buyOneTimeApp() throws Exception {

        /* Select One Time App. */
        click(listing);

        WebDriverWait element = new WebDriverWait(driver, 18);

        /* Select Buy Now button. */
        click(tryFree);

        /* Locate the Continue button and click on it. */
        element.until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
        scrollToElement(continueBtn);
        click(continueBtn);

        /* Locate the Place Order button and click on it. */
        element.until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtn));
        scrollToElement(placeOrderBtn);
        click(placeOrderBtn);

        //ExpectedConditions.visibilityOfElementLocated(feedBack);
        testLog.info(getText(feedBack));

        /* Locate the Manage App button and click on it. */
        element.until(ExpectedConditions.visibilityOfElementLocated(btnManageApp));
        scrollToElement(btnManageApp);
        click(btnManageApp);

        /* Assign App to User */
        click(btnAssignApp);

        ExpectedConditions.visibilityOfElementLocated(appToUsers);
        scrollToElement(appToUsers);

        /* Removed Purchased App */
       /* click(btnRemoveApp);
        click(btnYes);*/

        Thread.sleep(5000);
    }
}
