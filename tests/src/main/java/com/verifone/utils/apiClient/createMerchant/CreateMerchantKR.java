package com.verifone.utils.apiClient.createMerchant;

import com.google.gson.JsonObject;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;
import com.verifone.utils.apiClient.Headers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class CreateMerchantKR extends BaseApi {

    public CreateMerchantKR(String accessToken, String correlationId) throws IOException {
        super();
        url = BaseTest.envConfig.getApiUrls().getCreateMerchant();
        url = url + "?b2bMode=false";
        baseHeaders.put(Headers.CONTENT_TYPE.get(), prop.getProperty("createMerchant.contentType"));
        baseHeaders.put(Headers.AUTHORIZATION.get(), prop.getProperty("createMerchant.authorization") + accessToken);
        baseHeaders.put(Headers.CORRELATION_ID.get(), correlationId);
        baseHeaders.put(Headers.ORIGIN.get(), prop.getProperty("createMerchant.origin"));
        baseHeaders.put(Headers.REFERER.get(), prop.getProperty("createMerchant.referer"));
    }


    public String createMerchantKR(String eoAdminId) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String id = UUID.randomUUID().toString();

        JsonObject requestObj = readJsonFile(baseApiPath + "createMerchant\\create_merchantKR.json");
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

}
