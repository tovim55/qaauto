package com.verifone.utils.apiClient;

import com.google.gson.JsonObject;
import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
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
    private String confirmationCode;
    private String user;
    private boolean isBearer = true; //flag to define getToken type (with 'Bearer' or not)


//    public DataDrivenApi(ExtentTest testLog) {
//        this.testLog = testLog;
//    }

    public DataDrivenApi(ExtentTest child) {
        this.testLog = child;
    }

    /**
     * @param child
     * @param isBearer
     */
    public DataDrivenApi(ExtentTest child, boolean isBearer) {
        this.testLog = child;
        this.isBearer = isBearer;
    }

    public void startProsess(String accessToken, String accGrantType, String accSSOURL, String uri,
                             String requestMethod, String headers, String headersForGetToken, String body,
                             String expectedStatusCode, String expectedResult, String verifyList) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        headersMap = getMapFromStr(headers);
        getToken(accessToken, accGrantType, accSSOURL, headersForGetToken);
        if (confirmationCode != null)
            body = addConfirmationCode(body);
        System.out.println(headersMap);
        response = getRequestWithHeaders(uri, requestMethod, body, headersMap, Integer.parseInt(expectedStatusCode));
        System.out.println("response is: " + response);
        validateResult(expectedResult, verifyList);
    }


    private void validateResult(String expectedResult, String verifyList) {
        if (response != null)
            testLog.info("Response is:\n" + response.toString());
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
                    testLog.info("Result as expected: " + response.get(key).toString());
                } else {
                    org.testng.Assert.fail("Key: '" + key + "'  Is not appear in response");
                }
            }
        }
    }


    private String getToken(String accessToken, String accGrantType, String accSSOURL, String headersForGetToken) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        if (accessToken.equals("true")) {
            response = getRequestWithHeaders(accSSOURL, "post", accGrantType, getMapFromStr(headersForGetToken), 200);

            if (isBearer)
                headersMap.put("Authorization", "Bearer " + response.get("access_token").getAsString());
            else
                headersMap.put("Authorization", response.get("access_token").getAsString());

            testLog.info("Access Token: " + response.get("access_token").getAsString());

        }
        return accessToken;
    }

    private String addConfirmationCode(String body) {
        if (body.contains("\"username\":"))
            return body;
        body = body.substring(0, body.length() - 2);
        body = body + "\"code\":\"" + confirmationCode + "\",\"username\":\"" + user + "\"}";
        testLog.info("Confirmation Code: " + confirmationCode);
        System.out.println(body);
        return body;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private static String dataFile = System.getProperty("user.dir") + "\\src\\test\\resources\\";

    /**
     * @param fileQA
     * @param fileDev
     * @return full dataFilePath String (according to Environment)
     */
    public static String setFilePath(String fileQA, String fileDev) {

        String env = BaseTest.envConfig.getEnv();

        if (env.equals("QA"))
            return dataFile + fileQA;
        else
            return dataFile + fileDev;
    }
}
