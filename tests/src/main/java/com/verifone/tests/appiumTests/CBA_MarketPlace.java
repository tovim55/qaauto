package com.verifone.tests.appiumTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CBA_MarketPlace {

    private String reportDirectory = "C:\\Test\\AppiumReports";
    private String reportFormat = "xml";
    private String testName = "MarketPlace.html";
    protected AndroidDriver driver = null;


    @BeforeClass
    public void setUp() throws MalformedURLException
    {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability("noreset", true);
        dc.setCapability(MobileCapabilityType.UDID, "401-686-717");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.verifone.marketplace.cba.stage");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.wmode.appdirect.mobileapp.activities.MainActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);

    }

    @Test
    public void Login_Test() throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.findElement(By.xpath("//*[@text='SIGN IN']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='loginForm']")));

        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("testorg1.vhq5Merchant@gmail.com");

        driver.context("WEBVIEW_1");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='loginForm']")));

        driver.findElement(By.xpath("//*[@id='ipassword']")).sendKeys("Veri1234");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='btnPrimaryLogin']")));

        driver.findElement(By.id("btnPrimaryLogin")).submit();

        Thread.sleep(7000);

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
