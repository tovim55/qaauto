package com.verifone.pages.cgPages;

import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;

import java.io.File;

import static com.verifone.utils.Assertions.assertTextEqual;

public class CGLoginPage extends BasePage {


    private final static String url = BaseTest.envConfig.getWebUrl();
    private final static String title = "Commerce gateway portal";

    private By toLoginPageBtn = By.partialLinkText("Log");
    private By username = By.id("username");
    private By password = By.id("ipassword");
    private By loginBtn = By.id("btnPrimaryLogin");
    private By iframe = By.id("veriPassFrame");
    private By dashboardIframe = By.id("iFrameResizer0");
    private By cgDashboardTitle = By.xpath("(//*[@class=\"dashboard-header clearfix\"]/h2)");
    private By panel = By.xpath("//*[@class='panel-body']");


    public CGLoginPage() {
        super(url, title);

    }

    public void openChrome() {
        System.setProperty("webdriver.chrome.driver", new File(System.getProperty("user.dir")).getParent() + "\\infra\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--incognito");
        driver = new ChromeDriver();
        System.out.println("CHROME web driver started successfully");
        driver.manage().window().maximize();
        navigate();
    }

    public void doLogin(User user) {
        click(toLoginPageBtn);
        sendKeys(username, user.getUserName());
        switchToIframe(iframe);
        click(password);
        sendKeys(password, user.getPassword());
        driver.switchTo().defaultContent();
        click(loginBtn);
    }

    public void checkTitle() {
        switchToIframe(dashboardIframe);
        String pageTitle = getText(cgDashboardTitle);
        assertTextEqual("CG Dashboard", pageTitle);
        testLog.info("Test passed - Login success, browser on page: " + pageTitle);
        driver.switchTo().defaultContent();
    }


    public void clickLoginLink() {
        click(toLoginPageBtn);
    }

    public void inputUserName(String UserName){
        sendKeys(username, UserName);
        click(panel);
    }

    public void inputPassword(String Pwd){
       switchToIframe(iframe);
        sendKeys(password, Pwd);
        driver.switchTo().defaultContent();
        click(panel);
//        sendKeys(password, Pwd);
    }

    public void clickLoginBtn(){
        click(loginBtn);
    }


}
