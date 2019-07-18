package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CBAMarketplace extends BasePage{

    private static String appName = BaseTest.envConfig.getAppName();
    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Verifone Australia Sandbox | Verifone Australia";

    private By marketPlace = By.xpath("//*[@id='shop']");
    private By findApp = By.xpath("//input[@class='adb-input_row--item_content adb-search_field--input adb-text__small'][@placeholder='Find Applications']");
    private By searchBtn = By.xpath("//*[@class='adb-input_row--item_content adb-search_field--button adb-button adb-button__neutral adb-button__small'][@type='button']");

    private By listing = By.cssSelector("a[class='listing-row-content-title js-title--link']");

    private By tryFree = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__emphasis adb-button__large'][1]");
    private By goToMyAppsBtn = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary']");

    private List<WebElement> listingApps;

    public CBAMarketplace()
    {
        super(url, title);
    }

    public void searchForApp ()
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

    public void buyFreeApp ()
    {
        click(listing);
        click(tryFree);
        click(goToMyAppsBtn);
    }


}
