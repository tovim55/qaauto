package com.verifone.pages.mpPages;

import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

public class CBALoginPage extends BasePage
{
    private final static String url = "";
    private final static String title = "Login with Verifone Identity Server";

    private By emailField = By.id("username");
    private By veriIframe = By.id("veriPassFrame");
    private By passwordField = By.id("ipassword");
    private By LoginBtn = By.id("btnPrimaryLogin");


    public CBALoginPage()
    {
        super(url, title);
    }

    public void LogInToCBAAccount (User user)
    {
        sendKeys(emailField, user.getUserName());
        switchToIframe(veriIframe);
        System.out.println(user.getPassword());
        sendKeys(passwordField, user.getPassword());
        driver.switchTo().defaultContent();
        click(LoginBtn);
    }

}

