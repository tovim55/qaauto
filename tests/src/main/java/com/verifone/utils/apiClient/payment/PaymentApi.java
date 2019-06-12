package com.verifone.utils.apiClient.payment;

import com.verifone.utils.apiClient.Headers;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.json.JSONException;
import org.json.JSONObject;
import com.verifone.utils.apiClient.BaseApi;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.*;


public class PaymentApi extends BaseApi {

    public PaymentApi() throws IOException {
        super();
        baseHeaders.put(Headers.CONTENT_TYPE.get(), "text/xml; charset=UTF-8");
    }


    public String createTransactionRequest() throws IOException, JSONException, ParserConfigurationException, SAXException, TransformerException {
        url = "http://stgvpay.verifone.co.il/ws/vpayposproxy.asmx";
        String requestData = convertDocToStr(readXMLFile(
                baseApiPath + "payment" + File.separator + "create_transaction_request"));
        System.out.println("requestData: " + requestData);
        JSONObject result = postSOAPXML(requestData, 200);
        System.out.println(result.toString());
        String identifier = result.getJSONObject("soap:Envelope")
                .getJSONObject("soap:Body")
                .getJSONObject("CreateTransactionResponse")
                .getJSONObject("CreateTransactionResult")
                .getJSONObject("Body").getString("Identifier");
        testLog.info("Identifier: " + identifier);
        System.out.println(identifier);
        return identifier;
    }
        public String createTransactionRequestExcel(String getIdentifier) throws IOException, JSONException, ParserConfigurationException, SAXException, TransformerException {
//            url = "http://stgvpay.verifone.co.il/ws/vpayposproxy.asmx";
            String requestData = getIdentifier;
            System.out.println("requestData: " + requestData);
            JSONObject result = postSOAPXML(requestData, 200);
            System.out.println(result.toString());
            String identifier  = result.getJSONObject("soap:Envelope")
                    .getJSONObject("soap:Body")
                    .getJSONObject("CreateTransactionResponse")
                    .getJSONObject("CreateTransactionResult")
                    .getJSONObject("Body").getString("Identifier");
            testLog.info("Identifier: " + identifier);
            System.out.println(identifier);
            return identifier;

    }


    public void posDepositRequest(String identifier) throws IOException, ParserConfigurationException, SAXException, TransformerException, JSONException {
        url = "http://stgvpay.verifone.co.il/ws/vpayposproxy.asmx";

        Document xmlFile = readXMLFile(baseApiPath + "payment" + File.separator + "pos_deposit_request");
        xmlFile.getElementsByTagName("tem:InvoiceNumber").item(0).setTextContent(identifier);
        String requestData = convertDocToStr(xmlFile);
        postSOAPXML(requestData, 200);
    }

    public void posDepositRequestExcel(String identifier, String request, String exceptedResults) throws IOException,
            ParserConfigurationException, SAXException, TransformerException, JSONException {
//        url = "http://stgvpay.verifone.co.il/ws/vpayposproxy.asmx";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(request)));
            document.getElementsByTagName("tem:InvoiceNumber").item(0).setTextContent(identifier);
            String requestData = convertDocToStr(document);
            postSOAPXML(requestData, Integer.parseInt(exceptedResults));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Document xmlFile = readXMLFile(baseApiPath + "payment" + File.separator + "pos_deposit_request");
    }
}
