package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EditSegmentPage extends BasePage {

    private final static String url = "";
    private final static String title = "Create Segment Groups";

    private By fldSegmentNameLoc = By.name("name");
    private By fldSegmentCodeLoc = By.name("code");
    private By fldSearchCompanyLoc = By.xpath("//*[@placeholder='Search']");
    private By btnSearchCompanyLoc = By.xpath("//*[@type='button' and @aria-label='search']");
    private By titleEditSegmentLoc = By.xpath("//div[text()='Edit segment']");
    private By btnSaveLoc = By.xpath("//span[text()='Save']");
    private By tblCompaniesLoc = By.xpath("//*[@style='border-collapse: collapse; border-spacing: 0px; width: 100%;']");
    private By chkBoxCompanyLoc = By.xpath("//*[@type=\"checkbox\"]");
    private By btnConfirmLoc = By.xpath("//span[text()='Confirm']");

    private By msgConfirmationLoc = By.xpath("//*[@class='adb-local_alert--content']");

    public EditSegmentPage() {
        super(url, title);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Segment Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String GetSegmentName()  throws Exception {
        return getValue(fldSegmentNameLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Get Segment Code.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String GetSegmentCode()  throws Exception {
        return getValue(fldSegmentCodeLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Input Segment Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputSegmentName(String SName)  throws Exception {
        sendKeys(fldSegmentNameLoc, SName);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Input Segment Code.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputSegmentCode(String SName)  throws Exception {
        sendKeys(fldSegmentCodeLoc, SName);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Input Segment Code.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputSearchCompany(String SName)  throws Exception {
        sendKeys(fldSearchCompanyLoc, SName);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Title 'Create segment' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsTitleEditSegment () throws Exception {
        return isExists(titleEditSegmentLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Search button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSearchCompany () throws InterruptedException {

        click(btnSearchCompanyLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Search button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickChkBoxCompany () throws InterruptedException {

        click(chkBoxCompanyLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Table 'Add|Remove companies' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsTblCompanies () throws Exception {
        return isExists(tblCompaniesLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Button Confirm exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsBtnConfirm () throws Exception {
        return isExists(btnConfirmLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Save button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSave () throws InterruptedException {

        click(btnSaveLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Get Message Text.
     * Return Text as string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String msgConfirmationText () throws InterruptedException {
        return getText(msgConfirmationLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Confirm button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnConfirm () throws InterruptedException {

        click(btnConfirmLoc);
    }
//--------------------------------------------------------------------------

}

