package com.verifone.pages.marketPlacePages;

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

    public void LogInToCBAAccount ()
    {
        sendKeys(emailField, "verif563@gmail.com");
        switchToIframe(veriIframe);
        sendKeys(passwordField, "Veri1234");
        driver.switchTo().defaultContent();
        click(LoginBtn);
    }
}