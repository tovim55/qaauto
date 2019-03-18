package com.verifone.utils.appUtils;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.verifone.utils.apiClient.BaseApi.readJsonFile;

public class ApplicationUtils {


    //    private static String tmpAppPath = System.getProperty("user.dir") + "\\src\\test\\resources\\tempApp";
    private static String tmpAppPath = java.nio.file.Paths.get(
            System.getProperty("user.dir"), "src", "test", "resources", "tempApp").toString();

    //    private static File srcFolder = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\app");
    private static File srcFolder = new File(java.nio.file.Paths.get(
            System.getProperty("user.dir"), "src", "test", "resources", "app").toString());
    private static File destFolder = new File(tmpAppPath);
    private static String appPreviosName = File.separator + "ronrontes-582168017";

    public static void createZipApp(String appID, String appName) throws IOException, InterruptedException {
        // make app copy
        FileUtils.copyDirectory(srcFolder, destFolder);
        Thread.sleep(3000);
        //rename app directory
        renameFile(tmpAppPath + appPreviosName, tmpAppPath + File.separator + appID);
        // rename pem & sig
        renameFile(tmpAppPath + File.separator + appID + appPreviosName + ".pem", tmpAppPath + File.separator + appID + File.separator + appID + ".pem");
        renameFile(tmpAppPath + File.separator + appID + appPreviosName + ".sig", tmpAppPath + File.separator + appID + File.separator + appID + ".sig");
        //rename mft file
        renameFile(tmpAppPath + appPreviosName + ".mft", tmpAppPath + File.separator + appID + ".mft");

        JsonObject file = readJsonFile(tmpAppPath + File.separator + appID + ".mft");
        // replace appID $ appName in mft file
        file.getAsJsonObject("application").addProperty("name", appName);
        file.getAsJsonObject("application").addProperty("id", appID);

//        try (FileWriter newFile = new FileWriter(tmpAppPath + File.separator + appID + ".mft")) {
        FileWriter newFile = new FileWriter(new File(tmpAppPath + File.separator + appID + ".mft"));
        newFile.write(file.toString());
        newFile.flush();
        newFile.close();
//        }

        // create zip file
        ZipUtil.pack(destFolder, new File(tmpAppPath + File.separator + appID + ".zip"));
    }


    private static void renameFile(String oldFile, String newFile) {
        if (!new File(oldFile).renameTo(new File(newFile))) {
            org.testng.Assert.fail("File: " + oldFile + " Not Renamed To: " + newFile);
        }
    }

    public static void deleteDirectory() {
        try {
            Thread.sleep(3000);
            FileUtils.deleteDirectory(destFolder);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
