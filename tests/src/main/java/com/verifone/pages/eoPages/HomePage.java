package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class HomePage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";

    private By sectionTitleLoc = By.xpath("//*[@class='section-title']");
    private By merchantTable = By.className("vui-table ");
    private By merchantBtn = By.id("merchants");
    private By headerMenuContainerLoc = By.xpath("//*[@class='header-menu__container']");
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
//--------------------------------------------------------------------------
    /**
     * Method: Get Merchants list from current page.
     * Return Merchants list as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getMerchants() throws InterruptedException {
        return getTextFromTable(merchantTable);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Account menu exists.
     * Return True if exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean menuAccountExists () throws Exception {
        return isExists(headerMenuLoc, 20);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Merchants menu exists.
     * Return True if exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean menuMerchantsExists () throws Exception {
        return isExists(merchantsMenuLoc, 20);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Wait 20 sec. for Account menu displayed and click on Account menu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickHeaderMenu () throws InterruptedException {
    	boolean f1 = false;
    	int t = 0;
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(headerMenuLoc));
    	Thread.sleep(3000);
    	while (f1 == false & t < 20000){
    		f1 = driver.findElement(headerMenuLoc).isEnabled();
    		t=t+1000;
    	}
        click(headerMenuLoc);
    }


    //--------------------------------------------------------------------------
    /**
     * Method: Wait 20 sec. for Merchants menu displayed and click on Merchants menu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------

    public void clickMerchantsMenu () throws InterruptedException {
        boolean f1 = false;
        int t = 0;
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(merchantsMenuLoc));
        Thread.sleep(3000);
        while (f1 == false & t < 20000){
            f1 = driver.findElement(merchantsMenuLoc).isEnabled();
            t=t+1000;
        }
        click(merchantsMenuLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on My Profile submenu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMyProfileMenu () throws InterruptedException {

        click(profileMenuLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Users submenu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickUserMenu () throws InterruptedException {
        click(userMenuLoc);
    }
    public void clickCustomizeMenu () throws InterruptedException {
        click(mailerMenuLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on LogOut submenu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLogoutMenu () throws InterruptedException {
        click(logoutMenuLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Users submenu exists.
     * Return True if exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean menuUserExists () throws Exception {
    	return isExists(userMenuLoc, 20);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if My Profile submenu exists.
     * Return True if exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean menuProfileExists () throws Exception {
        return isExists(profileMenuLoc, 20);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Customize submenu exists.
     * Return True if exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean menuMailerExists () throws Exception {
        return isExists(mailerMenuLoc, 20);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Sponsor submenu exists.
     * Return True if exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean menuSponsorExists () throws Exception {
        return isExists(sponsorMenuLoc, 20);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if LogOut submenu exists.
     * Return True if exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean menuLogoutExists () throws Exception {
        return isExists(logoutMenuLoc, 20);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Company submenu exists.
     * Return True if exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
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
