package com.verifone.pages.mpPages;


import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.verifone.tests.steps.mpPortal.Steps.createAssignUser;
import static com.verifone.utils.Assertions.assertTextContains;

/**
 * This class described all elements and actions can be executed on Account & Assign Apps option.
 */

public class CBAAssignPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Dashboard | Verifone Australia";
    //private static String deviceUserName =BaseTest.envConfig.getDeviceUserName();

    private By linkManage = By.xpath("//*[text()='Manage']");
    private By btnAccount = By.xpath("//*[@id='account']");
    private By linkAssignApp = By.xpath("//*[text()='Assign Apps']");
    private By appToUsers = By.xpath("//*[contains(@aria-label,'appToUsers')]");
    private By searchAppLoc = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[@placeholder='Search']");
    private By btnAppSearch = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[@class='adb-icon__search']");
    private By findAppLoc = By.xpath("//*[@class='adb-table adb-table__actionable']//tbody//tr[1]//td[1]//div[2]//h4");
    private By searchUserLoc = By.xpath("//*[@class='adb-drawers--panel right-col']//*[@placeholder='Search']");
    private By btnUserSearch = By.xpath("//*[@class='adb-drawers--panel right-col']//*[@class='adb-icon__search']");
    //private By findUserLoc = By.xpath("//*[@class='adb-drawers--panel right-col']//*[text()='Merchant Automation']");
    private By findUserLoc = By.xpath("//div[@class='js-right-panel']//table//tbody//tr[1]");
    private By btnNext = By.xpath("//*[@type='button']//*[@class='adb-icon__arrow_right']");
    private By btnSubmit = By.xpath("//*[@class='js-pager-next adb-pager--item']//*[text()='Submit']");
    //private By txtAssignSuccess = By.xpath("//*[text()='1 assignment successfully updated or is in queue.']");
    private By txtAssignSuccess = By.xpath("//div[@class='js-content']/p");
    private By linkMyApps = By.xpath("//a[@id = 'myapps']");
    private By titleList = By.xpath("//h4[@class='adb-summary--title']");

    private By user = By.xpath("//*[@class='sortable renderable']//P[@class='adb-summary--details']");
    private By appCheck = By.xpath("//td[@class='checkbox-cell renderable']/input[@type='checkbox']");
    private By selectApp = By.xpath("//*[@class='adb-summary--title']");
    private By linkApplication = By.xpath("//ul[@class='adb-secondary_nav--items']//li[3]//a");
    private By browseMarketPlace = By.xpath("//a[@class='adb-button adb-button__primary']");

    //private final DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
    public static String jobCreatedOnSubscription;
    List<WebElement> elementManage;


    public CBAAssignPage() {
        super(url, title);
    }

    /**
     * This method described all actions can be executed on Account Page.
     */

    public void moveToAssignApps() {
        //verify the visibility of Manage option and perform the operation if it is find
        waitUntilPageLoad(linkMyApps);
        elementManage = driver.findElements(linkManage);
        System.out.println("link manage size : " + elementManage.size());
        if (elementManage.size() != 0)
            click(linkManage);

        click(btnAccount);
        click(linkAssignApp);
    }

    /**
     * This method described all actions to select Assign App to Users option.
     */

    public void btnSelectAssignAppsToUser() throws InterruptedException {

        /* scroll vertically till the element find and click on Assign App To User button */
        waitForLoader(appToUsers);
        ExpectedConditions.visibilityOfElementLocated(appToUsers);
        scrollToElement(appToUsers);
        click(appToUsers);
        scrollToElement(appToUsers);
    }

    /**
     * This method described all actions and elements can be executed to search the app.
     */

    public void searchAppToAssign(String getAppName) {
        scrollToElement(searchAppLoc);
        click(searchAppLoc);
        sendKeys(searchAppLoc, getAppName); /* get application name from the properties */
        click(btnAppSearch);

        click(findAppLoc);
    }

    /**
     * This method described all actions and elements can be executed to search the User.
     */

    public void searchUserToAssign(String assignUserName) {

        click(searchUserLoc);
        //sendKeys(searchUserLoc, createAssignUser().getUserName()); /* get user from the properties */
        sendKeys(searchUserLoc, assignUserName); //To Test on VHQ DeviceID
        click(btnUserSearch);

        click(findUserLoc);
    }

    public void assignUsersToApps(String getAppName) {

        ExpectedConditions.presenceOfElementLocated(btnAccount);
        click(btnAccount);
        click(linkAssignApp);
        waitForLoader(user);
        scrollToElement(user);
        click(user);
        sendKeys(searchUserLoc, getAppName);
        click(btnUserSearch);
        click(appCheck);
    }

    /**
     * This method described user assignments, confirmation & submission.
     */

    public void userAssignment() throws InterruptedException {
        click(btnNext);
        click(btnSubmit);
    }

    /**
     * This method verifies the assignment of app is successfully done or not.
     */
    public void isAssignUpdated() {
        waitForLoader(txtAssignSuccess);
        String txtResult = getText(txtAssignSuccess);
        assertTextContains("successfully updated", txtResult);
        testLog.info(txtResult);
        // jobCreatedOnSubscription = dateFormat.format(new Date());

        //dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+03:00"));
        // jobCreatedOnSubscription = dateFormat.format(new Date());
        jobCreatedOnSubscription = getDownloadScheduleTime();
        System.out.println("GMT time UnSubscription : " + jobCreatedOnSubscription);
        testLog.info("----- App Subscription created date & Time : " + jobCreatedOnSubscription + " -----");
    }

    /**
     * @Method : This method verifies whether the application is purchased or not. If the
     * application is not purchased then it will goes to market place and buy it.
     */

    public void getAppToAssignUser(String appName) throws Exception {

        moveToAssignApps();
        btnSelectAssignAppsToUser();
        click(searchAppLoc);
        sendKeys(searchAppLoc, appName);
        click(btnAppSearch);

        Thread.sleep(2000);

        List<WebElement> appList;
        appList = driver.findElements(titleList);
        System.out.println("list of app :" + appList.size());

        if (appList.size() != 0) {
            testLog.info(appName + " exists in the MyApps list");
        } else {

            testLog.info(String.format(appName + " doesn't exists in the MyApp list"));
            click(browseMarketPlace);

            CBAMarketplace market = PageFactory.getCBAMarketplace();
            market.searchForApp(appName);
            market.buyFreeApp();
        }

    }

}
