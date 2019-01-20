package com.verifone.tests.api.tests.cloud_api_ddt;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;


public class ShiftApiDdt extends BaseTest {

    private static String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("cloudApiQA.xls", "cloudApi.xls");
    }

    @DataProvider(name = "shift")
    public Object[][] shift() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "shift");
        return arrayObject;
    }

    @Test(dataProvider = "shift", groups = "cloudApi")
    public void cloudApiShiftDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        //Generate unique alphanumeric String
        String uuid = UUID.randomUUID().toString();
        if (requestMethod.equals("post")){
            if(headers==null)
            headers = "{RequestID:" + uuid + "}";} //verify post with unique requestID
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(), false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

}
