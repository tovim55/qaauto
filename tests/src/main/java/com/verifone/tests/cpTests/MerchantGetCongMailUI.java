package com.verifone.tests.cpTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.verifone.infra.SeleniumUtils;
import com.verifone.pages.BasePage;

public class MerchantGetCongMailUI {
	private static WebDriver driver;
	
    @Test(groups = {"CP-portal"})
    public void MerchantGetCongMailUI() throws Exception {
    	//      Navigate to Getnada
    	
    	BasePage.driver = SeleniumUtils.getDriver("CHROME");
    	BasePage.driver.navigate().to("https://getnada.com/#");
		addNewEmail("aeb90709d6164809a56447843ab87ac5");


		//  Open Email
    	Thread.sleep(2000);
    	BasePage.driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();
    	
    	//   Get email text    	
    	
    	Thread.sleep(2000);
    	
    	WebElement iFrame = BasePage.driver.findElement(By.id("idIframe"));
    	BasePage.driver.switchTo().frame(iFrame);
    	String mailText = BasePage.driver.getPageSource();
    	
    	Boolean langFlag = mailText.contains("Congratulations! You’ve just taken a giant leap toward enhanced");
    	
    	System.out.println("Mail text: " + langFlag);	
    	
    	BasePage.driver.findElement(By.linkText("Activate Account")).click(); 
    }

	static void addNewEmail(String emailName) throws InterruptedException {
		// Click Add Inbox
		Thread.sleep(2000);
		BasePage.driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();

		// Put email
		BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
		BasePage.driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(emailName);
		Thread.sleep(2000);

		BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
		BasePage.driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);

		//  Accept
		BasePage.driver.findElement(By.linkText("ACCEPT")).click();
	}

}
