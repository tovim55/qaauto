package com.verifone.utils.apiClient.getEoeadminData;

import javax.json.JsonObject;
import java.util.List;

public class GetEodminDataRespnseSchema {


    private String id;
    private String name;
    private List<Object> addresses;

    @Override
    public String toString() {
        return "GetEodminDataRespnseSchema{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addresses=" + addresses +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Object> addresses) {
        this.addresses = addresses;
    }
}
