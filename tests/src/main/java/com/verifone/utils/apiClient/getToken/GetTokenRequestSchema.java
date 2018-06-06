package com.verifone.utils.apiClient.getToken;

public class GetTokenRequestSchema {


    private String data;

    public GetTokenRequestSchema(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }


}
