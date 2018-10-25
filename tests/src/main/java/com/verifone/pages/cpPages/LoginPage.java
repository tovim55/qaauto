package com.verifone.pages.cpPages;


import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static com.verifone.utils.Assertions.assertTextContains;
import static com.verifone.utils.Assertions.assertTextEqual;


public class LoginPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "docs/overview/get-started/";
    private final static String title = "Getting Started | Get Started | developer.verifone.com";

    private By firstUsername = By.id("username");
    private By username = By.id("username");
    private By password = By.id("ipassword");
    private By iframe = By.id("veriPassFrame");
    private By supportUsername = By.id("Username");
    private By SupportPassword = By.id("Passwd");
    //    private By firstPass= By.id("password");
    private By toLoginPageBtn = By.xpath("(//*[@class=\"header-menu__link js-header-menu__link\"])[5]");
    private By continueToLoginBtn = By.xpath("//a[text()='Continue to Login']");

    private By loginBtn = By.id("btnPrimaryLogin");
    private By supportLoginBtn = By.id("signIn");
    private By companiesBtn = By.xpath("(//*[@class=\"header-menu__link js-header-menu__link\" and @id=\"companies\"])");
    private By newCompany = By.xpath("(//*[@class=\"vui-datagrid-body-row \"])[1]");
    private By tableRows2 = By.xpath("(//*[@class=\"vui-datagrid-body-row \"])[2]");
    private By acceptBtn = By.xpath("(//*[@class=\"btn btn-default btn-primary btn-raised approve\"])");
    private By rejectBtn = By.xpath("(//*[@class=\"btn btn-default btn-primary reject\"])");
    private By confirnReject = By.xpath("(//*[@id=\"modalAffirmId\" and @class=\"btn btn-primary btn-raised vui-modal-affirm\"])");
    protected By loader = By.className("vui-spinner");

    public LoginPage() {
        super(url, title);
        navigate();
//        validateTitle();
    }

    public void loginPageCp(User user) {
        waitSimple(2000);
        sendKeys(username, user.getUserName());
        switchToIframe(iframe);
        click(password);
        sendKeys(password, user.getPassword());
        driver.switchTo().defaultContent();
        click(loginBtn);

    }

    public void clickOmLoginBtn() {
        hoverAndClickOnElement(toLoginPageBtn);
        click(continueToLoginBtn);
    }

    public void supportLogin(User user) throws Exception {
        clickOmLoginBtn();
        sendKeys(firstUsername, user.getUserName());
        switchToIframe(iframe);
        click(password);
        if (user.getUserName().contains("@verifone.com")) {
            oktaHandle(user);
            driver.switchTo().defaultContent();
        } else {
            String userName = user.getUserName().split("@")[0];
            sendKeys(supportUsername, userName);
            sendKeys(SupportPassword, user.getPassword());
            click(supportLoginBtn);
        }
        waitSimple(2000);
    }

    private void oktaHandle(User user) throws Exception {
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
        OktaLogin.loginInputName(String.valueOf(user.getName()));
        OktaLogin.loginInputPassword(String.valueOf(user.getPassword()));
        OktaLogin.clickSignInBtn();
        waitSimple(1000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
        OktaLogin.loginInputAnswer(String.valueOf(user.getSecurityAnswer()));
        testLog.info("Security answer: " + "");
        OktaLogin.clickVerifyBtn();
    }

    public void checkExistCompanies(Company user) {
        waitSimple(5000);
        clickCompaniesBtn();
//        assertTextContains(user.getCompanyName(), getText(newCompany));
        checkCompanyDetails(user, "In Review");
//        click(newCompany);
    }

    private void clickCompaniesBtn() {
        waitSimple(8000);
        waitForLoader(loader);
        click(companiesBtn);
    }

    private void waitSimple(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void acceptCompany(Company user) {
        clickNewCompany();
        waitSimple(4000);
        click(acceptBtn);
        checkCompanyDetails(user, "CP_Approved");

    }

    private void checkCompanyDetails(Company user, String status) {
        waitSimple(8000);
        String[] companyDetails;
        companyDetails = getNewCompanyDetails();
        testLog.info("TEST 1: Excepted results: " + user.getCompanyName() + " Actual results: " + companyDetails[0]);
        assertTextContains(user.getCompanyName(), companyDetails[0]);
        testLog.info("TEST 2: Excepted results: " + status + " Actual results: " + companyDetails[1]);
        assertTextEqual(status, companyDetails[1]);
    }

    public void rejectCompany(Company user) {
        clickNewCompany();
        clickRejectCompany();
        checkCompanyDetails(user, "Rejected");
    }

    private void clickRejectCompany() {
        waitSimple(4000);
        click(rejectBtn);
        waitSimple(3000);
        click(confirnReject);
    }

    private String[] getNewCompanyDetails() {
        String[] companyDetails;
        companyDetails = getText(newCompany).split("\n");
        return companyDetails;
    }

    private void clickNewCompany() {
        clickCompaniesBtn();
        click(newCompany);

    }
}
