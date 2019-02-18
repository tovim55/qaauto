package com.verifone.tests.cpTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.cpPages.SetupPasswordPage;
import com.verifone.pages.eoPages.AddUserPage;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.pages.eoPages.LoginEOPortal;
import com.verifone.pages.eoPages.UserDetailsPage;
import com.verifone.pages.eoPages.UsersPage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;

public class EOPortalSanityUI extends BaseTest {

//	final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\eoAddUser.xls";
	final String xlsxFile = java.nio.file.Paths.get(
		System.getProperty("user.dir"),
		"src", "test", "resources").toString() + File.separator + "eoAddUser.xls";
	private static Integer getRowNumFromFile = 0;
	private static String UserEmail = "UserEOAdmin@getnada.com";
	private static String UserEOAdminEmail = "";
	private static String UserDevAppEmail = "";
	private static String UserMerchManEmail = "";
	private static String Env = "https://qa.estatemanager.verifonecp.com/";
	private static String EOAdminMail = "vfieous@getnada.com";
	private static String EOAdminPwd = "Veri1234";
	private WebDriver driver = new HomePage().getDriver();
	@BeforeTest
	public void startDDTest() throws Exception{
// 		Get number of Rows from Data driven
		getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "eoAddUser");
	}

//Data Provider

	@DataProvider(name = "eoAddUser")
	public Object[][] dataSupplierLoginData() throws Exception {
		Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "eoAddUser");
		return arrayObject;
	}

	@Test(enabled = true, priority=1, testName = "EOAdmin Users", dataProvider = "eoAddUser", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUsersUI(String eoRole, String eoMail, String eoPassword, String userName, String userLast, String userMail) throws Exception {
		driver.navigate().to("https://qa.estatemanager.verifonecp.com/");
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(eoMail);
		LoginEOPortal.loginInputPassword(eoPassword);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		boolean UserMenu = HomePage.menuUserExists();

		switch (eoRole) {
			case "eoMMan":
				AssertJUnit.assertEquals(false, UserMenu);
				break;
			case "eoAppDev":
				AssertJUnit.assertEquals(false, UserMenu);
				break;

			case "eoAdmin":
				AssertJUnit.assertEquals(true, UserMenu);

				HomePage.clickUserMenu();

				Thread.sleep(5000);
				availableWindows = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(availableWindows.get(0));
				UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
				AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
				UsersPage.clickAddUserBtn();

				Thread.sleep(5000);
				availableWindows = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(availableWindows.get(0));
				AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");
				AddUserPage.inputFirstName("12345");
				AddUserPage.clickLastNameFld();
				AssertJUnit.assertEquals("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", AddUserPage.errorFirstName());

				AddUserPage.inputFirstName("");
				AddUserPage.clickLastNameFld();
				AssertJUnit.assertEquals("Field First Name must not be empty", AddUserPage.errorFirstName());

				AddUserPage.inputFirstName(" ");
				AddUserPage.clickLastNameFld();
				AssertJUnit.assertEquals("Field First Name must not be empty", AddUserPage.errorFirstName());

				AddUserPage.inputFirstName("~!@#$%^&*()");
				AddUserPage.clickLastNameFld();
				AssertJUnit.assertEquals("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", AddUserPage.errorFirstName());

				AddUserPage.inputFirstName(".string");
				AddUserPage.clickLastNameFld();
				AssertJUnit.assertEquals("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", AddUserPage.errorFirstName());

				AddUserPage.inputFirstName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
				AddUserPage.clickLastNameFld();
				AssertJUnit.assertEquals("Field First Name must be at least 1 characters long, but not more than 35 characters", AddUserPage.errorFirstName());

				AddUserPage.inputFirstName("string . - 'string'.");
				AddUserPage.clickLastNameFld();

				AddUserPage.inputFirstName(userName);
				AddUserPage.clickLastNameFld();

				AddUserPage.inputLastName("12345");
				AddUserPage.clickEmailFld();
				AssertJUnit.assertEquals("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", AddUserPage.errorLastName());

				AddUserPage.inputLastName("");
				AddUserPage.clickEmailFld();
				AssertJUnit.assertEquals("Field Last Name must not be empty", AddUserPage.errorLastName());

				AddUserPage.inputLastName(" ");
				AddUserPage.clickEmailFld();
				AssertJUnit.assertEquals("Field Last Name must not be empty", AddUserPage.errorLastName());

				AddUserPage.inputLastName("~!@#$%^&*()");
				AddUserPage.clickEmailFld();
				AssertJUnit.assertEquals("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", AddUserPage.errorLastName());

				AddUserPage.inputLastName("'string'");
				AddUserPage.clickEmailFld();
				AssertJUnit.assertEquals("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", AddUserPage.errorLastName());

				AddUserPage.inputLastName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
				AddUserPage.clickEmailFld();
				AssertJUnit.assertEquals("Field Last Name must be at least 1 characters long, but not more than 35 characters", AddUserPage.errorLastName());

				AddUserPage.inputLastName("string . - 'string'.");
				AddUserPage.clickEmailFld();

				AddUserPage.inputLastName(userLast);
				AddUserPage.clickEmailFld();

				AddUserPage.inputEmail("plainaddress");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("#@%^%#$@#$@#.com");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("@example.com");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("Joe Smith <email@example.com>");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("email.example.com");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("email@example@example.com");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

//		AddUserPage.inputEmail(".email@example.com");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

//		AddUserPage.inputEmail("email.@example.com");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

//		AddUserPage.inputEmail("email..email@example.com");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("email@example.com (Joe Smith)");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("email@example");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

//		AddUserPage.inputEmail("email@-example.com");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

//		AddUserPage.inputEmail("email@example.web");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("email@111.222.333.44444");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("email@example..com");
				AddUserPage.clickDropDn();
				AssertJUnit.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

//		AddUserPage.inputEmail("Abc..123@example.com");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

				AddUserPage.inputEmail("");
				AddUserPage.clickDropDn();
				Thread.sleep(1000);
				AssertJUnit.assertEquals("Field Email must not be empty", AddUserPage.errorEmail());

				AddUserPage.inputEmail(userMail);

//		AddUserPage.clickDropDn();

				AddUserPage.clickDropDnItem("EO Merchant Manager");

				AddUserPage.clickDropDn();

				AddUserPage.clickDropDnItem("EO Device and App Manager");

				AddUserPage.clickDropDn();

				AddUserPage.clickDropDnItem("EO Admin");

				AddUserPage.clickCancelBtn();

				availableWindows = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(availableWindows.get(0));
				UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

				boolean fl = UsersPage.tblUsersText().contains(userMail);
				AssertJUnit.assertEquals(false, fl);

				break;
		}
	}

	@BeforeTest
	public void startDDTest1() throws Exception{
// 		Get number of Rows from Data driven

	}

	@Test(enabled = true, priority=2, testName = "EOAdmin Users", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminAddUserUI() throws Exception {
		driver.navigate().to("https://qa.estatemanager.verifonecp.com/");
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail("vfieous@getnada.com");
		LoginEOPortal.loginInputPassword("Veri1234");
//	LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		UsersPage.clickAddUserBtn();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");
		AddUserPage.inputFirstName("UserEOAdmin");
		AddUserPage.inputLastName("UserLastEOAdmin");
		AddUserPage.inputEmail(UserEmail);
		AddUserPage.clickDropDn();
		AddUserPage.clickDropDnItem("EO Admin");
		AddUserPage.clickSubmitBtn();
	}
	@BeforeTest
	public void startDDTest2() throws Exception{
// 		Get number of Rows from Data driven

	}

	@Test(enabled = true, priority=3, testName = "EOAdmin Users View", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminViewUI() throws Exception {
		driver.navigate().to("https://qa.estatemanager.verifonecp.com/");
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail("vfieous@getnada.com");
		LoginEOPortal.loginInputPassword("Veri1234");
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		HomePage.clickHeaderMenu();

		boolean UserMenu = HomePage.menuUserExists();
		AssertJUnit.assertEquals(true, UserMenu);

		HomePage.clickUserMenu();

		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

		AssertJUnit.assertEquals(true, UsersPage.tblUsersExists());
		AssertJUnit.assertEquals(true, UsersPage.pgUsersExists());
		AssertJUnit.assertEquals(true, UsersPage.btnAddUserExists());
	}

	@BeforeTest
	public void startDDTest3() throws Exception{
// 		Get number of Rows from Data driven

	}

	@Test(enabled = true, priority=4, testName = "EOAdmin Add User View", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminAddUserViewUI() throws Exception {
		driver.navigate().to("https://qa.estatemanager.verifonecp.com/");
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail("vfieous@getnada.com");
		LoginEOPortal.loginInputPassword("Veri1234");
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		UsersPage.clickAddUserBtn();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");

		AssertJUnit.assertEquals("Add User", AddUserPage.titleText());
		AssertJUnit.assertEquals("Add new user to your organization", AddUserPage.titleDescText());
		AssertJUnit.assertEquals("User Information", AddUserPage.titlePanelText());
		AssertJUnit.assertEquals("First Name", AddUserPage.hintFName());
		AssertJUnit.assertEquals("Last Name", AddUserPage.hintLName());
		AssertJUnit.assertEquals("Email", AddUserPage.hintEmail());
		AssertJUnit.assertEquals("This will be used for the business member to log into the system. Once submitted this user will get an email to set up their account.", AddUserPage.helpEmail());
		AssertJUnit.assertEquals("Role", AddUserPage.titleRole());

		AssertJUnit.assertEquals("Cancel", AddUserPage.btnCancelLabel());
		AssertJUnit.assertEquals("Submit", AddUserPage.btnSubmitLabel());
	}
	@BeforeTest
	public void startDDTest4() throws Exception{
// 		Get number of Rows from Data driven

	}

	@Test(enabled = true, priority=5, testName = "EOAdmin Add EOAdmin Exist Email", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminAddUserExistEmailUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		UsersPage.clickAddUserBtn();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");
		AddUserPage.inputFirstName("UserEOAdmin two");
		AddUserPage.inputLastName("UserLastEOAdmin two");
		AddUserPage.inputEmail(UserEmail);
		AddUserPage.clickDropDn();
		AddUserPage.clickDropDnItem("EO Admin");
		AddUserPage.clickSubmitBtn();
		Thread.sleep(3000);
		AssertJUnit.assertEquals("User, " + UserEmail + ", is already associated with an existing organization and cannot be added.", AddUserPage.errorEmail());
		AssertJUnit.assertEquals("User, " + UserEmail + ", is already associated with an existing organization and cannot be added.", AddUserPage.msgErrorText());

		UserEOAdminEmail = "User" + LocalDateTime.now() + "EOAdmin@getnada.com";
		UserEOAdminEmail = UserEOAdminEmail.replace("-", "");
		UserEOAdminEmail = UserEOAdminEmail.replace(":", "");
		AddUserPage.inputEmail(UserEOAdminEmail);
		AddUserPage.clickDropDn();
		AddUserPage.clickDropDnItem("EO Admin");
		AddUserPage.clickSubmitBtn();

		Thread.sleep(10000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

		boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserEOAdminEmail);
		AssertJUnit.assertEquals(true, fl);
	}

	@Test(enabled = true, priority=6, testName = "EOAdmin Add Merchant Manager Exist Email", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminAddMerchantManagerUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		UsersPage.clickAddUserBtn();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");
		AddUserPage.inputFirstName("UserEOAdmin two");
		AddUserPage.inputLastName("UserLastEOAdmin two");

		UserMerchManEmail = "User" + LocalDateTime.now() + "MerchMan@getnada.com";
		UserMerchManEmail = UserMerchManEmail.replace("-", "");
		UserMerchManEmail = UserMerchManEmail.replace(":", "");
		AddUserPage.inputEmail(UserMerchManEmail);
		AddUserPage.clickDropDn();
		AddUserPage.clickDropDnItem("EO Merchant Manager");
		AddUserPage.clickSubmitBtn();

		Thread.sleep(10000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

		boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserMerchManEmail);
		AssertJUnit.assertEquals(true, fl);
	}
	@Test(enabled = true, priority=7, testName = "EOAdmin Add Dev App Manager Exist Email", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminAddDevAppManagerUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		UsersPage.clickAddUserBtn();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");
		AddUserPage.inputFirstName("UserEOAdmin two");
		AddUserPage.inputLastName("UserLastEOAdmin two");

		UserDevAppEmail = "User" + LocalDateTime.now() + "DevAppMan@getnada.com";
		UserDevAppEmail = UserDevAppEmail.replace("-", "");
		UserDevAppEmail = UserDevAppEmail.replace(":", "");
		AddUserPage.inputEmail(UserDevAppEmail);
		AddUserPage.clickDropDn();
		AddUserPage.clickDropDnItem("EO Device and App Manager");
		AddUserPage.clickSubmitBtn();

		Thread.sleep(10000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

		boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserDevAppEmail);
		AssertJUnit.assertEquals(true, fl);
	}
	@Test(enabled = true, priority=8, testName = "EOAdmin Edit pending EOAdmin", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingEOAdminUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.pendingEOAdminRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(false, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Pending", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Resend Invitation"));
		AssertJUnit.assertEquals("EO Admin", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkResend();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogResendExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogResend().contains("This will resend another copy of the invitation email to the pending user. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelResend();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkResend();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogResendExists());
		UserDetailsPage.clickDoResend();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("Your invitation email was successfully sent.", UserDetailsPage.getMessage());

	}
	@Test(enabled = true, priority=9, testName = "EOAdmin Edit pending Merchant Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingMerchantManUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.pendingMerchantManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(false, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Pending", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Resend Invitation"));
		AssertJUnit.assertEquals("EO Merchant Manager", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkResend();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogResendExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogResend().contains("This will resend another copy of the invitation email to the pending user. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelResend();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkResend();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogResendExists());
		UserDetailsPage.clickDoResend();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("Your invitation email was successfully sent.", UserDetailsPage.getMessage());

	}
	@Test(enabled = true, priority=10, testName = "EOAdmin Edit pending Device App Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingDevAppManUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.pendingDevAppManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(false, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Pending", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Resend Invitation"));
		AssertJUnit.assertEquals("EO Device and App Manager", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkResend();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogResendExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogResend().contains("This will resend another copy of the invitation email to the pending user. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelResend();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkResend();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogResendExists());
		UserDetailsPage.clickDoResend();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("Your invitation email was successfully sent.", UserDetailsPage.getMessage());

	}
	@Test(enabled = true, priority=11, testName = "EOAdmin Disable active Device App Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveDevAppManUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeDevAppManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		Thread.sleep(2000);
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(true, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Active", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Resend Invitation"));;
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Disable User"));
		AssertJUnit.assertEquals("EO Device and App Manager", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(true, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkDisable();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogDisableExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogDisable().contains("This will disable this user account. They will not be able to log in to any Verifone portals. You can re-enable users that were disabled at any time. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelDisable();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkDisable();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogDisableExists());
		UserDetailsPage.clickDoDisable();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("The user's account was successfully disabled.", UserDetailsPage.getMessage());

	}
	@Test(enabled = true, priority=12, testName = "EOAdmin Enable disabled Device App Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEnableDisabledDevAppManUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.disableDevAppManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(false, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Disabled", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Resend Invitation"));
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Disable User"));
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Enable User"));
		AssertJUnit.assertEquals("EO Device and App Manager", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkEnable();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogEnableExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogEnable().contains("This will re-enable this user account and restore their access to the portal. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelEnable();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkEnable();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogEnableExists());
		UserDetailsPage.clickDoEnable();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("The user's account was successfully re-enabled.", UserDetailsPage.getMessage());

	}
	@Test(enabled = true, priority=13, testName = "EOAdmin Disable active EO Admin", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveEOAdminUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeEOAdminRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(true, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Active", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Resend Invitation"));
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Disable User"));
		AssertJUnit.assertEquals("EO Admin", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(true, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkDisable();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogDisableExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogDisable().contains("This will disable this user account. They will not be able to log in to any Verifone portals. You can re-enable users that were disabled at any time. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelDisable();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkDisable();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogDisableExists());
		UserDetailsPage.clickDoDisable();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("The user's account was successfully disabled.", UserDetailsPage.getMessage());

	}
	@Test(enabled = true, priority=14, testName = "EOAdmin Enable disabled EO Admin", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEnableDisabledEOAdminUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.disableEOAdminRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(false, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Disabled", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Resend Invitation"));
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Disable User"));
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Enable User"));
		AssertJUnit.assertEquals("EO Admin", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkEnable();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogEnableExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogEnable().contains("This will re-enable this user account and restore their access to the portal. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelEnable();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkEnable();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogEnableExists());
		UserDetailsPage.clickDoEnable();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("The user's account was successfully re-enabled.", UserDetailsPage.getMessage());

	}

	@Test(enabled = true, priority=15, testName = "EOAdmin Disable active EO Merchant Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveEOMerchantManUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeEOMerchantManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(true, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Active", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Resend Invitation"));
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Disable User"));
		AssertJUnit.assertEquals("EO Merchant Manager", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(true, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkDisable();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogDisableExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogDisable().contains("This will disable this user account. They will not be able to log in to any Verifone portals. You can re-enable users that were disabled at any time. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelDisable();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkDisable();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogDisableExists());
		UserDetailsPage.clickDoDisable();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("The user's account was successfully disabled.", UserDetailsPage.getMessage());

	}
	@Test(enabled = true, priority=16, testName = "EOAdmin Enable disabled EO Merchant Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEnableDisabledEOMerchantManUI() throws Exception {
		driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.disableEOMerchantManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");

		AssertJUnit.assertEquals(false, UserDetailsPage.elementEmailClickable());
		boolean fl = false;
		if (UserDetailsPage.getUserEmail().length() > 0) {
			fl = true;
		}
		AssertJUnit.assertEquals(true, fl);
		AssertJUnit.assertEquals(false, UserDetailsPage.elementUserEditClickable());
		AssertJUnit.assertEquals(UserDetailsPage.getTitle(), UserDetailsPage.getUserName());
		AssertJUnit.assertEquals("Disabled", UserDetailsPage.getStatus());
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Resend Invitation"));
		AssertJUnit.assertEquals(false, UserDetailsPage.getAction().contains("Disable User"));
		AssertJUnit.assertEquals(true, UserDetailsPage.getAction().contains("Enable User"));
		AssertJUnit.assertEquals("EO Merchant Manager", UserDetailsPage.getRole());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleClickable());
		AssertJUnit.assertEquals(false, UserDetailsPage.elementRoleEditClickable());

		UserDetailsPage.clickLnkEnable();
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogEnableExists());
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.getDialogEnable().contains("This will re-enable this user account and restore their access to the portal. Continue?"));
		Thread.sleep(3000);
		UserDetailsPage.clickCancelEnable();
		AssertJUnit.assertEquals(false, UserDetailsPage.messageExists());
		Thread.sleep(1000);
		UserDetailsPage.clickLnkEnable();
		Thread.sleep(1000);
		AssertJUnit.assertEquals(true, UserDetailsPage.dialogEnableExists());
		UserDetailsPage.clickDoEnable();
		Thread.sleep(5000);
		AssertJUnit.assertEquals(true, UserDetailsPage.messageExists());
		AssertJUnit.assertEquals("The user's account was successfully re-enabled.", UserDetailsPage.getMessage());

	}

}
