package com.verifone.infra;

import java.util.Properties;

import com.verifone.infra.User;

public class Credentials {

    private String env;
    private Properties prop;


    public Credentials(String env, Properties prop) {
        this.env = env + ".";
        this.prop = prop;
    }

    public User getEOAdminSupport() {
        return new User(prop.getProperty(env + "EOAdminSupport"), prop.getProperty(env + "EOAdminSupportPass"));
    }

    public User getEOAdmin() {
        return new User(prop.getProperty(env + "EOAdmin"), prop.getProperty(env + "EOAdminPass"));
    }

    public User getEOMerchantMeneger() {
        return new User(prop.getProperty(env + "EOMerchantManager"), prop.getProperty(env + "EOMerchantManagerPass"));
    }

    public User getEOMerchant() {
        return new User(prop.getProperty(env + "EOMerchant"), prop.getProperty(env + "EOMerchantPass"));
    }

    public User getDevSupportAdmin() {
        return new User(prop.getProperty(env + "DevSupportAdmin"), prop.getProperty(env + "DevSupportAdminPass"));
    }

    public User getDevAdmin() {
        return new User(prop.getProperty(env + "DevAdmin"), prop.getProperty(env + "DevAdminPass"));
    }

    public User getDevBasic() {
        return new User(prop.getProperty(env + "DevBasic"), prop.getProperty(env + "DevBasicPass"));
    }


}
