package com.verifone.tests.api.tests.cloud_api_ddt;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class CustomerApiDdt extends BaseTest {

    private String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("cloudApiQA.xls", "cloudApi.xls");
    }

    @DataProvider(name = "customer")
    public Object[][] customer() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "customer");
        return arrayObject;
    }

    @Test(dataProvider = "customer", groups = "cloudApi")

    public void cloudApiCustomerDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        //Generate unique alphanumeric String
        String uuid = UUID.randomUUID().toString();
        //Define unique unexisting email
        String email = uuid.replace("-", "").substring(21) + "@getnada.com";
        //Define existing email in Database
        String existingEmail = "test@getnada.com";

        if (requestMethod.equals("post")) {
            if(body!= null) {
                if (headers!=null)
                    body = body.replace("email@getnada.com", email); //verify post with unique email
                else
                    body = body.replace("email@getnada.com", existingEmail); //verify post with existing email
            }
            if(body == null && headers ==null) {
                headers = "{RequestID: }"; //verify post without requestID
            }
            else
                headers = "{RequestID:" + uuid + "}"; //verify post with unique requestID
        }
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

}
