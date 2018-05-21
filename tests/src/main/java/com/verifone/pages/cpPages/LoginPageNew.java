package com.verifone.pages.cpPages;


import com.verifone.pages.BasePage;
import org.openqa.selenium.By;


public class LoginPageNew extends BasePage {

    private final static String url = "";
    private final static String title = "Getting Started | Get Started | developer.verifone.com";

    private By username= By.id("username");
    private By password = By.id("password");
    private By toLoginPageBtn = By.partialLinkText("Log");
    private By loginBtn = By.id("btnPrimaryLogin");


    public LoginPageNew() {
        super(url, title);
        validateTitle();
    }

    public void loginPageCp(String userName, String pass) throws InterruptedException {
        click(toLoginPageBtn);
        sendKeys(username, userName);
        click(password);
        sendKeys(password, pass);
        click(loginBtn);

    }
}
