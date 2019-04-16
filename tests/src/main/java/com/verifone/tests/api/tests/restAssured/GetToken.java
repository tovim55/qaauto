package com.verifone.tests.api.tests.restAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetToken {
    private static String getTokenUrl = "https://qa.account.verifonecp.com/oauth2/token";

    private static String scope = "openid";
    private static String grant_type = "password";
    public static String username = "t4891@getnada.com";
    public static String password = "Veri1234";

    public static RequestSpecification request;
    public static Response response;

    static String POST_GetAccessToken()
    {

        RestAssured.baseURI = getTokenUrl;
        request = RestAssured.given();
        request.header("Authorization","Basic SThKMmVJTEpqQnIxUGxZTDU1NW5JOXdDMFIwYTpNNWs0YXBxbDdwem9QUjdIVVRmMk0zUTZJREFh");
        request.header("Content-Type","application/x-www-form-urlencoded");

        response = request.post("?scope="+ scope + "&grant_type=" + grant_type + "&username=" + username +"&password="+password);

        System.out.println(response.getBody().asString());

        JsonPath jp = response.jsonPath();

        return jp.get("access_token");
    }
}
