//package com.verifone.utils.EstateOwner;
//
//import com.verifone.pages.BasePage;
//import com.verifone.pages.PageFactory;
//import com.verifone.pages.eoPages.AddUserPage;
//import com.verifone.pages.eoPages.HomePage;
//import com.verifone.pages.eoPages.LoginEOPortal;
//import com.verifone.pages.eoPages.UsersPage;
//import org.testng.AssertJUnit;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//public class User {
//    public void addMerchantManager(String env, String EOAdminMail, String EOAdminPwd, String MerchantMail) throws InterruptedException {
//        BasePage.driver.navigate().to(Env);
//        LoginEOPortal LoginEOPortal = (LoginEOPortal) PageFactory.getPage("LoginEOPortal");
//        LoginEOPortal.loginInputEmail(EOAdminMail);
//        LoginEOPortal.loginInputPassword(EOAdminPwd);
//        LoginEOPortal.clickLoginBtn();
//
//
//        ArrayList<String> availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//        BasePage.driver.switchTo().window(availableWindows.get(0));
//        HomePage HomePage = (HomePage) PageFactory.getPage("HomePage");
//        HomePage.clickHeaderMenu();
//
//        HomePage.clickUserMenu();
//
//        Thread.sleep(5000);
//        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//        BasePage.driver.switchTo().window(availableWindows.get(0));
//        UsersPage UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
//        AssertJUnit.assertEquals("Users", UsersPage.titleUsers());
//        UsersPage.clickAddUserBtn();
//
//        Thread.sleep(5000);
//        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//        BasePage.driver.switchTo().window(availableWindows.get(0));
//        AddUserPage AddUserPage = (AddUserPage) PageFactory.getPage("AddUserPage");
//        AddUserPage.inputFirstName("UserEOAdmin two");
//        AddUserPage.inputLastName("UserLastEOAdmin two");
//
//        UserMerchManEmail = "User" + LocalDateTime.now() + "MerchMan@getnada.com";
//        UserMerchManEmail = UserMerchManEmail.replace("-", "");
//        UserMerchManEmail = UserMerchManEmail.replace(":", "");
//        AddUserPage.inputEmail(UserMerchManEmail);
//        AddUserPage.clickDropDn();
//        AddUserPage.clickDropDnItem("EO Merchant Manager");
//        AddUserPage.clickSubmitBtn();
//
//        Thread.sleep(10000);
//        availableWindows = new ArrayList<String>(BasePage.driver.getWindowHandles());
//        BasePage.driver.switchTo().window(availableWindows.get(0));
//        UsersPage = (UsersPage) PageFactory.getPage("UsersPage");
//
//        boolean fl = UsersPage.tblUsersFirstLineEmailText().contains(UserMerchManEmail);
//        AssertJUnit.assertEquals(true, fl);
//    }
//}
