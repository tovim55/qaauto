package com.verifone.pages.cpPages;


import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.appUtils.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.io.IOException;
import java.util.List;


public class NewAppFormPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "[QA] Developer Central | Home";

//    private By loader = By.className("vui-spinner");
    private By loader = By.className("vui-overlay light level");
    private By appName = By.xpath("//input[@name='name']");
    private By generateIdBtn = By.id("generateBtn");
    private By version = By.xpath("//input[@name='number']");
    private By description5Words = By.xpath("//input[@name='blurb']");
    private By description = By.xpath("//textarea[@name='description']");
    private By category = By.xpath("//select[@name='category']");
    private By nextBtn = By.id("nextBtn");
    private By selectAllCountriesBtn =By.className("check-all");
    private String id = "control-value";
    private String uploadFileClassName = "dz-default dz-message dz-clickable";
    private By supportEmail = By.xpath("//input[@name='supportEmail']");
    private By supportWebsiteUrl = By.xpath("//input[@name='supportWebsiteUrl']");
    private By supportPhoneNumber = By.xpath("//input[@name='supportPhoneNumber']");
    private By termsOfUseUrl = By.xpath("//input[@name='termsOfUseUrl']");
    private By submitBtn= By.id("submitBtn");
    private By okBtn= By.id("okBtn");
    private By dropZonePackage = By.xpath("//*[@class='dropzone document-upload dashed-border dropzone-square ']");
    private By dropZoneImages = By.xpath("//*[@class='dropzone image-upload dashed-border dropzone-square ']");




    public NewAppFormPage() {
        super(url, title);
//        navigate();
    }

    public String fillGetStartedForm(Application app) throws InterruptedException {
        Thread.sleep(10000);
        waitForLoader(loader);
        sendKeys(this.appName, app.getAppName());
        Thread.sleep(2000);
        click(version);
        Thread.sleep(1000);
        click(generateIdBtn);
        Thread.sleep(2000);
        sendKeys(version, app.getVersion());
        sendKeys(this.description5Words, app.getDescription5Words());
        sendKeys(this.description, app.getDescription());
        select(category, "b44ae8fc-5163-11e7-a459-12a5177fe69f");
        Thread.sleep(10000);
        String generatedId = getElementsByClassJs(id, 1).getText();
        click(nextBtn);
        return generatedId.split("\n")[0];

    }

    public void fillUploadPackageForm(String appPath) throws IOException {
        waitForLoader(loader);
        uploadFile(appPath, getWebElement(dropZonePackage, 10,
                ExpectedConditions.visibilityOfElementLocated(dropZonePackage)), "zip");
        waitForLoader(loader);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(nextBtn);
    }

    public void fillAppIconScreenshots(String path) throws  IOException {
        waitForLoader(loader);
        List<WebElement> imagesList = getWebElements(dropZoneImages, 10,
                ExpectedConditions.visibilityOfElementLocated(dropZoneImages));
        uploadFile(path, imagesList.get(0), "image / png");
        waitForLoader(loader);
        uploadFile(path, imagesList.get(1), "image / png");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForLoader(loader);
        click(nextBtn);
        click(nextBtn);
    }

    public void fillPriceForm() throws InterruptedException {
         Thread.sleep(7000);
         hoverAndClickOnElement(selectAllCountriesBtn);
         click(nextBtn);
    }


    public void fillLegalAndSupportForm() throws InterruptedException {
        waitForLoader(loader);
        Thread.sleep(4000);
        hoverAndClickOnElement(getElementsByClassJs("i-validator-radio radio-isEncrypt", 1));
        sendKeys(supportEmail, "asdasd@asd.com");
        sendKeys(supportWebsiteUrl, "asdas.com");
        sendKeys(supportPhoneNumber, "999999999999");
        sendKeys(termsOfUseUrl, "asdasd.com");
        click(nextBtn);
    }


    public void clickOnSubmitBtn() throws InterruptedException {
        waitForLoader(loader);
        Thread.sleep(6000);
        click(submitBtn);
        waitForLoader(loader);
        click(okBtn);
    }

}
