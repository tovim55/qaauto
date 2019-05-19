package com.verifone.tests.api.tests.restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class BearerToken
{
    //private static String bearerTokenUrl = "https://qa.account.verifonecp.com/oauth2/token";
    private static String bearerUrl = "https://qa.account.verifonecp.com";
    private static String authorization = "Basic ZFo5WW96OEgxUVBjdXhzRDJicWtXbk1WQWNNYTpuRW96X0Z4eUxZVjRBbGdPbk9TWEZBdUxObGthOg==";
    private static String bodySting = "grant_type=client_credentials&scope=openid";

    public static RequestSpecification request;
    public static Response response;

    @Test()
    public void generateBearerToken()
    {
        RestAssured.baseURI = bearerUrl;
        request = RestAssured.given();

        request.header("Content-Type", "application/x-www-form-urlencoded");
        request.header("Authorization", authorization);

        request.body(bodySting);

        response = request.post("/oauth2/token");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

}
