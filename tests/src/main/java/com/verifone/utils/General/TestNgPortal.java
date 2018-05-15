package com.verifone.utils.General;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class TestNgPortal {

	/**
	 * @param driver
	 * @param test
	 * @param result
	 * @throws Exception
	 */
	public static  void afterMethodReport(WebDriver driver, ExtentTest test, ITestResult result ) throws Exception {
		
	test.log(LogStatus.INFO, "result.getStatus value is : " + result.getStatus());
	switch (result.getStatus()) {        
	case ITestResult.SKIP:
		test.log(LogStatus.SKIP, "Test SKIP <span class='label info'>info</span>");			
		break;
	case ITestResult.SUCCESS:
		test.log(LogStatus.PASS, "Test Passed - <span class='label success'>success</span>");			
		break;
	case ITestResult.FAILURE:
		String capScreenShootPath = SeleniumUtils.getscreenshot(driver);
		test.log(LogStatus.FAIL, "Test Failed !!! <span class='Failed'>fail</span>");
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot path: " + (capScreenShootPath));
		test.log(LogStatus.INFO, "Test Failed !!! - Snapshot below: " + test.addBase64ScreenShot(capScreenShootPath));
		break;
	
}

}
	
	public static  void cpLoginPoratl(WebDriver driver, String usr, String pass, ExtentTest test ) throws Exception {
		
//		LOG-IN PORTAL AND NAVIGATE TO APPLICATION PAGE
	

	WebElement usern = driver.findElement(By.name("username"));
	usern.click();

	Actions builder = new Actions(driver);
	builder.sendKeys(usern, usr).build().perform();

//	Type password = Veri1234 and ENTER
	WebElement userpw = driver.findElement(By.name("password"));
	userpw.click();
//	Thread.sleep(2000);
	builder.sendKeys(userpw, pass + Keys.ENTER).build().perform();
//	Thread.sleep(5000);

	test.log(LogStatus.INFO, "Log in Portal: <span class='label success'>success</span>" );
}
	
	
}