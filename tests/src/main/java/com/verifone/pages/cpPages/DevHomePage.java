package com.verifone.pages.cpPages;


import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;


public class DevHomePage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "[QA] Developer Central | Home";

    private By connectWithCompanyBtn = By.xpath("//a[text()='Connect with Company']");
    private By message = By.xpath("Get started now");
    private By createAppBtn = By.xpath("//a[text()='Create an App']");


    public DevHomePage() {
        super(url, title);
        waitForLoader(loader);
    }

    public void createAppBtn() throws InterruptedException {
        Thread.sleep(8000);
        click(createAppBtn);
        Thread.sleep(3000);

    }

    public void clickconnectWithCompany() {
        click(connectWithCompanyBtn);

    }

}
