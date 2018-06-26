package com.verifone.tests;

import com.google.gson.JsonObject;
import com.verifone.utils.DataDrivenUtils;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import static com.verifone.utils.DataDrivenUtils.getHeaders;
import static com.verifone.utils.DataDrivenUtils.getMapFromStr;
import static com.verifone.utils.apiClient.BaseApi.getPostWithHeaders;
import static com.verifone.utils.apiClient.BaseApi.getRequestWithHeaders;

public class DataDrivenTest extends BaseTest {


    @DataProvider(name = "signUp")
    public Object[][] dataSupplierLoginData() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(System.getProperty("user.dir") + "\\src\\test\\resources\\apiData.xls", "EOAdminData");
        return arrayObject;
    }

    @Test(dataProvider = "signUp", groups = {"cgateway-portal"})
    public void dataDriven(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod, String headers, String headersForGetToken, String body, String expectedStatusCode, String expectedResult) throws Exception {
        starTestLog("Pub Sub Get Token", "get token and EOadmin data calls");


        HashMap headersMap = getMapFromStr(headers);
        JsonObject response;
        if (accessToken.equals("true")) {
            response = getPostWithHeaders(accSSOURL, accGrantType, getMapFromStr(headersForGetToken), 200);
            headersMap.put("Authorization", "Bearer " + response.get("access_token").getAsString());
        }
        response = getRequestWithHeaders(uri, requestMethod, body, headersMap, Integer.parseInt(expectedStatusCode));
        if (response != null) {
            System.out.println("Response is:");
            System.out.println(response.toString());
        }
        if (expectedResult != null) {
            HashMap<String, String> expectedResultMap = getMapFromStr(expectedResult);
            for (String key : expectedResultMap.keySet()) {
                System.out.println(key + " " + expectedResultMap.get(key));
                System.out.println(response.get(key));
                assertTextContains(expectedResultMap.get(key), response.get(key).toString());
            }
        }
    }
}
