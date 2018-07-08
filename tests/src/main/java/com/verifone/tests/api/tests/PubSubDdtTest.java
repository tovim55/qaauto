package com.verifone.tests.api.tests;


import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.*;


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


    @Test(dataProvider = "CreateMerchant")
    public void createMerchantDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                  String headers, String headersForGetToken, String body, String expectedStatusCode,
                                  String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi(testLog);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(dataProvider = "getTOS")
    public void getTOSDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                          String headers, String headersForGetToken, String body, String expectedStatusCode,
                          String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi(testLog);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(dataProvider = "EOAdminData")
    public void EOAdminDataDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                               String headers, String headersForGetToken, String body, String expectedStatusCode,
                               String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi(testLog);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }


    @Test(dataProvider = "UpdatePass")
    public void updatePassDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                              String headers, String headersForGetToken, String body, String expectedStatusCode,
                              String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi(testLog);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }


}
