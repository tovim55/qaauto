package com.verifone.utils.Mail;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.verifone.infra.SeleniumUtils;
import com.verifone.pages.BasePage;

public class InboxGetnada {
	
	public static void AddInboxGetnada(WebDriver driver, String EmailAddr, int timeout) throws Exception {
    	//      Navigate to Getnada
    	
    	BasePage.driver = SeleniumUtils.getDriver("CHROME");
    	BasePage.driver.navigate().to("https://getnada.com/#");
    	
    	// Click Add Inbox

    	BasePage.driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();  
    	
    	// Put email
    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
    	BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(EmailAddr);
    	
    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
    	BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);
    	
    	//  Accept
    	BasePage.driver.findElement(By.linkText("ACCEPT")).click(); 
    	
    	//  Open Email
    	Thread.sleep(timeout);
    	BasePage.driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();
    	
    }

}

