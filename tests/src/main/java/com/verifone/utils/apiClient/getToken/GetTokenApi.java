package com.verifone.utils.apiClient.getToken;

import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.User;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;

import java.io.IOException;

public class GetTokenApi extends BaseApi {

    public GetTokenApi(String correlationId) throws IOException {
        super();
        url = BaseTest.envConfig.getApiUrls().getGetToken();
        baseHeaders.put(this.contentType, prop.getProperty("getToken.contentType"));
        baseHeaders.put(this.correlationId, correlationId);
        baseHeaders.put(this.authorization, prop.getProperty(BaseTest.envConfig.getEnv() + "getToken.authorization"));
    }


    public String getToken(User user) throws IOException {
    	response = getPost("grant_type=password&username="+ user.getUserName() + "&password=" + user.getPassword() +"&scope=openid\"", 200);
        String accessToken = response.get("access_token").getAsString();
        System.out.println("access token was generated:  " + accessToken);
        testLog.log(LogStatus.INFO, "access token was generated:  " + accessToken);
        return accessToken;
    }

}
