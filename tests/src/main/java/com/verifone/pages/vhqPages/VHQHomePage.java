package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VHQHomePage extends BasePage
{
    private final static String url = "https://vhqtest.verifone.com/";
    private final static String title = "" ;

    public VHQHomePage()
    {
        super(url, title);
        navigate();
    }

    private By customer =  By.xpath("//p[contains(text(),'AppDirect2')]");
    private By mainMenu = By.xpath("//*[@id=\"mainMenuUl\"]");


    private List<WebElement> menu;

    public void verifyCustomer()
    {
        String customerName = "AppDirect2";
        ExpectedConditions.textToBe(customer, customerName);
        testLog.info(getText(customer));
    }

    public void verifyDownloadLibrary()
    {

    }
}
