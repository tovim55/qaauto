package com.verifone.pages.cgPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;

public class CGLoginPage extends BasePage {



    private final static String url = BaseTest.envConfig.getWebUrl() + "docs/overview/get-started/";
    private final static String title = "Commerce gateway portal";

    private By toLoginPageBtn = By.partialLinkText("Log");
    private By username= By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.id("btnPrimaryLogin");

    public CGLoginPage() {
        super(url, title);
    }


    public void doLogin(){
        click(toLoginPageBtn);
        sendKeys(username, "vfiqadevadmin@getnada.com");
        click(password);
        sendKeys(password, "Veri1234");
        click(loginBtn);
    }



}
