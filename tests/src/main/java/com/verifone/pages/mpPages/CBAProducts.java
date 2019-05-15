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

public class CBAProducts extends BasePage
{
    private final static String url = "";
    private final static String title = "";

    public CBAProducts() {
        super(url, title);
    }

    ////Manage MarketPlace//////
    private By manage = By.cssSelector("a[class ='ad-component--link ad-component_dropdown--trigger manage-link']");
    private By marketPlace = By.cssSelector(".ad-component_list-item.ad-component_list-item--channel>.ad-component--link ");

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
    private By feedBackPanel = By.xpath("//*[contains(text(),'Your listing')]");
    private By savePreviewBtn = By.cssSelector("button[class ='adb-button__neutral adb-toolbar--item'][id='savePreview']");
    /////Profile/////
    private By addFeatureswBtn = By.xpath("//*[@id=\"main\"]/div/div[2]/section/div/div[1]/div/section[2]/div/div[2]/div/div/div[2]/button");
    private By profile = By.linkText("Profile");
    private By splashTitle = By.cssSelector("input[type='text'][name='slogansAndDescriptionContainer:sloganFieldBorder:sloganFieldBorder_body:sloganField']");
    private By splashDescript = By.cssSelector("textarea[name='slogansAndDescriptionContainer:descriptionFieldBorder:descriptionFieldBorder_body:descriptionField']");
    private By overviewImg = By.cssSelector("input[type='file'][name='uploaderOverviewImageContainer:fileUploadOverviewLogo']");
    private By demoUrl = By.cssSelector("input[type='url'][name='demoUrl']");
    private By docLink = By.cssSelector("input[type='url'][name='helpLinkField']");
    /////Integration/////
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

    /////Run Ping Tests/////
    private By pingTests = By.linkText("Run Ping Tests");
    private By runAllTets = By.xpath("//button[@class='adb-button__primary']");

    /////Integration Report/////
    private By integrationReport = By.xpath("//a[contains(text(),'Integration Report')]");

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
    private By publishButton = By.cssSelector("button[type='button'][class ='adb-button__primary adb-button__small js-publish_button']");

    //////Delete Staging Product///////
    private By stagingProductLink = By.xpath("//a[@data-truncate='line'][1]");
    private By deleteButton = By.xpath("//button[@class ='adb-button__danger adb-button__small'][contains(@name,'wrapper:contentPanel:productTable:tbody')]");
    private By confirmDelete = By.cssSelector("button[type='button'][class ='adb-button adb-toolbar--item adb-button__primary']");
    private By deleteFeedbackInfo = By.xpath("//*[contains(text(),'You have successfully deleted')]");

    ////Data/////
    String productName = "CBATestingApp";
    String productDescription = "CBA Test App short description";
    String privacyPolicy = "https://www.commbank.com.au/security-privacy/general-security/privacy.html";
    String termsAndConditions = "https://www.samplestore.com/legal/terms_of_use_mobile";
    String feedBack = "Your listing info details have been saved.";
    String splash = "A Test App for CBA";
    String videoURL = "https://www.youtube.com/watch?v=jWFcGoGgnu4";
    String documentLink = "https://help.appdirect.com/appmarket/Default.htm#GettingStarted/gsg-intro.htm%3FTocPath%3DGetting%2520Started%7C_____0/?location%20=%20appmarket";
    String notificationURL = "https://marketplace.verifone.com/api/integration/v1/dummy/success?token={token}";
    String feedBackInfo = "The integration configuration was saved successfully.";
    String headerInfo = "Manage Platform";
    String productVersionTitle = "AndroidOn";
    String productVersionCode = "199245635";
    String productVersionName = "1.0.0";
    String validationInfo = "Successfully validated package. ";

    /////Files/////
    String workingDir = System.getProperty("user.dir");
    String imagepath = workingDir + "\\src\\test\\resources\\apps\\image.jpg";
    //String packagepath = workingDir + "\\src\\test\\resources\\apps\\AndroidOn-199245635-2.1.1.zip";
    String packagepath = workingDir + "\\src\\test\\resources\\apps\\Connectio-529464582-1.0.0.zip";



    public void createStagingProduct() throws Exception {
        waitForLoader(createProduct);
        hoverAndClickOnElement(createProduct);
        waitForLoader(appName);
        sendKeys(appName, productName);
        click(modelFree);
        click(createProductBtn);
    }

    public void listingInfoProduct()
    {
        waitForLoader(listingInfo);
        click(listingInfo);
        sendKeys(wordDescription, productDescription);
        sendKeys(description, productDescription);
        sendKeys(linkPP, privacyPolicy);
        sendKeys(linkTC,termsAndConditions );
        sendKeys(listingLogo, imagepath);
        sendKeys(profileLogo, imagepath);
        click(saveBtn);
        ExpectedConditions.textToBe(feedBackPanel, feedBack);
        click(savePreviewBtn);
        click(addFeatureswBtn);
    }

    public void profileProduct()
    {
        click(profile);
        sendKeys(splashTitle, splash);
        sendKeys(splashDescript, splash);
        sendKeys(overviewImg, imagepath);
        sendKeys(demoUrl, videoURL);
        sendKeys(docLink, documentLink);
        click(savePreviewBtn);
        click(addFeatureswBtn);
    }

    public void editIntegration()
    {
        click(integtation);
        sendKeys(subscrCreate, notificationURL);
        sendKeys(subscrChange, notificationURL);
        sendKeys(subscrCancel, notificationURL);
        sendKeys(subscrStatus, notificationURL);
        sendKeys(getSubscr, notificationURL);
        sendKeys(userAssign, notificationURL);
        sendKeys(userUnassign, notificationURL);
        click(saveBtn);
        click(confirmSaveBtn);
        ExpectedConditions.textToBe(feedBackPanelInfo, feedBackInfo);
    }

    public void runPingTests() throws InterruptedException {
        waitForLoader(pingTests);
        click(pingTests);
        waitForLoader(runAllTets);
        click(runAllTets);
        Thread.sleep(7000);
    }

    public void addPlatform()  {
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

        waitForLoader(refreshPage);
        click(refreshPage);
        waitForLoader(validatedPackage);
        ExpectedConditions.textToBe(validatedPackage, validationInfo);
        testLog.info(driver.findElement(validatedPackage).getText());
        click(approveButton);
        waitForLoader(approvedPackage);
        testLog.info(driver.findElement(approvedPackage).getText());
        click(managePlatform);
        waitForLoader(publishButton);
    }

    public void publishProduct()
    {
         click(publishButton);
    }
    public void deleteSatgingProduct()
    {
      WebElement stagingProduct = getWebElement(stagingProductLink, 500, ExpectedConditions.visibilityOfElementLocated(stagingProductLink));

      if(stagingProduct.getText().equals(productName)) {
          hoverAndClickOnElement(deleteButton);
      }
      waitForLoader(confirmDelete);
        hoverAndClickOnElement(confirmDelete);
      waitForLoader(deleteFeedbackInfo);
      testLog.info(driver.findElement(deleteFeedbackInfo).getText());
    }



}
