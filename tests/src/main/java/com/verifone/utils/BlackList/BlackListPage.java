package com.verifone.utils.BlackList;

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

public class BlackListPage {

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
	public static boolean buttonClick(WebDriver driver, String Name, String ident, int timeout) throws Exception {

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
//--------------------------------------------------------------------------
	/**
	* Method: TEXT BOX is Enabled? Wait maximum 20 sec till element text box became Visible
	* identifier is ID
	* @authors Yana Fridman
	*/
//--------------------------------------------------------------------------
	public static boolean textboxEnabled(WebDriver driver, String Name, String ident, int timeout) throws Exception {

		boolean result = false;
		int t = 0;

//		Find text box with wait
		try{
			result = driver.findElement(By.id(ident)).isDisplayed();
			}catch(Exception e){
				   //ignore
			}	
		while (result == false & t < timeout*10) {
			Thread.sleep(timeout);
			try{
				result = driver.findElement(By.id(ident)).isDisplayed();
				}catch(Exception e){
					   //ignore
				}
			t = t + timeout;
		}
		if (t < timeout*10 & result == true)  {
	//	Scroll to element
			WebElement elem = driver.findElement(By.id(ident));
			int y = elem.getLocation().getY();
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1 = (JavascriptExecutor) driver;
			try{
			js1.executeScript("window.scrollTo(0,"+y+")");
			}catch(Exception e){
				   //ignore
				}
//		Input text to text box
			result = driver.findElement(By.id(ident)).isEnabled();
		}
		return result;	
	}
//--------------------------------------------------------------------------
	/**
	* Method: WRITE TO TEXT BOX. Wait maximum 20 sec till element text box became enabled
	* identifier is ID
	* @authors Yana Fridman
	*/
//--------------------------------------------------------------------------
	public static boolean textboxInput(WebDriver driver, String Name, String ident, String inputText, int timeout) throws Exception {

		boolean result = false;
		int t = 0;

//		Find text box with wait
		try{
			result = driver.findElement(By.id(ident)).isDisplayed();
			}catch(Exception e){
				   //ignore
			}	
		while (result == false & t < timeout*10) {
			Thread.sleep(timeout);
			try{
				result = driver.findElement(By.id(ident)).isDisplayed();
				}catch(Exception e){
					   //ignore
				}
			t = t + timeout;
		}
		if (t < timeout*10 & result == true)  {
	//	Scroll to element
			WebElement elem = driver.findElement(By.id(ident));
			int y = elem.getLocation().getY();
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1 = (JavascriptExecutor) driver;
			try{
			js1.executeScript("window.scrollTo(0,"+y+")");
			}catch(Exception e){
				   //ignore
				}
//		Input text to text box
			Thread.sleep(timeout);
	        Actions builder = new Actions(driver);
	        try{
	        elem.clear();
	        }catch(Exception e){
				   //ignore
				}
	        Actions typeText = builder.moveToElement(elem).click().sendKeys(elem, inputText);
	        try{
	        	js1.executeScript("arguments[0].focus(); arguments[0].blur(); return true", elem);
	        }catch(Exception e){
				   //ignore
			}
			Thread.sleep(timeout/2);
	        typeText.perform();
			result = true;
		}
		return result;	
	}
//--------------------------------------------------------------------------
	/**
	* Method: WRITE TO TEXT BOX. Wait maximum 20 sec till element text box became enabled
	* identifier is XPATH
	* @authors Yana Fridman
	*/
//--------------------------------------------------------------------------
	public static boolean textboxInputXPATH(WebDriver driver, String Name, String ident, String inputText, int timeout) throws Exception {

		boolean result = false;
		int t = 0;

//		Find text box with wait
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
//		Input text to text box
			Thread.sleep(timeout);

	        Actions builder = new Actions(driver);
	        try{
	        elem.clear();
	        }catch(Exception e){
				   //ignore
			}

	        Actions typeText = builder.moveToElement(elem).click().sendKeys(elem,inputText);
	        try{
	        	js1.executeScript("arguments[0].focus(); arguments[0].blur(); return true", elem);
	        }catch(Exception e){
				   //ignore
			}
			Thread.sleep(timeout/2);
	        typeText.perform();
			result = true;
		}
		return result;	
	}

//------------------------------------------------------------------------------
	/**
	* Method: CLICK FILTER. Wait maximum 20 sec. till element button displayed and click on it
	* identifier is XPATH
	* @authors Yana Fridman
	*/
//------------------------------------------------------------------------------
	public static boolean filterClick(WebDriver driver, String ident, int timeout) throws Exception {

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
			driver.findElement(By.xpath(ident)).click();
			result = true;
		}
		return result;	
	}
//------------------------------------------------------------------------------
	/**
	* Method: TYPE TO FILTER AND SEARCH.
	* identifier is css
	* @authors Yana Fridman
	*/
//------------------------------------------------------------------------------
	public static boolean filterTypeSearch(WebDriver driver, String ident, String input, int timeout) throws Exception {

		boolean result = false;
		int t = 0;

		Thread.sleep(timeout);
		WebElement elem = null;
		try{
			result = driver.findElement(By.cssSelector(ident)).isDisplayed();
		}catch(Exception e){
			   //ignore
		}
		while (result == false & t < timeout*10) {
			Thread.sleep(timeout);
			try{
				result = driver.findElement(By.cssSelector(ident)).isDisplayed();
				}catch(Exception e){
					   //ignore
				}
			t = t + timeout;
		}
		if (t < timeout*10 & result == true)  {
			elem = driver.findElement(By.cssSelector(ident));
			elem.click();
			Actions builder = new Actions(driver);
			Actions builder1 = builder.moveToElement(elem).click().sendKeys(elem, input);
			builder1.perform();
			driver.findElement(By.cssSelector("[type=\"submit\"]")).click();	
			Thread.sleep(timeout);
		}
		return result;
	}
//------------------------------------------------------------------------------
	/**
	* Method: RESET FILTER.
	* identifier is css
	* @authors Yana Fridman
	*/
//------------------------------------------------------------------------------
	public static boolean filterReset(WebDriver driver, String ident, int timeout) throws Exception {

		boolean result = false;
		int t = 0;

		Thread.sleep(timeout);
		WebElement elem = null;
		try{
			result = driver.findElement(By.cssSelector(ident)).isDisplayed();
		}catch(Exception e){
			   //ignore
		}
		while (result == false & t < timeout*10) {
			Thread.sleep(timeout);
			try{
				result = driver.findElement(By.cssSelector(ident)).isDisplayed();
				}catch(Exception e){
					   //ignore
				}
			t = t + timeout;
		}
		if (t < timeout*10 & result == true)  {
			elem = driver.findElement(By.cssSelector(ident));
			elem.click();
			driver.findElement(By.cssSelector("[type=\"reset\"]")).click();	
			Thread.sleep(timeout);
		}
		return result;
	}

}

