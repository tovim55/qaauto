package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.appUtils.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

public class CBAProducts extends BasePage {
    private final static String url = "";
    private final static String title = "";

    public CBAProducts() {
        super(url, title);
    }


    /////Staging Products/////
    private By createProduct = By.cssSelector(".adb-styled.menu.attached.addProductMenuLink button:first-child");

    /////Product Info/////
    private By appName = By.id("appNameField");
    private By modelFree = By.cssSelector("input[id='free'][type='radio']");
    private By createProductBtn = By.cssSelector("button[name='createButton'][type='submit']");

    /////Listing Info/////
    private By listingInfo = By.linkText("Listing Info");
    private By wordDescription = By.cssSelector("input[name='blurbBorder:blurbBorder_body:blurbField'][type='text']");
    private By description = By.cssSelector("textarea[name='briefOverviewBorder:briefOverviewBorder_body:briefOverviewField']");
    private By linkPP = By.cssSelector("input[type='url'][name='privacyUrlBorder:privacyUrlBorder_body:privacyUrlField']");
    private By linkTC = By.cssSelector("input[type='url'][name='termsUrlBorder:termsUrlBorder_body:termsUrlField']");
    private By listingLogo = By.cssSelector("input[type='file'][name='uploadListingLogoContainer:fileUploadListingLogo']");
    private By profileLogo = By.cssSelector("input[type='file'][name='uploadProfileLogoContainer:fileUploadProfileLogo']");
    private By selectCategory = By.cssSelector("select[class ='adb-js-dropdown-select ']");
    private By saveBtn = By.cssSelector("button[class ='adb-button__primary adb-toolbar--item'][name='save']");
    private By feedBackPanel = By.xpath("//*[contains(text(),'Your listing')][@class ='feedbackPanelINFO']");
    private By savePreviewBtn = By.cssSelector("button[class ='adb-button__neutral adb-toolbar--item'][id='savePreview']");

    /////Profile/////
    private By addFeatureswBtn = By.xpath("//*[@id=\"main\"]/div/div[2]/section/div/div[1]/div/section[2]/div/div[2]/div/div/div[2]/button");
    private By profile = By.linkText("Profile");
    private By splashTitle = By.cssSelector("input[type='text'][name='slogansAndDescriptionContainer:sloganFieldBorder:sloganFieldBorder_body:sloganField']");
    private By splashDescript = By.cssSelector("textarea[name='slogansAndDescriptionContainer:descriptionFieldBorder:descriptionFieldBorder_body:descriptionField']");
    private By overviewImg = By.cssSelector("input[type='file'][name='uploaderOverviewImageContainer:fileUploadOverviewLogo']");
    private By demoUrl = By.cssSelector("input[type='url'][name='demoUrl']");
    private By docLink = By.cssSelector("input[type='url'][name='helpLinkField']");

    /////Credentials/////
    private By credentials = By.linkText("Credentials");
    private By autorizationType = By.xpath("//*[@class='sc-hgRTRy jFQzJN'][@name='authorizationType']");
    private By generateKey = By.xpath("//button[@class='sc-gldTML eVenQv'][@type='submit']");
    private By doneBtn = By.xpath("//button[@class='sc-gldTML dSzJck'][@type='button']");
    private By oathFeedbackInfo = By.xpath("//*[contains(text(),'A new OAuth 1.0 consumer was successfully created')]");

    /////Edit Integration/////
    private By integtation = By.linkText("Edit Integration");
    private By subscrCreate = By.cssSelector("input[type='url'][name='subscriptionConfig:createUrlBorder:createUrlBorder_body:createUrl']");
    private By subscrChange = By.cssSelector("input[type='url'][name='subscriptionConfig:upgradeUrlBorder:upgradeUrlBorder_body:upgradeUrl']");
    private By subscrCancel = By.cssSelector("input[type='url'][name='subscriptionConfig:cancelUrlBorder:cancelUrlBorder_body:cancelUrl']");
    private By subscrStatus = By.cssSelector("input[type='url'][name='subscriptionConfig:notifyUrlBorder:notifyUrlBorder_body:notifyUrl']");
    private By getSubscr = By.cssSelector("input[type='url'][name='subscriptionConfig:eventStatusUrlBorder:eventStatusUrlBorder_body:eventStatusUrl']");
    private By userAssign = By.cssSelector("input[type='url'][name='accessManagementConfig:assignUrl']");
    private By userUnassign = By.cssSelector("input[type='url'][name='accessManagementConfig:unassignUrl']");
    private By confirmSaveBtn = By.cssSelector("button[class ='adb-button__primary buttonResponse'][id='posLabel']");
    private By feedBackPanelInfo = By.xpath("//*[contains(text(),'The integration')]");

    /////Edit Authentication/////
    private By authentication = By.linkText("Edit Authentication");
    private By integrationSelect = By.xpath("//select[@aria-label='select'][@name='authenticationMethod'][@class='adb-js-dropdown-select ']");
    private By landingURL = By.xpath("//input[@name='landingUrl'][@type='text']");
    private By saveBut = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary'][@aria-label='save']");
    private By authenticationFeedbackInfo = By.xpath("//*[contains(text(),'The authentication configuration was saved successfully')]");

    /////Run Ping Tests/////
    private By pingTests = By.linkText("Run Ping Tests");
    private By runAllTets = By.xpath("//button[@class='adb-button__primary']");

    /////Integration Report/////
    private By integrationReport = By.xpath("//a[contains(text(),'Integration Report')]");
    private By markComplete = By.xpath("//a[@class='adb-button adb-button__medium adb-button__neutral toggle-button'][1]");
    private By subscribeRun = By.xpath("//a[@class='adb-button adb-button__medium adb-button__neutral'][1]");
    private By createSubscription = By.xpath("//button[@class='createSubscription buttonResponse adb-button__primary continue-to-next']");

    /////Platforms/////
    private By platforms = By.xpath("//a[contains(text(),'Platforms')]");
    private By addPlatformBtn = By.cssSelector("a[class ='add adb-button adb-button__neutral adb-button__small'][href='#product-platforms']");
    private By deviceRadio = By.cssSelector("input[type='radio'][value='[object Object]']");
    private By selectBtn = By.cssSelector("button[type='button'][class='adb-button adb-toolbar--item adb-button__primary']");
    private By headerItem = By.cssSelector("h1[class='adb-header--item']");
    private By addProductVersion = By.cssSelector("button[type='button'][class='adb-button']");

    //////Product Version///////
    private By versionTitle = By.cssSelector("input[type='text'][name='title']");
    private By versionCode = By.cssSelector("input[type='text'][name='versionCode']");
    private By versionName = By.cssSelector("input[type='text'][name='versionName']");

    private By chooseAsset = By.xpath("//div[@class='adb-form--field field-filenameFile']//input[@type='file']");
    private By chooseIcon = By.xpath("(//*[contains(text(),'Choose File')]//preceding::input[@type='file'])[2]");
    private By chooseImage = By.xpath("//*[contains(text(),'Overview Image')]//following::input[@type='file'][1]");

    private By mobileFamily = By.cssSelector("input[type='checkbox'][value='CarbonMobile']");
    private By saveButton = By.cssSelector("button[type='button'][class ='adb-button js-save-button adb-toolbar--item adb-button__primary'][name='save']");
    private By productVersionPanel = By.xpath("//*[contains(text(),'Product version details have been saved')]");
    private By refreshPage = By.xpath("//a[@class='adb-local_alert--link'][contains(text(),'refresh this page.')]");
    private By validatedPackage = By.xpath("//*[contains(text(),'Successfully validated package.')]");
    private By approveButton = By.cssSelector("button[type='button'][class ='adb-button js-approve-button adb-toolbar--item adb-button__primary']");
    private By approvedPackage = By.xpath("//*[contains(text(),'Product version has been approved and is now read-only')]");
    private By managePlatform = By.cssSelector("a[class ='adb-local_alert--link'][href='#product-platforms/a6e7fa19-2d2c-44fa-9274-131f5ed84240']");

    //////Publish Product///////
    //private By publishButton = By.cssSelector("button[type='button'][class ='adb-button__primary adb-button__small js-publish_button']");
    private By publishButton = By.xpath("//button[@class='adb-button__primary adb-button__small js-publish_button']");
    private By addToMarket = By.xpath("//button[@class ='adb-button__primary buttonResponse']");
    //private By saveMarket = By.xpath("//button[@class ='sc-fUKxqW bKnBhH sc-bAtgIc bePPjr']");
    private By saveMarket = By.xpath("//button[@type='submit']");
    private By addedFeedbackInfo = By.xpath("//*[contains(text(),'You have successfully added')]");

    //////Remove Product///////
    private By searchInput = By.xpath("//input[@class='js-search-input adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By searchIcon = By.xpath("//button[@class='adb-input_row--item_content adb-search_field--button adb-button__small']");
    private By productLink = By.xpath("//td[@class='product-name']");
    private By triggerMenu = By.xpath("//menu[@class='adb-js-context_menu adb-context_menu']//button[@class='adb-button adb-button__small adb-button__secret adb-context_menu--trigger adb-js-context_menu--trigger']");
    private By remove = By.xpath("//a[@class='adb-link__option adb-stack--item_content']//span[contains(text(),'Remove')]");
    private By removeProduct = By.xpath("//button[@class='adb-button__primary buttonResponse'][@id='posLabel']");
    private By removedFeedbackInfo = By.xpath("//*[contains(text(),'You successfully removed application')]");
    private By stagingCatalogLink = By.xpath("//a[@class='adb-link__nav adb-stack--item_content'][@href='#staging-products']");

    //////Unpublish Product///////
    private By unpublish = By.xpath("//button[@class='adb-button adb-button__neutral adb-button__small button-unpublish'][1]");
    private By confirmUnpublish = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary']");
    private By unpublishedFeedbackInfo = By.xpath("//*[contains(text(),'You have successfully unpublished')]");

    //////Delete Staging Product///////
    private By stagingProductLink = By.xpath("//a[@data-truncate='line']");
    private By deleteButton = By.xpath("//button[@class ='adb-button__danger adb-button__small'][contains(@name,'wrapper:contentPanel:productTable:tbody')]");
    private By confirmDelete = By.cssSelector("button[type='button'][class ='adb-button adb-toolbar--item adb-button__primary']");
    private By deleteFeedbackInfo = By.xpath("//*[contains(text(),'You have successfully deleted')]");

    ////Data/////
    String productName = "CBA-Carbon016 Test";
    String productDescription = "CBA Test App short description";
    String privacyPolicy = "https://www.commbank.com.au/security-privacy/general-security/privacy.html";
    String termsAndConditions = "https://www.samplestore.com/legal/terms_of_use_mobile";
    String feedBack = "Your listing info details have been saved.";
    String splash = "A Test App for CBA";
    String videoURL = "https://www.youtube.com/watch?v=jWFcGoGgnu4";
    String documentLink = "https://help.appdirect.com/appmarket/Default.htm#GettingStarted/gsg-intro.htm%3FTocPath%3DGetting%2520Started%7C_____0/?location%20=%20appmarket";
    String notificationURL = "https://verifoneausandbox.byappdirect.com/api/integration/v1/dummy/success";
    String feedBackInfo = "The integration configuration was saved successfully.";
    String headerInfo = "Manage Platform";
    String productVersionTitle = "Carbon 016";
    String productVersionCode = "1";//"199245635";
    String productVersionName = "1.1.0";
    String validationInfo = "Successfully validated package. ";
    String authenticationInfo = "The authentication configuration was saved successfully.";
    String addedInfo = "You have successfully added " + productName + " to your Production Catalog.";
    String removedInfo = "You successfully removed application " + productName + " from the Production Catalog. It is not visible in the marketplace, but it is still present in the Staging Catalog.";
    String unpublishedInfo = "You have successfully unpublished " + productName + ".";
    String oathKeyInfo = "A new OAuth 1.0 consumer was successfully created for your product.";

    /////Files/////
    String workingDir = System.getProperty("user.dir");
    String imagepath = workingDir + "\\src\\test\\resources\\apps\\image.jpg";
    //String packagepath = workingDir + "\\src\\test\\resources\\apps\\AndroidOn-199245635-2.1.1.zip";
    //String packagepath = workingDir + "\\src\\test\\resources\\apps\\Connectio-529464582-1.0.0.zip";
    String packagepath = workingDir + "\\src\\test\\resources\\apps\\Carbon016-144132322-1.1.0.zip";

    public void createStagingProduct() throws Exception {

        waitForLoader(appName);
        sendKeys(appName, productName);
        click(modelFree);
        click(createProductBtn);
    }

    public void listingInfoProduct() throws Exception {
        waitForLoader(listingInfo);
        click(listingInfo);
        sendKeys(wordDescription, productDescription);
        sendKeys(description, productDescription);
        sendKeys(linkPP, privacyPolicy);
        sendKeys(linkTC, termsAndConditions);
        sendKeys(listingLogo, imagepath);
        sendKeys(profileLogo, imagepath);
        click(saveBtn);

        if (isExists(feedBackPanel, 18)) {
            ExpectedConditions.textToBe(feedBackPanel, feedBack);
            testLog.info(driver.findElement(feedBackPanel).getText());
        }

        click(savePreviewBtn);
        click(addFeatureswBtn);
    }

    public void profileProduct() {
        click(profile);
        sendKeys(splashTitle, splash);
        sendKeys(splashDescript, splash);
        sendKeys(overviewImg, imagepath);
        sendKeys(demoUrl, videoURL);
        sendKeys(docLink, documentLink);
        click(savePreviewBtn);
        click(addFeatureswBtn);
    }

    public void credentialsOath() {
        waitForLoader(credentials);
        click(credentials);
        ExpectedConditions.presenceOfElementLocated(autorizationType);
        select(autorizationType, "shared");
        ExpectedConditions.elementToBeClickable(generateKey);
        click(generateKey);
        ExpectedConditions.elementToBeClickable(doneBtn);
        click(doneBtn);
        ExpectedConditions.textToBe(oathFeedbackInfo, oathKeyInfo);
        //click(listingInfo);
        //click(saveBtn);
    }

    public void editIntegration() {
        click(integtation);
        sendKeys(subscrCreate, notificationURL);
        sendKeys(subscrChange, notificationURL);
        sendKeys(subscrCancel, notificationURL);
        sendKeys(subscrStatus, notificationURL);
        sendKeys(getSubscr, notificationURL);
        sendKeys(userAssign, notificationURL);
        sendKeys(userUnassign, notificationURL);
        click(saveBtn);
        //click(confirmSaveBtn);
        ExpectedConditions.textToBe(feedBackPanelInfo, feedBackInfo);
    }

    public void editAuthentication() {
        waitForLoader(authentication);
        click(authentication);
        ExpectedConditions.elementToBeClickable(integrationSelect);
        select(integrationSelect, "BOOKMARK");
        sendKeys(landingURL, notificationURL);
        click(saveBut);
//        ExpectedConditions.textToBe(authenticationFeedbackInfo, authenticationInfo);
//        testLog.info(driver.findElement(authenticationFeedbackInfo).getText());

    }

    public void runPingTests() throws InterruptedException {
        waitForLoader(pingTests);
        click(pingTests);
        waitForLoader(runAllTets);
        click(runAllTets);
        Thread.sleep(7000);
    }

    public void integrationReport() {
        waitForLoader(integrationReport);
        click(integrationReport);

        List<WebElement> verifyAPI = getWebElements(markComplete, 500, ExpectedConditions.presenceOfElementLocated(markComplete));
        for (WebElement elem : verifyAPI) {
            elem.click();
        }
    }

    public void addPlatform() {
        waitForLoader(platforms);
        click(platforms);
        click(addPlatformBtn);
        click(deviceRadio);
        click(selectBtn);
        ExpectedConditions.textToBe(headerItem, headerInfo);
        click(addProductVersion);
    }

    public void productVersion() throws InterruptedException, AWTException {
        sendKeys(versionTitle, productVersionTitle);
        sendKeys(versionCode, productVersionCode);
        sendKeys(versionName, productVersionName);
        hoverAndClickOnElement(chooseAsset);
        fileUpload(packagepath);
        hoverAndClickOnElement(chooseIcon);

        fileUpload(imagepath);
        hoverAndClickOnElement(chooseImage);
        fileUpload(imagepath);

        click(mobileFamily);
        click(saveButton);
        WebElement productVersion = getWebElement(productVersionPanel, 500, ExpectedConditions.visibilityOfElementLocated(productVersionPanel));
        testLog.info(productVersion.getText());

        waitForLoader(managePlatform);
        List<WebElement> getElement = driver.findElements(refreshPage);
        System.out.println("size of the element :" + getElement.size());

        if (getElement.size() != 0) {
            System.out.println("inside if refresh");
            click(refreshPage);
        }

        waitForLoader(validatedPackage);
        ExpectedConditions.textToBe(validatedPackage, validationInfo);
        testLog.info(driver.findElement(validatedPackage).getText());
        click(approveButton);
        waitForLoader(approvedPackage);
        testLog.info(driver.findElement(approvedPackage).getText());
        click(managePlatform);
        waitForLoader(publishButton);
    }

    public void publishProduct() {
        waitForLoader(publishButton);
        scrollToElement(publishButton);
        click(publishButton);
        waitForLoader(addToMarket);
        click(addToMarket);
        waitForLoader(saveMarket);
        click(saveMarket);
        waitForLoader(addedFeedbackInfo);
        ExpectedConditions.textToBe(addedFeedbackInfo, addedInfo);
        testLog.info(driver.findElement(addedFeedbackInfo).getText());
    }

    public void removeProduct() {
        sendKeys(searchInput, productName);
        click(searchIcon);
        hoverAndClickOnElement(triggerMenu);

        hoverAndClickOnElement(remove);
        waitForLoader(removeProduct);
        click(removeProduct);
        waitForLoader(removedFeedbackInfo);
        ExpectedConditions.textToBe(removedFeedbackInfo, removedInfo);
        testLog.info(driver.findElement(removedFeedbackInfo).getText());
        hoverAndClickOnElement(stagingCatalogLink);

    }

    public void unpublishProduct() {
        waitForLoader(searchInput);
        sendKeys(searchInput, productName);
        click(searchIcon);
        hoverAndClickOnElement(unpublish);

        waitForLoader(confirmUnpublish);
        click(confirmUnpublish);
        waitForLoader(unpublishedFeedbackInfo);
        ExpectedConditions.textToBe(unpublishedFeedbackInfo, unpublishedInfo);
        testLog.info(driver.findElement(unpublishedFeedbackInfo).getText());
    }

    public void deleteSatgingProduct() {
        waitForLoader(searchInput);
        sendKeys(searchInput, productName);
        click(searchIcon);
        hoverAndClickOnElement(deleteButton);

        waitForLoader(confirmDelete);
        hoverAndClickOnElement(confirmDelete);
        waitForLoader(deleteFeedbackInfo);
        testLog.info(driver.findElement(deleteFeedbackInfo).getText());
    }
}
