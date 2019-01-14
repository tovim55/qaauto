package com.verifone.tests.api.tests.CloudApiDdt;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class CustomerAttributeApiDdt extends BaseTest {

    private String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("cloudApiQA.xls", "cloudApi.xls");
    }

    @DataProvider(name = "customerAttribute")
    public Object[][] customer() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "customerAttribute");
        return arrayObject;
    }

    @Test(dataProvider = "customerAttribute", groups = "cloudApi")

    public void cloudApiCustomerAttributeDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                            String headers, String headersForGetToken, String body, String expectedStatusCode,
                            String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        //Generate unique alphanumeric String
        String uuid = UUID.randomUUID().toString();
        //Define unique key parameter
        String key = uuid.replace("-", "").substring(21);
        //Define existing key in Database
        String existingKey = "city";

        if (requestMethod.equals("post")) {
            if(body!= null) {
                if (headers!=null)
                    body = body.replace("company", key); //verify post with unique key
                else
                    body = body.replace("company", existingKey); //verify post with existing key
            }
            if(body == null && headers ==null) {
                headers = "{RequestID: }"; //verify post without requestID
            }
            else
                headers = "{RequestID:" + uuid + "}"; //verify post with unique requestID
        }
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(), false);
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

}
