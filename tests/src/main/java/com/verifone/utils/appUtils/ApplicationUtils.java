package com.verifone.utils;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.verifone.utils.apiClient.BaseApi.readJsonFile;

public class ApplicationUtils {


    private static String tmpAppPath = System.getProperty("user.dir") + "\\src\\test\\resources\\tempApp";
    private static File srcFolder = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\app");
    private static File destFolder = new File(tmpAppPath);
    private static String appPreviosName = "\\ronrontes-582168017";

    public static void createZipApp(String appID, String appName) throws IOException, InterruptedException {
        // make app copy
        FileUtils.copyDirectory(srcFolder, destFolder);
        Thread.sleep(3000);
        //rename app directory
        new File(tmpAppPath + appPreviosName).renameTo(new File(tmpAppPath + "\\" + appID));
        // rename pem & sig
        new File(tmpAppPath + "\\" + appID + appPreviosName + ".pem").renameTo(new File(tmpAppPath + "\\" + appID + "\\" + appID + ".pem"));
        new File(tmpAppPath + "\\" + appID + appPreviosName + ".sig").renameTo(new File(tmpAppPath + "\\" + appID + "\\" + appID + ".sig"));
        //rename mft file
        new File(tmpAppPath + appPreviosName + ".mft").renameTo(new File(tmpAppPath + "\\" + appID + ".mft"));

        JsonObject file = readJsonFile(tmpAppPath + "\\" + appID + ".mft");
        // replace appID $ appName in mft file
        file.getAsJsonObject("application").addProperty("name", appName);
        file.getAsJsonObject("application").addProperty("id", appName);

        try (FileWriter newFile = new FileWriter(tmpAppPath + "\\" + appID + ".mft")) {
            newFile.write(file.toString());
        }
        // create zip file
        ZipUtil.pack(destFolder, new File(tmpAppPath + "\\" + appID + ".zip"));
    }

}
