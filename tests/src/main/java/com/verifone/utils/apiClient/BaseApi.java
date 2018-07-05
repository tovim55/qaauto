package com.verifone.utils.apiClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.utils.apiClient.getToken.GetTokenApi;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;


import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class BaseApi {


    protected String url;
    private int responseCode;
    protected JsonObject response;
    public static ExtentTest testLog;
    protected HashMap<String, String> baseHeaders = new HashMap<String, String>();
    protected String contentType = "Content-Type";
    protected String authorization = "Authorization";
    protected String correlationId = "X-VFI-CORRELATION-ID";
    protected String accept = "Accept";
    protected String origin = "Origin";
    protected String referer = "Referer";
    private HttpClient client = HttpClientBuilder.create().build();
    protected String baseApiPath = System.getProperty("user.dir") +
            "\\src\\main\\java\\com\\verifone\\utils\\apiClient\\";
    protected Properties prop = new Properties();


    public BaseApi() throws IOException {
        FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\verifone\\utils\\apiClient\\headers.properties");
        prop.load(ip);
    }


    public static JsonObject getRequestWithHeaders(String url, String method, String body, HashMap<String, String> headers, int expectedCode) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpRequestBase request = getRequest(url, method, body);
        headers.forEach(request::addHeader);
        Gson gson = new GsonBuilder().create();
        long startTime = System.currentTimeMillis();
        HttpResponse response = client.execute(request);
        reportReqestData(url, method, headers, startTime);
        int responseCode = response.getStatusLine().getStatusCode();
        if (method.equals("head")) {
            Assert.assertEquals(responseCode, expectedCode);
            return null;
        }
        String entity = EntityUtils.toString(response.getEntity());
        if (responseCode != expectedCode) {
            testLog.log(LogStatus.ERROR, "request failed:  " + entity);
            System.out.println("request failed:  " + entity);
            Assert.assertEquals(responseCode, expectedCode);
        }
        return gson.fromJson(entity, JsonObject.class);
    }

    private static void reportReqestData(String url, String method, HashMap<String, String> headers, long startTime) {
        System.out.println("Sending request to URL : " + url);
        testLog.log(LogStatus.INFO, "Sending request to URL : " + url);
        testLog.log(LogStatus.INFO, "Method: " + method);
        testLog.log(LogStatus.INFO, "Headers: " + headers.toString());
        testLog.log(LogStatus.INFO, "Response Time: " + (System.currentTimeMillis() - startTime));

    }

    private static HttpRequestBase getRequest(String url, String method, String body) {
        if (body == null)
            body = "";
        switch (method) {
            case "delete":
                return new HttpDelete(url);
            case "get":
                return new HttpGet(url);
            case "head":
                return new HttpHead(url);
            case "options":
                return new HttpOptions(url);
            case "patch":
                HttpPatch patch = new HttpPatch(url);
                patch.setEntity(new StringEntity(body, "UTF-8"));
                return patch;
            case "post":
                HttpPost post = new HttpPost(url);
                post.setEntity(new StringEntity(body, "UTF-8"));
                return post;
            case "put":
                HttpPut put = new HttpPut(url);
                put.setEntity(new StringEntity(body, "UTF-8"));
                return put;
            default:
                throw new IllegalArgumentException("Invalid or null HttpMethod: " + method);
        }
    }


    protected JsonObject getRequest(int expectedCode) throws IOException {

        HttpGet request = new HttpGet(url);
        baseHeaders.forEach(request::addHeader);
        HttpResponse response = client.execute(request);
        System.out.println("Sending request to URL : " + url);
        testLog.log(LogStatus.INFO, "Sending request to URL : " + url);
        String entity = EntityUtils.toString(response.getEntity());
        validateStatusCode(expectedCode, response, entity);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(entity, JsonObject.class);
    }


    protected JsonObject getPost(JsonObject requestData, int expectedCode) throws IOException {

        HttpPost post = new HttpPost(url);
        baseHeaders.forEach(post::addHeader);
        Gson gson = new GsonBuilder().create();
        post.setEntity(new StringEntity(gson.toJson(requestData), "UTF-8"));
        HttpResponse response = client.execute(post);
        System.out.println("Sending request to URL : " + url);
        testLog.log(LogStatus.INFO, "Sending request to URL : " + url);
        String entity = EntityUtils.toString(response.getEntity());
        validateStatusCode(expectedCode, response, entity);
        return gson.fromJson(entity, JsonObject.class);
    }


    protected JsonObject getPost(String requestData, int expectedCode) throws IOException {

        HttpPost post = new HttpPost(url);
        baseHeaders.forEach(post::addHeader);
        Gson gson = new GsonBuilder().create();
        post.setEntity(new StringEntity(requestData, "UTF-8"));
        HttpResponse response = client.execute(post);
        System.out.println("Sending request to URL : " + url);
        testLog.log(LogStatus.INFO, "Sending request to URL : " + url);
        String entity = EntityUtils.toString(response.getEntity());
        validateStatusCode(expectedCode, response, entity);
        return gson.fromJson(entity, JsonObject.class);
//        try (Writer writer = new FileWriter(jsonPath)) {
//            gson.toJson(EntityUtils.toString(response.getEntity()), writer);
//        }
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
        return gson.fromJson(br, JsonObject.class);
    }


    private void validateStatusCode(int expectedCode, HttpResponse response, String entity) {
        responseCode = response.getStatusLine().getStatusCode();
        if (responseCode != expectedCode) {
            testLog.log(LogStatus.ERROR, entity);
            System.out.println("request failed:  " + entity);
            Assert.assertEquals(responseCode, expectedCode);
        }
    }


}
