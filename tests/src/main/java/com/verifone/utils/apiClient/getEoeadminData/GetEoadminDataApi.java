package com.verifone.utils.apiClient.getEoeadminData;

import com.google.gson.JsonObject;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;

import java.io.IOException;

public class GetEoadminDataApi extends BaseApi {

    public GetEoadminDataApi(String accessToken, String correlationId) throws IOException {
        super();
        url = BaseTest.envConfig.getApiUrls().getGetEoAdminData();
        baseHeaders.put(this.contentType, prop.getProperty("edminData.contentType"));
        baseHeaders.put(this.authorization, prop.getProperty("edminData.authorization") + accessToken);
        baseHeaders.put(this.correlationId, correlationId);
        baseHeaders.put(this.accept, prop.getProperty("edminData.accept"));
    }


    public String getData() throws IOException {

        JsonObject response =  getRequest(200);
        String eoAdminId = getAtter(response,  new String[]{"data", "organizations"}).get("id").getAsString();
        System.out.println("Eo admin ID  "  + eoAdminId);
        return eoAdminId;


    }
}
