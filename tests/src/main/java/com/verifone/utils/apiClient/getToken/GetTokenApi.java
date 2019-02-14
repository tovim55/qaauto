package com.verifone.utils.apiClient.getToken;

import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.User;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;
import com.verifone.utils.apiClient.Headers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class GetTokenApi extends BaseApi {

    public GetTokenApi(String correlationId) throws IOException {
        super();
        url = BaseTest.envConfig.getApiUrls().getGetToken();
        baseHeaders.put(Headers.CONTENT_TYPE.get(), prop.getProperty("getToken.contentType"));
        baseHeaders.put(Headers.CORRELATION_ID.get(), correlationId);
        baseHeaders.put(Headers.AUTHORIZATION.get(), prop.getProperty(BaseTest.envConfig.getEnv() + "getToken.authorization"));
    }


    public String getToken(User user) throws IOException {
        JsonObject response = getPost("grant_type=password&username="+ user.getUserName() + "&password=" + user.getPassword() +"&scope=openid\"", 200);
        String accessToken = response.get("access_token").getAsString();
        System.out.println("access token was generated:  " + accessToken);
        testLog.info("access token was generated:  " + accessToken);
        return accessToken;
    }

}
