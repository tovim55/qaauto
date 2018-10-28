package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.eoPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import com.verifone.utils.DataDrivenUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;

public class EOPortalEditChangeStatusUsers_RegUI extends BaseTest {

	private static String EnvPort = ".estatemanager.verifonecp.com";
	private static String Env = "";
	private static String EOAdminMail = "vfieous@getnada.com"; //Default
	private static String EOAdminPwd = "Veri1234";  //Default
	private static Integer TimeOut = 2000;

	@Test(enabled = true, priority=8, testName = "EOAdmin Edit pending EOAdmin. Verify correct GUI state. Resend invitation", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingEOAdminUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Pending EO Admin -------------------------------------------------");

		a = UsersPage.pendingEOAdminRow();
		System.out.println(a);
		if (a <= 0){
			AssertJUnit.fail("Pending EOAdmin not found");
		}

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		UsersPage.clickOnRow(a);

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link disabled
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Pending
		if (!Assertions.compareValue("Pending", UserDetailsPage.getStatus(), "Status = Pending", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Role = EOAdmin
		if (!Assertions.compareValue("EO Admin", UserDetailsPage.getRole(), "Role = EOAdmin", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Resend invitation -------------------------------------------------");

		UserDetailsPage.clickLnkResend();
//		Resend dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogResendExists(), "Resend dialog displayed", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Resend dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogResend().contains("This will resend another copy of the invitation email to the pending user. Continue?"), "Resend dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);
//		Cancel Resend
		UserDetailsPage.clickCancelResend();
//		No Resend confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Resend confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Resend invitation again
		UserDetailsPage.clickLnkResend();
		Thread.sleep(TimeOut - 1000);
//		Resend dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogResendExists(), "Resend dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Resend
		UserDetailsPage.clickDoResend();
		Thread.sleep(TimeOut + 3000);

//		Resend message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Resend message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("Your invitation email was successfully sent.",  UserDetailsPage.getMessage(), "Resend message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);
	}
	@Test(enabled = true, priority=9, testName = "EOAdmin Edit pending Merchant Manager. Verify correct GUI state. Resend invitation", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingMerchantManUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();

		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Pending Merchant Manager -------------------------------------------------");

		a = UsersPage.pendingMerchantManRow();
		if (a <= 0){
			AssertJUnit.fail("Pending Merchant Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link disabled
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Pending
		if (!Assertions.compareValue("Pending", UserDetailsPage.getStatus(), "Status = Pending", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Role = EOAdmin
		if (!Assertions.compareValue("EO Merchant Manager", UserDetailsPage.getRole(), "Role = EOAdmin", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Resend invitation -------------------------------------------------");

		UserDetailsPage.clickLnkResend();

//		Resend dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogResendExists(), "Resend dialog displayed", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Resend dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogResend().contains("This will resend another copy of the invitation email to the pending user. Continue?"), "Resend dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);
//		Cancel Resend
		UserDetailsPage.clickCancelResend();
//		No Resend confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Resend confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Resend invitation again
		UserDetailsPage.clickLnkResend();
		Thread.sleep(TimeOut - 1000);
//		Resend dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogResendExists(), "Resend dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Resend
		UserDetailsPage.clickDoResend();
		Thread.sleep(TimeOut + 3000);

//		Resend message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Resend message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("Your invitation email was successfully sent.",  UserDetailsPage.getMessage(), "Resend message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);

	}
	@Test(enabled = true, priority=10, testName = "EOAdmin Edit pending Device App Manager. Verify correct GUI state. Resend invitation", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingDevAppManUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();

		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for pending Device and Application Manager -------------------------------------------------");

		a = UsersPage.pendingDevAppManRow();
		if (a <= 0){
			AssertJUnit.fail("Pending Device and Application Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link disabled
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Pending
		if (!Assertions.compareValue("Pending", UserDetailsPage.getStatus(), "Status = Pending", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Role = EOAdmin
		if (!Assertions.compareValue("EO Device and App Manager", UserDetailsPage.getRole(), "Role = EOAdmin", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Resend invitation -------------------------------------------------");

		UserDetailsPage.clickLnkResend();

//		Resend dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogResendExists(), "Resend dialog displayed", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Resend dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogResend().contains("This will resend another copy of the invitation email to the pending user. Continue?"), "Resend dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);
//		Cancel Resend
		UserDetailsPage.clickCancelResend();
//		No Resend confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Resend confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Resend invitation again
		UserDetailsPage.clickLnkResend();
		Thread.sleep(TimeOut - 1000);
//		Resend dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogResendExists(), "Resend dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Resend
		UserDetailsPage.clickDoResend();
		Thread.sleep(TimeOut + 3000);

//		Resend message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Resend message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("Your invitation email was successfully sent.",  UserDetailsPage.getMessage(), "Resend message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);

	}
	@Test(enabled = true, priority=11, testName = "EOAdmin Disable Active Device App Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveDevAppManUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();
		
		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active Device and Application Manager -------------------------------------------------");

		a = UsersPage.activeDevAppManRow();
		if (a <= 0){
			AssertJUnit.fail("Active Device and Application Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link enable
		if (!Assertions.compareBoolean(true, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Active
		if (!Assertions.compareValue("Active", UserDetailsPage.getStatus(), "Status = Active", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Disable User link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Disable User"), "Disable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Role = EO Device and App Manager
		if (!Assertions.compareValue("EO Device and App Manager", UserDetailsPage.getRole(), "Role = EOAdmin", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role clickable
		if (!Assertions.compareBoolean(true, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Disable User -------------------------------------------------");

		UserDetailsPage.clickLnkDisable();

//		Disable dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogDisableExists(), "Disable user dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);

//		Disable dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogDisable().contains("This will disable this user account. They will not be able to log in to any Verifone portals. You can re-enable users that were disabled at any time. Continue?"), "Disable user dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);

//		Cancel Disable User
		UserDetailsPage.clickCancelDisable();
//		No Disable User confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Disable User confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Disable User again
		UserDetailsPage.clickLnkDisable();
		Thread.sleep(TimeOut - 1000);
//		Disable User dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogDisableExists(), "Disable User dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Disable User
		UserDetailsPage.clickDoDisable();
		Thread.sleep(TimeOut + 3000);

//		Disable User message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Disable User message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("The user's account was successfully disabled.",  UserDetailsPage.getMessage(), "Disable User message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);


	}
	@Test(enabled = true, priority=12, testName = "EOAdmin Enable disabled Device App Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEnableDisabledDevAppManUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Disabled Device and Application Manager -------------------------------------------------");

		a = UsersPage.disableDevAppManRow();
		if (a <= 0){
			AssertJUnit.fail("Disabled Device and Application Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link disabled
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Disabled
		if (!Assertions.compareValue("Disabled", UserDetailsPage.getStatus(), "Status = Disabled", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Disable User link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Disable User"), "Disable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Enable User link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Enable User"), "Disable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Role = EO Device and App Manager
		if (!Assertions.compareValue("EO Device and App Manager", UserDetailsPage.getRole(), "Role = EOAdmin", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Active User -------------------------------------------------");

		UserDetailsPage.clickLnkEnable();

//		Active user dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogEnableExists(), "Active user dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Active user dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogEnable().contains("This will re-enable this user account and restore their access to the portal. Continue?"), "Active user dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);
//		Cancel Active User
		UserDetailsPage.clickCancelEnable();
//		No Active User confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Active User confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Active User again
		UserDetailsPage.clickLnkEnable();
		Thread.sleep(TimeOut - 1000);
//		Active User dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogEnableExists(), "Active User dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Active User
		UserDetailsPage.clickDoEnable();
		Thread.sleep(TimeOut + 3000);

//		Active User message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Active User message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("The user's account was successfully re-enabled.",  UserDetailsPage.getMessage(), "Disable User message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);

	}
	@Test(enabled = true, priority=13, testName = "EOAdmin Disable active EO Admin", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveEOAdminUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active EO Admin -------------------------------------------------");

		a = UsersPage.activeEOAdminRow();
		System.out.println(a);
		if (a <= 0){
			AssertJUnit.fail("Active EO Admin not found");
		}
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link enable
		if (!Assertions.compareBoolean(true, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Active
		if (!Assertions.compareValue("Active", UserDetailsPage.getStatus(), "Status = Active", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Disable User link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Disable User"), "Disable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Role = EO Admin
		if (!Assertions.compareValue("EO Admin", UserDetailsPage.getRole(), "Role = EOAdmin", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role clickable
		if (!Assertions.compareBoolean(true, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Disable User -------------------------------------------------");

		UserDetailsPage.clickLnkDisable();

//		Disable User dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogDisableExists(), "Disable user dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Disable dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogDisable().contains("This will disable this user account. They will not be able to log in to any Verifone portals. You can re-enable users that were disabled at any time. Continue?"), "Disable user dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);
//		Cancel Disable User
		UserDetailsPage.clickCancelDisable();
//		No Disable User confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Disable User confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Disable User again
		UserDetailsPage.clickLnkDisable();
		Thread.sleep(TimeOut - 1000);
//		Disable User dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogDisableExists(), "Disable User dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Disable User
		UserDetailsPage.clickDoDisable();
		Thread.sleep(TimeOut + 3000);

//		Disable User message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Disable User message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("The user's account was successfully disabled.",  UserDetailsPage.getMessage(), "Disable User message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);

	}
	@Test(enabled = true, priority=14, testName = "EOAdmin Enable disabled EO Admin", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEnableDisabledEOAdminUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();

		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Disabled EO Admin -------------------------------------------------");

		a = UsersPage.disableEOAdminRow();
		if (a <= 0){
			AssertJUnit.fail("Disabled EO Admin not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link disabled
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Disabled
		if (!Assertions.compareValue("Disabled", UserDetailsPage.getStatus(), "Status = Disabled", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Disable User link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Disable User"), "Disable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Enable User link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Enable User"), "Enable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Role = EO Admin
		if (!Assertions.compareValue("EO Admin", UserDetailsPage.getRole(), "Role = EOAdmin", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Active User -------------------------------------------------");

		UserDetailsPage.clickLnkEnable();

//		Active user dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogEnableExists(), "Active user dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Active user dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogEnable().contains("This will re-enable this user account and restore their access to the portal. Continue?"), "Active user dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);
//		Cancel Active User
		UserDetailsPage.clickCancelEnable();
//		No Active User confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Active User confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Active User again
		UserDetailsPage.clickLnkEnable();
		Thread.sleep(TimeOut - 1000);
//		Active User dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogEnableExists(), "Active User dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Active User
		UserDetailsPage.clickDoEnable();
		Thread.sleep(TimeOut + 3000);

//		Active User message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Active User message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("The user's account was successfully re-enabled.",  UserDetailsPage.getMessage(), "Disable User message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);

	}

	@Test(enabled = true, priority=15, testName = "EOAdmin Disable active EO Merchant Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveEOMerchantManUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active Merchant Manager -------------------------------------------------");

		a = UsersPage.activeEOMerchantManRow();
		if (a <= 0){
			AssertJUnit.fail("Active Merchant Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link enable
		if (!Assertions.compareBoolean(true, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Active
		if (!Assertions.compareValue("Active", UserDetailsPage.getStatus(), "Status = Active", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Disable User link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Disable User"), "Disable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Role = EO Merchant Manager
		if (!Assertions.compareValue("EO Merchant Manager", UserDetailsPage.getRole(), "Role = EO Merchant Manager", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role clickable
		if (!Assertions.compareBoolean(true, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Disable User -------------------------------------------------");

		UserDetailsPage.clickLnkDisable();

//		Disable User dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogDisableExists(), "Disable user dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Disable dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogDisable().contains("This will disable this user account. They will not be able to log in to any Verifone portals. You can re-enable users that were disabled at any time. Continue?"), "Disable user dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);
//		Cancel Disable User
		UserDetailsPage.clickCancelDisable();
//		No Disable User confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Disable User confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Disable User again
		UserDetailsPage.clickLnkDisable();
		Thread.sleep(TimeOut - 1000);
//		Disable User dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogDisableExists(), "Disable User dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Disable User
		UserDetailsPage.clickDoDisable();
		Thread.sleep(TimeOut + 3000);

//		Disable User message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Disable User message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("The user's account was successfully disabled.",  UserDetailsPage.getMessage(), "Disable User message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);


	}
	@Test(enabled = true, priority=16, testName = "EOAdmin Enable disabled EO Merchant Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEnableDisabledEOMerchantManUI() throws Exception {
		int a = 0;
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Disabled Merchant Manager -------------------------------------------------");

		a = UsersPage.disableEOMerchantManRow();
		if (a <= 0){
			AssertJUnit.fail("Disabled Merchant Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

//		Verify:
//		Mail disabled and not empty
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementEmailClickable(), "Email can be updated", testLog)){
			TestPassFlag = false;
		}
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		if (!Assertions.compareBoolean(true, fl, "Email not empty", testLog)){
			TestPassFlag = false;
		}
//		Edit User link disabled
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementUserEditClickable(), "Edit User link enabled", testLog)){
			TestPassFlag = false;
		}
//		Title = UserName
		if (!Assertions.compareValue(UserDetailsPage.getTitle(), UserDetailsPage.getUserName(), "Title = UserName", testLog)){
			TestPassFlag = false;
		}
//		Status = Disabled
		if (!Assertions.compareValue("Disabled", UserDetailsPage.getStatus(), "Status = Disabled", testLog)){
			TestPassFlag = false;
		}
//		Resend Invitation link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Resend Invitation"), "Resend Invitation link displayed", testLog)){
			TestPassFlag = false;
		}
//		Disable User link NOT displayed
		if (!Assertions.compareBoolean(false, UserDetailsPage.getAction().contains("Disable User"), "Disable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Enable User link displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.getAction().contains("Enable User"), "Enable User link displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Role = EO Merchant Manager
		if (!Assertions.compareValue("EO Merchant Manager", UserDetailsPage.getRole(), "Role = EO Merchant Manager", testLog)){
			TestPassFlag = false;
		}
//		Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleClickable(), "Role clickable", testLog)){
			TestPassFlag = false;
		}
//		Edit Role Not clickable
		if (!Assertions.compareBoolean(false, UserDetailsPage.elementRoleEditClickable(), "Edit Role clickable", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Active User -------------------------------------------------");

		UserDetailsPage.clickLnkEnable();

//		Active user dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogEnableExists(), "Active user dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Active user dialog message
		if (!Assertions.compareBoolean(true, UserDetailsPage.getDialogEnable().contains("This will re-enable this user account and restore their access to the portal. Continue?"), "Active user dialog message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut + 1000);
//		Cancel Active User
		UserDetailsPage.clickCancelEnable();
//		No Active User confirmation message
		if (!Assertions.compareBoolean(false, UserDetailsPage.messageExists(), "Active User confirmation message: ", testLog)){
			TestPassFlag = false;
		}
		Thread.sleep(TimeOut - 1000);
//		Active User again
		UserDetailsPage.clickLnkEnable();
		Thread.sleep(TimeOut - 1000);
//		Active User dialog displayed
		if (!Assertions.compareBoolean(true, UserDetailsPage.dialogEnableExists(), "Active User dialog displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Confirm Active User
		UserDetailsPage.clickDoEnable();
		Thread.sleep(TimeOut + 3000);

//		Active User message displayed
		if (!Assertions.compareBoolean(true,  UserDetailsPage.messageExists(), "Active User message displayed: ", testLog)){
			TestPassFlag = false;
		}
//		Resend message text
		if (!Assertions.compareValue("The user's account was successfully re-enabled.",  UserDetailsPage.getMessage(), "Disable User message text: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);

	}
}
