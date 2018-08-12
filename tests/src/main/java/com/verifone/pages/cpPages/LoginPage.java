package com.verifone.pages.cpPages;


import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.verifone.utils.Assertions.assertTextContains;
import static com.verifone.utils.Assertions.assertTextEqual;


public class LoginPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "docs/overview/get-started/";
    //    private final static String url = "";
    private final static String title = "Getting Started | Get Started | developer.verifone.com";

    private By firstUsername = By.id("username");
    private By username = By.id("username");
    private By password = By.id("ipassword");
    private By iframe = By.id("veriPassFrame");
    private By supportUsername = By.id("Username");
    private By SupportPassword = By.id("Passwd");
    //    private By firstPass= By.id("password");
    private By toLoginPageBtn = By.partialLinkText("Log");
    private By loginBtn = By.id("btnPrimaryLogin");
    private By supportLoginBtn = By.id("signIn");
    private By companiesBtn = By.xpath("(//*[@class=\"header-menu__link js-header-menu__link\"])[4]");
    private By newCompany = By.xpath("(//*[@class=\"vui-datagrid-body-row \"])[1]");
    private By tableRows2 = By.xpath("(//*[@class=\"vui-datagrid-body-row \"])[2]");
    private By acceptBtn = By.xpath("(//*[@class=\"btn btn-default btn-primary btn-raised approve\"])");
    private By rejectBtn = By.xpath("(//*[@class=\"btn btn-default btn-primary reject\"])");


    public LoginPage() {
        super(url, title);
        navigate();
//        validateTitle();
    }

    public void loginPageCp(User user) {
        sendKeys(username, user.getUserName());
        switchToIframe(iframe);
        click(password);
        sendKeys(password, user.getPassword());
        driver.switchTo().defaultContent();
        click(loginBtn);

    }

    public void clickOmLoginBtn() {
        click(toLoginPageBtn);
    }

    public void supportLogin(User user) {
        click(toLoginPageBtn);
        sendKeys(firstUsername, user.getUserName());
        switchToIframe(iframe);
        click(password);
        String userName = user.getUserName().split("@")[0];
        sendKeys(supportUsername, userName);
        sendKeys(SupportPassword, user.getPassword());
        click(supportLoginBtn);
    }

    public void checkExistCompanies(Company user) {
        waitSimple(5000);
        clickCompaniesBtn();
        System.out.println(getText(newCompany)  + "98998989898989  "  + user.getCompanyName());
        assertTextContains(user.getCompanyName(), getText(newCompany));
//        click(newCompany);
    }

    private void clickCompaniesBtn() {
        clickAcceptCompany(6000, companiesBtn);
    }

    private void waitSimple(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void restartDriver() {
        driver.manage().deleteAllCookies();         // Clear Cookies on the browser
        driver.quit();
        driver = null;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void acceptCompany(Company user) {
        clickNewCompany();
        clickAcceptCompany(5000, acceptBtn);
        checkCompanyDetails(user, "CP_Approved");

    }

    private void checkCompanyDetails(Company user, String status) {
        waitSimple(8000);
        String[] companyDetails;
        companyDetails = getNewCompanyDetails();
        assertTextContains(user.getCompanyName(), companyDetails[0]);
        assertTextEqual(status, companyDetails[1]);
    }

    public void rejectCompany(Company user) {
        clickNewCompany();
        clickRejectCompany();
        checkCompanyDetails(user, "Rejected");
    }

    private void clickRejectCompany() {
        click(rejectBtn);
    }

    private String[] getNewCompanyDetails() {
        String[] companyDetails;
        companyDetails = getText(newCompany).split("\n");
//        System.out.println(companyDetails[0] + "    0");
//        System.out.println(companyDetails[1] + "    1");
//        System.out.println(companyDetails[2] + "    2");
        return companyDetails;
    }

    private void clickNewCompany() {
        clickCompaniesBtn();
        click(newCompany);

    }

    private void clickAcceptCompany(int time, By acceptBtn) {
        waitSimple(time);
        click(acceptBtn);
    }
}
