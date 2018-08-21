package com.verifone.utils.appUtils;

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
        renameFile(tmpAppPath + appPreviosName, tmpAppPath + "\\" + appID);
        // rename pem & sig
        renameFile(tmpAppPath + "\\" + appID + appPreviosName + ".pem", tmpAppPath + "\\" + appID + "\\" + appID + ".pem");
        renameFile(tmpAppPath + "\\" + appID + appPreviosName + ".sig", tmpAppPath + "\\" + appID + "\\" + appID + ".sig");
        //rename mft file
        renameFile(tmpAppPath + appPreviosName + ".mft", tmpAppPath + "\\" + appID + ".mft");

        JsonObject file = readJsonFile(tmpAppPath + "\\" + appID + ".mft");
        // replace appID $ appName in mft file
        file.getAsJsonObject("application").addProperty("name", appName);
        file.getAsJsonObject("application").addProperty("id", appID);

//        try (FileWriter newFile = new FileWriter(tmpAppPath + "\\" + appID + ".mft")) {
        FileWriter newFile = new FileWriter(new File(tmpAppPath + "\\" + appID + ".mft"));
        newFile.write(file.toString());
        newFile.flush();
        newFile.close();
//        }

        // create zip file
        ZipUtil.pack(destFolder, new File(tmpAppPath + "\\" + appID + ".zip"));
    }


    private static void renameFile(String oldFile, String newFile) {
        if (!new File(oldFile).renameTo(new File(newFile))) {
            org.testng.Assert.fail("File: " + oldFile + " Not Renamed To: " + newFile);
        }
    }

    public static void deleteDirectory() {
        try {
            Thread.sleep(5000);
            FileUtils.deleteDirectory(destFolder);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
