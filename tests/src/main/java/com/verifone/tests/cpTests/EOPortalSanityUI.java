package com.verifone.tests.cpTests;

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
import com.verifone.pages.eoPages.AddUserPage;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.pages.eoPages.LoginEOPortal;
import com.verifone.pages.eoPages.UsersPage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;

public class EOPortalSanityUI extends BaseTest {

final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\eoAddUser.xls";
private static Integer getRowNumFromFile = 0;
private static String UserEmail = "UserEOAdmin@getnada.com";
private static String UserEmail2 = "";

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

@Test(enabled = true, testName = "EOAdmin Users", dataProvider = "eoAddUser", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUsersUI(String eoRole, String eoMail, String eoPassword, String userName, String userLast, String userMail) throws Exception {
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
		AssertJUnit.assertEquals("Field Email must not be empty", AddUserPage.errorEmail());
		
		AddUserPage.inputEmail(userMail);
		
		AddUserPage.clickDropDn();
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

@Test(enabled = true, testName = "EOAdmin Users", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminAddUserUI() throws Exception {
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

@Test(enabled = false, testName = "EOAdmin Users View", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminViewUI() throws Exception {
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

@Test(enabled = false, testName = "EOAdmin Add User View", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminAddUserViewUI() throws Exception {
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

@Test(enabled = false, testName = "EOAdmin Add User Exist Email", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminAddUserExistEmailUI() throws Exception {
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
	AddUserPage.inputFirstName("UserEOAdmin two");
	AddUserPage.inputLastName("UserLastEOAdmin two");
	AddUserPage.inputEmail(UserEmail);
	AddUserPage.clickDropDn();
	AddUserPage.clickDropDnItem("EO Admin");
	AddUserPage.clickSubmitBtn();
	Thread.sleep(3000);
	AssertJUnit.assertEquals("User, " + UserEmail + ", is already associated with an existing organization and cannot be added.", AddUserPage.errorEmail());
	AssertJUnit.assertEquals("User, " + UserEmail + ", is already associated with an existing organization and cannot be added.", AddUserPage.msgErrorText());
	
	UserEmail2 = "User" + LocalDateTime.now() + "EOAdmin@getnada.com";
	UserEmail2 = UserEmail2.replace("-", "");
	UserEmail2 = UserEmail2.replace(":", "");
	AddUserPage.inputEmail(UserEmail2);
	AddUserPage.clickDropDn();
	AddUserPage.clickDropDnItem("EO Admin");
	AddUserPage.clickSubmitBtn();
	
	Thread.sleep(10000);
	availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
	BasePage.driver.switchTo().window(availableWindows.get(0));
	UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	
	boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserEmail2);
	AssertJUnit.assertEquals(true, fl);
	}
}
