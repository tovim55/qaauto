package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";

    private By sectionTitleLoc = By.xpath("//*[@class='section-title']");
    private By merchantTable = By.className("vui-table ");
    private By merchantBtn = By.id("merchants");
    private By headerMenuContainerLoc = By.xpath("//*[@class='header-menu__container']");
//    private By headerMenuLoc = By.xpath("//*[@class='header-menu__item header-menu__item--last-child']");
    private By headerMenuLoc = By.xpath("//*[@class='header-menu__link ']");
    private By headerMenuGroupLoc = By.xpath("//*[@class='header-submenu__group']");
    private By merchantsMenuLoc = By.xpath("(//*[@class='header-menu__item'])[2]");
    private By userMenuLoc = By.id("users");
    private By profileMenuLoc = By.id("profile");
    private By mailerMenuLoc = By.id("mailer");
    private By sponsorMenuLoc = By.id("sponsor");
    private By logoutMenuLoc = By.id("logout");
    private By companyMenuLoc = By.id("company");

    public HomePage() {
        super(url, title);
    }

    public String getMerchants() throws InterruptedException {
        return getTextFromTable(merchantTable);
    }

    public boolean menuAccountExists () throws Exception {
        return isExists(headerMenuLoc, 20);
    }
    public boolean menuMerchantsExists () throws Exception {
        return isExists(merchantsMenuLoc, 20);
    }
    public void clickHeaderMenu () throws InterruptedException {
    	boolean f1 = false;
    	int t = 0;
    	WebDriverWait wait = new WebDriverWait(BasePage.driver, 20);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(headerMenuLoc));
    	Thread.sleep(3000);
    	while (f1 == false & t < 20000){
    		f1 = BasePage.driver.findElement(headerMenuLoc).isEnabled();
    		t=t+1000;
    	}
        click(headerMenuLoc);
    }
    public void clickMerchantsMenu () throws InterruptedException {
        boolean f1 = false;
        int t = 0;
        WebDriverWait wait = new WebDriverWait(BasePage.driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(merchantsMenuLoc));
        Thread.sleep(3000);
        while (f1 == false & t < 20000){
            f1 = BasePage.driver.findElement(merchantsMenuLoc).isEnabled();
            t=t+1000;
        }
        click(merchantsMenuLoc);
    }
    public void clickUserMenu () throws InterruptedException {
    	
        click(userMenuLoc);
    }
    public void clickLogoutMenu () throws InterruptedException {

        click(logoutMenuLoc);
    }
    public boolean menuUserExists () throws Exception {
    	return isExists(userMenuLoc, 20);
    }
    public boolean menuProfileExists () throws Exception {
        return isExists(profileMenuLoc, 20);
    }
    public boolean menuMailerExists () throws Exception {
        return isExists(mailerMenuLoc, 20);
    }
    public boolean menuSponsorExists () throws Exception {
        return isExists(sponsorMenuLoc, 20);
    }
    public boolean menuLogoutExists () throws Exception {
        return isExists(logoutMenuLoc, 20);
    }
    public boolean menuCompanyExists () throws Exception {
        return isExists(companyMenuLoc, 20);
    }

    public boolean headerExists () throws Exception {
        return isExists(headerMenuContainerLoc, 20);
    }
//    --------------------------------------------------------------------------
//    * Method: Get Menu text.
//    * Return Menu text as String
//    * @authors Yana Fridman
//    */
//--------------------------------------------------------------------------
    public String accountMenuText() {

    	String a = getText(headerMenuGroupLoc);
    	System.out.println(a);
    	return getText(headerMenuGroupLoc);
    }
    //    --------------------------------------------------------------------------
//    * Method: Get Title text.
//    * Return Title text as String
//    * @authors Yana Fridman
//    */
//--------------------------------------------------------------------------
    public String sectionTitle() {

        String a = getText(sectionTitleLoc);
        System.out.println(a);
        return getText(sectionTitleLoc);
    }
}
