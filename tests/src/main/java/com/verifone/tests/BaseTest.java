package com.verifone.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import com.verifone.pages.BasePage;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public abstract class BaseTest {


    protected String testName;
    protected String description;

    public Date date = new Date();
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    public String reportLocation = "C:\\reportTestNgResults\\" + dateFormat.format(date) + ".html";
    public static ExtentTest childTest, parentTest;
    public ExtentReports logger = new ExtentReports(reportLocation, true);
    public Boolean testStepPassed = true;
    public String capScreenShootPath;
    public ExtentTest testLog;
    public Properties prop = new Properties();
    private String basePropPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\verifone\\tests\\";
    protected String propFilePath = "";




    @Parameters({"env", "urlDev", "urlTest", "urlStaging1", "urlProduction", "browserType"})
    @BeforeMethod
    /**
     * Login to portal and navigate to Application page
     */
    public void startBrowser(String env, String urlDev, String urlTest,
                             String urlStaging1, String urlProduction, String browserType) throws Exception {
        // starting testLog
        ExtentTest driverLog = logger.startTest("setup driver", "");
        BasePage.driver = SeleniumUtils.getDriver(browserType);
        SeleniumUtils.setEnv(env, urlDev, urlTest, urlStaging1, urlProduction, driverLog);
        FileInputStream ip = new FileInputStream(basePropPath + propFilePath);
        prop.load(ip);
    }


    public void starTestLog(String testName, String description){
        testLog = BasePage.testLog = logger.startTest(testName, description);
    }


    @AfterMethod(lastTimeOnly = true)
    public void stopTestReport(ITestResult result) throws Exception {
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
    }


    @AfterMethod(alwaysRun = true)
    public void closePage() throws Exception {
//        System.out.println("Closing Web Page");
//        Reporter.log("Closing Web Page", true);
//        SeleniumUtils.closeRuntimeBrowserInstance();


    }


}
