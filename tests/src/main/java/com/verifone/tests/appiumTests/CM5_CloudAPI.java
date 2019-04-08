package com.verifone.tests.appiumTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CM5_CloudAPI
{
    private String reportDirectory = "C:\\Test\\AppiumReports";
    private String reportFormat = "xml";
    private String testName = "CloudAPI.html";
    //protected AndroidDriver driver = null;
    protected AndroidDriver driver = null;



    @BeforeClass
    public void setUp() throws MalformedURLException
    {

        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "401-666-420");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.verifone.cloudapi.cloudapi");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".SplashActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);


    }

    @Test
    public void Login_Test() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@contentDescription='More options']")).click();
        driver.findElement(By.xpath("//*[@text='Settings']")).click();
        driver.findElement(By.xpath("//*[@id='settings_generate_key']")).sendKeys("SThKMmVJTEpqQnIxUGxZTDU1NW5JOXdDMFIwYTpNNWs0YXBxbDdwem9QUjdIVVRmMk0zUTZJREFh");
        driver.findElement(By.id("application_id")).sendKeys("cloudAPI-1294609943");
        driver.findElement(By.id("merchant_id")).sendKeys("d74c2645-2529-462b-80e6-f61bc2b467d0");
        Thread.sleep(1000);
        driver.findElement(By.id("settings_save")).click();

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
