package com.verifone.pages.cpPages;


import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {

//    private final static String url = BaseTest.envConfig.getWebUrl() + "docs/overview/get-started/";
    private final static String url = "";
    private final static String title = "Getting Started | Get Started | developer.verifone.com";

    private By username= By.id("username");
    private By password = By.id("password");
    private By toLoginPageBtn = By.partialLinkText("Log");
    private By loginBtn = By.id("btnPrimaryLogin");


    public LoginPage() {
        super(url, title);
        navigate();
//        validateTitle();
    }

    public void loginPageCp(User user) {
        sendKeys(username, user.getUserName());
        click(password);
        sendKeys(password, user.getPassword());
        click(loginBtn);

    }

    public void clickOmLoginBtn() {
        click(toLoginPageBtn);
    }
}
