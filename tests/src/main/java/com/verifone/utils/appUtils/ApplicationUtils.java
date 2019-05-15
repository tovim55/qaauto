package com.verifone.utils.appUtils;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.verifone.utils.apiClient.BaseApi.readJsonFile;

public class ApplicationUtils {


    private static final String basePath = java.nio.file.Paths.get(
            System.getProperty("user.dir"), "src", "test", "resources").toString();

    private static String tmpAppPath = basePath + File.separator + "tempApp";
    private static String ZipTmpAppPath = basePath + File.separator + "zipTempApp";
    private static boolean zipTmpApp = new File(basePath + File.separator + "zipTempApp").mkdirs();
    private static File engageSrcFolder = new File(basePath + File.separator + "app");
    private static File destFolder = new File(tmpAppPath);
    private static File compressFolder = new File(ZipTmpAppPath);

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
        if (zipTmpApp) {
            ZipUtil.pack(destFolder, new File(ZipTmpAppPath + File.separator + appID + ".zip"));
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

        if (zipTmpApp) {
            ZipUtil.pack(destFolder, new File(ZipTmpAppPath + File.separator + appID + ".apk"));
        }

    }


    private static void renameFile(String oldFile, String newFile) {
        if (!new File(oldFile).renameTo(new File(newFile))) {
            org.testng.Assert.fail("File: " + oldFile + " Not Renamed To: " + newFile);
        }
    }

    public static void deleteDirectory() throws IOException, InterruptedException {
//        try {
        Thread.sleep(3000);
        FileUtils.cleanDirectory(new File(destFolder + File.separator + "cp-conf"));
        FileUtils.cleanDirectory(destFolder);
        FileUtils.deleteDirectory(destFolder);
        FileUtils.cleanDirectory(compressFolder);
        FileUtils.deleteDirectory(compressFolder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
