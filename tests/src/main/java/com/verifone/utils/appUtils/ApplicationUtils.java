package com.verifone.utils.appUtils;

import com.google.gson.JsonObject;
import com.verifone.pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static com.verifone.utils.apiClient.BaseApi.readJsonFile;

public class ApplicationUtils {


    private static final String basePath = java.nio.file.Paths.get(
            System.getProperty("user.dir"), "src", "test", "resources").toString();

    private static String tmpAppPath = basePath + File.separator + "tempApp";
    private static File engageSrcFolder = new File(basePath + File.separator + "app");
    private static File destFolder = new File(tmpAppPath);
    private static String ZipTmpAppPath = basePath + File.separator + "zipTempApp";
//    private static File compressFolder = new File(ZipTmpAppPath);

    private static final String engageAppNameFinal = File.separator + "ronrontes-582168017";

    private static File androidSrcFolder = new File(basePath + File.separator + "androidApp");
    private static final String androidMftFilePath = tmpAppPath + File.separator + "cp-conf" + File.separator;

    public static void createZipApp(String appID, String appName) throws IOException, InterruptedException {
        // make app copy
        FileUtils.copyDirectory(engageSrcFolder, destFolder);
        Thread.sleep(3000);
        //rename app directory
        renameFile(tmpAppPath + engageAppNameFinal, tmpAppPath + File.separator + appID);
        // rename pem & sig
        renameFile(tmpAppPath + File.separator + appID + engageAppNameFinal + ".pem", tmpAppPath + File.separator + appID + File.separator + appID + ".pem");
        renameFile(tmpAppPath + File.separator + appID + engageAppNameFinal + ".sig", tmpAppPath + File.separator + appID + File.separator + appID + ".sig");
        //rename mft file
        renameFile(tmpAppPath + engageAppNameFinal + ".mft", tmpAppPath + File.separator + appID + ".mft");

        JsonObject file = readJsonFile(tmpAppPath + File.separator + appID + ".mft");
        // replace appID $ appName in mft file
        file.getAsJsonObject("application").addProperty("name", appName);
        file.getAsJsonObject("application").addProperty("id", appID);

        FileWriter newFile = new FileWriter(new File(tmpAppPath + File.separator + appID + ".mft"));
        newFile.write(file.toString());
        newFile.flush();
        newFile.close();
//        }

        // create zip file
        createZipFile(appID, ".zip");
    }

    private static void createZipFile(String appID, String compressType) throws InterruptedException {
        boolean isCreate = new File(basePath + File.separator + "zipTempApp").mkdirs();
        Thread.sleep(3000);
        File dir = new File(ZipTmpAppPath);
        if (dir.exists() && dir.isDirectory()) {
            BasePage.testLog.info("New directory created in: " + isCreate);
            ZipUtil.pack(destFolder, new File(ZipTmpAppPath + File.separator + appID + compressType));
            BasePage.testLog.info("New " + compressType + "file created in: " + isCreate);
            Thread.sleep(10000);
        } else {
            BasePage.testLog.info("Create directory failed: " + isCreate);
        }
    }

    public static void createZipAppAndroid(String appID, String appName) throws IOException, InterruptedException {
        FileUtils.copyDirectory(androidSrcFolder, destFolder);
        Thread.sleep(3000);
        renameFile(androidMftFilePath + "test20031-282146004.mft", androidMftFilePath + appID + ".mft");
        JsonObject file = readJsonFile(androidMftFilePath + appID + ".mft");
        file.getAsJsonObject("application").addProperty("name", appName);
        file.getAsJsonObject("application").addProperty("id", appID);
        FileWriter newFile = new FileWriter(new File(androidMftFilePath + appID + ".mft"));
        newFile.write(file.toString());
        newFile.flush();
        newFile.close();

        createZipFile(appID, ".apk");

    }


    private static void renameFile(String oldFile, String newFile) {
        if (!new File(oldFile).renameTo(new File(newFile))) {
            org.testng.Assert.fail("File: " + oldFile + " Not Renamed To: " + newFile);
        }
    }

    public static void deleteDirectory() throws InterruptedException {
        File compressFolder = new File(ZipTmpAppPath);
            try {
                Thread.sleep(4000);
                FileUtils.cleanDirectory(destFolder);
                BasePage.testLog.info("Clean dir: " + destFolder);
            } catch (IllegalArgumentException | FileNotFoundException e) {
                BasePage.testLog.info("Failed to clean dir: " + destFolder);
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
                FileUtils.forceDelete(destFolder);
                BasePage.testLog.info("Delete dir: " + destFolder);
            } catch (IllegalArgumentException | FileNotFoundException e) {
                BasePage.testLog.info("Failed to delete dir: " + destFolder);
                e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }
        try {
                FileUtils.cleanDirectory(compressFolder);
                BasePage.testLog.info("Clean compress dir: " + compressFolder);
            } catch (IllegalArgumentException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }
        try {
                FileUtils.forceDelete(compressFolder);
                BasePage.testLog.info("Delete compress dir: " + compressFolder);
            } catch (IllegalArgumentException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
