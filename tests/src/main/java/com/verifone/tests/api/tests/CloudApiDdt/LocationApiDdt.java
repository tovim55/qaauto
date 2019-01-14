package com.verifone.tests.api.tests.CloudApiDdt;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class LocationApiDdt extends BaseTest {

    private String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("cloudApiQA.xls", "cloudApi.xls");
    }

    @DataProvider(name = "location")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "location");
        return arrayObject;
    }

    @Test(dataProvider = "location", groups = "cloudApi")

    public void cloudApiLocationDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

}
