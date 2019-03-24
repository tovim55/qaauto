package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class CreateSegmentPage extends BasePage {

    private final static String url = "";
    private final static String title = "Create Segment Groups";

    private By fldSegmentNameLoc = By.name("name");
    private By fldSegmentCodeLoc = By.name("code");
    private By titleCreateSegmentLoc = By.xpath("//div[text()='Create segment']");
    private By btnSaveLoc = By.xpath("//span[text()='Save']");

    private By msgConfirmationLoc = By.xpath("//*[@class='adb-local_alert--content']");

    public CreateSegmentPage() {
        super(url, title);
    }

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
    /**
     * Method: Title 'Create segment' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsTitleCreateSegmentGroup () throws Exception {
        return isExists(titleCreateSegmentLoc, 20);
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

}

