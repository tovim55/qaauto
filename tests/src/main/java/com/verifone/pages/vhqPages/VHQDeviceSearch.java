package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignGroupPage;
import com.verifone.pages.mpPages.CBAAssignPage;
import groovy.transform.Undefined;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.velocity.app.event.implement.EscapeXmlReference;
import org.apache.xalan.templates.ElemApplyImport;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.verifone.utils.Assertions.assertTextContains;
import static com.verifone.utils.Assertions.compareValue;

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
    private By btnScroll = By.xpath("//*[@id = 'jqxScrollBtnDownverticalScrollBarjqxgridDownloadJobProfil']");

    private static String getRowDetails = "";
    //private static boolean TestPassFlag = false;
    DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
    //private static String TestFlagRow = "true";

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

    public void validateJobInstall(String packageName, String deviceStatus, String jobCreatedOnSubscription) throws Exception {
        // scroll down the web page at the bottom of the page.
        scrollToHeight();
        testLog.info("-------- Start Time : " + getDownloadScheduleTime() + " -------");

        // following loop used to get the row value, verify against the date, job name, package name & scroll
        int i = 0;
        int scrollCounter = 1;
        String TestFlagRow = "true";
        boolean TestPassFlag = false;
        for (; i < 50; i++) {
            if (TestFlagRow.equals("true")) {
                testLog.info(" ------ Get the details of row index 0 : " + getRowDetails + " -----");
                System.out.println("if");
                TestFlagRow = "false";
                Thread.sleep(1000);
                getRowDetails = getText(divFirstRow);
                System.out.println("Details of row : " + getText(divFirstRow));
            } else if (!getRowDetails.equals(getText(divSecondRow))) {
                System.out.println("else");
                testLog.info(" ------ Get the details of row index 1 : " + getRowDetails + " -----");
                getRowDetails = getText(divSecondRow);
                System.out.println("Details of row : " + getText(divSecondRow));
            }

            testLog.info(" ------ Text expected : " + jobCreatedOnSubscription + " -- Was : " + getRowDetails + " -----");
            testLog.info(" ------ Text expected : " + packageName + " -- Was : " + getRowDetails + " -----");
            testLog.info(" ------ Text expected : " + deviceStatus + " -- Was : " + getRowDetails + " -----");
            System.out.println("updated date inside the loop " + jobCreatedOnSubscription);

            if (assertRowContains(jobCreatedOnSubscription, getRowDetails) & assertRowContains(packageName, getRowDetails)) {
                testLog.info(" ------ Condition true :  Date & Package name  -----");
                System.out.println("Condition true :  Date & Package name");
                Thread.sleep(500);
                if (isContain(getRowDetails, deviceStatus)) {
                    System.out.println("Job created successfully!");
                    testLog.info(" -------- VHQ : Package (" + deviceStatus + ") Job created successfully! -------- ");
                    TestPassFlag = true;
                    click(btnRefresh);
                    waitForLoader(btnRefresh);
                    break;
                }
            }

            //following condition verify the count of round trip.
            if (i == 49 & scrollCounter != 4) {
                testLog.info("-------- Reset the value of loop & increment the counter ------- ");
                //System.out.println("counter :" + scrollCounter);

                //Increment minute by one if current time is not find in the list of rows.
                jobCreatedOnSubscription = incrementTimeByOneMinute(scrollCounter, dateFormat, jobCreatedOnSubscription);
                System.out.println("Updated time : " + jobCreatedOnSubscription);
                i = 0;
                scrollCounter++;
                TestFlagRow = "true";
                click(btnRefresh);
                waitForLoader(btnRefresh);
            } else {
                testLog.info("----- Scroll the Page -----");
                click(btnScroll);
                Thread.sleep(1000);
            }
        }

        testLog.info("-------- End Time : " + getDownloadScheduleTime() + " -------");

        //Fail the test if value of TestPassFlag is false
        Assert.assertTrue(TestPassFlag);
    }

    // This method update the minute by 1 if the current time is not available in the list of rows.
    private static String incrementTimeByOneMinute(int scrollTime, DateFormat dateFormat, String jobCreatedOnSubscription) throws Exception {
        testLog.info("----- Current Time : " + jobCreatedOnSubscription + " -----");
        Date d = dateFormat.parse(jobCreatedOnSubscription);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        if (scrollTime == 1) {
            //System.out.println("inside" + scrollTime);
            cal.add(Calendar.MINUTE, -1);
            jobCreatedOnSubscription = dateFormat.format(cal.getTime());
        } else if (scrollTime == 2) {
            cal.add(Calendar.MINUTE, 2);
            jobCreatedOnSubscription = dateFormat.format(cal.getTime());
        }

        testLog.info("----- Date & Time verify : " + jobCreatedOnSubscription + " -----");
        return jobCreatedOnSubscription;
    }
}
