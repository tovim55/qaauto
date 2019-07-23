package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.eoPages.*;
import com.verifone.pages.mpPages.CBAHomePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import com.verifone.utils.DataDrivenUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
//--------------------------------------------------------------------------

/**
 * Portal: EstateManager
 * This test set verify EO Admin, Merchant Manager, Device and Application Manager can View and Add Users.
 * User with exists email validation
 * Users and Add User pages GUI validation
 *
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EOPortalAddViewUsers_RegUI extends BaseTest {

    //	final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\eoAddUser.xls";
    final String xlsxFile = java.nio.file.Paths.get(
            System.getProperty("user.dir"),
            "src", "test", "resources").toString() + File.separator + "eoAddUser.xls";
    private static Integer rowNumber = 0;
    private static Integer getRowNumFromFile = 0;
    private static String UserEmail = "";
    private static String UserEOAdminEmail = "";
    private static String UserDevAppEmail = "";
    private static String UserMerchManEmail = "";
    private static String EnvPort = ".estatemanager.verifonecp.com";
    private static String Env = "";
    private static String EOAdminMail = "vfieous@getnada.com";   //Default value
    private static String EOAdminPwd = "Veri1234";   //Default value
    private static Integer TimeOut = 2000;
    private static String env = "";

    @BeforeTest
    public void startDDTest() throws Exception {
// 		Get number of Rows from Data driven
        env = envConfig.getEnv();
        if (env.contains("QA")) {
            getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "eoAddUser");
        }
        if (env.contains("DEV")) {
            getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "eoAddUserDev");
        }
        if (env.contains("STAGING1")) {
            getRowNumFromFile = DataDrivenUtils.getRowNumberExcelData(xlsxFile, "eoAddUserSTAGING1");
        }
        Env = "https://" + envConfig.getEnv() + EnvPort;
    }

//Data Provider

    @DataProvider(name = "eoAddUser")
    public Object[][] dataSupplierLoginData() throws Exception {
        Object[][] arrayObject = null;
        if (env.contains("QA")) {
            arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "eoAddUser");
        }
        if (env.contains("DEV")) {
            arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "eoAddUserDev");
        }
        if (env.contains("STAGING1")) {
            arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "eoAddUserSTAGING1");
        }
        return arrayObject;
    }

    @Test(enabled = true, priority = 1, testName = "EOAdmin, Merchant Man and Dev App Manager add Users and Cancel action", dataProvider = "eoAddUser", groups = {"Sanity"}, alwaysRun = true)

    public void EOAdminAddUsersCancelUI(String eoRole, String eoMail, String eoPassword, String userName, String userLast, String userMail) throws Exception {
        WebDriver driver = new HomePage().getDriver();
        Boolean TestPassFlag = true;
        rowNumber = rowNumber + 1;
        testLog.info("Data Driven line number: " + rowNumber);

        testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

        driver.navigate().to(Env);
        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

        testLog.info("-------------------------------------------------Login as: " + eoMail + " " + eoPassword + "-------------------------------------------------");
        Thread.sleep(4000);
        LoginEOPortal.loginInputEmail(eoMail);
        LoginEOPortal.loginInputPassword(eoPassword);
        LoginEOPortal.clickLoginBtn();

        testLog.info("------------------------------------------------- Open Account menu -------------------------------------------------");

        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
        HomePage.clickHeaderMenu();

        testLog.info("------------------------------------------------- Verify User menu exists -------------------------------------------------");
        boolean UserMenu = HomePage.menuUserExists();

        switch (eoRole) {
            case "eoMMan":
                if (!Assertions.compareBoolean(false, UserMenu, "Merchant Manager view User menu", testLog, driver)) {
                    TestPassFlag = false;
                }
                break;
            case "eoAppDev":
                if (!Assertions.compareBoolean(false, UserMenu, "Device and App Manager view User menu", testLog, driver)) {
                    TestPassFlag = false;
                }
                break;

            case "eoAdmin":
                if (!Assertions.compareBoolean(true, UserMenu, "EO Admin view User menu", testLog, driver)) {
                    TestPassFlag = false;
                }


                testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

                HomePage.clickUserMenu();

                Thread.sleep(TimeOut + 3000);
                availableWindows = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(availableWindows.get(0));
                UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

//		Verify title page = Users

                if (!Assertions.compareValue("Users", UsersPage.titleUsers(), "page Title: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                testLog.info("------------------------------------------------- Add New User -------------------------------------------------");

                UsersPage.clickAddUserBtn();

//		First Name Validation check

                Thread.sleep(TimeOut + 3000);
                availableWindows = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(availableWindows.get(0));
                AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");
                AddUserPage.inputFirstName("12345");
                AddUserPage.clickLastNameFld();
                if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", AddUserPage.errorFirstName(), "FirstName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }
                AddUserPage.inputFirstName("");
                AddUserPage.clickLastNameFld();
                if (!Assertions.compareValue("Field First Name must not be empty", AddUserPage.errorFirstName(), "FirstName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputFirstName(" ");
                AddUserPage.clickLastNameFld();
                if (!Assertions.compareValue("Field First Name must not be empty", AddUserPage.errorFirstName(), "FirstName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputFirstName("~!@#$%^&*()");
                AddUserPage.clickLastNameFld();
                if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", AddUserPage.errorFirstName(), "FirstName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputFirstName(".string");
                AddUserPage.clickLastNameFld();
                if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", AddUserPage.errorFirstName(), "FirstName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }
                AddUserPage.inputFirstName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
                AddUserPage.clickLastNameFld();
                if (!Assertions.compareValue("Field First Name must be at least 1 characters long, but not more than 35 characters", AddUserPage.errorFirstName(), "FirstName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputFirstName("string . - 'string'.");
                AddUserPage.clickLastNameFld();

                AddUserPage.inputFirstName(userName);
                AddUserPage.clickLastNameFld();

//		Last Name Validation check
                AddUserPage.inputLastName("12345");
                AddUserPage.clickEmailFld();
                if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", AddUserPage.errorLastName(), "LastName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputLastName("");
                AddUserPage.clickEmailFld();
                if (!Assertions.compareValue("Field Last Name must not be empty", AddUserPage.errorLastName(), "LastName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputLastName(" ");
                AddUserPage.clickEmailFld();
                if (!Assertions.compareValue("Field Last Name must not be empty", AddUserPage.errorLastName(), "LastName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputLastName("~!@#$%^&*()");
                AddUserPage.clickEmailFld();
                if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", AddUserPage.errorLastName(), "LastName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputLastName("'string'");
                AddUserPage.clickEmailFld();
                if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", AddUserPage.errorLastName(), "LastName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputLastName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
                AddUserPage.clickEmailFld();
                if (!Assertions.compareValue("Field Last Name must be at least 1 characters long, but not more than 35 characters", AddUserPage.errorLastName(), "LastName validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputLastName("string . - 'string'.");
                AddUserPage.clickEmailFld();

                AddUserPage.inputLastName(userLast);
                AddUserPage.clickEmailFld();

//		Mail Validation check
                AddUserPage.inputEmail("plainaddress");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputEmail("#@%^%#$@#$@#.com");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputEmail("@example.com");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputEmail("Joe Smith <email@example.com>");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputEmail("email.example.com");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputEmail("email@example@example.com");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

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
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputEmail("email@example");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

//		AddUserPage.inputEmail("email@-example.com");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

//		AddUserPage.inputEmail("email@example.web");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

                AddUserPage.inputEmail("email@111.222.333.44444");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputEmail("email@example..com");
                AddUserPage.clickDropDn();
                if (!Assertions.compareValue("Field Email should be a valid email", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

//		AddUserPage.inputEmail("Abc..123@example.com");
//		AddUserPage.clickDropDn();
//		Assert.assertEquals("Field Email should be a valid email", AddUserPage.errorEmail());

                AddUserPage.inputEmail("");
                AddUserPage.clickDropDn();
                Thread.sleep(TimeOut - 2000);
                if (!Assertions.compareValue("Field Email must not be empty", AddUserPage.errorEmail(), "Mail validation error: ", testLog, driver)) {
                    TestPassFlag = false;
                }

                AddUserPage.inputEmail(userMail);

//		AddUserPage.clickDropDn();

                AddUserPage.clickDropDnItem("EO Merchant Manager");

                AddUserPage.clickDropDn();

                AddUserPage.clickDropDnItem("EO Device and App Manager");

                AddUserPage.clickDropDn();

                AddUserPage.clickDropDnItem("EO Admin");

                testLog.info("------------------------------------------------- Cancel all changes -------------------------------------------------");

                AddUserPage.clickCancelBtn();

                availableWindows = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(availableWindows.get(0));
                UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

                testLog.info("------------------------------------------------- Verify user not added -------------------------------------------------");

                boolean fl = UsersPage.tblUsersText().contains(userMail);
                Assertions.compareBoolean(false, fl, "User Not added ", testLog, driver);

                break;
        }
        Assert.assertTrue(TestPassFlag);
    }


@Test(enabled = true, priority=2, testName = "EOAdmin Add EOAdmin and Submit action", groups = { "Sanity" }, alwaysRun = true)

public void EOAdminAddUserSubmitUI() throws Exception {
	WebDriver driver = new HomePage().getDriver();
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

	testLog.info("------------------------------------------------- Open Account menu -------------------------------------------------");

	ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(availableWindows.get(0));
	HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
	HomePage.clickHeaderMenu();

	testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");
	HomePage.clickUserMenu();

	Thread.sleep(TimeOut + 3000);
	availableWindows = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(availableWindows.get(0));
	UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

	testLog.info("------------------------------------------------- Add New User - EO Admin -------------------------------------------------");

	UsersPage.clickAddUserBtn();

	Thread.sleep(TimeOut + 3000);
	availableWindows = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(availableWindows.get(0));
	AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");

	AddUserPage.inputFirstName("UserEOAdmin");
	AddUserPage.inputLastName("UserLastEOAdmin");
	// Make email uniq and input
	//UserEmail = UserEmail.replace("@", (LocalDateTime.now() + "@").replace(":",""));
    UserEmail = "UserEOAdmin" + LocalDateTime.now() + "@getnada.com";
    UserEmail = UserEmail.replace(":","");
    UserEmail = UserEmail.replace("-","");
    AddUserPage.inputEmail(UserEmail);
	AddUserPage.clickDropDn();
	AddUserPage.clickDropDnItem("EO Admin");

	testLog.info("------------------------------------------------- Submit changes -------------------------------------------------");

	AddUserPage.clickSubmitBtn();
	Thread.sleep(TimeOut + 3000);
	availableWindows = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(availableWindows.get(0));
	UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
	}


    @Test(enabled = true, priority = 3, testName = "EOAdmin View Users", groups = {"Sanity"}, alwaysRun = true)

    public void EOAdminViewUsersUI() throws Exception {
        WebDriver driver = new HomePage().getDriver();
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

        testLog.info("------------------------------------------------- Open Account menu -------------------------------------------------");

        HomePage.clickHeaderMenu();

        boolean UserMenu = HomePage.menuUserExists();
        AssertJUnit.assertEquals(true, UserMenu);

        testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

        HomePage.clickUserMenu();

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

//	Verify EO Admin can view Users
//    AssertJUnit.assertEquals(true, UsersPage.tblUsersExists());
        if (!Assertions.compareBoolean(true, UsersPage.tblUsersExists(), "Users table displayed", testLog, driver)) {
            TestPassFlag = false;
        }
//    AssertJUnit.assertEquals(true, UsersPage.pgUsersExists());
        if (!Assertions.compareBoolean(true, UsersPage.pgUsersExists(), "Users page displayed", testLog, driver)) {
            TestPassFlag = false;
        }
//    AssertJUnit.assertEquals(true, UsersPage.btnAddUserExists());
        if (!Assertions.compareBoolean(true, UsersPage.btnAddUserExists(), "Add User button displayed", testLog, driver)) {
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority = 4, testName = "EOAdmin View Add User page", groups = {"Sanity"}, alwaysRun = true)

    public void EOAdminViewAddUserPageUI() throws Exception {
        WebDriver driver = new HomePage().getDriver();
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

        testLog.info("------------------------------------------------- Open Account menu -------------------------------------------------");

        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
        Thread.sleep(TimeOut);

        HomePage.clickHeaderMenu();

        testLog.info("------------------------------------------------- Navigate to Users page -------------------------------------------------");

        HomePage.clickUserMenu();

        Thread.sleep(TimeOut + 3000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

        AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
        testLog.info("------------------------------------------------- Navigate to Add User page -------------------------------------------------");

        UsersPage.clickAddUserBtn();

        Thread.sleep(TimeOut + 3000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");

        //	Verify EO Admin can view Add User page
        //	Verify all gui elements present and enabled

        if (!Assertions.compareValue("Add User", AddUserPage.titleText(), "Verify page title", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("Add new user to your organization", AddUserPage.titleDescText(), "Verify page description text", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("User Information", AddUserPage.titlePanelText(), "Verify User Information section present", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("First Name", AddUserPage.hintFName(), "Verify First Name hint", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("Last Name", AddUserPage.hintLName(), "Verify Last Name hint", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("Email", AddUserPage.hintEmail(), "Verify Email hint", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("This will be used for the business member to log into the system. Once submitted this user will get an email to set up their account.", AddUserPage.helpEmail(), "Verify Email help", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("Role", AddUserPage.titleRole(), "Verify Role section", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("Cancel", AddUserPage.btnCancelLabel(), "Verify Cancel button present", testLog, driver)) {
            TestPassFlag = false;
        }
        if (!Assertions.compareValue("Submit", AddUserPage.btnSubmitLabel(), "Verify Submit button present", testLog, driver)) {
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority = 5, testName = "EOAdmin Add EOAdmin Exist Email", groups = {"Sanity"}, alwaysRun = true)

    public void EOAdminAddUserExistEmailUI() throws Exception {
        WebDriver driver = new HomePage().getDriver();
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

        Thread.sleep(TimeOut + 3000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
        AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
        UserEmail = UsersPage.EOAdminEmail();

        testLog.info("------------------------------------------------- Navigate to Add User page -------------------------------------------------");

        UsersPage.clickAddUserBtn();

        Thread.sleep(TimeOut + 3000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");

        testLog.info("------------------------------------------------- Fill new user data and exists email -------------------------------------------------");

        AddUserPage.inputFirstName("UserEOAdmin two");
        AddUserPage.inputLastName("UserLastEOAdmin two");
        if (UserEmail != "") {
            AddUserPage.inputEmail(UserEmail);
            AddUserPage.clickDropDn();
            AddUserPage.clickDropDnItem("EO Admin");
            AddUserPage.clickSubmitBtn();
            Thread.sleep(TimeOut + 1000);
            if (!Assertions.compareValue("User, " + UserEmail + ", is already associated with an existing organization and cannot be added.", AddUserPage.errorEmail(), "Verify error", testLog, driver)) {
                TestPassFlag = false;
            }
            //	AssertJUnit.assertEquals("User, " + UserEmail + ", is already associated with an existing organization and cannot be added.", AddUserPage.errorEmail());
            if (!Assertions.compareValue("User, " + UserEmail + ", is already associated with an existing organization and cannot be added.", AddUserPage.msgErrorText(), "Verify message error", testLog, driver)) {
                TestPassFlag = false;
            }
            //	AssertJUnit.assertEquals("User, " + UserEmail + ", is already associated with an existing organization and cannot be added.", AddUserPage.msgErrorText());
        }

//	Input not exists email
        UserEOAdminEmail = "User" + LocalDateTime.now() + "EOAdmin@getnada.com";
        UserEOAdminEmail = UserEOAdminEmail.replace("-", "");
        UserEOAdminEmail = UserEOAdminEmail.replace(":", "");
        AddUserPage.inputEmail(UserEOAdminEmail);
        AddUserPage.clickTitleFld();
        AddUserPage.clickDropDn();
        AddUserPage.clickDropDnItem("EO Admin");
        AddUserPage.clickSubmitBtn();

        Thread.sleep(TimeOut - 1000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

//	boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserEOAdminEmail);
        if (!Assertions.compareBoolean(true, UsersPage.tblUsersFirstLineEmailText().contains(UserEOAdminEmail), "Verify User added", testLog, driver)) {
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority = 6, testName = "EOAdmin Add Merchant Manager", groups = {"Sanity"}, alwaysRun = true)

    public void EOAdminAddMerchantManagerUI() throws Exception {
        WebDriver driver = new HomePage().getDriver();
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

        Thread.sleep(TimeOut + 3000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
        AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

        testLog.info("------------------------------------------------- Navigate to Add User page -------------------------------------------------");

        UsersPage.clickAddUserBtn();

        Thread.sleep(TimeOut + 3000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        testLog.info("------------------------------------------------- Fill valid user data -------------------------------------------------");

        AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");
        AddUserPage.inputFirstName("UserMerchantManager");
        AddUserPage.inputLastName("UserLastMerchantManager");

        UserMerchManEmail = "User" + LocalDateTime.now() + "MerchMan@getnada.com";
        UserMerchManEmail = UserMerchManEmail.replace("-", "");
        UserMerchManEmail = UserMerchManEmail.replace(":", "");
        AddUserPage.inputEmail(UserMerchManEmail);
        AddUserPage.clickDropDn();
        AddUserPage.clickDropDnItem("EO Merchant Manager");
        AddUserPage.clickSubmitBtn();

        Thread.sleep(TimeOut + 8000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

        if (!Assertions.compareBoolean(true, UsersPage.tblUsersFirstLineEmailText().contains(UserMerchManEmail), "Verify User added", testLog, driver)) {
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority = 7, testName = "EOAdmin Add Dev App Manager", groups = {"Sanity"}, alwaysRun = true)

    public void EOAdminAddDevAppManagerUI() throws Exception {
        WebDriver driver = new HomePage().getDriver();
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

        Thread.sleep(TimeOut + 3000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
        AssertJUnit.assertEquals("Users", UsersPage.titleUsers());

        testLog.info("------------------------------------------------- Navigate to Add User page -------------------------------------------------");

        UsersPage.clickAddUserBtn();
        Thread.sleep(TimeOut + 3000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");

        testLog.info("------------------------------------------------- Fill valid user data -------------------------------------------------");

        AddUserPage.inputFirstName("UserDevAppManager");
        AddUserPage.inputLastName("UserLastDevAppManager");

        UserDevAppEmail = "User" + LocalDateTime.now() + "DevAppMan@getnada.com";
        UserDevAppEmail = UserDevAppEmail.replace("-", "");
        UserDevAppEmail = UserDevAppEmail.replace(":", "");
        AddUserPage.inputEmail(UserDevAppEmail);
        AddUserPage.clickDropDn();
        AddUserPage.clickDropDnItem("EO Device and App Manager");
        AddUserPage.clickSubmitBtn();

        Thread.sleep(TimeOut + 8000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        UsersPage = (UsersPage) PageFactory.getPage("UsersPage");

        if (!Assertions.compareBoolean(true, UsersPage.tblUsersFirstLineEmailText().contains(UserDevAppEmail), "Verify User added", testLog, driver)) {
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
    }

}
