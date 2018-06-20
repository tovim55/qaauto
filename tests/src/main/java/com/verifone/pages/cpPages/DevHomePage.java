package com.verifone.pages.cpPages;


import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;


public class DevHomePage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "[QA] Developer Central | Home";

    private By connectWithCompanyBtn = By.className("btn btn-primary btn-raised");
    private By message = By.className("section-item col-xs-12 col-md-12 get-started");


    public DevHomePage() {
        super(url, title);
        navigate();
    }

    public void clickconnectWithCompany() {
        click(connectWithCompanyBtn);

    }

}
