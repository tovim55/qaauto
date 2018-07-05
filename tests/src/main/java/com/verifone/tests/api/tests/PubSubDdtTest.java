package com.verifone.tests.api.tests;

import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import static com.verifone.utils.DataDrivenUtils.getHeaders;
import static com.verifone.utils.DataDrivenUtils.getListFrromString;
import static com.verifone.utils.DataDrivenUtils.getMapFromStr;
//import static com.verifone.utils.apiClient.BaseApi.getPostWithHeaders;
import static com.verifone.utils.apiClient.BaseApi.getRequestWithHeaders;

public class PubSubDdtTest extends BaseTest {

//    public int count = 1;
    private static String dataFile = System.getProperty("user.dir") + "\\src\\test\\resources\\";
    private HashMap headersMap;
    private JsonObject response;
    private HashMap<String, String> expectedResultMap;
//    private String [] sheets = {"getTOS", "CreateMerchant", "EOAdminData"};

    @BeforeSuite
    private void setFile() {
        if (BaseTest.envConfig.getEnv().equals("QA"))
            dataFile += "apiDataQA.xls";
        else
            dataFile += "apiData.xls";
    }



    @DataProvider(name = "CreateMerchant")
    public Object[][] CreateMerchant() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "CreateMerchant");
        return arrayObject;
    }

    @DataProvider(name = "getTOS")
    public Object[][] getTOS() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "getTOS");
        return arrayObject;
    }

    @DataProvider(name = "EOAdminData")
    public Object[][] EOAdminData() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "EOAdminData");
        return arrayObject;
    }


    @Test(dataProvider = "CreateMerchant")
    public void createMerchant(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                               String headers, String headersForGetToken, String body, String expectedStatusCode,
                               String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi(testLog);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(dataProvider = "getTOS")
    public void getTOS(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                           String headers, String headersForGetToken, String body, String expectedStatusCode,
                           String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi(testLog);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(dataProvider = "EOAdminData")
    public void EOAdminData(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi(testLog);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }


}
