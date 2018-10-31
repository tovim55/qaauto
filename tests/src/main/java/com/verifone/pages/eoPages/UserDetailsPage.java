package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on User Details page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class UserDetailsPage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By titleLoc = By.xpath("//*[@class='top-container']");
    private By userNameLoc = By.xpath("(//*[@class='control-value'])[1]");
    private By userEmaulLoc = By.xpath("(//*[@class='control-value'])[2]");
//    private By userEditLoc = By.xpath("(//*[@class='pull-right text-muted'])[1]");
    private By userEditLoc = By.xpath("//*[@class='pull-right edit-user']");
//    private By roleEditLoc = By.xpath("(//*[@class='pull-right text-muted'])[2]");
    private By roleEditLoc = By.xpath("//*[@class='pull-right edit-role']");
    private By userEnableEditLoc = By.xpath("//*[@class='pull-right edit-user']");
    private By roleEnableEditLoc = By.xpath("//*[@class='pull-right edit-role']");
    private By userStatusLoc = By.xpath("//*[@class='label label-info']");
    private By userActiveStatusLoc = By.xpath("//*[@class='label label-success']");
    private By userDisabledStatusLoc = By.xpath("//*[@class='label label-default']");
    private By userActionLoc = By.xpath("//*[@class='action']");
    private By roleLoc = By.xpath("(//*[@class='control-value'])[4]");
    private By lnkResendLoc = By.xpath("//*[@class='resend-invitation']");
    private By lnkDisableLoc = By.xpath("//*[@class='disable-user']");
    private By lnkEnableLoc = By.xpath("//*[@class='enable-user']");
    private By messageLoc = By.xpath("//*[@class='message success']");
    
    private By dialogResendLoc = By.xpath("(//*[@class='modal-dialog  '])[3]");
    private By dialogResendTextLoc = By.xpath("(//*[@class='modal-body scroll'])[3]");
    private By dialogCancelBtnLoc = By.id("resendEmailModalNegateId");
    private By dialogResendBtnLoc = By.id("resendEmailModalAffirmId");
    
    private By dialogDisableLoc = By.xpath("(//*[@class='modal-dialog  '])[1]");
    private By dialogDisableTextLoc = By.xpath("(//*[@class='modal-body scroll'])[1]");
    private By dialogDisableCancelBtnLoc = By.id("disableUserModalNegateId");
    private By dialogDisableBtnLoc = By.id("disableUserModalAffirmId");
    
    private By dialogEnableLoc = By.xpath("(//*[@class='modal-dialog  '])[2]");
    private By dialogEnableTextLoc = By.xpath("(//*[@class='modal-body scroll'])[2]");
    private By dialogEnableCancelBtnLoc = By.id("enableUserModalNegateId");
    private By dialogEnableBtnLoc = By.id("enableUserModalAffirmId");

    public UserDetailsPage() {
        super(url, title);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Wait 5 sec for Email value displayed and Get Email value.
     * Return Email value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getUserEmail() throws InterruptedException {
        int t = 0;
        while (getText(userEmaulLoc).length()<=3 & t < 5000){
            Thread.sleep(500);
            t = t + 500;
        }
        return getText(userEmaulLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Name value.
     * Return Name value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getUserName() throws InterruptedException {
        return getText(userNameLoc);
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
     * Method: Get Status value.
     * Return Status value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getStatus() throws Exception {
    	if (isExists(userStatusLoc, 3)) {
    		return getText(userStatusLoc);
    	}
    	else if (isExists(userActiveStatusLoc, 3)) {
    		return getText(userActiveStatusLoc);
    	}
    	else if (isExists(userDisabledStatusLoc, 3)) {
    		return getText(userDisabledStatusLoc);
    	}
    	return "";
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Action value.
     * Return Action value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getAction() throws InterruptedException {
        return getText(userActionLoc);
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
     * Method: Get Resend Invitation dialog text.
     * Return Resend Invitation dialog text as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getDialogResend() throws InterruptedException {
        return getText(dialogResendTextLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Disable dialog text.
     * Return Disable dialog text as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getDialogDisable() throws InterruptedException {
        return getText(dialogDisableTextLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Enable dialog text.
     * Return Enable dialog text as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getDialogEnable() throws InterruptedException {
        return getText(dialogEnableTextLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Email field enabled.
     * Return True if enabled.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean elementEmailClickable() throws InterruptedException {
    	WebElement elem = driver.findElement(userEmaulLoc);
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
     * Method: Check if Role field enabled.
     * Return True if enabled.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean elementRoleClickable() throws InterruptedException {
    	WebElement elem = driver.findElement(roleLoc);
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
     * Method: Check if Edit Role link enabled.
     * Return True if enabled.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean elementRoleEditClickable() throws Exception {
    	if (isExists(roleEditLoc, 3)) {
    		return true;
    	}
    	if (isExists(roleEnableEditLoc, 3)) {
    		return true;
    	}
    	return false;
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Edit User Information link .
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLnkEditUserInf() throws InterruptedException {
        click(userEditLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Edit Role link .
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLnkEditRole() throws InterruptedException {
        click(roleEditLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Resend Invitation link .
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLnkResend() throws InterruptedException {
    	click(lnkResendLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Disable User link .
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLnkDisable() throws InterruptedException {
    	click(lnkDisableLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Enable User link .
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLnkEnable() throws InterruptedException {
    	click(lnkEnableLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Resend Invitation dialog displayed.
     * Return True if displayed.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean dialogResendExists() throws Exception {
    	return isExists(dialogResendLoc, 3);  	
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click Cancel on Resend Invitation dialog.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickCancelResend() throws InterruptedException {
    	click(dialogCancelBtnLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click Resend on Resend Invitation dialog.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickDoResend() throws InterruptedException {
    	click(dialogResendBtnLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if User Disable dialog displayed.
     * Return True if displayed.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean dialogDisableExists() throws Exception {
    	return isExists(dialogDisableLoc, 3);  	
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click Cancel on User Disable dialog.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickCancelDisable() throws InterruptedException {
    	click(dialogDisableCancelBtnLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click Disable on User Disable dialog.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickDoDisable() throws InterruptedException {
    	click(dialogDisableBtnLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if User Enable dialog displayed.
     * Return True if displayed.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean dialogEnableExists() throws Exception {
    	return isExists(dialogEnableLoc, 3);  	
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click Cancel on User Enable dialog.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickCancelEnable() throws InterruptedException {
    	click(dialogEnableCancelBtnLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click Enable on User Enable dialog.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickDoEnable() throws InterruptedException {
    	click(dialogEnableBtnLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if Message displayed.
     * Return True if displayed.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean messageExists() throws Exception {
    	return isExists(messageLoc, 30);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Message text.
     * Return Message text as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getMessage() throws InterruptedException {
        return getText(messageLoc);
    }
}


