package com.verifone.tests.api.tests;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.json.JsonObject;
import java.util.UUID;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;


public class CBAApiDdt extends BaseTest {

    private static String file;
    private static String merchantId;


    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("CBAApiQA.xls", "CBAApi.xls");
    }

    @DataProvider(name = "groups")
    public Object[][] shift() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "groups");
        return arrayObject;
    }
    @DataProvider(name = "CreateMerchant_EO_MP")
    public Object[][] merchantEO() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "CreateMerchant_EO_MP");
        return arrayObject;
    }


    @Test(dataProvider = "groups")
    public void cloudApiShiftDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),file);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(dataProvider = "CreateMerchant_EO_MP")
    public void createMerchant_SuperEO_DDT(String accessToken, String accGrantType, String accSSOURL, String uri1, String uri2, String requestMethod,
                                 String headers, String headersForGetToken, String body1, String body2, String expectedStatusCode1, String expectedStatusCode2,
                                 String expectedResult1, String expectedResult2, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        String uuid = UUID.randomUUID().toString();
        String muid = uuid.replace("-", "").substring(21);
        String email = muid + "@getnada.com";
        String name = "test"+ muid;

        if(body2.equals(" ")){body2 = null;}
        if(expectedResult2.equals(" ")){expectedResult2 = null;}
        if(verifyList.equals(" ")){verifyList = null;}

        if(body1!= null) {
            body1 = body1.replace("1doba@getnada.com", email);
            body1 = body1.replace("12345678",muid );
            body1 = body1.replace("test1234",name );
        }

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),file);
        api.startProsessWithGetValue(accessToken, accGrantType, accSSOURL, uri1, uri2, requestMethod, headers, headersForGetToken, body1, body2, expectedStatusCode1,expectedStatusCode2, expectedResult1,expectedResult2, verifyList);

    }


    }


