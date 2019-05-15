package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CBAMarketplace extends BasePage
{
    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Verifone Australia Sandbox | Verifone Australia";

    //private By marketPlace = By.xpath("//*[@id=\"header\"]/header/div/nav/ul/li[3]");
    private By marketPlace = By.xpath("//*[@id=\"shop\"]");
    private By findApp = By.xpath("//*[@id=\"secondaryNav\"]/nav/ul/li[3]/div/div[1]/input");
    private By searchBtn = By.xpath("//*[@id=\"secondaryNav\"]/nav/ul/li[3]/div/div[2]/button");

    private By listing = By.cssSelector("a[class='listing-row-content-title js-title--link']");

    private By buyNowBtn = By.xpath("//*[@id=\"main\"]/div/div[2]/section[1]/div/div[1]/div/div/menu/button"); //*[@id="main"]/div/div[2]/section/div/div[1]/div/div/menu/button[1]
    private By continueBtn = By.cssSelector("button[class='adb-button__primary continue buttonResponse continue-to-next']");
    private By placeOrderBtn = By.cssSelector("button[id='placeOrder']");
    private By goToMyAppsBtn = By.cssSelector("button[class='adb-button__emphasis adb-button__large buttonResponse']");

    private List<WebElement> listingApps;

    public CBAMarketplace()
    {
        super(url, title);
    }

    public void searchForApp (String appName)
    {
        click(marketPlace);
        sendKeys(findApp, appName);
        click(searchBtn);
    }

    public void veryfyListingApps ()
    {
        listingApps = getWebElements(listing, 500, ExpectedConditions.presenceOfElementLocated(listing));
        int appsNumber = listingApps.size();
        testLog.info(appsNumber + " apps in listing");

        for (WebElement listingApp : listingApps) {

            testLog.info(listingApp.getText() + " in listing");
        }
    }

    public void buyApp ()
    {
        click(listing);
        //waitForLoader(buyNowBtn);
        click(buyNowBtn);
        click(continueBtn);
        click(placeOrderBtn);
        click(goToMyAppsBtn);

    }


}
