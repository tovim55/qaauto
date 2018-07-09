package com.verifone.pages.cpPages;


import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;


public class VerifoneAccountLoginPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() +  "docs/overview/get-started/";
    private final static String title = "Getting Started | Get Started | developer.verifone.com";

    private By toLoginPageBtn = By.partialLinkText("Log");
    private By firstUsername= By.id("username");
    private By firstPass= By.id("password");
    private By username= By.id("Username");
    private By password = By.id("Passwd");
//    private By toLoginPageBtn = By.partialLinkText("Log");
    private By loginBtn = By.id("signIn");


    public VerifoneAccountLoginPage() {
        super(url, title);
        navigate();
        validateTitle();
    }

    public void login(User user) {
        click(toLoginPageBtn);
        sendKeys(firstUsername, user.getUserName());
        click(firstPass);
        String userName = user.getUserName().split("@")[0];
        sendKeys(username, userName);
        click(password);
        sendKeys(password, user.getPassword());
        click(loginBtn);

    }

//    public void clickOmLoginBtn() {
//        click(toLoginPageBtn);
//    }
}
