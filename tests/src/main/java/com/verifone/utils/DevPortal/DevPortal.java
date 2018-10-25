package com.verifone.utils.DevPortal;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.verifone.infra.SeleniumUtils;

/**
* This class described all elements and actions can be executed on Applications page. 
* @authors Yana Fridman
*/

public class DevPortal {

	/**
	* Method: BUTTON IS DISPLAYED? Wait maximum '20' sec till element button displayed
	* identifier is Xpath
	* @authors Yana Fridman
	*/
//--------------------------------------------------------------------------
	public static boolean buttonExists(WebDriver driver, String Name, String ident, int timeout) throws Exception {

		boolean result = false;
		int t = 0;

//		Find button with wait
		try{
			result = driver.findElement(By.xpath(ident)).isDisplayed();
			}catch(Exception e){
				   //ignore
			}	
		while (result == false & t < timeout*10) {
			Thread.sleep(timeout);
			try{
				result = driver.findElement(By.xpath(ident)).isDisplayed();
				}catch(Exception e){
					   //ignore
				}
			t = t + 2000;
		}
		return result;
	}
//--------------------------------------------------------------------------	
	/**
	* Method: BUTTON IS ENABLED? Wait maximum 20 sec till element button became enabled
	* identifier is Xpath
	* @authors Yana Fridman
	*/
//--------------------------------------------------------------------------
	public static boolean buttonEnabled(WebDriver driver, String Name, String ident, int timeout) throws Exception {

		boolean result = false;
		boolean result1 = false;
		int t = 0;

//		Find button with wait
		try{
			result = driver.findElement(By.xpath(ident)).isEnabled();
			}catch(Exception e){
				   //ignore
			}
		while (result == false & t < timeout*10) {
			Thread.sleep(timeout);
			try{
				result = driver.findElement(By.xpath(ident)).isEnabled();
				}catch(Exception e){
					   //ignore
				}
			t = t + timeout;
		}
		if (result == true) {
			String state = driver.findElement(By.xpath(ident)).getAttribute("class");
			if(!state.contains("k-state-disabled")) {
				result1 = true;
			}
		}
		
		return result1;
	}
//--------------------------------------------------------------------------	
		/**
		* Method: CLICK BUTTON. Wait maximum 20 sec. till element button displayed and click on it
		* identifier is Xpath
		* @authors Yana Fridman
		*/
//------------------------------------------------------------------------------
	public static boolean buttonClick(WebDriver driver, String ident, int timeout) throws Exception {

		boolean result = false;
		int t = 0;

//		Find button with wait
		try{
			result = driver.findElement(By.xpath(ident)).isDisplayed();
			}catch(Exception e){
				   //ignore
			}	
		while (result == false & t < timeout*10) {
			Thread.sleep(timeout);
			try{
				result = driver.findElement(By.xpath(ident)).isDisplayed();
				}catch(Exception e){
					   //ignore
				}
			t = t + timeout;
		}
//		Click on button
		if (t < timeout*10 & result == true)  {
			//	Scroll to element
			WebElement elem = driver.findElement(By.xpath(ident));
			int y = elem.getLocation().getY();
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1 = (JavascriptExecutor) driver;
			try{
			js1.executeScript("window.scrollTo(0,"+y+")");
			}catch(Exception e){
				   //ignore
				}
			driver.findElement(By.xpath(ident)).click();
			result = true;
		}
		return result;	
	}

}
