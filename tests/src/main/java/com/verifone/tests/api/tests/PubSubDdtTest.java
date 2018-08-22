package com.verifone.tests.api.tests;


import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import org.testng.annotations.*;

import java.util.UUID;

import static com.verifone.utils.DataDrivenUtils.getMapFromStr;
import static com.verifone.utils.apiClient.BaseApi.getRequestWithHeaders;


public class PubSubDdtTest extends BaseTest {

    private static String dataFile = System.getProperty("user.dir") + "\\src\\test\\resources\\";


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

    @DataProvider(name = "UpdatePass")
    public Object[][] updatePass() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "updatePassword");
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


    @DataProvider(name = "getAgreement")
    public Object[][] getAgreementData() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "getAgreement");
        return arrayObject;
    }

    @Test(dataProvider = "CreateMerchant")
    public void createMerchantDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                  String headers, String headersForGetToken, String body, String expectedStatusCode,
                                  String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(dataProvider = "getTOS")
    public void getTOSDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                          String headers, String headersForGetToken, String body, String expectedStatusCode,
                          String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(dataProvider = "EOAdminData")
    public void EOAdminDataDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                               String headers, String headersForGetToken, String body, String expectedStatusCode,
                               String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }


    @Test(dataProvider = "UpdatePass")
    public void updatePassDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                              String headers, String headersForGetToken, String body, String expectedStatusCode,
                              String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        JsonObject response = getRequestWithHeaders(accSSOURL, "post",
                accGrantType, getMapFromStr(headersForGetToken), 200);
        String id = UUID.randomUUID().toString();
        String confirmationCode = new CreateMerchant(response.get("access_token").getAsString(), "test")
                .createMerchantWithConfirmation("a840885f-0017-49ef-acb3-fdebf74bbd9c", id);
        api.setConfirmationCode(confirmationCode);
        api.setUser(id.replace("-", "") + "@getnada.com");
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }


//
//    @Test(dataProvider = "getAgreement")
//    public void getAgreementDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
//                              String headers, String headersForGetToken, String body, String expectedStatusCode,
//                              String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
//        starTestLog(rowNum + ". " + comments, comments);
//        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
//        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
//                expectedStatusCode, expectedResult, verifyList);
//    }


}
