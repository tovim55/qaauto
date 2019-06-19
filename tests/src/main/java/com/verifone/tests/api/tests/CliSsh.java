package com.verifone.tests.api.tests;

import com.aventstack.extentreports.ExtentTest;
import com.jcraft.jsch.*;
import com.verifone.infra.Company;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import static com.verifone.tests.steps.Steps.devLogin;
import static com.verifone.tests.steps.Steps.devLoginFillCompany;
import static com.verifone.tests.steps.Steps.devSignUp;

public class CliSsh extends BaseTest {


//    private static String dataFile = System.getProperty("user.dir") + "\\src\\test\\resources\\cloudApi.xls";


//    @Parameters({"user", "password", "host", "commands"} )
//    @Test(testName = "Developer Basic Add Company", description = "Aon", groups = {"CP-Portal"})
//    public void test(String user, String password, String host, String commands) throws Exception {
////        String user = "john";
////        String password = "mypassword";
////        String host = "192.168.100.23";
//        int port = 22;
////        String remoteFile = "/home/john/test.txt";
//
//        try {
//            JSch jsch = new JSch();
//            Session session = jsch.getSession(user, host, port);
//            session.setPassword(password);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.run();
//            System.out.println("Establishing Connection...");
//            session.connect();
//            System.out.println("Connection established.");
//            System.out.println("Crating SFTP Channel.");
//            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
//            sftpChannel.connect();
//            System.out.println("SFTP Channel created.");
//
//            InputStream inputStream = sftpChannel.get(remoteFile);
//
//            try (Scanner scanner = new Scanner(new InputStreamReader(inputStream))) {
//                while (scanner.hasNextLine()) {
//                    String line = scanner.nextLine();
//                    System.out.println(line);
//                }
//            }
//        } catch (JSchException | SftpException e) {
//            e.printStackTrace();
//        }
//        System.out.println("portal" + portal);
//}

}
