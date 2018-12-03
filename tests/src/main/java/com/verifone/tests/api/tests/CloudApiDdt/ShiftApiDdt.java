package com.verifone.tests.api.tests.CloudApiDdt;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

class CloudApiDdt extends BaseTest {


    private static String dataFile = System.getProperty("user.dir") + "\\src\\test\\resources\\cloudApi.xls";


    @DataProvider(name = "shift")
    public Object[][] shift() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "shift");
        return arrayObject;
    }

    @DataProvider(name = "employee")
    public Object[][] employee() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "employee");
        return arrayObject;
    }

    @Test(dataProvider = "shift")
    public void cloudApiShiftDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        String uuid = UUID.randomUUID().toString();
        if (requestMethod.equals("post"))
            headers = "{RequestID:" + uuid + "}";
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }
    @Test(dataProvider = "employee")
    public void cloudApiEmployeeDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                  String headers, String headersForGetToken, String body, String expectedStatusCode,
                                  String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        String uuid = UUID.randomUUID().toString();
        String email = uuid.replace("-", "") + "@getnada.com";
        if (requestMethod.equals("post")) {
            headers = "{RequestID:" + uuid + "}";
           if(body!= null)
           body = body.replace("test@getnada.com", email);
        }
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);

   }


}
