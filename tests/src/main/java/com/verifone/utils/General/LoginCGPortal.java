package com.verifone.utils.General;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.verifone.infra.SeleniumUtils;

/**
* This class contain method Login to CG Portal. 
* @authors Yana Fridman
*/
public class LoginCGPortal {
	
	/**
	 * @param driver
	 * @param user
	 * @param password
	 * @param clickCgLoginBtn
	 * @return
	 * @throws Exception
	 */
	public static boolean LoginCGPortal(WebDriver driver, String user, String password, boolean clickCgLoginBtn) throws Exception {
		
		
		Integer Slp = 20;


//		LOG-IN PORTAL AND NAVIGATE TO APPLICATION PAGE
		driver.manage().timeouts().implicitlyWait(Slp, TimeUnit.SECONDS);
		
		if (clickCgLoginBtn) {

	//		Click Log-in button
			driver.findElement(By.cssSelector("[href=\"javascript\\:void\\(gotoSso\\(\\)\\)\"]")).click(); 	
	
	//		Type username
			WebElement usern = driver.findElement(By.name("username"));
			Actions builder = new Actions(driver);
			builder.sendKeys(usern, user).build().perform();
			
	//		Type password = Veri1234 and ENTER
			WebElement userpw = driver.findElement(By.name("password"));
			builder.sendKeys(userpw, password + Keys.ENTER).build().perform();
		}

		return true;
	}
}
