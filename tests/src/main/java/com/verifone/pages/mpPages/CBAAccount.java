package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class CBAAccount extends BasePage
{

        private final static String url = BaseTest.envConfig.getWebUrl() + "home";
        private final static String title = "Dashboard | Verifone Australia";

    private By account = By.xpath("//*[@id='account']");
    private By myApps = By.xpath("//*[@id='myapps']");
    private By manageApps = By.xpath("//*[@class='adb-link__nav adb-stack--item_content js-count'][@href='/account/apps']");
    private By manage = By.cssSelector("button[class='adb-button adb-button__neutral adb-js-context_menu--trigger adb-context_menu--trigger']");
    private By cancelSubscr = By.cssSelector("a[class='cancelSubscriptionLink adb-link__option adb-stack--item_content']");
    private By feedBack = By.xpath("//*[contains(text(),'successfully')]");

    //////
    private By search = By.cssSelector("input[class='js-search-input adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By searchBtn = By.xpath("//*[@id=\"appsTable\"]/div/div[1]/div[1]/menu/div[2]/div/div[2]/button");
    private By itemLink = By.cssSelector("div[data-auto='icon']");

    private By removeBtn = By.xpath("//*[@id=\"cancel\"]");
    private By yesBtn = By.cssSelector("button[class='adb-button__primary buttonResponse']");



    public CBAAccount()
    {
        super(url, title);
    }

    private List<WebElement> purchasedApps;

    public void manageApps (String appName)
    {
        click(account);
        click(manageApps);
    }

    public void cancelSubscribsion (String appName) throws InterruptedException {
        ExpectedConditions.presenceOfElementLocated(manage);
        click(manage);
        click(cancelSubscr);
        ExpectedConditions.presenceOfElementLocated(yesBtn);
        click(yesBtn);
        ExpectedConditions.presenceOfElementLocated(feedBack);
        Thread.sleep(7000);
        testLog.info(getText(feedBack));
    }

    public void unsubscribeApp(String appName) throws InterruptedException
    {
        purchasedApps = getWebElements(itemLink, 500, ExpectedConditions.presenceOfElementLocated(itemLink));
        String textSuccess = "Your subscription to " + appName + " was successfully removed.";

        for(WebElement name: purchasedApps)
        {
           testLog.info(name.getAttribute("alt"));
           if(name.getAttribute("alt").equals(appName))
               name.click();
        }
///////
        testLog.info(getText(removeBtn));
        //ExpectedConditions.elementToBeClickable(removeBtn);
        click(removeBtn);
        //ExpectedConditions.elementToBeClickable(yesBtn);
        click(yesBtn);
        ExpectedConditions.presenceOfElementLocated(feedBack);
        Thread.sleep(7000);
        testLog.info(getText(feedBack));

    }

}
