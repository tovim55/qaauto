package com.verifone.tests.api.tests;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;


public class CBAApiDdt extends BaseTest {

    private static String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("CBAApiQA.xls", "CBAApi.xls");
    }

    @DataProvider(name = "groups")
    public Object[][] shift() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "groups");
        return arrayObject;
    }

    @Test(dataProvider = "groups", groups = "CBAApi")
    public void cloudApiShiftDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),file);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }
}


