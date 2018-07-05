//package com.verifone.pages.cpPages;
//
//
//import com.verifone.infra.User;
//import com.verifone.pages.BasePage;
//import com.verifone.tests.BaseTest;
//import org.openqa.selenium.By;
//
//
//public class VerifoneAccountLoginPage extends BasePage {
//
//    private final static String url = BaseTest.envConfig.getWebUrl() + "docs/overview/get-started/";
//    private final static String title = "Getting Started | Get Started | developer.verifone.com";
//
//    private By toLoginPageBtn = By.partialLinkText("Log");
//    private By firstUsername= By.id("username");
//    private By firstPass= By.id("password");
//    private By supportUsername= By.id("Username");
//    private By SupportPassword = By.id("Passwd");
////    private By toLoginPageBtn = By.partialLinkText("Log");
//    private By supportLoginBtn = By.id("signIn");
//
//
//
//    public VerifoneAccountLoginPage() {
//        super(url, title);
//        navigate();
//        validateTitle();
//    }
//
//    public void login(User user) {
//        click(toLoginPageBtn);
//        sendKeys(firstUsername, user.getUserName());
//        click(firstPass);
//        String userName = user.getUserName().split("@")[0];
//        sendKeys(supportUsername, userName);
//        sendKeys(SupportPassword, user.getPassword());
//        click(loginBtn);
//
//    }
//
////    public void clickOmLoginBtn() {
////        click(toLoginPageBtn);
////    }
//}
