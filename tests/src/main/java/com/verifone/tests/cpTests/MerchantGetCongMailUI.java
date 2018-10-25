package com.verifone.tests.cpTests;

import com.verifone.pages.eoPages.HomePage;
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
	//	private static WebDriver driver;
	private static WebDriver driver = new HomePage().getDriver();
	@Test(groups = {"CP-portal"})
	public void MerchantGetCongMailUI() throws Exception {
		//      Navigate to Getnada

		driver = new SeleniumUtils().getDriver("CHROME");
		driver.navigate().to("https://getnada.com/#");
		addNewEmail("aeb90709d6164809a56447843ab87ac5");


		//  Open Email
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class, 'subject ')]")).click();

		//   Get email text

		Thread.sleep(2000);

		WebElement iFrame = driver.findElement(By.id("idIframe"));
		driver.switchTo().frame(iFrame);
		String mailText = driver.getPageSource();

		Boolean langFlag = mailText.contains("Congratulations! You’ve just taken a giant leap toward enhanced");

		System.out.println("Mail text: " + langFlag);

		driver.findElement(By.linkText("Activate Account")).click();
	}

	static void addNewEmail(String emailName) throws InterruptedException {
		// Click Add Inbox
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class, 'icon-plus')]")).click();   //getText();

		// Put email
		driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).clear();
		driver.findElement(By.xpath("//input[contains(@class, 'user_name')]")).sendKeys(emailName);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).click();
		driver.findElement(By.xpath("//select[contains(@id, 'domain')]")).sendKeys("getnada.com" + Keys.ENTER);

		//  Accept
		driver.findElement(By.linkText("ACCEPT")).click();
	}

}
