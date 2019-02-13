package com.verifone.tests.oldTests;

import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.payment.PaymentApi;
import org.json.JSONException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;



public class Payment extends BaseTest {


    @Test(testName = "testSoap", description = "testSoap")
    public void testSoap() throws IOException, JSONException, ParserConfigurationException, SAXException, TransformerException {
        PaymentApi api = new PaymentApi();
        String identifier = api.createTransactionRequest();
        api.posDepositRequest(identifier);

    }
}