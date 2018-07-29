package com.verifone.pages.cpPages;


import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.verifone.utils.Assertions.assertTextContains;


public class LoginPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "docs/overview/get-started/";
    //    private final static String url = "";
    private final static String title = "Getting Started | Get Started | developer.verifone.com";

    private By firstUsername = By.id("username");
    private By username = By.id("username");
    private By password = By.id("password");
    private By supportUsername = By.id("Username");
    private By SupportPassword = By.id("Passwd");
    //    private By firstPass= By.id("password");
    private By toLoginPageBtn = By.partialLinkText("Log");
    private By loginBtn = By.id("btnPrimaryLogin");
    private By supportLoginBtn = By.id("signIn");
    private By companiesBtn = By.xpath("(//*[@class=\"header-menu__link js-header-menu__link\"])[4]");
    private By tableRows1 = By.xpath("(//*[@class=\"vui-datagrid-body-row \"])[1]");
    private By tableRows2 = By.xpath("(//*[@class=\"vui-datagrid-body-row \"])[2]");
    private By acceptBtn = By.xpath("(//*[@class=\"btn btn-default btn-primary btn-raised approve\"])");


    public LoginPage() {
        super(url, title);
        navigate();
//        validateTitle();
    }

    public void loginPageCp(User user) {
        sendKeys(username, user.getUserName());
        click(password);
        sendKeys(password, user.getPassword());
        click(loginBtn);

    }

    public void clickOmLoginBtn() {
        click(toLoginPageBtn);
    }

    public void supportLogin(User user) {
        click(toLoginPageBtn);
        sendKeys(firstUsername, user.getUserName());
        click(password);
        String userName = user.getUserName().split("@")[0];
        sendKeys(supportUsername, userName);
        sendKeys(SupportPassword, user.getPassword());
        click(supportLoginBtn);
    }

    public void checkExistCompanies(Company user) {
        clickCompaniesBtn();
        assertTextContains(user.getCompanyName(), getText(tableRows1));
        click(tableRows1);
    }

    private void clickCompaniesBtn() {
        waitSimple(6000);
        click(companiesBtn);
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
        clickCompaniesBtn();
        click(tableRows1);
        waitSimple(3000);
        click(acceptBtn);
        waitSimple(3000);
        System.out.println(getText(tableRows1));
        assertTextContains(user.getCompanyName(), getText(tableRows1));


    }
}
