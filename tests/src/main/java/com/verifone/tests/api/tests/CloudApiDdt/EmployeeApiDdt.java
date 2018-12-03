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


public class EmployeeApiDdt extends BaseTest {


    private String file;

    public EmployeeApiDdt() {
        this.file = setFilePath("cloudApiQA.xls", "cloudApi.xls");
    }

    @DataProvider(name = "employee")
    public Object[][] employee() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "employee");
        return arrayObject;
    }

    @Test(dataProvider = "employee", groups = "cloudApi")
    public void cloudApiEmployeeDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
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
                    body = body.replace("email@getnada.com", email);
                else
                    body = body.replace("email@getnada.com", existingEmail);
            }
            if(body == null && headers ==null) {
                headers = "{RequestID: }";
            }
            else
                headers = "{RequestID:" + uuid + "}";
        }
        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get());
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);

    }
}
