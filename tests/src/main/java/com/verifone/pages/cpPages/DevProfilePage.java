package com.verifone.pages.cpPages;


import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


public class DevProfilePage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home#profile";
    private final static String title = "[QA] Developer Central | Profile";

    // Company info form:
    private By addCompaniInfoBtn = By.xpath("//a[text()='Add Company Information']");
    private By companyName = By.xpath("//input[@name='name']");
    private By dunsNum = By.xpath("//input[@name='dunsNumber']");
    private By firstName = By.xpath("//input[@name='firstName']");
    private By lastName = By.xpath("//input[@name='lastName']");
    private By email = By.xpath("//input[@name='userEmail']");
    private By countryCode = By.xpath("//input[@name='countryCode']");
    private By countryTaxCode = By.xpath("//select[@name='taxCountryCode']");
    private By taxFormFile = By.className("default dz-message dz-clickable");
    private By checkboxAgreement = By.xpath("//input[@class='checkbox-agreement']");
    private By submitBtn = By.xpath("//button[@class='btn btn-primary btn-raised submit-button']");


    // User info form:
    private By editBtn = By.xpath("//a[text()='Edit']");
    private By country = By.xpath("//select[@name='countryName']");
    private By address = By.xpath("//input[@name='addressLineOne']");
    private By city = By.xpath("//input[@name='city']");
    private By zipCode = By.xpath("//input[@name='zipCode']");
    private By contactNumber = By.xpath("//input[@name='phoneNumber']");
    private By saveBtn = By.id("saveBtn");


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


    public void fillCompanyInfo(User user){
        click(addCompaniInfoBtn);
        sendKeys(companyName, "hola");
        select(country, "CA");
        sendKeys(address, "hotel california");
        sendKeys(city, "city");
        sendKeys(zipCode, "zipCode");
        sendKeys(firstName, user.getFirstName());
        sendKeys(lastName, user.getLastName());
        sendKeys(countryCode, "123");
        sendKeys(contactNumber, "123456789");
        select(countryTaxCode, "CA");
        sendKeys(email, user.getUserName());
        getWebElements("C:\\Users\\yonir1\\Desktop\\cpp projects\\CPP-3712\\feqaautomation\\tests\\src\\main\\resources\\a.txt");
//        getWebElements(taxFormFile, 10).get(1).sendKeys("C:\\Users\\yonir1\\Desktop\\cpp projects\\CPP-3712\\feqaautomation\\tests\\src\\main\\resources\\a.txt");
//        sendKeys(taxFormFile, "C:\\Users\\yonir1\\Desktop\\cpp projects\\CPP-3712\\feqaautomation\\tests\\src\\main\\resources\\a.txt");
        click(checkboxAgreement);
        click(submitBtn);


    }

}
