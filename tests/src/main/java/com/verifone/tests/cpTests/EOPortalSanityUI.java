package com.verifone.tests.cpTests;

import com.verifone.pages.eoPages.*;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

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
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;

public class EOPortalSanityUI extends BaseTest {

final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\eoAddUser.xls";
private static Integer getRowNumFromFile = 0;
private static String UserEmail = "UserEOAdmin@getnada.com";
private static String UserEOAdminEmail = "";
private static String UserDevAppEmail = "User20181010T190208.543DevAppMan@getnada.com";
private static String UserMerchManEmail = "User20181010T190123.176MerchMan@getnada.com";
private static String UserEOAdminPwd = "Veri1234";
private static String UserDevAppPwd = "Veri1234";
private static String UserMerchManPwd = "Veri1234";
private static String Env = "https://qa.estatemanager.verifonecp.com/";
private static String EOAdminMail = "vfieous@getnada.com";
private static String EOAdminPwd = "Veri1234";

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

@Test(enabled = false, priority=1, testName = "EOAdmin add Users and Cancel action", dataProvider = "eoAddUser", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminAddUsersCancelUI(String eoRole, String eoMail, String eoPassword, String userName, String userLast, String userMail) throws Exception {
		BasePage.driver.navigate().to("https://qa.estatemanager.verifonecp.com/");
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(eoMail);
		LoginEOPortal.loginInputPassword(eoPassword);
		LoginEOPortal.clickLoginBtn();
		

		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
    	BasePage.driver.switchTo().window(availableWindows.get(0));
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
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		UsersPage.clickAddUserBtn();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
		
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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

@Test(enabled = false, priority=2, testName = "EOAdmin Add Users and Submit action", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminAddUserSubmitUI() throws Exception {
	BasePage.driver.navigate().to("https://qa.estatemanager.verifonecp.com/");
	LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
	LoginEOPortal.loginInputEmail("vfieous@getnada.com");
	LoginEOPortal.loginInputPassword("Veri1234");
//	LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
	LoginEOPortal.clickLoginBtn();
	

	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
	HomePage.clickHeaderMenu();
	
	HomePage.clickUserMenu();
	
	Thread.sleep(5000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
	UsersPage.clickAddUserBtn();

	Thread.sleep(5000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
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

@Test(enabled = false, priority=3, testName = "EOAdmin View Users", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminViewUsersUI() throws Exception {
	BasePage.driver.navigate().to("https://qa.estatemanager.verifonecp.com/");
	LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
	LoginEOPortal.loginInputEmail("vfieous@getnada.com");
	LoginEOPortal.loginInputPassword("Veri1234");
	LoginEOPortal.clickLoginBtn();
	

	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
	
	HomePage.clickHeaderMenu();
	
	boolean UserMenu = HomePage.menuUserExists();
    AssertJUnit.assertEquals(true, UserMenu);
    
    HomePage.clickUserMenu();
    
    availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	
    AssertJUnit.assertEquals(true, UsersPage.tblUsersExists());
    AssertJUnit.assertEquals(true, UsersPage.pgUsersExists());
    AssertJUnit.assertEquals(true, UsersPage.btnAddUserExists());
}

@BeforeTest
public void startDDTest3() throws Exception{
// 		Get number of Rows from Data driven

}

@Test(enabled = false, priority=4, testName = "EOAdmin View Add User page", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminViewAddUserPageUI() throws Exception {
	BasePage.driver.navigate().to("https://qa.estatemanager.verifonecp.com/");
	LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
	LoginEOPortal.loginInputEmail("vfieous@getnada.com");
	LoginEOPortal.loginInputPassword("Veri1234");
	LoginEOPortal.clickLoginBtn();
	

	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
	
	HomePage.clickHeaderMenu();
	
	HomePage.clickUserMenu();
    
	Thread.sleep(5000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	
	AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
	UsersPage.clickAddUserBtn();
	
	Thread.sleep(5000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
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

@Test(enabled = false, priority=5, testName = "EOAdmin Add EOAdmin Exist Email", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminAddUserExistEmailUI() throws Exception {
	BasePage.driver.navigate().to(Env);
	LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
	LoginEOPortal.loginInputEmail(EOAdminMail);
	LoginEOPortal.loginInputPassword(EOAdminPwd);
	LoginEOPortal.clickLoginBtn();
	

	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
	HomePage.clickHeaderMenu();
	
	HomePage.clickUserMenu();
	
	Thread.sleep(5000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
	UsersPage.clickAddUserBtn();

	Thread.sleep(5000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
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
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	
	boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserEOAdminEmail);
	AssertJUnit.assertEquals(true, fl);
	}

@Test(enabled = false, priority=6, testName = "EOAdmin Add Merchant Manager Exist Email", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminAddMerchantManagerUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		UsersPage.clickAddUserBtn();
	
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		
		boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserMerchManEmail);
		AssertJUnit.assertEquals(true, fl);
	}
@Test(enabled = false, priority=7, testName = "EOAdmin Add Dev App Manager Exist Email", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminAddDevAppManagerUI() throws Exception {
	BasePage.driver.navigate().to(Env);
	LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
	LoginEOPortal.loginInputEmail(EOAdminMail);
	LoginEOPortal.loginInputPassword(EOAdminPwd);
	LoginEOPortal.clickLoginBtn();
	

	ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
	HomePage.clickHeaderMenu();
	
	HomePage.clickUserMenu();
	
	Thread.sleep(5000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
	UsersPage.clickAddUserBtn();

	Thread.sleep(5000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
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
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	
	boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserDevAppEmail);
	AssertJUnit.assertEquals(true, fl);
}
@Test(enabled = false, priority=8, testName = "EOAdmin Edit pending EOAdmin", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingEOAdminUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.pendingEOAdminRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
@Test(enabled = false, priority=9, testName = "EOAdmin Edit pending Merchant Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingMerchantManUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.pendingMerchantManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
@Test(enabled = false, priority=10, testName = "EOAdmin Edit pending Device App Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEditPendingDevAppManUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.pendingDevAppManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
@Test(enabled = false, priority=11, testName = "EOAdmin Disable active Device App Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveDevAppManUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.activeDevAppManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
@Test(enabled = false, priority=12, testName = "EOAdmin Enable disabled Device App Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminEnableDisabledDevAppManUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.disableDevAppManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
@Test(enabled = false, priority=13, testName = "EOAdmin Disable active EO Admin", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveEOAdminUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.activeEOAdminRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
	@Test(enabled = false, priority=14, testName = "EOAdmin Enable disabled EO Admin", groups = { "Sanity" }, alwaysRun = true)
	
	public void EOAdminEnableDisabledEOAdminUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.disableEOAdminRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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

	@Test(enabled = false, priority=15, testName = "EOAdmin Disable active EO Merchant Manager", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminDisableActiveEOMerchantManUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.activeEOMerchantManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
	@Test(enabled = false, priority=16, testName = "EOAdmin Enable disabled EO Merchant Manager", groups = { "Sanity" }, alwaysRun = true)
	
	public void EOAdminEnableDisabledEOMerchantManUI() throws Exception {
		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		
	
		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();
		
		HomePage.clickUserMenu();
		
		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
		
		int a = UsersPage.disableEOMerchantManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);
		
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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
	@Test(enabled = false, priority=17, testName = "EOAdmin Update enabled EO Merchant Manager - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEOMerchantMan_UserInfoUI() throws Exception {

		String firstName = "";
		String lastName = "";
		String userName = "";
		String update = " updated";
		int updateLength = update.length();

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeEOMerchantManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		userName = UserDetailsPage.getUserName();
		int userNameLength = userName.length();
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name
		firstName = EditUserPage.getfirstName();

//		Validation check
		EditUserPage.updateFirstName("12345");
		EditUserPage.clickLastNameFld();
		AssertJUnit.assertEquals("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditUserPage.errorFirstName());

		EditUserPage.inputFirstName("");
		EditUserPage.clickLastNameFld();
		AssertJUnit.assertEquals("Field First Name must not be empty", EditUserPage.errorFirstName());

		EditUserPage.inputFirstName(" ");
		EditUserPage.clickLastNameFld();
		AssertJUnit.assertEquals("Field First Name must not be empty", EditUserPage.errorFirstName());

		EditUserPage.inputFirstName("~!@#$%^&*()");
		EditUserPage.clickLastNameFld();
		AssertJUnit.assertEquals("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditUserPage.errorFirstName());

		EditUserPage.inputFirstName(".string");
		EditUserPage.clickLastNameFld();
		AssertJUnit.assertEquals("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditUserPage.errorFirstName());

		EditUserPage.inputFirstName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
		EditUserPage.clickLastNameFld();
		AssertJUnit.assertEquals("Field First Name must be at least 1 characters long, but not more than 35 characters", EditUserPage.errorFirstName());

		EditUserPage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditUserPage.getlastName();

		//		Validation check
		EditUserPage.updateLastName("12345");
		EditUserPage.clickFirstNameFld();
		AssertJUnit.assertEquals("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditUserPage.errorLastName());

		EditUserPage.inputLastName("");
		EditUserPage.clickFirstNameFld();
		AssertJUnit.assertEquals("Field Last Name must not be empty", EditUserPage.errorLastName());

		EditUserPage.inputLastName(" ");
		EditUserPage.clickFirstNameFld();
		AssertJUnit.assertEquals("Field Last Name must not be empty", EditUserPage.errorLastName());

		EditUserPage.inputLastName("~!@#$%^&*()");
		EditUserPage.clickFirstNameFld();
		AssertJUnit.assertEquals("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditUserPage.errorLastName());

		EditUserPage.inputLastName(".string");
		EditUserPage.clickFirstNameFld();
		AssertJUnit.assertEquals("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditUserPage.errorLastName());

		EditUserPage.inputLastName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
		EditUserPage.clickFirstNameFld();
		AssertJUnit.assertEquals("Field Last Name must be at least 1 characters long, but not more than 35 characters", EditUserPage.errorLastName());

		EditUserPage.inputLastName(lastName + update);
		EditUserPage.clickFirstNameFld();

//		Cancel changes
		EditUserPage.clickBtnCancel();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(userName);
//		Verify User Name not changed
		AssertJUnit.assertEquals(userName, UserDetailsPage.getUserName());

		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		firstName = EditUserPage.getfirstName();
		EditUserPage.updateFirstName(update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.updateLastName(update);

//		Save changes
		EditUserPage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		AssertJUnit.assertEquals(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."));
		AssertJUnit.assertEquals(true, UserDetailsPage.getUserName().contains(update));
		AssertJUnit.assertEquals(updateLength*2, UserDetailsPage.getUserName().length() - userNameLength);

//		Remove changes
		Thread.sleep(3000);
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		Thread.sleep(1000);
		firstName = EditUserPage.getfirstName();
		firstName = firstName.replace(update, "");
		EditUserPage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(1000);
		lastName = EditUserPage.getlastName();
		lastName = lastName.replace(update, "");
		EditUserPage.inputLastName(lastName);

//		Save changes
		Thread.sleep(1000);
		EditUserPage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
	}

	@Test(enabled = false, priority=18, testName = "EOAdmin Update enabled EO Merchant Manager - Role", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEOMerchantMan_RoleUI() throws Exception {

		String role = "";

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeEOMerchantManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		role = UserDetailsPage.getRole();

		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role
		EditRolePage.updateRole("EO_DEVICE_AND_APP_MANAGER");

//		Cancel changes
		EditRolePage.clickBtnCancel();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(role);
//		Verify User Name not changed
		AssertJUnit.assertEquals(role, UserDetailsPage.getRole());

		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_DEVICE_AND_APP_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		AssertJUnit.assertEquals(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."));
		AssertJUnit.assertEquals(false,UserDetailsPage.getRole().contains(role));

//		Return role value
		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
	}

	@Test(enabled = false, priority=19, testName = "EOAdmin Update enabled Dev and App Manager - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEODevAppMan_UserInfoUI() throws Exception {

		String firstName = "";
		String lastName = "";
		String userName = "";
		String update = " updated";
		int updateLength = update.length();

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeDevAppManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		userName = UserDetailsPage.getUserName();
		int userNameLength = userName.length();
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name
		firstName = EditUserPage.getfirstName();
		EditUserPage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.inputLastName(lastName + update);

//		Cancel changes
		EditUserPage.clickBtnCancel();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(userName);
//		Verify User Name not changed
		AssertJUnit.assertEquals(userName, UserDetailsPage.getUserName());

		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		firstName = EditUserPage.getfirstName();
		EditUserPage.updateFirstName(update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.updateLastName(update);

//		Save changes
		EditUserPage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		AssertJUnit.assertEquals(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."));
		AssertJUnit.assertEquals(true, UserDetailsPage.getUserName().contains(update));
		AssertJUnit.assertEquals(updateLength*2, UserDetailsPage.getUserName().length() - userNameLength);

//		Remove changes
		Thread.sleep(3000);
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		Thread.sleep(1000);
		firstName = EditUserPage.getfirstName();
		firstName = firstName.replace(update, "");
		EditUserPage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(1000);
		lastName = EditUserPage.getlastName();
		lastName = lastName.replace(update, "");
		EditUserPage.inputLastName(lastName);

//		Save changes
		Thread.sleep(1000);
		EditUserPage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
}

	@Test(enabled = false, priority=20, testName = "EOAdmin Update enabled Dev and App Manager - Role", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEODevAppMan_RoleUI() throws Exception {

		String role = "";

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeDevAppManRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		role = UserDetailsPage.getRole();

		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role
		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Cancel changes
		EditRolePage.clickBtnCancel();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(role);
//		Verify User Name not changed
		AssertJUnit.assertEquals(role, UserDetailsPage.getRole());

		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		AssertJUnit.assertEquals(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."));
		AssertJUnit.assertEquals(false,UserDetailsPage.getRole().contains(role));

//		Return role value
		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_DEVICE_AND_APP_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
	}
	@Test(enabled = false, priority=21, testName = "EOAdmin Update EOAdmin - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEOAdmin_UserInfoUI() throws Exception {

		String firstName = "";
		String lastName = "";
		String userName = "";
		String update = " updated";
		int updateLength = update.length();

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeEOAdminRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		userName = UserDetailsPage.getUserName();
		int userNameLength = userName.length();
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name
		firstName = EditUserPage.getfirstName();
		EditUserPage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.inputLastName(lastName + update);

//		Cancel changes
		EditUserPage.clickBtnCancel();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(userName);
//		Verify User Name not changed
		AssertJUnit.assertEquals(userName, UserDetailsPage.getUserName());

		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		firstName = EditUserPage.getfirstName();
		EditUserPage.updateFirstName(update);

//		Update last name

		lastName = EditUserPage.getlastName();
		EditUserPage.updateLastName(update);

//		Save changes
		EditUserPage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		AssertJUnit.assertEquals(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."));
		AssertJUnit.assertEquals(true, UserDetailsPage.getUserName().contains(update));
		AssertJUnit.assertEquals(updateLength*2, UserDetailsPage.getUserName().length() - userNameLength);

//		Remove changes
		Thread.sleep(3000);
		UserDetailsPage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditUserPage = (EditUserPage) PageFactory.getPage("EditUserPage");

//		Update first name

		Thread.sleep(1000);
		firstName = EditUserPage.getfirstName();
		firstName = firstName.replace(update, "");
		EditUserPage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(1000);
		lastName = EditUserPage.getlastName();
		lastName = lastName.replace(update, "");
		EditUserPage.inputLastName(lastName);

//		Save changes
		Thread.sleep(1000);
		EditUserPage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));

}

	@Test(enabled = false, priority=22, testName = "EOAdmin Update EOAdmin - Role", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateEOAdmin_RoleUI() throws Exception {

		String role = "";

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickHeaderMenu();

		HomePage.clickUserMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
		AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

		int a = UsersPage.activeEOAdminRow();
		System.out.println(a);
		UsersPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		role = UserDetailsPage.getRole();

		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role
		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Cancel changes
		EditRolePage.clickBtnCancel();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
		System.out.println(role);
//		Verify User Name not changed
		AssertJUnit.assertEquals(role, UserDetailsPage.getRole());

		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_MERCHANT_MANAGER");

//		Save changes
		EditRolePage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		UserDetailsPage = (UserDetailsPage) PageFactory.getPage("UserDetailsPage");
//		System.out.println(userName);

//		Verify User Name updated
		AssertJUnit.assertEquals(true,UserDetailsPage.getMessage().contains("The user's account was successfully updated."));
		AssertJUnit.assertEquals(false,UserDetailsPage.getRole().contains(role));

//		Return role value
		UserDetailsPage.clickLnkEditRole();

//		Edit User page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditRolePage = (EditRolePage) PageFactory.getPage("EditRolePage");

//		Update role

		EditRolePage.updateRole("EO_ADMIN");

//		Save changes
		EditRolePage.clickBtnSave();

//		User Details page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
	}

	@Test(enabled = false, priority=23, testName = "EOAdmin Update Active Merchant - Business Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateActiveMerchant_BusInfoUI() throws Exception {

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

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickMerchantsMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
		AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

		int a = MerchantsPage.activeMerchantRow();
		System.out.println(a);
		MerchantsPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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

//		Edit Business page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		Name = EditBusinessPage.getName();
//		Validation check
		EditBusinessPage.inputName("");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Name must not be empty", EditBusinessPage.errorName());

		EditBusinessPage.inputName(" ");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Name must not be empty", EditBusinessPage.errorName());

		EditBusinessPage.inputName("~!@#$%^&*()");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Name should contain only letters, numbers, commas, space, period, underscore, hyphen, apostrophe (') and the ampersand key (&). Name should begin with a letter or number", EditBusinessPage.errorName());

		EditBusinessPage.inputName("qwertyuiopqwertyuiopqwertyuiop.-.-.-123456789012345");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Name must be at least 1 characters long, but not more than 50 characters", EditBusinessPage.errorName());

		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(2000);
		IndCode = EditBusinessPage.getIndCode();
//		Validation check
		EditBusinessPage.inputIndCode("");
		EditBusinessPage.clickFldName();
		AssertJUnit.assertEquals("Field Industry Code must not be empty", EditBusinessPage.errorIndCode());

		EditBusinessPage.inputIndCode(" ");
		EditBusinessPage.clickFldName();
		AssertJUnit.assertEquals("Field Industry Code must not be empty", EditBusinessPage.errorIndCode());

		EditBusinessPage.inputIndCode("@#$%^&*()123");
		EditBusinessPage.clickFldName();
		AssertJUnit.assertEquals("Field Industry Code should contain only numbers", EditBusinessPage.errorIndCode());

		EditBusinessPage.inputIndCode("String123");
		EditBusinessPage.clickFldName();
		AssertJUnit.assertEquals("Field Industry Code should contain only numbers", EditBusinessPage.errorIndCode());

		EditBusinessPage.inputIndCode("123456789012345678901234567890123456789012345678901");
		EditBusinessPage.clickFldName();
		AssertJUnit.assertEquals("Field Industry Code must be at least 1 characters long, but not more than 50 characters", EditBusinessPage.errorIndCode());

		EditBusinessPage.inputIndCode(IndCode + updateNum);

//		Verify MID field disabled
		Thread.sleep(2000);
		AssertJUnit.assertEquals(false, EditBusinessPage.fldMIDEnabled());

//		Update Country
		Thread.sleep(2000);
		Country = EditBusinessPage.getCountry();

		EditBusinessPage.updateCountry("AW");
		EditBusinessPage.updateCountry("US");

//		Update Street
		Thread.sleep(2000);
		Street = EditBusinessPage.getStreet();
//		Validation check
		EditBusinessPage.inputStreet("");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Street 1 must not be empty", EditBusinessPage.errorStreet());

		EditBusinessPage.inputStreet(" ");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Street 1 must not be empty", EditBusinessPage.errorStreet());

		EditBusinessPage.inputStreet("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789 @");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Street 1 must be at least 1 characters long, but not more than 100 characters", EditBusinessPage.errorStreet());

		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(2000);
		City = EditBusinessPage.getCity();
//		Validation check
		EditBusinessPage.inputCity("");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Town/City must not be empty", EditBusinessPage.errorCity());

		EditBusinessPage.inputCity(" ");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Town/City must not be empty", EditBusinessPage.errorCity());

		EditBusinessPage.inputCity("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789 @");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Town/City must be at least 1 characters long, but not more than 100 characters", EditBusinessPage.errorCity());

		EditBusinessPage.inputCity(City + update);

//		Update State
		Thread.sleep(2000);
		State = EditBusinessPage.getState();
//		Validation check
		EditBusinessPage.inputState("");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field State/Province/County must not be empty", EditBusinessPage.errorState());

		EditBusinessPage.inputState(" ");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field State/Province/County must not be empty", EditBusinessPage.errorState());

		EditBusinessPage.inputState("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 1");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field State/Province/County must be at least 1 characters long, but not more than 50 characters", EditBusinessPage.errorState());

		EditBusinessPage.inputState(State + update);

//		Update ZIP Code
		Thread.sleep(2000);
		PostalCode = EditBusinessPage.getZipCode();
//		Validation check
		EditBusinessPage.inputPostalCode("");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field ZIP/Postal Code must not be empty", EditBusinessPage.errorZIPCode());

		EditBusinessPage.inputPostalCode("");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field ZIP/Postal Code must not be empty", EditBusinessPage.errorZIPCode());

		EditBusinessPage.inputPostalCode("!@#$%^&*()");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field ZIP/Postal Code should contain only letters, numbers, space and hyphen (-), and must begin with a letter or number", EditBusinessPage.errorZIPCode());

		EditBusinessPage.inputPostalCode("1");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field ZIP/Postal Code must be at least 2 characters long, but not more than 50 characters", EditBusinessPage.errorZIPCode());

		EditBusinessPage.inputPostalCode("123456789012345678901234567890123456789012345678901");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field ZIP/Postal Code must be at least 2 characters long, but not more than 50 characters", EditBusinessPage.errorZIPCode());

		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
		Thread.sleep(2000);
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
		AssertJUnit.assertEquals("Field Contact Number must not be empty", EditBusinessPage.errorPhoneNumber());

		EditBusinessPage.inputPhoneNumber(" ");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Contact Number must not be empty", EditBusinessPage.errorPhoneNumber());

		EditBusinessPage.inputPhoneNumber("strng!@#$%^&*()");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Contact Number should contain only numbers", EditBusinessPage.errorPhoneNumber());

		EditBusinessPage.inputPhoneNumber("24234 34534543");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Contact Number should contain only numbers", EditBusinessPage.errorPhoneNumber());

		EditBusinessPage.inputPhoneNumber("242");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Contact Number must be at least 4 characters long, but not more than 20 characters", EditBusinessPage.errorPhoneNumber());

		EditBusinessPage.inputPhoneNumber("123456789012345678901");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Contact Number must be at least 4 characters long, but not more than 20 characters", EditBusinessPage.errorPhoneNumber());

		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(2000);
		Mail = EditBusinessPage.getEmail();
//		Validation check
		EditBusinessPage.inputMail("");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Email must not be empty", EditBusinessPage.errorMail());

		EditBusinessPage.inputMail(" ");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Email must not be empty", EditBusinessPage.errorMail());

		EditBusinessPage.inputMail("strng!@#$%^&*()");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Email should be a valid email", EditBusinessPage.errorMail());

		EditBusinessPage.inputMail("czxvbnmqwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890@mail.com");
		EditBusinessPage.clickFldIndCode();
		AssertJUnit.assertEquals("Field Email must be at least 1 characters long, but not more than 255 characters", EditBusinessPage.errorMail());

		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

		EditBusinessPage.clickFldIndCode();
		EditBusinessPage.clickBtnCancel();

		EditBusinessPage.clickCancelMsgYes();

//		Verify No changes
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify No changes saved
		AssertJUnit.assertEquals(BusName, MerchantDetailsPage.getBusName());
		AssertJUnit.assertEquals(BusIndCode, MerchantDetailsPage.getIndCode());
		AssertJUnit.assertEquals(BusMID, MerchantDetailsPage.getMID());
		AssertJUnit.assertEquals(BusCountry, MerchantDetailsPage.getCountry());
		AssertJUnit.assertEquals(BusAddress, MerchantDetailsPage.getAddress());
		AssertJUnit.assertEquals(BusContNumber, MerchantDetailsPage.getContactNumber());
		AssertJUnit.assertEquals(BusEmail, MerchantDetailsPage.getBusEmail());

		MerchantDetailsPage.clickLnkEditBus();

//		Edit Business page again
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(2000);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);


//		Update Country
		Thread.sleep(2000);
		Country = EditBusinessPage.getCountry();
		EditBusinessPage.updateCountry("AW");

//		Update Street
		Thread.sleep(2000);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(2000);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update ZIP Code
		Thread.sleep(2000);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
		Thread.sleep(2000);
		PhoneCode = EditBusinessPage.getPhoneCode();
		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(2000);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(2000);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify changes saved
		AssertJUnit.assertEquals(false, BusName.contains(MerchantDetailsPage.getBusName()));
		AssertJUnit.assertEquals(false, BusIndCode.contains(MerchantDetailsPage.getIndCode()));
		AssertJUnit.assertEquals(false, BusAddress.contains(MerchantDetailsPage.getAddress()));
		AssertJUnit.assertEquals(false, BusContNumber.contains(MerchantDetailsPage.getContactNumber()));
		AssertJUnit.assertEquals(false, BusEmail.contains(MerchantDetailsPage.getBusEmail()));

		AssertJUnit.assertEquals(true, MerchantDetailsPage.getBusName().contains(update));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getIndCode().contains(updateNum));
		AssertJUnit.assertEquals(true, BusMID.contains(MerchantDetailsPage.getMID()));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getAddress().contains(update));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getContactNumber().contains(updateNum));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getBusEmail().contains(update.replace(" ","")));

		MerchantDetailsPage.clickLnkEditBus();
//		Remove changes
//		Edit Business page again
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		EditBusinessPage.inputName(Name);

//		Update Industry Code
		Thread.sleep(2000);
		EditBusinessPage.inputIndCode(IndCode);


//		Update Country
		Thread.sleep(2000);
		EditBusinessPage.updateCountry(Country);

//		Update Street
		Thread.sleep(2000);
		EditBusinessPage.inputStreet(Street);

//		Update City
		Thread.sleep(2000);
		EditBusinessPage.inputCity(City);

//		Update ZIP Code
		Thread.sleep(2000);
		EditBusinessPage.inputPostalCode(PostalCode);

//		Update Phone Code
		Thread.sleep(2000);
		EditBusinessPage.inputPhoneCode(PhoneCode);

//		Update Phone Number
		Thread.sleep(2000);
		EditBusinessPage.inputPhoneNumber(PhoneNumber);

//		Update Email
		Thread.sleep(2000);
		EditBusinessPage.inputMail(Mail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
	}

	@Test(enabled = false, priority=24, testName = "Merchant Manager Update Pending Merchant - Business Info", groups = { "Sanity" }, alwaysRun = true)

	public void MerchantManagerUpdatePendingMerchant_BusInfoUI() throws Exception {

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

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(UserMerchManEmail);
		LoginEOPortal.loginInputPassword(UserMerchManPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickMerchantsMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
		AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

		int a = MerchantsPage.pendingMerchantRow();
		System.out.println(a);
		MerchantsPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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

//		Edit Business page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(2000);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);

//		Verify MID field disabled
		Thread.sleep(2000);
		AssertJUnit.assertEquals(false, EditBusinessPage.fldMIDEnabled());

//		Update Country
		Thread.sleep(2000);
		Country = EditBusinessPage.getCountry();

		EditBusinessPage.updateCountry("AW");
		EditBusinessPage.updateCountry("US");

//		Update Street
		Thread.sleep(2000);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(2000);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update State
		Thread.sleep(2000);
		State = EditBusinessPage.getState();
		EditBusinessPage.inputState(State + update);

//		Update ZIP Code
		Thread.sleep(2000);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
		Thread.sleep(2000);
		PhoneCode = EditBusinessPage.getPhoneCode();
		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(2000);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(2000);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

		EditBusinessPage.clickBtnCancel();

		EditBusinessPage.clickCancelMsgYes();

//		Verify No changes
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify No changes saved
		AssertJUnit.assertEquals(BusName, MerchantDetailsPage.getBusName());
		AssertJUnit.assertEquals(BusIndCode, MerchantDetailsPage.getIndCode());
		AssertJUnit.assertEquals(BusMID, MerchantDetailsPage.getMID());
		AssertJUnit.assertEquals(BusCountry, MerchantDetailsPage.getCountry());
		AssertJUnit.assertEquals(BusAddress, MerchantDetailsPage.getAddress());
		AssertJUnit.assertEquals(BusContNumber, MerchantDetailsPage.getContactNumber());
		AssertJUnit.assertEquals(BusEmail, MerchantDetailsPage.getBusEmail());

		MerchantDetailsPage.clickLnkEditBus();

//		Edit Business page again
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(2000);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);


//		Update Country
		Thread.sleep(2000);
		Country = EditBusinessPage.getCountry();
		EditBusinessPage.updateCountry("AW");

//		Update Street
		Thread.sleep(2000);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(2000);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update ZIP Code
		Thread.sleep(2000);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
		Thread.sleep(2000);
		PhoneCode = EditBusinessPage.getPhoneCode();
		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(2000);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(2000);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify changes saved
		AssertJUnit.assertEquals(false, BusName.contains(MerchantDetailsPage.getBusName()));
		AssertJUnit.assertEquals(false, BusIndCode.contains(MerchantDetailsPage.getIndCode()));
		AssertJUnit.assertEquals(false, BusAddress.contains(MerchantDetailsPage.getAddress()));
		AssertJUnit.assertEquals(false, BusContNumber.contains(MerchantDetailsPage.getContactNumber()));
		AssertJUnit.assertEquals(false, BusEmail.contains(MerchantDetailsPage.getBusEmail()));

		AssertJUnit.assertEquals(true, MerchantDetailsPage.getBusName().contains(update));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getIndCode().contains(updateNum));
		AssertJUnit.assertEquals(true, BusMID.contains(MerchantDetailsPage.getMID()));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getAddress().contains(update));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getContactNumber().contains(updateNum));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getBusEmail().contains(update.replace(" ","")));

		MerchantDetailsPage.clickLnkEditBus();
//		Remove changes
//		Edit Business page again
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		EditBusinessPage.inputName(Name);

//		Update Industry Code
		Thread.sleep(2000);
		EditBusinessPage.inputIndCode(IndCode);


//		Update Country
		Thread.sleep(2000);
		EditBusinessPage.updateCountry(Country);

//		Update Street
		Thread.sleep(2000);
		EditBusinessPage.inputStreet(Street);

//		Update City
		Thread.sleep(2000);
		EditBusinessPage.inputCity(City);

//		Update ZIP Code
		Thread.sleep(2000);
		EditBusinessPage.inputPostalCode(PostalCode);

//		Update Phone Code
		Thread.sleep(2000);
		EditBusinessPage.inputPhoneCode(PhoneCode);

//		Update Phone Number
		Thread.sleep(2000);
		EditBusinessPage.inputPhoneNumber(PhoneNumber);

//		Update Email
		Thread.sleep(2000);
		EditBusinessPage.inputMail(Mail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
	}
	@Test(enabled = false, priority=25, testName = "EOAdmin Update Disabled Merchant - Business Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateDisabledMerchant_BusInfoUI() throws Exception {

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

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickMerchantsMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
		AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

		int a = MerchantsPage.disableMerchantRow();
		System.out.println(a);
		MerchantsPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
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

//		Edit Business page
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(2000);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);

//		Verify MID field disabled
		Thread.sleep(2000);
		AssertJUnit.assertEquals(false, EditBusinessPage.fldMIDEnabled());

//		Update Country
		Thread.sleep(2000);
		Country = EditBusinessPage.getCountry();

		EditBusinessPage.updateCountry("AW");
		EditBusinessPage.updateCountry("US");

//		Update Street
		Thread.sleep(2000);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(2000);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update State
		Thread.sleep(2000);
		State = EditBusinessPage.getState();
		EditBusinessPage.inputState(State + update);

//		Update ZIP Code
		Thread.sleep(2000);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
		Thread.sleep(2000);
		PhoneCode = EditBusinessPage.getPhoneCode();
		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(2000);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(2000);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

		EditBusinessPage.clickBtnCancel();

		EditBusinessPage.clickCancelMsgYes();

//		Verify No changes
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify No changes saved
		AssertJUnit.assertEquals(BusName, MerchantDetailsPage.getBusName());
		AssertJUnit.assertEquals(BusIndCode, MerchantDetailsPage.getIndCode());
		AssertJUnit.assertEquals(BusMID, MerchantDetailsPage.getMID());
		AssertJUnit.assertEquals(BusCountry, MerchantDetailsPage.getCountry());
		AssertJUnit.assertEquals(BusAddress, MerchantDetailsPage.getAddress());
		AssertJUnit.assertEquals(BusContNumber, MerchantDetailsPage.getContactNumber());
		AssertJUnit.assertEquals(BusEmail, MerchantDetailsPage.getBusEmail());

		MerchantDetailsPage.clickLnkEditBus();

//		Edit Business page again
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		Name = EditBusinessPage.getName();
		EditBusinessPage.inputName(Name + update);

//		Update Industry Code
		Thread.sleep(2000);
		IndCode = EditBusinessPage.getIndCode();
		EditBusinessPage.inputIndCode(IndCode + updateNum);


//		Update Country
		Thread.sleep(2000);
		Country = EditBusinessPage.getCountry();
		EditBusinessPage.updateCountry("AW");

//		Update Street
		Thread.sleep(2000);
		Street = EditBusinessPage.getStreet();
		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(2000);
		City = EditBusinessPage.getCity();
		EditBusinessPage.inputCity(City + update);

//		Update ZIP Code
		Thread.sleep(2000);
		PostalCode = EditBusinessPage.getZipCode();
		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
		Thread.sleep(2000);
		PhoneCode = EditBusinessPage.getPhoneCode();
		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(2000);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Email
		Thread.sleep(2000);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify changes saved
		AssertJUnit.assertEquals(false, BusName.contains(MerchantDetailsPage.getBusName()));
		AssertJUnit.assertEquals(false, BusIndCode.contains(MerchantDetailsPage.getIndCode()));
		AssertJUnit.assertEquals(false, BusAddress.contains(MerchantDetailsPage.getAddress()));
		AssertJUnit.assertEquals(false, BusContNumber.contains(MerchantDetailsPage.getContactNumber()));
		AssertJUnit.assertEquals(false, BusEmail.contains(MerchantDetailsPage.getBusEmail()));

		AssertJUnit.assertEquals(true, MerchantDetailsPage.getBusName().contains(update));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getIndCode().contains(updateNum));
		AssertJUnit.assertEquals(true, BusMID.contains(MerchantDetailsPage.getMID()));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getAddress().contains(update));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getContactNumber().contains(updateNum));
		AssertJUnit.assertEquals(true, MerchantDetailsPage.getBusEmail().contains(update.replace(" ","")));

		MerchantDetailsPage.clickLnkEditBus();
//		Remove changes
//		Edit Business page again
		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		EditBusinessPage.inputName(Name);

//		Update Industry Code
		Thread.sleep(2000);
		EditBusinessPage.inputIndCode(IndCode);


//		Update Country
		Thread.sleep(2000);
		EditBusinessPage.updateCountry(Country);

//		Update Street
		Thread.sleep(2000);
		EditBusinessPage.inputStreet(Street);

//		Update City
		Thread.sleep(2000);
		EditBusinessPage.inputCity(City);

//		Update ZIP Code
		Thread.sleep(2000);
		EditBusinessPage.inputPostalCode(PostalCode);

//		Update Phone Code
		Thread.sleep(2000);
		EditBusinessPage.inputPhoneCode(PhoneCode);

//		Update Phone Number
		Thread.sleep(2000);
		EditBusinessPage.inputPhoneNumber(PhoneNumber);

//		Update Email
		Thread.sleep(2000);
		EditBusinessPage.inputMail(Mail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
	}
	@Test(enabled = false, priority=26, testName = "Dev and App Manager CANT Update Enabled Merchant - Business Info", groups = { "Sanity" }, alwaysRun = true)

	public void DevAppManagerCANTUpdateEnabledMerchant_BusInfoUI() throws Exception {

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
		LoginEOPortal.loginInputEmail(UserDevAppEmail);
		LoginEOPortal.loginInputPassword(UserDevAppPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
		HomePage.clickMerchantsMenu();

		Thread.sleep(5000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantsPage MerchantsPage = (MerchantsPage) PageFactory.getPage("MerchantsPage");
		AssertJUnit.assertEquals("Merchants", MerchantsPage.titleMerchants());

		int a = MerchantsPage.activeMerchantRow();
		System.out.println(a);
		MerchantsPage.clickOnRow(a);

		Thread.sleep(3000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MerchantDetailsPage MerchantDetailsPage = (MerchantDetailsPage) PageFactory.getPage("MerchantDetailsPage");

//		Verify Dev App Manager can't edit Business Information
		AssertJUnit.assertEquals(false, MerchantDetailsPage.elementBusinessEditClickable());

	}

}
