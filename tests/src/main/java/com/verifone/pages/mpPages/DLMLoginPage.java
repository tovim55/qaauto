package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import com.verifone.tests.mpTests.ZipArchiver;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DLMLoginPage extends BasePage {

    private final static String title = "APK Signing";
    private final static String url = "http://ordlxwolf/signapk.php";

    private By btnBrowse = By.xpath("//*[@type = 'file']");
    private By btnSubmit = By.xpath("//*[@type = 'submit']");
    private By linkDownload = By.xpath("//a");

    //private static String packageName = "7augRes-35222748364-1.0.0";
    //private static String instPackageName = "cp-7augRes-35222748364-inst";
    //private static String downloadDirectory = System.getProperty("user.home") + File.separator + "Downloads";
    //private static String cpSignedDir = downloadDirectory + File.separator + "CG_Signed_Package";
    //private static String instDir = cpSignedDir + File.separator + "INSTALL" + File.separator + "ANDROID";
    //private static String unInstDir = cpSignedDir + File.separator + "UNINSTALL" + File.separator + "ANDROID";
    //private static File inputFolder = new File(instDir + File.separator + instPackageName);
    //private static File outputFolder = new File(instDir + File.separator + instPackageName + ".zip");

    private final static String getApplicationID = BaseTest.envConfig.getApplicationID();
    private final static String getApplicationVersion = BaseTest.envConfig.getApplicationVersion();

    private static String userDir = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "apps";
    private static String downloadFilePath = System.getProperty("user.dir") + File.separator + userDir;
    private static String downloadedZipName = downloadFilePath + File.separator + getApplicationID + "-" + getApplicationVersion;
    private static String instZipFileName = downloadedZipName + File.separator + "INSTALL" + File.separator + "ANDROID" + File.separator + "cp-" + getApplicationID + "-inst";

    public DLMLoginPage() {
        super(url, title);
        navigate();
    }

    public void getV2SignedPackage() throws Exception {
        WebElement elementBtnBrowse = driver.findElement(btnBrowse);
        testLog.info("Path of the apk for V2 signing : " + instZipFileName + File.separator + "cp-" + getApplicationID + ".apk");
        elementBtnBrowse.sendKeys(instZipFileName + File.separator + "cp-" + getApplicationID + ".apk");

        ExpectedConditions.elementToBeClickable(btnSubmit);
        click(btnSubmit);

        System.out.println("downloadFilePath : " + downloadFilePath);

        File isAPKExistsInDir = new File(downloadFilePath + File.separator + "cp-" + getApplicationID + ".apk");
        if (isAPKExistsInDir.exists()) {
            testLog.info("File" + "cp-" + getApplicationID + ".apk" + "exists : Delete the file before downloading it.");
            isAPKExistsInDir.delete();
        }

        ExpectedConditions.elementToBeClickable(linkDownload);
        click(linkDownload);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until((ExpectedCondition<Boolean>) webDriver -> isAPKExistsInDir.exists());

       /* if (wait.until((ExpectedCondition<Boolean>) webDriver -> isAPKExistsInDir.exists())) {

            File isAPKPresentInInstFolder = new File(instDir + File.separator + instPackageName + File.separator + "cp-7augRes-35222748364.apk");
            if (isAPKPresentInInstFolder.exists()) {
                isAPKPresentInInstFolder.delete();

                File copyDir = new File(instDir + File.separator + instPackageName);
                FileUtils.copyFileToDirectory(isAPKExistsInDir, copyDir);
            }
        }*/


        //File originalZip = new File("C:\\Users\\Prashant\\Desktop\\CBA_Upload\\V2\\Carbon016-144132322-1.1.0.zip");
        //File addSigned = new File("C:\\Users\\Prashant\\Desktop\\CBA_Upload\\V2\\v2\\cp-CarbonMob-885506042.apk");

        //addCpConfInZip(originalZip, addSigned);

        // zipFile(inputFolder, outputFolder); //**

        //FileUtils.deleteDirectory(inputFolder);
        //deleteDirectory(new File(String.valueOf(inputFolder)));
        // FileUtils.deleteDirectory(inputFolder);

        /*List<String> zipFileList = new ArrayList();
        zipFileList.add(outputFolder.getAbsolutePath());
        zipFileList.add(unInstDir + File.separator + "cp-7augRes-35222748364-un.zip");

        String copyFileName = packageName + ".zip";
        ZipArchiver.zip(zipFileList, cpSignedDir, copyFileName); *///**

       /* File destFileToBeCOpy = new File(cpSignedDir + File.separator + copyFileName);
        if (destFileToBeCOpy.exists()) {
            System.out.println("destFileToBeCOpy " + destFileToBeCOpy.getAbsolutePath());
        }
        File downloadDir = new File(downloadDirectory);
        FileUtils.copyFileToDirectory(destFileToBeCOpy, downloadDir);*/
    }

    /*public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                f.delete();
            }
            return true;
        }

        System.out.println("Directory is not present");
        return false;
    }*/


    public static void zipFile(File inputFolder, File outputFolder) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outputFolder)));
        BufferedInputStream in = null;

        byte[] bytesIn = new byte[4096];
        String files[] = inputFolder.list();
        for (int j = 0; j < files.length; j++) {
            in = new BufferedInputStream(new FileInputStream
                    (inputFolder.getPath() + "/" + files[j]), 1000);
            out.putNextEntry(new ZipEntry(files[j]));
            int totalcount;
            while ((totalcount = in.read(bytesIn, 0, 1000)) != -1) {
                out.write(bytesIn, 0, totalcount);
            }
            out.closeEntry();
        }
        out.flush();
        out.close();

    }

    /**
     * method : This method describe the package unzipping flow until the apk available for V2 signing.
     */
    public static void unZipCGSignedPackage() throws Exception {

        String zipFilePath = downloadedZipName + ".zip";
        String destDirectory = downloadedZipName;

        testLog.info("Source zip package : " + zipFilePath);
        testLog.info("destDirectory : " + destDirectory);

        unzip(zipFilePath, destDirectory);

        zipFilePath = instZipFileName + ".zip";
        destDirectory = instZipFileName;

        testLog.info("Source inst zip package : " + zipFilePath);
        testLog.info("destDirectory : " + destDirectory);

        unzip(zipFilePath, destDirectory);
    }

    private static final int BUFFER_SIZE = 4096;

    public static void unzip(String zipFilePath, String destDirectory)
            throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));

        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            } else {
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    private static void extractFile(ZipInputStream zipIn, String filePath)
            throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            new File(targetFile.getParent()).mkdirs();
            targetFile.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte['?'];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
