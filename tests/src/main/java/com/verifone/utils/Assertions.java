package com.verifone.utils;

public class Assertions {


    public static void assertTextContains(String expectedResult, String actual){
        if (!actual.contains(expectedResult)) {
            org.testng.Assert.fail("Text expected: " + expectedResult + " Was: " + actual);
        }
    }

}
