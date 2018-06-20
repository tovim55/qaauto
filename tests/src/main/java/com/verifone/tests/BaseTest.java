package com.verifone.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import com.verifone.infra.EnvConfig;
import com.verifone.pages.BasePage;
import com.verifone.utils.apiClient.BaseApi;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;


import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public abstract class BaseTest {


    public Date date = new Date();
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    public String reportLocation = "C:\\reportTestNgResults\\" + dateFormat.format(date) + ".html";
    public static ExtentTest childTest, parentTest;
    public ExtentReports logger = new ExtentReports(reportLocation, true);
    public Boolean testStepPassed = true;
    public String capScreenShootPath;
    public ExtentTest testLog;
    public Properties prop = new Properties();
    public static EnvConfig envConfig;


    @Parameters({"browserType"})
    @BeforeMethod
    public void startBrowser(Method method, String browserType) throws Exception {
        if (method.getName().contains("UI")) {
//            ExtentTest driverLog = logger.startTest("setup driver", "");
            BasePage.driver = SeleniumUtils.getDriver(browserType);
//            SeleniumUtils.setEnv(envConfig.getWebUrl());
        }

    }

    @Parameters({"env", "portal"})
    @BeforeSuite
    public void setEnv(String env, String portal) throws IOException {
        envConfig = new EnvConfig(env, portal);
        System.out.println("The Automation tests runs on : " + portal + " portal");
        System.out.println("The Automation tests runs on : " + env + " environment");
    }


    public void starTestLog(String testName, String description) {
        testLog = BaseApi.testLog = BasePage.testLog = logger.startTest(testName, description);
        testLog.log(LogStatus.INFO, "The Automation tests runs on : " + envConfig.getWebUrl());
        testLog.log(LogStatus.INFO, "The Automation tests runs on : " + envConfig.getEnv() + "portal");
    }


    @AfterMethod()
    public void stopTestReport(Method method, ITestResult result) throws Exception {
        testLog.log(LogStatus.INFO, "result get status is: " + result.getStatus());
        switch (result.getStatus()) {
            case ITestResult.SKIP:
                testLog.log(LogStatus.SKIP, "Test SKIP <span class='label info'>info</span>");
                break;
            case ITestResult.SUCCESS:
                testLog.log(LogStatus.PASS, "Test Passed <span class='label success'>success</span>");
                break;
            case ITestResult.FAILURE:
                String capScreenShootPath = SeleniumUtils.getScreenshot();
                testLog.log(LogStatus.FAIL, result.getThrowable() + " <span class='label label-fail'>fail</span>");
                testLog.log(LogStatus.INFO, "Snapshot path: " + (capScreenShootPath));
                testLog.log(LogStatus.INFO, "Snapshot below: " + testLog.addBase64ScreenShot(capScreenShootPath));
                break;
        }
        logger.endTest(testLog);
        logger.flush();
        if (method.getName().contains("UI")) {
            closePage();
        }
    }


    //    @AfterMethod(alwaysRun = true)
    public void closePage() throws Exception {
//        System.out.println("Closing Web Page");
//        Reporter.log("Closing Web Page", true);
//        SeleniumUtils.closeRuntimeBrowserInstance();
    }


    protected void assertTextContains(String expectedResult, String actual){
        if (!actual.contains(expectedResult)) {
            org.testng.Assert.fail("Text expected: " + expectedResult + " Was: " + actual);
        }
    }



}
