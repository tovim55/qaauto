package com.verifone.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.verifone.infra.EnvConfig;
import com.verifone.infra.SeleniumUtils;
import com.verifone.pages.BasePage;
import com.verifone.utils.apiClient.BaseApi;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class BaseTest {


    public static EnvConfig envConfig;
    private static ExtentReports extent;
    private static ThreadLocal parentTest = new ThreadLocal();
    protected static ThreadLocal test = new ThreadLocal();
    public Date date = new Date();
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    public String reportDirectory = "C:\\reportTestNgResults\\" + dateFormat.format(date) + "\\";
    public String reportLocation = reportDirectory + dateFormat.format(date) + ".html";


    @Parameters({"env", "portal"})
    @BeforeSuite
    public void beforeSuite(String env, String portal) throws IOException {
        new File(reportDirectory).mkdir();
        extent = ExtentManager.createInstance(reportLocation);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportLocation);
        extent.attachReporter(htmlReporter);
        setEnv(env, portal);

    }


    public void setEnv(String env, String portal) throws IOException {
        envConfig = new EnvConfig(env, portal);
        System.out.println("The Automation tests runs on : " + env + " environment");

    }

    @BeforeClass
    public synchronized void beforeClass() {
        ExtentTest parent = extent.createTest(getClass().getName());
        parentTest.set(parent);
        parent.info("The Automation tests runs on : " + envConfig.getWebUrl() + " " + envConfig.getEnv() + " environment");
    }


    @Parameters({"browserType"})
    @BeforeMethod
    public void startBrowser(Method method, String browserType) throws Exception {
        SeleniumUtils.reportDirectory = reportDirectory;
        if (method.getName().contains("DDT") && method.getName().contains("UI")) {
            BasePage.driver = SeleniumUtils.getDriver(browserType);
            return;
        } else if (method.getName().contains("DDT"))
            return;
        Test test = method.getAnnotation(Test.class);
        starTestLog(test.testName(), test.description());
        if (method.getName().contains("UI")) {
            BasePage.driver = SeleniumUtils.getDriver(browserType);

        }

    }


    protected void starTestLog(String testName, String description) {
        ExtentTest p = (ExtentTest) parentTest.get();
        test.set(p.createNode(testName, description));
        BaseApi.testLog = BasePage.testLog = (ExtentTest) test.get();
    }


    @AfterMethod()
    public void stopTestReport(Method method, ITestResult result) throws Exception {
        ExtentTest child = (ExtentTest) test.get();
        child.info("result get status is: " + result.getStatus());
        switch (result.getStatus()) {
            case ITestResult.SKIP:
                child.skip("Test SKIP <span class='label info'>info</span>");
                break;
            case ITestResult.SUCCESS:
                child.pass("Test Passed <span class='label success'>success</span>");
                break;
            case ITestResult.FAILURE:
                if (method.getName().contains("UI")) {
                    String capScreenShootPath = SeleniumUtils.getScreenshot();
                    child.info("Snapshot path: " + (capScreenShootPath));
                    child.info("Snapshot below: " + child.addScreenCaptureFromPath(capScreenShootPath));
                }
                child.fail(result.getThrowable() + " <span class='label label-fail'>fail</span>");
                break;
        }

        if (method.getName().contains("UI") & !method.getName().contains("UI_Cont")) {
            child.info("Closing Web Page");
            SeleniumUtils.closeRuntimeBrowserInstance();
        }
        extent.flush();
    }





}


class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance("test-output/extent.html");

        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }
}