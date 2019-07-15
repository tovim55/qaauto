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
    public void testSoapDepositDDT(String request, String exceptedResults, String testDescription, String rowNum)
            throws IOException, JSONException, ParserConfigurationException, SAXException, TransformerException {
        starTestLog("Test: " + testDescription + ", In row: " + rowNum, testDescription);
        PaymentApi api = new PaymentApi();
        String identifier = api.createTransactionRequest();
        api.posDepositRequestExcel(identifier, request, exceptedResults);
    }
}
