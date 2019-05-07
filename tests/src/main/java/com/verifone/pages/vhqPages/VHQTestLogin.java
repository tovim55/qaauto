package com.verifone.pages.vhqPages;

import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VHQTestLogin extends BasePage {
    private final static String url = "https://vhqtest.verifone.com/";
    private final static String title = "Login with Verifone Identity Server" ;

    public VHQTestLogin()
    {
        super(url, title);
        navigate();
    }

    private By email = By.cssSelector("input[class='form-control input-lg']");
    private By LoginVhqBtn = By.cssSelector("button[class='btn btn-login btn-block btn-lg'");
    private By veriIframe = By.id("veriPassFrame");
    private By passwordField = By.id("ipassword");
    private By LoginBtn = By.id("btnPrimaryLogin");

    public void LoginInVhq (User user)
    {

        waitForLoader(email);
        sendKeys(email, user.getUserName());
        click(LoginVhqBtn);
        ExpectedConditions.titleIs(title);
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(veriIframe);
        switchToIframe(veriIframe);
        sendKeys(passwordField, user.getPassword());
        driver.switchTo().defaultContent();
        click(LoginBtn);
    }
}
