package com.verifone.infra;

import java.util.Properties;
import com.verifone.infra.User;

public class Credentials {


    private static User EOAdminSupport;
    private static User EOAdmin;
    private static User EOMerchantMeneger;
    private static User EOMerchant;
    private static User DevSupportAdmin;
    private static User DevAdmin;
    private static User DevBasic;
    private static User FakeUser;



    public Credentials(String env, Properties prop) {
        env = env + ".";
        EOAdminSupport = new User(prop.getProperty(env + "EOAdminSupport"), prop.getProperty(env + "EOAdminSupportPass"));

        EOAdmin = new User(prop.getProperty(env + "EOAdmin"), prop.getProperty(env + "EOAdminPass"));

        EOMerchantMeneger = new User(prop.getProperty(env + "EOMerchantManager"), prop.getProperty(env + "EOMerchantManagerPass"));

        EOMerchant= new User (prop.getProperty(env + "EOMerchant"), prop.getProperty(env + "EOMerchantPass"));

        DevSupportAdmin = new User(prop.getProperty(env + "DevSupportAdmin"),prop.getProperty(env + "DevSupportAdminPass"));

        DevAdmin = new User(prop.getProperty(env + "DevAdmin"), prop.getProperty(env + "DevAdminPass"));

        DevBasic = new User( prop.getProperty(env + "DevBasic"),prop.getProperty(env + "DevBasicPass"));
    }

    public User getEOAdminSupport() {
        return EOAdminSupport;
    }

    public User getEOAdmin() {
        return EOAdmin;
    }

    public User getEOMerchantMeneger() {
        return EOMerchantMeneger;
    }

    public User getEOMerchant() {
        return EOMerchant;
    }

    public User getDevSupportAdmin() {
        return DevSupportAdmin;
    }

    public User getDevAdmin() {
        return DevAdmin;
    }

    public User getDevBasic() {
        return DevBasic;
    }


}
