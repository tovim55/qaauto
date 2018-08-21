package com.verifone.tests.cpTests;

import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.verifone.tests.steps.Steps.createApp;
import static com.verifone.tests.steps.Steps.devLogin;


public class DevUploadApp extends BaseTest {


    @Test(testName = "Dev admin upload an app", description = "Dev admin upload an app")
    public void devUploadAppUI() throws IOException, InterruptedException, AWTException {
        Company dev =  new Company();
        dev.setUserName("doba@cmail.club");
        dev.setPassword("Welcome3#");
        devLogin(dev);
        createApp();


//        String appID = "test1234";
//        String appName = "lala";
//        String tmpApp = System.getProperty("user.dir") + "\\src\\test\\resources\\tempApp";
//        File srcFolder = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\app");
//        File destFolder = new File(tmpApp);
//        FileUtils.copyDirectory(srcFolder,destFolder);
//        //rename app directory
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(new File(tmpApp + "\\ronrontes-582168017").renameTo(new File(tmpApp + "\\" + appID)));
//        // rename pem & sig
//        new File(tmpApp + "\\" + appID + "\\ronrontes-582168017.pem").renameTo(new File(tmpApp + "\\" + appID + "\\" + appID + ".pem"));
//        new File(tmpApp + "\\" + appID + "\\ronrontes-582168017.sig").renameTo(new File(tmpApp + "\\" + appID + "\\" + appID + ".sig"));
//        //rename mft file
//        new File(tmpApp + "\\ronrontes-582168017.mft").renameTo(new File(tmpApp + "\\" + appID + ".mft"));
//        JsonObject file = readJsonFile(tmpApp + "\\" + appID + ".mft");
//        // replace appID $ appName in mft file
//        file.getAsJsonObject("application").addProperty("name", appName);
//        file.getAsJsonObject("application").addProperty("id", appName);
//
//        try (FileWriter newFile = new FileWriter(tmpApp + "\\" + appID + ".mft")) {
//            newFile.write(file.toString());
//
//        }
//        ZipUtil.pack(destFolder, new File(tmpApp + "\\" + appID + ".zip"));


//        FileUtils.moveFile(
//                FileUtils.getFile(tmpApp + "oldname.txt"),
//                FileUtils.getFile("src/test/resources/renamed.txt"));

    }
}
