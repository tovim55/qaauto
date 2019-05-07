package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.appUtils.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

public class CBAMainMenu extends BasePage
{
    private final static String url = "";
    private final static String title = "";

    public CBAMainMenu () {
        super(url, title);
    }

    private By manage = By.cssSelector("a[class ='ad-component--link ad-component_dropdown--trigger manage-link']");
    private By marketPlace = By.cssSelector(".ad-component_list-item.ad-component_list-item--channel>.ad-component--link ");

    /////MarketPlace Products/////
    private By products = By.xpath("//*[@id=\"subnav-header\"]/div/ul[1]/li[3]");
    private By stagingCatalog = By.linkText("Staging Catalog");
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
    private By savePreviewBtn = By.cssSelector("button[class ='adb-button__neutral adb-toolbar--item'][name='savePreview']");
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

    /////Ping Tests/////
    private By pingTests = By.linkText("Run Ping Tests");
    private By runAllTests = By.cssSelector(".adb-toolbar.adb-header--item adb-button__group>.adb-button__primary");

    /////Platforms/////
    private By platforms = By.cssSelector("a[class ='adb-link__nav adb-stack--item_content adb-stack--item_content__nesting adb-js-toggle-link collapsed']");

    ////Data/////
    String productName = "CBATestingApp";
    String productDescription = "CBA Test App short description";
    String privacyPolicy = "https://www.commbank.com.au/security-privacy/general-security/privacy.html";
    String termsAndConditions = "https://www.samplestore.com/legal/terms_of_use_mobile";
    String feedBack = "Your listing info details have been saved.";
    String splash = "A Test App for CBA";
    String videoURL = "https://www.youtube.com/watch?v=jWFcGoGgnu4";
    String documentLink = "https://help.appdirect.com/appmarket/Default.htm#GettingStarted/gsg-intro.htm%3FTocPath%3DGetting%2520Started%7C_____0/?location%20=%20appmarket";
    String notificationURL = "https://testverifone.appdirect.com/api/integration/v1/dummy/success";
    String feedBackInfo = "The integration configuration was saved successfully.";

    /////Files/////
    String workingDir = System.getProperty("user.dir");
    String filepath = workingDir + "\\src\\test\\resources\\apps\\image.jpg";

    public void createStagingProduct() throws Exception {
        click(manage);
        click(marketPlace);
        ExpectedConditions.presenceOfElementLocated(products);
        click(products);
        testLog.info(driver.getCurrentUrl());
        click(stagingCatalog);
        waitForLoader(createProduct);
        click(createProduct);
    }

    public void listingInfoProduct()
    {
        waitForLoader(appName);
        sendKeys(appName, productName);
        click(modelFree);
        click(createProductBtn);
        waitForLoader(listingInfo);
        click(listingInfo);
        sendKeys(wordDescription, productDescription);
        sendKeys(description, productDescription);
        sendKeys(linkPP, privacyPolicy);
        sendKeys(linkTC,termsAndConditions );
        sendKeys(listingLogo, filepath);
        sendKeys(profileLogo, filepath);
        click(saveBtn);
        ExpectedConditions.textToBe(feedBackPanel, feedBack);
        click(savePreviewBtn);
    }

    public void profileProduct()
    {
        click(addFeatureswBtn);
        click(profile);
        sendKeys(splashTitle, splash);
        sendKeys(splashDescript, splash);
        sendKeys(overviewImg, filepath);
        sendKeys(demoUrl, videoURL);
        sendKeys(docLink, documentLink);
        click(savePreviewBtn);
    }

    public void editIntegration()
    {
        click(addFeatureswBtn);
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




}
