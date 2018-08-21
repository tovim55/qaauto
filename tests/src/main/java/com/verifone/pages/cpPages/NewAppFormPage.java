package com.verifone.pages.cpPages;


import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;


public class DevHomePage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "[QA] Developer Central | Home";

    private By connectWithCompanyBtn = By.xpath("//a[text()='Connect with Company']");
    //    private By message = By.className("section-item col-xs-12 col-md-12 get-started");
    private By message = By.xpath("Get started now");
    private By createAppBtn = By.linkText("Create an App");


    public DevHomePage() {
        super(url, title);
//        navigate();
    }

    public void createAppBtn() {
        click(createAppBtn);

    }

    public void clickconnectWithCompany() {
        click(connectWithCompanyBtn);

    }

}
