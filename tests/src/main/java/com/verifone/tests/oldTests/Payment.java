package com.verifone.tests.oldTests;

import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.payment.PaymentApi;
import org.json.JSONException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;


public class Payment extends BaseTest {


//    private static String dataFile = java.nio.file.Paths.get(System.getProperty("user.dir"),
//            "src", "test", "resources").toString();


//    @BeforeSuite
//    private void setFile() {
//        if (BaseTest.envConfig.getEnv().equalsIgnoreCase("QA")) {
//            dataFile += File.separator + "soapAPI.xls";
//            System.out.println("THE PATH FOR PUB-SUB IS: " + dataFile);
//        } else {
//            dataFile += File.separator + "soapAPI.xls";
//            System.out.println(dataFile);
//        }
//    }
//
//    @DataProvider(name = "paymentTests")
//    public Object[][] getAgreementData() throws Exception {
//        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "paymentTests");
//        return arrayObject;
//    }
//
//    @Test(dataProvider = "paymentTests")
//    public void createMerchantDDT(String getIdentifier, String request, String exceptedResults, String testDescription
//            , String rowNum) throws Exception {
//        starTestLog(rowNum + ". " + testDescription, testDescription);
//        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(), dataFile);
//        PaymentApi tests = new PaymentApi();
//        String identifier = tests.createTransactionRequestExcel(getIdentifier);
//        tests.posDepositRequestExcel(identifier, request, exceptedResults);


//        String uuid = UUID.randomUUID().toString();
//        String muid = uuid.replace("-", "").substring(21);
//        String email = muid + "@getnada.com";
//
//        if(body!= null) {
//            body = body.replace("1doba@cmail.club", email);
//            body = body.replace("1234533",muid );
//        }

//        api.startProsess(getIdentifier, request, exceptedResults, testDescription
//                , rowNum);
//    }

//    @Test(testName = "testSoap", description = "testSoap")
//    public void testSoap() throws IOException, JSONException, ParserConfigurationException, SAXException, TransformerException {
//        PaymentApi api = new PaymentApi();
//        String identifier = api.createTransactionRequest();
//        api.posDepositRequest(identifier);
//    }




    private static String dataFile = java.nio.file.Paths.get(System.getProperty("user.dir"),
            "src", "test", "resources").toString();


    @BeforeSuite
    private void setFile() {
//        if (BaseTest.envConfig.getEnv().equalsIgnoreCase("QA")) {
            dataFile += File.separator + "soapAPI.xls";
//        } else {
//            dataFile += File.separator + "soapAPI.xls";
//            System.out.println(dataFile);
//        }
    }

    @DataProvider(name = "paymentTests")
    public Object[][] getAgreementData() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(dataFile, "paymentTests");
        return arrayObject;
    }

    //
    @Test(dataProvider = "paymentTests")
    public void testSoapDepositDDT(String request, String exceptedResults, String testDescription, String rowNum) throws IOException, JSONException, ParserConfigurationException, SAXException, TransformerException {
        starTestLog("Test:" + rowNum + ". " + testDescription, testDescription);
        System.out.println("test");
        PaymentApi api = new PaymentApi();
        String identifier = api.createTransactionRequest();
        api.posDepositRequestExcel(identifier, request, exceptedResults);
    }
}
