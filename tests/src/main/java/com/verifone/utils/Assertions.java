package com.verifone.utils;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.infra.SeleniumUtils;

import java.awt.*;
import java.io.IOException;

import static com.verifone.pages.BasePage.testLog;
import static org.testng.AssertJUnit.assertEquals;

public class Assertions {

    public static ExtentTest testLog;

    public static void assertTextContains(String expectedResult, String actual) {
//        testLog.info("Test: text should contain: " + expectedResult + " Actual text: " + actual);
        if (!actual.contains(expectedResult)) {
            org.testng.Assert.fail("Text expected: " + expectedResult + " Was: " + actual);
        }
    }

    public static void assertTextEqual(String expectedResult, String actual) {
//        testLog.info("Test: text should equal: " + expectedResult + " Actual text: " + actual);
        assertEquals(expectedResult, actual);
    }

    public static boolean compareValue(String ExpectedRes, String ActualRes, String Desc, ExtentTest testLog) throws AWTException, InterruptedException, IOException, Exception {
        boolean TestPassFlag;
        String capScreenShootPath;
        boolean currentResult = ActualRes.contains(ExpectedRes);
        if (currentResult == true) {
            testLog.pass( Desc + " " + ExpectedRes + "...: Succesfull");
            TestPassFlag = true;
        } else {
            TestPassFlag = false;
            testLog.error( Desc + " " + ActualRes + ". Expected: " + ExpectedRes);
            capScreenShootPath = SeleniumUtils.getScreenshot();
            testLog.info( "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
            testLog.info( "Test Failed !!! - Snapshot below: " + testLog.addScreenCaptureFromPath(capScreenShootPath));
        }
        return TestPassFlag;
    }
}
