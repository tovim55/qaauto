package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.ChangePasswordPage;
import com.verifone.pages.eoPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
//--------------------------------------------------------------------------

/**
 * Portal: EstateManager
 * This test set verify EO Admin can change password.
 * EO Admin NOT allowed to LogIn with old password.
 * EO Admin allowed to LogIn with new password.
 * Change Password page GUI validation.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EOPortalChangePassword_RegUI extends BaseTest {

	private static String UserDevAppEmail = ""; //"User20181010T190208.543DevAppMan@getnada.com";
	private static String UserMerchManEmail = ""; //"User20181010T190123.176MerchMan@getnada.com";
	private static String UserDevAppPwd = "Veri1234";
	private static String UserMerchManPwd = "Veri1234";
	private static String EnvPort = ".estatemanager.verifonecp.com";
	private static String Env = "";
	private static String EOAdminMail = "vfieous@getnada.com";
	private static String EOAdminPwd = "Veri1234";
	private static Integer TimeOut = 2000;

	@Test(enabled = true, priority=33, testName = "EOAdmin Change Password", groups = { "Sanity" }, alwaysRun = true)

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

		User EOAdmin = EntitiesFactory.getEntity("EOAdmin");
		EOAdminMail = EOAdmin.getUserName();
		EOAdminPwd = EOAdmin.getPassword();

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickMyProfileMenu();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MyProfilePage MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		AssertJUnit.assertEquals("My Profile", MyProfilePage.getTitle());
//		Click change Password
		MyProfilePage.clickLnkChangePassword();

		testLog.info("------------------------------------------------- Change Password page -------------------------------------------------");

		Thread.sleep(TimeOut);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		ChangePasswordPage ChangePasswordPage = (ChangePasswordPage) PageFactory.getPage("ChangePasswordPage");
		AssertJUnit.assertEquals("Change Password", ChangePasswordPage.getTitle());

//		Validation
		if (!Assertions.compareValue("Current Password", ChangePasswordPage.currentPasswordLabel(), "Current Password hint: ", testLog, driver)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue("New Password", ChangePasswordPage.newPasswordLabel(), "New Password hint: ", testLog, driver)){
			TestPassFlag = false;
		}
//		Current Password Validation
		ChangePasswordPage.InputCurrentPassword("");
		ChangePasswordPage.clickNewPasswordFld();
		if (!Assertions.compareBoolean(true, ChangePasswordPage.errorCurrentPasswordExists(), "Current password validation error displayed: ", testLog, driver)){
			TestPassFlag = false;
		} else if (!Assertions.compareValue("Should not be empty", ChangePasswordPage.errorEmptyPassword(), "Current password validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		ChangePasswordPage.InputCurrentPassword(" ");
		ChangePasswordPage.clickNewPasswordFld();
		if (!Assertions.compareBoolean(true, ChangePasswordPage.errorCurrentPasswordExists(), "Current password validation error displayed: ", testLog, driver)){
			TestPassFlag = false;
		} else if (!Assertions.compareValue("Should not be empty", ChangePasswordPage.errorEmptyPassword(), "Current password validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		ChangePasswordPage.InputCurrentPassword("!@#$%^&*()");
		ChangePasswordPage.clickNewPasswordFld();
		if (!Assertions.compareBoolean(true, ChangePasswordPage.errorCurrentPasswordExists(), "Current password validation error displayed: ", testLog, driver)){
			TestPassFlag = false;
		} else if (!Assertions.compareValue("Invalid string", ChangePasswordPage.errorEmptyPassword(), "Current password validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		ChangePasswordPage.InputCurrentPassword("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		ChangePasswordPage.clickNewPasswordFld();
		if (!Assertions.compareBoolean(true, ChangePasswordPage.errorCurrentPasswordExists(), "Current password validation error displayed: ", testLog, driver)){
			TestPassFlag = false;
		}  else	if (!Assertions.compareValue("Long string", ChangePasswordPage.errorEmptyPassword(), "Current password validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		ChangePasswordPage.InputCurrentPassword(EOAdminPwd + "1");

//		New Password Validation
		ChangePasswordPage.InputNewPassword("");
		ChangePasswordPage.clickCurrentPasswordFld();
		if (!Assertions.compareBoolean(true, ChangePasswordPage.errorNewPasswordExists(), "New password validation error displayed: ", testLog, driver)){
			TestPassFlag = false;
		} else if (!Assertions.compareValue("Should not be empty", ChangePasswordPage.errorEmptyNewPassword(), "New password validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		ChangePasswordPage.InputNewPassword(" ");
		ChangePasswordPage.clickCurrentPasswordFld();
		if (!Assertions.compareBoolean(true, ChangePasswordPage.errorNewPasswordExists(), "New password validation error displayed: ", testLog, driver)){
			TestPassFlag = false;
		} else if (!Assertions.compareValue("Should not be empty", ChangePasswordPage.errorEmptyNewPassword(), "New password validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		ChangePasswordPage.InputNewPassword("!@#$%^&*()");
		ChangePasswordPage.clickCurrentPasswordFld();
		if (!Assertions.compareBoolean(true, ChangePasswordPage.errorNewPasswordExists(), "New password validation error displayed: ", testLog, driver)){
			TestPassFlag = false;
		} else if (!Assertions.compareValue("Invalid string", ChangePasswordPage.errorEmptyNewPassword(), "New password validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		ChangePasswordPage.InputNewPassword("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		ChangePasswordPage.clickCurrentPasswordFld();
		if (!Assertions.compareBoolean(true, ChangePasswordPage.errorNewPasswordExists(), "New password validation error displayed: ", testLog, driver)){
			TestPassFlag = false;
		}  else	if (!Assertions.compareValue("Long string", ChangePasswordPage.errorEmptyNewPassword(), "New password validation error: ", testLog, driver)){
			TestPassFlag = false;
		}

		ChangePasswordPage.InputNewPassword("Veri4321");

		ChangePasswordPage.clickBtnSubmit();
		if (!Assertions.compareValue("The information you've entered does not match the information we have on file.", ChangePasswordPage.errorMatch(), "New password match error: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("-----------------------------Input current password and new password and Cancel. Verify return to My Profile -------------------------------------------------");

		ChangePasswordPage.InputCurrentPassword(EOAdminPwd);
		ChangePasswordPage.InputNewPassword("Veri4321");
		ChangePasswordPage.clickBtnCancel();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");
		Thread.sleep(TimeOut );
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		AssertJUnit.assertEquals("My Profile", MyProfilePage.getTitle());
//		Click change Password
		Thread.sleep(TimeOut);
		MyProfilePage.clickLnkChangePassword();

		testLog.info("------------------------------------------------- Change Password again -------------------------------------------------");

		Thread.sleep(TimeOut);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		ChangePasswordPage = (ChangePasswordPage) PageFactory.getPage("ChangePasswordPage");
		AssertJUnit.assertEquals("Change Password", ChangePasswordPage.getTitle());

		ChangePasswordPage.InputCurrentPassword(EOAdminPwd);
		ChangePasswordPage.InputNewPassword("Veri4321");
		ChangePasswordPage.clickBtnSubmit();

		testLog.info("------------------------------------------------- Verify Notification -------------------------------------------------");

		if (!Assertions.compareBoolean(true, ChangePasswordPage.notifyText().contains("Password Changed"), "Password changed notification: ", testLog, driver)){
			TestPassFlag = false;
		}
		ChangePasswordPage.clickOkBtn();

		testLog.info("------------------------------------------------- Login page -------------------------------------------------");

		LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " " + EOAdminPwd + "-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword(EOAdminPwd);
		LoginEOPortal.clickLoginBtn();
		if (!Assertions.compareBoolean(true, LoginEOPortal.lerrorMatch().contains("The information you've entered does not match the information we have on file"), "Wrong Password error: ", testLog, driver)){
			TestPassFlag = false;
		}

		testLog.info("-------------------------------------------------Login as: " + EOAdminMail + " Veri4321-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EOAdminMail);
		LoginEOPortal.loginInputPassword("Veri4321");
		LoginEOPortal.clickLoginBtn();

		testLog.info("-------------------------------------------------Return Password to original-------------------------------------------------");

		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		HomePage = (HomePage) PageFactory.getPage("CBAHomePage");

		testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickMyProfileMenu();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");

//		Click change Password
		MyProfilePage.clickLnkChangePassword();

		testLog.info("------------------------------------------------- Change Password page -------------------------------------------------");

		Thread.sleep(TimeOut);
		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		ChangePasswordPage = (ChangePasswordPage) PageFactory.getPage("ChangePasswordPage");


		ChangePasswordPage.InputCurrentPassword("Veri4321");

		ChangePasswordPage.InputNewPassword(EOAdminPwd);

		ChangePasswordPage.clickBtnSubmit();

		testLog.info("------------------------------------------------- Verify Notification -------------------------------------------------");

		if (!Assertions.compareBoolean(true, ChangePasswordPage.notifyText().contains("Password Changed"), "Password changed notification: ", testLog, driver)){
			TestPassFlag = false;
		}
		ChangePasswordPage.clickOkBtn();

		Assert.assertTrue(TestPassFlag);
	}
//
}
