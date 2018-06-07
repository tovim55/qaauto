package com.verifone.utils.apiClient.createMerchant;

import com.google.gson.JsonObject;
import com.verifone.utils.apiClient.BaseApi;
import com.verifone.utils.apiClient.getToken.GetTokenResponseSchema;

import java.io.File;
import java.io.IOException;

public class CreateMerchant extends BaseApi {

    public CreateMerchant(String accessToken, String contentType, String authorization, String correlationId) {
        url = "https://dev.dashboard.verifonecp.com/v2/mashups/createMerchant";
        baseHeaders.put(this.contentType, contentType);
        baseHeaders.put(this.authorization, authorization + accessToken);
        baseHeaders.put(this.correlationId, correlationId);
    }


    public void createMerchant(String eoAdminId) throws IOException {
        JsonObject requestObj = readJsonFile(baseApiPath + "createMerchant\\create_merchant.json");
        requestObj.getAsJsonObject("data").addProperty("parentOrgID", eoAdminId);
        requestObj.getAsJsonObject("data").addProperty("rootOrgID", eoAdminId);
        getPost(requestObj, 201);


//        testLog.log(LogStatus.INFO, "access token was generated:  " + accessResponse.getAccess_token());

    }

}
