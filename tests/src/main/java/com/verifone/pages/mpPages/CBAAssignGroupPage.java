package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.vhqPages.VHQDeviceSearch;
import com.verifone.tests.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

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
    private By searchGroupMembers = By.xpath("//*[@class='group-details-batched js-group-details']//input[@placeholder='Search']");
    private By btnSearchGroupMember = By.xpath("//*[@class='group-details-batched js-group-details']//i[@class='adb-icon__search']");
    private By listOfGroupMembers = By.xpath("//*[@class='group-details-batched js-group-details']//*[@class='adb-summary--title']");

    //*** Locators related to verify App in the groups
    private By searchAppLoc = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[@placeholder='Search']");
    private By btnAppSearch = By.xpath("//*[@class='adb-drawers--panel adb-layout-column__first left-col']//*[@class='adb-icon__search']");
    private By titleList = By.xpath("//*[@class='adb-summary--title']");
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
    private By btnGroup = By.xpath("//*[@id='mainRegion']//li[1]/a");
    private By summaryDetails = By.xpath("//*[@class='adb-summary--details']");
    private By btnManageUsers = By.xpath("//ul[@class='adb-stack adb-stack__small']/li[1]");
    private By linkMyApps = By.xpath("//a[@id = 'myapps']");
    private By txtResult = By.xpath("//*[@class='adb-placeholder--content']/p");

    public static String jobCreatedOnGroups;

    public CBAAssignGroupPage() {
        super(url, title);
        System.out.println("url :" + url + " title :" + title);
    }

    /**
     * This method described the creation of group
     */
    public void createUsersGroup(String nameOfTheGroup, String groupDescription, ArrayList<String> listOfApp, ArrayList<String> listOfGroup) throws Exception {

        //Delete the group if it is present
        waitUntilPageLoad(titleList);
        List<WebElement> getList = getWebElements(titleList, 18, ExpectedConditions.presenceOfElementLocated(titleList));
        for (WebElement ele : getList) {
            if (ele.getText().equals(nameOfTheGroup)) {
                testLog.info("----- Group (<b>" + ele.getText() + "</b>) is exists. -----");
                verifyApplicationAssignment(listOfApp, listOfGroup);
                break;
            }
        }

        Thread.sleep(1000);
        testLog.info("---- (2). Create Group ----");
        //Move to Users page and create group by providing details
        click(btnCreateGroup);

        WebElement grpName = driver.findElement(txtGroupName);
        WebElement grpDescription = driver.findElement(txtGroupDescription);
        grpName.sendKeys(nameOfTheGroup);
        grpDescription.sendKeys(groupDescription);

        click(btnAddGroupDetails);
        waitUntilPageLoad(txtAvailableUsers);

        click(btnGroup);
        waitForLoader(btnGroup);
        displayDetails(titleList, nameOfTheGroup);
    }

    public void moveToGroups() {
        waitForLoader(tabGroups);
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
            //market.buyFreeApp();
            market.buyOneTimeApp();
            testLog.info(String.format("---- (3). " + appName + " app purchased from the marketplace."));
            Thread.sleep(3000);
            CBAAssignPage getPage = PageFactory.getAssignAppPage();
            getPage.moveToAssignApps();
            moveToGroups();
        }

    }

    public void moveToUsers() {
        testLog.info("---- (1). Go to Users section ----");

        //verify the visibility of Manage option and perform the operation if it is find
        waitUntilPageLoad(linkMyApps);
        List<WebElement> elementManage = driver.findElements(linkManage);
        System.out.println("link manage size : " + elementManage.size());
        if (elementManage.size() != 0)
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
    }

    public void verifyApplicationAssignment(ArrayList<String> listOfApp, ArrayList<String> listOfGroup) throws Exception {
        String textUnableToDelete = "Unable to Delete Group";
        String searchResult = "Search returned 0 results";

        testLog.info("----- (2). Delete Group :" + listOfGroup.get(0) + "-----");
        scrollToElement(txtSearchGroup);
        click(txtSearchGroup);
        sendKeys(txtSearchGroup, listOfGroup.get(0));
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

            //Un assign list of apps from the group
            for (String app : listOfApp) {
                assignApp.searchAppToAssign(app);
                assignApp.searchUserToAssign(listOfGroup.get(0));
            }

            assignApp.userAssignment();
            //assignApp.isAssignUpdated();
            testLog.info("---- (4). " + listOfGroup.get(0) + " is unassigned from the app.-----");
            deleteGroupTest(listOfGroup.get(0));

        } else {
            testLog.info("---- (3). " + listOfGroup.get(0) + " is not assigned to any app. -----");
            click(btnConfirm);
        }

        waitUntilPageLoad(txtResult);
        assertTextContains(getText(txtResult), searchResult);
        clearTextBoxValue(txtSearchGroup);
        //sendKeys(txtSearchGroup, "");
        click(btnSearch);
        waitUntilPageLoad(titleList);
        isGroupDeleted(titleList, listOfGroup.get(0));
    }


    public void isGroupDeleted(By loc, String val) {
        boolean testPassFlag = true;
        List<WebElement> getList = getWebElements(loc, 18, ExpectedConditions.presenceOfElementLocated(loc));
        System.out.println("size of summary :" + getList.size());
        for (WebElement ele : getList) {
            System.out.println("print the list :" + ele.getText());
            if (ele.getText().equals(val)) {
                testLog.info("----- Group (<b>" + ele.getText() + "</b>) is not deleted. -----");
                testPassFlag = false;
                break;
            }
            testLog.info("---- Available Group ( " + ele.getText() + " ) -----");
        }
        Assert.assertTrue(testPassFlag);
        testLog.info("---- (4). " + val + " is deleted. -----");
    }

    public void displayDetails(By loc, String val) {
        boolean testPassFlag = false;
        List<WebElement> getList = getWebElements(loc, 18, ExpectedConditions.presenceOfElementLocated(loc));
        System.out.println("size of summary :" + getList.size());
        for (WebElement ele : getList) {
            System.out.println("print the list :" + ele.getText());
            if (ele.getText().equals(val)) {
                testLog.info("----- New group (<b>" + ele.getText() + "</b>) has been created successfully. -----");
                testPassFlag = true;
                break;
            }
        }
        Assert.assertTrue(testPassFlag);
    }

    public void addDeviceToGroup(String groupName, ArrayList<String> listOfDevices, String deviceFlag) {
        String textGroupMembership = "Group membership has been successfully updated.";

        scrollToElement(txtSearchGroup);
        click(txtSearchGroup);
        sendKeys(txtSearchGroup, groupName);
        click(btnSearch);
        waitUntilPageLoad(btnSearch);
        hoverAndClickOnElement(btnSettings);

        scrollToElement(btnManageUsers);
        hoverAndClickOnElement(btnManageUsers);

        waitUntilPageLoad(txtAvailableUsers);
        scrollToElement(txtAvailableUsers);
        testLog.info("---- (3). Add available users to Group ----");

        waitUntilPageLoad(listOfUsers);
        for (int i = 0; i < listOfDevices.size(); i++) {
            System.out.println("list of array :" + listOfDevices.get(i));
            testLog.info("---- Added Users : " + (i) + ". " + listOfDevices.get(i) + " ----");

            //Verify the status of the device (Assigned or Removed) to/from the device.
            if (deviceFlag.equals("AddUser")) {
                manageAvailableUsers(searchAvailableUser, btnSearchUser, listOfUsers, listOfDevices.get(i));
            } else {
                manageAvailableUsers(searchGroupMembers, btnSearchGroupMember, listOfGroupMembers, listOfDevices.get(i));
            }
        }

        click(btnNext);

        waitUntilPageLoad(btnSubmit);
        click(btnSubmit);
        waitUntilPageLoad(isMembershipUpdated);
        assertTextContains(getText(isMembershipUpdated), textGroupMembership);

        //Get the time of Download Schedule
        jobCreatedOnGroups = getDownloadScheduleTime();

        testLog.info("---- (4). " + getText(isMembershipUpdated) + " -----");

        System.out.println("GMT time UnSubscription : " + jobCreatedOnGroups);
        testLog.info("----- App Subscription created date & Time : " + jobCreatedOnGroups + " -----");
    }

    public void searchDeviceJob(ArrayList<String> listOfApp, String jobName, String jobCreatedOnGroups, String deviceSerialNumber) throws Exception {
        VHQDeviceSearch deviceSearch = PageFactory.getVHQDeviceSearch();
        testLog.info("-------Device Serial Number : ( " + deviceSerialNumber + " )----------");
        for (String app : listOfApp) {
            System.out.println("List of app name : " + listOfApp);
            deviceSearch.validateJobInstall(app, jobName, jobCreatedOnGroups);
        }
    }

    private void manageAvailableUsers(By availableUser, By search, By users, String deviceName) {
        String disabled = "Disabled";
        click(availableUser);
        sendKeys(availableUser, deviceName);
        click(search);
        waitForLoader(users);
        assertTextContains(deviceName, getText(users));

        if (getText(isDisabled).equals(disabled)) {
            testLog.info("---- User / Device : " + getText(users) + " is disabled.");
            Assert.fail("---- User / Device : " + getText(users) + " is disabled.");
        } else {
            testLog.info("---- User / Device : " + getText(users) + " is active.");
            click(users);
        }
    }

}
