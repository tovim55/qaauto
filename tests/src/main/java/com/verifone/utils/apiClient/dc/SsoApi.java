package com.verifone.utils.apiClient.dc;

import com.google.gson.JsonObject;
import com.verifone.infra.User;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class SsoApi extends BaseApi {

    public SsoApi(String accessToken) throws IOException {
        super();
        baseHeaders.put(this.contentType, prop.getProperty("dc.contentType"));
        baseHeaders.put(this.authorization, prop.getProperty("dc.authorization") + accessToken);
    }


    public void createDcOrg(User user) throws IOException {
        url = BaseTest.envConfig.getApiUrls().getCreateDcOrg();
        JsonObject requestObj = readJsonFile(baseApiPath + "dc\\create_dc_org.json");
        requestObj.getAsJsonObject("data").addProperty("vhqCustomerId", user.getVhqCustomerId());
        requestObj.getAsJsonObject("data").addProperty("name", user.getFirstName());
        requestObj.getAsJsonObject("data").getAsJsonObject("contact")
                .getAsJsonObject("email").addProperty("email", user.getUserName());
        JsonObject result = getPost(requestObj, 201);
        user.setInternalId(result.getAsJsonArray("data").get(0).getAsJsonObject().get("id").getAsString());
        testLog.info("Response data: " + result.toString());
        testLog.info("SsoApi org ID : " + user.getInternalId());

    }


    public void createDcUser(User dcUser, User dcOrg) throws IOException {
        url = BaseTest.envConfig.getApiUrls().getCreateDcOrg() +
                "/" + dcOrg.getInternalId() + "/persons?createInIS=true";
        JsonObject requestObj = readJsonFile(baseApiPath + "dc\\create_dc_user.json");
        requestObj.getAsJsonObject("data").getAsJsonArray("emails").get(0).getAsJsonObject()
                .addProperty("email", dcUser.getUserName());
        JsonObject result = getPost(requestObj, 201);
        testLog.info("Response data: " + result.toString());
        dcUser.setInternalId(
                result.getAsJsonArray("data").get(0).getAsJsonObject().get("id").getAsString());


    }


}
