package com.verifone.tests.api.tests.restAssured;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.DataDrivenApi;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class POST_JSON_Shift extends BaseTest {

    public static String url = "https://t8d0j8mnug.execute-api.us-east-1.amazonaws.com/V1/shift/upload";

    private static String appId = "cloudAPI-1294609943";
    private static String merchantId = "d74c2645-2529-462b-80e6-f61bc2b467d0";

    public static RequestSpecification request;
    public static Response response;

    public static GetToken gat = new GetToken ();
    private static String Token = GetToken.POST_GetAccessToken();

    private String generateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }


    @BeforeClass
    public static void start()
    {
        RestAssured.baseURI = url;
        request = RestAssured.given();

        String uuid = UUID.randomUUID().toString();
        request.header("Authorization", Token);
        request.header("RequestID", uuid);

    }
    //POST FRON JSON FILE

    @Test
    public void POST_Shift_JSONFile() throws IOException {
        request.header("Content-Type", "application/x-www-form-urlencoded");

        JSONObject requestParams = new JSONObject();

        String jsonBody = generateStringFromResource("/Users/yegorp1/Desktop/CloudAPI/Shift/shifts.json");

        request.body(jsonBody);

        response = request.post("?appId=" + appId + "&merchantId=" + merchantId);

        assertEquals(response.getStatusCode(), 200);

    }


}
