package com.verifone.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//import com.verifone.entities.*;
public class EnvConfig {

    private String env;
    private String portal;
    private ApiUrls apiUrls;
    private String webUrl;
    private Credentials credentials;
    private Properties prop = new Properties();

    private String appName;
    private String oneTimeAppName;
    private String freeEditionApp;
    private String monthlyRecurringApp;
    private String yearlyRecurringApp;
    private String oneTimePayFreeTrialApp;
    private String monthlyRecurringFreeTrialApp;

//    private User user;

    public EnvConfig(String env, String portal) throws IOException {
        this.env = env;
        this.portal = portal;
        FileInputStream ip = new FileInputStream(java.nio.file.Paths.get(
                new File(System.getProperty("user.dir")).getParent(),
                "infra", "src", "main", "java", "com", "verifone", "infra",
                "config.properties").toString());
        prop.load(ip);
        setEnv();
    }


    private void setEnv() throws IOException {
        this.apiUrls = new ApiUrls(env, prop);
        this.credentials = new Credentials(env, prop);
        this.webUrl = prop.getProperty(portal + "." + env);
    }


    public ApiUrls getApiUrls() {
        return apiUrls;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getEnv(){
        return env;
    }

    public String getAppName() {
        return prop.getProperty(env + "." + "MPGetAppName");
    }

    public Credentials getCredentials(){return credentials;}

    public String getOneTimeAppName() {
        return prop.getProperty(env + "." + "MPPurchaseOneTimeApp");
    }

    public String getFreeEditionApp() {
        return prop.getProperty(env + "." + "MPPurchaseFreeEditionApp");
    }

    public String getMonthlyRecurringApp() {
        return prop.getProperty(env + "." + "MPMonthlyRecurringApp");
    }

    public String getYearlyRecurringApp() {
        return prop.getProperty(env + "." + "MPYearlyRecurringApp");
    }

    public String getOneTimePayFreeTrialApp() {
        return prop.getProperty(env + "." + "MPPurchaseOneTimePayFreeTrialApp");
    }

    public String getMonthlyRecurringFreeTrialApp() {
        return prop.getProperty(env + "." + "MPMonthlyRecurringFreeTrialApp");
    }
}
