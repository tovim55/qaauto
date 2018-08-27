package com.verifone.utils.appUtils;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class Application {


    public static final String appPath = System.getProperty("user.dir") + "\\src\\test\\resources\\tempApp";
    public static final String iconPath = System.getProperty("user.dir") + "\\src\\test\\resources\\app-store-icon.png";
    private String appName;
    private String id;
    private String version;
    private String description5Words;
    private String description;

    public Application(String appName, String appID, String version, String description5Words,
                       String description) {
        this.appName = appName;
        this.id = appID;
        this.version = version;
        this.description5Words = description5Words;
        this.description = description;
    }


    public Application(){
        this.appName = randomAlphabetic(5);
        this.id = "";
        this.version = "1.1.0";
        this.description5Words = randomAlphabetic(5);
        this.description = randomAlphabetic(10);
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription5Words() {
        return description5Words;
    }

    public void setDescription5Words(String description5Words) {
        this.description5Words = description5Words;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}