package com.verifone.utils;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.infra.SeleniumUtils;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class Assertions {

    public static ExtentTest testLog;

    public static void assertTextContains(String expectedResult, String actual) {
        if (!actual.contains(expectedResult)) {
            org.testng.Assert.fail("Text expected: " + expectedResult + " Was: " + actual);
        }
    }

    public static void assertTextEqual(String expectedResult, String actual) {
        assertEquals(expectedResult, actual);
    }

    public static void assertTextEqual(boolean expectedResult, boolean actual) {
        assertEquals(expectedResult, actual);
    }





    public static boolean compareValue(String ExpectedRes, String ActualRes, String Desc, ExtentTest testLog, WebDriver driver) throws Exception {
        boolean TestPassFlag;
        String capScreenShootPath;
        boolean currentResult = ActualRes.contains(ExpectedRes);
        if (currentResult == true) {
            testLog.pass(Desc + " " + ExpectedRes + "...: Succesfull");
            TestPassFlag = true;
        } else {
            TestPassFlag = false;
            testLog.error(Desc + " " + ActualRes + ". Expected: " + ExpectedRes);
            capScreenShootPath = SeleniumUtils.getScreenshot(driver);
            testLog.info("Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info("Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }
        return TestPassFlag;
    }
    public static boolean compareNumber(int ExpectedRes, int ActualRes, String Desc, ExtentTest testLog, WebDriver driver) throws  Exception {
        boolean TestPassFlag;
        String capScreenShootPath;

        if (ExpectedRes == ActualRes) {
            testLog.pass(Desc + " " + ExpectedRes + "...: Succesfull");
            TestPassFlag = true;
        } else {
            TestPassFlag = false;
            testLog.error(Desc + " " + ActualRes + ". Expected: " + ExpectedRes);
            capScreenShootPath = SeleniumUtils.getScreenshot(driver);
            testLog.info("Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info("Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }
        return TestPassFlag;
    }
    public static boolean compareBoolean(Boolean ExpectedRes, Boolean ActualRes, String Desc, ExtentTest testLog, WebDriver driver) throws Exception {
        boolean TestPassFlag;
        String capScreenShootPath;
        if (ActualRes == ExpectedRes) {
            testLog.pass(Desc + " " + ExpectedRes + " as expected");
            TestPassFlag = true;
        } else {
            TestPassFlag = false;
            testLog.error(Desc + " " + ActualRes + ". Expected: " + ExpectedRes);
            capScreenShootPath = SeleniumUtils.getScreenshot(driver);
            testLog.info("Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info("Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }
        return TestPassFlag;
    }
}
