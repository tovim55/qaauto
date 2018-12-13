package com.verifone.tests.api.tests.CloudApiDdt;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class MerchantApiDdt extends BaseTest {

    private String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("cloudApiQA.xls", "cloudApi.xls");
    }

    @DataProvider(name = "merchant")
    public Object[][] merchant() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "merchant");
        return arrayObject;
    }

    @Test(dataProvider = "merchant", groups = "cloudApi")

    public void cloudApiMerchantDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

}
