package com.verifone.utils.apiClient.createMerchant;

import com.google.gson.JsonObject;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

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


    public String createMerchant(String eoAdminId) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String id = UUID.randomUUID().toString();
        url = url + "b2bMode=false";
        JsonObject requestObj = readJsonFile(baseApiPath + "createMerchant\\create_merchant.json");
        requestObj.getAsJsonObject("data").addProperty("parentOrgID", eoAdminId);
        requestObj.getAsJsonObject("data").addProperty("rootOrgID", eoAdminId);
        requestObj.getAsJsonObject("data").addProperty("mID", id);
        id = id.replace("-", "");
        requestObj.getAsJsonObject("data").getAsJsonObject("merchantAdmin").getAsJsonArray("emails").get(0).
                getAsJsonObject().addProperty("email", id  + "@getnada.com");
        JsonObject a = getPost(requestObj, 201);
        System.out.println(a.toString());
        return id;

//        testLog.log(LogStatus.INFO, "access token was generated:  " + accessResponse.getAccess_token());

    }


    public String createMerchantWithConfirmation(String eoAdminId, String id) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        url += "?b2bMode=true";
        JsonObject requestObj = readJsonFile(baseApiPath + "createMerchant\\create_merchant.json");
        requestObj.getAsJsonObject("data").addProperty("parentOrgID", eoAdminId);
        requestObj.getAsJsonObject("data").addProperty("rootOrgID", eoAdminId);
        requestObj.getAsJsonObject("data").addProperty("mID", id);
        id = id.replace("-", "");
        requestObj.getAsJsonObject("data").getAsJsonObject("merchantAdmin").getAsJsonArray("emails").get(0).
                getAsJsonObject().addProperty("email", id + "@getnada.com");
        JsonObject a = getPost(requestObj, 201);
        JsonObject b = (JsonObject) a.getAsJsonArray("data").get(0);
        return b.get("confirmationCode").getAsString();
    }


}
