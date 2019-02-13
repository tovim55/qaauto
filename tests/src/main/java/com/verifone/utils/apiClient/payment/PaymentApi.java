package com.verifone.utils.apiClient.payment;

import org.json.JSONException;
import org.json.JSONObject;
import com.verifone.utils.apiClient.BaseApi;
import org.apache.http.entity.InputStreamEntity;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;


public class PaymentApi extends BaseApi {

    public PaymentApi() throws IOException {
        super();
        baseHeaders.put(this.contentType, "text/xml; charset=UTF-8");
    }


    public String createTransactionRequest() throws IOException, JSONException, ParserConfigurationException, SAXException, TransformerException {
        url = "http://stgvpay.verifone.co.il/ws/vpayposproxy.asmx";
//        File req_xml = new File(baseApiPath + "payment" + File.separator + "create_transaction_request");
//        JSONObject result = postSOAPXML(new InputStreamEntity(new FileInputStream(req_xml), req_xml.length()), 200);
        String requestData = convertDocToStr(readXMLFile(
                baseApiPath + "payment" + File.separator + "create_transaction_request"));
        JSONObject result = postSOAPXML(requestData, 200);

        return result.getJSONObject("soap:Envelope")
                .getJSONObject("soap:Body")
                .getJSONObject("CreateTransactionResponse")
                .getJSONObject("CreateTransactionResult")
                .getJSONObject("Body").getString("Identifier");

    }


    public void posDepositRequest(String identifier) throws IOException, ParserConfigurationException, SAXException, TransformerException, JSONException {
        url = "http://stgvpay.verifone.co.il/ws/vpayposproxy.asmx";

        Document xmlFile = readXMLFile(baseApiPath + "payment" + File.separator + "pos_deposit_request");
        xmlFile.getElementsByTagName("tem:InvoiceNumber").item(0).setTextContent(identifier);
        String requestData = convertDocToStr(xmlFile);
        postSOAPXML(requestData, 200);
    }


}
