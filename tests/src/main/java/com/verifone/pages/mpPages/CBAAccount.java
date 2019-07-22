package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.verifone.tests.steps.mpPortal.Steps.createAssignUser;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class CBAAccount extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Dashboard | Verifone Australia";

    private static String appName = BaseTest.envConfig.getAppName();
    private By account = By.xpath("//*[@id='account']");
    private By manageApps = By.xpath("//*[@class='adb-link__nav adb-stack--item_content js-count'][@href='/account/apps']");
    private By mySearch = By.xpath("//input[@class='js-search-input adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By iconSearch = By.xpath("//button[@class='adb-input_row--item_content adb-search_field--button adb-button__small']");

    private By manage = By.xpath("//button[@class='adb-button adb-button__neutral adb-js-context_menu--trigger adb-context_menu--trigger']");
    private By cancelSubscr = By.xpath("//a[@class='cancelSubscriptionLink adb-link__option adb-stack--item_content']");

    private By feedBack = By.xpath("//*[contains(text(),'successfully')]");

    private By search = By.cssSelector("input[class='js-search-input adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By searchBtn = By.xpath("//*[@id=\"appsTable\"]/div/div[1]/div[1]/menu/div[2]/div/div[2]/button");
    private By yesBtn = By.xpath("//button[@class='adb-button__primary buttonResponse']");
    private By linkManage = By.xpath("//*[text()='Manage']");

    //String textSuccess = "Your subscription to " + appName + " was successfully removed.";
    String textSuccess = "Your subscription to was successfully removed.";


    public CBAAccount() {
        super(url, title);
    }

    private List<WebElement> purchasedApps;


    public void cancelSubscribsion(String appName) throws InterruptedException {

        /* Click on Manage menu option only when the user is merchantforautomation@getnada.com */
        String getUser = createAssignUser().getUserName();
        if (getUser.equals("merchantforautomation@getnada.com")){
            System.out.println("click on manage");
            click(linkManage);
        }

        click(account);
        click(manageApps);
        sendKeys(mySearch, appName);
        click(iconSearch);
        //ExpectedConditions.presenceOfElementLocated(manage);
        hoverAndClickOnElement(manage);
        hoverAndClickOnElement(cancelSubscr);
        ExpectedConditions.presenceOfElementLocated(yesBtn);
        click(yesBtn);
        ExpectedConditions.textToBe(feedBack, textSuccess);
        Thread.sleep(7000);
        testLog.info(getText(feedBack));
    }

}