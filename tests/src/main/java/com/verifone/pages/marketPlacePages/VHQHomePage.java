package com.verifone.pages.marketPlacePages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public void verifyCustomer()
    {
        String customerName = "AppDirect2";
        ExpectedConditions.textToBe(customer, customerName);
        testLog.info(getText(customer));
    }
}
