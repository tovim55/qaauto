package com.verifone.tests.cpTests;

import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;


public class DevUploadApp extends BaseTest {


    @Test(testName = "Dev admin upload an app", description = "Dev admin upload an app")
    public void devUploadApp() throws IOException {
        String appID = "test1234";
        String tmpApp = System.getProperty("user.dir") + "\\src\\test\\resources\\tempApp";
        File srcFolder = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\app");
        File destFolder = new File(tmpApp);
        FileUtils.copyDirectory(srcFolder,destFolder);
        //rename app directory
        new File(tmpApp + "\\ronrontes-582168017").renameTo(new File(tmpApp + "\\" + appID));
        // rename pem & sig
        new File(tmpApp + "\\" + appID + "\\ronrontes-582168017.pem").renameTo(new File(tmpApp + "\\" + appID + "\\" + appID + ".pem"));
        new File(tmpApp + "\\" + appID + "\\ronrontes-582168017.sig").renameTo(new File(tmpApp + "\\" + appID + "\\" + appID + ".sig"));
        //rename mft file
        new File(tmpApp + "\\ronrontes-582168017.mft").renameTo(new File(tmpApp + "\\" + appID + ".mft"));


//        FileUtils.moveFile(
//                FileUtils.getFile(tmpApp + "oldname.txt"),
//                FileUtils.getFile("src/test/resources/renamed.txt"));

    }
}
