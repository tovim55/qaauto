package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VHQDownloadLibrary extends BasePage
{
    private final static String url = "https://vhqtest.verifone.com/#device/downloadLibrary/downloadLibrary/downloads";
    private final static String title = "" ;

    public VHQDownloadLibrary()
    {
        super(url, title);
        navigate();
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

    private By divPackage1 = By.xpath("//*[@id=\"row0jqxgridDownloadlib\"]");
    private By divPackage2 = By.xpath("//*[@id=\"row1jqxgridDownloadlib\"]");


    WebElement packageName1;
    WebElement packageName2;


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
        packageName1 = getWebElement(divPackage1, 500, ExpectedConditions.presenceOfElementLocated(divPackage1));
        if (packageName1.getText().contains(appName))
            testLog.info("Package Uninstall is downloaded");
        else
            testLog.info("Package is not appeared in VHQ");

        packageName2 = getWebElement(divPackage2, 500, ExpectedConditions.presenceOfElementLocated(divPackage2));
        if (packageName2.getText().contains(appName))
            testLog.info("Package Install is downloaded");
        else
            testLog.info("Package is not appeared in VHQ");

    }
}
