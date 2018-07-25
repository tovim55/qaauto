package com.verifone.pages.cpPages;


import com.verifone.infra.Company;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;


public class DevProfilePage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home#profile";
    private final static String title = "[QA] Developer Central | Profile";

    // Company info form:
    private By addCompaniInfoBtn = By.linkText("Add Company Information");
    private By companyName = By.xpath("//input[@name='name']");
    private By dunsNum = By.xpath("//input[@name='dunsNumber']");
    private By firstName = By.xpath("//input[@name='firstName']");
    private By lastName = By.xpath("//input[@name='lastName']");
    private By email = By.xpath("//input[@name='userEmail']");
    private By countryCode = By.xpath("//input[@name='countryCode']");
    private By countryTaxCode = By.xpath("//select[@name='taxCountryCode']");
    private By taxFormFile = By.className("default dz-message dz-clickable");
    private By checkboxAgreement = By.className("checkbox-material");
    private By submitBtn = By.xpath("//button[@class='btn btn-primary btn-raised submit-button']");
    private By agreeBtn = By.tagName("button");
    private String uploadFileClassName = "dz-default dz-message dz-clickable";


    // User info form:
    private By editBtn = By.xpath("//a[text()='Edit']");
    private By country = By.xpath("//select[@name='countryName']");
    private By address = By.xpath("//input[@name='addressLineOne']");
    private By city = By.xpath("//input[@name='city']");
    private By zipCode = By.xpath("//input[@name='zipCode']");
    private By contactNumber = By.xpath("//input[@name='phoneNumber']");
    private By saveBtn = By.id("saveBtn");
    private By membershipStatus = By.xpath("//span[@class='label label-warning visible-block']");


    public DevProfilePage() {
        super(url, title);
        navigate();
    }


    public void editUserInfo() throws InterruptedException {
        Thread.sleep(5000);
        click(editBtn);
        select(country, "CA");
        sendKeys(address, "hotel california");
        sendKeys(city, "city");
        sendKeys(zipCode, "zipCode");
        sendKeys(contactNumber, "123456789");
        click(saveBtn);
    }


    public void fillCompanyInfo(Company user) throws IOException, AWTException, InterruptedException {
        click(addCompaniInfoBtn);
        sendKeys(companyName, user.getCompanyName());
        select(country, user.getCountry());
        sendKeys(address, user.getAddress());
        sendKeys(city, user.getCity());
        sendKeys(zipCode, String.valueOf(user.getZipCode()));
        sendKeys(firstName, user.getFirstName());
        sendKeys(lastName, user.getLastName());
        sendKeys(countryCode, String.valueOf(user.getCountryCode()));
        sendKeys(contactNumber, String.valueOf(user.getContactNumber()));
        select(countryTaxCode, user.getCountryTaxCode());
        sendKeys(email, user.getUserName());
        uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\tax_file.docx",
                getElementsByClassJs(uploadFileClassName, 1));
        click(checkboxAgreement);
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.click(driver.findElements(agreeBtn).get(4)).perform();
        Thread.sleep(2000);
        click(submitBtn);

    }

    public String getMembershipStatus() {
        return getText(membershipStatus);
    }

}
