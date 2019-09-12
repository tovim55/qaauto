package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.verifone.utils.Assertions.assertTextContains;

public class VHQDeviceSearch extends BasePage {
    private final static String url = "";
    private final static String title = "";
    private DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy ");
    private String currentDate = dateFormat.format(new Date());

    public VHQDeviceSearch() {
        super(url, title);
    }

    //private By serialNum = By.linkText("401-686-709");
    private By linkSerialNumber = By.xpath("//*[@id='row0Devicejqxgrid']/child::div[2]//div[1]");
    private By download = By.id("Download_HistroytabLi");
    private By divFirstRow = By.id("row0jqxgridDownloadJobProfil");
    private By divSecondRow = By.id("row1jqxgridDownloadJobProfil");

    public void deviceProfile() {
        hoverAndClickOnElement(linkSerialNumber);
        testLog.info(driver.getCurrentUrl());
        waitForLoader(download);
        click(download);
    }

    public void searchJobStatus(String packageName, String appStatus) {
        if (appStatus.equals("UNINSTALL")) {
            testLog.info("------ Unsubscribe(UNINSTALL) :" + packageName + " App ------");
            WebElement firstRow = getWebElement(divFirstRow, 500, ExpectedConditions.visibilityOfElementLocated(divFirstRow));
            testLog.info(firstRow.getText());
            assertTextContains(packageName, firstRow.getText());
            assertTextContains(currentDate, firstRow.getText());
            assertTextContains(appStatus, firstRow.getText());
        } else {
            testLog.info("------ Subscribe(INSTALL) :" + packageName + " App ------");
            WebElement secondRow = getWebElement(divSecondRow, 500, ExpectedConditions.visibilityOfElementLocated(divSecondRow));
            testLog.info(secondRow.getText());
            assertTextContains(packageName, secondRow.getText());
            assertTextContains(currentDate, secondRow.getText());
            assertTextContains(appStatus, secondRow.getText());
        }

    }

}
