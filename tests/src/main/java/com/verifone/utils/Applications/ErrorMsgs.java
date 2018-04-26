package com.verifone.utils.Applications;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.verifone.infra.SeleniumUtils;

/**
* This class collect all error messages. 
* @authors Yana Fridman
*/

public class ErrorMsgs {

	/**
	* Method: Search error "Field is required" and return error text
	* identifier is css
	* @authors Yana Fridman
	*/
//--------------------------------------------------------------------------
	public static String msgFieldReqTxt(WebDriver driver, String ident, int timeout) throws Exception {

		boolean result = false;
		int t = 0;
		String txt;

//		Find error msg with wait
		Thread.sleep(timeout);
		try{
			result = driver.findElement(By.cssSelector(ident)).isDisplayed();
			}catch(Exception e){
				   //ignore
			}	
		while (result == false & t < timeout*10) {
			//	Scroll to element
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1 = (JavascriptExecutor) driver;
			try{
				WebElement elem = driver.findElement(By.cssSelector(ident));
				int y = elem.getLocation().getY();
				js1.executeScript("window.scrollTo(0,"+y+")");
			}catch(Exception e){
				   //ignore
				}
		//  Find element
			Thread.sleep(timeout);
			try{
				result = driver.findElement(By.cssSelector(ident)).isDisplayed();
				}catch(Exception e){
		//ignore
				}
			t = t + timeout;
		}
		if (result == true) {
			txt = driver.findElement(By.cssSelector(ident)).getText();
		}
		else {
			txt="Error message not found!";
		}
		return txt;
	}
}