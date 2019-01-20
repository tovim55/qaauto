package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on Edit Profile page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EditProfilePage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By titleLoc = By.xpath("//*[@class='content-header']");
    private By firstNameLoc = By.name("firstName");
    private By lastNameLoc = By.name("lastName");
    private By emailLoc = By.xpath("//*[@class='control-value']");
    private By errorFirstNameLoc = By.xpath("//*[@class='help-block']");
    private By errorLastNameLoc = By.xpath("(//*[@class='help-block'])[2]");
    private By btnCancelLoc = By.id("cancelBtn");
    private By btnSaveLoc = By.id("saveBtn");


    public EditProfilePage() {
        super(url, title);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Email value.
     * Return Email value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getEmail() throws InterruptedException {
        return getText(emailLoc);
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
     * Method: Wait for 5 sec till First Name display value and get this value.
     * Return First Name value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getfirstName() throws InterruptedException {
        int t = 0;
        while (driver.findElement(firstNameLoc).getAttribute("value").length()<1 & t < 5000){
            Thread.sleep(500);
            t = t + 500;
        }
        return driver.findElement(firstNameLoc).getAttribute("value");
    }

    public String getlastName() throws InterruptedException {
        return driver.findElement(lastNameLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Cancel button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnCancel() throws InterruptedException {
        click(btnCancelLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Save button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSave() throws InterruptedException {
        click(btnSaveLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Update First Name value.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void updateFirstName(String uFName)  throws Exception {
        sendKeysNoClear(firstNameLoc, uFName);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Replace First Name value.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputFirstName(String uFName)  throws Exception {
        sendKeys(firstNameLoc, uFName);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Update Last Name value.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void updateLastName(String uLName)  throws Exception {
        sendKeysNoClear(lastNameLoc, uLName);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Replace Last Name value.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputLastName(String uLName)  throws Exception {
        sendKeys(lastNameLoc, uLName);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on First Name field.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickFirstNameFld () throws InterruptedException {
        click(firstNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Last Name field.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLastNameFld () throws InterruptedException {
        click(lastNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get First Name validation error.
     * Return First Name validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorFirstName () throws InterruptedException {
        return getText(errorFirstNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Last Name validation error.
     * Return Last Name validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorLastName () throws InterruptedException {
        return getText(errorLastNameLoc);
    }

}


