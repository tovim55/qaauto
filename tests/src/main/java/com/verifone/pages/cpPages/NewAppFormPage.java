package com.verifone.pages.cpPages;


import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.appUtils.Application;
import org.openqa.selenium.By;

import java.awt.*;
import java.io.IOException;


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




    public NewAppFormPage() {
        super(url, title);
//        navigate();
    }

    public String fillGetStartedForm(Application app) throws InterruptedException {
        Thread.sleep(1000);
        sendKeys(this.appName, app.getAppName());
        click(version);
        click(generateIdBtn);
        Thread.sleep(2000);
        sendKeys(version, app.getVersion());
        sendKeys(this.description5Words, app.getDescription5Words());
        sendKeys(this.description, app.getDescription());
        select(category, "b44ae8fc-5163-11e7-a459-12a5177fe69f");
        String generatedId = getElementsByClassJs(id, 1).getText();
        click(nextBtn);
        return generatedId.split("\n")[0];

    }

    public void fillUploadPackageForm(String appPath) throws AWTException, InterruptedException, IOException {
        waitForLoader(loader);
        uploadFile(appPath, getElementsByClassJs(uploadFileClassName, 0));
        waitForLoader(loader);
        click(nextBtn);
    }

    public void fillAppIconScreenshots(String path) throws AWTException, InterruptedException, IOException {
        waitForLoader(loader);
        uploadFile(path, getElementsByClassJs(uploadFileClassName, 0));
        waitForLoader(loader);
        uploadFile(path, getElementsByClassJs(uploadFileClassName, 1));
        waitForLoader(loader);
        click(nextBtn);
    }

    public void fillPriceForm() throws InterruptedException {
         Thread.sleep(6000);
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
//        Thread.sleep(2000);
        click(submitBtn);
        waitForLoader(loader);
        click(okBtn);
    }

}
