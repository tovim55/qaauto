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
 * This test set verify EO Admin, Merchant Manager, Device and Application Manager can View and Update My Profile - User Info.
 * EO Admin can view and Update My Profile - Business Information.
 * Merchant Manager, Device and Application Manager not allowed to view and Update My Profile - Business Information.
 * My Profile - User Info GUI validation.
 * My Profile - Business Information GUI validation.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EOPortalUpdateMyProfile_RegUI extends BaseTest {

	private static String EnvPort = ".estatemanager.verifonecp.com";
	private static String Env = "";
	private static String EOAdminMail = ""; //"vfieous@getnada.com";
	private static String EOAdminPwd = ""; //"Veri1234";
	private static String EOMerchManMail = "";
	private static String EOMerchManPwd = "";
	private static String EODevAppManMail = "";
	private static String EODevAppManPwd = "";
	private static Integer TimeOut = 2000;

	@Test(enabled = true, priority=27, testName = "EOAdmin Update My Profile - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateMyProfile_UserInfoUI() throws Exception {

		String firstName = "";
		String lastName = "";
		String Name = "";
		String update = " updated";
		int updateLength = update.length();

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

		testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickMyProfileMenu();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		AssertJUnit.assertEquals("My Profile", MyProfilePage.getTitle());
		Name = MyProfilePage.getName();
		int NameLength = Name.length();
		MyProfilePage.clickLnkEditUserInf();

		testLog.info("------------------------------------------- Edit Profile page. Update User data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name
		firstName = EditProfilePage.getfirstName();

//		Validation check
		EditProfilePage.updateFirstName("12345");
		EditProfilePage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditProfilePage.errorFirstName(), "First Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputFirstName("");
		EditProfilePage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name must not be empty", EditProfilePage.errorFirstName(), "First Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputFirstName(" ");
		EditProfilePage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name must not be empty", EditProfilePage.errorFirstName(), "First Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputFirstName("~!@#$%^&*()");
		EditProfilePage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditProfilePage.errorFirstName(), "First Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputFirstName(".string");
		EditProfilePage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name should contain only letters, space, period, hyphen (-) and apostrophe ('). First Name should begin with a letter.", EditProfilePage.errorFirstName(), "First Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputFirstName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
		EditProfilePage.clickLastNameFld();
		if (!Assertions.compareValue("Field First Name must be at least 1 characters long, but not more than 35 characters", EditProfilePage.errorFirstName(), "First Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditProfilePage.getlastName();

		//		Validation check
		EditProfilePage.updateLastName("12345");
		EditProfilePage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditProfilePage.errorLastName(), "Last Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputLastName("");
		EditProfilePage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name must not be empty", EditProfilePage.errorLastName(), "Last Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputLastName(" ");
		EditProfilePage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name must not be empty", EditProfilePage.errorLastName(), "Last Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputLastName("~!@#$%^&*()");
		EditProfilePage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditProfilePage.errorLastName(), "Last Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputLastName(".string");
		EditProfilePage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name should contain only letters, space, period, hyphen (-) and apostrophe ('). Last Name should begin with a letter.", EditProfilePage.errorLastName(), "Last Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputLastName("qwertyuiopqwertyuiopqwertyuiop.-.-.-");
		EditProfilePage.clickFirstNameFld();
		if (!Assertions.compareValue("Field Last Name must be at least 1 characters long, but not more than 35 characters", EditProfilePage.errorLastName(), "Last Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditProfilePage.inputLastName(lastName + update);
		EditProfilePage.clickFirstNameFld();

//		Cancel changes
		EditProfilePage.clickBtnCancel();

		testLog.info("------------------------------------------------- My Profile page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		System.out.println(Name);
//		Verify User Name not changed

		if (!Assertions.compareValue(Name, MyProfilePage.getName(), "Name: ", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------- Edit Profile page. Update User data. Save -------------------------------------------------");

		MyProfilePage.clickLnkEditUserInf();
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name

		firstName = EditProfilePage.getfirstName();
		EditProfilePage.updateFirstName(update);

//		Update last name

		lastName = EditProfilePage.getlastName();
		EditProfilePage.updateLastName(update);

//		Save changes
		EditProfilePage.clickBtnSave();

		testLog.info("------------------------------------------------- My Profile page. Verify data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
//		System.out.println(userName);

//		Verify  Name updated
		if (!Assertions.compareBoolean(true,MyProfilePage.getName().contains(update), "Name updated: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareNumber(updateLength*2,MyProfilePage.getName().length() - NameLength, "UserName updated: ", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit User data. Remove changes -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		MyProfilePage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name

		Thread.sleep(TimeOut - 1000);
		firstName = EditProfilePage.getfirstName();
		firstName = firstName.replace(update, "");
		EditProfilePage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(TimeOut - 1000);
		lastName = EditProfilePage.getlastName();
		lastName = lastName.replace(update, "");
		EditProfilePage.inputLastName(lastName);

//		Save changes
		Thread.sleep(TimeOut - 1000);
		EditProfilePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=28, testName = "EOAdmin Update My Profile - Business Information", groups = { "Sanity" }, alwaysRun = true)

	public void EOAdminUpdateMyProfile_BusinessInfoUI() throws Exception {

		int a = 0;
		String Name = "";
		String update = " updated";
		String updateNum = "000";
		String Country = "";
		String Street = "";
		String City = "";
		String State = "";
		String PostalCode = "";
		String PhoneCode = "";
		String PhoneNumber = "";
		String WebSite = "";
		String Mail = "";
		String BusName = "";
		String BusAddress = "";
		String BusContNumber = "";
		String BusCountry = "";
		String BusEmail = "";
		String BusWebSite = "";
		String UpdatedMail = "";
		String UpdatedWebSite = "";

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

		testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickMyProfileMenu();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		AssertJUnit.assertEquals("My Profile", MyProfilePage.getTitle());
		Name = MyProfilePage.getName();
		int NameLength = Name.length();
		MyProfilePage.clickLnkEditBusInf();


		testLog.info("------------------------------------------- Edit Business page. Update Business data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(2000);
		Name = EditBusinessPage.getName();
//		Validation check
		EditBusinessPage.inputName("");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Name must not be empty", EditBusinessPage.errorName(), "Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputName(" ");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Name must not be empty", EditBusinessPage.errorName(), "Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputName("~!@#$%^&*()");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Name should contain only letters, numbers, commas, space, period, underscore, hyphen, apostrophe (') and the ampersand key (&). Name should begin with a letter or number", EditBusinessPage.errorName(), "Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputName("qwertyuiopqwertyuiopqwertyuiop.-.-.-123456789012345");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Name must be at least 1 characters long, but not more than 50 characters", EditBusinessPage.errorName(), "Name validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputName(Name + update);

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
		EditBusinessPage.clickFldName();
		if (!Assertions.compareValue("Field Street 1 must not be empty", EditBusinessPage.errorStreet(), "Street validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputStreet(" ");
		EditBusinessPage.clickFldName();
		if (!Assertions.compareValue("Field Street 1 must not be empty", EditBusinessPage.errorStreet(), "Street validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputStreet("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789 @");
		EditBusinessPage.clickFldName();
		if (!Assertions.compareValue("Field Street 1 must be at least 1 characters long, but not more than 100 characters", EditBusinessPage.errorStreet(), "Street validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputStreet(Street + update);

//		Update City
		Thread.sleep(TimeOut);
		City = EditBusinessPage.getCity();
//		Validation check
		EditBusinessPage.inputCity("");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Town/City must not be empty", EditBusinessPage.errorCity(), "City validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputCity(" ");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Town/City must not be empty", EditBusinessPage.errorCity(), "City validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputCity("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 123456789 @");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Town/City must be at least 1 characters long, but not more than 100 characters", EditBusinessPage.errorCity(), "City validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputCity(City + update);

//		Update State
		Thread.sleep(TimeOut);
		State = EditBusinessPage.getState();
//		Validation check
		EditBusinessPage.inputState("");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field State/Province/County must not be empty", EditBusinessPage.errorState(), "State validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputState(" ");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field State/Province/County must not be empty", EditBusinessPage.errorState(), "State validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputState("qwertyuiop 123456789qwertyuiop 123456789qwertyuiop 1");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field State/Province/County must be at least 1 characters long, but not more than 50 characters", EditBusinessPage.errorState(), "State validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputState(State + update);

//		Update ZIP Code
		Thread.sleep(TimeOut);
		PostalCode = EditBusinessPage.getZipCode();
//		Validation check
		EditBusinessPage.inputPostalCode("");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field ZIP/Postal Code must not be empty", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode("");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field ZIP/Postal Code must not be empty", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode("!@#$%^&*()");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field ZIP/Postal Code should contain only letters, numbers, space and hyphen (-), and must begin with a letter or number", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode("1");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field ZIP/Postal Code must be at least 2 characters long, but not more than 50 characters", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode("123456789012345678901234567890123456789012345678901");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field ZIP/Postal Code must be at least 2 characters long, but not more than 50 characters", EditBusinessPage.errorZIPCode(), "ZIP Code validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPostalCode(PostalCode + updateNum);

//		Update Phone Code
		Thread.sleep(TimeOut);
		PhoneCode = EditBusinessPage.getPhoneCode();

		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(2000);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
//		Validation check
		EditBusinessPage.inputPhoneNumber("");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Contact Number must not be empty", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber(" ");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Contact Number must not be empty", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber("strng!@#$%^&*()");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Contact Number should contain only numbers", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber("24234 34534543");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Contact Number should contain only numbers", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber("242");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Contact Number must be at least 4 characters long, but not more than 20 characters", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber("123456789012345678901");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Contact Number must be at least 4 characters long, but not more than 20 characters", EditBusinessPage.errorPhoneNumber(), "Phone number validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update Website
		Thread.sleep(TimeOut);
		WebSite = EditBusinessPage.getWebSite();
//		Validation check
		EditBusinessPage.inputWebSite("string");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Website should be a valid URL", EditBusinessPage.errorWebSite(), "WebSite validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputWebSite("string.x");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Website should be a valid URL", EditBusinessPage.errorWebSite(), "WebSite validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputWebSite("!@#$%^&*().com");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Website should be a valid URL", EditBusinessPage.errorWebSite(), "WebSite validation error: ", testLog)){
			TestPassFlag = false;
		}
		UpdatedWebSite = WebSite.replace(".",update + ".");
		UpdatedWebSite = UpdatedWebSite.replace(" ","");
		EditBusinessPage.inputWebSite(UpdatedWebSite);

//		Update Email
		Thread.sleep(TimeOut);
		Mail = EditBusinessPage.getEmail();
//		Validation check
		EditBusinessPage.inputMail("");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Email must not be empty", EditBusinessPage.errorMail(), "Mail validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputMail(" ");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Email must not be empty", EditBusinessPage.errorMail(), "Mail validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputMail("strng!@#$%^&*()");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Email should be a valid email", EditBusinessPage.errorMail(), "Mail validation error: ", testLog)){
			TestPassFlag = false;
		}

		EditBusinessPage.inputMail("czxvbnmqwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890@mail.com");
		EditBusinessPage.clickFldStreet();
		if (!Assertions.compareValue("Field Email must be at least 1 characters long, but not more than 255 characters", EditBusinessPage.errorMail(), "Mail validation error: ", testLog)){
			TestPassFlag = false;
		}

		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

		EditBusinessPage.clickFldStreet();
		EditBusinessPage.clickBtnCancel();

		EditBusinessPage.clickCancelMsgYes();

		testLog.info("------------------------------------------ My Profile page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");

//		Verify No changes saved
		if (!Assertions.compareValue(BusName, MyProfilePage.getBusName(), "Business Name not changed: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusCountry, MyProfilePage.getBusCountry(), "Country not changed: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusAddress, MyProfilePage.getBusAddress(), "Address not changed: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusContNumber, MyProfilePage.getBusPhoneNumber(), "Contact Number not changed: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusEmail, MyProfilePage.getBusEmail(), "Email not changed: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareValue(BusWebSite, MyProfilePage.getBusWebSite(), "WebSite not changed: ", testLog)){
			TestPassFlag = false;
		}

		MyProfilePage.clickLnkEditBusInf();

		testLog.info("------------------------------------------- Edit Business page. Update Business data. Save -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		BusName = EditBusinessPage.getName();
		EditBusinessPage.inputName(BusName + update);

//		Update Country
		Thread.sleep(TimeOut);
//		Country = EditBusinessPage.getCountry();
//		EditBusinessPage.updateCountry("AW");

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
		Thread.sleep(TimeOut);
		PhoneCode = EditBusinessPage.getPhoneCode();
		EditBusinessPage.inputPhoneCode(PhoneCode + updateNum);

//		Update Phone Number
		Thread.sleep(TimeOut);
		PhoneNumber = EditBusinessPage.getPhoneNumber();
		EditBusinessPage.inputPhoneNumber(PhoneNumber + updateNum);

//		Update WebSite
		Thread.sleep(TimeOut);
		WebSite = EditBusinessPage.getWebSite();
		UpdatedWebSite = WebSite.replace(".",update + ".");
		UpdatedWebSite = UpdatedWebSite.replace(" ","");
		EditBusinessPage.inputWebSite(UpdatedWebSite);

//		Update Email
		Thread.sleep(TimeOut);
		Mail = EditBusinessPage.getEmail();
		UpdatedMail = Mail.replace("@",update + "@");
		UpdatedMail = UpdatedMail.replace(" ","");
		EditBusinessPage.inputMail(UpdatedMail);

//		Save changes
		EditBusinessPage.clickBtnSave();

		testLog.info("------------------------------------------------- My Profile page. Verify Business data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");

//		Verify changes saved
		if (!Assertions.compareBoolean(false, BusName.contains(MyProfilePage.getBusName()), "Business Name stay with no changes: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusAddress.contains(MyProfilePage.getBusAddress()), "Address stay with no changes: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusContNumber.contains(MyProfilePage.getBusPhoneNumber()), "Contact Number stay with no changes: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusEmail.contains(MyProfilePage.getBusEmail()), "Email stay with no changes: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(false, BusWebSite.contains(MyProfilePage.getBusWebSite()), "WebSite stay with no changes: ", testLog)){
			TestPassFlag = false;
		}

		if (!Assertions.compareBoolean(true, MyProfilePage.getBusName().contains(update), "Business Name updated: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MyProfilePage.getBusAddress().contains(update), "Address updated: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MyProfilePage.getBusPhoneNumber().contains(updateNum), "Contact Number updated: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MyProfilePage.getBusEmail().contains(update.replace(" ","")), "Email updated: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareBoolean(true, MyProfilePage.getBusWebSite().contains(update.replace(" ","")), "WebSite updated: ", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit Business Information page. Remove changes -------------------------------------------------");

		MyProfilePage.clickLnkEditBusInf();
//		Remove changes
//		Edit Business page again
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditBusinessPage = (EditBusinessPage) PageFactory.getPage("EditBusinessPage");

//		Update Name
		Thread.sleep(TimeOut);
		EditBusinessPage.inputName(BusName);

//		Update Country
//		Thread.sleep(TimeOut);
//		EditBusinessPage.updateCountry(Country);

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
		Thread.sleep(TimeOut);
		EditBusinessPage.inputPhoneCode(PhoneCode);

//		Update Phone Number
		Thread.sleep(TimeOut);
		EditBusinessPage.inputPhoneNumber(PhoneNumber);

//		Update Email
		Thread.sleep(TimeOut);
		EditBusinessPage.inputMail(Mail);

//		Update WebSite
		Thread.sleep(TimeOut);
		EditBusinessPage.inputWebSite(WebSite);

//		Save changes
		EditBusinessPage.clickBtnSave();

		testLog.info("------------------------------------------------- Merchant Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=29, testName = "Merchant Manager Update My Profile - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void MerchantManUpdateMyProfile_UserInfoUI() throws Exception {

		String firstName = "";
		String lastName = "";
		String Name = "";
		String update = " updated";
		int updateLength = update.length();

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		User EOMerchantMan = EntitiesFactory.getEntity("EOMerchantManager");
		EOMerchManMail = EOMerchantMan.getUserName();
		EOMerchManPwd = EOMerchantMan.getPassword();

		testLog.info("-------------------------------------------------Login as: " + EOMerchManMail + " " + EOMerchManPwd + "-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EOMerchManMail);
		LoginEOPortal.loginInputPassword(EOMerchManPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickMyProfileMenu();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		AssertJUnit.assertEquals("My Profile", MyProfilePage.getTitle());
		Name = MyProfilePage.getName();
		int NameLength = Name.length();
		MyProfilePage.clickLnkEditUserInf();

		testLog.info("------------------------------------------- Edit Profile page. Update User data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name
		firstName = EditProfilePage.getfirstName();
		EditProfilePage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditProfilePage.getlastName();
		EditProfilePage.inputLastName(lastName + update);
		EditProfilePage.clickFirstNameFld();

//		Cancel changes
		EditProfilePage.clickBtnCancel();

		testLog.info("------------------------------------------------- My Profile page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		System.out.println(Name);
//		Verify User Name not changed

		if (!Assertions.compareValue(Name, MyProfilePage.getName(), "Name: ", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------- Edit Profile page. Update User data. Save -------------------------------------------------");

		MyProfilePage.clickLnkEditUserInf();
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name

		firstName = EditProfilePage.getfirstName();
		EditProfilePage.updateFirstName(update);

//		Update last name

		lastName = EditProfilePage.getlastName();
		EditProfilePage.updateLastName(update);

//		Save changes
		EditProfilePage.clickBtnSave();

		testLog.info("------------------------------------------------- My Profile page. Verify data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
//		System.out.println(userName);

//		Verify  Name updated
		if (!Assertions.compareBoolean(true,MyProfilePage.getName().contains(update), "Name updated: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareNumber(updateLength*2,MyProfilePage.getName().length() - NameLength, "UserName updated: ", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit User data. Remove changes -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		MyProfilePage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name

		Thread.sleep(TimeOut - 1000);
		firstName = EditProfilePage.getfirstName();
		firstName = firstName.replace(update, "");
		EditProfilePage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(TimeOut - 1000);
		lastName = EditProfilePage.getlastName();
		lastName = lastName.replace(update, "");
		EditProfilePage.inputLastName(lastName);

//		Save changes
		Thread.sleep(TimeOut - 1000);
		EditProfilePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}
	@Test(enabled = true, priority=30, testName = "Merchant Manager verify no Business Information link exists", groups = { "Sanity" }, alwaysRun = true)

	public void MerchantManUpdateMyProfile_NoBusinessInfoUI() throws Exception {


		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		User EOMerchantMan = EntitiesFactory.getEntity("EOMerchantManager");
		EOMerchManMail = EOMerchantMan.getUserName();
		EOMerchManPwd = EOMerchantMan.getPassword();

		testLog.info("-------------------------------------------------Login as: " + EOMerchManMail + " " + EOMerchManPwd + "-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EOMerchManMail);
		LoginEOPortal.loginInputPassword(EOMerchManPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickMyProfileMenu();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		AssertJUnit.assertEquals("My Profile", MyProfilePage.getTitle());

		testLog.info("------------------------------------------- Verify NO Business page link exists. -------------------------------------------------");

		if (!Assertions.compareBoolean(false,MyProfilePage.elementBusInfoEditClickable(), "Link Business Info exists: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=31, testName = "Device and Application Manager Update My Profile - User Info", groups = { "Sanity" }, alwaysRun = true)

	public void DevAppManUpdateMyProfile_UserInfoUI() throws Exception {

		String firstName = "";
		String lastName = "";
		String Name = "";
		String update = " updated";
		int updateLength = update.length();

		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		User EODevAppMan = EntitiesFactory.getEntity("EODevAppManager");
		EODevAppManMail = EODevAppMan.getUserName();
		EODevAppManPwd = EODevAppMan.getPassword();

		testLog.info("-------------------------------------------------Login as: " + EODevAppManMail + " " + EODevAppManPwd + "-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EODevAppManMail);
		LoginEOPortal.loginInputPassword(EODevAppManPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickMyProfileMenu();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		AssertJUnit.assertEquals("My Profile", MyProfilePage.getTitle());
		Name = MyProfilePage.getName();
		int NameLength = Name.length();
		MyProfilePage.clickLnkEditUserInf();

		testLog.info("------------------------------------------- Edit Profile page. Update User data. Cancel -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name
		firstName = EditProfilePage.getfirstName();
		EditProfilePage.inputFirstName(firstName + update);

//		Update last name

		lastName = EditProfilePage.getlastName();
		EditProfilePage.inputLastName(lastName + update);
		EditProfilePage.clickFirstNameFld();

//		Cancel changes
		EditProfilePage.clickBtnCancel();

		testLog.info("------------------------------------------------- My Profile page. Verify no changes saved -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		System.out.println(Name);
//		Verify User Name not changed

		if (!Assertions.compareValue(Name, MyProfilePage.getName(), "Name: ", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------- Edit Profile page. Update User data. Save -------------------------------------------------");

		MyProfilePage.clickLnkEditUserInf();
		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name

		firstName = EditProfilePage.getfirstName();
		EditProfilePage.updateFirstName(update);

//		Update last name

		lastName = EditProfilePage.getlastName();
		EditProfilePage.updateLastName(update);

//		Save changes
		EditProfilePage.clickBtnSave();

		testLog.info("------------------------------------------------- My Profile page. Verify data updated -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
//		System.out.println(userName);

//		Verify  Name updated
		if (!Assertions.compareBoolean(true,MyProfilePage.getName().contains(update), "Name updated: ", testLog)){
			TestPassFlag = false;
		}
		if (!Assertions.compareNumber(updateLength*2,MyProfilePage.getName().length() - NameLength, "UserName updated: ", testLog)){
			TestPassFlag = false;
		}

		testLog.info("------------------------------------------------- Edit User data. Remove changes -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		MyProfilePage.clickLnkEditUserInf();

//		Edit User page
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		EditProfilePage = (EditProfilePage) PageFactory.getPage("EditProfilePage");

//		Update first name

		Thread.sleep(TimeOut - 1000);
		firstName = EditProfilePage.getfirstName();
		firstName = firstName.replace(update, "");
		EditProfilePage.inputFirstName(firstName);

//		Update last name

		Thread.sleep(TimeOut - 1000);
		lastName = EditProfilePage.getlastName();
		lastName = lastName.replace(update, "");
		EditProfilePage.inputLastName(lastName);

//		Save changes
		Thread.sleep(TimeOut - 1000);
		EditProfilePage.clickBtnSave();

		testLog.info("------------------------------------------------- User Details page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		Assert.assertTrue(TestPassFlag);
	}
	@Test(enabled = true, priority=32, testName = "Device and Application Manager verify no Business Information link exists", groups = { "Sanity" }, alwaysRun = true)

	public void DevAppManUpdateMyProfile_NoBusinessInfoUI() throws Exception {


		Env = "https://" + envConfig.getEnv() + EnvPort;
		Boolean TestPassFlag = true;

		testLog.info("-------------------------------------------------Navigate to EO Portal-------------------------------------------------");

		BasePage.driver.navigate().to(Env);
		LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");

		User EODevAppMan = EntitiesFactory.getEntity("EODevAppManager");
		EODevAppManMail = EODevAppMan.getUserName();
		EODevAppManPwd = EODevAppMan.getPassword();

		testLog.info("-------------------------------------------------Login as: " + EODevAppManMail + " " + EODevAppManPwd + "-------------------------------------------------");

		LoginEOPortal.loginInputEmail(EODevAppManMail);
		LoginEOPortal.loginInputPassword(EODevAppManPwd);
		LoginEOPortal.clickLoginBtn();


		ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");

		testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

		HomePage.clickHeaderMenu();
		HomePage.clickMyProfileMenu();

		testLog.info("------------------------------------------------- My Profile page -------------------------------------------------");

		Thread.sleep(TimeOut + 1000);
		availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
		BasePage.driver.switchTo().window(availableWindows.get(0));
		MyProfilePage MyProfilePage = (MyProfilePage) PageFactory.getPage("MyProfilePage");
		AssertJUnit.assertEquals("My Profile", MyProfilePage.getTitle());

		testLog.info("------------------------------------------- Verify NO Business page link exists. -------------------------------------------------");

		if (!Assertions.compareBoolean(false,MyProfilePage.elementBusInfoEditClickable(), "Link Business Info exists: ", testLog)){
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);
	}

}
