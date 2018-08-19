package com.verifone.utils;

import com.aventstack.extentreports.ExtentTest;

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

    public static void assertTextEqual(boolean expectedResult, boolean actual) {
//        testLog.info("Test: text should equal: " + expectedResult + " Actual text: " + actual);
        assertEquals(expectedResult, actual);
    }

}
