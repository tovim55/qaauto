package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class CBAAccount extends BasePage
{

        private final static String url = "https://testverifone.appdirect.com/home";
        private final static String title = "Test Verifone US  | Verifone";

    private By account = By.xpath("//*[@id=\"header\"]/header/div/nav/ul/li[4]/ul/li[1]");
    private By myApps = By.xpath("//*[@id=\"myapps\"]");
    private By manageApps = By.xpath("//*[@id=\"mainRegion\"]/div/section/div[1]/div[1]/div/ul/li[2]");

    private By search = By.cssSelector("input[class='js-search-input adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By searchBtn = By.xpath("//*[@id=\"appsTable\"]/div/div[1]/div[1]/menu/div[2]/div/div[2]/button");
    private By itemLink = By.cssSelector("div[data-auto='icon']");

    private By removeBtn = By.xpath("//*[@id=\"cancel\"]");
    private By yesBtn = By.cssSelector("button[class='adb-button__primary buttonResponse']");
    private By feedBack = By.xpath("//*[contains(text(),'successfully')]");


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

    public void unsubscribeApp(String appName) throws InterruptedException {
        purchasedApps = getWebElements(itemLink, 500, ExpectedConditions.presenceOfElementLocated(itemLink));
        String textSuccess = appName + " was successfully removed.";

        for(WebElement name: purchasedApps)
        {
           testLog.info(name.getAttribute("alt"));
           if(name.getAttribute("alt").equals(appName))
               name.click();
        }

        testLog.info(getText(removeBtn));
        ExpectedConditions.elementToBeClickable(removeBtn);
        click(removeBtn);
        ExpectedConditions.elementToBeClickable(yesBtn);
        click(yesBtn);
        ExpectedConditions.presenceOfElementLocated(feedBack);
        Thread.sleep(7000);
        testLog.info(getText(feedBack));


    }

}