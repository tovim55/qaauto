package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VHQDownloadLibrary extends BasePage
{
    private final static String url = "";
    private final static String title = "" ;

    public VHQDownloadLibrary()
    {
        super(url, title);
    }

    private By ShowHide = By.id("btnShowHide");
    private By folderName = By.id("jqxgridDownloadlib-check-FolderName");
    private By file = By.id("jqxgridDownloadlib-check-FileName");
    private By fileSize = By.id("jqxgridDownloadlib-check-FileSizeInMB");
    private By fileVersion = By.id("jqxgridDownloadlib-check-FileVersion");
    private By packageCategory = By.id("jqxgridDownloadlib-check-PackageTypeString");
    private By btnOk = By.id("btnOkShowHide");
    private By updated = By.xpath("//*[@id=\"columntablejqxgridDownloadlib\"]/div[16]/div/div[1]");
    private By updatedOn = By.xpath("//*[@id=\"columntablejqxgridDownloadlib\"]/div[16]/div/div[2]/div[2]");


    private By divPackage = By.xpath("//div[starts-with(@id,'row')]");
    private By divPackage1 = By.xpath("//*[@id=\"row0jqxgridDownloadlib\"]");


    public void verifyPackageExist(String appName) {
        click(ShowHide);
        ExpectedConditions.elementToBeClickable(folderName);
        click(folderName);
        click(file);
        click(fileSize);
        click(fileVersion);
        click(packageCategory);
        ExpectedConditions.elementToBeClickable(btnOk);
        click(btnOk);
        waitForLoader(updated);
        click(updated);
        waitForLoader(updatedOn);
        ExpectedConditions.elementToBeClickable(updatedOn);
        click(updatedOn);
        waitForLoader(divPackage1);

        List<WebElement> divRows = getWebElements(divPackage, 500, ExpectedConditions.presenceOfElementLocated(divPackage));
        for (WebElement elem: divRows)
        {
            if(elem.getText().contains(appName)) {
                testLog.info("Package " + appName + " is downloaded");
                testLog.info(elem.getText());
                break;
            }

        }

    }
}
