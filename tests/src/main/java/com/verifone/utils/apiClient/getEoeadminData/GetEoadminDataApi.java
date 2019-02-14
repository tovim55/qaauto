package com.verifone.utils.apiClient.getEoeadminData;

import com.google.gson.JsonObject;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;
import com.verifone.utils.apiClient.Headers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class GetEoadminDataApi extends BaseApi {

    public GetEoadminDataApi(String accessToken, String correlationId) throws IOException {
        super();
        url = BaseTest.envConfig.getApiUrls().getGetEoAdminData();
        baseHeaders.put(Headers.CONTENT_TYPE.get(), prop.getProperty("edminData.contentType"));
        baseHeaders.put(Headers.AUTHORIZATION.get(), prop.getProperty("edminData.authorization") + accessToken);
        baseHeaders.put(Headers.CORRELATION_ID.get(), correlationId);
        baseHeaders.put(Headers.ACCEPT.get(), prop.getProperty("edminData.accept"));
    }


    public String getData() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        JsonObject response =  getRequest(200);
        String eoAdminId = getAtter(response,  new String[]{"data", "organizations"}).get("id").getAsString();
        System.out.println("Eo admin ID  "  + eoAdminId);
        return eoAdminId;


    }
}
