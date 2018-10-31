package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on My Profile page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class MyProfilePage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By titleLoc = By.xpath("//*[@class='content-header']");
    private By myNameLoc = By.xpath("(//*[@class='control-value'])[1]");
    private By myEmailLoc = By.xpath("(//*[@class='control-value'])[2]");
    private By userEditLoc = By.xpath("//*[@class='pull-right edit-user']");
    private By busEditLoc = By.xpath("//*[@class='edit-business pull-right']");
    private By userEnableEditLoc = By.xpath("//*[@class='pull-right edit-user']");
    private By roleLoc = By.xpath("(//*[@class='control-value'])[4]");
    private By lnkChangePasswordLoc = By.xpath("//*[@class='change-password']");

    private By myBusNameLoc = By.xpath("(//*[@class='control-value'])[5]");
    private By myBusCountryLoc = By.xpath("(//*[@class='control-value'])[6]");
    private By myBusAddressLoc = By.xpath("(//*[@class='control-value'])[7]");
    private By myBusPhoneNumberLoc = By.xpath("(//*[@class='control-value'])[8]");
    private By myBusWebSiteLoc = By.xpath("(//*[@class='control-value'])[9]");
    private By myBusEmailLoc = By.xpath("(//*[@class='control-value'])[10]");

    public MyProfilePage() {
        super(url, title);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Wait 5 sec for Email value displayed and Get Email value.
     * Return Email value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getMyEmail() throws InterruptedException {
        int t = 0;
        while (getText(myEmailLoc).length()<=3 & t < 5000){
            Thread.sleep(500);
            t = t + 500;
        }
        return getText(myEmailLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Name value.
     * Return Name value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getName() throws InterruptedException {
        return getText(myNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get page Title.
     * Return page Title as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getTitle() throws InterruptedException {
        return getText(titleLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Role value.
     * Return Role value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getRole() throws InterruptedException {
        return getText(roleLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Wait 5 sec for Business Name value displayed and Get Business Name value.
     * Return Business Name value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getBusName() throws InterruptedException {
        int t = 0;
        while (getText(myBusNameLoc).length()<1 & t < 5000){
            Thread.sleep(500);
            t = t + 500;
        }
        return getText(myBusNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Country value.
     * Return Country value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getBusCountry() throws InterruptedException {
        return getText(myBusCountryLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Address value.
     * Return Address value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getBusAddress() throws InterruptedException {
        return getText(myBusAddressLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Phone Number value.
     * Return Phone Number value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getBusPhoneNumber() throws InterruptedException {
        return getText(myBusPhoneNumberLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Website value.
     * Return Website value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getBusWebSite() throws InterruptedException {
        return getText(myBusWebSiteLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Email value.
     * Return Email value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getBusEmail() throws InterruptedException {
        return getText(myBusEmailLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Email enabled.
     * Return True if enabled.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean elementEmailClickable() throws InterruptedException {
    	WebElement elem = driver.findElement(myEmailLoc);
//    	return elem.isEnabled();	
    	try{
            WebDriverWait wait = new WebDriverWait(driver, 6);
            wait.until(ExpectedConditions.attributeContains(elem, "localName", "input"));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Role enabled.
     * Return True if enabled.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean elementRoleClickable() throws InterruptedException {
    	WebElement elem = driver.findElement(roleLoc);
//    	return elem.isEnabled();	
    	try{
            WebDriverWait wait = new WebDriverWait(driver, 6);
            wait.until(ExpectedConditions.attributeContains(elem, "localName", "input"));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Edit User link enabled.
     * Return True if enabled.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean elementUserEditClickable() throws Exception {
    	
    	if (isExists(userEditLoc, 3)) {
    		return true;
    	}
    	if (isExists(userEnableEditLoc, 3)) {
    		return true;
    	}
    	return false;
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Edit Business Information link enabled.
     * Return True if enabled.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean elementBusInfoEditClickable() throws Exception {

        if (isExists(busEditLoc, 3)) {
            return true;
        }
        return false;
    }

//--------------------------------------------------------------------------
    /**
     * Method: Check if Edit User Information link enabled.
     * Return True if enabled.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLnkEditUserInf() throws InterruptedException {
        click(userEditLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Edit Business Information link.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLnkEditBusInf() throws InterruptedException {
        click(busEditLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Enable link.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLnkEnable() throws InterruptedException {
    	click(lnkChangePasswordLoc);
    }
}


