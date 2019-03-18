package com.verifone.utils.apiClient;

import com.aventstack.extentreports.ExtentTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

import static com.verifone.utils.apiClient.BaseApi.reportRequestData;
import static com.verifone.utils.apiClient.MySSLSocketFactory.getNewHttpClient;

public class BaseDDTApi {

    public static ExtentTest testLog;


    public static JsonObject getRequestWithHeaders(String url, String method, String body,
                                                   HashMap<String, String> headers, int expectedCode) throws IOException {
        HttpClient client = getNewHttpClient();
        HttpRequestBase request = getRequest(url, method, body);
        headers.forEach(request::addHeader);
        Gson gson = new GsonBuilder().create();
        long startTime = System.currentTimeMillis();
        HttpResponse response = client.execute(request);
//        reportRequestData(url, method, headers, startTime, body, entity);
        int responseCode = response.getStatusLine().getStatusCode();
        if (response.getEntity() == null) {
            Assert.assertEquals(responseCode, expectedCode);
            reportRequestData(url, method, headers, startTime, body, "");
            return null;
        }
        String entity = EntityUtils.toString(response.getEntity());
        reportRequestData(url, method, headers, startTime, body, entity);
        if (responseCode != expectedCode) {
            testLog.error("request failed:  " + entity);
            System.out.println("request failed:  " + entity);
            Assert.assertEquals(responseCode, expectedCode);
        }
        if (entity.startsWith("\""))
            entity = convertFromPrimitive(entity);
        return gson.fromJson(entity, JsonObject.class);
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


    private static String convertFromPrimitive(String entity) {
        System.out.println(entity);
        String[] tempEntity = StringUtils.substringBetween(entity, "{", "}").split(" ,");
        entity = "{";
        for (String s : tempEntity) {
            entity += "\"";
            String[] tmp = s.split(":");
            if (tmp[0].startsWith(" "))
                tmp[0] = tmp[0].substring(1);
            entity += tmp[0] + "\"" + ":\"" + tmp[1] + "\",";
        }
        entity = entity.substring(0, entity.length() - 1);
        entity += "}";
        return entity;
    }


}
