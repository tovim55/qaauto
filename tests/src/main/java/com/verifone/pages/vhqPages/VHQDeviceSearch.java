package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.verifone.utils.Assertions.assertTextContains;

public class VHQDeviceSearch extends BasePage
{
    private final static String url = "";
    private final static String title = "" ;

    public VHQDeviceSearch()
    {
        super(url, title);
    }

    private By serialNum = By.linkText("401-686-709");
    private By download = By.id("Download_HistroytabLi");
    private By divFirstRow = By.id("row0jqxgridDownloadJobProfil");

    String jobStatus = "Successful";

    public void deviceProfile( String packageName)
    {
        hoverAndClickOnElement(serialNum);
        testLog.info(driver.getCurrentUrl());
        waitForLoader(download);
        click(download);

        WebElement firstRow = getWebElement(divFirstRow, 500, ExpectedConditions.visibilityOfElementLocated(divFirstRow));
        testLog.info(firstRow.getText());
        assertTextContains(packageName, firstRow.getText());
        assertTextContains(jobStatus, firstRow.getText());

    }

}
