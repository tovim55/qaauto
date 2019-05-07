package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CBAHomePage extends BasePage

{
    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Test Verifone US  | Verifone" ;

    private By LogIn = By.xpath("//*[@id=\"primaryNav\"]/div/nav/ul/li[3]/ul/li/a");


    public CBAHomePage()
    {
        super(url, title);
        navigate();
    }

     public void clickOnLogInLink ()
     {
         click(LogIn);
     }
}
