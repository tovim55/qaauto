package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.eoPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
//--------------------------------------------------------------------------

/**
 * Portal: EstateManager
 * This test set verify EO Admin can update Active EO Admin - User Info and Role.
 * EO Admin can update Active Merchant Manager - User Info and Role.
 * EO Admin can update Device and Application Manager - User Info and Role.
 * EO Admin and Merchant Manager can update Merchant in all statuses - User Info and Business Info.
 * Edit User page GUI validation.
 * Edit Role page GUI validation.
 * Edit Merchant - User info page GUI validation.
 * Edit Merchant - Business info page GUI validation.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EOPortalCustomizeTemplate_RegUI extends BaseTest {

	private static String UserDevAppEmail = ""; //"User20181010T190208.543DevAppMan@getnada.com";
	private static String UserMerchManEmail = ""; //"User20181010T190123.176MerchMan@getnada.com";
	private static String UserDevAppPwd = "Veri1234";
	private static String UserMerchManPwd = "Veri1234";
	private static String EnvPort = ".estatemanager.verifonecp.com";
	private static String Env = "";
	private static String EOAdminMail = "vfieous@getnada.com";
	private static String EOAdminPwd = "Veri1234";
	private static Integer TimeOut = 2000;

	@Test(enabled = false, priority = 34, testName = "EOAdmin. Email Template gui validation", groups = {"Sanity"}, alwaysRun = true)

	public void EOAdmin_EmailTemplateValidationUI() throws Exception {


		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Email Template page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickCustomizeMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EmailTemplatePage EmailTemplatePage = (EmailTemplatePage) PageFactory.getPage("EmailTemplatePage");
		AssertJUnit.assertEquals("Email Template", EmailTemplatePage.getTitle());

		testLog.info("------------------------------------------------- Search for SubTitle -------------------------------------------------");

		if (!Assertions.compareValue("Current Email Subject: Congrats on your Verifone Carbon purchase!", EmailTemplatePage.getSubTitle(), "SubTitle: ", testLog)) {
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Search for link Customize Template -------------------------------------------------");

		if (!Assertions.compareValue("Customize Template", EmailTemplatePage.getCustomizeLnk(), "Customize Template link: ", testLog)) {
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Search for Mailer preview -------------------------------------------------");

		if (!Assertions.compareBoolean(true, EmailTemplatePage.elementPreviewMailerExists(), "Mailer preview exists: ", testLog)) {
			TestPassFlag = false;
		}

		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority = 35, testName = "EOAdmin. Update Email Template", groups = {"Sanity"}, alwaysRun = true)

	public void EOAdmin_UpdateEmailTemplateUI() throws Exception {


		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Email Template page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickCustomizeMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EmailTemplatePage EmailTemplatePage = (EmailTemplatePage) PageFactory.getPage("EmailTemplatePage");
		AssertJUnit.assertEquals("Email Template", EmailTemplatePage.getTitle());

		EmailTemplatePage.clickLnkCustomize();

		testLog.info("------------------------------------------------- Customize Template page -------------------------------------------------");

	}
}