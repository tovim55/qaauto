package com.verifone.tests.api.tests;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CloudApiDdt extends BaseTest {


    private static String dataFile = System.getProperty("user.dir") + "\\src\\test\\resources\\cloudApi.xls";


    @DataProvider(name = "cloudApi")
    public Object[][] cloudApi() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "Sheet1");
        return arrayObject;
    }



    @Test(dataProvider = "cloudApi")
    public void cloudApiDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                  String headers, String headersForGetToken, String body, String expectedStatusCode,
                                  String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);

    }
}
