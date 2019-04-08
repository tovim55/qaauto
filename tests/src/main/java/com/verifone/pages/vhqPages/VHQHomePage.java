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
    private By devManag = By.xpath("//*[@id=\"device\"]");
    private By managDownl= By.xpath("//*[@id=\"downloads\"]");
    private By downlLib = By.xpath("//*[@id=\"downloadLibrarysublink\"]");
    private By ShowHide = By.id("btnShowHide");


    WebElement packageName1;
    WebElement packageName2;

    public void verifyCustomer()
    {
        String customerName = "AppDirect2";
        ExpectedConditions.textToBe(customer, customerName);
        testLog.info("Customer name is " + getText(customer));
    }

    public void verifyDownloadLibrary() throws InterruptedException {
        ExpectedConditions.elementToBeClickable(devManag);
        click(devManag);
        ExpectedConditions.elementToBeClickable(managDownl);
        hoverAndClickOnElement(managDownl);
        ExpectedConditions.elementToBeClickable(downlLib);
        click(downlLib);
        waitForLoader(ShowHide);
    }


}
