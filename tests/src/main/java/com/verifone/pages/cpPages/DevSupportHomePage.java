package com.verifone.pages.cpPages;


import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;

import static org.testng.Assert.assertTrue;


public class DevSupportHomePage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "[QA] Developer Central | Home";

    private By message = By.xpath("Get started now");
    private By getAppsNum = By.className("counter");
    private By usernameBtn = By.className("username");
    private By userBtn = By.id("users");
    private By appTable = By.xpath("//*[@class=\"section-item col-xs-12 col-md-12\"][0]");


    public DevSupportHomePage() {
        super(url, title);
//        navigate();
    }

    public void validateAppsNum() {
        assertTrue(0 < Integer.parseInt(getText(getAppsNum)));
    }

    public void goToUsersPage() {
        waitSimple(5000);
        hoverAndClickOnElement(usernameBtn);
        click(userBtn);

    }

    private void waitSimple(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void appsTable(){
//        getWebElements();

    }



}
