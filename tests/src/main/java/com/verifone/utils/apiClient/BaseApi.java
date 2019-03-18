package com.verifone.utils.apiClient;

import com.aventstack.extentreports.ExtentTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.testng.Assert;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;

import static com.verifone.utils.apiClient.MySSLSocketFactory.getNewHttpClient;

public abstract class BaseApi {


    protected String url;
    public static ExtentTest testLog;
    private Gson gson = new GsonBuilder().create();
    protected HashMap<String, String> baseHeaders = new HashMap<>();
    protected String baseApiPath = java.nio.file.Paths.get(
            System.getProperty("user.dir"),
            "src", "main", "java", "com", "verifone", "utils", "apiClient").toString() + File.separator;
    protected Properties prop = new Properties();


    public BaseApi() throws IOException {
        FileInputStream ip = new FileInputStream(baseApiPath + "headers.properties");
        prop.load(ip);
    }


    protected JSONObject postSOAPXML(String requestData, int expectedCode) throws IOException, JSONException {
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(requestData, "UTF-8"));
        baseHeaders.forEach(post::addHeader);
        String entity = executeRequest("<textarea>" + requestData + "</textarea>", expectedCode, post);
        return XML.toJSONObject(entity);

    }


    protected JsonObject getRequest(int expectedCode) throws IOException {
        HttpGet get = new HttpGet(url);
        baseHeaders.forEach(get::addHeader);
        String entity = executeRequest("", expectedCode, get);
        return gson.fromJson(entity, JsonObject.class);
    }


    protected JsonObject getPost(JsonObject requestData, int expectedCode) throws IOException {
        HttpPost post = new HttpPost(url);
        baseHeaders.forEach(post::addHeader);
        post.setEntity(new StringEntity(gson.toJson(requestData), "UTF-8"));
        String entity = executeRequest(requestData.toString(), expectedCode, post);
        return gson.fromJson(entity, JsonObject.class);
    }


    protected JsonObject getPost(String requestData, int expectedCode) throws IOException {
        HttpPost post = new HttpPost(url);
        baseHeaders.forEach(post::addHeader);
        post.setEntity(new StringEntity(requestData, "UTF-8"));
        String entity = executeRequest(requestData, expectedCode, post);
        return gson.fromJson(entity, JsonObject.class);
    }


    private String executeRequest(String requestData, int expectedCode, HttpRequestBase request) throws IOException {
        HttpClient client = getNewHttpClient();
        long startTime = System.currentTimeMillis();
        HttpResponse response = client.execute(request);
        String entity = EntityUtils.toString(response.getEntity());
        reportRequestData(url, request.getMethod(), baseHeaders, startTime, requestData, entity);
        validateStatusCode(expectedCode, response, entity);
        return entity;
    }


    public static void reportRequestData(String url, String method, HashMap<String, String> headers, long startTime, String requestData, String entity) {
        System.out.println("Sending request to URL : " + url);
        testLog.info("Sending request to URL : " + url);
        testLog.info("Method: " + method);
        testLog.info("Headers: " + headers.toString());
        testLog.info("request data: " + requestData);
        testLog.info("Response Time: " + (System.currentTimeMillis() - startTime));
        testLog.info("Response Data: " + entity);

    }


    protected JsonObject getAtter(JsonObject response, String[] keys) {
        for (String k : keys) {
            try {
                response = response.getAsJsonObject(k);
            } catch (ClassCastException e) {
                JsonArray list = response.getAsJsonArray(k);
                response = (JsonObject) list.get(0);
            }
        }
        return response;
    }


    public static JsonObject readJsonFile(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        Gson gson = new Gson();
        assert br != null;
        return gson.fromJson(br, JsonObject.class);
    }

    protected static Document readXMLFile(String filePath) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.parse(filePath);
    }

    protected String convertDocToStr(Document xmlFile) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        StringWriter sw = new StringWriter();
        trans.transform(new DOMSource(xmlFile), new StreamResult(sw));
        return sw.toString();
    }


    private void validateStatusCode(int expectedCode, HttpResponse response, String entity) {
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode != expectedCode) {
            testLog.error(entity);
            System.out.println("request failed:  " + entity);
            Assert.assertEquals(responseCode, expectedCode);
        }
    }


}
