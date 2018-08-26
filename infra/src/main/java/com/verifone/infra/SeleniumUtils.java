package com.verifone.infra;
//import static org.junit.Assert.assertTrue;

import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.gargoylesoftware.htmlunit.javascript.host.URL;
//import org.testng.annotations.Test;

/**
 * This class contains convenience methods for working with Selenium.
 *
 * @Giora Tovim
 */

@SuppressWarnings("unused")
public class SeleniumUtils {
    private static WebDriver driver;

    /**
     * Reads General Parameters from application.properties
     * Sets browser (Chrome, Firefox, IE etc...) and navigates to the class related page
     */
    public static WebDriver getDriver(String browserType) throws Exception {
        driver = SeleniumUtils.setBrowser(browserType);
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeRuntimeBrowserInstance() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


    /**
     * This method set browser
     * BrowserType:
     * Chrome - CHROME
     * FireFox - FF
     * Internet Explorer - IE
     *
     * @param browserType
     * @return driver
     * @throws Exception
     * @author Giora Tovim
     */
    @SuppressWarnings("deprecation")
    public static WebDriver setBrowser(String browserType) throws Exception

    {

        System.out.println("Starting web browser switch: " + browserType);
        switch (browserType) {
            case "FF":
                System.setProperty("webdriver.gecko.driver", new File(System.getProperty("user.dir")).getParent() + "\\infra\\drivers\\geckodriver.exe");
//			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//			desiredCapabilities.setAcceptInsecureCerts(true);
//			FirefoxProfile firefoxProfile = new FirefoxProfile();
//			firefoxProfile.setPreference("browser.private.browsing.autostart",true);
                driver = new FirefoxDriver();
                System.out.println("finish start FireFox!!!");
                break;

            case "CHROME":
                System.out.println("Starting CHROME web driver");
                System.out.println(new File(System.getProperty("user.dir")).getParent());
                System.setProperty("webdriver.chrome.driver", new File(System.getProperty("user.dir")).getParent() + "\\infra\\drivers\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                options.addArguments("--incognito");
                driver = new ChromeDriver();
                System.out.println("CHROME web driver started successfully");
                break;
            case "EDGE":
                System.out.println("Starting EDGE web driver");
                System.setProperty("webdriver.edge.driver", new File(System.getProperty("user.dir")).getParent() + "\\infra\\drivers\\MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
                System.out.println("EDGE web driver started successfully");
                break;

            case "IE":
                System.out.println("Starting IE web driver");
                System.setProperty("webdriver.ie.driver", new File(System.getProperty("user.dir")).getParent() + "\\infra\\drivers\\IEDriverServer.exe");
                WebDriver Driver = new InternetExplorerDriver();
                //				DesiredCapabilities desiredCapabilities1 = new DesiredCapabilities();
                //		    	desiredCapabilities1.setAcceptInsecureCerts(true);
                //				driver = new InternetExplorerDriver(desiredCapabilities1);
                System.out.println("IE web driver started successfully");
                break;

            default:
                System.out.println("Browser name is not exist!!!");

        }
        return driver;

    }


    /**
     * This method get screenshot name of file is date
     *
     * @throws Exception
     * @author Giora Tovim
     */
    public static String getScreenshot() throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        //The below method will save the screen shot in d drive with folder "screenshot" + filenameDate + ".png "
        String screeshootPath = "C:\\screenshot\\" + dateFormat.format(date) + ".png";
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screeshootPath));
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
            return "";
        }
        return screeshootPath;
    }


    //    TODO this method is just for support old tets
    public static void setEnv(String env, String urlDev, String urlTest,
                              String urlStaging1, String urlProduction, ExtentTest test) throws Exception {
        System.out.println("The Automation tests runs on : " + env + " environment");


    }

//    public static void restartDriver() {
//        driver.manage().deleteAllCookies();         // Clear Cookies on the browser
//        driver.quit();
//        driver = null;
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }

}