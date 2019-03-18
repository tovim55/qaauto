package com.verifone.tests.api.tests.restAssured;

import org.testng.annotations.Test;

public class RestAPI {

    public static String url = "https://t8d0j8mnug.execute-api.us-east-1.amazonaws.com/V1/";
    public static String source;

    private static String LocationID = "6dcbd7d3-a76e-477b-b515-1e7ec5c84946";
    public static String appId = "cloudAPI-1294609943";
    public static String merchantId = "d74c2645-2529-462b-80e6-f61bc2b467d0";

    //GET
//    @Test
//    public void GET_ShiftByEmployeeID()
//    {
//        source = "/employee/" + employeeID;
//        response = request.get(source + "?appId=" + appId + "&merchantId=" + merchantId);
//        System.out.println(response.getBody().asString());
//
//    }

    // POST
//    @Test
//    public void POST_ShiftByEmployeeID()
//    {
//        request.header("Content-Type", "application/json");
//
//        JSONObject requestParams = new JSONObject();
//
//        requestParams.put("clockIn","2017-11-12T11:45:26.371Z");
//        requestParams.put("clockOut","2018-11-12T11:45:50.371Z");
//        requestParams.put("cashTips", 111111);
//        requestParams.put("locationId","6dcbd7d3-a76e-477b-b515-1e7ec5c84946");
//
//        request.body(requestParams.toJSONString());
//        source="/employee/" + employeeID;
//
//        response = request.post(source + "?appId=" + appId + "&merchantId=" + merchantId);
//        System.out.println(response.getBody().asString());
//
//    }


    //PUT

//    @Test
//    public void PUT_ShiftByEmployeeID()
//    {
//
//        request.header("Content-Type", "application/json");
//        JSONObject requestParams = new JSONObject();
//
//        requestParams.put("empIdCiOverride","e4d6e711-25dc-4531-aa08-2d7755c70a93");
//        requestParams.put("clockInOverride","2020-11-12T13:55:50.371Z");
//        requestParams.put("empIdCoOverride","e4d6e711-25dc-4531-aa08-2d7755c70a93");
//        requestParams.put("clockOutOverride","2021-12-12T19:00:50.371Z");
//        requestParams.put("cashTips", 12345);
//        requestParams.put("locationId","6dcbd7d3-a76e-477b-b515-1e7ec5c84946");
//
//        request.body(requestParams.toJSONString());
//
//        response = request.put(ShiftID + "?appId=" + appId + "&merchantId=" + merchantId);
//        System.out.println(response.getBody().asString());
//    }


}
