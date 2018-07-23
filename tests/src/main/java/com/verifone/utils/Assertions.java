package com.verifone.utils;

import static org.testng.AssertJUnit.assertEquals;

public class Assertions {


    public static void assertTextContains(String expectedResult, String actual) {
        if (!actual.contains(expectedResult)) {
            org.testng.Assert.fail("Text expected: " + expectedResult + " Was: " + actual);
        }
    }

    public static void assertTextEqual(String expectedResult, String actual) {
        assertEquals(expectedResult, actual);
    }

}
