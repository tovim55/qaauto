package com.verifone.pages.cpPages;

import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;

import java.util.Random;

import static org.apache.commons.lang.StringUtils.leftPad;

public class CreateMerchantPage extends BasePage {
    private final static String url = BaseTest.envConfig.getWebUrl();
    private final static String title = "[QA] Estate Manager | Home";


    private By username = By.id("username");
    //    private By username= By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.id("btnPrimaryLogin");
    private By merchantsBtn = By.xpath("//*[@id=\"merchants\"]");
    private By addMerchantsBtn = By.linkText("Add Merchant");
    private By name = By.name("name");
    private By industryCode = By.name("industryCode");
    private By mIDInput = By.name("mID");
    private By addressLineOne = By.name("addressLineOne");
    private By city = By.name("city");
    private By state = By.name("state");
    private By phoneNumber = By.name("phoneNumber");
    private By USChoose = By.className("dropdownjs");
    private By USState = By.xpath("//ul/li[@value=\"US\"]");
    private By email = By.name("email");
    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By adminEmail = By.name("adminEmail");
    private By zipCode = By.name("zipCode");
    private By submitButton = By.xpath("//button[@class='btn btn-primary btn-raised submit-button']");

    private By connectWithCompanyBtn = By.xpath("//a[text()='Connect with Company']");
    //    private By message = By.className("section-item col-xs-12 col-md-12 get-started");
    private By message = By.xpath("Get started now");


    public CreateMerchantPage() {
        super(url, title);
        navigate();
    }

    public void clickconnectWithCompany() {
        click(connectWithCompanyBtn);

    }

    public void login(User user) {
        sendKeys(username, user.getUserName());
        click(password);
        sendKeys(password, user.getPassword());
        click(loginBtn);

    }

    public void merchantClick() {
        waitSimple(12000);
        click(merchantsBtn);
        waitSimple(7000);
        click(addMerchantsBtn);

    }

    private void waitSimple(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String fillForm() {
        waitSimple(15000);
        click(name);
        sendKeys(name, getRandomName());
        click(industryCode);
        sendKeys(industryCode, "1818");
        click(mIDInput);
        sendKeys(mIDInput, getRandomName());
        click(addressLineOne);
        sendKeys(addressLineOne, getRandomName());
        click(city);
        sendKeys(city, "NewYork");
        click(state);
        sendKeys(state, getRandomName());
        click(zipCode);
        sendKeys(zipCode, String.valueOf(getRandomNum() + 1600));
        click(phoneNumber);
        sendKeys(phoneNumber, String.valueOf(getRandomNum() + 1600));
        click(email);
        sendKeys(email, getRandomName() + "@gmail.com");
        click(firstName);
        sendKeys(firstName, "Test");
        click(lastName);
        sendKeys(lastName, "Auto");
        click(adminEmail);
        String emailAdmin = getRandomName() + "@getnada.com";
        sendKeys(adminEmail, emailAdmin);
        waitSimple(2000);
        click(submitButton);
        waitSimple(5000);
        return emailAdmin;
    }

    public String getRandomName() {
        return RandomStringUtils.randomAlphanumeric(8).toUpperCase();
    }

    public int getRandomNum() {
        Random rand = new Random();
        int x = rand.nextInt(100);
        return x;
    }


}
