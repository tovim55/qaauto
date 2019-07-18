package com.verifone.infra;

import java.util.Properties;

public class Credentials {

    private String env;
    private Properties prop;


    public Credentials(String env, Properties prop) {
        this.env = env + ".";
        this.prop = prop;
    }

    public User getEOAdminSupport() {
        return new User(prop.getProperty(env + "EOAdminSupport"), prop.getProperty(env + "EOAdminSupportName"), prop.getProperty(env + "EOAdminSupportPass"), prop.getProperty(env + "EOAdminSupportSecurityAnswer"));
    }

    public User getMPMerchantAdmin() {
        return new User(prop.getProperty(env + "MPMerchantAdmin"), prop.getProperty(env + "MPMerchantAdminPass"));
    }

    public User getVHQTestUserAdmin() {
        return new User(prop.getProperty(env + "VHQTestUserAdmin"), prop.getProperty(env + "VHQTestUserAdminPass"));
    }

    public User getVHQMumbaiUserAdmin() {
        return new User(prop.getProperty(env + "VHQMumbaiUserAdmin"), prop.getProperty(env + "VHQMumbaiUserAdminPass"));
    }

    public User getEOAdmin() {
        return new User(prop.getProperty(env + "EOAdmin"), prop.getProperty(env + "EOAdminPass"));
    }

    public User getEOMerchantManager() {
        return new User(prop.getProperty(env + "EOMerchantManager"), prop.getProperty(env + "EOMerchantManagerPass"));
    }

    public User getEOMerchantForgotPassword() {
        return new User(prop.getProperty(env + "EOMerchantForgotPassword"), "");
    }

    public User getEODevAppManager() {
        return new User(prop.getProperty(env + "EODevAppManager"), prop.getProperty(env + "EODevAppManagerPass"));
    }

    public User getEOMerchant() {
        return new User(prop.getProperty(env + "EOMerchant"), prop.getProperty(env + "EOMerchantPass"));
    }

    public User getDevSupportAdmin() {
        return new User(prop.getProperty(env + "DevSupportAdmin"), prop.getProperty(env + "DevSupportAdminName"),
                prop.getProperty(env + "DevSupportAdminPass"), prop.getProperty(env + "DevSupportAdminSecurityAnswer"));
    }

    public User getDevAdmin() {
        return new User(prop.getProperty(env + "DevAdmin"), prop.getProperty(env + "DevAdminPass"));
    }

    public User getDevBasic() {
        return new User(prop.getProperty(env + "DevBasic"), prop.getProperty(env + "DevBasicPass"));
    }

    public User getCGPortal() {
        return new User(prop.getProperty(env + "CGDevAdmin"), prop.getProperty(env + "CGDevAdminPass"));
    }

    public User getMPAssignUser() {
        return new User(prop.getProperty(env + "MPAssignUser"), prop.getProperty(env + "MPMerchantAdminPass"));
    }
}
