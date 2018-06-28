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
import static com.verifone.utils.DataDrivenUtils.getMapFromStr;
//import static com.verifone.utils.apiClient.BaseApi.getPostWithHeaders;
import static com.verifone.utils.apiClient.BaseApi.getRequestWithHeaders;

public class DataDrivenTest extends BaseTest {

    public static int count = 1;
    public static String dataFile = System.getProperty("user.dir") + "\\src\\test\\resources\\";

    @DataProvider(name = "signUp")
    public Object[][] dataSupplierLoginData() throws Exception {
        if (BaseTest.envConfig.getEnv().equals("QA"))
            dataFile += "apiDataQA.xls";
        else
            dataFile += "apiData.xls";
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "CreateMerchant");
        return arrayObject;
    }

    @Test(dataProvider = "signUp", groups = {"cgateway-portal"})
    public void dataDriven(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod, String headers, String headersForGetToken, String body, String expectedStatusCode, String expectedResult, String comments) throws Exception {
        count++;
        starTestLog(count + ". " + comments, comments);


        HashMap headersMap = getMapFromStr(headers);
        JsonObject response;
        if (accessToken.equals("true")) {
            response = getRequestWithHeaders(accSSOURL, "post", accGrantType, getMapFromStr(headersForGetToken), 200);
            headersMap.put("Authorization", "Bearer " + response.get("access_token").getAsString());
            testLog.log(LogStatus.INFO, "Access Token: " + response.get("access_token").getAsString());
        }
        response = getRequestWithHeaders(uri, requestMethod, body, headersMap, Integer.parseInt(expectedStatusCode));
        if (response != null)
            testLog.log(LogStatus.INFO, "Response is:\n" + response.toString());

        if (expectedResult != null) {
            HashMap<String, String> expectedResultMap = getMapFromStr(expectedResult);
            for (String key : expectedResultMap.keySet()) {
                if(response.has(key)) {
                    System.out.println(key + " " + expectedResultMap.get(key));
                    System.out.println(response.get(key));
                    assertTextContains(expectedResultMap.get(key), response.get(key).toString());
                }
                else{org.testng.Assert.fail("Key: '" + key + "'  Is not appear in response");}
            }
        }
    }
}
