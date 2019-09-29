package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignGroupPage;
import com.verifone.pages.mpPages.CBAAssignPage;
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
    // private DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy ");
    // private String currentDate = dateFormat.format(new Date());

    public VHQDeviceSearch() {
        super(url, title);
    }

    //private By serialNum = By.linkText("401-686-709");
    private By linkSerialNumber = By.xpath("//*[@id='row0Devicejqxgrid']/child::div[2]//div[1]");
    private By download = By.id("Download_HistroytabLi");
    private By divFirstRow = By.id("row0jqxgridDownloadJobProfil");
    private By divSecondRow = By.id("row1jqxgridDownloadJobProfil");
    private By deviceMode = By.id("btncomputedStatus");
    private By btnRefresh = By.id("btnRefresh");
    private By setDeviceStateActive = By.xpath("//*[text()='Active']");
    private By btnConfirmState = By.id("btnChangStatusYes");
    private By btnInfo = By.id("infoBtnOk");

    public void deviceProfile() {
        hoverAndClickOnElement(linkSerialNumber);
        testLog.info(driver.getCurrentUrl());
        waitForLoader(download);
        click(download);

        waitForLoader(deviceMode);
        System.out.println("text of the button :" + getText(deviceMode));
        if (getText(deviceMode).equals("Active")) {
            click(btnRefresh);
        } else {
            click(deviceMode);
            click(setDeviceStateActive);
            waitForLoader(btnInfo);
            click(btnConfirmState);
            waitForLoader(btnInfo);
            click(btnInfo);
            click(btnRefresh);
        }
        waitForLoader(deviceMode);
    }

    public void searchJobStatus(String packageName, String appStatus) {
        if (appStatus.equals("UNINSTALL")) {
            testLog.info("------ Unsubscribe(UNINSTALL) :" + packageName + " App ------");

            WebElement firstRow = getWebElement(divFirstRow, 500, ExpectedConditions.visibilityOfElementLocated(divFirstRow));

            testLog.info(" ----- packageName : " + packageName + "  ---- Text Contains in : ----  " + firstRow.getText() + " -----");
            assertTextContains(packageName, firstRow.getText());

            testLog.info(" ----- Application Status : " + appStatus + "  ---- Text Contains in : ----  " + firstRow.getText() + " -----");
            assertTextContains(appStatus, firstRow.getText());

            testLog.info(" ----- Package Unassigned date : " + CBAAccount.jobCreatedOnUnsubscription + "  ---- Text Contains in : ----  " + firstRow.getText() + " -----");
            assertTextContains(CBAAccount.jobCreatedOnUnsubscription, firstRow.getText());

        } else {
            testLog.info("------ Subscribe(INSTALL) :" + packageName + " App ------");

            WebElement secondRow = getWebElement(divSecondRow, 500, ExpectedConditions.visibilityOfElementLocated(divSecondRow));

            testLog.info(" ----- packageName : " + packageName + " ---- Text Contains in : ---- " + secondRow.getText() + " -----");
            assertTextContains(packageName, secondRow.getText());

            testLog.info(" ----- Application Status : " + appStatus + "  ---- Text Contains in : ----  " + secondRow.getText() + " -----");
            assertTextContains(appStatus, secondRow.getText());

            testLog.info(" ----- Package assigned date : " + CBAAssignPage.jobCreatedOnSubscription + "  ---- Text Contains in : ----  " + secondRow.getText() + " -----");
            assertTextContains(CBAAssignPage.jobCreatedOnSubscription, secondRow.getText());
        }

    }

}
