package com.verifone.utils.apiClient.createMerchant;

import com.google.gson.JsonObject;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;
import com.verifone.utils.apiClient.getToken.GetTokenResponseSchema;

import java.io.File;
import java.io.IOException;

public class CreateMerchant extends BaseApi {

    public CreateMerchant(String accessToken, String correlationId) throws IOException {
        super();
        url = BaseTest.envConfig.getApiUrls().getCreateMerchant();
        baseHeaders.put(this.contentType, prop.getProperty("createMerchant.contentType"));
        baseHeaders.put(this.authorization, prop.getProperty("createMerchant.authorization") + accessToken);
        baseHeaders.put(this.correlationId, correlationId);
        baseHeaders.put(this.origin, prop.getProperty("createMerchant.origin"));
        baseHeaders.put(this.referer, prop.getProperty("createMerchant.referer"));
    }


    public void createMerchant(String eoAdminId) throws IOException {
        JsonObject requestObj = readJsonFile(baseApiPath + "createMerchant\\create_merchant.json");
        requestObj.getAsJsonObject("data").addProperty("parentOrgID", eoAdminId);
        requestObj.getAsJsonObject("data").addProperty("rootOrgID", eoAdminId);
        JsonObject a = getPost(requestObj, 201);
        System.out.println(a.toString());

//        testLog.log(LogStatus.INFO, "access token was generated:  " + accessResponse.getAccess_token());

    }

}
