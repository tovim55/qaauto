package com.verifone.utils.apiClient.getToken;

import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.utils.apiClient.BaseApi;

import java.io.IOException;

public class GetTokenApi extends BaseApi {

    public GetTokenApi(String contentType, String authorization, String correlationId){
        url = "https://dev.account.verifonecp.com/oauth2/token";
        baseHeaders.put(this.contentType, contentType);
        baseHeaders.put(this.authorization, authorization);
        baseHeaders.put(this.correlationId, correlationId);
    }


    public String getToken(String data) throws IOException {
//        GetTokenResponseSchema accessResponse = (GetTokenResponseSchema) getPost(
//                new GetTokenRequestSchema(data).toString(), 200, GetTokenResponseSchema.class);
        response = getPost(data,200);
        String accessToken = response.get("access_token").getAsString();
        System.out.println("access token was generated:  " + accessToken);
        testLog.log(LogStatus.INFO, "access token was generated:  " + accessToken);
        return accessToken;
    }

}
