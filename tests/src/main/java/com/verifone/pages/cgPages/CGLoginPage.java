package com.verifone.pages.cgPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CGLoginPage extends BasePage {



    private final static String url = BaseTest.envConfig.getWebUrl() + "docs/overview/get-started/";
    private final static String title = "Commerce gateway portal";

    private By toLoginPageBtn = By.partialLinkText("Log");
    private By username= By.id("username");
    private By password = By.id("ipassword");
    private By loginBtn = By.id("btnPrimaryLogin");
    private By panel = By.xpath("//*[@class='panel-body']");

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
    public void inputUserName(String UserName){
        sendKeys(username, UserName);
        click(panel);
    }
    public void inputPassword(String Pwd){
        WebElement iFrame = BasePage.driver.findElement(By.id("veriPassFrame"));
        BasePage.driver.switchTo().frame(iFrame);
        BasePage.driver.findElement(password).sendKeys(Pwd);
        driver.switchTo().defaultContent();
        click(panel);
//        sendKeys(password, Pwd);
    }
    public void clickLoginLink(){
        click(toLoginPageBtn);
    }
    public void clickLoginBtn(){
        click(loginBtn);
    }
}
