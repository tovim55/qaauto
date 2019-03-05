package com.verifone.infra;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiUrls {


    private String getToken;
    private String getEoAdminData;
    private String createMerchant;
    private String getTosId;
    private String acceptTos;
    private String updatePassword;
    private String getAgreement;
    private String acceptAgreement;
    private String resendInvitation;
    private String createDcOrg;
    private String getVersions;


    public ApiUrls(String env, Properties prop) throws IOException {
        env = env + ".";
        getVersions = prop.getProperty(env + "getVersions");
        getToken = prop.getProperty(env + "getToken");
        getEoAdminData = prop.getProperty(env + "getEoAdminData");
        createMerchant = prop.getProperty(env + "createMerchant");
        getTosId = prop.getProperty(env + "getTosId");
        acceptTos = prop.getProperty(env + "getToken");
        updatePassword = prop.getProperty(env + "updatePassword");
        acceptAgreement = prop.getProperty(env + "acceptAgreement");
        resendInvitation = prop.getProperty(env + "resendInvitation");
        createDcOrg = prop.getProperty(env + "createDcOrg");

    }


    public String getGetVersions() {
        return getVersions;
    }

    public String getGetToken() {
        return getToken;
    }

    public void setGetToken(String getToken) {
        this.getToken = getToken;
    }

    public String getGetEoAdminData() {
        return getEoAdminData;
    }

    public void setGetEoAdminData(String getEoAdminData) {
        this.getEoAdminData = getEoAdminData;
    }

    public String getCreateMerchant() {
        return createMerchant;
    }

    public String getCreateDcOrg() {
        return createDcOrg;
    }

    public void setCreateMerchant(String createMerchant) {
        this.createMerchant = createMerchant;
    }

    public String getGetTosId() {
        return getTosId;
    }

    public void setGetTosId(String getTosId) {
        this.getTosId = getTosId;
    }

    public String getAcceptTos() {
        return acceptTos;
    }

    public void setAcceptTos(String acceptTos) {
        this.acceptTos = acceptTos;
    }

    public String getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(String updatePassword) {
        this.updatePassword = updatePassword;
    }

    public String getGetAgreement() {
        return getAgreement;
    }

    public void setGetAgreement(String getAgreement) {
        this.getAgreement = getAgreement;
    }

    public String getAcceptAgreement() {
        return acceptAgreement;
    }

    public void setAcceptAgreement(String acceptAgreement) {
        this.acceptAgreement = acceptAgreement;
    }

    public String getResendInvitation() {
        return resendInvitation;
    }

    public void setResendInvitation(String resendInvitation) {
        this.resendInvitation = resendInvitation;
    }
}