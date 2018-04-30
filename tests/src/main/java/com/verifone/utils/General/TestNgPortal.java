package com.verifone.utils.General;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.SeleniumUtils;
import java.io.FileInputStream;

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
}