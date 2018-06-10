package com.verifone.utils.apiClient.getToken;

import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.LogStatus;
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


    public String getToken() throws IOException {
        response = getPost(prop.getProperty(BaseTest.envConfig.getEnv() + "getToken.requestData"),200);
        String accessToken = response.get("access_token").getAsString();
        System.out.println("access token was generated:  " + accessToken);
        testLog.log(LogStatus.INFO, "access token was generated:  " + accessToken);
        return accessToken;
    }

}
