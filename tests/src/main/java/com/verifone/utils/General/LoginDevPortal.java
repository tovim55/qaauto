package com.verifone.utils.General;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.verifone.infra.SeleniumUtils;

public class LoginDevPortal {
	public static boolean SignUpDevPortal(WebDriver driver, String firstName, String lastName, String eMail, String password) throws Exception {
		
		
		Integer Slp = 20;

		driver.manage().timeouts().implicitlyWait(Slp, TimeUnit.SECONDS);
			
	
	//		Type first name
			WebElement fName = driver.findElement(By.id("givenname"));
			Actions builder = new Actions(driver);
			builder.sendKeys(fName, firstName).build().perform();
			
	//		Type last name
			WebElement lName = driver.findElement(By.id("lastname"));
			builder.sendKeys(lName, lastName).build().perform();
			
	//		Type email
			WebElement email = driver.findElement(By.id("username"));
			builder.sendKeys(email, eMail).build().perform();
			
	//		Type password
			WebElement psw = driver.findElement(By.id("credential"));
			builder.sendKeys(psw, password).build().perform();
			
	//		Check 18 years old
			WebElement chkBox = driver.findElement(By.xpath("//span[@class='check']"));
			builder.sendKeys(chkBox, Keys.ENTER).build().perform();
			
	//		Click on Next button
			
			driver.findElement(By.id("btnNext")).click();		
			
	//		Click on Agree button on certificate page
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary btn-raised btn-accept']")));
			driver.findElement(By.xpath("//*[@class='btn btn-primary btn-raised btn-accept']")).click();
			
		

		return true;
	}
	
	public static String SignUpDevPortalResult(WebDriver driver) throws Exception {
	//		Get text from final signUp page
		Integer Slp = 20;

		driver.manage().timeouts().implicitlyWait(Slp, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='instruction']")));
		
		String pageText = driver.findElement(By.xpath("//*[@class='instruction']")).getText();
		
		return pageText;
	}
	
	public static boolean LoginDevPortal(WebDriver driver, String user, String password, boolean clickCgLoginBtn) throws Exception {
		
		
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
