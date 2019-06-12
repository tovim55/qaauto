package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.eoPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import com.verifone.utils.DataDrivenUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
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
public class EOPortalUpdateUserMerchant_RegUI extends BaseTest {

	private static String UserDevAppEmail = ""; //"User20181010T190208.543DevAppMan@getnada.com";
	private static String UserMerchManEmail = ""; //"User20181010T190123.176MerchMan@getnada.com";
	private static String UserDevAppPwd = "Veri1234";
	private static String UserMerchManPwd = "Veri1234";
	private static String EnvPort = ".estatemanager.verifonecp.com";
	private static String Env = "";
	private static String EOAdminMail = "vfieous@getnada.com";
	private static String EOAdminPwd = "Veri1234";
	private static Integer TimeOut = 2000;

	@Test(enabled = true, priority=17, testName = "EOAdmin Update enabled EO Merchant Manager - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEOMerchantMan_UserInfoUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		String firstName = "";
		String lastName = "";
		String userName = "";
		String update = " updated";
		int updateLength = update.length();

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active Merchant Manager -------------------------------------------------");

		int a = UsersPage.activeEOMerchantManRow();
		if (a <= 0){
			AssertJUnit.fail("Active Merchant Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		userName = UserDetailsPage.getUserName();
		int userNameLength = userName.length();
		UserDetailsPage.clickLnkEditUserInf();

		testLog.info("------------------------------------------- Edit User page. Update User data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name
		Thread.sleep(TimeOut - 1000);
		firstName = EditUserPage.getfirstName();

//		Validation check
		EditUserPage.updateFirstName("12345");
		EditUserPage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditUserPage.errorFirstName(), "First Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputFirstName("");
		EditUserPage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name must not be empty", EditUserPage.errorFirstName(), "First Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputFirstName(" ");
		EditUserPage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name must not be empty", EditUserPage.errorFirstName(), "First Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputFirstName("~!@#$%^&*()");
		EditUserPage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditUserPage.errorFirstName(), "First Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputFirstName(".string");
		EditUserPage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditUserPage.errorFirstName(), "First Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputFirstName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
		EditUserPage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name must be at least 1 characters long, but not more than 35 characters", EditUserPage.errorFirstName(), "First Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditUserPage.getlastName();

		//		Validation check
		EditUserPage.updateLastName("12345");
		EditUserPage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditUserPage.errorLastName(), "Last Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputLastName("");
		EditUserPage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name must not be empty", EditUserPage.errorLastName(), "Last Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputLastName(" ");
		EditUserPage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name must not be empty", EditUserPage.errorLastName(), "Last Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputLastName("~!@#$%^&*()");
		EditUserPage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditUserPage.errorLastName(), "Last Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputLastName(".string");
		EditUserPage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditUserPage.errorLastName(), "Last Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputLastName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
		EditUserPage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name must be at least 1 characters long, but not more than 35 characters", EditUserPage.errorLastName(), "Last Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditUserPage.inputLastName(lastName + update);
		EditUserPage.clickFirstNameFld();

//		Cancel changes
		EditUserPage.clickBtnCancel();

		testLog.info("------------------------------------------------- User Details page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(userName);
//		Verify User Name not changed

		if (!Assertions.compareValue(userName, UserDetailsPage.getUserName(), "User Name: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------- Edit User page. Update User data. Save -------------------------------------------------");

		UserDetailsPage.clickLnkEditUserInf();
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		firstName = EditUserPage.getfirstName();
		EditUserPage.updateFirstName(update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.updateLastName(update);

//		Save changes
		EditUserPage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page. Verify data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		if (!Assertions.compareBoolean(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."), "Got message: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true,UserDetailsPage.getUserName().contains(update), "UserName updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareNumber(updateLength*2,UserDetailsPage.getUserName().length() - userNameLength, "UserName updated: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit User page. Remove changes -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		Thread.sleep(TimeOut - 1000);
		firstName = EditUserPage.getfirstName();
		firstName = firstName.replace(update, "");
		EditUserPage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(TimeOut - 1000);
		lastName = EditUserPage.getlastName();
		lastName = lastName.replace(update, "");
		EditUserPage.inputLastName(lastName);

//		Save changes
		Thread.sleep(TimeOut - 1000);
		EditUserPage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}
//
	@Test(enabled = true, priority=18, testName = "EOAdmin Update enabled EO Merchant Manager - Role", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEOMerchantMan_RoleUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		String role = "";

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active Merchant Manager -------------------------------------------------");

		int a = UsersPage.activeEOMerchantManRow();
		if (a <= 0){
			AssertJUnit.fail("Active Merchant Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		role = UserDetailsPage.getRole();

		UserDetailsPage.clickLnkEditRole();

		testLog.info("------------------------------------------- Edit User page. Update Role data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role
		EditRolePage.updateRole("EO_DEVICE_AND_APP_MANAGER");

//		Cancel changes
		EditRolePage.clickBtnCancel();

		testLog.info("------------------------------------------------- User Details page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(role);
//		Verify User Name not changed
		if (!Assertions.compareValue(role, UserDetailsPage.getRole(), "User Role: ", testLog, driver)){
			TestPassFlag = false;
		}

		UserDetailsPage.clickLnkEditRole();

		testLog.info("------------------------------------------- Edit User page. Update Role data. Save -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_DEVICE_AND_APP_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page. Verify Role updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		if (!Assertions.compareBoolean(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."), "Got message: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false,UserDetailsPage.getRole().contains(role), "Role updated: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit Role page. Remove changes -------------------------------------------------");

//		Return role value
		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=19, testName = "EOAdmin Update enabled Dev and App Manager - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEODevAppMan_UserInfoUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		int a = 0;
		String firstName = "";
		String lastName = "";
		String userName = "";
		String update = " updated";
		int updateLength = update.length();

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active Dev and App Manager -------------------------------------------------");

		a = UsersPage.activeDevAppManRow();
		if (a <= 0){
			AssertJUnit.fail("Active Dev and App Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		userName = UserDetailsPage.getUserName();
		int userNameLength = userName.length();
		UserDetailsPage.clickLnkEditUserInf();

		testLog.info("------------------------------------------- Edit User page. Update User data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name
		firstName = EditUserPage.getfirstName();
		EditUserPage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.inputLastName(lastName + update);

//		Cancel changes
		EditUserPage.clickBtnCancel();

		testLog.info("------------------------------------------------- User Details page. Verify no changes saved. -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(userName);
//		Verify User Name not changed
		if (!Assertions.compareValue(userName, UserDetailsPage.getUserName(), "User Name: ", testLog, driver)){
			TestPassFlag = false;
		}

		UserDetailsPage.clickLnkEditUserInf();

		testLog.info("------------------------------------------- Edit User page. Update User data. Save -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		firstName = EditUserPage.getfirstName();
		EditUserPage.updateFirstName(update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.updateLastName(update);

//		Save changes
		EditUserPage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page. Verify User data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		if (!Assertions.compareBoolean(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."), "Got message: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true,UserDetailsPage.getUserName().contains(update), "UserName updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareNumber(updateLength*2,UserDetailsPage.getUserName().length() - userNameLength, "UserName updated: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit User page. Remove changes -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		Thread.sleep(TimeOut - 1000);
		firstName = EditUserPage.getfirstName();
		firstName = firstName.replace(update, "");
		EditUserPage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(TimeOut - 1000);
		lastName = EditUserPage.getlastName();
		lastName = lastName.replace(update, "");
		EditUserPage.inputLastName(lastName);

//		Save changes
		Thread.sleep(TimeOut - 1000);
		EditUserPage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=20, testName = "EOAdmin Update enabled Dev and App Manager - Role", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEODevAppMan_RoleUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		int a = 0;
		String role = "";

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active Dev and App Manager -------------------------------------------------");

		a = UsersPage.activeDevAppManRow();
		if (a <= 0){
			AssertJUnit.fail("Active Dev and App Manager not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		role = UserDetailsPage.getRole();

		UserDetailsPage.clickLnkEditRole();

		testLog.info("------------------------------------------- Edit User page. Update Role data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role
		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Cancel changes
		EditRolePage.clickBtnCancel();

		testLog.info("------------------------------------------- User Details page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(role);
//		Verify User Name not changed
		if (!Assertions.compareValue(role, UserDetailsPage.getRole(), "User Role: ", testLog, driver)){
			TestPassFlag = false;
		}
		UserDetailsPage.clickLnkEditRole();

		testLog.info("------------------------------------------- Edit User page. Update Role data. Save -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page. Verify Role updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		if (!Assertions.compareBoolean(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."), "Got message: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false,UserDetailsPage.getRole().contains(role), "Role updated: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit Role page. Remove changes -------------------------------------------------");

		UserDetailsPage.clickLnkEditRole();
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_DEVICE_AND_APP_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}
	@Test(enabled = true, priority=21, testName = "EOAdmin Update EOAdmin - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEOAdmin_UserInfoUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		int a = 0;
		String firstName = "";
		String lastName = "";
		String userName = "";
		String update = " updated";
		int updateLength = update.length();

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active EO Admin -------------------------------------------------");

		a = UsersPage.activeEOAdminRow();
		if (a <= 0){
			AssertJUnit.fail("Active EO Admin not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		userName = UserDetailsPage.getUserName();
		int userNameLength = userName.length();
		UserDetailsPage.clickLnkEditUserInf();

		testLog.info("------------------------------------------- Edit User page. Update User data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name
		firstName = EditUserPage.getfirstName();
		EditUserPage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.inputLastName(lastName + update);

//		Cancel changes
		EditUserPage.clickBtnCancel();

		testLog.info("------------------------------------------------- User Details page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(userName);
//		Verify User Name not changed
		if (!Assertions.compareValue(userName, UserDetailsPage.getUserName(), "User Name: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------- Edit User page. Update User data. Save -------------------------------------------------");

		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		firstName = EditUserPage.getfirstName();
		EditUserPage.updateFirstName(update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.updateLastName(update);

//		Save changes
		EditUserPage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		if (!Assertions.compareBoolean(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."), "Got message: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true,UserDetailsPage.getUserName().contains(update), "UserName updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareNumber(updateLength*2,UserDetailsPage.getUserName().length() - userNameLength, "UserName updated: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit User page. Remove changes -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		Thread.sleep(TimeOut - 1000);
		firstName = EditUserPage.getfirstName();
		firstName = firstName.replace(update, "");
		EditUserPage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(TimeOut - 1000);
		lastName = EditUserPage.getlastName();
		lastName = lastName.replace(update, "");
		EditUserPage.inputLastName(lastName);

//		Save changes
		Thread.sleep(TimeOut - 1000);
		EditUserPage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=22, testName = "EOAdmin Update Active EOAdmin - Role", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEOAdmin_RoleUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		int a = 0;
		String role = "";

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickUserMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		testLog.info("------------------------------------------------- Search for Active EO Admin -------------------------------------------------");

		a = UsersPage.activeEOAdminRow();
		if (a <= 0){
			AssertJUnit.fail("Active EO Admin not found");
		}
		System.out.println(a);
		UsersPage.clickOnRow(a);

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		role = UserDetailsPage.getRole();

		UserDetailsPage.clickLnkEditRole();

		testLog.info("------------------------------------------- Edit User page. Update User data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role
		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Cancel changes
		EditRolePage.clickBtnCancel();

		testLog.info("------------------------------------------ User Details page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(role);
//		Verify User Role not changed
		if (!Assertions.compareValue(role, UserDetailsPage.getRole(), "User Role: ", testLog, driver)){
			TestPassFlag = false;
		}

		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page. Verify User Role updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Role updated
		if (!Assertions.compareBoolean(true, UserDetailsPage.getMessage().contains("The user's account was successfully updated."), "Got message: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, UserDetailsPage.getRole().contains(role), "User Role: ", testLog, driver)){
			TestPassFlag = false;
		}

//		Return role value
		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_ADMIN");

//		Save changes
		EditRolePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=23, testName = "EOAdmin Update Active Merchant - Business Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateActiveMerchant_BusInfoUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		int a = 0;
		String Name = "";
		String update = " updated";
		String updateNum = "000";
		String IndCode = "";
		String Country = "";
		String Street = "";
		String City = "";
		String State = "";
		String PostalCode = "";
		String PhoneCode = "";
		String PhoneNumber = "";
		String Mail = "";
		String BusName = "";
		String BusIndCode = "";
		String BusMID = "";
		String BusAddress = "";
		String BusContNumber = "";
		String BusCountry = "";
		String BusEmail = "";
		String UpdatedMail = "";

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Merchants page -------------------------------------------------");

		HomePage.clickMerchantsMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
		AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

		testLog.info("------------------------------------------------- Search for Active Merchant -------------------------------------------------");

		a = MerchantsPage.activeMerchantRow();
		if (a <= 0){
			AssertJUnit.fail("Active Merchant not found");
		}
		System.out.println(a);
		MerchantsPage.clickOnRow(a);

		testLog.info("------------------------------------------------- Merchant Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Get data from Business Information page
		BusName = MerchantDetailsPage.getBusName();
		BusIndCode = MerchantDetailsPage.getIndCode();
		BusMID = MerchantDetailsPage.getMID();
		BusCountry = MerchantDetailsPage.getCountry();
		BusAddress = MerchantDetailsPage.getAddress();
		BusContNumber = MerchantDetailsPage.getContactNumber();
		BusEmail = MerchantDetailsPage.getBusEmail();
		Thread.sleep(2000);

		MerchantDetailsPage.clickLnkEditBus();

		testLog.info("------------------------------------------- Edit Business page. Update Business data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		Name = EditBusinessPage.getName();
//		Validation check
		EditBusinessPage.inputName("");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Name must not be empty", EditBusinessPage.errorName(), "Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputName(" ");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Name must not be empty", EditBusinessPage.errorName(), "Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputName("~!@#$%^&*()");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Name should contain only letters, numbers, commas, space, period, underscore, hyphen, apostrophe (') and the ampersand key (&). Name should begin with a letter or number", EditBusinessPage.errorName(), "Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputName("qwertyuiopqwertyuiopqwertyuiop.-.-.-123456789012345");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Name must be at least 1 characters long, but not more than 50 characters", EditBusinessPage.errorName(), "Name validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(TimeOut);
		IndCode = EditBusinessPage.getIndCode();
//		Validation check
		EditBusinessPage.inputIndCode("");
		EditBusinessPage.clickFldName();
		if (!Assertions.compareValue("Field Industry Code must not be empty", EditBusinessPage.errorIndCode(), "Industry Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputIndCode(" ");
		EditBusinessPage.clickFldName();
		if (!Assertions.compareValue("Field Industry Code must not be empty", EditBusinessPage.errorIndCode(), "Industry Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}
		EditBusinessPage.inputIndCode("@#$%^&*()123");
		EditBusinessPage.clickFldName();
		if (!Assertions.compareValue("Field Industry Code should contain only numbers", EditBusinessPage.errorIndCode(), "Industry Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputIndCode("String123");
		EditBusinessPage.clickFldName();
		if (!Assertions.compareValue("Field Industry Code should contain only numbers", EditBusinessPage.errorIndCode(), "Industry Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputIndCode("123456789012345678901234567890123456789012345678901");
		EditBusinessPage.clickFldName();
		if (!Assertions.compareValue("Field Industry Code must be at least 1 characters long, but not more than 50 characters", EditBusinessPage.errorIndCode(), "Industry Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputIndCode(IndCode + updateNum);

//		Verify MID field disabled
		Thread.sleep(TimeOut);
		if (!Assertions.compareBoolean(false, EditBusinessPage.fldMIDEnabled(), "MID field editable: ", testLog, driver)){
			TestPassFlag = false;
		}

//		Update Country
		Thread.sleep(TimeOut);
		Country = EditBusinessPage.getCountry();

		EditBusinessPage.updateCountry("AW");
		EditBusinessPage.updateCountry("US");

//		Update Street
		Thread.sleep(TimeOut);
		Street = EditBusinessPage.getStreet();
//		Validation check
		EditBusinessPage.inputStreet("");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Street 1 must not be empty", EditBusinessPage.errorStreet(), "Street validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputStreet(" ");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Street 1 must not be empty", EditBusinessPage.errorStreet(), "Street validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputStreet("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789 @");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Street 1 must be at least 1 characters long, but not more than 100 characters", EditBusinessPage.errorStreet(), "Street validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(TimeOut);
		City = EditBusinessPage.getCity();
//		Validation check
		EditBusinessPage.inputCity("");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Town/City must not be empty", EditBusinessPage.errorCity(), "City validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputCity(" ");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Town/City must not be empty", EditBusinessPage.errorCity(), "City validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputCity("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789 @");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Town/City must be at least 1 characters long, but not more than 100 characters", EditBusinessPage.errorCity(), "City validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputCity(City + update);

//		Update State
		Thread.sleep(TimeOut);
		State = EditBusinessPage.getState();
//		Validation check
		EditBusinessPage.inputState("");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field State/Province/County must not be empty", EditBusinessPage.errorState(), "State validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputState(" ");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field State/Province/County must not be empty", EditBusinessPage.errorState(), "State validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputState("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 1");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field State/Province/County must be at least 1 characters long, but not more than 50 characters", EditBusinessPage.errorState(), "State validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputState(State + update);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		PostalCode = EditBusinessPage.getZipCode();
//		Validation check
		EditBusinessPage.inputPostalCode("");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field ZIP/Postal Code must not be empty", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode("");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field ZIP/Postal Code must not be empty", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode("!@#$%^&*()");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field ZIP/Postal Code should contain only letters, numbers, space and hyphen (-), and must begin with a letter or number", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode("1");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field ZIP/Postal Code must be at least 2 characters long, but not more than 50 characters", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode("123456789012345678901234567890123456789012345678901");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field ZIP/Postal Code must be at least 2 characters long, but not more than 50 characters", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
		Thread.sleep(TimeOut);
		PhoneCode = EditBusinessPage.getPhoneCode();
//		Validation check
//		EditBusinessPage.inputPhoneCode("");
//		EditBusinessPage.clickFldIndCode();
//		AssertJUnit.assertEquals("Field Code must not be empty", EditBusinessPage.errorPhoneCode());
//
//		EditBusinessPage.inputPhoneCode(" ");
//		EditBusinessPage.clickFldIndCode();
//		AssertJUnit.assertEquals("Field Code must not be empty", EditBusinessPage.errorPhoneCode());

		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(2000);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
//		Validation check
		EditBusinessPage.inputPhoneNumber("");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Contact Number must not be empty", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber(" ");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Contact Number must not be empty", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber("strng!@#$%^&*()");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Contact Number should contain only numbers", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber("24234 34534543");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Contact Number should contain only numbers", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber("242");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Contact Number must be at least 4 characters long, but not more than 20 characters", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber("123456789012345678901");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Contact Number must be at least 4 characters long, but not more than 20 characters", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(TimeOut);
		Mail = EditBusinessPage.getEmail();
//		Validation check
		EditBusinessPage.inputMail("");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Email must not be empty", EditBusinessPage.errorMail(), "Mail validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputMail(" ");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Email must not be empty", EditBusinessPage.errorMail(), "Mail validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputMail("strng!@#$%^&*()");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Email should be a valid email", EditBusinessPage.errorMail(), "Mail validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputMail("czxvbnmqwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890@mail.com");
		EditBusinessPage.clickFldIndCode();
		if (!Assertions.compareValue("Field Email must be at least 1 characters long, but not more than 255 characters", EditBusinessPage.errorMail(), "Mail validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

		EditBusinessPage.clickFldIndCode();
		EditBusinessPage.clickBtnCancel();

		EditBusinessPage.clickCancelMsgYes();

		testLog.info("------------------------------------------ Merchant Details page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify No changes saved
		if (!Assertions.compareValue(BusName, MerchantDetailsPage.getBusName(), "Business Name not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusIndCode, MerchantDetailsPage.getIndCode(), "Industry Code not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusMID, MerchantDetailsPage.getMID(), "MID not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusCountry, MerchantDetailsPage.getCountry(), "Country not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusAddress, MerchantDetailsPage.getAddress(), "Address not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareValue(BusContNumber, MerchantDetailsPage.getContactNumber(), "Contact Number not changed: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareValue(BusEmail, MerchantDetailsPage.getBusEmail(), "Email not changed: ", testLog, driver)){
			TestPassFlag = false;
		}

		MerchantDetailsPage.clickLnkEditBus();

		testLog.info("------------------------------------------- Edit Business page. Update Business data. Save -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(TimeOut);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);


//		Update Country
		Thread.sleep(TimeOut);
		Country = EditBusinessPage.getCountry();
		EditBusinessPage.updateCountry("AW");

//		Update Street
		Thread.sleep(TimeOut);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(TimeOut);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
//		Thread.sleep(TimeOut);
//		PhoneCode = EditBusinessPage.getPhoneCode();
//		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(TimeOut);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(TimeOut);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		testLog.info("------------------------------------------------- Merchant Details page. Verify Business data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 2000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify changes saved
		if (!Assertions.compareBoolean(false, BusName.contains(MerchantDetailsPage.getBusName()), "Business Name stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusIndCode.contains(MerchantDetailsPage.getIndCode()), "Industry Code stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusAddress.contains(MerchantDetailsPage.getAddress()), "Address stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareBoolean(false, BusContNumber.contains(MerchantDetailsPage.getContactNumber()), "Contact Number stay with no changes: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareBoolean(false, BusEmail.contains(MerchantDetailsPage.getBusEmail()), "Email stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}

		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getBusName().contains(update), "Business Name updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getIndCode().contains(updateNum), "Industry Code updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, BusMID.contains(MerchantDetailsPage.getMID()), "MID updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getAddress().contains(update), "Address updated: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getContactNumber().contains(updateNum), "Contact Number updated: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getBusEmail().contains(update.replace(" ","")), "Email updated: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit Merchant page. Remove changes -------------------------------------------------");

		MerchantDetailsPage.clickLnkEditBus();
//		Remove changes
//		Edit Business page again
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		EditBusinessPage.inputName(Name);

//		Update Industry Code
		Thread.sleep(TimeOut);
		EditBusinessPage.inputIndCode(IndCode);


//		Update Country
		Thread.sleep(TimeOut);
		EditBusinessPage.updateCountry(Country);

//		Update Street
		Thread.sleep(TimeOut);
		EditBusinessPage.inputStreet(Street);

//		Update City
		Thread.sleep(TimeOut);
		EditBusinessPage.inputCity(City);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		EditBusinessPage.inputPostalCode(PostalCode);

//		Update Phone Code
//		Thread.sleep(TimeOut);
//		EditBusinessPage.inputPhoneCode(PhoneCode);

//		Update Phone Number
		Thread.sleep(TimeOut);
		EditBusinessPage.inputPhoneNumber(PhoneNumber);

//		Update Email
		Thread.sleep(TimeOut);
		EditBusinessPage.inputMail(Mail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		testLog.info("------------------------------------------------- Merchant Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=24, testName = "Merchant Manager Update Pending Merchant - Business Info", groups = { "Sanity" }, alwaysRun = true)

	public void MerchantManagerUpdatePendingMerchant_BusInfoUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		int a = 0;
		String Name = "";
		String update = " updated";
		String updateNum = "000";
		String IndCode = "";
		String Country = "";
		String Street = "";
		String City = "";
		String State = "";
		String PostalCode = "";
		String PhoneCode = "";
		String PhoneNumber = "";
		String Mail = "";
		String BusName = "";
		String BusIndCode = "";
		String BusMID = "";
		String BusAddress = "";
		String BusContNumber = "";
		String BusCountry = "";
		String BusEmail = "";
		String UpdatedMail = "";

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + UserMerchManEmail + " " + UserMerchManPwd + "-------------------------------------------------");

		User EOMerchantManager = EntitiesFactory.getEntity("EOMerchantManager");
		UserMerchManEmail = EOMerchantManager.getUserName();
		UserMerchManPwd = EOMerchantManager.getPassword();
		LoginEOPortal.loginInputEmail(UserMerchManEmail);
		LoginEOPortal.loginInputPassword(UserMerchManPwd);
		LoginEOPortal.clickLoginBtn();

		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Merchants page -------------------------------------------------");

		HomePage.clickMerchantsMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
		AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

		testLog.info("------------------------------------------------- Search for Pending Merchant -------------------------------------------------");

		a = MerchantsPage.pendingMerchantRow();
		if (a <= 0){
			AssertJUnit.fail("Pending Merchant not found");
		}
		System.out.println(a);
		MerchantsPage.clickOnRow(a);

		testLog.info("------------------------------------------------- Merchant Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Get data from Business Information page
		BusName = MerchantDetailsPage.getBusName();
		BusIndCode = MerchantDetailsPage.getIndCode();
		BusMID = MerchantDetailsPage.getMID();
		BusCountry = MerchantDetailsPage.getCountry();
		BusAddress = MerchantDetailsPage.getAddress();
		BusContNumber = MerchantDetailsPage.getContactNumber();
		BusEmail = MerchantDetailsPage.getBusEmail();
		Thread.sleep(2000);

		MerchantDetailsPage.clickLnkEditBus();

		testLog.info("------------------------------------------- Edit Business page. Update Business data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(TimeOut);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);

//		Verify MID field disabled
		Thread.sleep(TimeOut);
		AssertJUnit.assertEquals(false, EditBusinessPage.fldMIDEnabled());

//		Update Country
		Thread.sleep(TimeOut);
		Country = EditBusinessPage.getCountry();

		EditBusinessPage.updateCountry("AW");
		EditBusinessPage.updateCountry("US");

//		Update Street
		Thread.sleep(TimeOut);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(TimeOut);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update State
		Thread.sleep(TimeOut);
		State = EditBusinessPage.getState();
		EditBusinessPage.inputState(State + update);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
//		Thread.sleep(TimeOut);
//		PhoneCode = EditBusinessPage.getPhoneCode();
//		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(TimeOut);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(TimeOut);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

		EditBusinessPage.clickBtnCancel();

		EditBusinessPage.clickCancelMsgYes();

		testLog.info("------------------------------------------ Merchant Business page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify No changes saved
		if (!Assertions.compareValue(BusName, MerchantDetailsPage.getBusName(), "Business Name not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusIndCode, MerchantDetailsPage.getIndCode(), "Industry Code not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusMID, MerchantDetailsPage.getMID(), "MID not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusCountry, MerchantDetailsPage.getCountry(), "Country not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusAddress, MerchantDetailsPage.getAddress(), "Address not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareValue(BusContNumber, MerchantDetailsPage.getContactNumber(), "Contact Number not changed: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareValue(BusEmail, MerchantDetailsPage.getBusEmail(), "Email not changed: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------- Edit Business page. Update Business data. Save -------------------------------------------------");

		MerchantDetailsPage.clickLnkEditBus();

//		Edit Business page again
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(TimeOut);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);


//		Update Country
		Thread.sleep(TimeOut);
		Country = EditBusinessPage.getCountry();
		EditBusinessPage.updateCountry("AW");

//		Update Street
		Thread.sleep(TimeOut);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(TimeOut);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
//		Thread.sleep(TimeOut);
//		PhoneCode = EditBusinessPage.getPhoneCode();
//		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(TimeOut);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(TimeOut);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		testLog.info("------------------------------------------ Merchant Business page. Verify data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify changes saved
		if (!Assertions.compareBoolean(false, BusName.contains(MerchantDetailsPage.getBusName()), "Business Name stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusIndCode.contains(MerchantDetailsPage.getIndCode()), "Industry Code stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusAddress.contains(MerchantDetailsPage.getAddress()), "Address stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareBoolean(false, BusContNumber.contains(MerchantDetailsPage.getContactNumber()), "Contact Number stay with no changes: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareBoolean(false, BusEmail.contains(MerchantDetailsPage.getBusEmail()), "Email stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}

		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getBusName().contains(update), "Business Name updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getIndCode().contains(updateNum), "Industry Code updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, BusMID.contains(MerchantDetailsPage.getMID()), "MID updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getAddress().contains(update), "Address updated: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getContactNumber().contains(updateNum), "Contact Number updated: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getBusEmail().contains(update.replace(" ","")), "Email updated: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit Business page. Remove changes -------------------------------------------------");

		MerchantDetailsPage.clickLnkEditBus();
//		Remove changes
//		Edit Business page again
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		EditBusinessPage.inputName(Name);

//		Update Industry Code
		Thread.sleep(TimeOut);
		EditBusinessPage.inputIndCode(IndCode);


//		Update Country
		Thread.sleep(TimeOut);
		EditBusinessPage.updateCountry(Country);

//		Update Street
		Thread.sleep(TimeOut);
		EditBusinessPage.inputStreet(Street);

//		Update City
		Thread.sleep(TimeOut);
		EditBusinessPage.inputCity(City);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		EditBusinessPage.inputPostalCode(PostalCode);

//		Update Phone Code
//		Thread.sleep(TimeOut);
//		EditBusinessPage.inputPhoneCode(PhoneCode);

//		Update Phone Number
		Thread.sleep(TimeOut);
		EditBusinessPage.inputPhoneNumber(PhoneNumber);

//		Update Email
		Thread.sleep(TimeOut);
		EditBusinessPage.inputMail(Mail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		testLog.info("------------------------------------------------- Merchant Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}
	@Test(enabled = true, priority=25, testName = "EOAdmin Update Disabled Merchant - Business Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateDisabledMerchant_BusInfoUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		String Name = "";
		String update = " updated";
		String updateNum = "000";
		String IndCode = "";
		String Country = "";
		String Street = "";
		String City = "";
		String State = "";
		String PostalCode = "";
		String PhoneCode = "";
		String PhoneNumber = "";
		String Mail = "";
		String BusName = "";
		String BusIndCode = "";
		String BusMID = "";
		String BusAddress = "";
		String BusContNumber = "";
		String BusCountry = "";
		String BusEmail = "";
		String UpdatedMail = "";

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Merchants page -------------------------------------------------");

		HomePage.clickMerchantsMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
		AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

		testLog.info("------------------------------------------------- Search for Disabled Merchant -------------------------------------------------");

		int a = MerchantsPage.disableMerchantRow();
		if (a >= 1000){
			AssertJUnit.fail("Disabled Merchant not found");
		}
		System.out.println(a);
		MerchantsPage.clickOnRow(a);

		testLog.info("------------------------------------------------- Merchant Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Get data from Business Information page
		BusName = MerchantDetailsPage.getBusName();
		BusIndCode = MerchantDetailsPage.getIndCode();
		BusMID = MerchantDetailsPage.getMID();
		BusCountry = MerchantDetailsPage.getCountry();
		BusAddress = MerchantDetailsPage.getAddress();
		BusContNumber = MerchantDetailsPage.getContactNumber();
		BusEmail = MerchantDetailsPage.getBusEmail();
		Thread.sleep(TimeOut);

		MerchantDetailsPage.clickLnkEditBus();

		testLog.info("------------------------------------------- Edit Business page. Update Business data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(TimeOut);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);

//		Verify MID field disabled
		Thread.sleep(TimeOut);
		AssertJUnit.assertEquals(false, EditBusinessPage.fldMIDEnabled());

//		Update Country
		Thread.sleep(TimeOut);
		Country = EditBusinessPage.getCountry();

		EditBusinessPage.updateCountry("AW");
		EditBusinessPage.updateCountry("US");

//		Update Street
		Thread.sleep(TimeOut);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(TimeOut);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update State
		Thread.sleep(TimeOut);
		State = EditBusinessPage.getState();
		EditBusinessPage.inputState(State + update);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
//		Thread.sleep(TimeOut);
//		PhoneCode = EditBusinessPage.getPhoneCode();
//		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(TimeOut);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(TimeOut);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

		EditBusinessPage.clickBtnCancel();

		EditBusinessPage.clickCancelMsgYes();

		testLog.info("------------------------------------------ Merchant Business page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

		if (!Assertions.compareValue(BusName, MerchantDetailsPage.getBusName(), "Business Name not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusIndCode, MerchantDetailsPage.getIndCode(), "Industry Code not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusMID, MerchantDetailsPage.getMID(), "MID not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusCountry, MerchantDetailsPage.getCountry(), "Country not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusAddress, MerchantDetailsPage.getAddress(), "Address not changed: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareValue(BusContNumber, MerchantDetailsPage.getContactNumber(), "Contact Number not changed: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareValue(BusEmail, MerchantDetailsPage.getBusEmail(), "Email not changed: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------- Edit Business page. Update Business data. Save -------------------------------------------------");

		MerchantDetailsPage.clickLnkEditBus();

//		Edit Business page again
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(TimeOut);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);


//		Update Country
		Thread.sleep(TimeOut);
		Country = EditBusinessPage.getCountry();
		EditBusinessPage.updateCountry("AW");

//		Update Street
		Thread.sleep(TimeOut);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(TimeOut);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
//		Thread.sleep(TimeOut);
//		PhoneCode = EditBusinessPage.getPhoneCode();
//		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(TimeOut);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(TimeOut);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		testLog.info("------------------------------------------ Merchant Business page. Verify data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify changes saved
		if (!Assertions.compareBoolean(false, BusName.contains(MerchantDetailsPage.getBusName()), "Business Name stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusIndCode.contains(MerchantDetailsPage.getIndCode()), "Industry Code stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusAddress.contains(MerchantDetailsPage.getAddress()), "Address stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareBoolean(false, BusContNumber.contains(MerchantDetailsPage.getContactNumber()), "Contact Number stay with no changes: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareBoolean(false, BusEmail.contains(MerchantDetailsPage.getBusEmail()), "Email stay with no changes: ", testLog, driver)){
			TestPassFlag = false;
		}

		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getBusName().contains(update), "Business Name updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getIndCode().contains(updateNum), "Industry Code updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, BusMID.contains(MerchantDetailsPage.getMID()), "MID updated: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getAddress().contains(update), "Address updated: ", testLog, driver)){
			TestPassFlag = false;
		}
//		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getContactNumber().contains(updateNum), "Contact Number updated: ", testLog, driver)){
//			TestPassFlag = false;
//		}
		if (!Assertions.compareBoolean(true, MerchantDetailsPage.getBusEmail().contains(update.replace(" ","")), "Email updated: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit Business page. Remove changes -------------------------------------------------");


		MerchantDetailsPage.clickLnkEditBus();
//		Remove changes
//		Edit Business page again
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		EditBusinessPage.inputName(Name);

//		Update Industry Code
		Thread.sleep(TimeOut);
		EditBusinessPage.inputIndCode(IndCode);


//		Update Country
		Thread.sleep(TimeOut);
		EditBusinessPage.updateCountry(Country);

//		Update Street
		Thread.sleep(TimeOut);
		EditBusinessPage.inputStreet(Street);

//		Update City
		Thread.sleep(TimeOut);
		EditBusinessPage.inputCity(City);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		EditBusinessPage.inputPostalCode(PostalCode);

//		Update Phone Code
//		Thread.sleep(TimeOut);
//		EditBusinessPage.inputPhoneCode(PhoneCode);

//		Update Phone Number
		Thread.sleep(TimeOut);
		EditBusinessPage.inputPhoneNumber(PhoneNumber);

//		Update Email
		Thread.sleep(TimeOut);
		EditBusinessPage.inputMail(Mail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		testLog.info("------------------------------------------------- Merchant Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}
	@Test(enabled = true, priority=26, testName = "Dev and App Manager CANT Update Enabled Merchant - Business Info", groups = { "Sanity" }, alwaysRun = true)

	public void DevAppManagerCANTUpdateEnabledMerchant_BusInfoUI() throws Exception {
		WebDriver driver = new HomePage().getDriver();
		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + UserDevAppEmail + " " + UserDevAppPwd + "-------------------------------------------------");

		User EODevAppManager = EntitiesFactory.getEntity("EODevAppManager");
		UserDevAppEmail = EODevAppManager.getUserName();
		UserDevAppPwd = EODevAppManager.getPassword();
		LoginEOPortal.loginInputEmail(UserDevAppEmail);
		LoginEOPortal.loginInputPassword(UserDevAppPwd);
		LoginEOPortal.clickLoginBtn();

		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to Merchants page -------------------------------------------------");

		HomePage.clickMerchantsMenu();

		Thread.sleep(TimeOut + 3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
		AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

		testLog.info("------------------------------------------------- Search for Active Merchant -------------------------------------------------");

		int a = MerchantsPage.activeMerchantRow();
		if (a <= 0){
			AssertJUnit.fail("Active Merchant not found");
		}
		System.out.println(a);
		MerchantsPage.clickOnRow(a);

		testLog.info("------------------------------------------------- Merchant Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

		testLog.info("--------------------------------------Verify Dev App Manager can't edit Business Information -----------------------------------------");

		AssertJUnit.assertEquals(false, MerchantDetailsPage.elementBusinessEditClickable());

	}
}
