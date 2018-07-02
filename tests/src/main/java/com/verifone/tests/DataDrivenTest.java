package com.verifone.tests;

import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.utils.DataDrivenUtils;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import static com.verifone.utils.DataDrivenUtils.getHeaders;
import static com.verifone.utils.DataDrivenUtils.getListFrromString;
import static com.verifone.utils.DataDrivenUtils.getMapFromStr;
//import static com.verifone.utils.apiClient.BaseApi.getPostWithHeaders;
import static com.verifone.utils.apiClient.BaseApi.getRequestWithHeaders;

public class DataDrivenTest extends BaseTest {

    public int count = 1;
    public static String dataFile = System.getProperty("user.dir") + "\\src\\test\\resources\\";
    private HashMap headersMap;
    private JsonObject response;
    private HashMap<String, String> expectedResultMap;
    private String [] sheets = {"getTOS", "CreateMerchant", "EOAdminData"};

    @DataProvider(name = "signUp")
    public Object[][] dataSupplierLoginData() throws Exception {
        if (BaseTest.envConfig.getEnv().equals("QA"))
            dataFile += "apiDataQA.xls";
        else
            dataFile += "apiData.xls";
//        for (String sheet: this.sheets){
            Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "CreateMerchant");
//        }

        return arrayObject;    }






    @Test(dataProvider = "signUp", groups = {"cgateway-portal"})
    public void dataDriven(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                           String headers, String headersForGetToken, String body, String expectedStatusCode,
                           String expectedResult, String verifyList, String comments) throws Exception {
        count++;
        starTestLog(count + ". " + comments, comments);
        headersMap = getMapFromStr(headers);
        getToken(accessToken, accGrantType, accSSOURL, headersForGetToken);
        response = getRequestWithHeaders(uri, requestMethod, body, headersMap, Integer.parseInt(expectedStatusCode));
        validateResult(expectedResult, verifyList);
    }








    private void validateResult(String expectedResult, String verifyList) {
        if (response != null)
            testLog.log(LogStatus.INFO, "Response is:\n" + response.toString());
        if (verifyList != null) {
            for (String param : getListFrromString(verifyList)) {
                assertTextContains(param, response.toString());
            }
        }
        if (expectedResult != null) {
            expectedResultMap = getMapFromStr(expectedResult);
            for (String key : expectedResultMap.keySet()) {
                if (response.has(key)) {
                    assertTextContains(expectedResultMap.get(key), response.get(key).toString());
                    testLog.log(LogStatus.INFO, "Result as expected: " + response.get(key).toString());
                } else {
                    org.testng.Assert.fail("Key: '" + key + "'  Is not appear in response");
                }
            }
        }
    }

    private void getToken(String accessToken, String accGrantType, String accSSOURL, String headersForGetToken) throws IOException {
        if (accessToken.equals("true")) {
            response = getRequestWithHeaders(accSSOURL, "post", accGrantType, getMapFromStr(headersForGetToken), 200);
            headersMap.put("Authorization", "Bearer " + response.get("access_token").getAsString());
            testLog.log(LogStatus.INFO, "Access Token: " + response.get("access_token").getAsString());
        }
    }
}
