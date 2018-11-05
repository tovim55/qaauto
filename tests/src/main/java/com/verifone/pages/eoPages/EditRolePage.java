package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on Edit Role page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EditRolePage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By titleLoc = By.xpath("//*[@class='content-header']");
    private By roleLoc = By.xpath("//*[@class='form-control i-validator select-role']");

    private By btnCancelLoc = By.id("cancelBtn");
    private By btnSaveLoc = By.id("createBtn");


    public EditRolePage() {
        super(url, title);
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
     * Method: Select Role.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void updateRole(String uRole)  throws Exception {
        select(roleLoc, uRole);
    }

}


