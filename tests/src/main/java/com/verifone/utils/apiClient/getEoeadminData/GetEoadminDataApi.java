package com.verifone.utils.apiClient.getEoeadminData;

import com.google.gson.JsonObject;
import com.verifone.utils.apiClient.BaseApi;

import java.io.IOException;

public class GetEoadminDataApi extends BaseApi {

    public GetEoadminDataApi(String accessToken, String contentType, String authorization, String correlationId, String accept){
        url = "https://dev.account.verifonecp.com/verifone-identity/api/v2/users/profile";
        baseHeaders.put(this.contentType, contentType);
        baseHeaders.put(this.authorization, authorization + accessToken);
        baseHeaders.put(this.correlationId, correlationId);
        baseHeaders.put(this.accept, accept);
    }


    public String getData() throws IOException {

        JsonObject response =  getRequest(200);
        String eoAdminId = getAtter(response,  new String[]{"data", "organizations"}).get("id").getAsString();
        System.out.println("Eo admin ID  "  + eoAdminId);
        return eoAdminId;


    }
}
