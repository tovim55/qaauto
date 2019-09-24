package com.verifone.pages.mpPages;

import com.verifone.infra.EnvConfig;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.verifone.utils.Assertions.assertTextContains;

public class CBAAssignGroupPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Assign Apps to Groups | AppDirect";

    //*** Locators related to Create group test. ***
    private By linkMerchantAutomation = By.xpath("//*[@class='ad-component--link ad-component_dropdown--trigger']");
    private By linkMyProfile = By.xpath("//*[@class='ad-component--link ' and text()='My Profile']");
    private By tabSelectGroups = By.xpath("//*[@class='adb-tabs--items']/li[2]/a");
    private By btnManageGroups = By.xpath("//*[@class='adb-header--item'][2]");
    private By btnCreateGroup = By.xpath("//i[@class='adb-button--icon adb-icon__add']");
    private By txtGroupName = By.xpath("//*[contains(@id,'name')]");
    private By txtGroupDescription = By.xpath("//*[contains(@id,'description')]");
    private By btnAddGroupDetails = By.xpath("//*[@class='adb-button adb-button__primary adb-toolbar--item']");
    private By txtAvailableUsers = By.xpath("//h3[@class='adb-container_header--title adb-container_header--item' and text()='Available Users']");
    private By listOfUsers = By.xpath("//*[@class='users-batched js-users']//*[@class='adb-summary--title']");
    private By btnSubmit = By.xpath("//*[@class='adb-button adb-button__emphasis']");
    private By searchAvailableUser = By.xpath("//*[@class='users-batched js-users']//input[@placeholder='Search']");
    private By btnSearchUser = By.xpath("//*[@class='users-batched js-users']//i[@class='adb-icon__search']");
    private By isMembershipUpdated = By.xpath("//*[@class='js-alert']//div/div/div//p");

    //*** Locators related to verify App in the groups
    private By searchAppLoc = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[@placeholder='Search']");
    private By btnAppSearch = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[@class='adb-icon__search']");
    private By titleList = By.xpath("//h4[@class='adb-summary--title']");
    private By browseMarketPlace = By.xpath("//a[@class='adb-button adb-button__primary']");
    private By tabGroups = By.xpath("//*[@class='adb-tabs--item']//*[text()='Groups']");
    private By appToGroups = By.xpath("//*[contains(@aria-label,'appToGroups')]");

    private By linkUser = By.xpath("//*[text()='Users']");
    private By linkGroups = By.xpath("//*[@class='adb-stack--item']//a[text()='Groups']");
    private By btnSettings = By.xpath("//*[@class='adb-context_menu adb-context_menu__small']");
    private By deleteGroup = By.xpath("//ul[@class='adb-stack adb-stack__small']/li[3]");
    private By linkManage = By.xpath("//*[text()='Manage']");
    private By btnAccount = By.xpath("//*[@id='account']");
    private By txtSearchGroup = By.xpath("//input[@class='adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By btnSearch = By.xpath("//button[@class='adb-input_row--item_content adb-search_field--button adb-button adb-button__neutral adb-button__small']");
    private By btnConfirm = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary']");
    private By txtModal = By.xpath("//h3[@class='adb-modal--header']");
    private By linkAssignApp = By.xpath("//*[text()='Assign Apps']");
    private By descriptionUnableToDelete = By.xpath("//div[@class='adb-modal--content modal-content']/p");
    private By isDisabled = By.xpath("//*[@class='adb-table--card_text adb-summary']//div[@class='header']");
    private By btnNext = By.xpath("//*[@type='button']//*[@class='adb-icon__arrow_right']");

    String textUnableToDelete = "Unable to Delete Group";
    String textGroupMembership = "Group membership has been successfully updated.";
    String disabled = "Disabled";

    public CBAAssignGroupPage() {
        super(url, title);
        System.out.println("url :" + url + " title :" + title);
    }

    /**
     * This method described the creation of group
     */
    public void createUsersGroup(String nameOfTheGroup, String groupDescription) {
        moveToUsers();

        testLog.info("---- (2). Create Group ----");
        //Move to Users page and create group by providing details
        click(btnCreateGroup);

        WebElement grpName = driver.findElement(txtGroupName);
        WebElement grpDescription = driver.findElement(txtGroupDescription);
        grpName.sendKeys(nameOfTheGroup);
        grpDescription.sendKeys(groupDescription);

        click(btnAddGroupDetails);
        waitUntilPageLoad(txtAvailableUsers);
        scrollToElement(txtAvailableUsers);

        testLog.info("---- (3). Add available users to Group ----");
        ArrayList<String> list = new ArrayList<String>();
        list.add("Merchant Automation");
        list.add("Device_ID CarbonMobile_CM5");

        waitUntilPageLoad(listOfUsers);
        List<WebElement> availableUsersList = driver.findElements(listOfUsers);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("list of array :" + list.get(i));
            testLog.info("---- Added Users : " + (i) + ". " + list.get(i) + " ----");
            click(searchAvailableUser);
            sendKeys(searchAvailableUser, list.get(i));
            click(btnSearchUser);
            waitForLoader(listOfUsers);
            assertTextContains(list.get(i), getText(listOfUsers));

            if (getText(isDisabled).equals(disabled)) {
                testLog.info("---- User: " + getText(listOfUsers) + " is disabled.");
            } else {
                testLog.info("---- User: " + getText(listOfUsers) + " is active.");
                click(listOfUsers);
            }
        }

        click(btnSubmit);
        waitUntilPageLoad(isMembershipUpdated);
        assertTextContains(getText(isMembershipUpdated), textGroupMembership);
        testLog.info("---- (4). " + getText(isMembershipUpdated) + " -----");
    }

    public void moveToGroups() {
        click(tabGroups);
        scrollToElement(appToGroups);
        click(appToGroups);
        scrollToElement(searchAppLoc);
    }

    public void getAppToAssignGroups(String appName) throws Exception {
        testLog.info("---- (1). Verify the availability of the app " + appName + " ----");
        click(searchAppLoc);
        sendKeys(searchAppLoc, appName);
        click(btnAppSearch);

        Thread.sleep(2000);

        List<WebElement> appList;
        appList = driver.findElements(titleList);
        System.out.println("list of app :" + appList.size());

        if (appList.size() != 0) {
            testLog.info("---- (2). " + appName + " exists in the MyApps list ----");
        } else {

            testLog.info(String.format("---- (2). " + appName + " doesn't exists in the MyApp list"));
            click(browseMarketPlace);

            CBAMarketplace market = PageFactory.getCBAMarketplace();
            market.searchForApp(appName);
            market.buyFreeApp();
            testLog.info(String.format("---- (3). " + appName + " app purchased from the marketplace."));
        }

    }

    public void moveToUsers() {
        testLog.info("---- (1). Go to Users section ----");
        click(linkManage);
        click(btnAccount);
        click(linkUser);
        waitForLoader(linkUser);
        scrollToElement(linkGroups);
        click(linkGroups);
        waitForLoader(linkGroups);
    }

    public void deleteGroupTest(String groupName) {
        // moveToUsers();

        click(linkUser);
        waitForLoader(linkUser);
        scrollToElement(linkGroups);
        click(linkGroups);
        waitForLoader(linkGroups);

        scrollToElement(txtSearchGroup);
        click(txtSearchGroup);
        sendKeys(txtSearchGroup, groupName);
        click(btnSearch);
        waitUntilPageLoad(btnSearch);

        hoverAndClickOnElement(btnSettings);

        scrollToElement(deleteGroup);
        hoverAndClickOnElement(deleteGroup);
        waitUntilPageLoad(txtModal);
        click(btnConfirm);
        testLog.info("---- (5). " + groupName + " is deleted. -----");
    }

    public void verifyApplicationAssignment(String groupName, String getAppName) throws Exception {
        testLog.info("----- (2). Delete Group :" + groupName + "-----");

        scrollToElement(txtSearchGroup);
        click(txtSearchGroup);
        sendKeys(txtSearchGroup, groupName);
        click(btnSearch);
        waitUntilPageLoad(btnSearch);
        hoverAndClickOnElement(btnSettings);

        scrollToElement(deleteGroup);
        hoverAndClickOnElement(deleteGroup);
        waitUntilPageLoad(txtModal);
        System.out.println("Text : Modal Dialog " + getText(txtModal) + "Unable to delete :" + textUnableToDelete);

        if (getText(txtModal).contains(textUnableToDelete)) {
            System.out.println("in");
            testLog.info("---- (3). " + getText(txtModal) + " : " + getText(descriptionUnableToDelete) + "----");

            click(btnConfirm);
            waitUntilPageLoad(linkAssignApp);
            click(linkAssignApp);
            moveToGroups();
            CBAAssignPage assignApp = PageFactory.getAssignAppPage();
            assignApp.searchAppToAssign(getAppName);
            assignApp.searchUserToAssign(groupName);
            assignApp.userAssignment();
            assignApp.isAssignUpdated();
            testLog.info("---- (4). " + groupName + " is unassigned from the app.-----");
            deleteGroupTest(groupName);

        } else {
            testLog.info("---- (3). " + groupName + " is not assigned to any app. -----");
            click(btnConfirm);
            testLog.info("---- (4). " + groupName + " is deleted. -----");
        }
    }
}
