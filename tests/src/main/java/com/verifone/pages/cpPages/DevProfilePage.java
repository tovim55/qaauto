package com.verifone.pages.cpPages;


import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;


public class DevProfilePage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home#profile";
    private final static String title = "[QA] Developer Central | Profile";

    private By addCompaniInfoBtn = By.className("btn btn-raised btn-primary pull-right add-company");
    private By editBtn = By.className("pull-right edit-user");
    private By country = By.className("fakeinput form-control i-validator select-countryName empty");
    private By address = By.className("form-control i-validator-text input-addressLineOne");
    private By city = By.className("form-control i-validator-text input-city");
    private By zipCode= By.className("form-control i-validator-text input-zipCode");
    private By  contactNumber = By.className("form-control i-validator-text input-phoneNumber");
    private By  saveBtn = By.id("saveBtn");


    public DevProfilePage() {
        super(url, title);
        navigate();
    }


    public void fillDetails() {
        click(addCompaniInfoBtn);
        click(editBtn);
        sendKeys(country, "Canada");
        sendKeys(address, "asd");
        sendKeys(city, "sdsdf");
        sendKeys(zipCode, "123456");
        sendKeys(contactNumber, "123456");
        click(saveBtn);
    }

}
