package com.verifone.pages.mpPages;

import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.verifone.tests.BaseTest.envConfig;
import static com.verifone.tests.steps.mpPortal.Steps.createAssignUser;
import static com.verifone.tests.steps.mpPortal.Steps.createMerchantUser;

/**
 * This class described all elements and actions can be executed on Account & Assign Apps option.
 */

public class CBAAssignPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Dashboard | Verifone Australia";

    private final static String getAppName = BaseTest.envConfig.getAppName();

    private By linkManage = By.xpath("//*[text()='Manage']");
    private By btnAccount = By.xpath("//*[@id=\"account\"]");
    private By linkAssignApp = By.xpath("//*[text()='Assign Apps']");
    private By appToUsers = By.xpath("//*[contains(@aria-label,'appToUsers')]");
    private By searchAppLoc = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[@placeholder='Search']");
    private By btnAppSearch = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[@class='adb-icon__search']");
    private By findAppLoc = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[text()='New Danube']");
    private By searchUserLoc = By.xpath("//*[@class='adb-drawers--panel right-col']//*[@placeholder='Search']");
    private By btnUserSearch = By.xpath("//*[@class='adb-drawers--panel right-col']//*[@class='adb-icon__search']");
    private By findUserLoc = By.xpath("//*[@class='adb-drawers--panel right-col']//*[text()='Merchant Automation']");
    private By btnNext = By.xpath("//*[@type='button']//*[@class='adb-icon__arrow_right']");
    private By btnSubmit = By.xpath("//*[@class='js-pager-next adb-pager--item']//*[text()='Submit']");
    private By txtAssignSuccess = By.xpath("//*[text()='1 assignment successfully updated or is in queue.']");

    private By user = By.xpath("//*[@class='sortable renderable']");
    private By appCheck = By.xpath("//td[@class='checkbox-cell renderable']/input[@type='checkbox']");

    public CBAAssignPage()
    {
        super(url, title);
    }

    /**
     * This method described all actions can be executed on Account Page.
     */

    public void moveToAssignApps(){

        ExpectedConditions.elementToBeClickable(linkManage);
        click(linkManage);

        ExpectedConditions.elementToBeClickable(btnAccount);
        click(btnAccount);

        ExpectedConditions.elementToBeClickable(linkAssignApp);
        click(linkAssignApp);
    }

    /**
     * This method described all actions to select Assign App to Users option.
     */

    public void btnSelectAssignAppsToUser()throws InterruptedException{

        /* scroll vertically till the element find and click on Assign App To User button */
        scrollToElement(appToUsers);

        ExpectedConditions.elementToBeClickable(appToUsers);
        click(appToUsers);

        scrollToElement(appToUsers);
    }

    /**
     * This method described all actions and elements can be executed on Assign App to Users section.
     */

    public void assignAppToUser(){

        ExpectedConditions.elementToBeClickable(searchAppLoc);
        click(searchAppLoc);
        sendKeys(searchAppLoc,getAppName); /* get application name from the properties */
        click(btnAppSearch);


        ExpectedConditions.presenceOfElementLocated(findAppLoc);
        click(findAppLoc);

        ExpectedConditions.elementToBeClickable(searchAppLoc);
        click(searchUserLoc);
        sendKeys(searchUserLoc,createAssignUser().getUserName()); /* get user from the properties */
        click(btnUserSearch);

        ExpectedConditions.presenceOfElementLocated(findUserLoc);
        click(findUserLoc);

    }

    public void assignUsersToApps(){

        ExpectedConditions.presenceOfElementLocated(btnAccount);
        click(btnAccount);
        click(linkAssignApp);
        click(user);
        sendKeys(searchUserLoc,getAppName);
        click(btnUserSearch);
        click(appCheck);
    }

    /**
     * This method described user assignments, confirmation & submission.
     */

    public void userAssignment() throws InterruptedException{

        ExpectedConditions.elementToBeClickable(searchAppLoc);
        click(btnNext);

        ExpectedConditions.presenceOfElementLocated(btnSubmit);
        click(btnSubmit);
    }

   /* public void isAssignUpdated(){
        String txtResult = getText(txtAssignSuccess);
        System.out.println("result is :" + txtResult);
        Assert.assertTrue("Text not found!", txtResult.contains(text));
    }*/

}
