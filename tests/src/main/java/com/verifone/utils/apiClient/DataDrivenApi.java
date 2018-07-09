package com.verifone.utils.apiClient;

import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.util.HashMap;

import static com.verifone.utils.Assertions.assertTextContains;
import static com.verifone.utils.DataDrivenUtils.getListFrromString;
import static com.verifone.utils.DataDrivenUtils.getMapFromStr;
import static com.verifone.utils.apiClient.BaseApi.getRequestWithHeaders;

public class DataDrivenApi {

    private HashMap headersMap;
    private JsonObject response;
    private HashMap<String, String> expectedResultMap;
    private ExtentTest testLog;


    public DataDrivenApi(ExtentTest testLog) {
        this.testLog = testLog;
    }


    public void startProsess(String accessToken, String accGrantType, String accSSOURL, String uri,
                             String requestMethod, String headers, String headersForGetToken, String body,
                             String expectedStatusCode, String expectedResult, String verifyList) throws IOException {
        headersMap = getMapFromStr(headers);
        getToken(accessToken, accGrantType, accSSOURL, headersForGetToken);
        response = getRequestWithHeaders(uri, requestMethod, body, headersMap, Integer.parseInt(expectedStatusCode));
        System.out.println("response is: " + response);
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
