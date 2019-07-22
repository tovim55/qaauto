package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VHQHomePage extends BasePage
{
    //private final static String url = "https://vhqtest.verifone.com/";
    private final static String url = "https://qa.mumbai.verifonehq.net";
    private final static String title = "" ;
    //private final static String customerName = "AppDirect2";
    private final static String customerName = "TestOrg1_VHQ5";
    private final static String deviceSN = "401-686-709";

    public VHQHomePage()
    {
        super(url, title);
        navigate();
    }

    /////Device Management/////
    private By customer =  By.xpath("//p[@data-blind='text:customerFullName']");
    private By devManag = By.xpath("//*[@id='device']");
    private By managDownl= By.xpath("//*[@id=\"downloads\"]");
    private By downlLib = By.xpath("//*[@id=\"downloadLibrarysublink\"]");
    private By ShowHide = By.id("btnShowHide");

    /////Device Search/////
    private By devSearch = By.xpath("//*[@class='icon-device-search-filter']");
    //private By selectAttributes = By.xpath("//div[@id='deviceAttributDDL_chosen']");
    private By selectDeviceAttribute = By.xpath("//select[@id='deviceAttributDDL']");
    private By selectAttributeName = By.xpath("//select[@id='ddlAttrName']");
    private By contralValue = By.xpath("//input[@id='txtAttrValue']");
    private By btnAdd = By.xpath("//a[@id='btnAddAttr']");
    private By btnApplyFilter = By.xpath("//button[@id='btnApplyFilter']");

    public void verifyCustomer()
    {
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

    public void deviceSearch() throws InterruptedException {
        ExpectedConditions.elementToBeClickable(devSearch);
        click(devSearch);
        //hoverAndClickOnElement(selectAttributes);
        select(selectDeviceAttribute, "SerialNumber");
        select(selectAttributeName, "27");
        sendKeys(contralValue, deviceSN);
        click(btnAdd);
        ExpectedConditions.elementToBeClickable(btnApplyFilter);
        click(btnApplyFilter);


    }




}
